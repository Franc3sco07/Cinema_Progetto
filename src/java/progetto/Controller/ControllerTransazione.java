package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.functions.FunzionalitaDate;
import progetto.model.Transazione;

import java.io.BufferedReader;
import java.util.Date;
import java.util.Optional;


/**
 * Classe ControllerTransazione
 * Implementazione di metodi per gestire le transazioni
 */

public class ControllerTransazione {
    private final String tableName = "transazione.csv";

    /**
     * Funzione che preso a parametro un id di una prenotazione restituisce la transazione a essa collegata transazione
     *
     * @param idPrenotazione l'id della prenotazione di cui vogliamo sapere la transazione
     * @return la transazione da noi cercata
     */
    public Optional<Transazione> getTransazioneByIDPrenotazione(String idPrenotazione) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Transazione.stringToTransazione(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> s.getIdPrenotazione().equals(idPrenotazione.trim()))
                    .findFirst();
        } else {
            return Optional.empty();
        }


    }

    /**
     * Funzione che presa a parametro un id film e una data, restituisce tutte le relative vendite giornaliere
     *
     * @param IDfilm id film di cui ci interessano le vendite
     * @param data   data in cui deve essere avvenuta la vendita
     * @return la vendita totale del film nella giornata scelta
     */
    public Double getTotataleVenditeByIDFilmInADay(String IDfilm, Date data) {
        Optional<BufferedReader> optionalBufferedReader = Gestione_db.getTable(tableName);
        if (optionalBufferedReader.isPresent()) {
            BufferedReader in = optionalBufferedReader.get();
            return in.lines().parallel()
                    .map(s -> Transazione.stringToTransazione(s))
                    .filter(s -> s.isPresent())
                    .map(s -> s.get())
                    .filter(s -> s.getIdFilm().equals(IDfilm.trim()) &&
                            FunzionalitaDate.stessoGiorno(data, s.getData()))
                    .map(s -> Double.parseDouble(s.getImporto()))
                    .reduce(0.0, Double::sum);
        } else {
            return 0.0;
        }
    }

    /**
     * Funzione che presa in input un id transazione, elimina la transazione con quell'id
     *
     * @param IDtransazione
     * @return
     */
    public String deleteTransazione(String IDtransazione) {
        return Gestione_db.deleteRow(IDtransazione, tableName);
    }

    /**
     * Funzione utilizzata per gestire l'inserimento di una transazione
     *
     * @param transazione
     * @return messaggio di conferma, se è stato inserito ritorna l'id dell'elemento
     */
    public String insertTransazione(String transazione) {
        return Gestione_db.insertRow(tableName, transazione);
    }

    /**
     * Funzione utilizzata per gestire la modifica di una transazione
     *
     * @param transazioneModificata
     * @return
     */
    public String modifyTransazione(Transazione transazioneModificata) {
        return Gestione_db.modifyRow(transazioneModificata.getIdTransazione(), tableName, transazioneModificata.toString());
    }


}
