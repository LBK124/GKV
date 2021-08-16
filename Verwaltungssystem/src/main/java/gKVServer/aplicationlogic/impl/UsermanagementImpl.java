package gKVServer.aplicationlogic.impl;

import data.DrinkTO;
import data.OrderTO;
import data.PersonTO;
import gKVServer.Servercommunication.Servercommunication;
import gKVServer.aplicationlogic.Usermanagement;
import gKVServer.database.UserDB;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Autor: Brandes Lukas
 */
public class UsermanagementImpl implements Usermanagement {

    private UserDB userDB = null;
    public void setUserDB(UserDB userDB){this.userDB = userDB;}


    private LocalDateTime timeStampStart = LocalDateTime.of(2020, 10, 13, 15, 56);


    @Override
    public boolean createOrderAdmin(String cellarUserID, String adminID, String adminPSW, int value, String usage) {
        if(userDB == null){
            return false;
        }
        boolean answer = false;
        if(userDB.getUser(adminID).password.equals(adminPSW)){
            if(userDB.getUser(adminID).admin){
                LocalDateTime currentTime = LocalDateTime.now();
                // erstellen einer Buchung für die Dokumentation
                answer = userDB.createOrder(cellarUserID,value,usage,currentTime);

                if(answer) {
                    // Den Buchungsbetrag mit dem Konto des betroffenen Nutzers verrechnen
                    PersonTO user = userDB.getUser(cellarUserID);
                    userDB.setUser(new PersonTO(user.id, user.cellerId, user.firstname, user.surename, user.state,
                            user.key, user.admin, user.balance + value, user.password, user.eMail, user.state, user.wish));
                    // Den Buchungsbetrag mit dem Konto der jeweiligen Hauskasse verrechnen
                    // Eventuell nochmal überarbeiten, schönere Lösung finden
                    if(user.cellerId == 1){
                        PersonTO cellar = userDB.getUser("HK100");
                        userDB.setUser(new PersonTO(cellar.id, cellar.cellerId, cellar.firstname, cellar.surename, cellar.state,
                                cellar.key, cellar.admin, cellar.balance - value, cellar.password, cellar.eMail, cellar.state, cellar.wish));
                    }
                    if(user.cellerId == 2){
                        PersonTO cellar = userDB.getUser("HK200");
                        userDB.setUser(new PersonTO(cellar.id, cellar.cellerId, cellar.firstname, cellar.surename, cellar.state,
                                cellar.key, cellar.admin, cellar.balance - value, cellar.password, cellar.eMail, cellar.state, cellar.wish));
                    }
                    if(user.cellerId == 3){
                        PersonTO cellar = userDB.getUser("HK300");
                        userDB.setUser(new PersonTO(cellar.id, cellar.cellerId, cellar.firstname, cellar.surename, cellar.state,
                                cellar.key, cellar.admin, cellar.balance - value, cellar.password, cellar.eMail, cellar.state, cellar.wish));
                    }
                    if(user.cellerId == 4){
                        PersonTO cellar = userDB.getUser("HK400");
                        userDB.setUser(new PersonTO(cellar.id, cellar.cellerId, cellar.firstname, cellar.surename, cellar.state,
                                cellar.key, cellar.admin, cellar.balance - value, cellar.password, cellar.eMail, cellar.state, cellar.wish));
                    }
                }
            }
        }
        return answer;
    }


    @Override
    public boolean createOrderUser(String userID, String userPSW, String drinkName, int amount, String bottleTpye) {
        if(userDB == null){
            return false;
        }
        // Prüft, ob der Keller offen ist. Eventeuell nochmal anständig überarbeiten.
        int cellarID = getUser(userID).cellerId;
        if(cellarID == 1){
            if(!userDB.getUser("HK100").state){
                return false;
            }
        }
        if(cellarID == 2){
            if(!userDB.getUser("HK200").state){
                return false;
            }
        }
        if(cellarID == 3){
            if(!userDB.getUser("HK300").state){
                return false;
            }
        }
        if(cellarID == 4){
            if(!userDB.getUser("HK400").state){
                return false;
            }
        }

        boolean answer = false;
        if(userDB.getUser(userID).password.equals(userPSW)){
            if(userDB.getUser(userID).state){

                // erstellen einer Buchung der entnommeden Getränke
                LocalDateTime currentTime = LocalDateTime.now();
                answer = userDB.createOrder(userID, drinkName,amount,bottleTpye,currentTime);
                // Entname der Getränke mit dem Inventar vereinen

                DrinkTO drinkDB  = userDB.getDrinkDBU(drinkName,bottleTpye, userDB.getUser(userID).cellerId);
                userDB.setDrinkUDB(userDB.getUser(userID).cellerId,new DrinkTO(drinkDB.name,drinkDB.bottleType,
                                    drinkDB.packType,drinkDB.price,drinkDB.setvalue,drinkDB.actualvalue -amount ));

                // Kontostand des Nutzers anpassen
                PersonTO user = userDB.getUser(userID);
                userDB.setUser(new PersonTO(user.id, user.cellerId, user.firstname, user.surename, user.state,
                        user.key, user.admin, user.balance -(amount * userDB.getDrinkDBU(drinkName,bottleTpye, getUser(userID).cellerId).price),
                        user.password, user.eMail, user.state, user.wish));

            }
        }
        return answer;
    }

