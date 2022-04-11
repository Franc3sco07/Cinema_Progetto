package progetto.model;

import java.util.Arrays;

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
        return "Sala{" +
                "id='" + id + '\'' +
                ", numeroPosti=" + numeroPosti +
                ", disposizionePosti=" + Arrays.toString(disposizionePosti) +
                '}';
    }
}
