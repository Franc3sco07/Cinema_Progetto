package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Prenotazione;

import java.util.Collection;

public class ControllerPrenotazione {
    private final String tableName = "Prenotazione.csv";

    public Collection getPrenotazioniByIDUtente(String IDutente){
        return null;
    }

    public Collection getPrenotazioniByIDFilm(String IDfilm){
        return null;
    }

    public Collection getPrenotazioneByIDProiezione(String IDProiezione){
        return null;
    }

    public String insertPrenotazione(Prenotazione prenotazione){
        return null;
    }

    public String deletePrenotazione(String IDprenotazione){

        return Gestione_db.deleteRow(IDprenotazione, tableName);
    }

    public String modifyPrenotazione(Prenotazione prenotazioneModificata){
        return null;
    }
}
