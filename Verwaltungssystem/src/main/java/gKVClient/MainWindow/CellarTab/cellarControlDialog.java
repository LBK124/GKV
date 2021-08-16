package gKVClient.MainWindow.CellarTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cellarControlDialog extends javax.swing.JDialog{
    private JPanel ContentPane;

    private JButton ButtonOpenClose;
    private JPanel JPanelDrinkInfo;
    private JPanel JPanelCellarAttributes;
    private JPanel JPanelCellarTreasur;

    private cellarControlDialogDrink panelDrink;
    private cellarControlDialogAtribute panelAttrib;
    private cellarControllDialogTreasur panelTreas;

    private Boolean state;

    public cellarControlDialog () {
        setContentPane(ContentPane);
        state = false;
        loadPanels();

        //Klick auf Keller Öffnen/Schließen
        ButtonOpenClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(state){
                    ButtonOpenClose.setText("Keller Öffnen");
                    state = false;
                }
                else {
                    ButtonOpenClose.setText("Keller Schließen");
                    state = true;
                }
            }
        });
    }
    //Lädt die externen Panels Initialisiert den Tab
    private void loadPanels(){
        //Initialisiert das Panel Getränke Info
        panelDrink = new cellarControlDialogDrink();
        JPanelDrinkInfo.setLayout(panelDrink.getLayout());
        JPanelDrinkInfo.add(panelDrink.ContentPane);

        //Initialisiert das Panel Keller Verwaltung
        panelAttrib = new cellarControlDialogAtribute(this);
        JPanelCellarAttributes.setLayout(panelAttrib.getLayout());
        JPanelCellarAttributes.add(panelAttrib.ContenetPane);

        //Initialisiert das Panel Kellerkasse
        panelTreas = new cellarControllDialogTreasur();
        JPanelCellarTreasur.setLayout(panelTreas.getLayout());
        JPanelCellarTreasur.add(panelTreas.ContentPane);

    }
}
