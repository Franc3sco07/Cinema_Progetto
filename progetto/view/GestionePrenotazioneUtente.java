/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package progetto.view;

import progetto.Controller.ControllerPrenotazione;
import progetto.Controller.ControllerProiezione;
import progetto.Main;
import progetto.Session;
import progetto.functions.TraduttoreMatrice;
import progetto.functions.TrasformatoreArrayList;
import progetto.model.Prenotazione;
import progetto.model.Proiezione;
import progetto.state.FilmState;
import progetto.state.ProiezioneState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author francesco
 */
public class GestionePrenotazioneUtente extends javax.swing.JPanel {
    private ArrayList postiSelezionati = new ArrayList();
    private String imgPath = "progetto/elementiGrafici/poltrona2.png";
    private Color selezionato = new Color(0,125,0);
    private ImageIcon icon  ;
    private final int panel_lunghezza = 770; // 800
    private final int panel_altezza = 357; // 450

    /**
     * Creates new form postiCinema
     */
    public GestionePrenotazioneUtente() {

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {


        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        String proizioneId = Session.getSessioneCorrente().getIdRiferimentoProiezione();
        int posti[][] = new ControllerProiezione().getProezioneByID(proizioneId).getPostiAttualiOccupati();

        int righe = posti.length;
        int colonne = posti[0].length;
        icon = new ImageIcon(imgPath);
        Image image = icon.getImage();
        int lunghezza_img = panel_lunghezza/(colonne);
        int altezza_img =  panel_altezza/(righe);

        Image newIcon;
        int altezza_gap= 0;
        int lunghezza_gap =0 ;
        int lunghezza_finale;
        int altezza_finale;
        if(lunghezza_img < altezza_img){
            lunghezza_finale = lunghezza_img*colonne;
            altezza_finale = lunghezza_img*righe;

            newIcon = image.getScaledInstance(lunghezza_img, lunghezza_img,  java.awt.Image.SCALE_SMOOTH);
        }else{
            lunghezza_finale = altezza_img*colonne;
            altezza_finale = altezza_img*righe;

            newIcon = image.getScaledInstance(altezza_img, altezza_img,  java.awt.Image.SCALE_SMOOTH);
        }



        icon = new ImageIcon(newIcon);
        jPanel1 = new javax.swing.JPanel(new GridLayout(righe,colonne));
        jPanel1.setSize(lunghezza_finale,altezza_finale);
        //JPanel posti = new JPanel(new GridLayout(righe,colonne));
        JButton tmp ;
        for(int x=0; x<righe;x++){
            for(int y=0;y<colonne;y++){
                tmp = new JButton(icon);
                tmp.setSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()));
                //tmp.setContentAreaFilled(false); fa diventare il bottone trasparente
                tmp.setBorder(null);
                //tmp.setIcon(icon);
                //tmp.setBorderPainted(false);

                if(posti[x][y] == 0){
                    tmp.setVisible(false);
                }else if (posti[x][y] == 2){
                    tmp.setBackground(Color.RED);
                    tmp.setEnabled(false);
                    tmp.setToolTipText("Posto occupato");
                }else{
                    tmp.setVisible(true);
                    tmp.setName(x+":"+y);
                    tmp.setToolTipText("Seleziona posto "+x+","+y);
                    tmp.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {

                            JButton source = (JButton) evt.getSource();
                            if(source.getBackground()!=selezionato){
                                String name = source.getName();
                                postiSelezionati.add(name+";");
                                int numbers[][] = TraduttoreMatrice.stringToMatrice(name) ;
                                posti[numbers[0][0]][numbers[0][1]] = 2;

                                if(!jButton1.isEnabled()){
                                    jButton1.setToolTipText(null);
                                    jButton1.setEnabled(true);
                                }
                                source.setBackground(selezionato);
                                source.setToolTipText("Annulla selezione");
                            }else{
                                String name = source.getName();
                                postiSelezionati.remove(name+";");
                                int numbers[][] = TraduttoreMatrice.stringToMatrice(name) ;
                                posti[numbers[0][0]][numbers[0][1]] = 1;
                                source.setBackground(null);
                                source.setToolTipText("Seleziona posto "+source.getName().replaceAll(":",","));


                                if(postiSelezionati.size() < 1){
                                    jButton1.setToolTipText("Seleziona un posto prima di continuare");
                                    jButton1.setEnabled(false);
                                }
                            }

                        }
                    });
                }

                jPanel1.add(tmp);
            }
        }

        jButton1.setText("Prenota");
        jButton1.setEnabled(false);
        jButton1.setToolTipText("Seleziona un posto prima di continuare");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(postiSelezionati.size()>0){
                    //System.out.println(TrasformatoreArrayList.arrayListToStringMat(postiSelezionati));
                    //idGeneratore, idProiezione, idFilm, postoAssegnato
                    String prenotazione = Session.getSessioneCorrente().getUtenteLoggato().getId()
                            + "," + Session.getSessioneCorrente().getIdRiferimentoProiezione()
                            + "," + Session.getSessioneCorrente().getIdRiferimentoFilm()
                            + "," + TrasformatoreArrayList.arrayListToStringMat(postiSelezionati);
                    new ControllerPrenotazione().insertPrenotazione(prenotazione);

                    Proiezione modified = new ControllerProiezione().getProezioneByID(Session.getSessioneCorrente().getIdRiferimentoProiezione());
                    modified.setPostiAttualiOccupati(posti);
                    modified.setPostiLiberi(modified.getPostiLiberi()-postiSelezionati.size());
                    new ControllerProiezione().modifyProiezione(modified);


                    JOptionPane.showMessageDialog(null, "Prenotazione effettuata con sucesso!\n Verrai reindirizzato al catalogo film");
                    new FilmState().doAction(Main.context);
                } //else {
                  //  JOptionPane.showMessageDialog(null, "Seleziona un posto desiderato");
                //}

            }
        });

        jButton2.setText("Indietro");
        jButton2.setToolTipText("Ritorna alle proiezioni");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new ProiezioneState().doAction(Main.context);
            }
        });

        JPanel jPanel2 = new JPanel();
        jPanel2.setSize(panel_lunghezza,panel_altezza);
        jPanel2.add(jPanel1,CENTER_ALIGNMENT);
        jPanel2.setVisible(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, panel_lunghezza, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, panel_altezza, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
