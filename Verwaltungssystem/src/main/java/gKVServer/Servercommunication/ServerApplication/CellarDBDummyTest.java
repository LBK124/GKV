package gKVServer.Servercommunication.ServerApplication;

import data.*;
import gKVServer.database.CellarDB;
import java.time.LocalDateTime;


/**
 * Autor: Brandes Lukas
 */

    public class CellarDBDummyTest implements CellarDB {

        int tributeDB = 24;
        int cellarCash1 = 0;
        int cellarCash2 = 1000;
        boolean cellarState1 = true;
        private DocTO[] dockList;
        private DrinkTO[] inventory;
        private DrinkTO[] providerList;
        private DrinkTO[] wishlist;
        private CellarTO cellar;
        private int[] rooms = {201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,
                222,223,224,225,226,227,228,229,230,231,232,233,234,235,236};
        private OrderTO[] orders;

        public CellarDBDummyTest(){
            //Dokumente anlegen
            dockList = new DocTO[3];
            dockList[0] = new DocTO("Rechnung 20.8.2020");
            dockList[1] = new DocTO("Rechnung 31.7.2020");
            dockList[2] = new DocTO("Inventur 15.5.2020");

            //Inventar anlegen
            inventory = new DrinkTO[12];
            inventory[0] = new DrinkTO("Fanta","1,0",12,150,100,50);
            inventory[1] = new DrinkTO("Cola","1,0",12,150,100,45);
            inventory[2] = new DrinkTO("Volvic Touch","1,5",12,199,100,65);
            inventory[3] = new DrinkTO("Römerquelle","1",12,99,100,29);
            inventory[4] = new DrinkTO("Hacker Radler","0,5",24,100,100,78);
            inventory[5] = new DrinkTO("Spezi Original","0,5",24,100,100,32);
            inventory[6] = new DrinkTO("Deit Zitrone","1,0",24,150,100,14);
            inventory[7] = new DrinkTO("Augustiner Edelstoff","0,5",24,100,100,85);
            inventory[8] = new DrinkTO("Gutmann Weißbier","0,5",24,85,100,36);
            inventory[9] = new DrinkTO("Pöllinger Drink ACE","0,33",24,85,100,19);
            inventory[10] = new DrinkTO("Pöllinger ISO-Fit","0,33",24,85,100,67);
            inventory[11] = new DrinkTO("Gösser Naturradler","0,5",24,85,100,55);

            //Wunschliste anlegen
            wishlist = new DrinkTO[3];
            wishlist[0] = new DrinkTO("Fritz Kola","0,33",24,1,100,50);
            wishlist[1] = new DrinkTO("Orangensaft","1,0",12,1,100,50);
            wishlist[2] = new DrinkTO("Milch 1,5%","1,0",12,1,100,50);

            //Provider Liste anlegen
            providerList = new DrinkTO[8];
            providerList[0] = new DrinkTO("Fanta","0,33",24,1,100,0);
            providerList[1] = new DrinkTO("Cola","0,33",24,1,100,0);
            providerList[2] = new DrinkTO("Hacker Radler","0,5",24,100,100,0);
            providerList[3] = new DrinkTO("Spezi Original","0,5",24,100,100,0);
            providerList[4] = new DrinkTO("Deit Zitrone","1,0",24,150,100,0);
            providerList[5] = new DrinkTO("Augustiner Edelstoff","0,5",24,100,100,0);
            providerList[6] = new DrinkTO("Gutmann Weißbier Alkoholfrei","0,5",24,85,100,0);
            providerList[7] = new DrinkTO("Pöllinger Drink ACE Vitamin","0,33",24,85,100,0);

            //Keller anlegen
            cellar = new CellarTO(100,dockList,cellarState1,tributeDB,wishlist, rooms,1);

            //Bestellungen anlegen
            orders = new OrderTO[1];
            orders[0] = new OrderTO("MM201","cola", 1, 1, LocalDateTime.of(2019, 2, 13, 15, 56) );
        }

        @Override
        public int getCellerCash(int cellerID) {
            return cellarCash1;
        }

        @Override
        public boolean setCellerCash(int cellerID, int value) {
            if(cellerID == 1){
                cellarCash1 = value;
                return true;
            }
            if(cellerID == 2){
                cellarCash2 = value;
            }
            return false;
        }

        @Override
        public DocTO[] getDocList(int cellerID) {
            return dockList;
        }

        @Override
        public boolean setDoc(int cellerID, String doc) {
            if(dockList == null){
                dockList = new DocTO[1];
                dockList[0] = new DocTO(doc);
            }
            else{
                DocTO[] temp = new DocTO[dockList.length + 1];
                System.arraycopy(dockList,0,temp,0,dockList.length);
                temp[dockList.length] = new DocTO(doc);
                dockList = temp;
            }
            return true;
        }

        @Override
        public boolean getState(int cellerID) {
            if(cellerID == 2 ){
                return cellarState1;
            }
            return false;
        }

        @Override
        public boolean setState(int cellerID, boolean state) {
            if(cellerID == 2){
                cellarState1 = state;
                return true;
            }
            return false;
        }

        @Override
        public int getTribute(int cellerID) {
            if( cellerID == 1){
                return 24;
            }
            if(cellerID == 2){
                return tributeDB;
            }
            return 0;
        }

        @Override
        public boolean setTribute(int cellerID, int tribute) {
            if(cellerID == 1){
                tributeDB = tribute;
            }
            return false;
        }

        @Override
        public DrinkTO getDrink(String name, String size, int cellarID) {
            for(DrinkTO drink : inventory){
                if(drink.name.contentEquals(name)){
                    return drink;
                }
            }
            return null;
        }




        @Override
        public DrinkTO[] getInventory(int cellerID) {
            return inventory;
        }

        @Override
        public BillTO[] getBill(String userID) {
            /**
             * funktinalität aktuell noch nicht abgebildet
             */
            return new BillTO[0];
        }

        @Override
        public boolean createDrink(DrinkTO drink, int cellarID) {
            DrinkTO[] temp = new DrinkTO[inventory.length +1];

            System.arraycopy(inventory,0,temp,0,inventory.length);

            temp[inventory.length] = drink;
            inventory = temp;
            return true;
        }

        @Override
        public boolean deleteDoc(String doc) {
            for(int i = 0; i < dockList.length;++i){
                if(dockList[i].name.contentEquals(doc)){
                    if(dockList.length == 1){
                        dockList = null;
                    }
                    else {
                        DocTO[] temp = new DocTO[dockList.length - 1];
                        System.arraycopy(dockList, 0, temp, 0, i);
                        System.arraycopy(dockList, i + 1, temp, i, dockList.length - i - 1);
                        dockList = temp;
                    }
                    return true;
                }
            }
            return false;
        }


        @Override
        public boolean setDrink(int cellarID, DrinkTO drink) {
            if(inventory == null){
                inventory = new DrinkTO[1];
                inventory[0] = drink;
            }
            else{
                DrinkTO[] temp = new DrinkTO[inventory.length + 1];
                System.arraycopy(inventory,0,temp, 0,inventory.length);
                temp[inventory.length] = drink;
                inventory = temp;
            }
            return true;
        }

        @Override
        public boolean setInventory(int cellarID, DrinkTO[] drinks) {
            if(cellarID == 2){
                inventory = drinks;
                return true;
            }
            return false;
        }


        @Override
        public CellarTO getCellar(int cellarID) {
            return cellar;
        }

        @Override
        public DocTO getDoc(int cellarID, String name) {
            for(DocTO dokument : dockList){
                if(dokument.name.contentEquals(name)){
                    return dokument;
                }
            }
            return null;
        }

        @Override
        public DrinkTO[] getProviderList() {
            return providerList;
        }

        @Override
        public OrderTO[] getOrder(String cellarUser) {
            return orders;
        }
    }

