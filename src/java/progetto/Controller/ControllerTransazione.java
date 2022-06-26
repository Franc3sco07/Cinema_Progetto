package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.functions.FunzionalitaDate;
import progetto.functions.ValidatoreCampi;
import progetto.model.Transazione;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Classe ControllerTransazione
 * Implementazione di metodi per gestire le transazioni
 */

public class ControllerTransazione {
    private final String tableName = "Transazione.csv";

    /**
     * Funzione che preso a parametro un id prenotazione restituisce una transazione
     * @param idPrenotazione
     * @return
     */

    public Transazione getTransazioneByIDPrenotazione(String idPrenotazione){
        Transazione tmp;
        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                tmp = stringToTransazione(l);
                if(tmp.getIdPrenotazione().equals(idPrenotazione)){
                    return tmp;
                }
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}

        return null;

    }

    public Collection<Transazione> getAllTransazioni(){
        ArrayList<Transazione> transazioni = new ArrayList<Transazione>();

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                transazioni.add(stringToTransazione( l ));
            }
            return transazioni;
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}

        return null;
    }

    public Collection<Transazione> getTransazioniByFilmID(String IDfilm){
        ArrayList<Transazione> transazioni = new ArrayList<>();

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                transazioni.add(stringToTransazione( l ));
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}


        ArrayList<Transazione> prenotazioniByFilmID = new ArrayList<>();
        Transazione transazioniTemp ;

        for(Iterator<Transazione> iterator = transazioni.iterator(); iterator.hasNext();){
            //System.out.println(iterator.next());
            transazioniTemp = iterator.next();
            if (IDfilm.equals(transazioniTemp.getIdFilm())) {
                prenotazioniByFilmID.add(transazioniTemp);
            }
        }

        return prenotazioniByFilmID;
    }

    /**
     * Funzione che presa a parametro un id film e una data, restituisce tutte le relative vendite giornaliere
     * @param IDfilm
     * @param data
     * @return
     */
    public Collection<Double> getAllVenditeByIDFilmInADay(String IDfilm, Date data ){
        ArrayList<Double> vendite = new ArrayList<>();
        Transazione tmp;
        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                tmp = stringToTransazione( l );
                if(tmp.getIdFilm().equals(IDfilm) && FunzionalitaDate.stessoGiorno(data,tmp.getData())){
                    vendite.add(Double.parseDouble(tmp.getImporto().trim()));
                }

            }
            return vendite;
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}
        return null;

    }

    /**
     * Funzione che presa in input un id transazione, elimina la transazione con quell'id
     * @param IDtransazione
     * @return
     */
    public String deleteTransazione (String IDtransazione){
        return Gestione_db.deleteRow(IDtransazione, tableName);
    }

    /**
     * Funzione utilizzata per gestire l'inserimento di una transazione
     * @param transazione
     * @return
     */
    public String insertTransazione(String transazione){
        //System.out.println("CAIO");
        return Gestione_db.insertRow(tableName, transazione);
    }

    /**
     * Funzione utilizzata per gestire la modifica di una transazione
     * @param transazioneModificata
     * @return
     */
    public String modifyTransazione(Transazione transazioneModificata){
        return Gestione_db.modifyRow(transazioneModificata.getIdTransazione(), tableName, transazioneModificata.toString() );
    }

    /**
     * Funzione che data una stringa con le informazioni di una transazione, lo trasforma in un oggetto di tipo Transazione
     * @param stringTransazione
     * @return
     */

    private Transazione stringToTransazione ( String stringTransazione){
        String[] datiTransazione =stringTransazione.split(",");

        try {
            Date d = ValidatoreCampi.DATEFORMAT.parse(datiTransazione[3]);
            return new Transazione(datiTransazione[0],datiTransazione[1],datiTransazione[2],d,datiTransazione[4]);
        } catch (ParseException e) {
            return null;
        }
    }


}
