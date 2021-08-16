package gKVServer.database.data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
//Autor Richard Moeckel
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;
    @Column
    private LocalDateTime timestampstart;
    @Column
    private LocalDateTime timestampstop;


    @Column
    private boolean payed;
    @OneToMany
    @JoinColumn(name = "BILL_ID")
    private List<Order> orders;


    public Bill(){

    }
    public Bill(Person user, List<Order> orders, LocalDateTime timestampstart, LocalDateTime timestampstop){
        this.person = user;
        this.timestampstart = timestampstart;
        this.timestampstop = timestampstop;
        this.orders = orders;
        payed = false;
    }
    public long getBillid() {
        return id;
    }

    public void setBillid(Long billid) {
        this.id = billid;
    }


    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestampstart() {
        return timestampstart;
    }

    public void setTimestampstart(LocalDateTime timestampstart) {
        this.timestampstart = timestampstart;
    }

    public LocalDateTime getTimestampstop() {
        return timestampstop;
    }

    public void setTimestampstop(LocalDateTime timestampstop) {
        this.timestampstop = timestampstop;
    }

}
