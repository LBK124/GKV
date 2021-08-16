package gKVServer.aplicationlogic;

import java.time.LocalDateTime;
import gKVServer.aplicationlogic.Usermanagement;
/**
 * Autor: Brandes Lukas
 */
public interface ManagementExcange {

    /**
     * @Aufgabe Überprüft ob der Nutzer und das zugehörige Passwort übereinstimmen
     * @param userID    NutzerID des betroffenen Nutzers
     * @param userPW    Passwort des jeweiligen Nutzers
     * @return "True" bei übereinstimmung mit den Serverdaten,"False" in allen Fehlerfällen
     */
    boolean checkUser(String userID, String userPW);

    /**
     * @Aufgabe Überprüft ob es sich bei den Anmeldedaten um einen Admin handelt
     * @param adminID   NutzerID des Administrators
     * @param adminPW   Passwort des Administrators
     * @return  "True" wenn es sich um einen richtig angemeldeten Admin handelt. "False"in allen Fehlerfällen
     */
    boolean checkAdmin(String adminID, String adminPW);

    /**
     * @Afgabe Lässt von dem Usermanagement eine Buchung erstellen
     * @param userID        NutzerID des Nutzers
     * @param userPSW       Passwort des Nutzers
     * @param drinkName     Name des Getränks
     * @param amount        Anzahl der entnommenen Getränke
     * @param bottleTpye    der Flaschentyp
     * @return Antwort des Usermanagement
     */
    boolean createOrder(String userID,String userPSW,String drinkName,int amount,String bottleTpye);

    /**
     * @Aufgabe liefert die die Keller Id des Nutzers zurück
     * @param userID
     * @return
     */
    int getCellarID(String userID);
}
