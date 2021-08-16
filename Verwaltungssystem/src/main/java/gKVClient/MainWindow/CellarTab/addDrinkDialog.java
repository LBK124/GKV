package gKVClient.MainWindow.CellarTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gKVClient.Utils.utils;

public class addDrinkDialog extends javax.swing.JDialog {
    private JPanel ContentPane;
    private JTextField textFieldName;
    private JTextField textFieldPrice;
    private JTextField textFieldSetPoint;
    private JTextField textFieldActualPoint;
    private JComboBox comboBoxBottleTyp;
    private JComboBox comboBoxPackType;
    private JButton ButtonCancel;
    private JButton ButtonAdd;

    private utils tools;

    public addDrinkDialog (){
        setContentPane(ContentPane);
        tools = new utils();
        //Klick auf Bestellen
        ButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Getränk zum Kellerinventar hinzufügen?","Getränk hinzufügen?", 2);
                if(result == 0){
                    dispose();
                }
            }
        });
        //Klick auf Abbruch
        ButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    //Konstruktor für vorausgefüllte Makse
    public addDrinkDialog (String Name , String bottleType, int packType, int price) {
        textFieldName.setText(Name);
        comboBoxBottleTyp.setSelectedIndex(0);
        comboBoxPackType.setSelectedIndex(0);
        textFieldPrice.setText(tools.intToString(price));
        textFieldSetPoint.setText("0");
        textFieldActualPoint.setText("0");
    }
}
