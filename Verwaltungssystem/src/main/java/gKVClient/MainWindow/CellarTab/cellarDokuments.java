package gKVClient.MainWindow.CellarTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cellarDokuments extends javax.swing.JDialog {
    private JPanel ContentPane;
    private JTree tree1;
    private JButton ButtonOkay;
    private JButton ButtonDownload;
    private JButton ButtonUpload;
    private JButton ButtonShow;
    private JButton ButtonDelete;

    public cellarDokuments() {
        setContentPane(ContentPane);
        //Klick auf Anzeigen
        ButtonShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pdfViewer pdfDialog = new pdfViewer();
                pdfDialog.setSize(600,450);
                pdfDialog.setVisible(true);
            }
        });
        //Klick auf Upload
        ButtonUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //Klick auf Download
        ButtonDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //Klick auf Okay
        ButtonOkay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
