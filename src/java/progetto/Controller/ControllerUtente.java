package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Utente;

import javax.swing.text.html.Option;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Classe ControllerUtente
 * Implementazione di metodi per gestire gli utenti
 */

public class ControllerUtente {
    private final String tableName = "utente.csv";

    /**
     * Funzione che preso in input un id utente, restituisce l'utente con quell'id
     * @param IDUtente
     * @return
     */
    public Optional<Utente>  getUtenteByID(String IDUtente){
        String stringaUtente = Gestione_db.getRow(tableName, IDUtente);
        return Utente.stringToUtente( stringaUtente );
    }

    /**
     * Funzione che restituisce l'utente con l'email e password passati come parametri
     * @param email
     * @param password
     * @return
     */
    public Optional<Utente> login(String email, String password){
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
        }else{
            return Optional.empty();
        }

    }

    /**
     * Funzione per gestire l'inserimento di un utente
     * @param nuovoUtente
     * @return messaggio di conferma, se è stato inserito ritorna l'id dell'elemento
     */
    public String insertUtente(String nuovoUtente){
        return Gestione_db.insertRow(tableName, nuovoUtente);
    }

    /**
     * Funzione per eliminare un utente
     * @param IDutente
     * @return
     */
    public String deleteUtenteByID(String IDutente){
        return Gestione_db.deleteRow(IDutente, tableName);
    }

    /**
     * Funzione per modificare un utente
     * @param utenteModificato
     * @return
     */
    public String modifyUtente(Utente utenteModificato){
        return Gestione_db.modifyRow(utenteModificato.getId(), tableName, utenteModificato.toString() );
    }

    /**
     * Funzione per verificare se un'email è già presente nel database
     * @param email
     * @return
     */
    public boolean checkEmail(String email){
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Utente.stringToUtente(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> s.getEmail().equals(email.trim()))
                    .toList().isEmpty();
        }else{
            return false;
        }
    }

    /**
     * Funzione che restituisce tutti gli amministratori e dipendenti, eccetto l'amministratore passato a parametro.
     * @param idAdmin
     * @return
     */
    public Collection<Utente> getAllStaff(String idAdmin){
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Utente.stringToUtente(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> !s.getTipo().equals("U") &&
                            !s.getId().equals(idAdmin.trim()))
                    .toList();
        }else{
            return new ArrayList<>();
        }
    }



}
