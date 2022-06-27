package progetto.view;

import progetto.Controller.ControllerPrenotazione;
import progetto.Session;
import progetto.elementiGrafici.PrenotazioneSingola;
import progetto.model.Prenotazione;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Classe PrenotazioniDipendente
 * Gestione della prenotazione da parte del dipendente
 */
public class PrenotazioniDipendente extends javax.swing.JPanel{
    public PrenotazioniDipendente() {
        initComponents();
    }
    private void initComponents() {
        JPanel prenotazioni = new JPanel();
        prenotazioni.setLayout(new BoxLayout(prenotazioni, BoxLayout.Y_AXIS));
        Collection<Prenotazione> listaPrenotazioni = new ControllerPrenotazione()
                .getPrenotazioniByIDFilmInSameDate(Session.getSessioneCorrente().getUtenteConesso().getId(),
                        Session.getSessioneCorrente().getIdRiferimentoFilm(),
                        new Date());
        Prenotazione tmpPrenotazione;
        System.out.println("prenotazioni dipendente");

        for (Iterator<Prenotazione> iterator = listaPrenotazioni.iterator(); iterator.hasNext(); ){
            tmpPrenotazione = iterator.next();
            JPanel j = new PrenotazioneSingola(tmpPrenotazione);
            j.setBorder(BorderFactory.createLineBorder(Color.black));
            prenotazioni.add(j);
            j.setOpaque(false);
        }
        jScrollPane1 = new javax.swing.JScrollPane(prenotazioni);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );
    }

    private javax.swing.JScrollPane jScrollPane1;
}
