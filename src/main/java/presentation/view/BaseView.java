
package presentation.view;

import business.BaseProduct;
import business.Client;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;


public class BaseView extends JFrame {

    private JLabel titleLabel;
    private JLabel keywordLabel;
    private JLabel raitingLabel;
    private JLabel nrcaloriesLabel;
    private JLabel proteinsLabel;
    private JLabel fatsLabel;
    private JLabel sodiuLabel;
    private JLabel priceLabel;
    private JTextField keywordText;
    private JTextField raitingText;
    private JTextField nrcaloriesText;
    private JTextField proteinsText;
    private JTextField fatsText;
    private JTextField sodiumText;
    private JTextField priceText;


    private JButton backButton;
    private JButton addButton;
    private JButton editButton;


    private JButton showInfoButton;



    public BaseView(boolean edit, BaseProduct baseProduct) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 1200, 700);
        this.getContentPane().setLayout(null);

        Font biggerFont = new Font("Times New Roman", Font.PLAIN, 18);
        Font hugeFont = new Font("Times New Roman", Font.PLAIN, 32);

        titleLabel = new JLabel("Base operations");
        titleLabel.setFont(hugeFont);
        titleLabel.setBounds(300, 50, 450, 50);
        getContentPane().add(titleLabel);

        addButton = new JButton("Add");
        addButton.setFont(biggerFont);
        addButton.setBounds(50, 550, 150, 50);
        getContentPane().add(addButton);

        editButton = new JButton("Edit");
        editButton.setFont(biggerFont);
        editButton.setBounds(300, 550, 150, 50);
        getContentPane().add(editButton);


        keywordText = new JTextField();
        keywordText.setBounds(200,100,200,30);
        getContentPane().add(keywordText);

        keywordLabel = new JLabel("KeyWord");
        keywordLabel.setBounds(100,100,200,30);
        getContentPane().add(keywordLabel);

        raitingText = new JTextField();
        raitingText.setBounds(200,140,200,30);
        getContentPane().add(raitingText);

        raitingLabel = new JLabel("Raiting");
        raitingLabel.setBounds(100,140,200,30);
        getContentPane().add(raitingLabel);

        nrcaloriesText = new JTextField();
        nrcaloriesText.setBounds(200,180,200,30);
        getContentPane().add(nrcaloriesText);

        nrcaloriesLabel = new JLabel("NrOfCalories");
        nrcaloriesLabel.setBounds(100,180,200,30);
        getContentPane().add(nrcaloriesLabel);

        proteinsText = new JTextField();
        proteinsText.setBounds(200,220,200,30);
        getContentPane().add(proteinsText);

        proteinsLabel = new JLabel("Proteins");
        proteinsLabel.setBounds(100,220,200,30);
        getContentPane().add(proteinsLabel);

        fatsText = new JTextField();
        fatsText.setBounds(200,260,200,30);
        getContentPane().add(fatsText);

        fatsLabel = new JLabel("Fats");
        fatsLabel.setBounds(100,260,200,30);
        getContentPane().add(fatsLabel);

        sodiumText = new JTextField();
        sodiumText.setBounds(200,300,200,30);
        getContentPane().add(sodiumText);

        sodiuLabel= new JLabel("Sodium");
        sodiuLabel.setBounds(100,300,200,30);
        getContentPane().add(sodiuLabel);

        priceText = new JTextField();
        priceText.setBounds(200,340,200,30);
        getContentPane().add(priceText);

        priceLabel= new JLabel("Price");
        priceLabel.setBounds(100,340,200,30);
        getContentPane().add(priceLabel);

        if(edit) {
            addButton.setVisible(false);
            keywordText.setEditable(false);
        }
        else
            editButton.setVisible(false);
    }

    public void addCreateButtonActionListener(ActionListener actionListener)
    {
        addButton.addActionListener(actionListener);
    }
    public void addEditButtonActionListener(ActionListener actionListener)
    {
        editButton.addActionListener(actionListener);
    }

    public String getTitle(){
        return keywordText.getText();
    }
    public void setTitle(String s) {
        keywordText.setText(s);
    }
    public String getPrice(){ return priceText.getText();}
    public String getRaiting(){return raitingText.getText(); }
    public String getCalories(){return nrcaloriesText.getText();}
    public String getProteins(){return proteinsText.getText();}
    public String getFats(){return fatsText.getText();}
    public String getSodium(){return sodiumText.getText();}
    public void setPrice(String s){ priceText.setText(s);}
    public void setRaiting(String s){ raitingText.setText(s);}
    public void setCalories(String s){ nrcaloriesText.setText(s);}
    public void setFats(String s){ fatsText.setText(s);}
    public void setSodium(String s){ sodiumText.setText(s);}
    public void setProteins(String s){ proteinsText.setText(s);}




}