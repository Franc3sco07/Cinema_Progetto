package progetto.model;

public class Film {
    private String id, nome, locandina, info, prezzo;

    public Film(String id, String nome, String locandina, String info, String prezzo) {
        this.id = id.trim();
        this.nome = nome.trim();
        this.locandina = locandina.trim();
        this.info = info.trim();
        this.prezzo = prezzo.trim();
    }

    @Override
    public String toString() {
        return id + ',' + nome + ',' + locandina + ',' + info + ',' + prezzo;
    }

    public String getId() {
        return id;
    }
}


