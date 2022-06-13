package progetto.Controller;

import progetto.database.Gestione_db;
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

public class ControllerTransazione {
    private final String tableName = "Transazione.csv";

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

    public Collection getTransazioniByFilmID(String IDfilm){
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

    public String deleteTransazione (String IDtransazione){
        return Gestione_db.deleteRow(IDtransazione, tableName);
    }

    public String insertTransazione(String transazione){
        return Gestione_db.insertRow(tableName, transazione);
    }

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