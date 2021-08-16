package gKVClient.MainWindow.UserTab;

import data.PersonTO;
import gKVClient.Clientcommunikation.Clientcommunikation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userInfo extends javax.swing.JPanel {

    private PersonTO loggedUser;
    private Clientcommunikation interf;

    public userInfo(Clientcommunikation interf, PersonTO loggedUser){
        this.interf = interf;
        this.loggedUser = loggedUser;

        CheckBoxKey.setSelected(loggedUser.key);
        CheckBoxInactive.setSelected(!loggedUser.state);

        textFieldUserID.setText(loggedUser.id);
        textFieldUserAccountValue.setText(String.format("%d.%02d€",loggedUser.balance/100,loggedUser.balance%100));

        ButtonAskForStatusChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interf.setUserState(loggedUser.id);
            }
        });
    }
    public JPanel ContentPane;

    private JTextField textFieldUserID;
    private JTextField textFieldUserAccountValue;
    private JCheckBox CheckBoxInactive;
    private JButton ButtonAskForStatusChange;
    private JCheckBox CheckBoxKey;

}
