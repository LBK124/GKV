package data;

public class CellarTO implements java.io.Serializable{

    public final int Cellarcash;
    public final DocTO[] Document;
    public final boolean state;
    public final int fee;
    public final DrinkTO[] Wishlist;
    public final int[] Rooms;
    public final int CellarID;

    public CellarTO(int cellarcash, DocTO[] document, boolean state, int fee, DrinkTO[] wishlist, int[] rooms, int cellarID) {
        Cellarcash = cellarcash;
        Document = document;
        this.state = state;
        this.fee = fee;
        Wishlist = wishlist;
        Rooms = rooms;
        CellarID = cellarID;
    }


}
