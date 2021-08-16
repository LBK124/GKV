package gKVServer.database.impl;

import data.*;
import gKVServer.database.CellarDB;
import gKVServer.database.UserDB;
import gKVServer.database.data.Cellar;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
//Autor Richard Moeckel
public abstract class CellarBDimplTest {
    private Data data;
    private UserDB userDB;
    private CellarDB cellarDB;

    protected abstract Data getData();

    @BeforeEach
    void init() {
        data = getData();
        userDB = data.getUserData();
        cellarDB = data.getCellarData();
        //System.out.println("fill w/ testdata");
        //fillWithTestData();

    }




    @Nested
    class Tests {
        @Test
        void initTest() {
            assertNotNull(data);
            assertNotNull(userDB);
            assertNotNull(cellarDB);
        }

        @Test
        void getCellerCash() {
            int cash = 500;
            assertTrue(cellarDB.setCellerCash(1, cash));
            assertEquals(cash, cellarDB.getCellerCash(1));
        }


        @Test
        void setCellerCash() {
            int cash = 2500;
            assertTrue(cellarDB.setCellerCash(1, cash));
            assertEquals(cash, cellarDB.getCellerCash(1));
        }

        @Test
        void getDoc() {
            assertTrue(false);

        }

        @Test
        void getDocList() {
            assertTrue(false);
        }

        @Test
        void setDoc() {
            assertTrue(false);
        }

        @Test
        void getState() {
            CellarTO celler = cellarDB.getCellar(2);
            assertEquals(celler.state, cellarDB.getState(2));
        }

        @Test
        void setState() {
            CellarTO celler = cellarDB.getCellar(2);
            assertTrue(cellarDB.setState(celler.CellarID, false));
            assertEquals(false, cellarDB.getState(2));
        }

        @Test
        void getandsetTribute() {
            assertTrue(cellarDB.setTribute(2, 50));
           assertEquals(50,cellarDB.getTribute(2) );
        }




        @Test
        void setgetInventory() {
            DrinkTO[] drinks ={ new DrinkTO("Kola", "0.33", 24, 100, 10, 5),
                    new DrinkTO("Fanta", "0.5", 10, 200, 15, 3)};
            assertTrue(cellarDB.setInventory(2, drinks));
            assertEquals(cellarDB.getInventory(2).length, drinks.length);
        }



        @Test
        void getBill() {

          BillTO[] bill =  cellarDB.getBill("UD205");

            assertEquals(bill[0].BillTimestart.toString(),
                    LocalDateTime.of(2020, 10, 1, 0, 0 ,0).toString());


        }

        @Test
        void createDrink() {
            DrinkTO drink = new DrinkTO("Bier", "0.5", 24, 100, 200, 2);
            assertTrue(cellarDB.createDrink(drink, 2));
            assertEquals(cellarDB.getDrink("Bier", "0.5", 2).name, drink.name);

        }

        @Test
        void deleteDoc() {
            assertTrue(false);
        }

        @Test
        void setDoc1() {
            assertTrue(false);
        }

        @Test
        void setDrink() {
            DrinkTO drink1 = new DrinkTO("Bier2", "0.5", 24, 100, 200, 2);
            assertTrue(cellarDB.createDrink(drink1, 2));

            DrinkTO drink = new DrinkTO("Bier2", "0.5", 24, 100, 200, 42);
            assertTrue(cellarDB.setDrink(2, drink));
        }



        @Test
        void getCellar() {
            CellarTO cellar = cellarDB.getCellar(2);
            assertEquals(cellar.CellarID, 2);
        }

        @Test
        void getDoc1() {
            assertTrue(false);
        }

        @Test
        void getDoc2() {
            assertTrue(false);
        }

        @Test
        void getProviderList() {
            DrinkTO[] wishlist = cellarDB.getProviderList();
            assertTrue(wishlist[0].name.contains("Kola"));
        }

