package gKVServer.database.data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//Autor Richard Moeckel
@Entity
@Table(name = "ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Person user;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int value;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private LocalDateTime date;



    @ManyToOne
    @JoinColumn(name = "BILL_ID")
    private Bill bill;

    public Order(){

    }

    public Order(Person user, String name, int value, int amount, LocalDateTime date) {
        this.user = user;
        this.name = name;
        this.value = value;
        this.amount = amount;
        this.date = date;
    }
    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
