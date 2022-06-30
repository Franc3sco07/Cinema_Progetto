package progetto.model;

import java.util.Optional;

public class Utente {
    private String id, tipo, nome, cognome, numeroCellulare, email, codiceFiscale, password;

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

    /**
     * Funzione che data una stringa con le informazioni di un utente, lo trasforma in un oggetto di tipo Utente
     *
     * @param utenteString
     * @return
     */
    public static Optional<Utente> stringToUtente(String utenteString) {
        String[] datiUtente = utenteString.split(",");
        if (datiUtente.length > 2) {
            Utente ut = new Utente(datiUtente[0], datiUtente[1], datiUtente[2], datiUtente[3], datiUtente[4], datiUtente[5], datiUtente[6], datiUtente[7]);
            return Optional.of(ut);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return id + ',' + tipo + ',' + nome + ',' + cognome + ',' + email + ',' + numeroCellulare + ',' + codiceFiscale + ',' + password;
    }

    public String getId() {
        return id.trim();
    }

    public void setId(String id) {
        this.id = id.trim();
    }

    public String getEmail() {
        return email.trim();
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPassword() {
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public String getTipo() {
        return tipo.trim();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.trim();
    }

    public String getNome() {
        return nome.trim();
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getCognome() {
        return cognome.trim();
    }

    public void setCognome(String cognome) {
        this.cognome = cognome.trim();
    }

    public String getNumeroCellulare() {
        return numeroCellulare.trim();
    }

    public void setNumeroCellulare(String numeroCellulare) {
        this.numeroCellulare = numeroCellulare.trim();
    }

    public String getCodiceFiscale() {
        return codiceFiscale.trim();
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale.trim();
    }

    public int compareTo(Utente daConfrontare) {
        if (this.equals(daConfrontare)) {
            return 0;
        }

        if (this.cognome.compareTo(daConfrontare.cognome) != 0) {
            return this.cognome.compareTo(daConfrontare.cognome);
        }
        if (this.nome.compareTo(daConfrontare.nome) != 0) {
            return this.cognome.compareTo(daConfrontare.cognome);
        }
        if (this.email.compareTo(daConfrontare.email) != 0) {
            return this.cognome.compareTo(daConfrontare.cognome);
        }

        return 0;

    }
}