package gKVServer.database.data;

import javax.persistence.*;
import java.util.List;
//Autor Richard Moeckel
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
   @ManyToOne
    @JoinColumn(name = "CELLAR_ID")
    private Cellar cellar;
   @OneToMany(mappedBy = "person")
   private List<Bill> bill;

    public Long getId() {
        return id;
    }

    @OneToMany(mappedBy = "user")
    private List<Order> order;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column
    private boolean state;
    @Column
    private boolean requeststate;
    @Column
    private boolean key;
    @Column
    private boolean admin;
    @Column
    private int blance;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    public boolean isWish() {
        return wish;
    }

    public void setWish(boolean wish) {
        this.wish = wish;
    }

    @Column
    private boolean wish;

    public List<Bill> getBill() {
        return bill;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setBill(List<Bill> bill) {
        this.bill = bill;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Cellar getCellar() {
        return cellar;
    }

    public void setCellar(Cellar cellar) {
        this.cellar = cellar;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String surename) {
        this.firstname = surename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isRequeststate() {
        return requeststate;
    }

    public void setRequeststate(boolean requeststate) {
        this.requeststate = requeststate;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getBlance() {
        return blance;
    }

    public void setBlance(int blance) {
        this.blance = blance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person(){

    }
    public Person( Cellar cellar ,String username, String firstname, String lastname,
                  boolean state, boolean requeststate, boolean key, boolean admin, int blance,
                  String password, String email, boolean wish) {
        this.username = username;
        this.cellar = cellar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.state = state;
        this.requeststate = requeststate;
        this.key = key;
        this.admin = admin;
        this.blance = blance;
        this.password = password;
        this.email = email;
        this.wish = wish;
    }




}



