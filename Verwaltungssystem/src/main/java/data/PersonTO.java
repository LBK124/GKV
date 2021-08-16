package data;

public class PersonTO implements java.io.Serializable {

    public final String id;
    public final int cellerId;
    public final String firstname;
    public final String surename;
    public final boolean state;
    public final boolean key;
    public final boolean admin;
    public final int balance;
    public final String password;
    public final String eMail;
    public final boolean requestState;
    public final boolean wish;


    public PersonTO(String id, int cellerId, String firstname, String surename, boolean state, boolean key, boolean admin, int balance, String password, String eMail, boolean requestState, boolean wish) {
        this.id = id;
        this.cellerId = cellerId;
        this.firstname = firstname;
        this.surename = surename;
        this.state = state;
        this.key = key;
        this.admin = admin;
        this.balance = balance;
        this.password = password;
        this.eMail = eMail;
        this.requestState = requestState;
        this.wish = wish;
    }
    public String getUserString(){
        return String.format("%s    %-15s %-15s %-7s %-7s %-7s %-20s %7s   %d.%02d€"
                ,id,surename,firstname, key, state, admin, eMail, requestState, balance/100,balance%100);
    }
}

