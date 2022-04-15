package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Proiezione;

import java.util.Collection;
import java.util.Date;

public class ControllerProiezione {
    private final String tableName = "Proiezione.csv";

    public Collection getProiezioneByIDFilm(String IDfilm){
        return null;
    }

    public Collection getProiezioneByDate(Date data){
        return null;
    }

    public String insertProiezione(Proiezione proiezione){
        return null;
    }

    public String deleteProiezione(String IDproiezione){
        return Gestione_db.deleteRow(IDproiezione, tableName);
    }

    public String modifyProiezione(Proiezione proiezioneModificata){
        return null;
    }

}
