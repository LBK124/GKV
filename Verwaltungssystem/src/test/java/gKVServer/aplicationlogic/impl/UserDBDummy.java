package gKVServer.aplicationlogic.impl;

import data.*;
import gKVServer.database.UserDB;

import java.time.LocalDateTime;
import java.util.WeakHashMap;
import java.util.concurrent.BlockingDeque;

/**
 * Autor: Brandes Lukas
 */
public class UserDBDummy implements UserDB {

    private BillTO[] bills;
    private BillTO billDb1;
    private BillTO billDb2;
    private PersonTO[] users;
    private DrinkTO[] inventory;
    private DrinkTO[] wishlist;
    private DrinkTO[] providerList;
    private CellarTO cellar;
    private int[] rooms = {201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,
                            222,223,224,225,226,227,228,229,230,231,232,233,234,235,236};
    private OrderTO[] orders;
    private DocTO[] docks;

    private PersonTO logedUser;
    public UserDBDummy() {

        //Personen anlegen
        users = new PersonTO[21];
        users[0] = new PersonTO("HK200", 2, "Hauskasse", "200", true, false, false, 0, "123456789Ab!", "haus200@unibw.de", false, false);
        users[1] = new PersonTO("MM205", 2, "Admin", "Dummy", true, true, true, -50, "12345", "admin@unibw.de", false, true);
        users[2] = new PersonTO("MM201", 2, "User", "Dummy", true, true, false, 10, "12345", "user@unibw.de", false, true);
        users[3] = new PersonTO("MM202", 2, "User", "Dummy", true, true, false, 0, "12345", "user@unibw.de", false, false);
        users[4] = new PersonTO("MM203", 2, "User", "Dummy", true, true, false, 0, "12345", "user@unibw.de", false, false);
        users[5] = new PersonTO("MM204", 2, "User", "Dummy", false, true, false, 0, "?k0-6DqW", "user@unibw.de", false, false);
        users[6] = new PersonTO("MM220", 2, "User", "Dummy", true, true, false, 0, "?k0-6DqW", "user@unibw.de", false, false);
        users[7] = new PersonTO("MM206", 2, "User", "Dummy", false, true, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[8] = new PersonTO("MM207", 2, "User", "Dummy", true, false, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[9] = new PersonTO("MM208", 2, "User", "Dummy", false, true, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[10] = new PersonTO("MM209", 2, "User", "Dummy", true, false, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[11] = new PersonTO("MM210", 2, "User", "Dummy", false, true, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[12] = new PersonTO("MM211", 2, "User", "Dummy", true, false, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[13] = new PersonTO("MM212", 2, "User", "Dummy", false, true, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[14] = new PersonTO("MM213", 2, "User", "Dummy", true, true, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[15] = new PersonTO("MM214", 2, "User", "Dummy", false, true, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[16] = new PersonTO("MM215", 2, "User", "Dummy", true, false, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[17] = new PersonTO("MM216", 2, "User", "Dummy", false, true, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[18] = new PersonTO("MM217", 2, "User", "Dummy", true, true, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[19] = new PersonTO("MM218", 2, "User", "Dummy", false, false, false, 0, "?k0-6DqW", "user@unibw.de", false, true);
        users[20] = new PersonTO("MM219", 2, "User", "Dummy", true, true, false, 0, "?k0-6DqW", "user@unibw.de", false, true);

        //Inventar anlegen
        inventory = new DrinkTO[5];
        inventory[0] = new DrinkTO("Fanta","0,33",24,1,100,50);
        inventory[1] = new DrinkTO("Cola","0,33",24,1,100,50);

        //wunschliste anlegen
        wishlist = new DrinkTO[3];
        wishlist[0] = new DrinkTO("Fanta","0,33",24,1,100,50);
        wishlist[1] = new DrinkTO("Cola","0,33",24,1,100,50);

        //Lieferantenliste anlegen
        String[] name = {"Augustiener Hell", "Tegernseer Hell", "Fritz Cola", "Fanta", "Sprite", "Cola", "Apfelschorle", "Desperados", "Erdinger Alkoholfrei", "Voss Wasser"};
        String[] Bottle = {"Glas 0.5L", "Glas 0.5L", "Glas 0.33L", "Mehrweg 1.0L", "Mehrweg 1.0L", "Mehrweg 1.0L", "Mehrweg 1.0L", "Glas 0.33L", "Glas 0.5L", "Glas 0.2L"};
        int[] pack = {20, 20, 24, 12, 12, 12, 12, 24, 20, 6};
        int[] price = {1550, 1550, 2600, 1100, 1100, 1100, 1300, 2500, 1800, 4000};
        providerList = new DrinkTO[10];
        for (int i = 0; i < 10; ++i) {
            providerList[i] = new DrinkTO(name[i], Bottle[i], pack[i], price[i], 0, 0);
        }

        //Dokumente anlegen
        docks = new DocTO[3];
        docks[0] = new DocTO("Rechnung 20.8.2020");
        docks[1] = new DocTO("Rechnung 31.7.2020");
        docks[2] = new DocTO("Inventur 15.5.2020");

        //Keller anlegen
        cellar = new CellarTO(12345, docks, true, 70, wishlist, rooms, 2);

        //Bestellung anlegen
        orders = new OrderTO[3];
        orders[0] = new OrderTO("MM205","Fanta",1,2,LocalDateTime.of(2019, 2, 13, 15, 56) );
        orders[1] = new OrderTO("MM205","Cola",5,2,LocalDateTime.of(2020, 2, 13, 15, 56) );
//        orders[2] = null;

        //Rechnung anlegen
        billDb1 = new BillTO(null,null, orders );

    }
    @Override
    public boolean createUser(PersonTO person) {
        if(users == null){
            users = new PersonTO[1];
            users[0] = person;
        }
        else{
            PersonTO[] temp = new PersonTO[users.length + 1];
            System.arraycopy(users,0,temp,0,users.length);
            temp[users.length] = person;
            users = temp;
        }
        return true;
    }

    @Override
    public PersonTO getUser(String personID) {

        for(PersonTO person : users){
            if(person.id.contentEquals(personID)){
                return person;
            }
        }
        return null;
    }


    @Override
    public boolean setUser(PersonTO personTO) {
        for(int i = 0; i < users.length; ++i){
            if(users[i].id.contentEquals(personTO.id)){
                users[i] = personTO;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean createOrder(OrderTO order) {
        if(order != null){
            return false;
        }
        OrderTO[] temp = new OrderTO[orders.length + 1];
        System.arraycopy(orders,0,temp,0,orders.length);
        temp[orders.length] = order;
        orders = temp;
        return true;
    }

    @Override
    public OrderTO[] getOrder(String personID, LocalDateTime timestampstart, LocalDateTime timstampstop) {
        return orders;
    }

    @Override
    public boolean createbill(BillTO bill) {
        billDb2 = bill;
        return true;
    }

    @Override
    public OrderTO[] getOrdersnoBill(String personID) {
        return new OrderTO[0];
    }

    @Override
    public BillTO getBill(String personID, LocalDateTime timstamp) {
        return billDb2;
    }


    @Override
    public boolean createOrder(String userID, int value, String purpose, LocalDateTime timestamp) {
        if (orders == null){
            orders = new OrderTO[1];
            orders[0] = new OrderTO(userID,purpose,value,1,timestamp);
        }
        else{
            OrderTO[] temp = new OrderTO[orders.length+1];
            System.arraycopy(orders,0,temp,0,orders.length);
            temp[orders.length] = new OrderTO(userID,purpose,value,1,timestamp);
            orders = temp;
        }
        return true;
    }


    @Override
    public boolean createOrder(String userID, String drink, int quantity, String bottletype, LocalDateTime timestamp) {
        if (orders == null){
            orders = new OrderTO[1];
            orders[0] = new OrderTO(userID,drink,quantity,1,timestamp);
        }
        else{
            OrderTO[] temp = new OrderTO[orders.length+1];
            System.arraycopy(orders,0,temp,0,orders.length);
            temp[orders.length] = new OrderTO(userID,drink,quantity,1,timestamp);
            orders = temp;
        }
        return true;
    }


    @Override
    public boolean deltePerson(String userID) {
        for(int i = 0; i < users.length;++i){       //für alle nutzer
            if(users[i].id.contentEquals(userID)){  //sucht nutzer
                if(users[i].balance == 0){          //wenn keine rechung mehr offen
                    if(users[i].admin){     //wenn ad1min
                        return false;           //abbruch
                    }
                    PersonTO[] temp = new PersonTO[users.length - 1];   //wenn keine Schulden und admin bedingung nicht abbricht
                    System.arraycopy(users, 0, temp, 0, i);
                    System.arraycopy(users, i + 1,temp, i, users.length - i - 1);
                    users = temp;
                    return true;
                }
                else{
                    return false;                   //wenn geldbeträge offen sind abbruch
                }
            }
        }
        return false;
    }

    @Override
    public OrderTO[] getOrder(String user, LocalDateTime timeStamp) {
            return orders;
    }

    @Override
    public PersonTO[] getUsers(int cellarID) {
        return users;
    }

    @Override
    public DrinkTO[] getWishlist(int cellerID) {
        return wishlist;
    }

    @Override
    public boolean setDrinkUDB(int cellarID, DrinkTO drink) {
        for (int i = 0; i < inventory.length;++i){
            if(inventory[i].name.contentEquals(drink.name)){
                DrinkTO list[] = new DrinkTO[inventory.length];
                System.arraycopy(inventory,0,list,0, i);
                list[i] = drink;
                System.arraycopy(inventory,i+1,list,i+1,inventory.length - i - 1);
                inventory = list;
                return true;
            }
        }
        return false;
    }

    @Override
    public DrinkTO getDrinkDBU(String name, String size, int cellarID) {
        for(DrinkTO drink : inventory){
            if(drink.name.contentEquals(name)){
                return drink;
            }
        }
        return null;
    }

    @Override
    public boolean setWishlist(int cellerID, DrinkTO[] list) {
        wishlist = list;
        return true;
    }
}
