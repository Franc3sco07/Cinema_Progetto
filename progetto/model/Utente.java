package progetto.model;

public class Utente {

    private String id, tipo, nome, cognome, numeroCellulare, email, codiceFiscale, password ;

    public Utente(String id, String tipo, String nome, String cognome, String email, String numeroCellulare, String codiceFiscale, String password) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.cognome = cognome;
        this.numeroCellulare = numeroCellulare;
        this.email = email;
        this.codiceFiscale = codiceFiscale;
        this.password = password;
    }

    @Override
    public String toString() {
        return id + ',' + tipo + ',' + nome + ',' + cognome + ',' + email + ',' + numeroCellulare + ',' + codiceFiscale + ',' + password ;
    }

    public String getId() {
        return id.trim();
    }

    public String getEmail() {
        return email.trim();
    }

    public String getPassword() {
        return password.trim();
    }
}