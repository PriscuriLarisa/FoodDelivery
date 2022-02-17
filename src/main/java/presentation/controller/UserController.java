package presentation.controller;

import business.Client;
import business.DeliveryService;
import business.MenuItem;
import business.Order;
import presentation.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserController {

    private UserView userView;
    public DeliveryService deliveryService;
    private LogInView logInView;
    private Client user;

    public void start(Client user, LogInView logInView)
    {
        this.user = user;
        this.userView = new UserView(logInView, user);
        userView.setVisible(true);
        this.logInView = logInView;

        deliveryService = new DeliveryService();

        userView.setTable(new ArrayList<>(deliveryService.getMenu()));

        initializeUserInterfaceButtons();
    }

    public void initializeUserInterfaceButtons() {
        userView.addCreateOrderActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                deliveryService.addOrder(userView.getSelectedRows(), user.getUserId());
            }
        });

        userView.addSearchActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuItem> arrayList = deliveryService.searchProducts( userView.getTitle(),
                                                                                userView.getRating(),
                                                                                userView.getCalories(),
                                                                                userView.getProteins(),
                                                                                userView.getFats(),
                                                                                userView.getSodium(),
                                                                                userView.getPrice());

                userView.setTable(arrayList);


            }
        });

        userView.addShowInfoButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = userView.getSelectedRow();
                System.out.println(string);
                String s= "";
                for(MenuItem menuItem1 : deliveryService.getMenu()) {
                    if(menuItem1.getTitle().equals(string)) {
                        s+= menuItem1.toString();
                    }
                }
                userView.setInfo(s);

            }
        });


    }


}
