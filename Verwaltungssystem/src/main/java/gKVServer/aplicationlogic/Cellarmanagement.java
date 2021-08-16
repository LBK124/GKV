package gKVServer.aplicationlogic;

import data.*;
import gKVServer.database.CellarDB;

/**
 * Autor: Brandes Lukas
 */
public interface Cellarmanagement {
    /**
     * @Anwendungsfall Neues Getränk einbinden
     * @Aufgabe Prüft ob es sich bei dem Nutzer um den Administrator handel, indem er die Admin Daten über die
     *          ManagementExcange Schnittstelle an das Usermanagement übergibt. Anschließend wird überprüft, ob die eingegebene
     *          Getränkedaten vollständig sind. Wenn dies der Fall ist, werden die Getränke Daten and die Datenbank
     *          weitergegeben und ein neues Getränk angelegt.
     * @param adminID   NutzerID des Administrators
     * @param adminPSW  Nutzer Passwort des Administrators
     * @param drink     Das Transportobjekt enthält: name, bottleType, packType, price, setvalue, actualvalue = 0
     * @return "True" bei einer erfolgreichen Rückmeldung der Datenbank und "False" in allen Fehlerfellen.
     */
    boolean createDrink(String adminID, String adminPSW,DrinkTO drink);

    /**
     * @Anwendungsfall Dokument löschen/verwalten
     * @Aufgabe Prüft ob es sich bei dem Nutzer um den Administrator handel, indem er die Admin Daten über die
     *          ManagementExcange Schnittstelle an das Usermanagement übergibt. Anschließend wird der name des zu
     *          löschenden Dokuments an die Datenbank weitergegeben.
     * @param adminID   NutzerID des Administrators
     * @param adminPSW  Nutzer Passwort des Administrators
     * @param docName   Name des zu löschenden Dokuments
     * @return "True" bei einer erfolgreichen Rückmeldung der Datenbank und "False" in allen Fehlerfellen.
     */
    boolean deleteDoc(String adminID, String adminPSW,String docName);

    /**
     * @Anwendungsfall Bestellung generieren
     * @Aufgabe Wenn die Variable acknowledge "True" ist, wird die letzte erstellte Rechnung auf der Datenbank gespeichert
     * @param acknowledge Eine Variable welche die Zufriedenheit des Admin mit der generierten Bestellung verkörpert.
     * @return "True" für erfolgreiche Speicherung, "False" für alle Fehlerfälle
     */
    boolean sendAcknowledge (boolean acknowledge);

    /**
     * @Anwendungsfall Rechnung versenden
     * @Aufgabe Prüft ob es sich bei dem Nutzer um den Administrator handel, indem er die Admin Daten über die
     *          ManagementExcange Schnittstelle an das Usermanagement übergibt. Anschliesend wird das Plugin des E-Mail
     *          Service gestartet und mit den den Variablen "adminRzPSW" und "adminRZID" versorgt. Wenn von diesem Plugin
     *          eine erfolgreiche Meldung zurück kommt, wird die Schnittstelle ManagementExcange aufgefordert sämtliche
     *          Rechnungen sowie E-Mail addressen der Nutzer von der Datenbank zu laden. Daraufhin wird für jeden Nutzer
     *          eine E-mail erstellt werden seine Rechnung erhält und versendet.
     * @param adminRZID     RechnenZentrums Kennung des Nutzers
     * @param adminRZPSW    RechenZentrums Passwort des Nutzers
     * @param adminID       Nutzer ID des Administrators
     * @param adminPSW      Passwort des Administrators
     * @return "True" für erfolgreiche versenden der Email, "False" für alle Fehlerfälle
     */
    boolean sendBill (String adminRZID, String adminRZPSW, String adminID, String adminPSW);

    /**
     * @Anwendungsfall Dokument speichern
     * @Aufgabe Prüft ob es sich bei dem Nutzer um den Administrator handel, indem er die Admin Daten über die
     *          ManagementExcange Schnittstelle an das Usermanagement übergibt. Anschliesend wird das Dokument zum speichern
     *          an die Datenbank übergeben.
     * @param adminID   Nutzer ID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @param docName   Name des Dokuments
     * @return "True" für eine erfolgreiche Rückmeldung der Datenbank , "False" für alle Fehlerfälle
     */
    boolean setDoc(String adminID, String adminPSW, String docName);

    /**
     * @Anwendungsfall Preis anpassen
     * @Aufgabe Prüft ob es sich bei dem Nutzer um den Administrator handel, indem er die Admin Daten über die
     *          ManagementExcange Schnittstelle an das Usermanagement übergibt. Wenn dies mit "True" durchläuft, werden
     *          die neuen Getränke Infos and die Datenbank zum überschreiben weitergegeben
     * @param adminID   Nutzer ID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @param drink     Das vollständig ausgefüllte DrinkTO
     * @return "True" für erfolgreiche versenden der Email, "False" für alle Fehlerfälle
     */
    boolean setDrink(String adminID, String adminPSW,DrinkTO drink);

