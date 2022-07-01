package cinema.model;

import cinema.funzioni.TraduttoreMatrice;

import java.util.Optional;

public class Sala {
    private String id;
    private int numeroPosti;
    private int[][] disposizionePosti;

    public Sala(String id, int numeroPosti, int[][] disposizionePosti) {
        this.id = id;
        this.numeroPosti = numeroPosti;
        this.disposizionePosti = disposizionePosti;
    }

    /**
     * Funzione che data una stringa con le informazioni di una sala, lo trasforma in un oggetto di tipo Sala
     *
     * @param salaString
     * @return
     */
    public static Optional<Sala> stringToSala(String salaString) {
        String[] datiSala = salaString.split(",");
        if (datiSala.length > 2) {
            Sala sl = new Sala(datiSala[0], Integer.parseInt(datiSala[1].trim()), TraduttoreMatrice.stringToMatrice(datiSala[2]));
            return Optional.of(sl);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return id + ',' + numeroPosti + ',' + TraduttoreMatrice.matriceToString(disposizionePosti);
    }

    public String getId() {
        return id;
    }

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public int[][] getDisposizionePosti() {
        return disposizionePosti;
    }

    public int comapareTo(Sala daConfrontare) {
        int thisIdSala = Integer.parseInt(this.id.trim());
        int idDaConfrontare = Integer.parseInt(daConfrontare.getId().trim());
        if (thisIdSala < idDaConfrontare) {
            return -1;
        } else if (thisIdSala > idDaConfrontare) {
            return 1;
        } else {
            return 0;
        }
    }


}
