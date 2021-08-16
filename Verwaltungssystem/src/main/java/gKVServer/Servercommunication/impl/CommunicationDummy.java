package gKVServer.Servercommunication.impl;

import data.*;
import gKVServer.Servercommunication.Servercommunication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

/**
 * @author Oliver Neuhaeusler
 */

public class CommunicationDummy extends UnicastRemoteObject implements Servercommunication{

    protected CommunicationDummy() throws RemoteException {
        super();
    }

    public static void main(String[] args){

            System.setProperty("sun.rmi.transport.connectionTimeout", "5000");
            System.getProperties().forEach((key, value) -> System.out.println(key + " : " + value));

            Registry registry = null;

            try {
                // LocateRegistry.createRegistry(PORT);
                registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
                System.out.println("Registry ready");
            } catch (RemoteException e) {
                System.out.println("Registry exception: " + e.getMessage());
            }

            // Anmelden bei der Registry
            try {
                Servercommunication sc = new CommunicationDummy();
                registry.rebind("server", sc);
                System.out.println("[server] eingetragen.");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    @Override
    public boolean sendAcknowledge(boolean state) throws RemoteException {
        return state;
    }

    @Override
    public boolean deleteDoc(String adminID, String adminPSW, String docName) throws RemoteException {
        return true;
    }

    @Override
    public boolean setDoc(String adminID, String adminPSW, String docName) throws RemoteException {
        return true;
    }

    @Override
    public boolean createOrderAdmin(String userID, String adminID, String adminPSW, int value, String usage) throws RemoteException {
        return true;
    }

    @Override
    public boolean createOrderUser(String userID, String userPSW, String drinkName, int amount, String bottleType) throws RemoteException {
        return true;
    }

    @Override
    public boolean sendInfo(String adminID, String adminPSW, String adminRZID, String adminRZPSW, String message) throws RemoteException {
        return true;
    }

    @Override
    public boolean setInventory(String adminID, String adminPSW, DrinkTO[] drinks) throws RemoteException {
        return true;
    }

    @Override
    public boolean createDrink(String adminID, String adminPSW, DrinkTO drinkName) throws RemoteException {
        return true;
    }

    @Override
    public boolean creatWish(DrinkTO[] drinks, String userID) throws RemoteException {
        return true;
    }

    @Override
    public boolean resetPassword(String adminID, String adminPSW, String userID) throws RemoteException {
        return true;
    }

    @Override
    public boolean createUser(String adminID, String adminPSW, PersonTO person) throws RemoteException {
        return true;
    }

    @Override
    public boolean setUser(String adminID, String adminPSW, PersonTO person) throws RemoteException {
        return true;
    }

    @Override
    public boolean setDrink(String adminID, String adminPSW, DrinkTO drink) throws RemoteException {
        return true;
    }

    @Override
    public boolean sendBill(String adminRZID, String adminRZPSW, String adminID, String adminPSW) throws RemoteException {
        return true;
    }

    @Override
    public boolean setUserState(String userID) throws RemoteException {
        return true;
    }

    @Override
    public boolean deletePerson(String adminID, String adminPSW, String userID) throws RemoteException {
        return true;
    }

    @Override
    public CellarTO getCellar(String adminID, String adminPSW) throws RemoteException {
        int[] rooms = null;
        return new CellarTO(100, new DocTO[]{new DocTO("Rechnung20201127.pdf"), new DocTO("Inventur20201001.pdf")}, true, 24,
                new DrinkTO[]{new DrinkTO("Augustiner Export", "0,5", 20, 100, 200,
                        10), new DrinkTO("Maxlrainer Helle", "0,5", 20, 100,
                        120, 0)}, rooms, 2);
    }

    @Override
    public DocTO createDelivery(String adminID, String adminPSW) throws RemoteException {
        return new DocTO("Bestellung20201118.pdf");
    }

    @Override
    public DocTO getDoc(String adminID, String adminPSW, String docName) throws RemoteException {
        DocTO document = new DocTO("Rechnung20201127.pdf");
        return document;
    }

    @Override
    public DocTO[] getDocs(String adminID, String adminPSW) throws RemoteException {
        return new DocTO[0];
    }

    @Override
    public DrinkTO[] getProviderList() throws RemoteException {
        return new DrinkTO[0];
    }

    @Override
    public DrinkTO[] getInventory(int cellarID) throws RemoteException {
        return new DrinkTO[]{new DrinkTO("Augustiner Export","0,5",20,100,200,
                                       10), new DrinkTO("Maxlrainer Helle","0,5",20,100,
                                       120,0)};
    }

    @Override
    public OrderTO[] getOrderAdmin(String adminID, String adminPSW, String cellarUserID) throws RemoteException {
        return new OrderTO[]{new OrderTO("HP227","Augistiner Export",1,2,LocalDateTime.of(2020, 11, 11, 11, 11)),
                                new OrderTO("HP227", "Cola", 1,2, LocalDateTime.of(2020, 5, 30, 20, 15))};
    }

    @Override
    public OrderTO[] getOrderUser(String userID) throws RemoteException {
        return new OrderTO[0];
    }

    @Override
    public PersonTO getUser(String userID) throws RemoteException {
         return new PersonTO("HM217",2,"Hans","Meier",true,false,false,0,"HM1234","hans.meier@unibw.de",false,false);
    }

    @Override
    public PersonTO[] getUsers(String adminID, String adminPSW) throws RemoteException {
        return new PersonTO[] {new PersonTO("HM217",2,"Hans","Meier",true,false,false,0,"HM1234","hans.meier@unibw.de",false,false),
                new PersonTO("JH231",2,"Jakob","Huber",true,true,false,0,"JH1234","jakob.huber@unibw.de",false,false),
                new PersonTO("PL211",2,"Peter","Luster",true,true,false,0,"PL1234","peter.lustig@unibw.de",false,false)};
    }

    @Override
    public ReturnLogin login(String userID, String userPSW) throws RemoteException {
        return (ReturnLogin.LOGIN_SUCCESSFUL_USER);
    }
}




