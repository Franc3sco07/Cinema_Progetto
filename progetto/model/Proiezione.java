package progetto.model;

import progetto.functions.TraduttoreMatrice;
import progetto.functions.ValidatoreCampi;

import java.util.Date;

public class Proiezione {
    private String id, idFilm, idSala, prezzo;
    private int[][] postiAttualiOccupati;
    private int postiLiberi;
    private Date data;

    public Proiezione( String id, String idFilm, String idSala, String prezzo, Date data, int postiLiberi, int[][] postiAttualiOccupati ) {
        this.id = id;
        this.idFilm = idFilm;
        this.idSala = idSala;
        this.prezzo = prezzo;
        this.postiAttualiOccupati = postiAttualiOccupati;
        this.postiLiberi = postiLiberi;
        this.data = data;
    }

    public String getId() {
        return id.trim();
    }

    @Override
    public String toString() {
        return
                "" + id +
                "," + idFilm +
                ","+ idSala +
                ",'" + prezzo +
                "," + ValidatoreCampi.DATEFORMAT.format(data) +
                "," + postiLiberi +
                "," + TraduttoreMatrice.matriceToString(postiAttualiOccupati) ;
    }

    public String getIdFilm() {
        return idFilm.trim();
    }

    public String getIdSala() {
        return idSala;
    }

    public Date getData() {
        return data;
    }

    public int[][] getPostiAttualiOccupati() {
        return postiAttualiOccupati;
    }

    public void setPostiAttualiOccupati(int[][] postiAttualiOccupati) {
        this.postiAttualiOccupati = postiAttualiOccupati;
    }

    public int getPostiLiberi() {
        return postiLiberi;
    }

    public void setPostiLiberi(int postiLiberi) {
        this.postiLiberi = postiLiberi;
    }
}
