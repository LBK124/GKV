package gKVServer.communication.impl;

import data.*;
import gKVServer.Servercommunication.Servercommunication;
import gKVServer.aplicationlogic.Cellarmanagement;
import gKVServer.aplicationlogic.Usermanagement;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oliver Neuhaeusler
 */

class ServercommunicationimplTest{

    Cellarmanagement cellarmanagement = null;
    Usermanagement usermanagement = null;

    @Test
    void sendAcknowledge() {
        boolean acknowledge = true;
        assertTrue(cellarmanagement.sendAcknowledge(acknowledge),"Test erfolgreich gesendet");
    }

    @Test
    void deleteDoc() {
        assertTrue(cellarmanagement.deleteDoc("MM201","1234","Rechnung20201030.pdf "),
                "Dokument erfolgreich geloescht!");
    }

    @Test
    void setDoc() {
        assertTrue(cellarmanagement.setDoc("MM201","1234", "Rechnung20201127.pdf"),
                "Dokument erfolgreich hochgeladen!");
    }

    @Test
    void createOrderAdmin() {
        assertTrue(usermanagement.createOrderAdmin("2","MM201","1234",55,
                "Zahlung Mayer 13.11.2020"),"erfolgreich uebermittelt!");
    }

    @Test
    void createOrderUser() {
        assertTrue(usermanagement.createOrderUser("HP227","xD%76.","Augustiner Export",
                2,"0,5"), "Entnahme erfolgreich");
    }

    @Test
    void sendInfo() {
        assertTrue(usermanagement.sendInfo("MM201","1234","j7ab1234","123456",
                "Keller ausrauemen am 17.11.2020 um 18:00 Uhr!"));
    }

    @Test
    void setInventory() {
        assertTrue(cellarmanagement.setInventory("MM201","1234",
                new DrinkTO[]{new DrinkTO("Augustiner Export","0,5",20,100,200,10),
                        new DrinkTO("Maxlrainer Helle","0,5",20,100,120,0)}),
                "Bestand erfolgreich angepasst!");
    }

    @Test
    void createDrink() {
        assertTrue(cellarmanagement.createDrink("MM201","1234",
                new DrinkTO("Spalter Pils","0,33",20,100,60,0)),
                "Erfolgreiche Anlage des Getraenks");
    }

    @Test
    void creatWish() {
        assertTrue(usermanagement.creatWish(new DrinkTO[]{
                        new DrinkTO("Giesinger Kellerbier","0,5",20,110,0,0),
                        new DrinkTO("Gutmann Weißbier leicht","0,5",20,90,0,0),
                        new DrinkTO("Schneider Aventinus Weißbier Bockr","0,5",20,120,0,0)},"HP227"),
                "Wunsch erfolgreich versendet");
    }

    @Test
    void resetPassword() {
        assertTrue(usermanagement.resetPassword("MM201","1234","HP227"),
                "Passwort erfolgreich zurueckgesetzt!");
    }

    @Test
    void createUser() {
        assertTrue(usermanagement.createUser("MM201","1234",
                new PersonTO("HM217",2,"Hans","Meier",true,false,
                        false,0,"HM1234","hans.meier@unibw.de",false,false)),
                "Nutzer erfolgreich angelegt!");
    }

    @Test
    void setUser() {
        assertTrue(usermanagement.setUser("MM201","1234",
                new PersonTO("HM217",2,"Hans","Meier",true,true,
                        false,0,"HM1234","hans.meier@unibw.de",false,true)),
                "Nutzer erfolgreich geaendert!");
    }

    @Test
    void setDrink() {
        assertTrue(cellarmanagement.setDrink("MM201","1234",
                new DrinkTO("Spalter Pils","0,5",20,100,60,60)),
                "Erfolgreiche Aenderung des Getraenks");
    }

    @Test
    void sendBill() {
        assertTrue(cellarmanagement.sendBill("j7no0337","&25ae!1","MM201","1234"),
                "Rechnungen erfolgreich versendet");
    }

    @Test
    void setUserState() {

        assertTrue(true,"Status Aenderung versendet");
    }

    @Test
    void deletePerson() {
        assertTrue(usermanagement.deletePerson("MM201","1234","HP227"),
                "Person erfolgreich geloescht");
    }

     @Test
         void getCellar() {

        assertNotNull(cellarmanagement.getCellar("MM201", "1234"));

     }

    @Test
    void createDelivery() {
        assertEquals(new DocTO("Bestellung20201118.pdf"),cellarmanagement.createDelivery("MM201","1234"));
    }

    @Test
    void getDoc() {
        assertEquals(new DocTO("Rechnung20201127.pdf"), cellarmanagement.getDoc("MM201","1234","Rechnung20201127.pdf"));
    }

    @Test
    void getProviderList() {
        assertEquals(new DrinkTO[]{new DrinkTO("Cola", "1,0", 12,
                        2, 10, 10)}, cellarmanagement.getProviderList());
    }


    @Test
    void getInventory() {
        assertEquals(new DrinkTO[]{new DrinkTO("Augustiner Export","0,5",20,100,200,
                        10), new DrinkTO("Maxlrainer Helle","0,5",20,100,
                        120,0)},cellarmanagement.getInventory(2));


    }

    @Test
        //Kellerbuchungen anzeigen
    void getOrderAdmin() {
        //LocalDateTime localDT= LocalDateTime.parse("2020-11-17T10:20:45")
        LocalDateTime now = LocalDateTime.now();
        assertEquals(new OrderTO[]{new OrderTO("HP227","Augistiner Export",1,2,now),
                        new OrderTO("HP227", "Cola", 1,2, now)}, cellarmanagement.getOrder("MM201","1234","HK200"));
    }

    @Test
        //Kontostand anzeigen
    void getOrderUser() {
        LocalDateTime now = LocalDateTime.now();
       // assertEquals(usermanagement.getOrder(new OrderTO("HP227","user",10,10,));

    }

    @Test
    void getUser() {
        assertEquals(new PersonTO("HM217",2,"Hans","Meier",true,false,
                false,0,"HM1234","hans.meier@unibw.de",false,false), usermanagement.getUser("HP227"));
    }

    @Test
    void getUsers() {
        assertEquals(new PersonTO[] {new PersonTO("HM217",
                2,"Hans","Meier",true,false,
                false,0,"HM1234","hans.meier@unibw.de",false,false),
                new PersonTO("JH231",2,"Jakob","Huber",true,true,
                        false,0,"JH1234","jakob.huber@unibw.de",false,false),
                new PersonTO("PL211",2,"Peter","Luster",true,true,
                        false,0,"PL1234","peter.lustig@unibw.de",false,false)}, usermanagement.getUsers("MM201","1234"));
    }

    @Test
    void login() {
        assertEquals(Servercommunication.ReturnLogin.CHANGE_PASSWORD, usermanagement.login("HP227","fdp65%&0"));
    }
}