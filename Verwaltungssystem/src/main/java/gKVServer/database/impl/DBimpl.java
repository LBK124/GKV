package gKVServer.database.impl;

import data.*;
import gKVServer.database.CellarDB;
import gKVServer.database.UserDB;
import gKVServer.database.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
//Autor Richard Moeckel
@Component
public class DBimpl implements CellarDB, UserDB {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private CellarRepository cellarRepository;
    @Autowired
    private DocumentsRepository documentsRepository;
    @Autowired
    private DrinkRepository drinkRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WishesRepository wishesRepository;
    @Autowired
    private ProviderRepository providerRepository;
    private  int i = 0;
    public boolean createcellars() {
        System.out.println("create cellers");
        if(cellarRepository.count() < 4){
            for (long i = 1; i < 5; i++) {
                System.out.println("cellar"+ i+ "created");
                Cellar cellar = new Cellar(i);
                cellar.setBalance(0);
                cellarRepository.save(cellar);

            }}
        return true;

    }

    public void fillwithtestdata(){
        System.out.println("nur einmal aber ich war hier " + i);
        if(i > 0){
            return;}else {
            i++;
            Cellar cellar = cellarRepository.findById(2L).get();
            Drink kola = new Drink(cellar, "Kola", 100, 5, 10, "0.33", 24);
            Drink fanta = new Drink(cellar, "Fanta", 100, 5, 10, "0.33", 24);
            Person userHK = new Person(cellar, "HK200", "200", "Hauskasse",
                    false, false, false, false, 0, hashAndClearPassword("HK200", "123456789Ab!".toCharArray()),
                    "haus200@unibw.de", false);
            Person userAD = new Person(cellar, "AD201", "Admin ", "Dummy",
                    false, false, true, true, 0, hashAndClearPassword("HK200", "12Admin34!!!".toCharArray()),
                    "admin@unibw.de", false);
            Person userUD = new Person(cellar, "UD205", "User ", "Dummy",
                    false, false, false, false, 0, hashAndClearPassword("UD205", "123UsEr45!!!".toCharArray()),
                    "user@unibw.de", false);
            personRepository.save(userHK);
            personRepository.save(userAD);
            personRepository.save(userUD);
            Order orderkola = new Order(personRepository.findByUsername("UD205"), "Kola", 100, 1, LocalDateTime.of(2020, 10, 5, 12, 00, 0));
            Order orderfanta = new Order(personRepository.findByUsername("UD205"), "Fanta", 100, 1, LocalDateTime.of(2020, 10, 6, 12, 00, 0));

            drinkRepository.save(kola);
            drinkRepository.save(fanta);
            orderRepository.save(orderfanta);
            orderRepository.save(orderkola);
            Provider provided = new Provider( "Kola", 100, "0.33", 24);
            providerRepository.save(provided);
            //Bill bill = new Bill(personRepository.findByUsername("UD205"), personRepository.findByUsername("UD205").getOrder(), LocalDateTime.of(2020, 10, 1, 0, 0, 0),
            //        LocalDateTime.of(2020, 10, 31, 0, 0, 0));
           // orderkola.setBill(bill);

            Wishes wish = new Wishes(cellarRepository.findById(2l).get(), "wunschkola", 300, "0.5", 24, 3);
            Wishes wish2 = new Wishes(cellarRepository.findById(2l).get(), "wunschbier", 300, "0.5", 24, 3);
            wishesRepository.save(wish);
            wishesRepository.save(wish2);
           // billRepository.save(bill);
            //orderRepository.save(orderkola);

        }
    }
    public static String hashAndClearPassword(String nickname, char[] password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(nickname.getBytes());
            md.update(String.valueOf(password).getBytes());
            Arrays.fill(password, ' ');

            return Base64.getEncoder().encodeToString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("no SHA-256 implementation found!");
        }
    }

    @Override
    public int getCellerCash(int cellerID) {

        Optional<Cellar> cellar = cellarRepository.findById((long)cellerID);
        int tmp = cellar.get().getBalance();
        return tmp;

    }

    @Override
    public boolean setCellerCash(int cellerID, int value) {
        try {

            Cellar cellar = cellarRepository.findById((long)cellerID).get();
            cellar.setBalance(value);
            cellarRepository.save(cellar);
            return true;
        }catch (Exception e) {
            return false;
        }



    }


    @Override
    public DocTO[] getDocList(int cellerID) {
        return new DocTO[0];
    }

    @Override
    public boolean setDoc(int cellerID, String doc) {
        return false;
    }

    @Override
    public boolean getState(int cellerID) {
        Cellar cellar = null;
        try {
            cellar = cellarRepository.findById((long)cellerID).get();

        }catch (Exception e){
            e.printStackTrace();

        }
        return cellar.isStatus();
    }

    @Override
    public boolean setState(int cellerID, boolean state) {
        try {
            Cellar cellar = cellarRepository.findById((long)cellerID).get();
            cellar.setStatus(state);
            cellarRepository.save(cellar);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public int getTribute(int cellerID) {
        try {
            Cellar cellar = cellarRepository.findById((long)cellerID).get();
            return cellar.getFee();
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean setTribute(int cellerID, int tribute) {
        try {
            Cellar cellar = cellarRepository.findById((long)cellerID).get();
            cellar.setFee(tribute);
            cellarRepository.save(cellar);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override

    public DrinkTO getDrink(String name, String size, int cellarID) {
        try {
            Drink drink = drinkRepository.findByNameAndBottletypeAndCellar(name, size,
                    cellarRepository.findById((long)cellarID).get());
            DrinkTO drinkreturn = new DrinkTO(drink.getName(), drink.getBottletype(), drink.getBoxtype(), drink.getPrice(), drink.getMust(),drink.getHave());
            return drinkreturn;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public DrinkTO getDrinkDBU(String name, String size, int cellarID) {
        try {
            Drink drink = drinkRepository.findByNameAndBottletypeAndCellar(name, size,
                    cellarRepository.findById((long)cellarID).get());
            DrinkTO drinkreturn = new DrinkTO(drink.getName(), drink.getBottletype(), drink.getBoxtype(), drink.getPrice(), drink.getMust(),drink.getHave());
            return drinkreturn;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DrinkTO[] getInventory(int cellerID) {
        try {
            Cellar cellar = cellarRepository.findById((long)cellerID).get();
            Set<Drink> drinks= cellar.getInnventory();
            DrinkTO[] drinkarray = new DrinkTO[drinks.size()];
            AtomicInteger i = new AtomicInteger(0);
            drinks.forEach(drink -> {

                drinkarray[i.get()] = new DrinkTO(drink.getName(), drink.getBottletype(),drink.getBoxtype(),
                        drink.getPrice(), drink.getMust(), drink.getHave());
                i.getAndIncrement();
            });
            return drinkarray;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public BillTO[] getBill(String userID) {
        try {
            Person user = personRepository.findByUsername(userID);
            List<Bill> bills = user.getBill();
            int listlength = bills.size();
            BillTO[] billarray = new BillTO[listlength];
            AtomicInteger h = new AtomicInteger(0);
            AtomicInteger j = new AtomicInteger(0);
           bills.forEach(bill -> {
               OrderTO orderarray[] = new OrderTO[bill.getOrders().size()];
               List<Order> orders = bill.getOrders();
               orders.forEach(order ->{
                   orderarray[j.get()] = new OrderTO(order.getUser().getUsername(), order.getName(),
                           order.getValue(), order.getAmount(), order.getDate());
                   j.getAndIncrement();
               });
               billarray[h.get()] = new BillTO(bill.getTimestampstart(),bill.getTimestampstop(), orderarray);
               h.getAndIncrement();
           });
           return billarray;
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public boolean createUser(PersonTO person) {
        try {
            Cellar cellar = cellarRepository.findById((long)person.cellerId).get();
            Person user = new Person(cellar, person.id, person.firstname,
                    person.surename, person.state, person.requestState,
                    person.key, person.admin, person.balance, person.password, person.eMail, person.wish);
            personRepository.save(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public PersonTO getUser(String personID) {
        try {
            Person user = personRepository.findByUsername(personID);
            PersonTO userreturn = new PersonTO(user.getUsername(), (int)user.getCellar().getId(), user.getFirstname(), user.getLastname(), user.isState(), user.isKey(), user.isAdmin(), user.getBlance(),
                    user.getPassword(), user.getEmail(), user.isRequeststate(), user.isWish());
            return userreturn;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean setUser(PersonTO person) {
        try {
           Person user = personRepository.findByUsername(person.id);
           user.setWish(person.wish);
           user.setRequeststate(person.requestState);
           user.setEmail(person.eMail);
           user.setPassword(person.password);
           user.setBlance(person.balance);
           user.setAdmin(person.admin);
           user.setKey(person.key);
           user.setState(person.state);
           user.setLastname(person.surename);
           user.setFirstname(person.firstname);
           user.setCellar(cellarRepository.findById((long)person.cellerId).get());
           user.setUsername(person.id);
           personRepository.save(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createOrder(OrderTO order) {
        try {
            Order orderreturn = new Order(personRepository.findByUsername(order.personID), order.name, order.value,
                    order.amount, order.date);
            orderRepository.save(orderreturn);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OrderTO[] getOrder(String personID, LocalDateTime timestampstart, LocalDateTime timstampstop) {

        AtomicInteger j = new AtomicInteger(0);

        List<Order> orders = personRepository.findByUsername(personID).getOrder();
        OrderTO orderarray[] = new OrderTO[orders.size()];
        orders.forEach(order ->{
            LocalDateTime date = order.getDate();
            if((date.equals(timestampstart)||date.equals(timstampstop))|| (date.isAfter(timestampstart)&& date.isBefore(timstampstop)))
            {
                orderarray[j.getAndIncrement()] = new OrderTO(order.getUser().getUsername(), order.getName(),
                        order.getValue(), order.getAmount(), order.getDate());
            }
        });
        AtomicInteger i = new AtomicInteger(0);
        for (OrderTO orderTO : orderarray) {
            if(orderTO != null) {
                i.incrementAndGet();
            }else break;
        }
        OrderTO[] orderarrayout = new OrderTO[j.get()];
        System.arraycopy(orderarray, 0, orderarrayout, 0, i.get());

        return orderarrayout;
    }

    @Override
    public boolean createbill(BillTO bill) {
        try {
            AtomicInteger i = new AtomicInteger(0);
            OrderTO[] Orderarray = bill.Orders;
            List<Order> orderint = new LinkedList<Order>();

            if(Orderarray == null)
                return false;
            Person user = personRepository.findByUsername(Orderarray[0].personID);
            for (OrderTO orderTO : Orderarray) {
                orderint.add(orderRepository.findByUserAndDate(user, orderTO.date));
            }

            Bill newbill = new Bill(user,orderint
                    , bill.BillTimestart, bill.BillTimestop );
            billRepository.save(newbill);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public OrderTO[] getOrdersnoBill(String personID) {
        try {
            Person user = personRepository.findByUsername(personID);
            List<Order> orders = user.getOrder();
            OrderTO[] orderreturn = new OrderTO[orders.size()];
            AtomicInteger i = new AtomicInteger(0);
            orders.forEach(order -> {
                if(order.getBill() == null) {
                    orderreturn[i.getAndIncrement()] = new OrderTO(order.getUser().getUsername(), order.getName(), order.getValue(), order.getAmount(),
                            order.getDate());
                }
            });
            return orderreturn;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public BillTO getBill(String personID, LocalDateTime timstamp) {
       try {
           Bill bill = billRepository.findByPersonAndTimestampstart(personRepository.findByUsername(personID), timstamp);
           OrderTO[] ordertmp = new OrderTO[bill.getOrders().size()];
           List<Order> orders = bill.getOrders();
           AtomicInteger i = new AtomicInteger(0);
           orders.forEach(order -> {
               ordertmp[i.getAndIncrement()] = new  OrderTO(order.getUser().getUsername(), order.getName(), order.getValue(), order.getAmount(),
                       order.getDate());
           });
           return new BillTO(bill.getTimestampstart(),bill.getTimestampstop(),ordertmp );
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public boolean createOrder(String user, int value, String purpose, LocalDateTime timestamp) {
        try {
            OrderTO order = new OrderTO(user, purpose, value, 1, timestamp);
            createOrder(order);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean createOrder(String user, String drink, int quantity, String bottletype, LocalDateTime timestamp) {
        try {
            Cellar cellar = personRepository.findByUsername(user).getCellar();
            Drink tmp = drinkRepository.findByNameAndBottletypeAndCellar(drink, bottletype, cellar);
            int value = tmp.getPrice() * quantity;
            OrderTO order = new OrderTO(user, drink, value, quantity, timestamp);
            createOrder(order);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deltePerson(String userID) {
        try {
            Person user = personRepository.findByUsername(userID);
            Long id = user.getId();
            personRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public OrderTO[] getOrder(String user, LocalDateTime timeStamp) {
    try {
        List<Order> orders = personRepository.findByUsername(user).getOrder();
        OrderTO[] orderarray = new OrderTO[orders.size()];
        AtomicInteger i = new AtomicInteger(0);
        orders.forEach(order -> {
            LocalDateTime tmp;
            tmp = order.getDate();

            if (tmp.isAfter(timeStamp) || tmp.isEqual(timeStamp)) {
                orderarray[i.getAndIncrement()] = new OrderTO(order.getUser().getUsername(), order.getName(), order.getValue(), order.getAmount(), order.getDate());
            }
        });
        AtomicInteger j = new AtomicInteger(0);
        for (OrderTO orderTO : orderarray) {
            if(orderTO != null) {
                j.incrementAndGet();
            }else break;
        }
        OrderTO[] orderarrayout = new OrderTO[j.get()];
        System.arraycopy(orderarray, 0, orderarrayout, 0, j.get());

        return orderarrayout;
    }catch (Exception e){
        e.printStackTrace();
        return null;
     }
    }

    @Override
    public PersonTO[] getUsers(int cellarID) {
        try {
            Cellar cellar = cellarRepository.findById((long) cellarID).get();
            Set<Person> users = cellar.getUserlsit();
            PersonTO[] userarray = new PersonTO[users.size()];
            AtomicInteger i = new AtomicInteger(0);
            users.forEach(person -> {
                userarray[i.getAndIncrement()] = new PersonTO(person.getUsername(), (int) person.getCellar().getId(), person.getFirstname(), person.getLastname(), person.isState(),
                        person.isKey(), person.isAdmin(), person.getBlance(), person.getPassword(), person.getEmail(), person.isRequeststate(), person.isWish());

            });
            return userarray;
        }catch (Exception e){
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public DrinkTO[] getWishlist(int cellerID) {
        Cellar cellar = cellarRepository.findById((long) cellerID).get();
       Set<Wishes> wishlist = cellar.getWishlist();
       DrinkTO[] wishlistarray = new DrinkTO[wishlist.size()];
       AtomicInteger i = new AtomicInteger(0);
       wishlist.forEach(wishes -> {
           wishlistarray[i.getAndIncrement()] = new DrinkTO(wishes.getName(), wishes.getBottletype(), wishes.getBoxtype(), 0, 0, 0);

       });
       return new DrinkTO[0];
    }

    @Override
    public boolean setWishlist(int cellerID, DrinkTO[] wishlist) {
        List<Wishes> wishlistold = wishesRepository.findAll();
        try {

            wishesRepository.deleteAll();
            Cellar cellar = cellarRepository.findById((long) cellerID).get();

            for (DrinkTO drinkTO : wishlist) {
                wishesRepository.save(new Wishes(cellar, drinkTO.name, drinkTO.price, drinkTO.bottleType, drinkTO.packType, 1));
            }
            return true;



        }catch (Exception e){
            wishesRepository.saveAll(wishlistold);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createDrink(DrinkTO drink, int cellarID) {
        try {
            Cellar cellar = cellarRepository.findById((long)cellarID).get();
            drinkRepository.save(new Drink(cellar, drink.name, drink.price, drink.actualvalue, drink.setvalue, drink.bottleType, drink.packType));

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteDoc(String doc) {
        try {
            //documentsRepository.deleteById(documentsRepository.findByName(doc).getId());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public boolean setDrink(int cellarID, DrinkTO drink) {
        Cellar cellar = cellarRepository.findById((long)cellarID).get();
        Drink drinkold = drinkRepository.findByNameAndBottletypeAndCellar(drink.name, drink.bottleType, cellar);
        try {
            Drink drinknew =drinkold;
            drinknew.setName(drink.name);
            drinknew.setPrice(drink.price);
            drinknew.setHave(drink.actualvalue);
            drinknew.setMust(drink.setvalue);
            drinknew.setBottletype(drink.bottleType);
            drinknew.setBoxtype(drink.packType);
           // drinkRepository.deleteById(drinkold.getId());
             //new Drink(cellar, drink.name, drink.price, drink.actualvalue, drink.setvalue, drink.bottleType, drink.packType);
            drinkRepository.save(drinknew);

            return true;

        }catch (Exception e){
            e.printStackTrace();
            drinkRepository.save(drinkold);
            return false;
        }
    }
    @Override
    public boolean setDrinkUDB(int cellarID, DrinkTO drink) {
        Cellar cellar = cellarRepository.findById((long)cellarID).get();
        Drink drinkold = drinkRepository.findByNameAndBottletypeAndCellar(drink.name, drink.bottleType, cellar);
        try {
            Drink drinknew =drinkold;
            drinknew.setName(drink.name);
            drinknew.setPrice(drink.price);
            drinknew.setHave(drink.actualvalue);
            drinknew.setMust(drink.setvalue);
            drinknew.setBottletype(drink.bottleType);
            drinknew.setBoxtype(drink.packType);
            // drinkRepository.deleteById(drinkold.getId());
            //new Drink(cellar, drink.name, drink.price, drink.actualvalue, drink.setvalue, drink.bottleType, drink.packType);
            drinkRepository.save(drinknew);

            return true;

        }catch (Exception e){
            e.printStackTrace();
            drinkRepository.save(drinkold);
            return false;
        }
    }

    @Override
    public boolean setInventory(int cellarID, DrinkTO[] drinks) {
        Cellar cellar = cellarRepository.findById((long)cellarID).get();
        Set<Drink> inventoryold = cellar.getInnventory();
        try {
            List<Drink> inventorty = drinkRepository.findAll();
            inventorty.forEach(drink -> {
                if(drink.getCellar().getId() == cellar.getId())
                    drinkRepository.deleteById(drink.getId());
            });
            for (DrinkTO drink : drinks) {
                drinkRepository.save(new Drink(cellar, drink.name, drink.price, drink.actualvalue, drink.setvalue, drink.bottleType, drink.packType));
            }
            return true;


        }catch (Exception e){
            e.printStackTrace();
            drinkRepository.saveAll(inventoryold);
            return false;
        }

    }

    @Override
    public CellarTO getCellar(int cellarID) {
        try {
            Cellar cellar = cellarRepository.findById((long)cellarID).get();
           Set<Documents> doclist = cellar.getDoc();
            Set<Wishes> wishlist = cellar.getWishlist();
            DocTO[] docarray = new DocTO[cellar.getDoc().size()];
            DrinkTO[] wisharray = new DrinkTO[cellar.getDoc().size()];
            AtomicInteger i = new AtomicInteger(0);
            AtomicInteger j = new AtomicInteger(0);

            doclist.forEach(documents -> {
               docarray[i.getAndIncrement()] = new DocTO(documents.getName());
            });
            wishlist.forEach(wishes -> {
                wisharray[j.getAndIncrement()] = new DrinkTO(wishes.getName(), wishes.getBottletype(), wishes.getBoxtype(), wishes.getPrice(), 0, 0);
            });


            CellarTO cellarTO = new CellarTO(cellar.getBalance(), docarray, cellar.isStatus(),
                    cellar.getFee(), wisharray, cellar.getRoom(), (int)cellar.getId());
            return cellarTO;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public DocTO getDoc(int cellarID, String name) {
        try {
            Cellar cellar = cellarRepository.findById((long) cellarID).get();
            Documents doc =  documentsRepository.findByNameAndCellar(name, cellar);
            return new DocTO(doc.getName());

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public DrinkTO[] getProviderList() {
        try {
            List <Provider> provider = providerRepository.findAll();
            if(provider.isEmpty())
                return null;
            DrinkTO[] providerarray = new DrinkTO[provider.size()];
            AtomicInteger i = new AtomicInteger(0);
            provider.forEach(provider1 -> {
                    providerarray[i.getAndIncrement()] = new DrinkTO(provider1.getName(),
                            provider1.getBottletype(), provider1.getBoxtype(), provider1.getPrice(), 0, 0);

            });
            return providerarray;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public OrderTO[] getOrder(String cellarUser) {
        try {
           List <Order> orders = personRepository.findByUsername(cellarUser).getOrder();
           OrderTO[] orderarray = new OrderTO[orders.size()];
           AtomicInteger i = new AtomicInteger(0);
           orders.forEach(order -> {
               orderarray[i.getAndIncrement()] = new OrderTO(order.getUser().getUsername(), order.getName(), order.getValue(), order.getAmount(), order.getDate());

           });
           return  orderarray;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }


}
