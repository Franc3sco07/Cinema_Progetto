package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Sala;

import java.io.BufferedReader;
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
     *
     * @param IDsala id della sala che ci interessa
     * @return la sala desiderata
     */

    public Optional<Sala> getSalaByID(String IDsala) {
        String stringaSala = Gestione_db.getRow(tableName, IDsala);
        return Sala.stringToSala(stringaSala);
    }

    /**
     * Funzione che restituisce tutte le sale
     *
     * @return una collezione con tutte le sale
     */
    public Collection<Sala> getAllSala() {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Sala.stringToSala(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }


}
