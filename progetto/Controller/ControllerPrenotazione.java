package progetto.Controller;
import progetto.database.Gestione_db;
import progetto.functions.ValidatoreCampi;
import progetto.model.Prenotazione;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class ControllerPrenotazione {
    private final String tableName = "prenotazione.csv";

    public Collection<Prenotazione> getPrenotazioniByIDgeneratore(String IDutente){
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                prenotazioni.add(stringToPrenotazione(l));
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}


        ArrayList<Prenotazione> prenotazioniByIDUtente = new ArrayList<>();
        Prenotazione prenotazioniTemp ;

        for(Iterator<Prenotazione> iterator = prenotazioni.iterator(); iterator.hasNext();){
            //System.out.println(iterator.next());
            prenotazioniTemp = iterator.next();
            if (IDutente.trim().equals(prenotazioniTemp.getIdGeneratore().trim())) {
                prenotazioniByIDUtente.add(prenotazioniTemp);
            }
        }

        return prenotazioniByIDUtente;
    }

    public Collection<Prenotazione> getPrenotazioniByIDFilm(String IDfilm){
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
            if (IDfilm.trim().equals(prenotazioniTemp.getIdFilm().trim())) {
                prenotazioniByIDFilm.add(prenotazioniTemp);
            }
        }

        return prenotazioniByIDFilm;
    }

    public Collection<Prenotazione> getByIDGeneratoreAfterDate(String idGeneratore, Date data){
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        Prenotazione tmp;

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                tmp = stringToPrenotazione(l);
                if(tmp.getIdGeneratore().equals(idGeneratore) && tmp.getData().before(data)){
                    prenotazioni.add(tmp);
                }

            }
            return prenotazioni;
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}
        return null;
    }

    public Collection<Prenotazione> getPrenotazioneByIDProiezione(String IDProiezione){
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
            if (IDProiezione.trim().equals(prenotazioniTemp.getIdProiezione().trim())) {
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
        Date d = null;
        try{
            d = ValidatoreCampi.DATEFORMAT.parse(datiPrenotazione[4]);
        } catch (ParseException e) {
            System.out.println("Errore ParseException");
            System.out.println(datiPrenotazione);
            return null;

        }
        
        return new Prenotazione(datiPrenotazione[0], datiPrenotazione[1], datiPrenotazione[2], datiPrenotazione[3], d, datiPrenotazione[5], datiPrenotazione[6]);
    }
}
