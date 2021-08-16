package gKVClient.MainWindow.UserControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userAddRemovePanel extends javax.swing.JPanel{
    public JPanel ContentPane;
    private JButton ButtonAddUser;
    private JButton ButtonRemoveUser;

    public userAddRemovePanel() {

        //KLick auf Benutzer hinzufügen
        ButtonAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userAddDialog addDialog = new userAddDialog();
                addDialog.pack();
                addDialog.setVisible(true);
            }
        });
        //Lick auf benutzer Löschen
        ButtonRemoveUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Keine Verbindung zum Server", "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
