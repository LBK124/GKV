package gKVServer.database.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
//Autor Richard Moeckel
@Entity
public class Cellar {
    @Id
    private Long id;
    @Column
    private int balance;
    @OneToMany(mappedBy = "cellar")
    private Set<Drink> innventory;
    @OneToMany(mappedBy = "cellar")
    private Set<Documents> doc;
    @Column
    private boolean status;
    @Column
    private int fee;
   @OneToMany(mappedBy = "cellar")
    private Set<Wishes> wishlist;
    @OneToMany(mappedBy = "cellar")
    private Set<Person> userlsit;
    @Column
    private int[] room;

    public Cellar(Long id){
        this.id = id;
    }

    public Cellar() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

   public Set<Drink> getInnventory() {
        return innventory;
    }

    public void setInnventory(Set<Drink> innventory) {
        this.innventory = innventory;
    }

    public Set<Documents> getDoc() {
        return doc;
    }

    public void setDoc(Set<Documents> doc) {
        this.doc = doc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Set<Wishes> getWishlist() {
        return wishlist;
    }

    public void setWishlist(Set<Wishes> wishlist) {
        this.wishlist = wishlist;
    }

    public Set<Person> getUserlsit() {
        return userlsit;
    }


    public void setUsers(Set<Person> users) {
        this.userlsit = users;
    }

    public int[] getRoom() {
        return room;
    }

    public void setRoom(int[] room) {
        this.room = room;
    }

    public void setCellarid(long cellarid) {
        this.id = cellarid;
    }


    public long getCellarid() {
        return id;
    }
}
