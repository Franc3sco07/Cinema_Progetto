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

        Film f1 = f.getFilmByID("203");
        System.out.println("id 203" + f1);

        Film f2 = new Film("201", "Francesco", "arancia.png", "bello", "7.50");
        System.out.println("modifica: " + f.modifyFilm(f2));

        String prova = "Francesco, prova.png, bellissimo, 90.90";
        System.out.println("Inserimento: " + f.insertFilm(prova));

        System.out.println("eliminazione: " + f.deleteFilmByID("204"));

        film = f.getAllFilms();
        for (Iterator<Film> iterator = film.iterator(); iterator.hasNext(); ){
            System.out.println(iterator.next());
        }

    }
}
