/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto.view;

import progetto.Controller.ControllerProiezione;
import progetto.Main;
import progetto.Session;
import progetto.elementiGrafici.ProiezioneSingola;
import progetto.model.Proiezione;
import progetto.state.FilmState;

import javax.swing.*;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author franc
 */
public class VisualizzaProiezioni extends javax.swing.JPanel {

    /**
     * Creates new form VisualizzaProiezioni
     */
    public VisualizzaProiezioni() {
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
        if(Session.getSessioneCorrente().getUtenteLoggato().getTipo().equals("U")){
            proiezioneUtente();
        }else{
            proiezioneDipedente();
        }

    }// </editor-fold>//GEN-END:initComponents

    private void proiezioneUtente(){

        JPanel infoPannello = new JPanel();
        infoPannello.setLayout(new BoxLayout(infoPannello, BoxLayout.Y_AXIS));
        Date oggi = new Date();
        Collection<Proiezione> listaProiezione = new ControllerProiezione().getAllProiezioneByIdFilmAfterDate(Session.getSessioneCorrente().getIdRiferimentoFilm(),oggi);
        Proiezione tmpProiezione;

        for (Iterator<Proiezione> iterator = listaProiezione.iterator(); iterator.hasNext(); ){
            tmpProiezione = iterator.next();
            JPanel j = new ProiezioneSingola(tmpProiezione);
            infoPannello.add(j);
            j.setOpaque(false);
        }
        jScrollPane1 = new javax.swing.JScrollPane(infoPannello);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jButton1 = new javax.swing.JButton();

        jButton1.setText("Indietro");
        jButton1.setToolTipText("Ritorna ai film");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new FilmState().doAction(Main.context);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButton1)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addContainerGap())
        );
    }

    private void proiezioneDipedente(){

        JPanel infoPannello = new JPanel();
        infoPannello.setLayout(new BoxLayout(infoPannello, BoxLayout.Y_AXIS));
        Date oggi = new Date();
        Collection<Proiezione> listaProiezione = new ControllerProiezione().getProiezioneByIDFilmInADay(Session.getSessioneCorrente().getIdRiferimentoFilm(),oggi);
        Proiezione tmpProiezione;

        for (Iterator<Proiezione> iterator = listaProiezione.iterator(); iterator.hasNext(); ){
            tmpProiezione = iterator.next();
            JPanel j = new ProiezioneSingola(tmpProiezione);
            infoPannello.add(j);
            j.setOpaque(false);
        }
        jScrollPane1 = new javax.swing.JScrollPane(infoPannello);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jButton1 = new javax.swing.JButton();

        jButton1.setText("Indietro");
        jButton1.setToolTipText("Ritorna ai film");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new FilmState().doAction(Main.context);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButton1)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addContainerGap())
        );

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}