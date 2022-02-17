package presentation.controller;

import business.BaseProduct;
import business.DeliveryService;
import business.MenuItem;
import presentation.view.AdminView;
import presentation.view.BaseView;
import presentation.view.LogInView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BaseController {
    private BaseView baseView;
    public DeliveryService deliveryService;
    private LogInView logInView;


    public void start(LogInView logInView, boolean edit, BaseProduct baseProduct) {

        this.logInView = logInView;
        this.baseView = new BaseView(edit, baseProduct);
        baseView.setVisible(true);
        deliveryService = new DeliveryService();

        if(edit)
        {
            baseView.setTitle(baseProduct.getTitle());
            baseView.setCalories(String.valueOf(baseProduct.getCalories()));
            baseView.setRaiting(String.valueOf(baseProduct.getRating()));
            baseView.setSodium(String.valueOf(baseProduct.getSodium()));
            baseView.setFats(String.valueOf(baseProduct.getFat()));
            baseView.setProteins(String.valueOf(baseProduct.getProtein()));
            baseView.setPrice(String.valueOf(baseProduct.getPrice()));

        }

        initializeUserInterfaceButtons();
    }

    public void initializeUserInterfaceButtons() {
        baseView.addCreateButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                deliveryService.addBaseMenuItem(baseView.getTitle(),
                        baseView.getRaiting(),
                        baseView.getCalories(),
                        baseView.getProteins(),
                        baseView.getFats(),
                        baseView.getSodium(),
                        baseView.getPrice());
            }
        });

        baseView.addEditButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.editMenuItem(baseView.getTitle(),
                        baseView.getRaiting(),
                        baseView.getCalories(),
                        baseView.getProteins(),
                        baseView.getFats(),
                        baseView.getSodium(),
                        baseView.getPrice());

            }
        });


    }
}
