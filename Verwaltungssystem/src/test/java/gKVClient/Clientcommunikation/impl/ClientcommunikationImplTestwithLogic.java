package gKVClient.Clientcommunikation.impl;

import data.*;
import gKVClient.Clientcommunikation.Clientcommunikation;
import gKVServer.Servercommunication.Servercommunication;
import gKVServer.aplicationlogic.Usermanagement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Autor: Brandes Lukas
 * Ein versuch für die Präsentation einen Vorzeigbaren Test zu erzeugen
 *
 */

public class ClientcommunikationImplTestwithLogic {

    static Clientcommunikation clientcommunikation = null;

        @BeforeAll
        public static void initConection(){
            ClientcommunikationImpl clientcommunikation2 = new ClientcommunikationImpl();
            clientcommunikation2.initConection("");
            clientcommunikation = clientcommunikation2;
        }


    @Test
    void createOrderAdmin() {
        PersonTO user = clientcommunikation.getUser("MM210");
        assertTrue(clientcommunikation.createOrderAdmin("MM210","MM205","12345",-10,"test"));
        // zum Überprüfen, ob die Buchung auch auf dem Nutzer gespeichert wrude
        assertEquals(user.balance-10,clientcommunikation.getUser("MM210").balance);
    }

    @Test
    void createOrderUser() {
        assertTrue(clientcommunikation.createOrderUser("MM201","12345!!!", "Cola",0,"0,33"));
    }

    @Test
    void createUser() {
        PersonTO user = new PersonTO("MM236", 2, "User", "Dummy", true, true, false, 0, "&T6T8fqhan!N", "user@unibw.de", false, true);
        // Prüfen ob der Rückgabewert passt
        assertTrue(clientcommunikation.createUser("MM205", "12345",user));
        // prüfen ob die Person richtig angelegt wurde
        assertEquals(user.id,clientcommunikation.getUser("MM236").id);
        assertEquals(user.cellerId,clientcommunikation.getUser("MM236").cellerId);
        assertEquals(user.firstname,clientcommunikation.getUser("MM236").firstname);
        assertEquals(user.surename,clientcommunikation.getUser("MM236").surename);
        assertEquals(user.state,clientcommunikation.getUser("MM236").state);
        assertEquals(user.key,clientcommunikation.getUser("MM236").key);
        assertEquals(user.admin,clientcommunikation.getUser("MM236").admin);
        assertEquals(user.balance,clientcommunikation.getUser("MM236").balance);
        assertEquals(user.password,clientcommunikation.getUser("MM236").password);
        assertEquals(user.eMail,clientcommunikation.getUser("MM236").eMail);
        assertEquals(user.requestState,clientcommunikation.getUser("MM236").requestState);
        assertEquals(user.wish,clientcommunikation.getUser("MM236").wish);
    }

    @Test
    void creatWish() {
        //Wunschliste zum vergleich auf der Datenbank
        DrinkTO[] wishlist = new DrinkTO[3];
        wishlist[0] = new DrinkTO("Fanta","0,33",24,1,100,50);
        wishlist[1] = new DrinkTO("Cola","0,33",24,1,100,50);
        wishlist[2] = new DrinkTO("Bier","1",24,1,100,50);
        //Wünsche
        DrinkTO[] wishlistUser = new DrinkTO[3];
        wishlistUser[0] = new DrinkTO("Radler_1","1",24,1,100,50);
        wishlistUser[1] = new DrinkTO("Radler_2","1",24,1,100,50);
        wishlistUser[2] = new DrinkTO("Radler_2","1",24,1,100,50);

        // Prüfung des Rückgabeparameters
        assertTrue(clientcommunikation.creatWish(wishlistUser,"MM205"));

        //Prüfung des korrekten Anlegens der Wunschliste
    }

    @Test
    void deletePerson() {
        assertTrue(clientcommunikation.deletePerson("MM205", "12345", "MM210"));
    }

    @Test
    void resetPassword() {
        assertTrue(clientcommunikation.resetPassword("MM205", "12345", "MM210"));
    }

    @Test
    void sendInfo() {
        //TODO ich werden den Test schreiben, sobald ich das mit dem Plug-In verstanden habe
        assertEquals(true,false);
    }

    @Test
    void setUser() {
        PersonTO user = new PersonTO("MM220", 2, "User","Dummy", false, true, false, 10, "12345!!!", "user@unibw.de", false, true);
        assertEquals(true, clientcommunikation.setUser("MM205", "12345",user));
    }

