package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.functions.FunzionalitaDate;
import progetto.model.Prenotazione;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

/**
 * Classe ControllerPrenotazione
 * Implementazione di metodi per gestire le prenotazioni
 */

public class ControllerPrenotazione {
    private final String tableName = "prenotazione.csv";

    /**
     * Funzione che preso un id restituisce l'utente con l'id richiesto
     *
     * @param id id utente da cercare
     * @return l'utente con l'id desiderato o null se non presente
     */
    public Optional<Prenotazione> getPrenotazioneById(String id) {
        return Prenotazione.stringToPrenotazione(Gestione_db.getRow(tableName, id));
    }

    /**
     * funzione che preso in input un id e una data, restituisce tutte le proiezioni dell'utente successive alla data
     *
     * @param IDutente utente di cui si vogliono sapere le prenotazioni
     * @param data     data da cui cercare
     * @return una collezione contenenti tutte le prenotazioni dell'utente disponibili
     */
    public Collection<Prenotazione> getPrenotazioniByIDgeneratoreAfterDate(String IDutente, Date data) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Prenotazione.stringToPrenotazione(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(x -> x.getIdGeneratore().trim().equals(IDutente.trim())
                            && FunzionalitaDate.dateSuccesive(data, x.getData()))
                    .toList();
        } else {
            return new ArrayList<>();
        }

    }

    /**
     * funzione che preso in input un id film restituisce le prenotazioni associate
     *
     * @param IDfilm id del film di cui ci interessa la lista di proizioni
     * @return la collezione delle prenotazioni
     */
    public Collection<Prenotazione> getPrenotazioniByIDFilm(String IDfilm) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Prenotazione.stringToPrenotazione(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> s.getIdFilm().equals(IDfilm.trim()))
                    .toList();
        } else {
            return new ArrayList<>();
        }


    }


    /**
     * Funzione che preso in input un idUtente e una data, restituisce gli id dei film con una prenotazione datta dall'utente nello stesso giorno
     *
     * @param idUtente l'id dell'utente di cui ci interessano le prenotazioni
     * @param data     la data in cui deve esserci una prenotazione
     * @return una collezione contenete gli id dei film che ci interressa
     */
    public Collection<String> getIdFilmByIdUtenteInADay(String idUtente, Date data) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Prenotazione.stringToPrenotazione(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> s.getIdGeneratore().equals(idUtente.trim()) &&
                            FunzionalitaDate.stessoGiorno(data, s.getData()))
                    .map(s -> s.getIdFilm())
                    .distinct().toList();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Funzione che preso in input un idUtente, idFIlm e una data, restituisce la lista di prenotazioni dello stesso giorno di dell utente per il film dcelto
     *
     * @param idUtente l'id dell'utente che ha effettuato la prenotazione
     * @param idFilm   l'id del film che ci interessa
     * @param data     la data in cui ci interessano che ci sia una prenotazione
     * @return una collezione contente tutte le prenotazioni cercate
     */
    public Collection<Prenotazione> getPrenotazioniByIDFilmInADay(String idUtente, String idFilm, Date data) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Prenotazione.stringToPrenotazione(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> s.getIdGeneratore().equals(idUtente.trim()) &&
                            FunzionalitaDate.stessoGiorno(data, s.getData()) &&
                            FunzionalitaDate.dateSuccesive(data, s.getData()) &&
                            s.getIdFilm().equals(idFilm.trim()))
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Funzioni che gestisce l'inserimento di una prenotazione
     *
     * @param prenotazione stringa con le informazioni delle nuova prenotazione
     * @return messaggio di conferma, se è stato inserito ritorna l'id dell'elemento
     */
    public String insertPrenotazione(String prenotazione) {
        return Gestione_db.insertRow(tableName, prenotazione);
    }

    /**
     * Funzioni che gestisce l'eliminazione di una prenotazione
     *
     * @param IDprenotazione id della prenotazione che si vuole eliminare
     * @return conferma
     */
    public String deletePrenotazione(String IDprenotazione) {
        return Gestione_db.deleteRow(IDprenotazione, tableName);
    }

    /**
     * Funzioni che gestisce la modifica di una prenotazione
     *
     * @param prenotazioneModificata l'oggetto modifiacato
     * @return conferma
     */
    public String modifyPrenotazione(Prenotazione prenotazioneModificata) {
        return Gestione_db.modifyRow(prenotazioneModificata.getId(), tableName, prenotazioneModificata.toString());
    }


}
