package gKVClient.MainWindow.UserControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userEdit extends javax.swing.JDialog{
    //GUI elements
    private JPanel ContentPane;
    //textFileds
    private JTextField textFieldSurName;
    private JTextField textFieldName;
    private JTextField textFieldID;
    private JTextField textFieldEmail;
    //checkBoxes
    private JCheckBox checkBoxKey;
    private JCheckBox checkBoxInactive;
    private JCheckBox checkBoxAdmin;
    private JCheckBox checkBoxMovedOut;
    //Buttons
    private JButton buttonSave;
    private JButton buttonCancel;
    private JButton buttonResetPSW;
    //Superkalsse
    private userControl parrentWindow;

    public void loadElement(String name, String surName, String id, String email, boolean admin, boolean key, boolean status, boolean movedOut){
        //Textboxen Füllen
        this.textFieldID.setText(id);
        this.textFieldName.setText(name);
        this.textFieldSurName.setText(surName);
        this.textFieldEmail.setText(email);
        //Flags in checkboxen setzen
        this.checkBoxAdmin.setSelected(admin);
        this.checkBoxInactive.setSelected(status);
        this.checkBoxKey.setSelected(key);
        this.checkBoxMovedOut.setSelected(movedOut);

    }
    //sendet die Änderungen an das Fenster userControl
    private void saveElement(){
        if(parrentWindow != null) {
            parrentWindow.getUserChanges(this.textFieldName.getText(),
                    this.textFieldSurName.getText(),
                    this.textFieldID.getText(),
                    this.textFieldEmail.getText(),
                    this.checkBoxAdmin.isSelected(),
                    this.checkBoxKey.isSelected(),
                    this.checkBoxInactive.isSelected(),
                    this.checkBoxMovedOut.isSelected());
        }
    }

    //Knopfdruck
    public userEdit(userControl pWin) {
        this.parrentWindow = pWin;
        setContentPane(ContentPane);
        //Abbrechen schließt aktuelles Fenster
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //Speichern sendet Daten an SuperForm und schließt das aktuelle Fenster
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveElement();
                dispose();
            }
        });
        //Setzt das Passwort des Nutzers zurück
        buttonResetPSW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Passwort erfolgreich zurückgesetzt", "Erfolgreich", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

}
