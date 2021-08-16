package gKVClient.MainWindow.CellarTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cellarControlPanel extends javax.swing.JPanel {
    public JPanel ContentPane;

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton buttonShowBill;
    private JButton buttonExport;


    public cellarControlPanel() {
        //Klick auf Zeige Rechnung
        buttonShowBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //Klick auf Exportieren
        buttonExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
