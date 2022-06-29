
package progetto.view;

import progetto.Controller.ControllerFilm;
import progetto.Controller.ControllerPrenotazione;
import progetto.Session;
import progetto.elementiGrafici.*;
import progetto.model.Film;
import progetto.model.Prenotazione;
import progetto.model.Proiezione;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Classe VisualizzaPrenotazioni
 * Gestione delle prenotazioni, con la possibilità della modifica o eliminazione della prenotazione.
 * La classe funzionerà in maniera differente in base al tipo di utente connesso: utente o dipendente
 */
public class VisualizzaPrenotazioni extends javax.swing.JPanel {
    public VisualizzaPrenotazioni() {
        initComponents();
    }

    private void initComponents() {
        if(Session.getSessioneCorrente().getUtenteConesso().getTipo().equals("U") ){
            prenotazioniUtente();
        }else{
            prenotazioniDipendente();
        }
    }

    private void prenotazioniUtente(){
        JPanel prenotazioni = new JPanel();
        prenotazioni.setLayout(new BoxLayout(prenotazioni, BoxLayout.Y_AXIS));

        Collection<Prenotazione> listaPrenotazioni = new ControllerPrenotazione().getPrenotazioniByIDgeneratoreAfterDate(Session.getSessioneCorrente().getUtenteConesso().getId(), new Date());
        listaPrenotazioni = listaPrenotazioni.stream().sorted(Prenotazione::compareTo).toList();

        listaPrenotazioni.stream()
                .map(s -> generazionePrenotazione(s))
                .forEach(s -> prenotazioni.add(s));

        jScrollPane1 = new javax.swing.JScrollPane(prenotazioni);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );
    }

    private void prenotazioniDipendente(){
        JPanel panelloFilm = new JPanel();
        panelloFilm.setLayout(new BoxLayout(panelloFilm, BoxLayout.Y_AXIS));
        String idUtente = Session.getSessioneCorrente().getUtenteConesso().getId();
        Collection<String> listaFilm = new ControllerPrenotazione().getIdFilmByIdUtenteInADay(idUtente,new Date());
        Collection<Film> filmDisponibili = new ControllerFilm().getAllFilmsByIdList(listaFilm );
        int i = filmDisponibili.size();
        filmDisponibili.stream()
                .map(s -> generazioneFilm(s))
                .forEach(s -> panelloFilm.add(s));
        for (;i<4;i++){
            JPanel j = new FilmVuoto();
            panelloFilm.add(j);
        }

        jScrollPane1 = new javax.swing.JScrollPane(panelloFilm);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );
    }

    private JPanel generazionePrenotazione(Prenotazione prenotazioneDaGenerare){
        JPanel j = new PrenotazioneSingola(prenotazioneDaGenerare);
        j.setBorder(new MatteBorder(0,0,1,0, Color.gray));
        return j;
    }
    private JPanel generazioneFilm (Film filmDaGenerare){
        JPanel j = new FilmSingolo(filmDaGenerare);
        j.setBorder(new MatteBorder(0,0,1,0, Color.gray));
        return j;
    }
    private javax.swing.JScrollPane jScrollPane1;
}
