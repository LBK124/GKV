package data;

import gKVClient.Utils.utils;

public class DrinkTO implements java.io.Serializable {
    public final String name;
    public final String bottleType;
    public final int packType;
    public final int price;
    public final int setvalue;
    public final int actualvalue;

    //-------------------------------Konstruktor-------------------------------------
    public DrinkTO(String name, String bottleType, int packType, int price, int setvalue, int actualvalue){
        this.name =name;
        this.bottleType = bottleType;
        this.packType = packType;
        this.price = price;
        this.setvalue = setvalue;
        this.actualvalue = actualvalue;
    }

    public String toString(){
        return String.format("%-30s %-15d %5d.%02d€",name, actualvalue,price/100,price%100);
    }
}
