package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Utente;

public class ControllerUtente {
    private final String tableName = "Utente.csv";

    public Utente getUtenteByID(String ID){ return null; }

    public String insertUtente(Utente nuovoUtente){
        return null;
    }

    public String deleteUtenteByID(String IDutente){
        return Gestione_db.deleteRow(IDutente, tableName);
    }

    public String modifyUtente(Utente utenteModificato){
        return null;
    }

    private String checkEmail(String email){
        return null;
    }

}
