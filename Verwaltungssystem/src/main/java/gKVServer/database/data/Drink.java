package gKVServer.database.data;

import javax.persistence.*;
//Autor Richard Moeckel
@Entity
public class Drink {


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
    private int have;
    @Column
    private int must;
    @Column
    private String bottletype;
    @Column
    private int boxtype;



    public Drink(){

    }

 public Drink(Cellar cellar, String name, int price, int have, int must, String bottletype, int boxtype) {
  this.cellar = cellar;
  this.name = name;
  this.price = price;
  this.have = have;
  this.must = must;
  this.bottletype = bottletype;
  this.boxtype = boxtype;
 }



    public Long getId() {
        return id;
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

    public int getHave() {
        return have;
    }

    public void setHave(int have) {
        this.have = have;
    }

    public int getMust() {
        return must;
    }

    public void setMust(int must) {
        this.must = must;
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
}
