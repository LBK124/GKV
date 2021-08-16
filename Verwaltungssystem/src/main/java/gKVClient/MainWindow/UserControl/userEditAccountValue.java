package gKVClient.MainWindow.UserControl;

import javax.swing.*;
import java.awt.event.*;


public class userEditAccountValue extends javax.swing.JDialog {
    private JPanel ContentPane;
    private JTextField textFieldOldValue;
    private JTextField textFieldBooking;
    private JTextField textFieldNewValue;
    private JButton ButtonBook;
    private JButton ButtonCancel;


    private userControl pWin;
    private int oldValue, newValue, diff;

    public userEditAccountValue(userControl parWin,int accVal) {
        this.pWin = parWin;
        //wählt sichtbares Panel aus
        setContentPane(ContentPane);

        this.oldValue = accVal;
        this.newValue = oldValue;
        this.diff = 0;

        UpdateEntry();


        // Knopf Buchen schreibt aktuellen Kontowert zurück an hauptfenster
        ButtonBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateEntry();
                pWin.getAccountValue(newValue);
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


        //update Listener ändert den neuen Kontostand wenn Tstelosgelassen wird
       textFieldBooking.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                diff = pWin.stringToInt(textFieldBooking.getText());
                UpdateEntry();
            }
        });
    }
    //Aktualliesiert Die TextFelder
    private void UpdateEntry(){
        textFieldOldValue.setText(pWin.tools.intToString(oldValue));
        textFieldBooking.setText(pWin.tools.intToString(diff));
        newValue = oldValue + diff;
        textFieldNewValue.setText(pWin.tools.intToString(newValue));
    }
}
