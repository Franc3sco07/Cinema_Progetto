package progetto.model;

public class Prenotazione {
    String id, idGeneratore, idProiezione, idFilm, idTransazione, postoAssegnato;


    public Prenotazione(String id, String idGeneratore, String idProiezione, String idFilm, String idTransazione, String postoAssegnato) {
        this.id = id;
        this.idGeneratore = idGeneratore;
        this.idProiezione = idProiezione;
        this.idFilm = idFilm;
        this.idTransazione = idTransazione;
        this.postoAssegnato = postoAssegnato;
    }

    @Override
    public String toString() { return id + ',' + idGeneratore + ',' + idProiezione + ',' + idFilm + ',' + idTransazione + "," + postoAssegnato; }

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
}