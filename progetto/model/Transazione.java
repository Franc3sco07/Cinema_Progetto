package progetto.model;

import java.util.Date;

public class Transazione {
    private String idTransazione, idFilm, idPrenotazione,importo;
    private Date data;

    public Transazione(String idTransazione, String idPrenotazione,String idFilm, Date data,String importo) {
        this.idTransazione = idTransazione;
        this.idFilm = idFilm;
        this.idPrenotazione = idPrenotazione;
        this.data = data;
        this.importo= importo;
    }

    public String getIdTransazione() {
        return idTransazione;
    }

    @Override
    public String toString() {
        return  idTransazione +
                "," + idPrenotazione +
                "," + idFilm+
                "," + data +
                "," + importo;
    }
}
