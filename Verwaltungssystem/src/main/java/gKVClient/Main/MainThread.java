package gKVClient.Main;


import data.PersonTO;
import gKVClient.Clientcommunikation.Clientcommunikation;
import gKVClient.Clientcommunikation.impl.ClientcommunikationImpl;
import gKVClient.LoginPackage.LoginDialog;
import gKVClient.MainWindow.mainWindow;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainThread {
    protected PersonTO loggedUser;
    protected Clientcommunikation interf;
    protected mainWindow dialog;

    /*
     * Autor: Hörkner
     * Beschreibung: Hauptmethode des Clients
     * */
    public static void main(String[] args) throws InterruptedException {
        MainThread mainthread = new MainThread(false);
    }

    //Startpunkt der GUI lädt Login Fenster
    public MainThread (boolean test) throws InterruptedException {
        if(!test) {
            interf = new ClientcommunikationImpl();
            boolean conection;
            do{
                conection = interf.initConection("");
                if(!conection) {
                    if (JOptionPane.NO_OPTION == JOptionPane.showOptionDialog(null, "Verbindung fehlgeschlagen wiederholen?", "Netwerkfehler", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null)) {
                        conection = true;
                        System.exit(0);
                    }
                }
            }while (!conection);
            loggedUser = null;
            initLogin();
        }
    }


    //initialisiert den Login
    protected void initLogin(){
        LoginDialog logDialog = new LoginDialog(this ,interf);
        logDialog.setSize(500,150);
        logDialog.setVisible(true);

        //Aktion wenn loginfenster geschlossen wird falls loggedUser noch nicht angelegt dann wird das Programm beendet
        logDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if(loggedUser == null){
                    System.exit(0);
                }
            }
        });
    }

    //Lädt Hauptfenster
    protected void initWindow(){
        this.dialog = new mainWindow(loggedUser, interf);
        this.dialog.setModal(false);
        this.dialog.pack();
        this.dialog.setSize(800,450);
        this.dialog.setVisible(true);

        //Event wenn Hauptfenster geschlossen wird beendet Programm
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                System.exit(0);
            }
        });

    }

    //Callback von Login
    public void setLoggedUser(PersonTO user){
        loggedUser = user;
        initWindow();
    }
    //gibt angemeldeten Benutzer zurück
    public PersonTO getLoggedUser(){
        return loggedUser;
    }
}
