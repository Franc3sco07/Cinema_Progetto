package cinema.view.prenotazione;

import cinema.controller.ControllerFilm;
import cinema.controller.ControllerPrenotazione;
import cinema.Session;
import cinema.elementiGrafici.PrenotazioneSingola;
import cinema.elementiGrafici.film.FilmSingolo;
import cinema.elementiGrafici.film.FilmVuoto;
import cinema.model.Film;
import cinema.model.Prenotazione;
import cinema.model.TipiUtente;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Stream;

/**
 * Classe VisualizzaPrenotazioni
 * Gestione delle prenotazioni, con la possibilità della modifica o eliminazione della prenotazione.
 * La classe funzionerà in maniera differente in base al tipo di utente connesso: utente o dipendente
 */
public class VisualizzaPrenotazioni extends javax.swing.JPanel {
    private javax.swing.JScrollPane panelloScorrimento;

    public VisualizzaPrenotazioni() {
        initComponents();
    }

    private void initComponents() {
        if (Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.UTENTE.tipo)) {
            prenotazioniUtente();
        } else {
            prenotazioniDipendente();
        }
    }

    private void prenotazioniUtente() {
        JPanel prenotazioni = new JPanel();
        prenotazioni.setLayout(new BoxLayout(prenotazioni, BoxLayout.Y_AXIS));

        Collection<Prenotazione> listaPrenotazioni = new ControllerPrenotazione().getPrenotazioniByIDgeneratoreAfterDate(Session.getSessioneCorrente().getUtenteConesso().getId(), new Date());
        listaPrenotazioni = listaPrenotazioni.stream().sorted(Prenotazione::compareTo).toList();

        listaPrenotazioni.stream()
                .map(s -> generazionePrenotazione(s))
                .forEach(s -> prenotazioni.add(s));

        panelloScorrimento = new javax.swing.JScrollPane(prenotazioni);
        panelloScorrimento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelloScorrimento, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelloScorrimento, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );
    }

    private void prenotazioniDipendente() {
        JPanel panelloFilm = new JPanel();
        panelloFilm.setLayout(new BoxLayout(panelloFilm, BoxLayout.Y_AXIS));
        String idUtente = Session.getSessioneCorrente().getUtenteConesso().getId();
        Collection<String> listaFilm = new ControllerPrenotazione().getIdFilmByIdUtenteInADay(idUtente, new Date());
        Collection<Film> filmDisponibili = new ControllerFilm().getAllFilmsByIdList(listaFilm);
        filmDisponibili.stream()
                .map(s -> generazioneFilm(s))
                .forEach(s -> panelloFilm.add(s));

        int i = 4 - filmDisponibili.size() ;
        if (i>0){
            Stream.generate(FilmVuoto::new)
                    .limit(i)
                    .forEach(s ->panelloFilm.add(s));
        }

        panelloScorrimento = new javax.swing.JScrollPane(panelloFilm);
        panelloScorrimento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelloScorrimento, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelloScorrimento, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );
    }

    private JPanel generazionePrenotazione(Prenotazione prenotazioneDaGenerare) {
        JPanel j = new PrenotazioneSingola(prenotazioneDaGenerare);
        j.setBorder(new MatteBorder(0, 0, 1, 0, Color.gray));
        return j;
    }

    private JPanel generazioneFilm(Film filmDaGenerare) {
        JPanel j = new FilmSingolo(filmDaGenerare);
        j.setBorder(new MatteBorder(0, 0, 1, 0, Color.gray));
        return j;
    }
}
