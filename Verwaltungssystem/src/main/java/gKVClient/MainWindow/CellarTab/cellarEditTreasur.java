package gKVClient.MainWindow.CellarTab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cellarEditTreasur extends javax.swing.JDialog {
    private JTextField textFieldTreasury;
    private JTextField textFieldEditValue;
    private JTextField textFieldReason;
    private JButton ButtonCancel;
    private JButton ButtonEnter;
    private JPanel ContentPane;
    private JScrollPane scrollPaneTreasur;
    private JPanel scrollPaneHeader;
    private JTable tableTreasur;
    private DefaultTableModel table_model;


    public cellarEditTreasur() {
        setContentPane(ContentPane);
        textFieldEditValue.setText("0,00");
        initList();
        //Knopf Buchen
        ButtonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int res = Integer.parseInt(textFieldEditValue.getText().replace(",", ""));
                }
                catch (NumberFormatException | NullPointerException nfe){
                    JOptionPane.showMessageDialog(null,"Eingabe fehlerhaft", "Fehler", JOptionPane.ERROR_MESSAGE);
                    textFieldEditValue.setText("0,00");
                    return;
                }
                if(textFieldReason.getText().length() == 0){
                    JOptionPane.showMessageDialog(null,"Verwendungszweck fehlt", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    addToList(textFieldEditValue.getText(),textFieldReason.getText());
                    //dispose();
                }
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
    private void initList(){
        String columnName[] = {"Betrag","Verwendungszweck"};
        String dummyRow[][] = {{"3,00", "Rechnug Max"},{"15,45", "Rechnug Mustermann"}, {"-30,45", "Bestellung Bellaris"}};
        //Header initialisieren

        //table der View hinzufügen
        tableTreasur = new JTable(dummyRow,columnName);

        table_model = new DefaultTableModel();
        table_model.addColumn("Betrag");
        table_model.addColumn("Verwendungszweck");
        table_model.addRow(dummyRow[0]);
        table_model.addRow(dummyRow[1]);
        table_model.addRow(dummyRow[2]);
        tableTreasur = new JTable(table_model);

        /*tableTreasur.setFillsViewportHeight(true);
        tableTreasur.setRowSelectionAllowed(true);
        tableTreasur.setColumnSelectionAllowed(false);
        tableTreasur.setCellSelectionEnabled(false);
        tableTreasur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);*/

        scrollPaneTreasur.setViewportView(tableTreasur);
        scrollPaneTreasur.updateUI();

    }
    //fügt einen neuen eintrag der Tabelle Hinzu
    private void addToList(String value, String reason){
        String temp[] = {value,reason};
        table_model.addRow(temp);
        scrollPaneTreasur.updateUI();
    }
}
