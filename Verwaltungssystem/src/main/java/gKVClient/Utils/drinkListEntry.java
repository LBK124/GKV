package gKVClient.Utils;

public class drinkListEntry {
    private String name;
    private int amount;
    private int price;
    //Konstruktor
    public drinkListEntry(String name, int amount, int price){
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
    //Überschreibt die Methode to String um ListenString zu erzeugen
    public String toString(){
        utils tools = new utils();
        StringBuffer dest = new StringBuffer();
        dest.append(name);
        for(int i = 0; i < 15 - name.length(); ++i) {
            dest.append(" ");
        }
        dest.append(amount);
        for(int i = 0; i < 15 - Integer.toString(amount).length(); ++i) {
            dest.append(" ");
        }
        dest.append(tools.intToString(price));
        dest.append("€");

        return dest.toString();
    }


}
