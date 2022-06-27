package progetto;

import progetto.Controller.ControllerUtente;
import progetto.model.Utente;

/**
 * Classe che gestisce la sessione dell'utente connesso
  */

public class Session {
    private Utente utenteConesso;
    private String idRiferimentoFilm, IdRiferimentoProiezione;
    private static Session sessioneCorrente;

    private Session(){
    }

    public static Session logIn (String eMail, String password){
        if(sessioneCorrente == null){
            ControllerUtente utente = new ControllerUtente();
            Utente tmp = utente.login( eMail.trim(), password.trim() );
            if (tmp != null ){
                sessioneCorrente = new Session();
                sessioneCorrente.utenteConesso = tmp;
                return sessioneCorrente;
            }else{
                return null;
            }
        }else{
            return sessioneCorrente;
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
