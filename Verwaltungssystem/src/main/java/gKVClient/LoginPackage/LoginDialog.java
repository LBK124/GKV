package gKVClient.LoginPackage;

import gKVClient.Clientcommunikation.Clientcommunikation;
import gKVClient.Main.MainThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginDialog extends javax.swing.JDialog{
    private JPanel ContentPane;
    private JTextField textFieldName;
    private JPasswordField passwordField;
    private JButton ButtonResetPassword;
    private JButton ButtonLoggin;


    public LoginDialog(MainThread parent,Clientcommunikation interf)  {

        setContentPane(ContentPane);
        ButtonResetPassword.setVisible(false);
        ButtonLoggin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ceckInput()){
                    String psw = new String(passwordField.getPassword());
                    switch(interf.login(textFieldName.getText(),psw)){
                        case LOGIN_SUCCESSFUL_USER:
                        case LOGIN_SUCCESSFUL_ADMIN:
                            parent.setLoggedUser(interf.getUser(textFieldName.getText()));
                            dispose();
                            break;
                        case CHANGE_PASSWORD:
                            initChangePSW(interf);
                            break;
                        case LOGIN_FAIL:
                            JOptionPane.showMessageDialog(null,"Passwort Falsch","Fehler beim Login",JOptionPane.ERROR_MESSAGE);
                            break;
                        case ERROR:
                            JOptionPane.showMessageDialog(null,"Benutzername Falsch","Fehler beim Login",JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                }
            }
        });
    }

    private boolean ceckInput(){
        if(textFieldName.getText().contentEquals("")){
            JOptionPane.showMessageDialog(null,"Benutzername Fehlt","Fehler beim Login",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(passwordField.getPassword().toString().contentEquals("")){
            JOptionPane.showMessageDialog(null,"Passwort Fehlt","Fehler beim Login",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    //
    private void initChangePSW(Clientcommunikation interf){
        ChangePassword changePassword = new ChangePassword(interf,textFieldName.getText());
        changePassword.pack();
        changePassword.setModal(true);
        changePassword.setVisible(true);
    }
}
