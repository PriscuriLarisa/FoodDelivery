package presentation.view;

import business.Client;
import business.MenuItem;
import presentation.view.CompositeView;
import presentation.view.LogInView;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AdminView extends JFrame {

    private JLabel titleLabel;
    private CompositeView compositeView;

    private JButton backButton;
    private JButton createBaseButton;
    private JButton createCompositeButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton generateReportButton;

    private JTable objectsTable;



    public AdminView(LogInView logInView) {



        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 900, 700);
        this.getContentPane().setLayout(null);

        Font biggerFont = new Font("Times New Roman", Font.PLAIN, 18);
        Font hugeFont = new Font("Times New Roman", Font.PLAIN, 32);

        objectsTable = new JTable();
        objectsTable.setBounds(500, 100, 300, 400);
        objectsTable.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(objectsTable);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(100, 100, 300, 400);
        panel.add(scrollPane);
        getContentPane().add(panel);

        titleLabel = new JLabel("Admin View");
        titleLabel.setFont(hugeFont);
        titleLabel.setBounds(300, 50, 450, 50);
        getContentPane().add(titleLabel);

        backButton = new JButton("Back");
        backButton.setBounds(750, 550, 100, 50);
        getContentPane().add(backButton);

        createBaseButton = new JButton("CreateBase");
        createBaseButton.setBounds(20, 550, 125, 50);
        getContentPane().add(createBaseButton);

        createCompositeButton = new JButton("CreateComposite");
        createCompositeButton.setBounds(160, 550, 125, 50);
        getContentPane().add(createCompositeButton);


        deleteButton = new JButton("Delete");
        deleteButton.setBounds(300, 550, 125, 50);
        getContentPane().add(deleteButton);


        editButton = new JButton("Edit");
        editButton.setBounds(450, 550, 125, 50);
        getContentPane().add(editButton);


        generateReportButton = new JButton("GenerateReport");
        generateReportButton.setBounds(600, 550, 125, 50);
        getContentPane().add(generateReportButton);

    }

    public void setTable(ArrayList<MenuItem> menu) {
        String[] fields = {"Denumire produs"};
        Object[][] data = new Object[menu.size()][fields.length];
        for(int i=0;i<menu.size();i++) {
            data[i][fields.length-1] = menu.get(i).getTitle();
        }
        DefaultTableModel tableModel = (DefaultTableModel) objectsTable.getModel();
        tableModel.setDataVector(data, fields);
        objectsTable.setModel(tableModel);
    }

    public String getSelectedRow(){
        DefaultTableModel model = (DefaultTableModel)objectsTable.getModel();

        int columns = objectsTable.getColumnCount();
        int row = objectsTable.getSelectedRow();
        String[] value = new String[columns];
        for(int i=0;i<columns; i++) {
            value[i] = objectsTable.getModel().getValueAt(row, i).toString();
        }
        return value[0];
    }

    public void addDeleteActionListener(ActionListener actionListener)
    {
        deleteButton.addActionListener(actionListener);
    }
    public void addCreateBaseActionListener(ActionListener actionListener)
    {
        createBaseButton.addActionListener(actionListener);
    }
    public void addCreateCompActionListener(ActionListener actionListener)
    {
        createCompositeButton.addActionListener(actionListener);
    }

    public void addEditActionListener(ActionListener actionListener) {
        editButton.addActionListener(actionListener);
    }

    public void setGenerateReportButton(ActionListener actionListener) {
        generateReportButton.addActionListener(actionListener);
    }


}