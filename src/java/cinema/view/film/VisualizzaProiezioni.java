/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.view.film;

import cinema.controller.ControllerProiezione;
import cinema.Main;
import cinema.Session;
import cinema.elementiGrafici.proiezione.ProiezioneSingola;
import cinema.elementiGrafici.proiezione.ProiezioneVuota;
import cinema.model.Proiezione;
import cinema.model.TipiUtente;
import cinema.state.film.FilmState;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Stream;

/**
 * Classe VisualizzaProiezioni
 * Gestione della visualizzazione delle proiezioni e prenotazione dei posti per la proiezione;
 */
public class VisualizzaProiezioni extends javax.swing.JPanel {

    private javax.swing.JButton indietro;
    private javax.swing.JScrollPane panelloScorrimento;

    public VisualizzaProiezioni() {
        initComponents();
    }

    private void initComponents() {

        JPanel infoPannello = new JPanel();
        infoPannello.setLayout(new BoxLayout(infoPannello, BoxLayout.Y_AXIS));
        Date oggi = new Date();
        Collection<Proiezione> listaProiezioni;

        if (!Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.DIPENDENTE.tipo)) {

            listaProiezioni = new ControllerProiezione().getAllProiezioneByIdFilmAfterDate(Session.getSessioneCorrente().getIdRiferimentoFilm(), oggi);

        } else {

            listaProiezioni = new ControllerProiezione().getProiezioneByIDFilmInADay(Session.getSessioneCorrente().getIdRiferimentoFilm(), oggi);
        }


        listaProiezioni.stream().sorted(Proiezione::compareTo)
                .map(x -> generatoreProiezioni(x))
                .forEach(x -> infoPannello.add(x));
        int i = 7 - listaProiezioni.size() ;
        if (i>0){
            Stream.generate(ProiezioneVuota::new)
                    .limit(i)
                    .forEach(s ->infoPannello.add(s));
        }


        panelloScorrimento = new javax.swing.JScrollPane(infoPannello);
        panelloScorrimento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        indietro = new javax.swing.JButton();
        indietro.setText("Indietro");
        indietro.setToolTipText("Ritorna ai film");
        indietro.addActionListener(evt -> new FilmState().doAction(Main.context));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelloScorrimento, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(indietro)))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelloScorrimento, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(indietro)
                                .addContainerGap())
        );
    }

    private JPanel generatoreProiezioni(Proiezione proiezione) {
        JPanel j = new ProiezioneSingola(proiezione);
        j.setBorder(new MatteBorder(0, 0, 1, 0, Color.gray));
        return j;
    }
}
