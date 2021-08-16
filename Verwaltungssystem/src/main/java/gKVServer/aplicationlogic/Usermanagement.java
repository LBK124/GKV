package gKVServer.aplicationlogic;

import data.*;
import gKVServer.Servercommunication.Servercommunication;
import gKVServer.database.UserDB;

/**
 * @Autor Brandes Lukas
 */
public interface Usermanagement {
    /**
     * @Anwendungsfall Kotostand änder
     * @Aufgabe Diese Funktion wird von dem Administrator genutzt, um eine Kontostandänderung eines Nutzers vorzunehmen.
     *          Der Funktion wird die NutzerID des betrofenden Nutzers, der Betrag, ein Verwendungszweck sowie die Admin
     *          Daten übergeben. Zu begin werden die Personen Daten des Admin von der Datenbank angefragt um diese dann
     *          mit den übergebenen Daten zu vergleichen. Wenn nachgewiesen werden konnte, das es sich um einen Admin
     *          handel, werden die Buchungsdaten und ein Zeitstempel an die Datenbank übertragen um eine neue Buchung
     *          anzulegen.
     * @param cellarUserID
     * @param adminID       NutzerID des Administrators
     * @param adminPSW      Passwort des Administrators
     * @param value         Buchungsbetrag
     * @param usage         Verwendungszweck
     * @return  "True" wenn die Buchung auf der Datenbank erfolgreich gespeichert werden konnte, "False" in allen
     *          Fehlerfällen
     */
    boolean createOrderAdmin(String cellarUserID, String adminID, String adminPSW,int value,String usage);

    /**
     * @Anwendungsfall Getränk buchen
     * @Aufgabe Diese Funktion wird von einem Nutzer genutzt, um eine Buchung eines Getränks vorzunehmen. Es werden die
     *          Personen Daten des jeweiligen Nutzers von der Datenbank angefragt. Nachdem ermittelt wurde, ob der Nutzer
     *          berechtigt ist, eine Buchung vorzunehmen, werden die Buchungsdaten mit einem Zeitstempel versehen und
     *          an die Datenbank übergeben.
     * @param userID        NutzerID des Nutzers
     * @param userPSW       Passwort des Nutzers
     * @param drinkName     Name des Getränks
     * @param amount        Anzahl der entnommenen Getränke
     * @param bottleTpye    der Flaschentyp
     * @return "True" wenn die Buchung erfolgreich auf der Datenbank gespeichert werden konnte, "False" in allen
     *          Fehlerfällen
     */
    boolean createOrderUser(String userID,String userPSW,String drinkName,int amount,String bottleTpye);

    /**
     * @Anwendungsfall Neue Person anlegen
     * @Aufgabe Diese Funktion wird von dem Administrator benutzt, um einen neuen Nutzer anzulegen. Zu begin werden die
     *          Personen Daten des Admin von der Datenbank angefragt um diese dann mit den übergebenen Daten zu vergleichen.
     *          Wenn dies der Fall ist, wird überprüft ob sämtliche geforderten Daten übergeben wurden und anschliesend
     *          werden diese der Datenbank übergeben
     * @param adminID   NutzerID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @param user      vellständiges PersonenTO welches die Personen Daten des neuen Nutzers enhält
     * @return "True" bei einer erfolgreichen Anlegung der Person auf dem Server, "False" in allen Fehlerfällen
     */
    boolean createUser(String adminID, String adminPSW, PersonTO user);

    /**
     * @Anwendungsfall Neues Getränk vorschlagen
     * @Aufgabe Diese Funktion erweitert die allgemeine Wunschliste um den getätigten Wunsch eines jeden nutzers. Es
     *          werden die Personen Daten des jeweiligen Nutzers von der Datenbank angefragt um auf berechtigung überprüft.
     *          Wenn dies der Fall ist, wird die gesamt Wunschliste von der Datenbank gezogen und um die Wünsche des Nutzers
     *          erweitert. Die neue Wunschliste sowie die abgeänderten Personendaten werden auf der Datenbank gespeichert
     * @param drinks        Transportobjekte der Getränke
     * @param userID        NutzerID des Nutzers
     * @return  "True" bei einer erfolgreichen speicherung des Wunsches, "False" bei allen Fehlerfällen
     */
    boolean creatWish(DrinkTO[] drinks ,String userID);

    /**
     * @Anwendungsfall Person bearbeiten / löschen
     * @Aufgabe Diese Funktion löschte einen existierenden Nutzer. Hierfür werden die Benutzer Daten des Administrators
     *          von der Datenbank geladen und mit den übergebenen verglichen. Wenn diese überein stillen, werden die
     *          Personen Daten des zu löschenden Nutzers geladen. es Wird überprüft, ob dieser Nutzer gelöscht werden kann.
     *          ein Administrator und Personen mit einer offenen Rechnung können nicht gelöscht werden. Wenn die Person
     *          jedoch gelöscht werden kann, wird die PersonenID der zu löschenden Person an die Datenbak weitergegeben.
     * @param adminID   NutzerID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @param userID    NutzerID der zu löschenden Person
     * @return "True" bei erfolgreicher löschung von der Datenbank, "False" bei allen Fehlerfällen
     */
    boolean deletePerson(String adminID,String adminPSW,String userID);

