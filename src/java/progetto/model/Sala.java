package progetto.model;

import progetto.functions.TraduttoreMatrice;

public class Sala {
    private String id;
    private int numeroPosti;
    private int[][] disposizionePosti;

    public Sala(String id, int numeroPosti, int[][] disposizionePosti) {
        this.id = id;
        this.numeroPosti = numeroPosti;
        this.disposizionePosti = disposizionePosti;
    }

    @Override
    public String toString() {
        return id + ',' + numeroPosti + ',' + TraduttoreMatrice.matriceToString(disposizionePosti) ;
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

    public  int comapareTo(Sala daConfrontare){
        int thisIdSala = Integer.parseInt(this.id.trim());
        int idDaConfrontare =  Integer.parseInt(daConfrontare.getId().trim());
        if (thisIdSala<idDaConfrontare){
            return 1;
        } else if (thisIdSala>idDaConfrontare) {
            return -1;
        }else{
            return 0;
    }
    }



}
