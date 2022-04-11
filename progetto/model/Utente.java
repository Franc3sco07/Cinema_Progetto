package progetto.model;

public class Utente {

    private String id, nome, cognome, numeroCellulare, email, codiceFiscale, password ;

    public Utente(String id, String nome, String cognome, String numeroCellulare, String email, String codiceFiscale, String password) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.numeroCellulare = numeroCellulare;
        this.email = email;
        this.codiceFiscale = codiceFiscale;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", numeroCellulare='" + numeroCellulare + '\'' +
                ", email='" + email + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}