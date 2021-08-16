package gKVClient.Clientcommunikation;

import data.*;
import gKVServer.Servercommunication.Servercommunication;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Autor: Florian Hoerkner
 */
public interface Clientcommunikation extends Remote {
    /**
     * @Aufgabe: gibt die Daten fuer ein neues Getraenk ueber den TCP-Stack an den Server weiter
     * @param adminID   AdminID fuer Berechtigungsabfrage
     * @param adminPSW  Admin Passwort fuer Berechtigungsabfrage
     * @param drink Transportobjekt fuer ein Getraenk
     * @return gibt zurueck ob das Getraenk erfolgreich angelegt wurde
     */
    boolean createDrink(String adminID,String adminPSW,DrinkTO drink);

    /**
     * Aufgabe: Gibt die noetigen Daten zum erstellen einer Buchung auf ein anderes Benutzerkonto an den Server weiter
     * @param cellarUserID ID des zu bearbeitenden Benutzers (Kellerbenutzer)
     * @param adminID AdminID fuer Berechtigungsabfrage
     * @param adminPSW  Admin Passwort fuer Berechtigungsabfrage
     * @param value Wert der Buchung (positiv oder negativ)
     * @param usage Verwendungszweck
     * @return gibt zurueck ob die buchung erfolgreich war
     */
    boolean createOrderAdmin(String cellarUserID,String adminID,String adminPSW,int value,String usage);

    /**
     * Aufgabe: Gibt die noetigen Daten zum durchfuehren einer Buchung an den Server weiter
     * @param userID        ID des Beutzers
     * @param userPSW       Passwort des Benutzers fuer Berechtigungsabfrage
     * @param drinkName     Name des Getraenks
     * @param amount        anzahl der Flaschen
     * @param bottleType    FlaschenTyp des Getraenks falls name nicht eindeutig
     * @return              gibt zurueck ob Buchung erfolgreich war
     */
    boolean createOrderUser(String userID,String userPSW,String drinkName,int amount,String bottleType);

    /**
     * Aufgabe: Gibt die Daten zum anlegen einens neuen Benutzers an den Server weiter
     * @param adminID   ID des Admins fuer Berechtigungsabfrage
     * @param adminPSW  Passwort des Admins fuer Berechtigungsabfrage
     * @param user      Transportobjekt fuer neuen Benutzer enthaelt alle noetigen Daten
     * @return          Gibt zurueck ob das anlegen erfolgreich war
     */
    boolean createUser(String adminID,String adminPSW,PersonTO user);

    /**
     * Aufgabe: Gibt die noetigen Daten fuer das Hinzufuegen eines neuen Getraenkes auf die Wunschliste an den Server weiter
     * @param userID        BenutzerID um wunsch zuordnen koennen
     * @return              Gibt zurueck ob das hinzufuegen des neuen Wunschgetraenks erfolgreich war
     */
    boolean creatWish(DrinkTO[] drinks ,String userID);

    /**
     * Aufgabe: gibt die Daten zum Loeschen eines Vorhandenen Dokuments an den Server weiter
     * @param adminID       ID des Admins zur Berechtigungsabfrage
     * @param adminPSW      Passwort des Admins zur Berechtigungsabfrage
     * @param docName       Name des zu loeschenden Dokuments
     * @return              Gibt zurueck ob das Loeschen erfolgreich war
     */
    boolean deleteDoc(String adminID,String adminPSW,String docName);

    /**
     * Aufgabe: Gibt die Daten zum Loeschen einer Person an den Server weiter
     * @param adminID       ID des Admins zur Berechtigungsabfrage
     * @param adminPSW      Passwort des Admins zur Berechtigungsabfrage
     * @param userID        die ID des zu Loeschenden Benutzers
     * @return              Gibt zurueck ob das Loeschen erfolgreich war
     */
    boolean deletePerson(String adminID,String adminPSW,String userID);

    /**
     * Aufgabe: Gibt die Daten zum ruecksetzen des Passwortes eines Benutzers an den Server weiter
     * @param adminID       ID des Admins zur Berechtigungsabfrage
     * @param adminPSW      Passwort des Admins zur Berechtigungsabfrage
     * @param userID        Die ID des Benutzers dessen Passwort zurueckgesetzt werden soll
     * @return              Gibt zurueck ob das Passwort erfolgreich zurueckgesetzt wurde
     */
    boolean resetPassword(String adminID,String adminPSW,String userID);

    /**
     * Aufgabe: Sendet eine Bestaetigung an den Server um auf die ueberpruefung des Benutzers zu reagieren
     * @param acknowledge   Bestaetigung ob generierte Daten richtig angezeigt wurden
     * @return              Gibt zurueck ob die Bestaetigung erfolgreich beim Server ankam
     */
    boolean sendAcknowledge (boolean acknowledge);

