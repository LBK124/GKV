package gKVClient.GUI;

import data.*;
import gKVClient.Clientcommunikation.Clientcommunikation;
import gKVServer.Servercommunication.Servercommunication;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Clientdummy implements Clientcommunikation {
    private PersonTO[] users;

    private DrinkTO[] inventory;
    private DrinkTO[] wishlist;
    private DrinkTO[] providerList;

    private CellarTO cellar;
    private int[] rooms = {201,202,203,204,205,206,207,208,209,210,211,212,213};

    private OrderTO[] orders;

    private DocTO[] docks;

    private PersonTO logedUser;

    //Konstruktor
    public Clientdummy(){

        //Personen anlegen
        users = new PersonTO[3];
        users[0] = new PersonTO("HK200",2,"Hauskasse","200",false,false,false,0, "123456789Ab!","haus200@unibw.de",false,false);
        users[1] = new PersonTO("AD201",2,"Admin","Dummy",false,true, true, 0,"12Admin34!!!","admin@unibw.de",false,true);
        users[2] = new PersonTO("UD205",2,"User","Dummy",false,false,false,0,"123UsEr45!!!","user@unibw.de", false,true);
        //Inventar anlegen
        inventory = new DrinkTO[4];
        inventory[0] = new DrinkTO("Cola","PET 1.0L",12,100,5,55);
        inventory[1] = new DrinkTO("Augustiener Hell","Glas 0.5L",20,80,10,155);
        inventory[2] = new DrinkTO("Tegernseer Hell", "Glas 0.5L",20,80,10,140);
        inventory[3] = new DrinkTO("Fritz Cola", "Glas 0.33L",24,110,5,71);
        //wunschliste anlegen
        wishlist = new DrinkTO[0];

        //Lieferantenliste anlegen
        String[] name ={"Augustiener Hell", "Tegernseer Hell", "Fritz Cola", "Fanta", "Sprite", "Cola", "Apfelschorle", "Desperados", "Erdinger Alkoholfrei", "Voss Wasser"};
        String[] Bottle = {"Glas 0.5L","Glas 0.5L","Glas 0.33L","Mehrweg 1.0L", "Mehrweg 1.0L", "Mehrweg 1.0L", "Mehrweg 1.0L", "Glas 0.33L", "Glas 0.5L", "Glas 0.2L"};
        int[] pack = {20,20,24,12,12,12,12,24,20,6};
        int[] price = {1550,1550,2600,1100,1100,1100,1300,2500,1800,4000};

        providerList = new DrinkTO[10];
        for(int i = 0; i < 10; ++i){
            providerList[i] = new DrinkTO(name[i],Bottle[i],pack[i],price[i],0,0);
        }

        //Dokumente anlegen
        docks = new DocTO[3];
        docks[0] = new DocTO("Rechnung 20.8.2020");
        docks[1] = new DocTO("Rechnung 31.7.2020");
        docks[2] = new DocTO("Inventur 15.5.2020");

        //Keller anlegen
        cellar = new CellarTO(12345,docks,true,70,wishlist,rooms,2);
    }


    //Interface implementation
    @Override
    //Fügt neues Getränk an Feld an
    public boolean createDrink(String adminID, String adminPSW, DrinkTO drink) {
        DrinkTO[] temp = new DrinkTO[inventory.length +1];

        System.arraycopy(inventory,0,temp,0,inventory.length);

        temp[inventory.length] = drink;
        inventory = temp;
        return true;
    }

    @Override
    //Lässt den Admin auf andere Benutzer Buchen
    public boolean createOrderAdmin(String cellarUserID, String adminID, String adminPSW, int value, String usage) {
        boolean exist = false;
        for (PersonTO user : users) {
            if (user.id.contentEquals(cellarUserID)) {
                exist = true;
            }
        }
        if(!exist){
            return false;
        }

        if (orders == null){
            orders = new OrderTO[1];
            orders[0] = new OrderTO(cellarUserID,usage,value,0,LocalDateTime.now());
        }
        else{
            OrderTO[] temp = new OrderTO[orders.length+1];

            System.arraycopy(orders,0,temp,0,orders.length);

            temp[orders.length] = new OrderTO(cellarUserID,usage,value,0,LocalDateTime.now());
            orders = temp;
        }
        return true;
    }

    @Override
    //Fügt eine neue Buchung hinzu
    public boolean createOrderUser(String userID, String userPSW, String drinkName, int amount, String bottleType) {
        int pr = 0;
        for (DrinkTO drinkTO : inventory) {
            if (drinkTO.name.contentEquals(drinkName)) {
                pr = drinkTO.price;
                if(reduceDrink(drinkTO,amount) == false){
                    return false;
                }
            }
        }
        if (orders == null){
            orders = new OrderTO[1];
            orders[0] = new OrderTO(userID,drinkName,pr * amount,amount,LocalDateTime.now());
        }
        else{
            OrderTO[] temp = new OrderTO[orders.length + 1];

            System.arraycopy(orders,0,temp,0,orders.length);

            temp[orders.length] = new OrderTO(userID,drinkName,pr * amount,amount,LocalDateTime.now());
            orders = temp;
        }

        return true;
    }

    @Override
    //Erstellt einen neuen Nutzer
    public boolean createUser(String adminID, String adminPSW, PersonTO user) {
        if(users == null){
            users = new PersonTO[1];
            users[0] = user;
        }
        else{
            PersonTO[] temp = new PersonTO[users.length + 1];
            System.arraycopy(users,0,temp,0,users.length);
            temp[users.length] = user;
            users = temp;
        }
        return true;
    }

    @Override
    public boolean creatWish(DrinkTO[] drinks, String userID) {
        return false;
    }
/*
    @Override
    //Erstellt ein neues Wunschgetränk
    public boolean creatWish(String drinkName, String bottleType, int packType, String userID) {
        boolean exist = false;
        for(int i = 0; i < users.length; ++i){
            if(users[i].id.contentEquals(userID)){
                if(users[i].wish) {
                    exist = true;
                }
                users[i] = new PersonTO(users[i].id,users[i].cellerId,users[i].firstname,users[i].surename,users[i].state,users[i].key,users[i].admin,users[i].balance,users[i].password,users[i].eMail,users[i].requestState,false);
            }
        }
        if(!exist){
            return false;
        }
        if(wishlist == null){
            wishlist = new DrinkTO[1];
            wishlist[0] = new DrinkTO(drinkName,bottleType,packType,0,0,0);
        }
        else{
            DrinkTO[] temp = new DrinkTO[wishlist.length + 1];

            System.arraycopy(wishlist,0,temp,0,wishlist.length);

            temp[wishlist.length] = new DrinkTO(drinkName,bottleType,packType,0,0,0);
            wishlist = temp;
        }

        return true;
    }
*/
    @Override
    //Löscht ein Dokument
    public boolean deleteDoc(String adminID, String adminPSW, String docName) {
        for(int i = 0; i < docks.length;++i){
            if(docks[i].name.contentEquals(docName)){
                if(docks.length == 1){
                    docks = null;
                }
                else {
                    DocTO[] temp = new DocTO[docks.length - 1];
                    System.arraycopy(docks, 0, temp, 0, i);
                    System.arraycopy(docks, i + 1, temp, i, docks.length - i - 1);
                    docks = temp;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    //Löscht eine Person
    public boolean deletePerson(String adminID, String adminPSW, String userID) {
        for(int i = 0; i < users.length;++i){       //für alle nutzer
            if(users[i].id.contentEquals(userID)){  //sucht nutzer
                if(users[i].balance == 0){          //wenn keine rechung mehr offen
                    if(users[i].admin){     //wenn ad1min
                        if(getAdminAmount() == 1){  //wenn einziger admin
                            return false;           //abbruch
                        }
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
    //setzt das passworts eines Benutzers zurück
    public boolean resetPassword(String adminID, String adminPSW, String userID) {
        for(int i = 0; i < users.length; ++i){
            if(users[i].id.contentEquals(userID)){
                users[i] = new PersonTO(users[i].id,
                                        users[i].cellerId,
                                        users[i].firstname,
                                        users[i].surename,
                                        users[i].state,
                                        users[i].key,
                                        users[i].admin,
                                        users[i].balance,
                                "1234567AbC!!",
                                        users[i].eMail,
                                        users[i].requestState,
                                        users[i].wish);
                return true;
            }
        }
        return false;
    }

    @Override
    //schickt eine bestätigung an den Server (redet mit sich selbst)
    public boolean sendAcknowledge(boolean acknowledge) {
        return acknowledge;
    }

    @Override
    //Versendet die rechungen fällt hier weg
    public boolean sendBill(String adminRZID, String adminRZPSW, String adminID, String adminPSW) {
        return true;
    }

    @Override
    //Versendet eine info an den Verteiler fällt hier weg
    public boolean sendInfo(String adminID, String adminPSW, String adminRZID, String adminRZPSW, String message) {
        return !message.contentEquals("");
    }

    @Override
    //setzt neues Dokument
    public boolean setDoc(String adminID, String adminPSW, String docName) {
        if(docks == null){
            docks = new DocTO[1];
            docks[0] = new DocTO(docName);
        }
        else{
            DocTO[] temp = new DocTO[docks.length + 1];
            System.arraycopy(docks,0,temp,0,docks.length);
            temp[docks.length] = new DocTO(docName);
            docks = temp;
        }
        return true;
    }

    @Override
    //fügt neues Getränk dem Keller hinzu
    public boolean setDrink(String adminID, String adminPSW, DrinkTO drink) {
        if(inventory == null){
            inventory = new DrinkTO[1];
            inventory[0] = drink;
        }
        else{
            DrinkTO[] temp = new DrinkTO[inventory.length + 1];
            System.arraycopy(inventory,0,temp, 0,inventory.length);
            temp[inventory.length] = drink;
            inventory = temp;
        }
        return true;
    }

    @Override
    //setzt neues Inventar
    public boolean setInventory(String adminID, String adminPSW, DrinkTO[] drinks) {
        inventory = drinks;
        return true;
    }

    @Override
    public boolean setUser(String adminID, String adminPSW, PersonTO user) {
        if(users == null){
            users = new PersonTO[1];
            users[0] = user;
        }
        else{
            PersonTO[] temp = new PersonTO[users.length + 1];
            System.arraycopy(users, 0 , temp, 0, users.length);
            temp[users.length] = user;
            users = temp;
        }
        return true;
    }

    @Override
    //setzt den status eines Nutzers auf inaktiv
    public boolean setUserState(String userID) {
        for(int i = 0; i < users.length; ++i){
            if(users[i].id.contentEquals(userID)){
                users[i] = new PersonTO(users[i].id,
                                        users[i].cellerId,
                                        users[i].firstname,
                                        users[i].surename,
                                        !(users[i].state),
                                        users[i].key,
                                        users[i].admin,
                                        users[i].balance,
                                        users[i].password,
                                        users[i].eMail,
                                        users[i].requestState,
                                        users[i].wish);
            }
        }
        return false;
    }

    @Override
    //gibt das keller elemt zurück
    public CellarTO getCellar(String adminID, String adminPSW) {
        return cellar;
    }

    @Override
    //generiert eine neue Bestellung
    public DocTO createDelivery(String adminID, String adminPSW) {
        StringBuilder name = new StringBuilder("Bestellung ");
        name.append(LocalDate.now());
        setDoc(adminID,adminPSW,name.toString());

        return getDoc(adminID,adminPSW,name.toString());
    }

    @Override
    //gibt ein Dokument zurück
    public DocTO getDoc(String adminID, String adminPSW, String docName) {
        for(DocTO dokument : docks){
            if(dokument.name.contentEquals(docName)){
                return dokument;
            }
        }
        return null;
    }

    @Override
    //gibt die dokumente zurück
    public DocTO[] getDocs(String adminID, String adminPSW) {
        return docks;
    }

    @Override
    //gibt das keller inventar zurück
    public DrinkTO[] getInventory(int cellarID) {
        return inventory;
    }

    @Override
    //gibt die Lieferanten Liste zurück
    public DrinkTO[] getProviderList() {
        return providerList;
    }

    @Override
    //Gibt alle Bestellungen des Benutzers zurück
    public OrderTO[] getOrder(String userID) {
        OrderTO[] userOrder = null;
        for(OrderTO order : orders){        //für alle Bestellungen
            if(order.personID.contentEquals(userID)){   //wenn benutzer passt
                if(userOrder == null){      //für die erste gefundene
                    userOrder = new OrderTO[1];
                    userOrder[0] = order;
                }
                else{                       //für alle weiteren Bestellungen
                    OrderTO[] temp = new OrderTO[userOrder.length + 1];
                    System.arraycopy(userOrder,0,temp,0,userOrder.length);
                    temp[userOrder.length] = order;
                    userOrder = temp;
                }
            }
        }
        return userOrder;
    }

    @Override
    //gibt bestellungen des nutzers zurück
    public OrderTO[] getOrder(String adminID, String adminPSW, String cellarUserID) {
        OrderTO[] userOrder = null;
        for(OrderTO order : orders){                //für alle Bestellungen
            if(order.personID.contentEquals(cellarUserID)){     //wenn benutzer passt
                if(userOrder == null){              //für die erste gefundene
                    userOrder = new OrderTO[1];
                    userOrder[0] = order;
                }
                else{                               //für alle weiteren Bestellungen
                    OrderTO[] temp = new OrderTO[userOrder.length + 1];
                    System.arraycopy(userOrder,0,temp,0,userOrder.length);
                    temp[userOrder.length] = order;
                    userOrder = temp;
                }
            }
        }
        return userOrder;
    }

    @Override
    //gibt benutzerobjekt zurück
    public PersonTO getUser(String userID) {
        for(PersonTO person : users){
            if(person.id.contentEquals(userID)){
                return person;
            }
        }
        return null;
    }

    @Override
    //Gibt die aktuellen Benutzer zurück
    public PersonTO[] getUsers(String adminID, String adminPSW) {
        return users;
    }

    @Override
    //Login versuch des nutzers
    public Servercommunication.ReturnLogin login(String userID, String userPSW) {
        for(PersonTO person : users){
            if(person.id.contentEquals(userID)){    //sucht nutzer
                if(person.password.contentEquals(userPSW)){
                    if(person.password.contentEquals("1234567AbC!!")) { //erstanmeldung std psw
                        return Servercommunication.ReturnLogin.CHANGE_PASSWORD;
                    }
                    if(person.admin){   //Erfolgreich admin
                        return Servercommunication.ReturnLogin.LOGIN_SUCCESSFUL_ADMIN;
                    }
                    else{   //Erfolgreich benutzer
                        return Servercommunication.ReturnLogin.LOGIN_SUCCESSFUL_USER;
                    }
                }
                else{   //falls Passwort falsch
                    return Servercommunication.ReturnLogin.LOGIN_FAIL;
                }
            }
        }
        return Servercommunication.ReturnLogin.ERROR;   //wenn benutzer nicht gefunden
    }

    @Override
    public boolean setPassword(String userID, String userPSW) {
        return false;
    }

    @Override
    public boolean initConection(String serverIP) {
        return false;
    }

    //Dummy klassen
    private int getAdminAmount(){
        int amount = 0;
        for(PersonTO person : users){
            if(person.admin) {
                ++amount;
            }
        }
        return amount;
    }

    private boolean reduceDrink(DrinkTO drink,int amount){
        DrinkTO temp = null;
        for (int i = 0; i < inventory.length;++i){
            if(inventory[i].name.contentEquals(drink.name)){
                temp = new DrinkTO(inventory[i].name,inventory[i].bottleType,inventory[i].packType,inventory[i].price,inventory[i].setvalue,inventory[i].actualvalue - amount);
                DrinkTO list[] = new DrinkTO[inventory.length];
                System.arraycopy(inventory,0,list,0, i);
                list[i] = temp;
                System.arraycopy(inventory,i+1,list,i+1,inventory.length - i - 1);
                inventory = list;
                return true;
            }
        }

        return false;
    }
}
