package gKVServer.database;

import data.DrinkTO;
import data.PersonTO;
import data.OrderTO;
import data.BillTO;
import java.time.LocalDateTime;

/**
 * Autor: Richard Moeckel
 * Änderungen: Lukas Brandes
 */
public interface UserDB {
    /**
     * @param person Personenidentifikationsnummer
     * @return true bei erfolgreichem Abschluss, false bei fehler
     * @Aufgabe erstellt einen neuen Nutzer
     */
    boolean createUser(PersonTO person);

    /**
     * @param personID Personenidentifikationsnummer
     * @return Personentrransportobjekt
     * @Aufgabe gibt Personentrransportobjekt der jeweiligen Person zurueck
     */
    PersonTO getUser(String personID);

    /**
     * @param personTO Personentrransportobjekt
     * @return true bei erfolgreichem Abschluss, false bei fehler
     * @Aufgabe ueberschreibt einen Nutzer
     */
    boolean setUser(PersonTO personTO);

    /**
     * @param order Bestellungstransportobjekt
     * @return true bei erfolgreichem Abschluss, false bei fehler
     * @Aufgabe speichert eine Bestellung
     */
    boolean createOrder(OrderTO order);

    /**
     * @param personID       Personenidentifikationsnummer
     * @param timestampstart Anfang des Zeitraumes
     * @param timstampstop   Ende des Zeitraumes
     * @return Array aus Bestellungstransportobjekten
     * @Aufgabe gibt alle Buchungen aus entsprechendem Zeitraum zurueck
     */
    OrderTO[] getOrder(String personID, LocalDateTime timestampstart, LocalDateTime timstampstop);

    /**
     * @param bill Rechnungstransportobjekt
     * @return true bei erfolgreichem Abschluss, false bei fehler
     * @Aufgabe speichert eine Rechnung
     */
    boolean createbill(BillTO bill);

    /**
     *
     * @param personID Personenidentifikationsnummer
     * @return Ordertransportobjekt array
     * @Aufgabe gibt alle Oders der Person die noch nicht mit einer Rechung verknüfpt sind zurück
     */
    OrderTO[] getOrdersnoBill(String personID);

    /**
     *
     * @param personID Personenidentifikationsnummer
     * @param timstamp Zeitstempel
     * @return Rechnungssportobjekt
     * @Aufgabe gibt Rechungen des entsprechend Personid und timestamps zurück
     */


    BillTO getBill(String personID, LocalDateTime timstamp);

    /**
     * @param user      Personenidentifikationsnummer
     * @param value     Wert der Buchung
     * @param purpose   Verwendungszweck
     * @param timestamp Zeitstempel
     * @return true bei erfolgreichem Abschluss, false bei fehler
     * @Aufgabe erstellt eine Buchung
     */
    boolean createOrder(String user, int value, String purpose, LocalDateTime timestamp);

    /**
     * @param user       Personenidentifikationsnummer
     * @param drink      name des Getraenkes
     * @param quantity   Menge
     * @param bottletype flaschentyp
     * @param timestamp  Zeitstempel
     * @return true bei erfolgreichem Abschluss, false bei fehler
     * @Aufgabe erstellt die Buchung eines Getraenkes
     */
    boolean createOrder(String user, String drink, int quantity, String bottletype, LocalDateTime timestamp);


    /**
     * @param userID Personenidentifikationsnummer
     * @return true bei erfolgreichem Abschluss, false bei fehler
     * @Aufgabe loescht eine Person
     */
    boolean deltePerson(String userID);


    /**
     * @param user      Personenidentifikationsnummer
     * @param timeStamp Zeitstempel
     * @return Bestellungstransportobjekt als array
     * @Aufgabe gibt die Orders ab dem Zeitstempel zurueck
     */
    OrderTO[] getOrder(String user, LocalDateTime timeStamp);

    /**
     * @param cellarID Kelleridentifikationsnummer
     * @return Personentransportobjekt als Array
     * @Aufgabe gibt alle dem Keller zugehoerige Personen zurueck
     */
    PersonTO[] getUsers(int cellarID);

    /**
     * @Aufgabe gibt die Wunschliste zurück
     * @param cellerID Kelleridentifikationsnummer
     * @return Getraenke als Array
     * @Änderung von CellarDB zu UserDB
     */
    DrinkTO[] getWishlist (int cellerID);

    /**
     * @Aufgabe Speichert bzw ersetzt ein Getraenk
     * @param drink Transportobjekt für Getroenk
     * @return true bei erfolgreichem Abschluss, false bei fehler
     * @Änderung int cellarID
     */
    boolean setDrinkUDB(int cellarID, DrinkTO drink);

    /**
     * @Aufgabe gibt das jeweilige Objekt des geforderten Getränkes zurück
     * @param name Name des Getränkes
     * @param size Flaschengröße
     * @return
     */

    DrinkTO getDrinkDBU(String name, String size, int cellarID);

    /**
     * @Aufgabe Überschreibt die Wunschliste mit einer neuen
     * @param cellerID Kelleridentifikationsnummer
     * @param wishlist wunschliste als Array von Getraenken
     * @return true bei erfolgreichem Abschluss, false bei fehler
     * @Änderung von CellarDB zu UserDB
     */
    boolean setWishlist (int cellerID, DrinkTO[] wishlist);
}