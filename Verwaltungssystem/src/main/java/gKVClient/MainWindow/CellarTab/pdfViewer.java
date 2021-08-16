package gKVClient.MainWindow.CellarTab;

// externe bibiothek PDFBox

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pdfViewer extends javax.swing.JDialog {
    private String path;

    private JPanel ContentPane;

    private JButton ButtonEdit;
    private JButton ButtonOkay;
    private JButton ButtonCancel;

    //------------------------------------Konstruktor------------------------------
    public pdfViewer(){
    setContentPane(ContentPane);
        //klick auf Okay
        ButtonOkay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //Klick auf Bearbeiten
        ButtonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //Klick auf Abbrechen
        ButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    //------------------------------------setter-----------------------------------
    public Boolean setPath(String path){
        this.path = path;
        return true;
    }
    //------------------------------------getter-----------------------------------
    public String getPath(){
        return path;
    }
}
