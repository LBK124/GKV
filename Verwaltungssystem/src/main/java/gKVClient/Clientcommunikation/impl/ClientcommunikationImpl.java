package gKVClient.Clientcommunikation.impl;

import data.*;
import gKVClient.Clientcommunikation.Clientcommunikation;
import gKVServer.Servercommunication.Servercommunication;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Oliver Neuhaeusler
 */

public class ClientcommunikationImpl implements Clientcommunikation {

    private Servercommunication servercommunication = null;

    //initialisiert die Serververbindung
    public boolean initConection(String serverIP){
        String serverLocationD;
        if(serverIP.length()==0) {
            serverLocationD = "127.0.0.1:1099";
        }
        else{
            serverLocationD = serverIP;
        }
        try {
            servercommunication = (Servercommunication) Naming.lookup("//" + serverLocationD+"/server");
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        } catch (NotBoundException e) {
            e.printStackTrace();
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean createDrink(String adminID, String adminPSW, DrinkTO drink) {
        try {
            return servercommunication.createDrink(adminID, adminPSW, drink);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;

        }

    }

    @Override
    public boolean createOrderAdmin(String cellarUserID, String adminID, String adminPSW, int value, String usage) {

        try {
            return servercommunication.createOrderAdmin(cellarUserID, adminID, adminPSW, value, usage);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;

        }

    }

    @Override
    public boolean createOrderUser(String userID, String userPSW, String drinkName, int amount, String bottleType) {

        try {
            return servercommunication.createOrderUser(userID, userPSW, drinkName, amount, bottleType);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean createUser(String adminID, String adminPSW, PersonTO user) {
        try {
            return servercommunication.createUser(adminID, adminPSW, user);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean creatWish(DrinkTO[] drinks, String userID) {

        try {
            return servercommunication.creatWish(drinks, userID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean deleteDoc(String adminID, String adminPSW, String docName) {

        try {
            return servercommunication.deleteDoc(adminID, adminPSW, docName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean deletePerson(String adminID, String adminPSW, String userID) {

        try {
            return servercommunication.deletePerson(adminID, adminPSW, userID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean resetPassword(String adminID, String adminPSW, String userID) {

        try {
            return servercommunication.resetPassword(adminID, adminPSW, userID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean sendAcknowledge(boolean acknowledge) {

        try {
            return servercommunication.sendAcknowledge(acknowledge);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean sendBill(String adminRZID, String adminRZPSW, String adminID, String adminPSW) {

        try {
            return servercommunication.sendBill(adminRZID, adminRZPSW, adminID, adminPSW);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean sendInfo(String adminID, String adminPSW, String adminRZID, String adminRZPSW, String message) {

        try {
            return servercommunication.sendInfo(adminID, adminPSW, adminRZID, adminRZPSW, message);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean setDoc(String adminID, String adminPSW, String docName) {

        try {
            return servercommunication.setDoc(adminID, adminPSW, docName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean setDrink(String adminID, String adminPSW, DrinkTO drink) {

        try {
            return servercommunication.setDrink(adminID, adminPSW, drink);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean setInventory(String adminID, String adminPSW, DrinkTO[] drinks) {

        try {
            return servercommunication.setInventory(adminID, adminPSW, drinks);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean setUser(String adminID, String adminPSW, PersonTO user) {
        try {
            return servercommunication.setUser(adminID, adminPSW, user);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    @Override
    public boolean setUserState(String userID) {

        try {
            return servercommunication.setUserState(userID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public CellarTO getCellar(String adminID, String adminPSW) {

        try {
            return servercommunication.getCellar(adminID, adminPSW);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public DocTO createDelivery(String adminID, String adminPSW) {

        try {
            return servercommunication.createDelivery(adminID, adminPSW);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public DocTO getDoc(String adminID, String adminPSW, String docName) {

        try {
            return servercommunication.getDoc(adminID, adminPSW, docName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public DocTO[] getDocs(String adminID, String adminPSW) {
        try {
            return servercommunication.getDocs(adminID, adminPSW);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }


    @Override
    public DrinkTO[] getInventory(int cellarID) {

        try {
            return servercommunication.getInventory(cellarID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }


    }

    @Override
    public DrinkTO[] getProviderList() {

        try {
            return servercommunication.getProviderList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public OrderTO[] getOrder(String userID) {

        try {
            return servercommunication.getOrderUser(userID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public OrderTO[] getOrder(String adminID, String adminPSW, String cellarUserID) {

        try {
            return servercommunication.getOrderAdmin(adminID, adminPSW, cellarUserID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public PersonTO getUser(String userID) {

        try {
            return servercommunication.getUser(userID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public PersonTO[] getUsers(String adminID, String adminPSW) {

        try {
            return servercommunication.getUsers(adminID, adminPSW);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }


    @Override
    public Servercommunication.ReturnLogin login(String userID, String userPSW) {

        try {
            return servercommunication.login(userID, userPSW);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Netzwerkfehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    @Override
    public boolean setPassword(String userID, String userPSW) {
        return false;
    }
}
