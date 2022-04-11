package progetto.model;

public class Film {
    private String id, nome, email, prezzo, locandina, info;

    public Film(String id, String nome, String email, String prezzo, String locandina, String info) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.prezzo = prezzo;
        this.locandina = locandina;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", prezzo='" + prezzo + '\'' +
                ", locandina='" + locandina + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}


