package gKVServer.aplicationlogic.impl;

import data.CellarTO;
import data.DocTO;
import data.DrinkTO;
import data.OrderTO;
import gKVServer.aplicationlogic.Cellarmanagement;
import gKVServer.aplicationlogic.ManagementExcange;
import gKVServer.aplicationlogic.Usermanagement;
import gKVServer.database.CellarDB;

/**
 * Autor: Brandes Lukas
 */
public class CellarmanagementImpl implements Cellarmanagement {

    private CellarDB cellarDB = null;
    public void setCellarDB(CellarDB cellarDB){this.cellarDB = cellarDB;}
    private ManagementExcange managementExcange = null;
    public CellarmanagementImpl(Usermanagement usermanagement){this.managementExcange = new ManagementExcangeImpl(usermanagement);}

    private boolean acknowledgeServer = false;
    @Override
    public boolean createDrink(String adminID, String adminPSW, DrinkTO drink) {
        if(managementExcange.checkAdmin(adminID,adminPSW)){
            cellarDB.createDrink(drink,managementExcange.getCellarID(adminID));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteDoc(String adminID, String adminPSW, String docName) {
        if(managementExcange.checkAdmin(adminID,adminPSW)){
            return cellarDB.deleteDoc(docName);

        }
        return false;
    }

    @Override
    public boolean sendAcknowledge(boolean acknowledge) {
        acknowledgeServer = acknowledge;
        return true;
    }

    @Override
    //TODO E-Mail PlugIn
    public boolean sendBill(String adminRZID, String adminRZPSW, String adminID, String adminPSW) {
    /**
    * Das senden der E-Mail wird durch eine Ausgabe Simuliert
    */

        System.out.println("Universität der Bundeswehr München\n" +
                "Informationen Geb. 8/300 \n" +
                "Studentenfachbereichs A - \n" +
                "                                       Brandes Lukas, Leutnant \n"+
                "                                       E-Mail: lukas.brandes@unibw.de\n\n"+
                "Rechnung:  Name                        Rechnungszeitraum       \n"+
                "Nr.        Name     Datum      Prei\n"+
                "1.         Cola     02.12.20   1");
        return true;
    }

    @Override
    public boolean setDoc(String adminID, String adminPSW, String docName) {
        if(managementExcange.checkAdmin(adminID,adminPSW)){
            cellarDB.setDoc(managementExcange.getCellarID(adminID),docName);
            return true;
        }
        return false;
    }

    @Override
    public boolean setDrink(String adminID, String adminPSW, DrinkTO drink) {
        if(managementExcange.checkAdmin(adminID,adminPSW)){
            return cellarDB.setDrink(managementExcange.getCellarID(adminID),drink);
        }
        return false;
    }

    @Override
    public boolean setInventory(String adminID, String adminPSW, DrinkTO[] drinks) {
        if(managementExcange.checkAdmin(adminID,adminPSW)){
            return cellarDB.setInventory(managementExcange.getCellarID(adminID), drinks);
        }
        return false;
    }

    @Override
    public CellarTO getCellar(String adminID, String adminPSW) {
        if(managementExcange.checkAdmin(adminID,adminPSW)){
            return cellarDB.getCellar(managementExcange.getCellarID(adminID));
        }
        return null;
    }

    @Override
    //TODO ebenfalls E-Mail PlugIn notwendig
    public DocTO createDelivery(String adminID, String adminPSW) {
        /**
         * Das senden der E-Mail wird durch eine Ausgabe Simuliert
         */

        System.out.println("Universität der Bundeswehr München\n" +
                "Informationen Geb. 8/300 \n" +
                "Studentenfachbereichs A - \n" +
                "                                       Brandes Lukas, Leutnant \n"+
                "                                       E-Mail: lukas.brandes@unibw.de\n\n"+
                "Bestellung:                            Rechnungszeitraum       \n"+
                "Nr.        Name     Anzahl      Prei\n"+
                "1.         Cola     3           24");
        return new DocTO("name");
    }

    @Override
    public DocTO getDoc(String adminID, String adminPSW, String docName) {
        if(managementExcange.checkAdmin(adminID,adminPSW)){
            return cellarDB.getDoc(managementExcange.getCellarID(adminID),docName);
        }
        return null;
    }

    @Override
    public DocTO[] getDocs(String adminID, String adminPSW) {
        if(managementExcange.checkAdmin(adminID,adminPSW)){
            return cellarDB.getDocList(managementExcange.getCellarID(adminID));
        }
        return null;
    }

    @Override
    public DrinkTO[] getInventory(int cellarID) {
        return cellarDB.getInventory(cellarID);
    }

    @Override
    public DrinkTO[] getProviderList() {
        return cellarDB.getProviderList();
    }

    @Override
    public OrderTO[] getOrder(String adminID, String adminPSW, String cellerUserID) {
        if(managementExcange.checkAdmin(adminID,adminPSW)){
            return cellarDB.getOrder(cellerUserID);
        }
        return null;
    }

}