    @Test
    void setUserState() {
        assertEquals(true, clientcommunikation.setUserState("MM210"));
    }

    @Test
    void getOrder() {
        OrderTO[] orders = new OrderTO[3];
        orders[0] = new OrderTO("MM205","Fanta",1,2, LocalDateTime.of(2019, 2, 13, 15, 56) );
        orders[1] = new OrderTO("MM205","Cola",5,2,LocalDateTime.of(2020, 2, 13, 15, 56) );


        for (int i = 0;i <= 1; i++) {

            assertEquals(orders[i].personID, clientcommunikation.getOrder("MM205")[i].personID);
            assertEquals(orders[i].name, clientcommunikation.getOrder("MM205")[i].name);
            assertEquals(orders[i].value, clientcommunikation.getOrder("MM205")[i].value);
            assertEquals(orders[i].amount, clientcommunikation.getOrder("MM205")[i].amount);
            assertEquals(orders[i].date, clientcommunikation.getOrder("MM205")[i].date);
        }
    }

    @Test
    void getUser() {
        // Warum kann man keine Transportobjekte vergleichen
        //  assertEquals( new  PersonTO("MM201", 2, "User","Dummy", false, true, false, 10, "12345!!!", "user@unibw.de", false, true),usermanagement.getUser("MM201"));

        PersonTO user = new PersonTO("MM201", 2, "User","Dummy", true, true, false, 10, "12345!!!", "user@unibw.de", false, true);
        assertEquals(user.id,clientcommunikation.getUser("MM201").id);
        assertEquals(user.cellerId,clientcommunikation.getUser("MM201").cellerId);
        assertEquals(user.firstname,clientcommunikation.getUser("MM201").firstname);
        assertEquals(user.surename,clientcommunikation.getUser("MM201").surename);
        assertEquals(user.state,clientcommunikation.getUser("MM201").state);
        assertEquals(user.key,clientcommunikation.getUser("MM201").key);
        assertEquals(user.admin,clientcommunikation.getUser("MM201").admin);
        assertEquals(user.balance,clientcommunikation.getUser("MM201").balance);
        assertEquals(user.password,clientcommunikation.getUser("MM201").password);
        assertEquals(user.eMail,clientcommunikation.getUser("MM201").eMail);
        assertEquals(user.requestState,clientcommunikation.getUser("MM201").requestState);
        assertEquals(user.wish,clientcommunikation.getUser("MM201").wish);
    }

    @Test
    void getUsers() {

        PersonTO[] users = new PersonTO[4];
        users[0] = new PersonTO("HK200", 2, "Hauskasse", "200", true, false, false, 0, "123456789Ab!", "haus200@unibw.de", false, false);
        users[1] = new PersonTO("MM205", 2, "Admin", "Dummy", false, true, true, -50, "12345", "admin@unibw.de", false, true);
        users[2] = new PersonTO("MM201", 2, "User", "Dummy", true, true, false, 10, "12345!!!", "user@unibw.de", false, true);
        users[3] = new PersonTO("MM210", 2, "User", "Dummy", true, true, false, 0, "?k0-6DqW", "user@unibw.de", false, true);

        //  assertEquals(users,usermanagement.getUsers("MM205","12345"));
        for (int i = 0;i <= 3; i++) {

            assertEquals(users[i].id, clientcommunikation.getUser(users[i].id).id);
            assertEquals(users[i].cellerId, clientcommunikation.getUser(users[i].id).cellerId);
            assertEquals(users[i].firstname, clientcommunikation.getUser(users[i].id).firstname);
            assertEquals(users[i].surename, clientcommunikation.getUser(users[i].id).surename);
            assertEquals(users[i].state, clientcommunikation.getUser(users[i].id).state);
            assertEquals(users[i].key, clientcommunikation.getUser(users[i].id).key);
            assertEquals(users[i].admin, clientcommunikation.getUser(users[i].id).admin);
            assertEquals(users[i].balance, clientcommunikation.getUser(users[i].id).balance);
            assertEquals(users[i].password, clientcommunikation.getUser(users[i].id).password);
            assertEquals(users[i].eMail, clientcommunikation.getUser(users[i].id).eMail);
            assertEquals(users[i].requestState, clientcommunikation.getUser(users[i].id).requestState);
            assertEquals(users[i].wish, clientcommunikation.getUser(users[i].id).wish);
        }
    }


