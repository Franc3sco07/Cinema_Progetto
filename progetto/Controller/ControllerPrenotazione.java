package progetto.Controller;
import progetto.database.Gestione_db;
import progetto.model.Prenotazione;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ControllerPrenotazione {
    private final String tableName = "Prenotazione.csv";

    public Collection getPrenotazioniByIDUtente(String IDutente){
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                prenotazioni.add(stringToPrenotazione( l ));
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}


        ArrayList<Prenotazione> prenotazioniByIDUtente = new ArrayList<>();
        Prenotazione prenotazioniTemp ;

        for(Iterator<Prenotazione> iterator = prenotazioni.iterator(); iterator.hasNext();){
            //System.out.println(iterator.next());
            prenotazioniTemp = iterator.next();
            if (IDutente.equals(prenotazioniTemp.getIdGeneratore())) {
                prenotazioniByIDUtente.add(prenotazioniTemp);
            }
        }

        return prenotazioniByIDUtente;
    }

    public Collection getPrenotazioniByIDFilm(String IDfilm){
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                prenotazioni.add(stringToPrenotazione( l ));
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}


        ArrayList<Prenotazione> prenotazioniByIDFilm = new ArrayList<>();
        Prenotazione prenotazioniTemp ;

        for(Iterator<Prenotazione> iterator = prenotazioni.iterator(); iterator.hasNext();){
            //System.out.println(iterator.next());
            prenotazioniTemp = iterator.next();
            if (IDfilm.equals(prenotazioniTemp.getIdFilm())) {
                prenotazioniByIDFilm.add(prenotazioniTemp);
            }
        }

        return prenotazioniByIDFilm;
    }

    public Collection getPrenotazioneByIDProiezione(String IDProiezione){
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                prenotazioni.add(stringToPrenotazione( l ));
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}


        ArrayList<Prenotazione> prenotazioniByIDProiezione = new ArrayList<>();
        Prenotazione prenotazioniTemp ;

        for(Iterator<Prenotazione> iterator = prenotazioni.iterator(); iterator.hasNext();){
            //System.out.println(iterator.next());
            prenotazioniTemp = iterator.next();
            if (IDProiezione.equals(prenotazioniTemp.getIdProiezione())) {
                prenotazioniByIDProiezione.add(prenotazioniTemp);
            }
        }

        return prenotazioniByIDProiezione;
    }

    public String insertPrenotazione(String prenotazione){
        return Gestione_db.insertRow(tableName, prenotazione);
    }

    public String deletePrenotazione(String IDprenotazione){
        return Gestione_db.deleteRow(IDprenotazione, tableName);
    }

    public String modifyPrenotazione(Prenotazione prenotazioneModificata){
        return Gestione_db.modifyRow(prenotazioneModificata.getId(), tableName, prenotazioneModificata.toString() );
    }

    private Prenotazione stringToPrenotazione(String prenotazioneString){
        String[] datiPrenotazione = prenotazioneString.split(",");
        return new Prenotazione(datiPrenotazione[0], datiPrenotazione[1], datiPrenotazione[2], datiPrenotazione[3], datiPrenotazione[4], datiPrenotazione[5]);
    }
}
