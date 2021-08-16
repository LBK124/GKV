package gKVServer.Servercommunication;

import data.*;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * @author Oliver Neuhaeusler
 */

public interface Servercommunication extends Remote{

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, wenn Bestellung abgeschickt werden soll.
     * @param state Status
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean sendAcknowledge (boolean state) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit  Dokument geloescht wird.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param docName Name des Dokument
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean deleteDoc(String adminID, String adminPSW, String docName) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit  Dokument hochgeladen wird.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param docName Name des Dokument
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean setDoc(String adminID, String adminPSW, String docName) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Usermanagement um Daten weiterzuleiten, damit Buchung durchgefuehrt wird.
     * @param userID Identifikator des Nutzer
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param value Betrag der Buchung
     * @param usage Vewendungszweck der Buchung
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean createOrderAdmin(String userID, String adminID, String adminPSW, int value, String usage) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Usermanagement um Daten weiterzuleiten, damit Buchung der Getraenkeentnahme durchgefuehrt wird.
     * @param userID Identifikator des User
     * @param userPSW Passwort des Administrator
     * @param drinkName Name des Getraenk
     * @param amount Anzahl der zu entnehmenden Getraenke
     * @param bottleType Flaschentyp
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean createOrderUser(String userID, String userPSW, String drinkName, int amount, String bottleType) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Usermanagement um Daten weiterzuleiten, damit Informationen an das Haus versendet werden kann.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param adminRZID Rechenzentrum Identifikator des Administrator
     * @param adminRZPSW Rechenzentrum Passwort des Administrator
     * @param message zu versendede Nachricht
     * @return true: Daten erfolgreich uebermittelt
               false: Fehler bei Uebermittlung
     */
    boolean sendInfo (String adminID, String adminPSW, String adminRZID, String adminRZPSW, String message) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Usermanagement um Daten weiterzuleiten, damit Bestand angepasst werden kann.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param drinks Transportobjekt Getraenke
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean setInventory(String adminID, String adminPSW, DrinkTO[] drinks) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Usermanagement um Daten weiterzuleiten, damit neues Getraenk zum Keller hinzugefuegt wird.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param drinkName Name des Getraenk
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean createDrink(String adminID, String adminPSW, DrinkTO drinkName) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Usermanagement um Daten weiterzuleiten, damit Getraenkewunsch verschickt werden kann.
     * @param userID Identifikator des Nutzer
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean creatWish(DrinkTO[] drinks ,String userID) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Usermanagement um Daten weiterzuleiten, damit der Administrator das Passwort des Nutzer zuruecksenden kann.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param userID Identifikator des Nutzer
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean resetPassword(String adminID, String adminPSW, String userID) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Usermanagement um Daten weiterzuleiten, damit ein neuer Benutzer angelegt werden kann.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param person Transportobjekt Person
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean createUser(String adminID, String adminPSW, PersonTO person) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Usermanagement um Daten weiterzuleiten, damit ein Benutzer angelegt bearbeitet werden kann.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param person Transportobjekt Person
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean setUser(String adminID, String adminPSW, PersonTO person) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit ein Getraenk bearbeitet werden kann.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param drink Transportobjekt drink
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean setDrink(String adminID, String adminPSW, DrinkTO drink) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit eine Rechnung generiert werden kann.
     * @param adminRZID Rechenzentrum Identifikator des Administrator
     * @param adminRZPSW Rechenzentrum Passwort des Administrator
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean sendBill (String adminRZID, String adminRZPSW, String adminID, String adminPSW) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit der Nutzer einen Status anfragen kann.
     * @param userID Identifikator des Nutzer
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean setUserState(String userID) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Usermanagement um Daten weiterzuleiten, damit eine Person geloescht werden kann.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param userID Identifikator des Nutzer
     * @return true: Daten erfolgreich uebermittelt
     *         false: Fehler bei Uebermittlung
     */
    boolean deletePerson(String adminID, String adminPSW, String userID) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit der Keller bearbeitet werden kann.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @return Gibt ein Transportobjekt vom Typ CellarTO zurueck, dass die Kellerdaten anzeigt.
     */
    CellarTO getCellar(String adminID, String adminPSW) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit eine Bestellung generiert werden kann.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @return Gibt ein Transportobjekt vom Typ DocTo zurueck, dass die generierte Bestellung anzeigt.
     */
    DocTO createDelivery (String adminID, String adminPSW) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit ein Dokument gedownloaded werden kann.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param docName Name des Dokument
     * @return Gibt ein Transportobjekt vom Typ DocTo mit dem jeweiligen Dokument zurueck.
     */
    DocTO getDoc(String adminID, String adminPSW, String docName) throws RemoteException;


    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit ein Dokument gedownloaded werden kann.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @return Gibt ein Transportobjekt vom Typ DocTo mit dem jeweiligen Dokument zurueck.
     */
    DocTO[] getDocs(String adminID, String adminPSW) throws RemoteException;


    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit Getraenkeliste des Lieferanten angzeigt werden kann.
     * @return Gibt ein Feld von Transportobjekten DrinkTO zurueck in dem die Getraenkeliste des Lieferanten enthalten ist.
     */
    DrinkTO[] getProviderList() throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit eine Bestellung generiert werden kann.
     * @param cellarID KellerID
     * @return Gibt ein Feld von Transportobjekten DrinkTO zurueck in dem alle vorhandenen Getraenkedaten enthalten sind.
     */
    DrinkTO[] getInventory(int cellarID) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientcommunication und Cellarmanagement um Daten weiterzuleiten, damit Kellerbuchungen angezeigt werden können.
     * @param adminID Identifikator des Administrator
     * @param adminPSW Passwort des Administrator
     * @param cellarUserID KellerID
     * @return Gibt ein Feld von Transportobjekten OrderTO zurueck in dem alle Kellerbuchungen gespeichert sind.
     */
    OrderTO[] getOrderAdmin(String adminID, String adminPSW, String cellarUserID) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientkommunikation und Usermanagement um Daten weiterzuleiten,
     *           damit der Benutzer seinen eigenen Kontostand ansehen kann.
     * @param userID BenutzerID des Benutzers der geladen werden soll
     * @return  Gibt ein Feld von Benutzertransportobjekten zurueck bei einem Fehler wird NULL zurueck gegeben.
     */
    OrderTO[] getOrderUser(String userID) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientkommunikation und Usermanagement um Daten weiterzuleiten,
     *           damit das Benutzerprofil des angemeldeten Benutzers geladen werden kann.
     * @param userID ID des Benutzers dessen Profil geladen werden soll
     * @return  Gibt ein Transportobjekt vom Typ PersonTO zurueck in dem alle Daten des Benutzers gespeichert sind.
     */
    PersonTO getUser(String userID) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientkommunikation und Usermanagement um Daten weiterzuleiten,
     *           damit die Benutzerliste des Kellers geladen werden kann.
     * @param adminID ID des Administrators
     * @param adminPSW Passwort des Administrator
     * @return  Gibt ein Feld von Benutzertransportobjekten zurueck die alle Benutzer des Kellers enthalten
     *          bei einem Fehler wird NULL zurueckgegeben.
     */
    PersonTO[] getUsers(String adminID, String adminPSW) throws RemoteException;

    /**
     * @Aufgabe: Agiert zwischen Clientkommunikation und Usermanagement um Daten weiterzuleiten,
     *           damit sich ein Benutzer einloggen kann
     * @param userID ID des Benutzers
     * @param userPSW Passwort des Benutzers
     * @return  Gibt ein Transportobjekt vom Typ ReturnLoginTO zurueck, dass anzeigt ob der Login erfolgreich war
     *          oder eine Passwortaenderung noetig ist
     *          bei einem Fehler wird NULL zurueckgegeben.
     */
    ReturnLogin login(String userID, String userPSW) throws RemoteException;

    /**
     * Änderung von Transportobjekt zu Enum von Brandes Lukas
     */
    enum ReturnLogin{
        LOGIN_SUCCESSFUL_USER,
        LOGIN_SUCCESSFUL_ADMIN,
        LOGIN_FAIL,
        CHANGE_PASSWORD,
        ERROR,
    }

}
