package gKVClient.MainWindow.UserControl;

public class FilterTO {
    public boolean key;
    public boolean admin;
    public boolean state;
    public boolean movedout;

    public boolean balance;
    public int refbalance;
    public boolean bigger;

    public SortBy sort;
    public boolean sortrising;

    public enum SortBy{ROOM,NAME,SURNAME,BALANCE}

    public FilterTO(){
        key = false;
        admin = false;
        state = false;
        movedout = false;
        balance = false;
        refbalance = 0;
        bigger = false;
        sortrising = false;
        sort = SortBy.ROOM;
    }
}
