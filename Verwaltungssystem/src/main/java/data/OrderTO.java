package data;

import java.time.LocalDateTime;

public class OrderTO implements java.io.Serializable{

    public final String personID;
    public final String name;
    public final int value;
    public final int amount;
    public final LocalDateTime date;

    public OrderTO(String personID, String name, int value, int amount, LocalDateTime date) {
        this.personID = personID;
        this.name = name;
        this.value = value;
        this.amount = amount;
        this.date = date;
    }

    public String getListEntry(){

        return String.format("%-20s",date.toString().substring(0,10)) + String.format("%-40s",name) + String.format("%d.%02d€",value/100,value%100);
    }
}
