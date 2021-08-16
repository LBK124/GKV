package gKVClient.MainWindow.CellarTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cellarImportDeliveryManually extends javax.swing.JDialog{
    public JPanel ContentPane;
    private JButton ButtonImport;
    private JButton ButtonCancel;

    private cellarImportTemplate DrinkPanes[];
    private JPanel JPanelDrinks;

    private int amount;

    public cellarImportDeliveryManually(int amount, String names[]){
        this.amount = amount;

        loadPanels(amount, names);
        //Knopf Einbinden
        ButtonImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getResults();
                dispose();
            }
        });
        //Knopf Abbrechen
        ButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
    private void loadPanels (int amount, String names[]){
        setContentPane(ContentPane);
        //erzeugt die nötigen Panels zum einbinden
        DrinkPanes = new cellarImportTemplate[amount];
        for(int i = 0; i < amount; ++i){
            DrinkPanes[i] = new cellarImportTemplate(names[i]);
            DrinkPanes[i].setImport();
        }

        //übergibt dem HauptPanel ein passendes Layout
        JPanelDrinks.setLayout(new GridLayout(amount,1));

        //bindest die erzeugten panels ein
        for (int i = 0; i < amount; ++i){
            JPanelDrinks.add(DrinkPanes[i].ContentPane);
        }
    }

    private void getResults(){
        int res[] = new int[amount];
        //speichert alle eingegebenen daten im feld res
        for(int i = 0; i < amount; ++i) {
            res[i] = DrinkPanes[i].getAmount();
        }
        //übergabe der Werte

    }
}
