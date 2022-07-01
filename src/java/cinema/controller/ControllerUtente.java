package cinema.controller;

import cinema.database.Gestione_db;
import cinema.model.TipiUtente;
import cinema.model.Utente;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Classe ControllerUtente
 * Implementazione di metodi per gestire gli utenti
 */

public class ControllerUtente {
    private final String tableName = "utente.csv";

    /**
     * Funzione che preso in input un id utente, restituisce l'utente con quell'id
     *
     * @param IDUtente
     * @return l'utente se è presente
     */
    public Optional<Utente> getUtenteByID(String IDUtente) {
        String stringaUtente = Gestione_db.getRow(tableName, IDUtente);
        return Utente.stringToUtente(stringaUtente);
    }

    /**
     * Funzione che restituisce l'utente con l'email e password passati come parametri
     *
     * @param email
     * @param password
     * @return l'utente se le credenziali sono corrette
     */
    public Optional<Utente> login(String email, String password) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Utente.stringToUtente(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> s.getEmail().equals(email.trim()) &&
                            s.getPassword().equals(password.trim()))
                    .findFirst();
        } else {
            return Optional.empty();
        }

    }

    /**
     * Funzione per gestire l'inserimento di un utente
     *
     * @param nuovoUtente
     * @return messaggio di conferma, se è stato inserito ritorna l'id dell'elemento
     */
    public String insertUtente(String nuovoUtente) {
        return Gestione_db.insertRow(tableName, nuovoUtente);
    }

    /**
     * Funzione per eliminare un utente
     *
     * @param IDutente
     * @return conferma
     */
    public String deleteUtenteByID(String IDutente) {
        return Gestione_db.deleteRow(IDutente, tableName);
    }

    /**
     * Funzione per modificare un utente
     *
     * @param utenteModificato
     * @return conferma
     */
    public String modifyUtente(Utente utenteModificato) {
        return Gestione_db.modifyRow(utenteModificato.getId(), tableName, utenteModificato.toString());
    }

    /**
     * Funzione per verificare se un'email è già presente nel database
     *
     * @param email
     * @return true se l'email non è presente, false se l'email è gia presente
     */
    public boolean checkEmail(String email) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Utente.stringToUtente(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> s.getEmail().equals(email.trim()))
                    .toList().isEmpty();
        } else {
            return false;
        }
    }

    /**
     * Funzione che restituisce tutti gli amministratori e dipendenti, eccetto l'amministratore passato a parametro.
     *
     * @param idAdmin
     * @return una collezione di utenti contenenti tutti gli admin e dipendenti
     */
    public Collection<Utente> getAllStaff(String idAdmin) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Utente.stringToUtente(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> !s.getTipo().equals(TipiUtente.UTENTE.tipo) &&
                            !s.getId().equals(idAdmin.trim()))
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }


}
