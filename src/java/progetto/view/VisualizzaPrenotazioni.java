
package progetto.view;

import progetto.Controller.ControllerFilm;
import progetto.Controller.ControllerPrenotazione;
import progetto.Session;
import progetto.elementiGrafici.*;
import progetto.model.Film;
import progetto.model.Prenotazione;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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
        Prenotazione tmpPrenotazione;
        for (Iterator<Prenotazione> iterator = listaPrenotazioni.iterator(); iterator.hasNext(); ){
            tmpPrenotazione = iterator.next();
            JPanel j = new PrenotazioneSingola(tmpPrenotazione);
            prenotazioni.add(j);
            j.setBorder(new MatteBorder(0,0,1,0, Color.gray));
        }
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
        int i = 0;

        Film tmpFilm;
        for (Iterator<Film> iterator = filmDisponibili.iterator(); iterator.hasNext(); ){
            tmpFilm = iterator.next();
            JPanel j = new FilmSingolo(tmpFilm);
            panelloFilm.add(j);
            j.setOpaque(false);
            i++;
        }
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
    private javax.swing.JScrollPane jScrollPane1;
}
