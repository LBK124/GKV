package gKVClient.MainWindow.CellarTab;



import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class cellarOptions extends javax.swing.JPanel {
    public JPanel ContentPane;
    private JButton buttonImportDelivery;
    private JButton buttonDokuments;
    private JButton buttonOptions;
    private JButton buttonNewBill;
    private JButton ButtonSendInfo;
    private JButton ButtonInventory;
    private JButton ButtonGenerateOrder;
    private JFileChooser fileChooser;

    public cellarOptions() {
        changeLanguage();
        //Klick auf Dokumente
        buttonDokuments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cellarDokuments dokuDialog = new cellarDokuments();
                dokuDialog.setSize(400,350);
                dokuDialog.setVisible(true);
            }
        });
        //Klick auf Lieferung Imprtieren
        buttonImportDelivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Automatisch","Manuell"};
                int res = JOptionPane.showOptionDialog(null,"Wie wollen Sie die Lieferung einbinden?",
                        "Lieferung Importieren",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null, options, options[0]);
                //Automatisch
                if (res == 0){
                    fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileFilter() {
                        @Override
                        public boolean accept(File f) {
                            return false;
                        }

                        @Override
                        public String getDescription() {
                            return null;
                        }
                    });
                    int desition = fileChooser.showDialog(null,"Importieren");
                }
                //Manuell
                else if( res == 1){
                    //öffnet eingabe Maske
                    String names[] = {"Bier","Cola","Fanta","Schnaps"};
                    cellarImportDeliveryManually importDialog = new cellarImportDeliveryManually(4, names);
                    importDialog.pack();
                    importDialog.setVisible(true);
                }
            }
        });
        //Klick auf Neue Rechnung
        buttonNewBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pdfViewer pdfDialog = new pdfViewer();
                pdfDialog.setSize(600,450);
                pdfDialog.setVisible(true);
            }
        });
        //Klick auf Optionen
        buttonOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cellarControlDialog dialog = new cellarControlDialog();
                dialog.setSize(750,450);
                dialog.setVisible(true);
            }
        });
        //Inventur
        ButtonInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dummy
                String name[] = {"Bier", "Schnaps", "Fanta","Cola"};
                int setpoint[] = {15,10,5,5};
                int actualpoint[] = {87,15,20,5};
                String bottle[] = {"Bier", "Schnaps", "Fanta","Cola"};
                cellarInventory inventoryDialog = new cellarInventory(4,name,setpoint,actualpoint,bottle);
                inventoryDialog.pack();
                inventoryDialog.setVisible(true);
            }
        });
        //Neue Bestellung generieren
        ButtonGenerateOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //Klick Info versenden
        ButtonSendInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cellarEmailLogin emailDialog = new cellarEmailLogin();
                emailDialog.pack();
                emailDialog.setVisible(true);
            }
        });
    }
    public void changeLanguage(){
        // Default locale is english, if there is polish we need to change JFileChooser
        // Java doesn't allow for easy changes of JFileChooser so this is quick hack
            UIManager.put("FileChooser.openButtonText","Öffnen");
            UIManager.put("FileChooser.cancelButtonText","Abbrechen");
            UIManager.put("FileChooser.saveButtonText","Speichern");
            UIManager.put("FileChooser.cancelButtonToolTipText", "Schließt Fenster");
            UIManager.put("FileChooser.saveButtonToolTipText", "Speichert Datei");
            UIManager.put("FileChooser.openButtonToolTipText", "Öffnet Datei");

            UIManager.put("FileChooser.lookInLabelText", "Suche in:");
            UIManager.put("FileChooser.fileNameLabelText", "Datei:");
            UIManager.put("FileChooser.filesOfTypeLabelText", "Dateityp:");
            UIManager.put("FileChooser.upFolderToolTipText", "nach Oben");
            UIManager.put("FileChooser.homeFolderToolTipText", "Home");
            UIManager.put("FileChooser.newFolderToolTipText", "Neuer Ordner");
            UIManager.put("FileChooser.listViewButtonToolTipText", "Liste");
            UIManager.put("FileChooser.detailsViewButtonToolTipText", "Details");



    }
}
