package data;

import business.DeliveryService;
import business.MenuItem;
import business.Order;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class FileWriter {

    public void write(String path, String text) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.write(text);
        pw.close();
    }

    public void bill(Order order, HashMap<Order, ArrayList<MenuItem>> orders) {
        DeliveryService deliveryService = new DeliveryService();
        ArrayList<MenuItem> menuItems = orders.get(order);
        int s = 0;
        for(MenuItem menuItem : menuItems) {
            s += menuItem.computePrice();
        }

        String bill = "Order number " + order.getOrderId() +"\n";
        for(MenuItem menuItem : menuItems) {
            bill += menuItem.getTitle()+ "\n";
        }
        bill+="\nTotal Price: " + s;
        write(new String("bill" + order.getOrderId() + ".txt"), bill);
    }
}
