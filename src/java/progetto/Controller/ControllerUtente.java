package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Utente;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

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
    public Utente getUtenteByID(String IDUtente){
        String stringaUtente = Gestione_db.getRow(tableName, IDUtente);

        String[] datiFilm = stringaUtente.split(",");
        if (datiFilm.length > 1) {
            return stringToUtente( stringaUtente );
        }
        return null;
    }

    /**
     * Funzione che restituisce l'utente con l'email e password passati come parametri
     * @param email
     * @param password
     * @return
     */
    public Utente login(String email, String password){
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines().parallel()
                .map(s -> stringToUtente(s))
                .filter(s-> s.getEmail().equals(email.trim()) &&
                            s.getPassword().equals(password.trim()))
                .findFirst().get();
    }


    /**
     * Funzione per gestire l'inserimento di un utente
     * @param nuovoUtente
     * @return
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
     * Funzione che data una stringa con le informazioni di un utente, lo trasforma in un oggetto di tipo Utente
     * @param utenteString
     * @return
     */
    private Utente stringToUtente(String utenteString){
        String[] datiUtente = utenteString.split(",");
        return new Utente(datiUtente[0], datiUtente[1], datiUtente[2], datiUtente[3], datiUtente[4], datiUtente[5], datiUtente[6], datiUtente[7]);
    }

    /**
     * Funzione per verificare se un'email è già presente nel database
     * @param email
     * @return
     */
    public boolean checkEmail(String email){
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines().parallel()
                .map(s -> stringToUtente(s))
                .filter(s -> s.getEmail().equals(email.trim()))
                .toList().isEmpty();
    }

    /**
     * Funzione che restituisce tutti gli amministratori e dipendenti, eccetto l'amministratore passato a parametro.
     * @param idAdmin
     * @return
     */
    public Collection<Utente> getAllStaff(String idAdmin){
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines().parallel()
                .map(s -> stringToUtente(s))
                .filter(s -> !s.getTipo().equals("U") &&
                            !s.getId().equals(idAdmin.trim()))
                .toList();
    }


}
