package progetto.model;

import progetto.functions.ValidatoreCampi;

import java.util.Date;

public class Prenotazione {
    String id, idGeneratore, idProiezione, idFilm, postoAssegnato, prezzo;
    Date data;

    public Prenotazione(String id, String idGeneratore, String idProiezione, String idFilm, Date data, String prezzo, String postoAssegnato) {
        this.id = id;
        this.idGeneratore = idGeneratore;
        this.idProiezione = idProiezione;
        this.idFilm = idFilm;
        this.postoAssegnato = postoAssegnato;
        this.prezzo = prezzo;
        this.data = data;
    }

    @Override
    public String toString() { return id + ',' + idGeneratore + ',' + idProiezione + ',' + idFilm + ',' + ValidatoreCampi.DATEFORMAT.format(data) + "," + prezzo + "," + postoAssegnato; }

    public String getId() { return id; }

    public String getIdGeneratore() {
        return idGeneratore;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public String getIdProiezione() {
        return idProiezione;
    }

    public String getPrezzo(){
        return prezzo;
    }

    public String getPostoAssegnato() {
        return postoAssegnato;
    }

    public Date getData() {
        return data;
    }

    public void setPostoAssegnato(String postoAssegnato) {
        this.postoAssegnato = postoAssegnato;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }
}