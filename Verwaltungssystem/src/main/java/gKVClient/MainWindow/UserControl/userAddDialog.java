package gKVClient.MainWindow.UserControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userAddDialog extends javax.swing.JDialog{
    private JPanel ContentPane;
    private JTextField textFieldSurname;
    private JTextField textFieldName;
    private JTextField textFieldEmail;
    private JButton benutzerAnlegenButton;
    private JButton abbrechenButton;
    private JComboBox comboBoxRoom;
    private JCheckBox schluesselCheckBox;
    private JCheckBox inaktivCheckBox;


    public userAddDialog() {
        setContentPane(ContentPane);
        String room[] = {"101", "102","103","104", "105"};
        for(int i = 0; i< room.length; ++i){
            comboBoxRoom.addItem(room[i]);
        }
        comboBoxRoom.setSelectedIndex(0);
        //Klick auf Benutzer Anlegen
        benutzerAnlegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldName.getText().length() == 0){
                    JOptionPane.showMessageDialog(null, "Name fehlt", "Fehler Eingabe fehlt",JOptionPane.ERROR_MESSAGE);
                }
                else if(textFieldSurname.getText().length() == 0){
                    JOptionPane.showMessageDialog(null, "Nachname fehlt", "Fehler Eingabe fehlt",JOptionPane.ERROR_MESSAGE);
                }
                else if(textFieldEmail.getText().length() == 0){
                    JOptionPane.showMessageDialog(null, "Email fehlt", "Fehler Eingabe fehlt",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    dispose();
                }
            }
        });

        //Klick auf Abbrechen
        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
