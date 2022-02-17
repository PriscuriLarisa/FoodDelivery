package presentation.controller;


import business.BaseProduct;
import business.DeliveryService;
import business.MenuItem;
import presentation.view.AdminView;
import presentation.view.LogInView;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminController {
    private AdminView adminView;
    public DeliveryService deliveryService;
    private LogInView logInView;


    public void start(LogInView logInView) {

        this.logInView = logInView;
        this.adminView = new AdminView(logInView);
        adminView.setVisible(true);
        deliveryService = new DeliveryService();
        adminView.setTable(new ArrayList<>(deliveryService.getMenu()));

        initializeUserInterfaceButtons();
    }

    public void initializeUserInterfaceButtons() {
        adminView.addDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = adminView.getSelectedRow();
                deliveryService.deleteMenuItem(s);

            }
        });

        adminView.addCreateBaseActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseController baseController = new BaseController();
                baseController.start(logInView, false, null);
            }
        });

        adminView.addCreateCompActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompositeController compositeController = new CompositeController();
                compositeController.start(logInView);
            }
        });
        adminView.addEditActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = adminView.getSelectedRow();
                BaseProduct baseProduct=null;
                for(MenuItem menuItem : deliveryService.getMenu()) {
                    if ((menuItem instanceof BaseProduct) && menuItem.getTitle().equals(title))
                        baseProduct = (BaseProduct) menuItem;
                }

                BaseController baseController = new BaseController();
                baseController.start(logInView, true, baseProduct);
            }
        });

        adminView.setGenerateReportButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateReportController reportController = new GenerateReportController();
                reportController.start();
            }
        });

    }
}