    /**
     * Aufgabe: Gibt die Daten zum erstellen und Versenden einer neuen Rechnug an den Server weiter
     * @param adminRZID     Loginname des Administrators fuer den RZemail Server
     * @param adminRZPSW    Passwort des Administrators fuer den RZemail Server
     * @param adminID       ID des Admins zur Berechtigungsabfrage
     * @param adminPSW      Passwort des Admins zur Berechtigungsabfrage
     * @return              gibt zurueck ob die Rechungen erfolgreich generiet und versendet werden konnten
     */
    boolean sendBill (String adminRZID, String adminRZPSW,String adminID,String adminPSW);

    /**
     * Aufgabe: Gibt die Daten zum Versenden von Nachrichten an den Emailverteiler an den Server weiter
     * @param adminID       ID des Admins zur Berechtigungsabfrage
     * @param adminPSW      Passwort des Admins zur Berechtigungsabfrage
     * @param adminRZID     Loginname des Administrators fuer den RZemail Server
     * @param adminRZPSW    Passwort des Administrators fuer den RZemail Server
     * @param message       Nachricht die an die Benutzer versendet werden soll
     * @return              gibt zurueck ob die Infos erfolgreich versendet werden konnte
     */
    boolean sendInfo (String adminID, String adminPSW, String adminRZID, String adminRZPSW,String message) ;

    /**
     * Aufgabe: Gibt die Daten zum anlegen eines neuen Dokuments in die Dokumenten DB an den Server weiter
     * @param adminID    ID des Admins zur Berechtigungsabfrage
     * @param adminPSW   Passwort des Admins zur Berechtigungsabfrage
     * @param docName    Name des Dokuments das Hochgeladen werden soll(mit Dateipfad)
     * @return           gibt zurueck ob das Dokument erfolgreich hochgeladen wurde
     */
    boolean setDoc(String adminID, String adminPSW, String docName);

    /**
     * Aufgabe: Gibt die Daten zum bearbeiten eines Getraenks an den Server weiter
     * @param adminID   ID des Admins zur Berechtigungsabfrage
     * @param adminPSW  Passwort des Admins zur Berechtigungsabfrage
     * @param drink     das Transportobjekt fuer das bearbeitete Getraenk
     * @return          gibt zurueck ob das Getraenk erfolgreich bearbeitet werden konnte
     */
    boolean setDrink(String adminID, String adminPSW, DrinkTO drink);

    /**
     * Aufgabe: Gibt die Daten zum bearbeiten des aktuellen Kellerbestandes an den Server weiter
     * @param adminID   ID des Admins zur Berechtigungsabfrage
     * @param adminPSW  Passwort des Admins zur Berechtigungsabfrage
     * @param drinks    Getraenkefeld mit allen Getraenken und den bearbeiteten Werten
     * @return          Gibt zurueck ob die neue Inventarliste des Kellers uebernommen werden konnte
     */
    boolean setInventory(String adminID, String adminPSW, DrinkTO[] drinks);

    /**
     * Aufgabe: Gibt die Daten zum bearbeiten eines bereits angelegten Benutzers an den Server weiter
     * @param adminID   ID des Admins zur Berechtigungsabfrage
     * @param adminPSW  Passwort des Admins zur Berechtigungsabfrage
     * @param user      Benutzertransportobjekt mit den bearbeitten werten
     * @return          gibt zurueck ob der bearbeitete Benutzer erfolgreich in die Datenbank uebernommen wurde
     */
    boolean setUser(String adminID, String adminPSW, PersonTO user);

    /**
     * Aufgabe: Gibt die Daten zum beantragen des Benutzerstatuses auf inaktiv an den Server weiter
     * @param userID    BenutzerID dessen Account auf inaktiv gesetzt werden soll
     * @return          Gibt zurueck ob der Antrag erfolgreich beim Server ankam
     */
    boolean setUserState(String userID);

    /**
     * Aufgabe: Gibt die Daten zum laden des Kellers an den Server weiter
     * @param adminID   ID des Admins zur Berechtigungsabfrage
     * @param adminPSW  Passwort des Admins zur Berechtigungsabfrage
     * @return          Transportobjekt des Kellers in dem alle Kellerdaten gespeichert sind
     *                  bei einem Fehler wird NULL zurueckgegeben
     */
    CellarTO getCellar(String adminID, String adminPSW) ;

    /**
     * Aufgabe: Gibt die Daten zum erstellen einer neuen Bestellung an den Server weiter
     * @param adminID   ID des Admins zur Berechtigungsabfrage
     * @param adminPSW  Passwort des Admins zur Berechtigungsabfrage
     * @return          gibt das neu erzeugte Dokument zurueck
     *                  bei einem Fehler wird NULL zurueckgegeben
     */
    DocTO createDelivery (String adminID, String adminPSW) ;

