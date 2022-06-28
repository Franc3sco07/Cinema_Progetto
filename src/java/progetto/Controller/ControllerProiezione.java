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

/**
 * Classe ControllerProiezione
 * Implementazione di metodi per gestire le proiezioni
 */

public class ControllerProiezione {
    private final String tableName = "proiezione.csv";

    /**
     * Funzione che presa a paramento un id proiezione restituisca una proiezione
     * @param IDProiezione id della proiezione che ci interessa
     * @return la proiezione con l'id desiderato
     */
    public Proiezione getProiezioneByID(String IDProiezione){
        return stringToProiezione(Gestione_db.getRow( tableName, IDProiezione));
    }

    /**
     * Funzione che presa in input un id film, restituisce le proiezioni di quel film
     * @param IDfilm id del film di cui ci interessano le proiezioni
     * @return una collezione contenenti le proiezioni
     */
    public Collection<Proiezione> getProiezioneByIDFilm(String IDfilm){
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines()
                .parallel()
                .map(s -> stringToProiezione(s))
                .filter(s -> s.getIdFilm().equals(IDfilm.trim()))
                .toList();
    }

    /**
     * Funzione che presa in input un id film e una data, restituisce le proiezioni di quel film in quel giorno
     * @param IDfilm id del film di cui ci interessano le proiezioni
     * @param data data in della proiezione
     * @return collezione di proiezioni del film nella giorno scelto
     */
    public Collection<Proiezione> getProiezioneByIDFilmInADay(String IDfilm,Date data){
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines()
                .parallel()
                .map(s -> stringToProiezione(s))
                .filter(s -> s.getIdFilm().equals(IDfilm.trim()) &&
                        FunzionalitaDate.stessoGiorno(data,s.getData()) &&
                        FunzionalitaDate.dateSuccesive(data,s.getData()) )
                .toList();
    }

    /**
     * Funzione che presa in input una data, restituisce tutti gli id dei film che hanno una proiezione in quel giorno
     * @param data giorno in cui ci interessa che un film abbia una proiezione
     * @return una collezione contenente gli id dei film che hanno una proiezione in quel giorno
     */
    public Collection<String> getAllIdFilmInADay (Date data){
        HashSet<String> idFilms = new HashSet<>();
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines()
                .parallel()
                .map(s -> stringToProiezione(s))
                .filter(s -> FunzionalitaDate.stessoGiorno(data,s.getData()) &&
                        FunzionalitaDate.dateSuccesive(data,s.getData()))
                .map(s-> s.getIdFilm())
                .distinct().toList();
    }

    /**
     * Funzione che presa in input una data e un idFilm, restituisce tutte le proiezioni successive a quella data di quel film
     * @param idFilm id del film di cui ci interessano le proiezioni
     * @param data  la data da quando ci interessa che ci sia la proiezione
     * @return una collezione di proiezioni del film
     */
    public Collection<Proiezione> getAllProiezioneByIdFilmAfterDate(String idFilm, Date data){
        ArrayList<Proiezione> proiezioni = new ArrayList<>();
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines()
                .parallel()
                .map(s -> stringToProiezione(s))
                .filter(s -> s.getIdFilm().equals(idFilm.trim()) &&
                        FunzionalitaDate.dateSuccesive(data,s.getData()))
                .toList();
    }

    /**
     * Funzione che presa in input una data, restituisce tutti gli id dei film che hanno una proiezione successiva a quella data
     * @param data la data da quando ci interessa che ci sia una proiezione
     * @return una collezione d'id di film che abbiano almeno una proiezione disponibile
     */
    public Collection<String> getAllIdFilmAfterDate(Date data){
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines().parallel()
                .map(s -> stringToProiezione(s))
                .filter(s -> FunzionalitaDate.dateSuccesive(data,s.getData()) )
                .map(s -> s.getIdFilm())
                .distinct().toList();
    }

    /**
     * Funzione che gestisce l'inserimento di una proiezione
     * @param proiezione stringa contenente le informazioni della nuova proiezione
     * @return confernma
     */

    public String insertProiezione(String proiezione){
        return Gestione_db.insertRow(tableName,proiezione);
    }

    /**
     * Funzione che gestisce l'eliminazione di una proiezione
     * @param IDproiezione id dela proiezione da eliminare
     * @return conferma
     */
    public String deleteProiezione(String IDproiezione){
        return Gestione_db.deleteRow(IDproiezione, tableName);
    }

    /**
     * Funzione che gestisce la modifica di una proiezione
     * @param proiezioneModificata la proiezione modificata da aggiornare
     * @return conferma
     */
    public String modifyProiezione(Proiezione proiezioneModificata){
        return Gestione_db.modifyRow(proiezioneModificata.getId(), tableName, proiezioneModificata.toString());
    }

    /**
     * Funzione che data una stringa con le informazioni di una proiezione, lo trasforma in un oggetto di tipo Proiezione
     * @param proiezione Stringa contentente le informazioni della proiezione
     * @return un oggetto proiezione con le informazioni della stringa
     */
    public Proiezione stringToProiezione (String proiezione){
        System.out.println(proiezione);
        String[] proizioneDati = proiezione.split(",");
        Date d = null;
        if(proizioneDati.length>2){
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
        }else{
            return null;
        }
    }

}