        @Test
        void getOrder() {
            OrderTO[] order = cellarDB.getOrder("UD205");
            assertTrue(order[0].personID == "UD205");
        }
        //Userdb Tests
        @Test
        void createandgetUserUDB() {
            PersonTO user = new PersonTO("TU203", 2, "Firstname", "Lastname", true,
                    true, false, 0, "password", "test@unibw.de", false, false);
            assertTrue(userDB.createUser(user));
            assertEquals(user.id,userDB.getUser("TU203").id);
        }


        @Test
        void createOrderUDB() {
            LocalDateTime timestamp = LocalDateTime.now();
            OrderTO order = new OrderTO("UD205", "Kola", 420, 1, timestamp);
            assertTrue(userDB.createOrder(order));
            //assertEquals(order.toString(), userDB.getOrder("UD205", timestamp)[0].toString());

        }

        @Test
        void getOrderUDB() {
            OrderTO[] order = userDB.getOrder("UD205", LocalDateTime.of(2020, 10, 1, 00, 00, 0),
                    LocalDateTime.of(2020, 10, 31, 00, 00, 0));
            OrderTO[] order2 = userDB.getOrder("UD205", LocalDateTime.of(2020, 10, 1, 00, 00, 0));

            assertEquals(order[0].name, order2[0].name);




        }

        @Test
        void createandgetbillUDB() {
            OrderTO[] order = userDB.getOrder("UD205", LocalDateTime.of(2020, 10, 1, 00, 00, 0),
                    LocalDateTime.of(2020, 10, 31, 00, 00, 0));
            BillTO bill = new BillTO(LocalDateTime.of(2020, 10, 1, 00, 00, 0),
                    LocalDateTime.of(2020, 10, 31, 00, 00, 0), order);
            assertTrue(userDB.createbill(bill));
            BillTO bill2 = userDB.getBill("UD205", LocalDateTime.of(2020, 10, 1, 00, 00, 0));
            assertEquals(bill.BillTimestart,bill2.BillTimestart);


        }


        @Test
        void createOrder1UDB() {
            LocalDateTime timestamp = LocalDateTime.now();
            assertTrue(userDB.createOrder("UD205", 500,"verwendungszweck", timestamp));
        }

        @Test
        void createOrder2UDB() {
            LocalDateTime timestamp = LocalDateTime.now();
            assertTrue(userDB.createOrder("UD205", "Kola", 1, "0.33", timestamp));

        }

        @Test
        void createWishUDB() {
            DrinkTO[] wishlsit1 = userDB.getWishlist(2);
            assertTrue(userDB.setWishlist(2, wishlsit1));
        }

        @Test
        void deltePersonUDB() {
            PersonTO user = new PersonTO("TU203", 2, "Firstname", "Lastname", true,
                    true, false, 0, "password", "test@unibw.de", false, false);
            boolean test= userDB.getUser("TU203").id.contains("TU203");
            if(!test) {
               assertTrue(userDB.createUser(user));
            }
            assertTrue(userDB.deltePerson("TU203"));
        }

        @Test
        void setUserUDB() {

            PersonTO user = new PersonTO("TU203", 2, "Firstname", "Lastname", true,
                    true, false, 0, "password", "test@unibw.de", false, false);
            PersonTO user1 = new PersonTO("TU203", 2, "Firstname", "Lastname", true,
                    false, false, 0, "password", "test@unibw.de", false, false);
            if(userDB.getUser("TU203") == null) {
                assertTrue(userDB.createUser(user));
            }
            assertTrue(userDB.setUser(user1));
        }


        @Test
        void getUsersUDB() {
            PersonTO[] persons = userDB.getUsers(2);
            if(persons[0].toString().contains("AD201"))
                assertTrue(true);
            if(persons[1].toString().contains("AD201"))
                assertTrue(true);
            if(persons[2].toString().contains("AD201"))
                assertTrue(true);

            assertTrue(true);


        }

    }
}