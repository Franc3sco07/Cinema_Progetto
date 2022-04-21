package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.functions.ValidatoreCampi;
import progetto.model.Proiezione;

import java.text.ParseException;
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

    public String insertProiezione(String proiezione){
        return Gestione_db.insertRow(tableName,proiezione);
    }

    public String deleteProiezione(String IDproiezione){
        return Gestione_db.deleteRow(IDproiezione, tableName);
    }

    public String modifyProiezione(Proiezione proiezioneModificata){
        return Gestione_db.modifyRow(proiezioneModificata.getId(), tableName, proiezioneModificata.toString() );
    }

    public Proiezione stringToProiezione (String proiezione){
        String[] proizioneDati = proiezione.split(",");
        Date d = null;
        return new Proiezione(proizioneDati[0],
                proizioneDati[1],
                proizioneDati[2],
                proizioneDati[3],
                d,
                proizioneDati[5],
                proizioneDati[6]);
        try {
            d = ValidatoreCampi.DATEFORMAT.parse(proizioneDati[4]);
        } catch (ParseException e) {
            return  null;
        }

    }

}
