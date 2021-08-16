package gKVClient.MainWindow;

import data.DrinkTO;
import data.OrderTO;
import gKVClient.Clientcommunikation.Clientcommunikation;
import gKVClient.MainWindow.UserControl.FilterTO;
import gKVClient.Utils.utils;
import gKVClient.MainWindow.CellarTab.cellarControlPanel;
import gKVClient.MainWindow.CellarTab.cellarOptions;
import gKVClient.MainWindow.UserControl.userControl;
import gKVClient.MainWindow.UserControl.userFilter;
import gKVClient.MainWindow.UserTab.userInfo;
import gKVClient.MainWindow.DrinkWishList.*;
import gKVClient.MainWindow.UserControl.userAddRemovePanel;
import data.PersonTO;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
* Autoren : Hörkner
* Beschreibung: Hauptfenster des Clients
* */
public class mainWindow extends javax.swing.JDialog {
    private javax.swing.JPanel contentPane;
    private javax.swing.JList listview;
    private javax.swing.JTabbedPane tabbedPane1;
    private javax.swing.JScrollPane availableList;
    private javax.swing.JTextField textFieldNameInfo;
    private javax.swing.JTextField textFieldAmountInfo;
    private javax.swing.JTextField textFieldPriceInfo;

    private javax.swing.JTextField textFieldAmountOrder;
    private javax.swing.JTextField textFieldPriceOrder;
    private javax.swing.JButton ButtonOrder;
    private JScrollPane UserBillItems;

    private JPanel userControlPane;
    private JScrollPane JScrollPaneUser;
    private JPanel JPanelCellarControl;
    private JPanel JPanelCellarOptions;
    private JPanel JPanelUserInfo;
    private JLabel LabelFilter;
    private JPanel JPanelDrinkWish;
    private JPanel JPanelUserTab;
    private JPanel JPanelCellarTab;
    private JPanel JPanelUserAddRemove;

    //UnterPanels für GUI
    private userControl mainUserControl;
    private cellarControlPanel mainCellarControlPanel;
    private cellarOptions mainCellarOptions;
    private userInfo mainUserInfo;
    private userPanel mainWishListuser;
    private adminPanel mainWishListadmin;
    private userAddRemovePanel mainUserAddRemove;




    private utils tools;
    private FilterTO filt;
    MouseListener mouseListenerBill;
    MouseListener mouseListenerUser;
    private int count = 0;
    private PersonTO loggedUser;
    private DrinkTO inventory[];
    private Clientcommunikation interf;
    private mainWindow me;


