package gKVServer.aplicationlogic.impl;

import data.DrinkTO;
import data.OrderTO;
import data.PersonTO;
import gKVServer.Servercommunication.Servercommunication;
import gKVServer.aplicationlogic.Usermanagement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Autor: Brandes Lukas
 */
abstract class UsermanagementTest {

    public abstract Usermanagement getUsermanagement();
    Usermanagement usermanagement = getUsermanagement();

    @Test
    void createOrderAdmin() {
        PersonTO user = usermanagement.getUser("MM210");
        assertTrue(usermanagement.createOrderAdmin("MM210","MM205","12345",-10,"test"));
        // zum Überprüfen, ob die Buchung auch auf dem Nutzer gespeichert wrude
        assertEquals(user.balance-10,usermanagement.getUser("MM210").balance);
    }

    @Test
    void createOrderUser() {
        assertTrue(usermanagement.createOrderUser("MM201","12345", "Cola",0,"0,33"));
    }

    @Test
    void createUser() {
        PersonTO user = new PersonTO("MM221", 2, "User", "Dummy", true, true, false, 0, "&T6T8fqhan!N", "user@unibw.de", false, true);
        // Prüfen ob der Rückgabewert passt
        assertTrue(usermanagement.createUser("MM205", "12345",user));
        // prüfen ob die Person richtig angelegt wurde
        assertEquals(user.id,usermanagement.getUser("MM221").id);
        assertEquals(user.cellerId,usermanagement.getUser("MM221").cellerId);
        assertEquals(user.firstname,usermanagement.getUser("MM221").firstname);
        assertEquals(user.surename,usermanagement.getUser("MM221").surename);
        assertEquals(user.state,usermanagement.getUser("MM221").state);
        assertEquals(user.key,usermanagement.getUser("MM221").key);
        assertEquals(user.admin,usermanagement.getUser("MM221").admin);
        assertEquals(user.balance,usermanagement.getUser("MM221").balance);
        assertEquals(user.password,usermanagement.getUser("MM221").password);
        assertEquals(user.eMail,usermanagement.getUser("MM221").eMail);
        assertEquals(user.requestState,usermanagement.getUser("MM221").requestState);
        assertEquals(user.wish,usermanagement.getUser("MM221").wish);
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
        assertTrue(usermanagement.creatWish(wishlistUser,"MM205"));

        //Prüfung des korrekten Anlegens der Wunschliste
    }

    @Test
    void deletePerson() {
        assertTrue(usermanagement.deletePerson("MM205", "12345", "MM210"));
    }

    @Test
    void resetPassword() {
        assertTrue(usermanagement.resetPassword("MM205", "12345", "MM210"));
    }

    @Test
    void sendInfo() {
        //TODO ich werden den Test schreiben, sobald ich das mit dem Plug-In verstanden habe
        assertTrue(usermanagement.sendInfo("MM205", "12345", "j7...","12345", "Test"));
    }

    @Test
    void setUser() {
        PersonTO user = new PersonTO("MM220", 2, "User","Dummy", false, true, false, 10, "12345!!!", "user@unibw.de", false, true);
        assertEquals(true, usermanagement.setUser("MM205", "12345",user));
    }

    @Test
    void setUserState() {
        assertEquals(true, usermanagement.setUserState("MM210"));
    }

    @Test
    void getOrder() {
        OrderTO[] orders = new OrderTO[3];
        orders[0] = new OrderTO("MM205","Fanta",1,2, LocalDateTime.of(2019, 2, 13, 15, 56) );
        orders[1] = new OrderTO("MM205","Cola",5,2,LocalDateTime.of(2020, 2, 13, 15, 56) );


        for (int i = 0;i <= 1; i++) {

            assertEquals(orders[i].personID, usermanagement.getOrder("MM205")[i].personID);
            assertEquals(orders[i].name, usermanagement.getOrder("MM205")[i].name);
            assertEquals(orders[i].value, usermanagement.getOrder("MM205")[i].value);
            assertEquals(orders[i].amount, usermanagement.getOrder("MM205")[i].amount);
            assertEquals(orders[i].date, usermanagement.getOrder("MM205")[i].date);
        }
    }

    @Test
    void getUser() {
        // Warum kann man keine Transportobjekte vergleichen
        //  assertEquals( new  PersonTO("MM201", 2, "User","Dummy", false, true, false, 10, "12345!!!", "user@unibw.de", false, true),usermanagement.getUser("MM201"));


        PersonTO user = new PersonTO("MM201", 2, "User", "Dummy", true, true, false, 10, "12345", "user@unibw.de", false, true);
        assertEquals(user.id,usermanagement.getUser("MM201").id);
        assertEquals(user.cellerId,usermanagement.getUser("MM201").cellerId);
        assertEquals(user.firstname,usermanagement.getUser("MM201").firstname);
        assertEquals(user.surename,usermanagement.getUser("MM201").surename);
        assertEquals(user.state,usermanagement.getUser("MM201").state);
        assertEquals(user.key,usermanagement.getUser("MM201").key);
        assertEquals(user.admin,usermanagement.getUser("MM201").admin);
        assertEquals(user.balance,usermanagement.getUser("MM201").balance);
        assertEquals(user.password,usermanagement.getUser("MM201").password);
        assertEquals(user.eMail,usermanagement.getUser("MM201").eMail);
        assertEquals(user.requestState,usermanagement.getUser("MM201").requestState);
        assertEquals(user.wish,usermanagement.getUser("MM201").wish);

    }


    @Test
    void getUsers() {

        PersonTO[] users = new PersonTO[4];
        users[0] = new PersonTO("HK200", 2, "Hauskasse", "200", true, false, false, 0, "123456789Ab!", "haus200@unibw.de", false, false);
        users[1] = new PersonTO("MM205", 2, "Admin", "Dummy", true, true, true, -50, "12345", "admin@unibw.de", false, true);
        users[2] = new PersonTO("MM201", 2, "User", "Dummy", true, true, false, 10, "12345", "user@unibw.de", false, true);
        users[3] = new PersonTO("MM202", 2, "User", "Dummy", true, true, false, 0, "12345", "user@unibw.de", false, false);

        //  assertEquals(users,usermanagement.getUsers("MM205","12345"));
        for (int i = 0;i <= 3; i++) {

            assertEquals(users[i].id, usermanagement.getUser(users[i].id).id);
            assertEquals(users[i].cellerId, usermanagement.getUser(users[i].id).cellerId);
            assertEquals(users[i].firstname, usermanagement.getUser(users[i].id).firstname);
            assertEquals(users[i].surename, usermanagement.getUser(users[i].id).surename);
            assertEquals(users[i].state, usermanagement.getUser(users[i].id).state);
            assertEquals(users[i].key, usermanagement.getUser(users[i].id).key);
            assertEquals(users[i].admin, usermanagement.getUser(users[i].id).admin);
            assertEquals(users[i].balance, usermanagement.getUser(users[i].id).balance);
            assertEquals(users[i].password, usermanagement.getUser(users[i].id).password);
            assertEquals(users[i].eMail, usermanagement.getUser(users[i].id).eMail);
            assertEquals(users[i].requestState, usermanagement.getUser(users[i].id).requestState);
            assertEquals(users[i].wish, usermanagement.getUser(users[i].id).wish);
        }
    }

    @Test
    void login() {
        assertEquals(usermanagement.login("MM201","12345"), Servercommunication.ReturnLogin.LOGIN_SUCCESSFUL_USER);

    }
}