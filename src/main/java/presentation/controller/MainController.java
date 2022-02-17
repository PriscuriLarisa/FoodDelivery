package presentation.controller;

import business.Client;
import business.DeliveryService;
import presentation.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MainController {

    private UserView userView;
    private DeliveryService deliveryService;
    private AdminView adminGUI;
    private EmployeeView employeeView;
    private LogInView logInView;
    private Client user;
    private CompositeView compositeView;
    private BaseView baseView;

    public void start()
    {
        logInView = new LogInView();

        userView = new UserView(logInView,user);
        employeeView = new EmployeeView(logInView);

        compositeView = new CompositeView();
        adminGUI = new AdminView(logInView);

        logInView.setVisible(true);


        initializeUserInterfaceButtons();
    }

    public void initializeUserInterfaceButtons()
    {
        logInView.addRegisterButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = logInView.getUsername();
                String password = logInView.getPassword();

                DeliveryService deliveryService = new DeliveryService();
                if(deliveryService.register(username, password))
                    System.out.println("True");
            }
        });

        logInView.addLogInButtonActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = logInView.getUsername();
                String password = logInView.getPassword();

                DeliveryService deliveryService = new DeliveryService();
                if(deliveryService.logIn(username, password) != null) {
                    user = deliveryService.logIn(username, password);
                    System.out.println("true");
                    logInView.setVisible(false);

                    UserController userController = new UserController();
                    userController.start(user, logInView);

                }
                else
                {
                    System.out.println("Date incorecte");
                }

            }
        });

        logInView.addAdminButtonActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                logInView.setVisible(false);
                AdminController adminController = new AdminController();
                adminController.start(logInView);


            }
        });

        logInView.addEmployeeButtonActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                logInView.setVisible(false);
                employeeView.setVisible(true);
            }
        });




    }


}