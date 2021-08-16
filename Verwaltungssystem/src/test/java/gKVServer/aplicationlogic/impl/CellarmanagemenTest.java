package gKVServer.aplicationlogic.impl;

import data.CellarTO;
import data.DocTO;
import data.DrinkTO;
import data.OrderTO;
import gKVServer.aplicationlogic.Cellarmanagement;
import gKVServer.aplicationlogic.Usermanagement;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
/**
 * Autor: Brandes Lukas
 */
import static org.junit.jupiter.api.Assertions.*;

abstract class CellarmanagemenTest {

    public abstract Cellarmanagement getCellarmanagement();
    Cellarmanagement cellarmanagement = getCellarmanagement();


    @Test
    void createDrink() {

        assertTrue(cellarmanagement.createDrink("MM205", "12345",
                new DrinkTO("Fanta","0,33",24,1,100,50)),
                "Erfolgreiches anlegen des Getränks");


    }

    @Test
    void deleteDoc() {
        assertTrue(cellarmanagement.deleteDoc("MM205", "12345", "Test Dokument"),
                "Erfolgreiches Löschen des Dokuments");
    }

    @Test
    void sendAcknowledge() {
        assertTrue(cellarmanagement.sendAcknowledge(true),"Test erfolgreich" );
    }

    @Test
    void sendBill() {
        assertTrue(cellarmanagement.sendBill("j7mm1234","1234", "MM205","12345"),
                "Erfolgreiches senden der E-Mails");
    }

    @Test
    void setDoc() {
        assertTrue(cellarmanagement.setDoc("MM205", "12345", "Test Dokument"),
                "Erfolgreiches anlegen eines Dokuments");
    }

    @Test
    void setDrink() {
        assertTrue(cellarmanagement.setDrink("MM205","12345",
                new DrinkTO("Fanta","0,33",24,1,100,50)),
                "Erfolgreiches ändern eines Getränks");
    }

    @Test
    void setInventory() {
        new DrinkTO("Fanta","0,33",24,1,100,50);
        assertTrue(cellarmanagement.setInventory("MM205","12345",
                new DrinkTO[]{new DrinkTO("Fanta","0,33",24,1,100,50)
                        ,new DrinkTO("Cola","0,33",24,1,100,50)}));
}

    @Test
    void getCellar() {
        int[] rooms = {201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,
                222,223,224,225,226,227,228,229,230,231,232,233,234,235,236};
        DocTO[] dockList = new DocTO[3];
        dockList[0] = new DocTO("Rechnung 20.8.2020");
        dockList[1] = new DocTO("Rechnung 31.7.2020");
        dockList[2] = new DocTO("Inventur 15.5.2020");
        DrinkTO[] wishlist = new DrinkTO[2];
        wishlist[0] = new DrinkTO("Fanta","0,33",24,1,100,50);
        wishlist[1] = new DrinkTO("Cola","0,33",24,1,100,50);

        CellarTO returncellar = cellarmanagement.getCellar("MM205", "12345");
        CellarTO expectedcellar = new CellarTO(100,dockList, false, 0,
                       wishlist, rooms, 1);

        // ich habe hir einige Elemente auf Ihre Länge getestet, um nicht alle Ihre jeweilige Elemente vergleichen zu müssen
        // und um diese nicht in einen String bzw Hash umwandeln zu müssen. (Keine Ideale Lösung)
        assertEquals(expectedcellar.CellarID,returncellar.CellarID);
        assertEquals(expectedcellar.Cellarcash,returncellar.Cellarcash);
        assertEquals(expectedcellar.fee,returncellar.fee);
        assertEquals(expectedcellar.state,returncellar.state);
        assertEquals(expectedcellar.Document.length,returncellar.Document.length);
        assertEquals(expectedcellar.Rooms.length,returncellar.Rooms.length);
        assertEquals(expectedcellar.Wishlist.length,returncellar.Wishlist.length);
    }

    @Test
    void createDelivery() {
        assertEquals(cellarmanagement.createDelivery("MM205","12345").name,new DocTO("name").name);
    }

    @Test
    void getDoc() {
        assertEquals("Rechnung 31.7.2020",cellarmanagement.getDoc("MM205","12345","Rechnung 20.8.2020").name);
    }

