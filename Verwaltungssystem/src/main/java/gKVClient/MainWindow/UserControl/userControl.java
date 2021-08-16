package gKVClient.MainWindow.UserControl;

import data.PersonTO;
import gKVClient.Clientcommunikation.Clientcommunikation;
import gKVClient.Main.MainThread;
import gKVClient.Utils.utils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.abs;

public class userControl extends javax.swing.JPanel {

    public JPanel ContentPane;
    //textFileds
    private JTextField textFieldSurName;
    private JTextField textFieldName;
    private JTextField textFieldRoom;
    private JTextField textFieldAccountValue;
    //checkBoxes
    private JCheckBox checkBoxKey;
    private JCheckBox checkBoxInactive;
    private JCheckBox checkBoxAdmin;
    private JCheckBox checkBoxMovedOut;

    //buttons
    private JButton buttonEdit;
    private JButton buttonEditAccountValue;
    private JButton buttonCreateUserBill;

    private userEdit editUser;
    //murks?
    private userControl me;
    private Clientcommunikation interf;
    private PersonTO loggedUser;

    private String name ,surName, id, email, room;
    private boolean admin, key, status, movedOut;
    private int accValue;
    protected utils tools;

    public userControl(Clientcommunikation interf, PersonTO loggedUser) {
        this.interf = interf;
        this.loggedUser = loggedUser;

        //murks?
        me = this;
        tools = new utils();
        //CreateDummy();

        //klick auf Knopf Bearbeite Benutzer öffnet Bearbeitungs Fenster
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUser = new userEdit(me);
                editUser.pack();
                editUser.setSize(600,350);
                editUser.loadElement(name,surName,room,email,admin,key,status,movedOut);
                editUser.setVisible(true);

            }
        });
        buttonEditAccountValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userEditAccountValue uEAV = new userEditAccountValue(me, accValue);
                uEAV.pack();
                uEAV.setSize(600,350);
                uEAV.setVisible(true);
            }
        });
    }
    //Bekommtänderungen aus dem userEdit Fenster
    public void getUserChanges(String name, String surName, String id, String email, boolean admin, boolean key, boolean status, boolean movedOut) {
        //Setze Textfelder
        this.name = name;
        this.surName = surName;
        this.id = id;
        this.room = id.substring(id.length()-3);
        this.email = email;
        //Übenehme Flags
        this.admin = admin;
        this.key = key;
        this.status = status;
        this.movedOut = movedOut;
        this.updateEntry();

    }

    //Bekommt Änderungen aus dem Fenster userEditAccountValue
    public void getAccountValue(int newValue){
        this.accValue = newValue;
        this.updateEntry();
    }
    //Aktuallisiert die Textboxen
    private void updateEntry(){

        //Textfelder auffrischen
        textFieldRoom.setText(room);
        textFieldAccountValue.setText(tools.intToString(accValue));
        textFieldSurName.setText(surName);
        textFieldName.setText(name);

        checkBoxAdmin.setSelected(admin);
        checkBoxInactive.setSelected(status);
        checkBoxKey.setSelected(key);
        checkBoxMovedOut.setSelected(movedOut);

    }

    //erstellt dummy eintrag debug der GUI
    private void CreateDummy(){
        room = "101";
        name = "Max";
        surName = "Mustermann";
        email = "max.mustermann@unibw.de";
        id = name.substring(0,1) + surName.substring(0,1) + room;

        accValue = -10001;

        admin = false;
        key = true;
        status = false;
        movedOut = false;
        updateEntry();

    }

    //Util Funktion für Konvertierung der Textboxen in Integerwerte
    public int stringToInt (String src){
        int dest;

        if(src.indexOf(",") == -1) {
            dest = Integer.parseInt(src);
        }
        else{
            try {
                dest = Integer.parseInt(src.replace(",", ""));
            }
            catch (NumberFormatException | NullPointerException nfe){
                JOptionPane.showMessageDialog(null,"Eingabe Fehlerhaft");
                return 0;
            }
        }

        return dest;
    }

    public void loadData(String element){
        PersonTO user = interf.getUser(element.substring(0,5).trim());
        textFieldSurName.setText(user.surename);
        textFieldName.setText(user.firstname);
        textFieldRoom.setText(user.id.substring(2,5));
        textFieldAccountValue.setText(String.format("%d.%02d€",user.balance/100,abs(user.balance%100)));
        checkBoxAdmin.setSelected(user.admin);
        checkBoxInactive.setSelected(!user.state);
        checkBoxKey.setSelected(user.key);
        ContentPane.updateUI();
    }


}
