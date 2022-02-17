package presentation.view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LogInView extends JFrame {

    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel paswLabel;
    private JTextField usernameText;
    private JTextField paswText;
    private JButton LogInButton;
    private JButton AdminButton;
    private JButton RegisterButton;
    private JButton EmployeeButton;



    public LogInView() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 1000, 700);
        this.getContentPane().setLayout(null);

        // use a bigger font
        Font biggerFont = new Font("Times New Roman", Font.PLAIN, 18);
        Font hugeFont = new Font("Times New Roman",Font.PLAIN,32);

        titleLabel = new JLabel("User operations");
        titleLabel.setFont(hugeFont);
        titleLabel.setBounds(300,50,450,50);
        getContentPane().add(titleLabel);


        LogInButton = new JButton("LogIn");
        LogInButton.setFont(biggerFont);
        LogInButton.setBounds(100,150,150,50);
        getContentPane().add(LogInButton);

        RegisterButton = new JButton("Register");
        RegisterButton.setFont(biggerFont);
        RegisterButton.setBounds(300,150,150,50);
        getContentPane().add(RegisterButton);

        AdminButton = new JButton("AdminView");
        AdminButton.setFont(biggerFont);
        AdminButton.setBounds(500,150,150,50);
        getContentPane().add(AdminButton);

        EmployeeButton = new JButton("EmployeeView");
        EmployeeButton.setFont(biggerFont);
        EmployeeButton.setBounds(700,150,200,50);
        getContentPane().add(EmployeeButton);

        usernameLabel = new JLabel("UserName");
        usernameLabel.setBounds(300,250,150,50);
        getContentPane().add(usernameLabel);

        paswLabel = new JLabel("Password");
        paswLabel.setBounds(300,300,300,50);
        getContentPane().add(paswLabel);

        usernameText = new JTextField();
        usernameText.setBounds(500,250,300,50);
        getContentPane().add(usernameText);

        paswText = new JTextField();
        paswText.setBounds(500,300,300,50);
        getContentPane().add(paswText);
    }


    public String getUsername() {
        return usernameText.getText();
    }

    public String getPassword(){
        return paswText.getText();
    }

    public void addEmployeeButtonActionListener(ActionListener actionListener)
    {
        EmployeeButton.addActionListener(actionListener);
    }

    public void addAdminButtonActionListener(ActionListener actionListener)
    {
        AdminButton.addActionListener(actionListener);
    }

    public void addRegisterButtonActionListener(ActionListener actionListener)
    {
        RegisterButton.addActionListener(actionListener);
    }

    public void addLogInButtonActionListener(ActionListener actionListener)
    {
        LogInButton.addActionListener(actionListener);
    }
}