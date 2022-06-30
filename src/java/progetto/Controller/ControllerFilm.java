package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Film;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Classe ControllerFilm
 * Implementazione di metodi per gestire i film
 */

public class ControllerFilm {
    private final String tableName = "film.csv";


    /**
     * Funzione che data una collezione di idFilm, restituisce una collezione con i film corrispondenti agli id
     *
     * @param idFilms collezione con gli id dei film che cerchiamo
     * @return una collezione con i film cercati
     */
    public Collection<Film> getAllFilmsByIdList(Collection<String> idFilms) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Film.stringToFilm(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(x -> idFilms.contains(x.getId()))
                    .toList();
        } else {
            return new ArrayList<>();
        }

    }

    /**
     * Funzione che dato un id di un film, restituisce il film corrispettivo
     *
     * @param IDfilm id del film che si vuole cercare
     * @return il film richiesto
     */
    public Optional<Film> getFilmByID(String IDfilm) {
        String stringaFilm = Gestione_db.getRow(tableName, IDfilm);
        return Film.stringToFilm(stringaFilm);
    }

    /**
     * Restituisce tutti gli id e i nomi dei film presenti nel database
     *
     * @return una collezione di stringhe in formato "id,NomeFilm"
     */
    public Collection<String> getAllFilmNameAndId() {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Film.stringToFilm(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .map(s -> s.getId() + "," + s.getNome())
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Data una stringa con tutte le informazioni del film, procederà con l'inserimento del db
     *
     * @param film stringa con informazioni del film da aggiungere
     * @return messaggio di conferma, se è stato inserito ritorna l'id dell'elemento
     */
    public String insertFilm(String film) {
        return Gestione_db.insertRow(tableName, film);
    }

    /**
     * Funzione che dato l'id di un film, procederà all'eliminazione del film dal database
     *
     * @param IDfilm l'id del film da eliminare
     * @return messaggio di conferma
     */
    public String deleteFilmByID(String IDfilm) {
        return Gestione_db.deleteRow(IDfilm, tableName);
    }


}