    @Test
    void getDocs() {
        //Dokumente anlegen
        DocTO[] dockListExpected = new DocTO[3];
        dockListExpected[0] = new DocTO("Rechnung 20.8.2020");
        dockListExpected[1] = new DocTO("Rechnung 31.7.2020");
        dockListExpected[2] = new DocTO("Inventur 15.5.2020");
        DocTO[] dockListactualy = cellarmanagement.getDocs("MM205", "12345");

        assertEquals(dockListExpected[0].name,cellarmanagement.getDocs("MM205", "12345")[0].name);
        assertEquals(dockListExpected[1].name,cellarmanagement.getDocs("MM205", "12345")[1].name);
        assertEquals(dockListExpected[2].name,cellarmanagement.getDocs("MM205", "12345")[2].name);

    }

    @Test
    void getInventory() {
        /*
        assertEquals(cellarmanagement.getInventory(1),
                new DrinkTO[]{new DrinkTO("Fanta","0,33",24,1,100,50)
                        ,new DrinkTO("Cola","0,33",24,1,100,50)});
         */

        DrinkTO[] drinks = new DrinkTO[2];
        drinks[0] = new DrinkTO("Fanta","1,0",12,150,100,50);
        drinks[1] = new DrinkTO("Cola","1,0",12,150,100,45);

        for (int i = 0;i <= 1; i++) {

            assertEquals(drinks[i].name, cellarmanagement.getInventory(1)[i].name);
            assertEquals(drinks[i].bottleType, cellarmanagement.getInventory(1)[i].bottleType);
            assertEquals(drinks[i].packType, cellarmanagement.getInventory(1)[i].packType);
            assertEquals(drinks[i].price, cellarmanagement.getInventory(1)[i].price);
            assertEquals(drinks[i].setvalue, cellarmanagement.getInventory(1)[i].setvalue);
            assertEquals(drinks[i].actualvalue, cellarmanagement.getInventory(1)[i].actualvalue);

        }
    }

    @Test
    void getProviderList() {
        /*
        assertEquals(cellarmanagement.getProviderList(),
                new DrinkTO[]{new DrinkTO("Fanta","0,33",24,1,100,50)
                        ,new DrinkTO("Cola","0,33",24,1,100,50)});

         */
        DrinkTO[] providerList = new DrinkTO[2];
        providerList[0] = new DrinkTO("Fanta","0,33",24,1,100,50);
        providerList[1] = new DrinkTO("Cola","0,33",24,1,100,50);

        for (int i = 0;i <= 1; i++) {

            assertEquals(providerList[i].name, cellarmanagement.getProviderList()[i].name);
            assertEquals(providerList[i].bottleType, cellarmanagement.getProviderList()[i].bottleType);
            assertEquals(providerList[i].packType, cellarmanagement.getProviderList()[i].packType);
            assertEquals(providerList[i].price, cellarmanagement.getProviderList()[i].price);
            assertEquals(providerList[i].setvalue, cellarmanagement.getProviderList()[i].setvalue);
            assertEquals(providerList[i].actualvalue, cellarmanagement.getProviderList()[i].actualvalue);

        }
    }

    @Test
    void getOrder() {
        /*
        LocalDateTime now = LocalDateTime.now();
        assertEquals(cellarmanagement.getOrder("MM201", "1234", "MM205"),
                new OrderTO[]{new OrderTO("MM205","Fanta", 1,2,now),
                        new OrderTO("MM205","Cola", 1,1,now)});

         */


        //Bestellungen anlegen
        OrderTO[] orders = new OrderTO[2];
        orders[0] = new OrderTO("MM201","cola", 1, 1, LocalDateTime.of(2019, 2, 13, 15, 56) );



            assertEquals(orders[0].personID, cellarmanagement.getOrder("MM205","12345", "MM201")[0].personID);
            assertEquals(orders[0].name, cellarmanagement.getOrder("MM205","12345", "MM201")[0].name);
            assertEquals(orders[0].value, cellarmanagement.getOrder("MM205","12345", "MM201")[0].value);
            assertEquals(orders[0].amount, cellarmanagement.getOrder("MM205","12345", "MM201")[0].amount);
            assertEquals(orders[0].date, cellarmanagement.getOrder("MM205","12345", "MM201")[0].date);




    }
}