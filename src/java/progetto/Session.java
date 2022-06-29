package progetto;

import progetto.Controller.ControllerUtente;
import progetto.model.Utente;

import java.util.Optional;

/**
 * Classe che gestisce la sessione dell'utente connesso
  */

public class Session {
    private Utente utenteConesso;
    private String idRiferimentoFilm, IdRiferimentoProiezione;
    private static Session sessioneCorrente;

    private Session(){
    }

    public static Boolean logIn (String eMail, String password){
        if(sessioneCorrente == null){
            Optional<Utente>  opUtente = new ControllerUtente().login( eMail.trim(), password.trim() );
            if (opUtente.isPresent() ){
                sessioneCorrente = new Session();
                sessioneCorrente.utenteConesso = opUtente.get();
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    }

    public static Session getSessioneCorrente(){
        return sessioneCorrente;
    }

    public Utente getUtenteConesso() {
        return utenteConesso;
    }

    public String getIdRiferimentoFilm() {
        return idRiferimentoFilm;
    }

    public String getIdRiferimentoProiezione() {
        return IdRiferimentoProiezione;
    }

    public void setIdRiferimentoFilm(String idRiferimentoFilm) {
        this.idRiferimentoFilm = idRiferimentoFilm;
    }

    public void setIdRiferimentoProiezione(String idRiferimentoProiezione) {
        this.IdRiferimentoProiezione = idRiferimentoProiezione;
    }

    public void logOut (){
        utenteConesso = null;
        sessioneCorrente = null;
    }

    public void setUtenteConesso(Utente utenteConesso) {
        this.utenteConesso = utenteConesso;
    }
}
