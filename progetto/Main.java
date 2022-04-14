package progetto;

import progetto.Controller.ControllerFilm;
import progetto.database.Gestione_db;
import progetto.model.Film;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        ControllerFilm f = new ControllerFilm();
        Collection<Film> film = f.getAllFilms();

        for (Iterator<Film> iterator = film.iterator(); iterator.hasNext(); ){
            System.out.println(iterator.next());
        }

        System.out.println(f.deleteFilmByID("202"));

        film = f.getAllFilms();
        for (Iterator<Film> iterator = film.iterator(); iterator.hasNext(); ){
            System.out.println(iterator.next());
        }

    }
}
