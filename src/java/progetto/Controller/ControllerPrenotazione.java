package progetto.Controller;
import progetto.database.Gestione_db;
import progetto.functions.FunzionalitaDate;
import progetto.functions.ValidatoreCampi;
import progetto.model.Prenotazione;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Classe ControllerPrenotazione
 * Implementazione di metodi per gestire le prenotazioni
 */

public class ControllerPrenotazione {
    private final String tableName = "prenotazione.csv";

    /**
     * Funzione che preso un id restituisce l'utente con l'id richiesto
     * @param id id utente da cercare
     * @return l'utente con l'id desiderato o null se non presente
     */
    public Prenotazione getPrenotazioneById (String id){
        return stringToPrenotazione(Gestione_db.getRow(tableName,id)) ;
    }

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

    /**
     * funzione che preso in input un id e una data, restituisce tutte le proiezioni dell'utente successive alla data
     * @param IDutente utente di cui si vogliono sapere le prenotazioni
     * @param data data da cui cercare
     * @return una collezione contenenti tutte le prenotazioni dell'utente disponibili
     */
    public Collection<Prenotazione> getPrenotazioniByIDgeneratoreAfterDate(String IDutente, Date data){
        BufferedReader in = Gestione_db.getTable(tableName);
        return  in.lines()
                    .parallel()
                        .map(s -> stringToPrenotazione(s))
                            .filter(x-> x.getIdGeneratore().trim().equals(IDutente.trim())
                                    && FunzionalitaDate.dateSuccesive(data,x.getData()))
                                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
    }

    /**
     * funzione che preso in input un id film restituisce le prenotazioni associate
     * @param IDfilm id del film di cui ci interessa la lista di proizioni
     * @return la collezione delle prenotazioni
     */
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
        Prenotazione prenotazioniTemp;

        for(Iterator<Prenotazione> iterator = prenotazioni.iterator(); iterator.hasNext();){
            prenotazioniTemp = iterator.next();
            if (IDfilm.trim().equals(prenotazioniTemp.getIdFilm().trim())) {
                prenotazioniByIDFilm.add(prenotazioniTemp);
            }
        }

        return prenotazioniByIDFilm;
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
            prenotazioniTemp = iterator.next();
            if (IDProiezione.trim().equals(prenotazioniTemp.getIdProiezione().trim())) {
                prenotazioniByIDProiezione.add(prenotazioniTemp);
            }
        }

        return prenotazioniByIDProiezione;
    }

    /**
     * Funzione che preso in input un idUtente e una data, restituisce gli id dei film con una prenotazione datta dall'utente nello stesso giorno
     * @param idUtente l'id dell'utente di cui ci interessano le prenotazioni
     * @param data la data in cui deve esserci una prenotazione
     * @return una collezione contenete gli id dei film che ci interressa
     */
    public Collection<String> getIdFilmByIdUtenteInADay(String idUtente, Date data){
        HashSet<String> idFilms = new HashSet<>();
        BufferedReader in = Gestione_db.getTable(tableName);
        Prenotazione tmp;
        try {
            String l;
            while ((l = in.readLine()) != null) {
                tmp = stringToPrenotazione(l);
                if(tmp.getIdGeneratore().equals(idUtente) &&
                        FunzionalitaDate.stessoGiorno(data,tmp.getData()) &&
                        FunzionalitaDate.dateSuccesive(data,tmp.getData())){
                    idFilms.add(tmp.getIdFilm());
                }
            }
            return idFilms;
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}
        return null;
    }

    /**
     * Funzione che preso in input un idUtente, idFIlm e una data, restituisce la lista di prenotazioni dello stesso giorno
     * @param idUtente l'id dell'utente che ha effettuato la prenotazione
     * @param idFilm l'id del film che ci interessa
     * @param data la data in cui ci interessano che ci sia una prenotazione
     * @return una collezione contente tutte le prenotazioni cercate
     */
    public Collection<Prenotazione> getPrenotazioniByIDFilmInADay(String idUtente, String idFilm, Date data){
        ArrayList<Prenotazione> proiezioni = new ArrayList<>();
        BufferedReader in = Gestione_db.getTable(tableName);
        Prenotazione tmp;
        try {
            String l;
            while ((l = in.readLine()) != null) {
                tmp = stringToPrenotazione(l);
                if(tmp.getIdGeneratore().equals(idUtente) &&
                        tmp.getIdFilm().equals(idFilm)    &&
                        FunzionalitaDate.stessoGiorno(data,tmp.getData()) &&
                        FunzionalitaDate.dateSuccesive(data,tmp.getData())){
                    proiezioni.add(tmp);
                }
            }
            return proiezioni;
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}

        return null;
    }

    /**
     * Funzioni che gestisce l'inserimento di una prenotazione
     * @param prenotazione stringa con le informazioni delle nuova prenotazione
     * @return conferma
     */
    public String insertPrenotazione(String prenotazione){
        return Gestione_db.insertRow(tableName, prenotazione);
    }

    /**
     * Funzioni che gestisce l'eliminazione di una prenotazione
     * @param IDprenotazione id della prenotazione che si vuole eliminare
     * @return conferma
     */
    public String deletePrenotazione(String IDprenotazione){
        return Gestione_db.deleteRow(IDprenotazione, tableName);
    }

    /**
     * Funzioni che gestisce la modifica di una prenotazione
     * @param prenotazioneModificata l'oggetto modifiacato
     * @return conferma
     */
    public String modifyPrenotazione(Prenotazione prenotazioneModificata){
        return Gestione_db.modifyRow(prenotazioneModificata.getId(), tableName, prenotazioneModificata.toString() );
    }

    /**
     * Funzione che data una stringa con le informazioni di una prenotazione, lo trasforma in un oggetto di tipo Prenotazione
     * @param prenotazioneString stringa con le informazioni delle nuova prenotazione
     * @return un oggetto prenotazione con le informazioni di una prenotazione
     */
    private Prenotazione stringToPrenotazione(String prenotazioneString){
        String[] datiPrenotazione = prenotazioneString.split(",");
        Date d = null;
        if (datiPrenotazione.length>1){
            try{
                d = ValidatoreCampi.DATEFORMAT.parse(datiPrenotazione[4]);
            } catch (ParseException e) {
                System.out.println("Errore ParseException");
                System.out.println(datiPrenotazione);
                return null;

            }
            return new Prenotazione(datiPrenotazione[0], datiPrenotazione[1], datiPrenotazione[2], datiPrenotazione[3], d, datiPrenotazione[5], datiPrenotazione[6]);
        }
        return null;
        

    }

}
