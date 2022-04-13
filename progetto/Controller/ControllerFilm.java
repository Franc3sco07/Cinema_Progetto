package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Film;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ControllerFilm {
    private final String tableName = "Film.csv";

    public Collection<Film> getAllFilms(){
        Gestione_db db = new Gestione_db();

        ArrayList<Film> films = new ArrayList<Film>();

        BufferedReader in = db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                String[] datiFilm = l.split(",");
                films.add(new Film(datiFilm[0], datiFilm[1], datiFilm[2], datiFilm[3], datiFilm[4]));
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}

        return films;
    }

    public String getFilmByID(String IDfilm){
        return null;
    }

    public String insertFilm(String film){
        return null;
    }

    public String deleteFilmByID(String IDfilm){
        return null;
    }

    private String modifyFilm(Film filmModificato){
        return null;
    }
}
