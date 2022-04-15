package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Transazione;

import java.util.Collection;

public class ControllerTransazione {
    private final String tableName = "Transazione.csv";

    public Collection getAllTransazioni(){
        return null;
    }

    public Collection getTransazioniByFilmID(String IDfilm){ return null; }

    public String deleteTransazione (String IDtransazione){
        return Gestione_db.deleteRow(IDtransazione, tableName);
    }

    public String insertTransazione(Transazione transazione){
        return null;
    }
}
