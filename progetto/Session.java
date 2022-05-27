package progetto;

import progetto.Controller.ControllerUtente;
import progetto.model.Utente;
import progetto.state.FilmState;

public class Session {
    private Utente utenteLoggato;
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
                sessioneCorrente.utenteLoggato = tmp;
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

    public Utente getUtenteLoggato() {
        return utenteLoggato;
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
        utenteLoggato = null;
        sessioneCorrente = null;
    }
}
