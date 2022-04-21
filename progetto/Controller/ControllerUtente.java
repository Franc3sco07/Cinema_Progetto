package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Utente;

public class ControllerUtente {
    private final String tableName = "Utente.csv";

    public Utente getUtenteByID(String IDUtente){
        String stringaUtente = Gestione_db.getRow(tableName, IDUtente);

        String[] datiFilm = stringaUtente.split(",");
        if (datiFilm.length > 1) {
            return stringToUtente( stringaUtente );
        }
        return null;
    }

    public String insertUtente(String nuovoUtente){
        return Gestione_db.insertRow(tableName, nuovoUtente);
    }

    public String deleteUtenteByID(String IDutente){
        return Gestione_db.deleteRow(IDutente, tableName);
    }

    public String modifyUtente(Utente utenteModificato){
        return Gestione_db.modifyRow(utenteModificato.getId(), tableName, utenteModificato.toString() );
    }

    private Utente stringToUtente(String utenteString){
        String[] datiUtente = utenteString.split(",");
        return new Utente(datiUtente[0], datiUtente[1], datiUtente[2], datiUtente[3], datiUtente[4], datiUtente[5], datiUtente[6]);
    }

    private String checkEmail(String email){
        return null;
    }

}
