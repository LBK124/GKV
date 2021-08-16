package gKVServer.Servercommunication.impl;

import data.*;
import gKVServer.Servercommunication.Servercommunication;
import gKVServer.aplicationlogic.Cellarmanagement;
import gKVServer.aplicationlogic.Usermanagement;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

/**
 * @author Oliver Neuhaeusler
 */

public class ServercommunicationImpl extends UnicastRemoteObject implements Servercommunication {

        public ServercommunicationImpl() throws RemoteException {
            super();
        }

        private Usermanagement usermanagement;
        public void setUsermanagement(Usermanagement usermanagement2) {
            usermanagement = usermanagement2;
        }

        private Cellarmanagement cellarmanagement;
        public void setCellarmanagement(Cellarmanagement cellarmanagement2){
            cellarmanagement = cellarmanagement2;
        }


    @Override
    public boolean sendAcknowledge(boolean state) {
        return cellarmanagement.sendAcknowledge(state);
    }

    @Override
    public boolean deleteDoc(String adminID, String adminPW, String docName) {
        return cellarmanagement.deleteDoc(adminID, adminPW, docName);
    }

    @Override
    public boolean setDoc(String adminID, String adminPSW, String docName) {
        return cellarmanagement.setDoc(adminID, adminPSW, docName);
    }

    @Override
    public boolean createOrderAdmin(String userID, String adminID, String adminPSW, int value, String usage) {
        return usermanagement.createOrderAdmin(userID, adminID, adminPSW, value, usage);
    }

    @Override
    public boolean createOrderUser(String userID, String userPSW, String drinkName, int amount, String bottleType) {
        return usermanagement.createOrderUser(userID, userPSW, drinkName, amount, bottleType);
    }

    @Override
    public boolean sendInfo(String adminID, String adminPSW, String adminRZID, String adminRZPSW, String message) {
        return usermanagement.sendInfo(adminID, adminPSW, adminRZID, adminRZPSW, message);
    }

    @Override
    public boolean setInventory(String adminID, String adminPSW, DrinkTO[] drinks) {
        return cellarmanagement.setInventory(adminID, adminPSW, drinks);
    }

    @Override
    public boolean createDrink(String adminID, String adminPSW, DrinkTO drinkName) {
        return cellarmanagement.createDrink(adminID, adminPSW, drinkName);

    }

    @Override
    public boolean creatWish(DrinkTO[] drinks ,String userID) {
        return usermanagement.creatWish(drinks, userID);
    }

    @Override
    public boolean resetPassword(String adminID, String adminPSW, String userID) {
        return usermanagement.resetPassword(adminID, adminPSW, userID);
    }

    @Override
    public boolean createUser(String adminID, String adminPSW, PersonTO person) {
        return usermanagement.createUser(adminID, adminPSW, person);
    }

    @Override
    public boolean setUser(String adminID, String adminPSW, PersonTO person) {
        return usermanagement.setUser(adminID, adminPSW, person);
    }

    @Override
    public boolean setDrink(String adminID, String adminPSW, DrinkTO drink) {
        return cellarmanagement.setDrink(adminID, adminPSW, drink);
    }

    @Override
    public boolean sendBill(String adminRZID, String adminRZPSW, String adminID, String adminPSW) {
        return cellarmanagement.sendBill(adminRZID, adminRZPSW, adminID, adminPSW);
    }

    @Override
    public boolean setUserState(String userID) {
        return usermanagement.setUserState(userID);
    }

    @Override
    public boolean deletePerson(String adminID, String adminPSW, String userID) {
        return usermanagement.deletePerson(adminID, adminPSW, userID);
    }

    @Override
    public CellarTO getCellar(String adminID, String adminPSW) {
        return cellarmanagement.getCellar(adminID, adminPSW);
    }

    @Override
    public DocTO createDelivery(String adminID, String adminPSW) {
        return cellarmanagement.createDelivery(adminID, adminPSW);
    }

    @Override
    public DocTO getDoc(String adminID, String adminPSW, String docName) {
        return cellarmanagement.getDoc(adminID, adminPSW, docName);
    }

    @Override
    public DocTO[] getDocs(String adminID, String adminPSW) {
        return cellarmanagement.getDocs(adminID, adminPSW);
    }

    @Override
    public DrinkTO[] getProviderList() {
        return cellarmanagement.getProviderList();
    }

    @Override
    public DrinkTO[] getInventory(int cellarID) {
        return cellarmanagement.getInventory(cellarID);
    }

    @Override
    public OrderTO[] getOrderAdmin(String adminID, String adminPSW, String cellarUserID) {
        return cellarmanagement.getOrder(adminID, adminPSW, cellarUserID);
    }

    @Override
    public OrderTO[] getOrderUser(String userID) {
      return usermanagement.getOrder(userID);
    }

    @Override
    public PersonTO getUser(String userID) {
        return usermanagement.getUser(userID);
    }

    @Override
    public PersonTO[] getUsers(String adminID, String adminPSW) {
        return usermanagement.getUsers(adminID, adminPSW);
    }


    @Override
    public Servercommunication.ReturnLogin login(String userID, String userPSW) {

        return usermanagement.login(userID,userPSW);
    }
}
