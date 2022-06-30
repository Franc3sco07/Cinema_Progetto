package progetto.model;

import progetto.functions.FunzionalitaDate;
import progetto.functions.TraduttoreMatrice;
import progetto.functions.ValidatoreCampi;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

public class Proiezione {
    private String id, idFilm, idSala, prezzo;
    private int[][] postiAttualiOccupati;
    private int postiLiberi;
    private Date data;

    public Proiezione(String id, String idFilm, String idSala, String prezzo, Date data, int postiLiberi, int[][] postiAttualiOccupati) {
        this.id = id;
        this.idFilm = idFilm;
        this.idSala = idSala;
        this.prezzo = prezzo;
        this.postiAttualiOccupati = postiAttualiOccupati;
        this.postiLiberi = postiLiberi;
        this.data = data;
    }

    /**
     * Funzione che data una stringa con le informazioni di una proiezione, lo trasforma in un oggetto di tipo Proiezione
     *
     * @param proiezione Stringa contenente le informazioni della proiezione
     * @return un oggetto proiezione con le informazioni della stringa
     */
    public static Optional<Proiezione> stringToProiezione(String proiezione) {
        String[] proizioneDati = proiezione.split(",");
        Date d;
        if (proizioneDati.length > 2) {
            try {
                d = ValidatoreCampi.DATEFORMAT.parse(proizioneDati[4]);
                Proiezione pr = new Proiezione(proizioneDati[0],
                        proizioneDati[1],
                        proizioneDati[2],
                        proizioneDati[3],
                        d,
                        Integer.parseInt(proizioneDati[5].trim()),
                        TraduttoreMatrice.stringToMatrice(proizioneDati[6]));
                return Optional.of(pr);
            } catch (ParseException e) {
            }
        }
        return Optional.empty();
    }

    public String getId() {
        return id.trim();
    }

    @Override
    public String toString() {
        return
                "" + id +
                        "," + idFilm +
                        "," + idSala +
                        "," + prezzo +
                        "," + ValidatoreCampi.DATEFORMAT.format(data) +
                        "," + postiLiberi +
                        "," + TraduttoreMatrice.matriceToString(postiAttualiOccupati);
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

    public String getPrezzo() {
        return prezzo.replace("\\.", ",");
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo.trim();
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

    public int compareTo(Proiezione daConfrontare) {
        if (FunzionalitaDate.stessaData(this.getData(), daConfrontare.getData())) {
            return 0;
        }
        if (FunzionalitaDate.dateSuccesive(daConfrontare.getData(), this.getData())) {
            return 1;
        } else {
            return -1;
        }
    }
}
