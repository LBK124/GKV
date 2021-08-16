package gKVServer.database;
import data.*;
import data.CellarTO;
//Autor Richard Moeckel
import java.awt.dnd.DropTarget;
/**
 * Autor: Richard Moeckel
 * Änderungen: Lukas Brandes
 */
public interface CellarDB {

    /**
     * @Aufgabe: laedt den aktuellen Hauskassenwert von der Datenbank
     * @param cellerID Kelleridentifikationsnummer
     * @return stand der Hauskasse
     */
    int getCellerCash (int cellerID);

    /**
     * @Aufgabe: setzt den Hauskassenstand auf einen neuen Wert
     * @param cellerID Kelleridentifikationsnummer
     * @param value neuer Hauskassenwert
     * @return True bei erfolgreichem Abschluss, false bei fehler
     */
    boolean setCellerCash (int cellerID, int value);


    /**
     * @Aufgabe gibt alle dem Keller zugehoerigen Dokumente zurück
     * @param cellerID Kelleridentifikationsnummer
     * @return alle Dokumente als Array
     */
    DocTO[] getDocList (int cellerID);

    /**
     * @Aufgabe Speichern von einem Dokument
     * @param cellerID Kelleridentifikationsnummer
     * @param doc Dateipfad von Dokument
     * @return True bei erfolgreichem Abschluss, false bei fehler
     * @Änderung String -> int bei cellarID
     */
    boolean setDoc (int cellerID, String doc);

    /**
     * @Aufgabe gibt den Status des Kellers (Aktiv oder Inkativ) zurück
     * @param cellerID Kelleridentifikationsnummer
     * @return True bei geoeffntem Keller false bei geschlossenem
     */
    boolean getState (int cellerID);

    /**
     * @Aufgabe setzt den Status des Kellers
     * @param cellerID Kelleridentifikationsnummer
     * @param state true bei Aktiv, false bei intaktiv
     * @return True bei erfolgreichem Abschluss, false bei fehler
     */
    boolean setState (int cellerID, boolean state);

    /**
     * @Aufgabe gibt den aktuellen Hausbeitrag zurück
     * @param cellerID Kelleridentifikationsnummer
     * @return aktueller Hausbeitrag
     */
    int getTribute (int cellerID);

    /**
     * @Aufgabe setzt den neuen Hausbeitrag
     * @param cellerID Kelleridentifikationsnummer
     * @param tribute neuer Hausbeitrag
     * @return true bei erfolgreichem Abschluss, false bei fehler
     */
    boolean setTribute (int cellerID, int tribute);



    /**
     * @Aufgabe gibt das jeweilige Objekt des geforderten Getränkes zurück
     * @param name Name des Getränkes
     * @param size Flaschengröße
     * @return
     */

    DrinkTO getDrink(String name, String size, int cellarID);
    /**
     * @Aufgabe gibt das gesamte Inverntar des Kellers zurück
     * @param cellerID Kelleridentifikationsnummer
     * @return Getraenke als Array
     */
    DrinkTO[] getInventory (int cellerID);

    /**
     * @Aufgabe gibt alle Rechnungen die mit dem Nutzer verknüpft sind zurück
     * @param userID Nutzeridentifikationsnummer
     * @return true bei erfolgreichem Abschluss, false bei fehler
     */
    BillTO[] getBill(String userID);

    /**
     * @Aufgabe fügt neues Getraenk der Datenbank zu
     * @param drink Transportobjekt für Getraenk
     * @return  true bei erfolgreichem Abschluss, false bei fehler
     */
    public boolean createDrink(DrinkTO drink, int cellarID);

    /**
     * @Aufgabe loeschen eines Dokuments
     * @param doc Name des Dokuments
     * @return true bei erfolgreichem Abschluss, false bei fehler
     */
    boolean deleteDoc(String doc);


    /**
     * @Aufgabe Speichert bzw ersetzt ein Getraenk
     * @param drink Transportobjekt für Getroenk
     * @return true bei erfolgreichem Abschluss, false bei fehler
     * @Änderung int cellarID
     */
    boolean setDrink(int cellarID, DrinkTO drink);

    /**
     * @Aufgabe Speichert bzw ersetzt alle  Getraenke im Inventar
     * @param drinks Getraenke als Array
     * @param cellarID Kelleridentifikationsnummer
     * @return true bei erfolgreichem Abschluss, false bei fehler
     * @Änderung String -> int bei cellarID
     */

    boolean setInventory(int cellarID, DrinkTO[] drinks);

    /**
     * @Aufgabe gibt dioe gesammten Kellerdaten zurück
     * @param cellarID Kelleridentifikationsnummer
     * @return Kellertransportobjekt
     * @Änderung String -> int bei cellarID
     */

    CellarTO getCellar(int cellarID);

    /**
     * @Aufgabe Gibt das entspredchende Dokument zurück
     * @param cellarID Kelleridentifikationsnummer
     * @param name Name des Dokuments
     * @return Dokumententransportobjekt
     * @Änderung String -> int bei cellarID
     */
    DocTO getDoc(int cellarID, String name);


    /**
     * @Aufgabe gibt die Getraenkeliste vom Lieferanten zurück
     * @return Getroenke als Array
     */
    DrinkTO[] getProviderList();

    /**
     * @Aufgabe Gibt alle Bestellungen des Kellers zurück
     * @param cellarUser Kelleridentifikationsnummer
     * @return Bestellungstransportobjekt als Array
     */
    OrderTO[] getOrder(String cellarUser);
}
