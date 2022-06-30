package progetto.model;

import progetto.functions.FunzionalitaDate;
import progetto.functions.ValidatoreCampi;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

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

    /**
     * Funzione che data una stringa con le informazioni di una prenotazione, lo trasforma in un oggetto di tipo Prenotazione
     *
     * @param prenotazioneString stringa con le informazioni delle nuova prenotazione
     * @return un oggetto prenotazione con le informazioni di una prenotazione
     */
    public static Optional<Prenotazione> stringToPrenotazione(String prenotazioneString) {
        String[] datiPrenotazione = prenotazioneString.split(",");
        Date d;
        if (datiPrenotazione.length > 2) {
            try {
                d = ValidatoreCampi.DATEFORMAT.parse(datiPrenotazione[4]);
                Prenotazione elemento = new Prenotazione(datiPrenotazione[0], datiPrenotazione[1], datiPrenotazione[2], datiPrenotazione[3], d, datiPrenotazione[5], datiPrenotazione[6]);
                return Optional.of(elemento);
            } catch (ParseException e) {
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return id + ',' + idGeneratore + ',' + idProiezione + ',' + idFilm + ',' + ValidatoreCampi.DATEFORMAT.format(data) + "," + prezzo + "," + postoAssegnato;
    }

    public String getId() {
        return id;
    }

    public String getIdGeneratore() {
        return idGeneratore.trim();
    }

    public String getIdFilm() {
        return idFilm;
    }

    public String getIdProiezione() {
        return idProiezione;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public String getPostoAssegnato() {
        return postoAssegnato;
    }

    public void setPostoAssegnato(String postoAssegnato) {
        this.postoAssegnato = postoAssegnato;
    }

    public Date getData() {
        return data;
    }

    public int compareTo(Prenotazione daConfrontare) {       // da rivedere
        if (FunzionalitaDate.stessaData(this.data, daConfrontare.data)) {
            Integer thisId = Integer.parseInt(this.id);
            Integer daConfrontareId = Integer.parseInt(daConfrontare.getId());
            return thisId.compareTo(daConfrontareId);
        }
        if (FunzionalitaDate.dateSuccesive(daConfrontare.data, this.data)) {
            return 1;
        } else {
            return -1;
        }
    }
}