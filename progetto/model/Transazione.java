package progetto.model;

import java.util.Date;

public class Transazione {
    private String idTransazione, idFilm, idPrenotazione;
    private Date data;

    public Transazione(String idTransazione, String idFilm, String idPrenotazione, Date data) {
        this.idTransazione = idTransazione;
        this.idFilm = idFilm;
        this.idPrenotazione = idPrenotazione;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Transazione{" +
                "idTransazione='" + idTransazione + '\'' +
                ", idFilm='" + idFilm + '\'' +
                ", idPrenotazione='" + idPrenotazione + '\'' +
                ", data=" + data +
                '}';
    }
}
