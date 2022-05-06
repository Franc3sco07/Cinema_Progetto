package progetto.model;

public class Film {
    private String id, nome, locandina, info, prezzo;

    public Film(String id, String nome, String locandina, String info, String prezzo) {
        this.id = id;
        this.nome = nome;
        this.locandina = locandina;
        this.info = info;
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return id + ',' + nome + ',' + locandina + ',' + info + ',' + prezzo;
    }

    public String getId() {
        return id.trim();
    }
}


