package cinema.model;

public enum TipiUtente {
    ADMIN("A"),
    UTENTE("U"),
    DIPENDENTE("D");

    public final String tipo;

    private TipiUtente(String label) {
        this.tipo = label;
    }


}
