package progetto.model;

import progetto.functions.ValidatoreCampi;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

public class Transazione {
    private String idTransazione, idFilm, idPrenotazione, importo;
    private Date data;

    public Transazione(String idTransazione, String idPrenotazione, String idFilm, Date data, String importo) {
        this.idTransazione = idTransazione;
        this.idFilm = idFilm;
        this.idPrenotazione = idPrenotazione;
        this.data = data;
        this.importo = importo;
    }

    /**
     * Funzione che data una stringa con le informazioni di una transazione, lo trasforma in un oggetto di tipo Transazione
     *
     * @param stringTransazione
     * @return
     */
    public static Optional<Transazione> stringToTransazione(String stringTransazione) {
        String[] datiTransazione = stringTransazione.split(",");
        if (datiTransazione.length > 2) {
            try {
                Date d = ValidatoreCampi.DATEFORMAT.parse(datiTransazione[3]);
                Transazione tr = new Transazione(datiTransazione[0], datiTransazione[1], datiTransazione[2], d, datiTransazione[4]);
                return Optional.of(tr);
            } catch (ParseException e) {
            }
        }
        return Optional.empty();
    }

    public String getIdTransazione() {
        return idTransazione.trim();
    }

    @Override
    public String toString() {
        return idTransazione +
                "," + idPrenotazione +
                "," + idFilm +
                "," + data +
                "," + importo;
    }

    public String getIdFilm() {
        return idFilm.trim();
    }

    public String getIdPrenotazione() {
        return idPrenotazione.trim();
    }

    public String getImporto() {
        return importo;
    }

    public void setImporto(String importo) {
        this.importo = importo;
    }

    public Date getData() {
        return data;
    }
}
