package gKVClient.LoginPackage;

import gKVClient.Clientcommunikation.Clientcommunikation;
import gKVClient.Main.MainThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ChangePassword extends javax.swing.JDialog{
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton ButtonSavePassword;
    private JButton ButtonCancel;



    public ChangePassword(Clientcommunikation interf, String userID){
        //Passwort speichern Schaltfläche
        ButtonSavePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(securePassword(passwordField1.getPassword().toString())){
                    if(!passwordField1.getPassword().toString().contentEquals(passwordField2.getPassword().toString())){
                        JOptionPane.showMessageDialog(null,"Passwörter stimmen nicht überein", "ungleiche Passwörter",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        if(interf.setPassword(userID,hashPSW(userID, passwordField1.getPassword()))){
                            JOptionPane.showMessageDialog(null,"Passwort erfolgreich geändert", "Passwort geändert", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Passwort konnte nicht geändert werden", "Fehler beim ändern",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        //Schaltflaeche Abbrechen
        ButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private boolean securePassword(String psw){
        boolean low,high,num,spec;
        low = false;
        high = false;
        num = false;
        spec = false;
        char[] specialChars = {'!','"','§','$','%','&','/','(',')','=','?','*','+','-','#','.',',',':',';','[',']','{','}','_'};
        //analyse
        //sonderzeichen
        for(char schar : specialChars){
            if(psw.indexOf(schar) != -1){
                spec = true;
                break;
            }
        }
        //Alphabetisch
        for(char pswcahr : psw.toCharArray()){
            if(Character.getType(pswcahr) == Character.LOWERCASE_LETTER){
                low = true;
            }
            if(Character.getType(pswcahr) == Character.UPPERCASE_LETTER){
                high = true;
            }
            if(Character.getType(pswcahr) == Character.DIRECTIONALITY_EUROPEAN_NUMBER){
                num = true;
            }
        }

        //kontrolle
        //laenge
        if(psw.length() < 12){
            JOptionPane.showMessageDialog(null,"Das eingegebene Passwort ist zu kurz, mindestens 12 Zeichen","Passwort entspricht nicht den Anforderungen",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //sonderzeichen
        if(!spec){
            JOptionPane.showMessageDialog(null,"Das Passwort muss Sonderzeichen enthalten", "Passwort entspricht nicht den Anforderungen",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!num){
            JOptionPane.showMessageDialog(null,"Das Passwort muss Numerischezeichen enthalten", "Passwort entspricht nicht den Anforderungen",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!low){
            JOptionPane.showMessageDialog(null,"Das Passwort muss Kleinbuchstaben enthalten", "Passwort entspricht nicht den Anforderungen",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!high){
            JOptionPane.showMessageDialog(null,"Das Passwort muss Großbuchstaben enthalten", "Passwort entspricht nicht den Anforderungen",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private String hashPSW (String userID, char[] psw){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(userID.getBytes());
            md.update(String.valueOf(psw).getBytes());
            return Base64.getEncoder().encodeToString(md.digest());
        }
        catch (NoSuchAlgorithmException e){
            JOptionPane.showMessageDialog(null,"keine SHA-256 Instanz gefunden","Fehlende Instanz",JOptionPane.ERROR_MESSAGE);
        }
        return "";
    }
}
