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
import progetto.elementiGrafici.ProiezioneVuota;
import progetto.model.Proiezione;
import progetto.state.FilmState;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Classe VisualizzaProiezioni
 * Gestione della visualizzazione delle proiezioni e prenotazione dei posti per la proiezione;
 */
public class VisualizzaProiezioni extends javax.swing.JPanel {


    public VisualizzaProiezioni() {
        initComponents();
    }

    private void initComponents() {

            JPanel infoPannello = new JPanel();
            infoPannello.setLayout(new BoxLayout(infoPannello, BoxLayout.Y_AXIS));
            Date oggi = new Date();
            Collection<Proiezione> listaProiezione;

            if(!Session.getSessioneCorrente().getUtenteLoggato().getTipo().equals("D")){

                listaProiezione = new ControllerProiezione().getAllProiezioneByIdFilmAfterDate(Session.getSessioneCorrente().getIdRiferimentoFilm(),oggi);
            }else{

                listaProiezione = new ControllerProiezione().getProiezioneByIDFilmInADay(Session.getSessioneCorrente().getIdRiferimentoFilm(),oggi);
            }

            listaProiezione = listaProiezione.stream().sorted(Proiezione::compareTo).collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
            Proiezione tmpProiezione;
            int i = 0;
            for (Iterator<Proiezione> iterator = listaProiezione.iterator(); iterator.hasNext(); ){

                tmpProiezione = iterator.next();
                JPanel j = new ProiezioneSingola(tmpProiezione);
                infoPannello.add(j);
                j.setBorder(new MatteBorder(0,0,1,0, Color.gray));
                j.setOpaque(false);
                i++;
            }
            for(;i<7;i++){

                JPanel j = new ProiezioneVuota();
                infoPannello.add(j);
            }

            jScrollPane1 = new javax.swing.JScrollPane(infoPannello);
            jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            jButton1 = new javax.swing.JButton();
            jButton1.setText("Indietro");
            jButton1.setToolTipText("Ritorna ai film");
            jButton1.addActionListener(evt -> new FilmState().doAction(Main.context));

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
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
}