    /**
     * @Anwendungsfall Passwort zurücksetzten / Person bearbeiten
     * @Aufgabe Diese Funktion setzt das Passwort einen Nutzers auf das Standart Passwort zurück. Hierfür werden die
     *          Benutzer Daten des Administrators von der Datenbank geladen und mit den übergebenen verglichen. Wenn
     *          diese überein stillen, wird die betroffene Person aus der Datenbank geladen, das Passwort auf das
     *          Satandartpasswort zurückgesetzt und die Person anschliesend wieder auf der Datenbank gespeichert
     * @param adminID   NutzerID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @param userID    NutzerID der betroffenen Person
     * @return "True" wenn eine erfolgreiche Passwortänderung von der Datenbank zurück kommt, "False" in allen Fehlerfällen
     */
    boolean resetPassword(String adminID,String adminPSW, String userID);

    /**
     * @Anwendungsfall Info versenden
     * @Aufgabe Diese Funktion versendet eine E-Mail an alle Nutzer. Hierfür werden die Benutzer Daten des Administrators
     *          von der Datenbank geladen und mit den übergebenen verglichen. Wenn diese überein stillen, wird das
     *          PersonenTO des Kellers geladen, um den E-Mail verteiler des Hauses zu erhalten. Daraufhin werden die
     *          RZ-Anmeldedaten an das E-Mail Plugin weitergegben. Wenn die anmeldung dort erfolgreich war, wird die E-Mail
     *          mit allen wichtigen informationen gefüllt und versendet.
     * @param adminID       NutzerID des Administrators
     * @param adminPSW      Passwort des Administrators
     * @param adminRZID     RZ NutzerID des Administrators
     * @param adminRZPSW    RZ Passwort des Administrators
     * @param message       zu übertragende Nachricht
     * @return "True" bei erfolgreichem senden der Nachricht, "False" in allen Fehlerfällen
     */
    boolean sendInfo (String adminID,String adminPSW , String adminRZID, String adminRZPSW,String message);

    /**
     * @Anwendungsfall Person bearbeiten
     * @Aufgabe Diese Funktion erlaubt es die Daten einer Person zu bearbeiten. Hierfür werden die Benutzer Daten des
     *          Administrators von der Datenbank geladen und mit den übergebenen verglichen. Wenn diese überein stillen,
     *          werden die Personen Daten des betroffenen Nutzers von der Datenbank geladen. Daraufhin, werden die
     *          geänderten Daten eingetragen und die Person wieder auf der Datenbank gespeichert.
     * @param adminID   NutzerID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @param user      NutzerID der betroffenen Person
     * @return "True" bei erfolgreicher änderung auf der Datenbank, "False" in allen Fehlerfällen
     */
    boolean setUser(String adminID, String adminPSW, PersonTO user);

    /**
     * @Anwendugsfall Status beantragen
     * @Aufgabe Diese Funktion ändert den Anwesenheitsstatus einer Person.
     * @param userID NutzerID der betroffenen Person
     * @return "True" erfolgreiche änderung, "Fase" Fehlerfall
     */
    boolean setUserState(String userID);

    /**
     * @Aufgabe Dieser Funktion liefert alle Buchungen eines Nutzers. Hierfür werden das OrderTO der betroffenen Person
     *          aus der Datenbank geladen und an die Serverkommunikation übergeben.
     * @param userID    NutzerID der betroffenen Person
     * @return alle ausgefüllten OrderTO
     */
    OrderTO[] getOrder(String userID);

    /**
     * @Aufgabe Diese Funktion liefert alle persönlichen Daten einer Person. Hierfür werden das PersonenTo der betroffene
     *          Person aus der Datenbank geladen und an die Serverkommunikation übergeben.
     * @param userID    NutzerID der betroffenen Person
     * @return Ein ausgefülltes PersonenTO
     */
    PersonTO getUser(String userID);

    /**
     * @Aufgabe Diese Funktion liefert alle Personen des Gebäudes zurück.Hierfür werden die Benutzer Daten des Administrators
     *          von der Datenbank geladen und mit den übergebenen verglichen. Wenn diese überein stillen, werden alle
     *          PersonenTo aus der Datenbank geladen und der Serverkommunikation übergeben.
     * @param adminID   NutzerID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @return Alle ausgefüllten PersonenTO des Gebäude
     */
    PersonTO[] getUsers(String adminID,String adminPSW);

    /**
     * @Aufgabe Diese Funktion überprüft den Einlog Vorgang. Es werden die Personen Daten der betroffenen Person von der
     *          Datenbank geladen. Anschliesend wrid das eingegebene Passwort mit dem hinterlegten verglichen. Wenn diese
     *          übereinstimmen wird die Person angemeldet. Wenn das Passwort mit dem Standartpasswort übereinstimmt,
     *          wird der Nutzer aufgeforder dies zu ändern.
     * @param userID    NutzerID der betroffenen Person
     * @param userPSW   Passwort der betroffenen Person
     * @return Bei standartpasswort -> Passwort ändern, bei korrekter Anmeldung -> erfolgreich angemeldet, bei falsher
     *         Anmeldung -> falsche Anmeldung.
     */
    Servercommunication.ReturnLogin login(String userID, String userPSW);

    /**
     * Änderung von Transportobjekt zu Enum von Brandes Lukas
     */

    void setUserDB(UserDB userDB);
}
