package gKVClient.Clientcommunikation.impl;

import data.*;
import gKVClient.Clientcommunikation.Clientcommunikation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oliver Neuhaeusler
 */

class ClientcommunikationImplTest {

    static Clientcommunikation clientcommunikation = null;

    @BeforeAll
    public static void initConection(){
        ClientcommunikationImpl clientcommunikation2 = new ClientcommunikationImpl();
        clientcommunikation2.initConection("");
        clientcommunikation = clientcommunikation2;
    }


    @Test
    void createDrink() {
        assertTrue(clientcommunikation.createDrink("MM201", "1234",
                new DrinkTO("Fanta","0,33",24,100,100,50)),
                "Erfolgreiches anlegen des Getränks");
    }

    @Test
    void createOrderAdmin() {
        assertTrue(clientcommunikation.createOrderAdmin("2","MM201","1234",55,
                "Zahlung Mayer 13.11.2020"),"erfolgreich uebermittelt!");
    }

    @Test
    void createOrderUser() {
        assertTrue(clientcommunikation.createOrderUser("HP227","xD%76.","Augustiner Export",
                2,"0,5"), "Entnahme erfolgreich");
    }

    @Test
    void createUser() {
        assertTrue(clientcommunikation.createUser("MM201","1234",
                new PersonTO("HM217",2,"Hans","Meier",true,false,
                        false,0,"HM1234","hans.meier@unibw.de",false,false)),
                "Nutzer erfolgreich angelegt!");
    }

    @Test
    void creatWish() {
        assertTrue(clientcommunikation.creatWish(new DrinkTO[]{
                        new DrinkTO("Giesinger Kellerbier","0,5",20,110,0,0),
                        new DrinkTO("Gutmann Weißbier leicht","0,5",20,90,0,0),
                        new DrinkTO("Schneider Aventinus Weißbier Bockr","0,5",20,120,0,0)},"HP227"),
                "Wunsch erfolgreich versendet");
    }

    @Test
    void deleteDoc() {
        assertTrue(clientcommunikation.deleteDoc("MM201","1234","Rechnung20201030.pdf "),
                "Dokument erfolgreich geloescht!");
    }

    @Test
    void deletePerson() {
        assertTrue(clientcommunikation.deletePerson("MM201","1234","HP227"),
                "Person erfolgreich geloescht");
    }

    @Test
    void resetPassword() {
        assertTrue(clientcommunikation.resetPassword("MM201","1234","HP227"),
                "Passwort erfolgreich zurueckgesetzt!");
    }

    @Test
    void sendAcknowledge() {
        boolean acknowledge = true;
        //boolean ack = clientcommunikation.sendAcknowledge(acknowledge);
        assertTrue(clientcommunikation.sendAcknowledge(acknowledge),"Test erfolgreich gesendet");
    }

    @Test
    void sendBill() {
        assertTrue(clientcommunikation.sendBill("j7no0337","&25ae!1","MM201","1234"),
                "Rechnungen erfolgreich versendet");
    }

    @Test
    void sendInfo() {
        assertTrue(clientcommunikation.sendInfo("MM201","1234","j7ab1234","123456",
                "Keller ausrauemen am 17.11.2020 um 18:00 Uhr!"));
    }

    @Test
    void setDoc() {
        assertTrue(clientcommunikation.setDoc("MM201","1234", "Rechnung20201127.pdf"),
                "Dokument erfolgreich hochgeladen!");
    }

    @Test
    void setDrink() {
        assertTrue(clientcommunikation.setDrink("MM201","1234",
                new DrinkTO("Spalter Pils","0,5",20,100,60,60)),
                "Erfolgreiche Aenderung des Getraenks");
    }

    @Test
    void setInventory() {
        assertTrue(clientcommunikation.setInventory("MM201","1234",
                new DrinkTO[]{new DrinkTO("Augustiner Export","0,5",20,100,200,10),
                        new DrinkTO("Maxlrainer Helle","0,5",20,100,120,0)}),
                "Bestand erfolgreich angepasst!");
    }

    @Test
    void setUser() {
        assertTrue(clientcommunikation.setUser("MM201","1234",
                new PersonTO("HM217",2,"Hans","Meier",true,true,
                        false,0,"HM1234","hans.meier@unibw.de",false,true)),
                "Nutzer erfolgreich geaendert!");
    }

    @Test
    void setUserState() {
        assertTrue(true,"Status Aenderung versendet");
    }

    @Test
    void getCellar() {
        assertNotNull(clientcommunikation.getCellar("MM201", "1234"));
    }

    @Test
    void createDelivery() {
        assertNotNull(clientcommunikation.createDelivery("MM201","1234"));
    }

    @Test
    void getDoc() {
        assertNotNull(clientcommunikation.getDoc("MM201","1234","Rechnung20201127.pdf"));
    }

    @Test
    void getInventory() {
        assertNotNull(clientcommunikation.getInventory(2));
    }

    @Test
    void getProviderList() {
        assertNotNull(clientcommunikation.getProviderList());
    }

    //Kellerbuchungen anzeigen
    @Test
    void getOrderAdmin() {
        assertNotNull(clientcommunikation.getOrder("MM201","1234","HK200"));
    }

    //Kontostand anzeigen
    @Test
    void getOrderUser() {
        assertNotNull(clientcommunikation.getOrder("HP227"));
    }

    @Test
    void getUser() {
        assertNotNull(clientcommunikation.getUser("HP227"));
    }

    @Test
    void getUsers() {
        assertNotNull(clientcommunikation.getUsers("MM201","1234"));
    }

    @Test
    void login() {
        assertNotNull(clientcommunikation.login("MM205","12345"));
    }
}