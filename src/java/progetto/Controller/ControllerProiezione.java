package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.functions.FunzionalitaDate;
import progetto.model.Proiezione;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

/**
 * Classe ControllerProiezione
 * Implementazione di metodi per gestire le proiezioni
 */

public class ControllerProiezione {
    private final String tableName = "proiezione.csv";

    /**
     * Funzione che presa a paramento un id proiezione restituisca una proiezione
     *
     * @param IDProiezione id della proiezione che ci interessa
     * @return la proiezione con l'id desiderato se presente
     */
    public Optional<Proiezione> getProiezioneByID(String IDProiezione) {
        return Proiezione.stringToProiezione(Gestione_db.getRow(tableName, IDProiezione));
    }

    /**
     * Funzione che presa in input un id film, restituisce le proiezioni di quel film
     *
     * @param IDfilm id del film di cui ci interessano le proiezioni
     * @return una collezione contenenti le proiezioni
     */
    public Collection<Proiezione> getProiezioneByIDFilm(String IDfilm) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines()
                    .parallel()
                    .map(s -> Proiezione.stringToProiezione(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> s.getIdFilm().equals(IDfilm.trim()))
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Funzione che presa in input un id film e una data, restituisce le proiezioni di quel film in quel giorno
     *
     * @param IDfilm id del film di cui ci interessano le proiezioni
     * @param data   data in della proiezione
     * @return collezione di proiezioni del film nella giorno scelto
     */
    public Collection<Proiezione> getProiezioneByIDFilmInADay(String IDfilm, Date data) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines()
                    .parallel()
                    .map(s -> Proiezione.stringToProiezione(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> s.getIdFilm().equals(IDfilm.trim()) &&
                            FunzionalitaDate.stessoGiorno(data, s.getData()) &&
                            FunzionalitaDate.dateSuccesive(data, s.getData()))
                    .toList();
        } else {
            return new ArrayList<>();
        }

    }

    /**
     * Funzione che presa in input una data, restituisce tutti gli id dei film che hanno una proiezione in quel giorno
     *
     * @param data giorno in cui ci interessa che un film abbia una proiezione
     * @return una collezione contenente gli id dei film che hanno una proiezione in quel giorno
     */
    public Collection<String> getAllIdFilmInADay(Date data) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines()
                    .parallel()
                    .map(s -> Proiezione.stringToProiezione(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> FunzionalitaDate.stessoGiorno(data, s.getData()) &&
                            FunzionalitaDate.dateSuccesive(data, s.getData()))
                    .map(s -> s.getIdFilm())
                    .distinct().toList();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Funzione che presa in input una data e un idFilm, restituisce tutte le proiezioni successive a quella data di quel film
     *
     * @param idFilm id del film di cui ci interessano le proiezioni
     * @param data   la data da quando ci interessa che ci sia la proiezione
     * @return una collezione di proiezioni del film
     */
    public Collection<Proiezione> getAllProiezioneByIdFilmAfterDate(String idFilm, Date data) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines()
                    .parallel()
                    .map(s -> Proiezione.stringToProiezione(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> s.getIdFilm().equals(idFilm.trim()) &&
                            FunzionalitaDate.dateSuccesive(data, s.getData()))
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Funzione che presa in input una data, restituisce tutti gli id dei film che hanno una proiezione successiva a quella data
     *
     * @param data la data da quando ci interessa che ci sia una proiezione
     * @return una collezione d'id di film che abbiano almeno una proiezione disponibile
     */
    public Collection<String> getAllIdFilmAfterDate(Date data) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Proiezione.stringToProiezione(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> FunzionalitaDate.dateSuccesive(data, s.getData()))
                    .map(s -> s.getIdFilm())
                    .distinct().toList();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Funzione che gestisce l'inserimento di una proiezione
     *
     * @param proiezione stringa contenente le informazioni della nuova proiezione
     * @return messaggio di conferma, se è stato inserito ritorna l'id dell'elemento
     */

    public String insertProiezione(String proiezione) {
        return Gestione_db.insertRow(tableName, proiezione);
    }

    /**
     * Funzione che gestisce l'eliminazione di una proiezione
     *
     * @param IDproiezione id dela proiezione da eliminare
     * @return conferma
     */
    public String deleteProiezione(String IDproiezione) {
        return Gestione_db.deleteRow(IDproiezione, tableName);
    }

    /**
     * Funzione che gestisce la modifica di una proiezione
     *
     * @param proiezioneModificata la proiezione modificata da aggiornare
     * @return conferma
     */
    public String modifyProiezione(Proiezione proiezioneModificata) {
        return Gestione_db.modifyRow(proiezioneModificata.getId(), tableName, proiezioneModificata.toString());
    }


}
