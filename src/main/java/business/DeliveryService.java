package business;

import data.FileWriter;
import data.Serializator;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements  IDeliveryServiceProcessing {

    private Serializator serializator = new Serializator();
    //data stored
    private List<MenuItem> menu = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<>();

    public DeliveryService (){
        serializator = new Serializator();
        //getFirstProducts();
        deserialize();

    }

    public void getFirstProducts(){
        menu = serializator.deserializeInitial();
        this.serialize();
    }

    public void deserialize(){
        serializator.deserialize(this);
    }

    public void serialize(){
        serializator.serialize(menu, clients, orders);
    }

    @Override
    public boolean register(String username, String password) {
        assert isWellFormed();
        assert !username.isEmpty();
        assert !password.isEmpty();
        int id = clients.size()+1;
        for(Client client : clients) {
            if(client.getUsername().equals(username))
                return false;
        }
        Client client = new Client(id, username, password);
        clients.add(client);
        serialize();
        assert isWellFormed();
        for(Client client1 : clients)
            System.out.println(client1.getUsername());
        return true;
    }

    @Override
    public Client logIn(String username, String password) {
        assert !username.isEmpty();
        assert !password.isEmpty();
        assert isWellFormed();
        for(Client client : clients) {
            if(client.getUsername().equals(username) && client.getPassword().equals(password))
                assert isWellFormed();
                return client;
        }
        assert isWellFormed();
        return null;
    }

    @Override
    public void addBaseMenuItem(String title, String rating, String calories, String proteins, String fats, String sodium, String price) {
        assert isWellFormed();
        BaseProduct menuItem = new BaseProduct( title,
                                                Double.valueOf(rating),
                                                Integer.valueOf(calories),
                                                Integer.valueOf(proteins),
                                                Integer.valueOf(fats),
                                                Integer.valueOf(sodium),
                                                Integer.valueOf(price));
        menu.add(menuItem);
        serialize();
        assert isWellFormed();
    }

    @Override
    public void addCompositeMenuItem(String title, ArrayList<MenuItem> menuItems) {
        assert isWellFormed();
        System.out.println(title);
        for(MenuItem menuItem: menuItems){
            System.out.println((menuItem.getTitle()));
        }
        MenuItem menuItem = new CompositeProduct(title, menuItems);
        menu.add(menuItem);
        serialize();
        assert isWellFormed();
    }

    @Override
    public void editMenuItem(String title, String rating, String calories, String proteins, String fats, String sodium, String price) {

        assert isWellFormed();
        BaseProduct menuItem = new BaseProduct(  title,
                Double.valueOf(rating),
                Integer.valueOf(calories),
                Integer.valueOf(proteins),
                Integer.valueOf(fats),
                Integer.valueOf(sodium),
                Integer.valueOf(price));
        MenuItem tmp = null;
        for(MenuItem menuItem1 : menu) {
            if(menuItem1.getTitle().equals(menuItem.getTitle())) {
                tmp = menuItem1;
            }
        }
        menu.remove(tmp);
        menu.add(menuItem);
        serialize();
        assert isWellFormed();
    }

    @Override
    public void deleteMenuItem(String menuItem) {
        assert isWellFormed();
        assert !menuItem.isEmpty();
        MenuItem tmp = null;
        for(MenuItem menuItem1 : menu) {
            if(menuItem1.getTitle().equals(menuItem)) {
                tmp = menuItem1;
            }
        }

        menu.remove(tmp);
        serialize();
        assert isWellFormed();
    }

    @Override
    public void addOrder(String[] rows, int id){
        assert isWellFormed();
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        for(String string : rows) {
            for(MenuItem menuItem : menu) {
                if(string.equals(menuItem.getTitle()))
                    menuItems.add(menuItem);
            }
        }
        Order order = new Order(orders.size()+1, id, LocalDateTime.now());

        orders.put(order, menuItems);
        setChanged();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(orders);
        notifyObservers(objects);
        serialize();

        bill(order);

        assert isWellFormed();

    }

    @Override
    public void generateReports(ReportDate reportDate){
        assert isWellFormed();
        assert reportDate!=null;
        String s = "";
        List<Order> r1 = timeInterval(reportDate.getStartHour(), reportDate.getEndHour());
        List<MenuItem> r2 = orderedTimes(reportDate.getTimesOrdered());
        List<Client> r3 = clientsSelected(reportDate.getMinOrders(), reportDate.getMinPrice());
        Map<Object, Object> r4 = getWithinDay(reportDate.getDay(), reportDate.getMonth(), reportDate.getYear());
        s+="First report:\n";
        for(Order order : r1) {
            s+="Order number " + order.getOrderId()+ "\n";
        }
        s+="\nSecond report:\n";
        for(MenuItem menuItem : r2) {
            s+=menuItem.getTitle()+ "\n";
        }
        s+="\nThird report:\n";
        for(Client client : r3) {
            s+="Client number " + client.getUserId() + " - " + client.getUsername()+ "\n";
        }
        s+="\nFourth report:\n";
        for(Map.Entry<Object, Object> map : r4.entrySet()) {
            s+= ((MenuItem)map.getKey()).getTitle() + " x " +  map.getValue() + "\n";
        }
        writeReport(s);

        FileWriter fileWriter = new FileWriter();
        fileWriter.write("report.txt", s);

        assert isWellFormed();
    }

    public void writeReport(String s) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream("report.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.write(s);
        pw.close();
    }

    @Override
    public List<Order> timeInterval(int start, int end) {
        assert start<=end;
        List<Order> orderList = orders.entrySet().stream()
                .filter(map -> map.getKey().getHour() >= start && map.getKey().getHour() <= end)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return  orderList;
    }

    @Override
    public List<MenuItem> orderedTimes(int nb) {

        List<MenuItem> products = menu.stream()
                .filter(p -> (orders.entrySet().stream()
                        .filter(map -> map.getValue().stream()
                                .mapToInt(e -> e.getTitle().equals(p.getTitle()) ? 1 : 0).sum()>0)).count() > nb).collect(Collectors.toList());

        return products;
    }

    @Override
    public List<Client> clientsSelected(int nb, int price) {
        List<Order> ordersPrice = orders.entrySet().stream()
                .filter(map -> map.getValue().stream().mapToInt(p -> p.computePrice()).sum() > price)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        for(Order order : ordersPrice) {
            System.out.println(order.getOrderId() + "\n");
        }

        List<Client> clientsSelected = clients.stream()
                .filter(c -> ordersPrice.stream().mapToInt(o -> o.getClientId() == c.getUserId()? 1 : 0).sum() > nb)
                .collect(Collectors.toList());

        for(Client client : clientsSelected) {
            System.out.println(client.getUserId() + ": " + client.getUsername());
        }
        return clientsSelected;
    }

    @Override
    public Map<Object, Object> getWithinDay(int day, int month, int year){

        Map<Object, Object> products = menu.stream()
                .filter(p -> orders.entrySet().stream()
                        .filter(map -> map.getKey().getOrderDay()==day && map.getKey().getOrderMonth() == month && map.getKey().getOrderYear() == year)
                .filter(o -> o.getValue().stream().anyMatch(c -> c.getTitle().equals(p.getTitle()))).count()>0)
                .collect(Collectors.toMap(p -> p, p -> orders.entrySet().stream()
                        .filter(map -> map.getKey().getOrderDay()==day && map.getKey().getOrderMonth() == month && map.getKey().getOrderYear() == year)
                        .filter(o -> o.getValue().stream().anyMatch(c -> c.getTitle().equals(p.getTitle()))).count()));

        return products;
    }

    @Override
    public void bill(Order order){
        assert order!=null;
        FileWriter fileWriter = new FileWriter();
        fileWriter.bill(order, orders);
    }

    @Override
    public List<MenuItem> getMenu() {
        return menu;
    }

    @Override
    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }

    @Override
    public List<Client> getClients() {
        return clients;
    }

    @Override
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public HashMap<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    @Override
    public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
        this.orders = orders;
    }

    @Override
    public boolean isWellFormed() {
        for(MenuItem menuItem : menu)
            if (menuItem == null)
                return false;
        for (Map.Entry<Order, ArrayList<MenuItem>> order : orders.entrySet())
            if (order == null)
                return false;
        for (Client client : clients)
            if (client == null)
                return false;
        return true;
    }

    @Override
    public ArrayList<MenuItem> searchProducts(String title, String rating, String calories, String protein, String fat, String sodium, String price) {
        assert isWellFormed();

        ArrayList<MenuItem> menu = new ArrayList<>(this.menu);
        List<MenuItem> products = menu
                .stream()
                .filter(item -> (title.isEmpty() || item.getTitle().contains(title)) &&
                        (rating.isEmpty() || item.getRating() == Double.valueOf(rating)) &&
                        (calories.isEmpty() || item.getCalories() == Integer.valueOf(calories)) &&
                        (protein.isEmpty() || item.getProtein() == Integer.valueOf(protein)) &&
                        (fat.isEmpty() || item.getFat() == Integer.valueOf(fat)) &&
                        (sodium.isEmpty() || item.getSodium() == Integer.valueOf(sodium)) &&
                        (price.isEmpty() || item.computePrice() == Integer.valueOf(price)))
                .collect(Collectors.toList());

        assert isWellFormed();
        return new ArrayList<>(products);


    }
}
