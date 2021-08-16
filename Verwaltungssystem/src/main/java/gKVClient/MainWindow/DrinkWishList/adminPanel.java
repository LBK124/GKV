package gKVClient.MainWindow.DrinkWishList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gKVClient.MainWindow.CellarTab.addDrinkDialog;

public class adminPanel extends javax.swing.JPanel{
    public JPanel ContentPane;

    private JTextField textFieldDrinkName;
    private JTextField textFieldBottlePrice;
    private JTextField textFieldPackAmount;
    private JTextField textFieldBottleVol;
    private JButton ButtonDeleteWish;
    private JButton ButtonAddDrink;

    public adminPanel() {
        ButtonAddDrink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDrinkDialog add = new addDrinkDialog();
                add.setSize(400,300);
                add.setModal(true);
                add.setVisible(true);
            }
        });
    }
}
