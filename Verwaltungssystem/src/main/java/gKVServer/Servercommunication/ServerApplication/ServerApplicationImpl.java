package gKVServer.Servercommunication.ServerApplication;

import gKVServer.Servercommunication.Servercommunication;
import gKVServer.Servercommunication.impl.ServercommunicationImpl;
import gKVServer.aplicationlogic.Usermanagement;
import gKVServer.aplicationlogic.Cellarmanagement;
import gKVServer.aplicationlogic.impl.CellarmanagementImpl;
import gKVServer.aplicationlogic.impl.UsermanagementImpl;
import gKVServer.database.CellarDB;
import gKVServer.database.impl.DBimpl;
import gKVServer.Servercommunication.ServerApplication.*;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Oliver Neuhaeusler
 */

public class ServerApplicationImpl {
    private static final int PORT = 1099;

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
            ServercommunicationImpl sc = new ServercommunicationImpl();
            registry.rebind("server", (Servercommunication) sc);
            Usermanagement usermanagementObject = new UsermanagementImpl();
            Cellarmanagement cellarmanagementObject = new CellarmanagementImpl(usermanagementObject);
            sc.setUsermanagement(usermanagementObject);
            sc.setCellarmanagement(cellarmanagementObject);
            /*DBimpl dbimplObject = new DBimpl();
            usermanagementObject.setUserDB(dbimplObject);
            cellarmanagementObject.setCellarDB(dbimplObject);*/
            //dummy murks
            CellarDB cb = new CellarDBDummyTest();
            usermanagementObject.setUserDB(new UserDBDummyTest(cb));
            cellarmanagementObject.setCellarDB(cb);



            System.out.println("[server] eingetragen.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

