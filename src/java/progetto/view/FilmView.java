
package progetto.view;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import progetto.Controller.ControllerFilm;
import progetto.Controller.ControllerProiezione;
import progetto.Session;
import progetto.elementiGrafici.FilmSingolo;
import progetto.elementiGrafici.FilmVuoto;
import progetto.model.Film;

import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


public class FilmView extends javax.swing.JPanel {

    public FilmView() {
        initComponents();
    }

    private void initComponents() {
        JPanel infoPannello = new JPanel();
        infoPannello.setLayout(new BoxLayout(infoPannello, BoxLayout.Y_AXIS));
        Date oggi = new Date();

        Collection<Film> filmDisponibili;
        //differenzia i film da fare visualizzare in base al tipo di utente conesso
        if(Session.getSessioneCorrente().getUtenteLoggato().getTipo().equals("D")){
            Collection<String> idFilm = new ControllerProiezione().getAllIdFilmInADay(oggi);
            filmDisponibili = new ControllerFilm().getAllFilmsByIdList(idFilm);
        }else{
            Collection<String> idFilm = new ControllerProiezione().getAllIdFilmAfterDate(oggi);
            filmDisponibili = new ControllerFilm().getAllFilmsByIdList(idFilm);
        }
        Film tmpFilm;
        int i = 0;
        //generazione in grafica dei dei vari film
        for (Iterator<Film> iterator = filmDisponibili.iterator(); iterator.hasNext(); ){
            tmpFilm = iterator.next();
            JPanel j = new FilmSingolo(tmpFilm);
            infoPannello.add(j);
            j.setOpaque(false);
            i++;
            j.setBorder(new MatteBorder(0,0,1,0, Color.gray));
        }
        for (;i<4;i++){
            JPanel j = new FilmVuoto();
            infoPannello.add(j);
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


    private javax.swing.JScrollPane jScrollPane1;

}
