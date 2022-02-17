package data;


import business.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Serializator {

    public List<MenuItem> deserializeInitial() {
        List<String[]> lines = null;
        try {
            lines = Files.lines(Paths.get("products.csv"))
                    .filter(line -> !line.startsWith("Title"))
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
            List<BaseProduct> itemsSet = lines.stream()
                    .map(a -> new BaseProduct(a[0], Double.valueOf(a[1]), Integer.valueOf(a[2]), Integer.valueOf(a[3])
                            , Integer.valueOf(a[4]), Integer.valueOf(a[5]), Integer.valueOf(a[6])))
                    .collect(Collectors.toList());

            Set<String> titleSet = new HashSet<>();
            // se filtreaza sa fie unice
            List<MenuItem> baseProducts = itemsSet.stream()
                    .filter(c -> titleSet.add(c.getTitle()))
                    .collect(Collectors.toList());

            return baseProducts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void serialize(List<MenuItem> menu, List<Client> clients, HashMap<Order, ArrayList<MenuItem>> order) {
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setMenu(menu);
        deliveryService.setClients(clients);
        deliveryService.setOrders(order);
        try{
            FileOutputStream fileOutput = new FileOutputStream("file.txt");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
            outputStream.writeObject(deliveryService.getMenu());
            outputStream.writeObject(deliveryService.getOrders());
            outputStream.writeObject(deliveryService.getClients());

            fileOutput.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize(DeliveryService deliveryService) {

        try {
            FileInputStream fileInput = new FileInputStream("file.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
            if(fileInput.available() != 0) {
                deliveryService.setMenu((List<MenuItem>) inputStream.readObject());
                deliveryService.setOrders((HashMap<Order, ArrayList<MenuItem>>) inputStream.readObject());
                deliveryService.setClients((List<Client>) inputStream.readObject());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
