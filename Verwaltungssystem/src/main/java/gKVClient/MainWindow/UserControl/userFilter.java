package gKVClient.MainWindow.UserControl;

import gKVClient.MainWindow.mainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userFilter extends javax.swing.JDialog {
    private JPanel ContentPane;

    private JRadioButton RadioButtonRoom;
    private JRadioButton RadioButtonName;
    private JRadioButton RadioButtonSurname;
    private JRadioButton RadioButtonAccountVal;
    private JRadioButton RadioButtonUp;
    private JRadioButton RadioButtonDown;
    private JCheckBox CheckBoxKey;
    private JCheckBox CheckBoxInactive;
    private JCheckBox CheckBoxMovedOut;
    private JCheckBox CheckBoxAdmin;
    private JCheckBox CheckBoxAccVal;
    private JRadioButton RadioButtonBigger;
    private JRadioButton RadioButtonSmaler;
    private JTextField textFieldAccValue;
    private JPanel JPanelFilterAccValue;
    private JPanel JPanelSortOption;
    private JButton ButtonCancel;
    private JButton ButtonOkay;

    private mainWindow mw;

    public userFilter(mainWindow mw) {
        this.mw = mw;
        FilterTO filt = mw.getFilt();
        switch (filt.sort) {
            case NAME:
                RadioButtonName.setSelected(true);
                break;
            case BALANCE:
                RadioButtonAccountVal.setSelected(true);
                break;
            case SURNAME:
                RadioButtonSurname.setSelected(true);
                break;
            default:
                RadioButtonRoom.setSelected(true);
                break;
        }

        if(filt.sortrising){
            RadioButtonUp.setSelected(true);
        }
        else{
            RadioButtonDown.setSelected(true);
        }

        CheckBoxAdmin.setSelected(filt.admin);
        CheckBoxInactive.setSelected(filt.state);
        CheckBoxKey.setSelected(filt.key);
        CheckBoxMovedOut.setSelected(filt.movedout);
        CheckBoxAccVal.setSelected(filt.balance);
        textFieldAccValue.setEditable(CheckBoxAccVal.isSelected());
        RadioButtonBigger.setEnabled(CheckBoxAccVal.isSelected());
        RadioButtonSmaler.setEnabled(CheckBoxAccVal.isSelected());

        setContentPane(ContentPane);
        //Prüft ob Kontostands Filter ausgewählt wurde und aktiviert das dazugehörige Panel
        CheckBoxAccVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldAccValue.setEditable(CheckBoxAccVal.isSelected());
                RadioButtonBigger.setEnabled(CheckBoxAccVal.isSelected());
                RadioButtonSmaler.setEnabled(CheckBoxAccVal.isSelected());
                //JPanelFilterAccValue.setEnabled(CheckBoxAccVal.isEnabled());
            }
        });
        //Button Okay
        ButtonOkay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterTO filt = mw.getFilt();
                filt.admin = CheckBoxAdmin.isSelected();
                filt.key = CheckBoxKey.isSelected();
                filt.movedout = CheckBoxMovedOut.isSelected();
                filt.state = CheckBoxInactive.isSelected();
                if(CheckBoxAccVal.isSelected()){
                    filt.balance = true;
                    try{
                        filt.refbalance = Integer.parseInt(textFieldAccValue.getText());
                    }catch (NumberFormatException er){
                        filt.refbalance = 0;
                    }
                    filt.bigger = RadioButtonBigger.isSelected();
                }
                else{
                    filt.balance = false;
                }
                //Sortier optionen
                if(RadioButtonSurname.isSelected()){
                    filt.sort = FilterTO.SortBy.SURNAME;
                }else if(RadioButtonName.isSelected()){
                    filt.sort = FilterTO.SortBy.NAME;
                }else if(RadioButtonAccountVal.isSelected()){
                    filt.sort = FilterTO.SortBy.BALANCE;
                }else{
                    filt.sort = FilterTO.SortBy.ROOM;
                }
                filt.sortrising = RadioButtonUp.isSelected();
                mw.setFilter(filt);
                dispose();
            }
        });
        //Button Abbrechen
        ButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
