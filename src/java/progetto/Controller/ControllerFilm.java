package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Film;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe ControllerFilm
 * Implementazione di metodi per gestire i film
 */

public class ControllerFilm {
    private final String tableName = "film.csv";

    public Collection<Film> getAllFilms(){
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines().parallel()
                .map(s -> stringToFilm(s))
                .filter(s -> s.isPresent())
                .map(s-> s.get())
                .toList();
    }

    /**
     * Funzione che data una collezione di idFilm, restituisce una collezione con i film corrispondenti agli id
     * @param idFilms collezione con gli id dei film che cerchiamo
     * @return una collezione con i film cercati
     */
    public Collection<Film> getAllFilmsByIdList (Collection<String> idFilms){
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines().parallel()
                .map(s -> stringToFilm(s))
                .filter(s -> s.isPresent())
                .map(s-> s.get())
                .filter(x-> idFilms.contains(x.getId()))
                .toList();
    }

    /**
     * Funzione che dato un id di un film, restituisce il film corrispettivo
     * @param IDfilm id del film che si vuole cercare
     * @return il film richiesto
     */
    public Optional<Film> getFilmByID(String IDfilm){
        String stringaFilm = Gestione_db.getRow(tableName, IDfilm);
        return stringToFilm(stringaFilm);
    }

    /**
     * Restituisce tutti gli id e i nomi dei film presenti nel database
     * @return una collezione di stringhe in formato "id,NomeFilm"
     */
    public Collection<String> getAllFilmNameAndId(){
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines().parallel()
                .map(s -> stringToFilm(s))
                .filter(s -> s.isPresent())
                .map(s-> s.get())
                .map(s -> s.getId()+","+s.getNome())
                .toList();
    }

    /**
     * Data una stringa con tutte le informazioni del film, procederà con l'inserimento del db
     * @param film stringa con informazioni del film da aggiungere
     * @return messaggio di conferma
     */
    public String insertFilm(String film){
        return Gestione_db.insertRow(tableName, film);
    }

    /**
     * Funzione che dato l'id di un film, procederà all'eliminazione del film dal database
     * @param IDfilm l'id del film da eliminare
     * @return messaggio di conferma
     */
    public String deleteFilmByID(String IDfilm) {
        return Gestione_db.deleteRow(IDfilm, tableName);
    }

    public String modifyFilm(Film filmModificato){ return Gestione_db.modifyRow(filmModificato.getId(), tableName, filmModificato.toString() ); }

    /**
     * Funzione che data una stringa con le informazioni di un film, lo trasforma in un oggetto di tipo Film
     * @param filmString Stringa con le informazioni del film
     * @return Un oggetto Film creato tramite le informazioni nella stringa
     */
    private Optional<Film> stringToFilm(String filmString){
        String[] datiFilm = filmString.split(",");
        if(datiFilm.length>2){
            Film elemento = new Film(datiFilm[0], datiFilm[1], datiFilm[2], datiFilm[3], datiFilm[4]);
            return Optional.of(elemento);
        }
        return Optional.empty() ;
    }
}