    @Override
    public boolean createUser(String adminID, String adminPSW, PersonTO user) {
        if(userDB == null){
            return false;
        }
        boolean answer = false;
        if(userDB.getUser(adminID).password.equals(adminPSW)) {
            if (userDB.getUser(adminID).admin){
                answer = userDB.createUser(new PersonTO(user.id,user.cellerId,user.firstname,user.surename,user.state,
                        user.key,false,0,"&T6T8fqhan!N",user.eMail,false, true));
            }
        }
        return answer;
    }

    @Override
    public boolean creatWish(DrinkTO[] drinks, String userID) {
        if(userDB.getUser(userID).wish) {
            // Alte Wunschliste laden
            DrinkTO[] wishlistOld = userDB.getWishlist(userDB.getUser(userID).cellerId);
            // Wünsche ergänzen
            DrinkTO[] wishlistNew = new DrinkTO[wishlistOld.length+3];
            System.arraycopy(wishlistOld,0,wishlistNew,0,wishlistOld.length);
            wishlistNew[wishlistOld.length ] = drinks[0];
            wishlistNew[wishlistOld.length +1] = drinks[1];
            wishlistNew[wishlistOld.length +2] = drinks[2];
            // Wunschliste speichern
            return userDB.setWishlist(userDB.getUser(userID).cellerId, wishlistNew);
        }
        return false;
    }



    @Override
    public boolean deletePerson(String adminID, String adminPSW, String userID) {

        if(userDB == null){
            return false;
        }
        boolean answer = false;
        PersonTO admin = userDB.getUser(adminID);
        if(admin.password.equals(adminPSW)){
            if(admin.admin){
                PersonTO user = getUser(userID);
                if(user.balance == 0) {
                    answer = userDB.deltePerson(userID);
                }
            }
        }
        return answer;
    }


    @Override
    public boolean resetPassword(String adminID, String adminPSW, String userID) {

        if(userDB == null){
            return false;
        }
        boolean answer = false;

        if(userDB.getUser(adminID).password.equals(adminPSW)){
            if(userDB.getUser(adminID).admin){
                PersonTO user = getUser(userID);
                answer = userDB.setUser(new PersonTO(user.id,user.cellerId,user.firstname,user.surename,user.state,
                        user.key,user.admin,user.balance,"&T6T8fqhan!N",user.eMail,user.requestState, user.wish));
            }
        }
        return answer;
    }

    @Override
    //TODO herausfinden, wie das E-Mail PlugIn funktioniert
    public boolean sendInfo(String adminID, String adminPSW, String adminRZID, String adminRZPSW, String message) {
        System.out.println("Universität der Bundeswehr München\n" +
                "Informationen Geb. 8/300 \n" +
                "Studentenfachbereichs A - \n" +
                "                                       Brandes Lukas, Leutnant \n"+
                "                                       E-Mail: lukas.brandes@unibw.de\n"+
                "Infos:");
        return true;
    }

    @Override
    public boolean setUser(String adminID, String adminPSW, PersonTO user) {

        if(userDB == null){
            return false;
        }
        boolean answer = false;
        if(userDB.getUser(adminID).password.equals(adminPSW)){
            if(userDB.getUser(adminID).admin){
                answer = userDB.setUser(user);
            }
        }
        return answer;
    }

    @Override
    public boolean setUserState(String userID) {

        if(userDB == null){
            return false;
        }
        PersonTO user = userDB.getUser(userID);

       return userDB.setUser(new PersonTO(user.id,user.cellerId,user.firstname,user.surename,user.state,
                user.key,user.admin,user.balance, user.password,user.eMail,true, user.wish));

    }

    @Override
    public OrderTO[] getOrder(String userID) {
        if(userDB == null){
            return null;
        }
        LocalDateTime timeStampStop = LocalDateTime.now();
        return userDB.getOrder(userID,timeStampStart,timeStampStop);
    }

    @Override
    public PersonTO getUser(String userID) {
        if(userDB == null){
            return null;
        }
        return userDB.getUser(userID);
    }

    @Override
    public PersonTO[] getUsers(String adminID, String adminPSW) {
        if (userDB == null) {
            return null;
        }

        if(userDB.getUser(adminID).password.equals(adminPSW)){
          return userDB.getUsers(userDB.getUser(adminID).cellerId);
        }
        return null;
    }

    @Override
    public Servercommunication.ReturnLogin login(String userID, String userPSW) {
        if(userDB == null){
            return null;
        }
        String defaultPassword = "&T6T8fqhan!N";
        if(userDB.getUser(userID).password.equals(userPSW)){
            if(userDB.getUser(userID).password.equals(defaultPassword)){
                return Servercommunication.ReturnLogin.CHANGE_PASSWORD;
            }
            if(userDB.getUser(userID).admin)
                return Servercommunication.ReturnLogin.LOGIN_SUCCESSFUL_ADMIN;
            return Servercommunication.ReturnLogin.LOGIN_SUCCESSFUL_USER;
        }
        return null;
    }
}
