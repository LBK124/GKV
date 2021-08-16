package gKVServer.database.data;

import javax.persistence.*;
//Autor Richard Moeckel
@Entity
public class Documents {
    @Id
    private Long id;
    @Column
    private String name;

    public String getName() {
        return name;
    }

    @ManyToOne()
    @JoinColumn(name = "CELLAR_ID")
    private Cellar cellar;

    public Long getId() {
        return id;
    }

    @Column
    private String filepath;


}
