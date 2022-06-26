package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.functions.TraduttoreMatrice;
import progetto.model.Sala;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe ControllerSala
 * Implementazione di metodi per gestire le sale
 */

public class ControllerSala {
    private final String tableName = "sala.csv";

    /**
     * Funzione che preso in input un id sala restituisce un Sala
     * @param IDsala
     * @return
     */

    public Sala getSalaByID(String IDsala){
        String stringaSala = Gestione_db.getRow(tableName, IDsala);

        String[] datiFilm = stringaSala.split(",");
        if (datiFilm.length > 1) {
            return stringToSala( stringaSala );
        }

        return null;
    }

    public Collection<String> getAllIdSala(){

        ArrayList<String> idSale = new ArrayList<>();

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                idSale.add(stringToSala(l).getId());
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}

        return idSale;
    }

    /**
     * Funzione che restituisce tutte le sale
     * @return
     */
    public Collection<Sala> getAllSala(){
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines().parallel()
                .map(s -> stringToSala(s))
                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
    }

    /**
     * Funzione che data una stringa con le informazioni di una sala, lo trasforma in un oggetto di tipo Sala
     * @param salaString
     * @return
     */
    private Sala stringToSala(String salaString){
        String[] datiSala = salaString.split(",");
        return new Sala(datiSala[0], Integer.parseInt(datiSala[1].trim()), TraduttoreMatrice.stringToMatrice(datiSala[2]));
    }

}
