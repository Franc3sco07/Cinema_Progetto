package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.functions.FunzionalitaDate;
import progetto.functions.TraduttoreMatrice;
import progetto.functions.ValidatoreCampi;
import progetto.model.Proiezione;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class ControllerProiezione {
    private final String tableName = "proiezione.csv";

    public Proiezione getProezioneByID(String IDProiezione){
        return stringToProiezione(Gestione_db.getRow( tableName, IDProiezione));
    }
    public Collection<Proiezione> getProiezioneByIDFilm(String IDfilm){
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

    public Collection<Proiezione> getProiezioneByIDFilmInADay(String IDfilm,Date data){
        ArrayList<Proiezione> proiezioni = new ArrayList<>();
        BufferedReader in = Gestione_db.getTable(tableName);
        Proiezione tmp;
        try {
            String l;
            while ((l = in.readLine()) != null) {
                tmp = stringToProiezione(l);
                if( tmp.getIdFilm().equals(IDfilm) && FunzionalitaDate.stessoGiorno(data,tmp.getData()) && FunzionalitaDate.dateSuccesive(data,tmp.getData())){
                    proiezioni.add(tmp);
                }
            }
            return proiezioni;
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}

        return null;
    }

    public Collection<String> getAllIdFilmInADay (Date data){
        HashSet<String> idFilms = new HashSet<>();
        BufferedReader in = Gestione_db.getTable(tableName);
        Proiezione tmp;
        try {
            String l;
            while ((l = in.readLine()) != null) {
                tmp = stringToProiezione(l);
                if(FunzionalitaDate.stessoGiorno(data,tmp.getData()) && FunzionalitaDate.dateSuccesive(data,tmp.getData())){
                    idFilms.add(tmp.getIdFilm());
                }
            }
            return idFilms;
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}
        return null;
    }


    public Collection<Proiezione> getAllProiezioneByIdFilmAfterDate(String idFilm, Date data){
        ArrayList<Proiezione> proiezioni = new ArrayList<>();
        BufferedReader in = Gestione_db.getTable(tableName);
        Proiezione tmp;
        try {
            String l;

            while ((l = in.readLine()) != null) {
                tmp = stringToProiezione(l);
                if(tmp.getIdFilm().equals(idFilm) && FunzionalitaDate.dateSuccesive(data,tmp.getData())){
                    proiezioni.add(tmp);
                }
            }
        } catch (IOException e) {
        }
        return proiezioni;
    }

    public Collection<String> getAllIdFilmAfterDate(Date data){
        HashSet<String> filmSetId= new HashSet<>();
        BufferedReader in = Gestione_db.getTable(tableName);
        Proiezione tmp;
        try {
            String l;
            while ((l = in.readLine()) != null) {
                tmp = stringToProiezione( l );
                if(tmp.getData().after(data)) {
                    filmSetId.add(tmp.getIdFilm());
                }
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}
        return filmSetId;

    }


    public String insertProiezione(String proiezione){
        return Gestione_db.insertRow(tableName,proiezione);
    }

    public String deleteProiezione(String IDproiezione){
        return Gestione_db.deleteRow(IDproiezione, tableName);
    }

    public String modifyProiezione(Proiezione proiezioneModificata){
        return Gestione_db.modifyRow(proiezioneModificata.getId(), tableName, proiezioneModificata.toString());
    }

    public Proiezione stringToProiezione (String proiezione){
        String[] proizioneDati = proiezione.split(",");
        Date d = null;

        try {
            d = ValidatoreCampi.DATEFORMAT.parse(proizioneDati[4]);
            return new Proiezione(proizioneDati[0],
                    proizioneDati[1],
                    proizioneDati[2],
                    proizioneDati[3],
                    d,
                    Integer.parseInt(proizioneDati[5].trim()),
                    TraduttoreMatrice.stringToMatrice(proizioneDati[6]));
        } catch (ParseException e) {
            System.out.println("Errore ParseException");
            System.out.println(proizioneDati);
            return null;
        }

    }

}
