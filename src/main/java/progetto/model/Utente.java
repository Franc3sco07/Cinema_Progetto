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

    public String getTipo() {
        return tipo.trim();
    }

    public String getNome() {
        return nome.trim();
    }

    public String getCognome() {
        return cognome.trim();
    }

    public String getNumeroCellulare() {
        return numeroCellulare.trim();
    }

    public String getCodiceFiscale() {
        return codiceFiscale.trim();
    }

    public void setId(String id) {
        this.id = id.trim();
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public void setCognome(String cognome) {
        this.cognome = cognome.trim();
    }

    public void setNumeroCellulare(String numeroCellulare) {
        this.numeroCellulare = numeroCellulare.trim();
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.trim();
    }
}