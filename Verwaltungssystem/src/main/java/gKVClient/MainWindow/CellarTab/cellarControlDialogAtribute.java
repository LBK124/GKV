package gKVClient.MainWindow.CellarTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cellarControlDialogAtribute extends javax.swing.JPanel {
    public JPanel ContenetPane;

    private JTextField textFieldHousePrice;
    private JButton ButtonSet;
    private JButton ButtonOkay;
    private JButton ButtonEmailTemplates;

    private cellarControlDialog parent;

    public cellarControlDialogAtribute(cellarControlDialog parent){
        this.parent = parent;
        //Klick Okay
        ButtonOkay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.dispose();
            }
        });
        //Button set
        ButtonSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Hausbeitrag noch setzen
                   int dest = Integer.parseInt(textFieldHousePrice.getText().replace(",", ""));
                }
                catch (NumberFormatException | NullPointerException nfe){
                    JOptionPane.showMessageDialog(null,"Eingabe Fehlerhaft");
                    textFieldHousePrice.setText("0,00");
                }
            }
        });
    }

}
