package gKVClient.GUI;

import data.PersonTO;
import gKVClient.Clientcommunikation.Clientcommunikation;
import gKVClient.LoginPackage.LoginDialog;
import gKVClient.Main.MainThread;
import gKVClient.MainWindow.mainWindow;

public class mainGUITest extends MainThread {

    public static void main(String[] args) throws InterruptedException {
        mainGUITest mainthread = new mainGUITest(true);
    }

    //Startpunkt der GUI lädt Login Fenster
    public mainGUITest(boolean test) throws InterruptedException {
        super(test);
        //initalisiert das dummy interface
        super.interf = new Clientdummy();
        super.loggedUser = null;

        super.initLogin();
    }

}
