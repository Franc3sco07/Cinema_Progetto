package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.functions.TraduttoreMatrice;
import progetto.model.Sala;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ControllerSala {
    private final String tableName = "Sala.csv";

    public Sala getSalaByID(String IDsala){
        String stringaSala = Gestione_db.getRow(tableName, IDsala);

        String[] datiFilm = stringaSala.split(",");
        if (datiFilm.length > 1) {
            return stringToSala( stringaSala );
        }

        return null;
    }

    public Collection<Sala> getAllSala(){

        ArrayList<Sala> sale = new ArrayList<Sala>();

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                sale.add(stringToSala( l ));
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}

        return sale;
    }

    private Sala stringToSala(String salaString){
        String[] datiSala = salaString.split(",");
        return new Sala(datiSala[0], Integer.parseInt(datiSala[1].trim()), TraduttoreMatrice.stringToMatrice(datiSala[2]));
    }

}
