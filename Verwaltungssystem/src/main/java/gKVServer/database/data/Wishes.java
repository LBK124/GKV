package gKVServer.database.data;

import javax.persistence.*;
//Autor Richard Moeckel
@Entity
public class Wishes {
    @ManyToOne
    @JoinColumn(name = "CELLAR_ID")
    private Cellar cellar;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private int price;
    @Column
    private String bottletype;
    @Column
    private int boxtype;
    @Column
    private int count;

    public Wishes(Cellar cellar, String name, int price, String bottletype, int boxtype, int count) {
        this.cellar = cellar;
        this.name = name;
        this.price = price;
        this.bottletype = bottletype;
        this.boxtype = boxtype;
        this.count = count;
    }

    public Wishes() {

    }

    public Cellar getCellar() {
        return cellar;
    }

    public void setCellar(Cellar cellar) {
        this.cellar = cellar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBottletype() {
        return bottletype;
    }

    public void setBottletype(String bottletype) {
        this.bottletype = bottletype;
    }

    public int getBoxtype() {
        return boxtype;
    }

    public void setBoxtype(int boxtype) {
        this.boxtype = boxtype;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
