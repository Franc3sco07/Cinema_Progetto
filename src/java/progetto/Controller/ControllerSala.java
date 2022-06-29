package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.functions.TraduttoreMatrice;
import progetto.model.Sala;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Classe ControllerSala
 * Implementazione di metodi per gestire le sale
 */

public class ControllerSala {
    private final String tableName = "sala.csv";

    /**
     * Funzione che preso in input un id sala restituisce un Sala
     * @param IDsala id della sala che ci interessa
     * @return la sala desiderata
     */

    public Optional<Sala> getSalaByID(String IDsala){
        String stringaSala = Gestione_db.getRow(tableName, IDsala);
        return stringToSala( stringaSala );
    }
    /**
     * Funzione che restituisce tutte le sale
     * @return una collezione con tutte le sale
     */
    public Collection<Sala> getAllSala(){
        BufferedReader in = Gestione_db.getTable(tableName);
        return in.lines().parallel()
                .map(s -> stringToSala(s))
                .filter(s -> s.isPresent())
                .map(s-> s.get())
                .toList();
    }

    /**
     * Funzione che data una stringa con le informazioni di una sala, lo trasforma in un oggetto di tipo Sala
     * @param salaString
     * @return
     */
    private Optional<Sala> stringToSala(String salaString){
        String[] datiSala = salaString.split(",");
        if(datiSala.length > 2){
            Sala sl = new Sala(datiSala[0], Integer.parseInt(datiSala[1].trim()), TraduttoreMatrice.stringToMatrice(datiSala[2]));
            return Optional.of(sl);
        }else{
            return Optional.empty();
        }
    }

}
