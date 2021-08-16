package gKVServer.database.data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

//Autor Richard Moeckel
@Entity
public class Provider {
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

    public Provider(String name, int price, String bottletype, int boxtype) {
        this.name = name;
        this.price = price;
        this.bottletype = bottletype;
        this.boxtype = boxtype;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


    public String getBottletype() {
        return bottletype;
    }

    public int getBoxtype() {
        return boxtype;
    }

    public Provider() {

    }

    public Long getId() {
        return id;
    }
}
