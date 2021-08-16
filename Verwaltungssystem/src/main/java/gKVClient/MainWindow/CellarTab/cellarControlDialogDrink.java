package gKVClient.MainWindow.CellarTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cellarControlDialogDrink extends javax.swing.JPanel {
    public JPanel ContentPane;
    private JTextField textFieldName;
    private JTextField textFieldSetPoint;
    private JTextField textFieldActual;
    private JTextField textFieldPrice;
    private JButton ButtonAdd;
    private JButton ButtonDelete;
    private JButton ButtonEdit;
    private JComboBox comboBoxBottleType;
    private JComboBox comboBoxPackType;


    public cellarControlDialogDrink() {
        //KLick auf Getränk Bearbeiten
        ButtonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldName.isEditable()){
                    ButtonEdit.setText("Getränk Bearbeiten");
                    setEnable(false);
                }
                else{
                    ButtonEdit.setText("Getränk Speichern");
                    setEnable(true);
                }
            }
        });
        //Klick auf Lösche Getränk
        ButtonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //KLick auf neues Getränk hinzufügen
        ButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    //schaltet die Eigenschaften Felder auf editierbar oder nicht editierbar
    private void setEnable (Boolean state){
        textFieldName.setEditable(state);
        textFieldActual.setEditable(state);
        textFieldPrice.setEditable(state);
        textFieldSetPoint.setEditable(state);
        comboBoxBottleType.setEditable(state);
        comboBoxPackType.setEditable(state);

    }
}
