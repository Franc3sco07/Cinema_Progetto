package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.functions.ValidatoreCampi;
import progetto.model.Film;
import progetto.model.Proiezione;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class ControllerProiezione {
    private final String tableName = "Proiezione.csv";

    public Collection getProiezioneByIDFilm(String IDfilm){
        ArrayList<Proiezione> proiezioni = new ArrayList<>();

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                proiezioni.add(stringToProiezione( l ));
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}


        ArrayList<Proiezione> proiezioniByIDFilm = new ArrayList<>();
        Proiezione proiezioneTemp ;

        for(Iterator<Proiezione> iterator = proiezioni.iterator(); iterator.hasNext();){
            //System.out.println(iterator.next());
            proiezioneTemp = iterator.next();
            if (IDfilm.equals(proiezioneTemp.getIdFilm())) {
                proiezioniByIDFilm.add(proiezioneTemp);
            }
        }

        return proiezioniByIDFilm;
    }

    public Collection getProiezioneByDate(Date data){
        ArrayList<Proiezione> proiezioni = new ArrayList<>();

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                proiezioni.add(stringToProiezione( l ));
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}


        ArrayList<Proiezione> proiezioniByDate = new ArrayList<>();
        Proiezione proiezioneTemp ;

        for(Iterator<Proiezione> iterator = proiezioni.iterator(); iterator.hasNext();){
            //System.out.println(iterator.next());
            proiezioneTemp = iterator.next();
            if (data.equals(proiezioneTemp.getData())) {
                proiezioniByDate.add(proiezioneTemp);
            }
        }

        return proiezioniByDate;
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
