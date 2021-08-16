package gKVClient.MainWindow.CellarTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cellarControllDialogTreasur extends javax.swing.JPanel {
    public JPanel ContentPane;
    private JButton ButtonEditTreasur;
    private JTextField textField1;

    public cellarControllDialogTreasur() {
        ButtonEditTreasur.addActionListener(new ActionListener() {
            @Override
            //Klick eingaben Verwalten
            public void actionPerformed(ActionEvent e) {
                cellarEditTreasur treasurEdit = new cellarEditTreasur();
                treasurEdit.pack();
                treasurEdit.setVisible(true);
            }
        });
    }
}
