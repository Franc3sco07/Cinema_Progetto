package progetto.model;

import java.util.Arrays;
import java.util.Date;

public class Proiezione {
    private String id, idFilm, idSala, prezzo;
    private int[][] postiAttualiOccupati;
    private int postiLiberi;
    private Date data;

    public Proiezione(String id, String idFilm, String idSala, String prezzo, int[][] postiAttualiOccupati, int postiLiberi, Date data) {
        this.id = id;
        this.idFilm = idFilm;
        this.idSala = idSala;
        this.prezzo = prezzo;
        this.postiAttualiOccupati = postiAttualiOccupati;
        this.postiLiberi = postiLiberi;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Proiezione{" +
                "id='" + id + '\'' +
                ", idFilm='" + idFilm + '\'' +
                ", idSala='" + idSala + '\'' +
                ", prezzo='" + prezzo + '\'' +
                ", postiAttualiOccupati=" + Arrays.toString(postiAttualiOccupati) +
                ", postiLiberi=" + postiLiberi +
                ", data=" + data +
                '}';
    }
}