    @Test
    void createDrink() {

        assertTrue(clientcommunikation.createDrink("MM205", "12345",
                new DrinkTO("Fanta","0,33",24,1,100,50)),
                "Erfolgreiches anlegen des Getränks");


    }

    @Test
    void deleteDoc() {
        assertTrue(clientcommunikation.deleteDoc("MM205", "12345", "Test Dokument"),
                "Erfolgreiches Löschen des Dokuments");
    }

    @Test
    void sendAcknowledge() {
        boolean acknowledge = true;
        assertTrue(clientcommunikation.sendAcknowledge(acknowledge),"Test erfolgreich" );
    }

    @Test
    void sendBill() {
        assertTrue(clientcommunikation.sendBill("j7mm1234","1234", "MM205","12345"),
                "Erfolgreiches senden der E-Mails");
    }

    @Test
    void setDoc() {
        assertTrue(clientcommunikation.setDoc("MM205", "12345", "Test Dokument"),
                "Erfolgreiches anlegen eines Dokuments");
    }

    @Test
    void setDrink() {
        assertTrue(clientcommunikation.setDrink("MM205","12345",
                new DrinkTO("Fanta","0,33",24,1,100,50)),
                "Erfolgreiches ändern eines Getränks");
    }

    @Test
    void setInventory() {
        new DrinkTO("Fanta","0,33",24,1,100,50);
        assertTrue(clientcommunikation.setInventory("MM205","12345",
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

        CellarTO returncellar = clientcommunikation.getCellar("MM205", "12345");
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
        assertEquals(clientcommunikation.createDelivery("MM205","12345"),new DocTO("name"));
    }

    @Test
    void getDoc() {
        assertEquals("Rechnung 31.7.2020",clientcommunikation.getDoc("MM205","12345","Rechnung 20.8.2020").name);
    }

    @Test
    void getDocs() {
        //Dokumente anlegen
        DocTO[] dockListExpected = new DocTO[3];
        dockListExpected[0] = new DocTO("Rechnung 20.8.2020");
        dockListExpected[1] = new DocTO("Rechnung 31.7.2020");
        dockListExpected[2] = new DocTO("Inventur 15.5.2020");
        DocTO[] dockListactualy = clientcommunikation.getDocs("MM205", "12345");

        assertEquals(dockListExpected[0].name,clientcommunikation.getDocs("MM205", "12345")[0].name);
        assertEquals(dockListExpected[1].name,clientcommunikation.getDocs("MM205", "12345")[1].name);
        assertEquals(dockListExpected[2].name,clientcommunikation.getDocs("MM205", "12345")[2].name);

    }

    @Test
    void getInventory() {
        /*
        assertEquals(cellarmanagement.getInventory(1),
                new DrinkTO[]{new DrinkTO("Fanta","0,33",24,1,100,50)
                        ,new DrinkTO("Cola","0,33",24,1,100,50)});
         */

        DrinkTO[] drinks = new DrinkTO[2];
        drinks[0] = new DrinkTO("Fanta","0,33",24,1,100,50);
        drinks[1] = new DrinkTO("Cola","0,33",24,1,100,50);

        for (int i = 0;i <= 1; i++) {

            assertEquals(drinks[i].name, clientcommunikation.getInventory(1)[i].name);
            assertEquals(drinks[i].bottleType, clientcommunikation.getInventory(1)[i].bottleType);
            assertEquals(drinks[i].packType, clientcommunikation.getInventory(1)[i].packType);
            assertEquals(drinks[i].price, clientcommunikation.getInventory(1)[i].price);
            assertEquals(drinks[i].setvalue, clientcommunikation.getInventory(1)[i].setvalue);
            assertEquals(drinks[i].actualvalue, clientcommunikation.getInventory(1)[i].actualvalue);

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

            assertEquals(providerList[i].name, clientcommunikation.getProviderList()[i].name);
            assertEquals(providerList[i].bottleType, clientcommunikation.getProviderList()[i].bottleType);
            assertEquals(providerList[i].packType, clientcommunikation.getProviderList()[i].packType);
            assertEquals(providerList[i].price, clientcommunikation.getProviderList()[i].price);
            assertEquals(providerList[i].setvalue, clientcommunikation.getProviderList()[i].setvalue);
            assertEquals(providerList[i].actualvalue, clientcommunikation.getProviderList()[i].actualvalue);

        }
    }


}