    /*
    * Autor: Hörkner
    * Beschreibung: Events des Hauptfensters
    * Implementiert: -Buchungsknopf(Tab: Buchen)
    * */
    public mainWindow(PersonTO user, Clientcommunikation interf) {
        loggedUser = user;
        me = this;
        tools = new utils();
        this.interf = interf;
        init();
        loadPanels();

        //getränke Buchen Tab Liste anlegen und in scrollpanel laden
        updateUI();
        //this.availableList.setColumnHeaderView(this.listview);

        setModal(true);
        /*
         * Autor: Hörkner
         * //----------------------------------------Reiter Buchen-----------------------------------
         * Beschreibung: Klick auf Bestellknopf
         * */
        ButtonOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldAmountOrder.getText().length() == 0){
                    JOptionPane.showMessageDialog(null,"Bitte die Anzahl des Getränks angeben!","Fehler",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if (Integer.parseInt(textFieldAmountInfo.getText().trim()) < Integer.parseInt(textFieldAmountOrder.getText().trim())) {
                        JOptionPane.showMessageDialog(null, "Bestand zu gering", "Fehler", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DrinkTO selected = tools.getDrinkByName(inventory, textFieldNameInfo.getText().trim());
                        //Neue Bestellung anlegen
                        if (interf.createOrderUser(loggedUser.id, loggedUser.password, selected.name, Integer.parseInt(textFieldAmountOrder.getText()), selected.bottleType) == true) {
                            updateUI();
                        } else {
                            JOptionPane.showMessageDialog(null, "Fehler bei Buchung", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        //Bei klick auf element in der liste Getraenke im Keller
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                String element = theList.getSelectedValue().toString();
                //System.out.println(element);
                textFieldAmountInfo.setText(tools.getAmountFromList(element));
                textFieldNameInfo.setText(tools.getNameFromList(element));
                textFieldPriceInfo.setText(tools.getPriceFromList(element));

            }
        };


        listview.addMouseListener(mouseListener);
        //BenutzerTab Klick auf Filter
        LabelFilter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                userFilter filterWindow = new userFilter(me);
                filterWindow.pack();
                filterWindow.setModal(true);
                filterWindow.setVisible(true);
            }
        });
        //Amount Feld
        textFieldAmountOrder.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try{
                    int amount = Integer.parseInt(textFieldAmountOrder.getText());
                    int price = getPriceFromTextBox();
                    amount *=price;
                    textFieldPriceOrder.setText(String.format("%d.%02d€",amount/100 ,amount%100));
                    textFieldPriceOrder.updateUI();
                }
                catch(NumberFormatException e1){
                    textFieldAmountOrder.setText("");
                    textFieldPriceOrder.setText("");
                    textFieldPriceOrder.updateUI();
                }
            }
        });
    }
    /*
    * Autor: Hörkner
    * ------------------------Reiter Buchen---------------------------------------------------
    * Beschreibung: fügt einen neuen Eintrag zur VerfügbarkeitsListe Hinzu und updatet diese
    * */

    //Füllt die GetränkeListe im Reiter Buchen
    private JList fillDrinkList(){

        inventory = interf.getInventory(tools.getCellarIDFromPerson(loggedUser));

        JList dest = new JList();
        dest.setFont(new Font("Courier New",Font.PLAIN,12));
        dest.setListData(inventory);
        return dest;
    }

    //Initialisiert alle Panels der GUI
    private void loadPanels(){
        //Setzt Hauptfenster
        setContentPane(contentPane);

        //Benutzer Fenster Laden
        this.mainUserControl = new userControl(interf,loggedUser);
        this.userControlPane.setLayout(this.mainUserControl.getLayout());
        this.userControlPane.add(this.mainUserControl.ContentPane);

        //Benutzer Hinzufügen Entfernen Laden
        this.mainUserAddRemove = new userAddRemovePanel();
        this.JPanelUserAddRemove.setLayout(mainUserAddRemove.getLayout());
        this.JPanelUserAddRemove.add(this.mainUserAddRemove.ContentPane);

        //Kellerverwaltung Laden
        this.mainCellarControlPanel = new cellarControlPanel();
        this.JPanelCellarControl.setLayout(this.mainCellarControlPanel.getLayout());
        this.JPanelCellarControl.add(this.mainCellarControlPanel.ContentPane);

        //Kelleroptionen laden
        this.mainCellarOptions = new cellarOptions();
        this.JPanelCellarOptions.setLayout(this.mainCellarOptions.getLayout());
        this.JPanelCellarOptions.add(this.mainCellarOptions.ContentPane);

        //Fenster für Benutzer Info Laden (meine Rechnung)
        mainUserInfo = new userInfo(interf,loggedUser);
        JPanelUserInfo.setLayout(mainUserInfo.getLayout());
        JPanelUserInfo.add(mainUserInfo.ContentPane);

        //Fenster als Admin oder User Laden
        if(loggedUser.admin) {
            mainWishListadmin = new adminPanel();
            JPanelDrinkWish.setLayout(mainWishListadmin.getLayout());
            JPanelDrinkWish.add(mainWishListadmin.ContentPane);
            tabbedPane1.setEnabledAt(3,true);
            tabbedPane1.setEnabledAt(4,true);
            loadUsers();
        }
        else {
            mainWishListuser = new userPanel();
            JPanelDrinkWish.setLayout(mainWishListuser.getLayout());
            JPanelDrinkWish.add(mainWishListuser.ContentPane);
            tabbedPane1.setEnabledAt(3,false);
            tabbedPane1.setEnabledAt(4,false);
        }
        loadUserBill();
    }

    private void updateUI(){
        listview = fillDrinkList();
        availableList.setViewportView(listview);
        availableList.updateUI();
        loadUserBill();

        if(loggedUser.admin){
            loadUsers();
        }
    }

    private int getPriceFromTextBox(){
        StringBuffer temp = new StringBuffer();
        String box = textFieldPriceInfo.getText();
        temp.append(box.substring(0,box.indexOf('.')));
        temp.append(box.substring(box.indexOf('.')+1).replace('€', ' '));
        int price = Integer.parseInt(temp.toString().trim());
        return price;
    }

    private void loadUserBill(){
        OrderTO[] orders = interf.getOrder(loggedUser.id);

        DefaultListModel listmodel = new DefaultListModel();
        if(orders != null) {

            for (OrderTO ord : orders) {
                listmodel.addElement(ord.getListEntry());
            }

            JList list = new JList(listmodel);
            list.addMouseListener(mouseListenerBill);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setVisibleRowCount(4);

            UserBillItems.setViewportView(list);
        }
    }

    private void loadUsers(){
        PersonTO[] users = interf.getUsers(loggedUser.id,loggedUser.password);
        if(users == null){
            return;
        }
        users = userfilter(users);
        DefaultListModel listmodel = new DefaultListModel();
        for( PersonTO user : users){
            listmodel.addElement(user.getUserString());
        }
        JList list = new JList(listmodel);
        list.addMouseListener(mouseListenerUser);
        list.setFont(new Font("Courier New",Font.BOLD,12));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPaneUser.setViewportView(list);

    }
    private PersonTO[] userfilter(PersonTO[] users){
        users = tools.filterBy(users,filt);
        switch (filt.sort){
            case ROOM:
                users = tools.SortByRoom(users);
                break;
            default:
                break;
        }
        if(filt.sortrising){
            users = tools.InvertArray(users);
        }
        return users;
    }
    public void setFilter(FilterTO filt){
        this.filt = filt;
        loadUsers();
    }
    public FilterTO getFilt(){
        return filt;
    }
    private void init(){
        filt = new FilterTO();
        mouseListenerUser = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                String element = theList.getSelectedValue().toString();
                mainUserControl.loadData(element);

            }
        };
        //Klick auf Order Liste
        mouseListenerBill = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                String element = theList.getSelectedValue().toString();

            }
        };
    }



}
