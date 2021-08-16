package gKVClient.MainWindow.CellarTab;

import javax.swing.*;


public class cellarImportTemplate extends javax.swing.JPanel {
    private JTextField textFieldSetPoint;
    private JLabel LabelName;
    public JPanel ContentPane;
    private JTextField textFieldActuallPoint;
    private JComboBox comboBoxBottleType;
    private String bottle;
    private Boolean orderimport;

    public cellarImportTemplate(String name){
        LabelName.setText(name);

    }
    public cellarImportTemplate(String name, int setpoint, int actualpoint, String bottle){
        LabelName.setText(name);
        textFieldActuallPoint.setText(Integer.toString(actualpoint));
        textFieldSetPoint.setText(Integer.toString(setpoint));
        comboBoxBottleType.addItem(bottle);
        orderimport = false;
    }

    //----------------------------------getter----------------------------------
    public int getAmount(){
        return Integer.parseInt(textFieldSetPoint.getText());
    }
    public String getDrinkName(){
        return LabelName.getText();
    }

    public void setImport(){
        orderimport = true;
        comboBoxBottleType.setVisible(false);
        textFieldSetPoint.setVisible(false);
    }
}
