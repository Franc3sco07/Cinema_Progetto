package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Film;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ControllerFilm {
    private final String tableName = "film.csv";

    public Collection<Film> getAllFilms(){

        ArrayList<Film> films = new ArrayList<Film>();

        BufferedReader in = Gestione_db.getTable(tableName);
       // films =   in.lines().parallel().map(s -> stringToFilm(s)).collect();
        try {
            String l;
            while ((l = in.readLine()) != null) {
                films.add(stringToFilm( l ));
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}

        return films;
    }

    public Collection<Film> getAllFilmsByIdList (Collection<String> idFilms){
        ArrayList<Film> films = new ArrayList<Film>();
        BufferedReader in = Gestione_db.getTable(tableName);
        Film tmp;
        try {
            String l;
            while ((l = in.readLine()) != null) {
                tmp = stringToFilm(l);
                if (idFilms.contains(tmp.getId())){
                    films.add(tmp);
                }

            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}
        return films;
    }

    public Film getFilmByID(String IDfilm){
        String stringaFilm = Gestione_db.getRow(tableName, IDfilm);

        String[] datiFilm = stringaFilm.split(",");
        if (datiFilm.length > 1) {
            return stringToFilm( stringaFilm );
        }

        return null;
    }

    public Collection<String> getAllFilmNameAndId(){
        ArrayList<String> films = new ArrayList<>();
        BufferedReader in = Gestione_db.getTable(tableName);
        Film tmp;
        try {
            String l;
            while ((l = in.readLine()) != null) {
                    tmp = stringToFilm(l);
                    films.add(tmp.getId()+", "+tmp.getNome());
                }

            }catch (FileNotFoundException e){}
        catch (IOException e){}
        return films;
    }

    public String insertFilm(String film){
        return Gestione_db.insertRow(tableName, film);
    }

    public String deleteFilmByID(String IDfilm) {
        return Gestione_db.deleteRow(IDfilm, tableName);
    }

    public String modifyFilm(Film filmModificato){ return Gestione_db.modifyRow(filmModificato.getId(), tableName, filmModificato.toString() ); }

    private Film stringToFilm(String filmString){
        String[] datiFilm = filmString.split(",");
        return new Film(datiFilm[0], datiFilm[1], datiFilm[2], datiFilm[3], datiFilm[4]);
    }
}
