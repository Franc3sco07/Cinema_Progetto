package cinema.view.film;

import cinema.controller.ControllerFilm;
import cinema.controller.ControllerProiezione;
import cinema.Session;
import cinema.elementiGrafici.film.FilmSingolo;
import cinema.elementiGrafici.film.FilmVuoto;
import cinema.model.Film;
import cinema.model.TipiUtente;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Stream;

/**
 * Classe FilmView
 * Gestione della visualizzazione dei film disponibili
 */

public class FilmView extends javax.swing.JPanel {

    private javax.swing.JScrollPane jScrollPane1;

    public FilmView() {
        initComponents();
    }

    private void initComponents() {
        JPanel infoPannello = new JPanel();
        infoPannello.setLayout(new BoxLayout(infoPannello, BoxLayout.Y_AXIS));
        Date oggi = new Date();

        Collection<Film> filmDisponibili;
        //differenzia i film da fare visualizzare in base al tipo di utente conesso
        if (Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.DIPENDENTE.tipo)) {
            Collection<String> idFilm = new ControllerProiezione().getAllIdFilmInADay(oggi);
            filmDisponibili = new ControllerFilm().getAllFilmsByIdList(idFilm);
        } else {
            Collection<String> idFilm = new ControllerProiezione().getAllIdFilmAfterDate(oggi);
            filmDisponibili = new ControllerFilm().getAllFilmsByIdList(idFilm);
        }
        //generazione in grafica dei dei vari film
        filmDisponibili.stream()
                .sorted(Film::compareTo)
                .map(s -> generazioneFilm(s))
                .forEach(s -> infoPannello.add(s));

        int i = 3 - filmDisponibili.size() ;
        if (i>0){
            Stream.generate(FilmVuoto::new)
                    .limit(i)
                    .forEach(s ->infoPannello.add(s));
        }



        jScrollPane1 = new javax.swing.JScrollPane(infoPannello);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );

    }

    private JPanel generazioneFilm(Film filmDaGenerare) {
        JPanel j = new FilmSingolo(filmDaGenerare);
        j.setBorder(new MatteBorder(0, 0, 1, 0, Color.gray));
        return j;
    }

}
