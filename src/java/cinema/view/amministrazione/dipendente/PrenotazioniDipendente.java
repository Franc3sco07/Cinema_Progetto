package cinema.view.amministrazione.dipendente;

import cinema.controller.ControllerPrenotazione;
import cinema.Session;
import cinema.elementiGrafici.PrenotazioneSingola;
import cinema.model.Prenotazione;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Collection;
import java.util.Date;

/**
 * Classe PrenotazioniDipendente
 * Gestione della prenotazione da parte del dipendente
 */
public class PrenotazioniDipendente extends javax.swing.JPanel {
    private javax.swing.JScrollPane panelloScorrimento;

    public PrenotazioniDipendente() {
        initComponents();
    }

    private void initComponents() {
        JPanel prenotazioni = new JPanel();
        prenotazioni.setLayout(new BoxLayout(prenotazioni, BoxLayout.Y_AXIS));
        Collection<Prenotazione> listaPrenotazioni = new ControllerPrenotazione()
                .getPrenotazioniByIDFilmInADay(Session.getSessioneCorrente().getUtenteConesso().getId(),
                        Session.getSessioneCorrente().getIdRiferimentoFilm(),
                        new Date())
                .stream().sorted(Prenotazione::compareTo).toList();
        listaPrenotazioni.stream()
                .map(s -> generaPrenotazione(s))
                .forEach(s -> prenotazioni.add(s));
        panelloScorrimento = new javax.swing.JScrollPane(prenotazioni);
        panelloScorrimento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelloScorrimento, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelloScorrimento, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );
    }

    private JPanel generaPrenotazione(Prenotazione pr) {
        JPanel j = new PrenotazioneSingola(pr);
        j.setBorder(new MatteBorder(0, 0, 1, 0, Color.gray));
        return j;
    }
}
