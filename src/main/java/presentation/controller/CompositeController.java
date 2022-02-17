package presentation.controller;

import business.CompositeProduct;
import business.DeliveryService;
import business.MenuItem;
import presentation.view.CompositeView;
import presentation.view.LogInView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompositeController {

    private CompositeView compositeView;
    public DeliveryService deliveryService;
    private LogInView logInView;


    public void start(LogInView logInView) {

        this.logInView = logInView;
        this.compositeView = new CompositeView();
        compositeView.setVisible(true);
        deliveryService = new DeliveryService();
        compositeView.setTable(new ArrayList<>(deliveryService.getMenu()));


        initializeUserInterfaceButtons();
    }

    public void initializeUserInterfaceButtons() {

        compositeView.addEditButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuItem> menuItems = new ArrayList<>();
                String[] rows = compositeView.getSelectedRows();
                int i=0;
                if(rows.length>0)
                    do {
                        for(MenuItem menuItem : deliveryService.getMenu()) {
                            if(rows[i].equals(menuItem.getTitle()))
                                menuItems.add(menuItem);
                        }
                        i++;
                    }while(i< rows.length);

                CompositeProduct compositeProduct = new CompositeProduct(compositeView.getTitle(), menuItems);

                deliveryService.addCompositeMenuItem(compositeView.getTitle(), menuItems);

            }
        });

    }
}
