package gKVClient.MainWindow.CellarTab;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cellarInventory extends javax.swing.JDialog {
    private JButton speichernButton;
    private JButton abbrechenButton;
    private JButton inventarlisteDownloadenButton;
    private JPanel JPanelList;
    private JPanel ContentPane;
    private cellarImportTemplate JPanelTemplate[];
    private int amount;

    public cellarInventory(int amount, String name[], int setpoint[], int actualpoint[],String bottle[]) {
        this.amount = amount;
        loadPanels(name,setpoint,actualpoint,bottle);

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadPanels(String name[], int setpoint[], int actualpoint[], String  bottle[]){
        setContentPane(ContentPane);
        //Panels anlegen
        JPanelTemplate = new cellarImportTemplate[amount];
        for(int i = 0; i<amount;++i){
            JPanelTemplate[i] = new cellarImportTemplate(name[i],setpoint[i],actualpoint[i],bottle[i]);
        }
        //Layout festsetzen
        JPanelList.setLayout(new GridLayout(amount,1));
        //Panels hinzufügen
        for(int i = 0; i < amount; ++i){
            JPanelList.add(JPanelTemplate[i].ContentPane);
        }

    }
}