    /**
     * @Anwendungsfall Inventur / Korrektur
     * @Aufgabe Prüft ob es sich bei dem Nutzer um den Administrator handel, indem er die Admin Daten über die
     *          ManagementExcange Schnittstelle an das Usermanagement übergibt. Wenn dies der Fall ist, wird jedes Getränk
     *          in dem Feld auf der Datenbank angelegt.
     * @param adminID   Nutzer ID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @param drinks    Das vollständig ausgefüllte DrinkTO auf jeder Position des Feldes
     * @return "True" für erfolgreiche versenden der Email, "False" für alle Fehlerfälle
     */
    boolean setInventory(String adminID, String adminPSW,DrinkTO[] drinks);

    /**
     * @Anwendungsfall Keller bearbeiten (kein Anwendungsfall jedoch vorraussetzung für einige)
     * @Aufgabe Prüft ob es sich bei dem Nutzer um den Administrator handel, indem er die Admin Daten über die
     *          ManagementExcange Schnittstelle an das Usermanagement übergibt. Wenn dies der Fall ist, wird von der
     *          Datenbank die Informationen des jeweiligen Kellers angefragt.
     * @param adminID   Nutzer ID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @return liefert ein vollständig ausgefülltest CellerTO zurück. Null im Fehlerfall
     */
    CellarTO getCellar(String adminID, String adminPSW);

    /**
     * @Anwendungsfall Bestellung generieren
     * @Aufgabe Prüft ob es sich bei dem Nutzer um den Administrator handel, indem er die Admin Daten über die
     *          ManagementExcange Schnittstelle an das Usermanagement übergibt. Wenn dies der Fall ist, wird von der Datenbank
     *          das aktuelle Inventar angefordert. Es wird der Fehlbestand aus "Ist" und "Soll" Wert errechnet und durch die
     *          Kastengröße geteilt. Somit wurde die nachzubestellende Kastenanzahl ermittelt. Dies wird für jede Getränk
     *          durchgeführt. Die Anzahl der zu bestellenden Kästen sowie der jeweilge Name werden in die Bestellvorlage
     *          eingebunden.
     * @param adminID   Nutzer ID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @return das vollständig ausgefüllte Bestellvormular oder Null im Fehlerfall
     */
    DocTO createDelivery (String adminID, String adminPSW);

    /**
     * @Anwendungsfall Dokumente verwlten
     * @Aufgabe Prüft ob es sich bei dem Nutzer um den Administrator handel, indem er die Admin Daten über die
     *          ManagementExcange Schnittstelle an das Usermanagement übergibt. Wenn dies der Fall ist, wird das geforderte
     *          Dokument von der Datenbank angefragt.
     * @param adminID   Nutzer ID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @param docName   Name des Dokuments
     * @return  Das geforderte Dokument oder Null im Fehlerfall
     */
    DocTO getDoc(String adminID, String adminPSW,String docName);

    /**
     * @Anwendungsfall Dokumente Anzeigen
     * @Aufgabe Prüft ob es sich bei dem Nutzer um den Administrator handel, indem er die Admin Daten über die
     *          ManagementExcange Schnittstelle an das Usermanagement übergibt. Wenn dies der Fall ist, Werden alle Daten
     *          von der Datenbank angefordert.
     * @param adminID   Nutzer ID des Administrators
     * @param adminPSW  Passwort des Administrators
     * @return  Alle Dokumente welche auf der Datenbank hinterlegt wurden, oder eine leere Liste
     */
    DocTO[] getDocs(String adminID, String adminPSW);

    /**
     * @Anwendungsfall Inventur/Korrektur
     * @Aufgabe Es werden alle Getränke des jeweiligen Kellers als einfach verkettete Liste von der Datenbank gelade.
     * @param cellarID Die Identifiaktionsnummer des jeweiligen Kellers
     * @return  Eine Liste aller Getränke des Kellers oder null im Fehlerfall
     */
    DrinkTO[] getInventory(int cellarID);

    /**
     * @Anwendungsfall Wunschliste
     * @Aufgabe Es wird gesamte Liste des Liefanten von der Datenbank angeforder
     * @return Die gesamte Liste des Liefanten oder "Null" im Fehlerfall
     */
    DrinkTO[] getProviderList();

    /**
     * @Anwendungsfall Buchung anzeigen
     * @Aufgabe Prüft ob es sich bei dem Nutzer um den Administrator handel, indem er die Admin Daten über die
     *          ManagementExcange Schnittstelle an das Usermanagement übergibt. Wenn dies der Fall ist, werden die Buchungen
     *          der jeweiligen Person von der Datenbank angefrat und an ausgegeben
     * @param adminID       Nutzer ID des Administrators
     * @param adminPSW      Passwort des Administrators
     * @param cellerUserID  Nutzer ID aller Nutzer
     * @return ein vollständig ausgefülltes OrderTO aller Nutzer
     */
    OrderTO[] getOrder(String adminID, String adminPSW,String cellerUserID);


    void setCellarDB(CellarDB cellarDB);
}
