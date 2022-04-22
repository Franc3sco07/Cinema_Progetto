package progetto.model;

import java.util.Arrays;
import java.util.Date;

public class Proiezione {
    private String id, idFilm, idSala, prezzo;
    private int[][] postiAttualiOccupati;
    private int postiLiberi;
    private Date data;

    public Proiezione(String id, String idFilm, String idSala, String prezzo,Date data, int[][] postiAttualiOccupati, int postiLiberi ) {
        this.id = id;
        this.idFilm = idFilm;
        this.idSala = idSala;
        this.prezzo = prezzo;
        this.postiAttualiOccupati = postiAttualiOccupati;
        this.postiLiberi = postiLiberi;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "" + id +
                "," + idFilm +
                ","+ idSala +
                ",'" + prezzo +
                "," + data +
                "," + Arrays.toString(postiAttualiOccupati) +
                "," + postiLiberi;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public Date getData() {
        return data;
    }
}
