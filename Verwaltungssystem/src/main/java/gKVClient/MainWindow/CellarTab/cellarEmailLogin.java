package gKVClient.MainWindow.CellarTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cellarEmailLogin extends javax.swing.JDialog{
    private JTextField textFieldCredential;
    private JButton ButtonCancel;
    private JButton ButtonLogin;
    private JPanel ContentPane;
    private JPasswordField passwordField;

    public cellarEmailLogin() {
        setContentPane(ContentPane);
        ButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        ButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