    /**
     * Aufgabe: Gibt die Daten zum laden eines Dokuments an den Server weiter
     * @param adminID   ID des Admins zur Berechtigungsabfrage
     * @param adminPSW  Passwort des Admins zur Berechtigungsabfrage
     * @param docName   Name des Dokuments das Heruntergeladen werden soll
     * @return          Gibt das Dokumenten Transportobjekt fuer das Passende Dokument zurueck
     *                  bei einem Fehler wird NULL zurueckgegeben
     */
    DocTO getDoc(String adminID, String adminPSW,String docName);

    /**
     * Aufgabe: Gibt die Daten zum laden der gesammten Dokumentenliste des Kellers an den Server weiter
     * @param adminID   ID des Admins zur Berechtigungsabfrage
     * @param adminPSW  Passwort des Admins zur Berechtigungsabfrage
     * @return          Gibt ein Feld aus Dokumententransportobjekten zurueck bei einem Fehler wird NULL zurueckgegeben
     */
    DocTO[] getDocs(String adminID, String adminPSW) ;

    /**
     * Aufgabe: Gibt die Daten zum laden des KellerInventars(Getraenkeliste) an den Server weiter
     * @param cellarID  Die KellerID um das Passende Inventar zu laden
     * @return          Gibt ein Feld aus Getraenketransportobjekten zurueck die das Inventardes Kellers repraesentieren
     *                  bei einem Fehler wird NULL zurueckgegeben
     */
    DrinkTO[] getInventory(int cellarID) ;

    /**
     * Aufgabe: Gibt die Anfrage zum laden der moeglichen Getaenke des Lieferanten fuer die Wunschliste an den Server weiter
     * @return          Gibt ein Feld aus Getraenketransportobjekten zurueck das alle moeglichen Getraenke enthaelt
     *                  bei einem Fehler wird NULL zurueckgegeben
     */
    DrinkTO[] getProviderList() ;

    /**
     * Aufgabe: Gibt die Daten zum laden aller Buchungen eines Benutzers an den Server weiter
     * @param userID    BenutzerID um den Passenden Nutzer aus de Datenbank zu lesen
     * @return          Gibt ein Feld aus Bestellungtransportobjekten zurueck bei einem Fehler wird NULL zurueckgegeben
     */
    OrderTO[] getOrder(String userID) ;

    /**
     * Aufgabe: Gibt die Daten zum anzeigen der Kellerbuchungen an den Server weiter
     * @param adminID       ID des Admins zur Berechtigungsabfrage
     * @param adminPSW      Passwort des Admins zur Berechtigungsabfrage
     * @param cellarUserID  KellerID (Kelleralsbenutzer fuer Buchungen)
     * @return              Gibt ein Feld aus Bestellungtransportobjekten zurueck bei einem Fehler wird NULL zurueckgegeben
     */
    OrderTO[] getOrder(String adminID, String adminPSW,String cellarUserID) ;

    /**
     * Aufgabe: gibt die Daten um einen Benutzerzu laden an den Server weiter
     * @param userID        BenutzerID des zu ladenden Benutzers
     * @return              Gibt ein Benutzertransportobjekt zurueck bei einem Fehler wird NULL zurueckgegeben
     */
    PersonTO getUser(String userID) ;

    /**
     * Aufgabe: gibt die Daten zum laden der gesammten Benutzerliste eines Kellers an den Server weiter
     * @param adminID       ID des Admins zur Berechtigungsabfrage
     * @param adminPSW      Passwort des Admins zur Berechtigungsabfrage
     * @return              gibt ein Feld auf Benutzertransportobjekten zurueck bei einem Fehler wird NULL zurueckgegeben
     */
    PersonTO[] getUsers(String adminID, String adminPSW) ;

    /**
     * Aufgabe: Gibt die Daten fuer einen Login an den Server weiter
     * @param userID    Die ID des Benutzers der sich einloggen moechte
     * @param userPSW   das Benutzerpasswort fuer den Login
     * @return          gibt ein Transportobjekt ReturnLoginTO zurueck in diesem wird gespeichert ob der Login
     *                  erfolgreich war oder ob es sich um eine erstmalige anmeldung handelt (Passwort muss geaendert werden)
     */
    Servercommunication.ReturnLogin login(String userID, String userPSW);

    /**
     * @param userID    Die ID des Benutzers der sein Passwort aendern moechte
     * @param userPSW   Das neue gehashte Passwort des Benutzers
     * @return          gibt zurueck ob das Passwort erfolgreich in der Datenbank geaendert wurde
     */
    boolean setPassword(String userID, String userPSW);

    /**
     *
     * @param serverIP  Die IP des Zielservers
     * @return          ob Verbindung erfolgreich oder nicht
     */
    boolean initConection(String serverIP);

}
