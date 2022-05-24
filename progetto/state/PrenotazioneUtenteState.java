package progetto.state;

import progetto.Main;
import progetto.view.GestionePrenotazioneUtente;

public class PrenotazioneUtenteState implements State{
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new GestionePrenotazioneUtente());
        context.setState(this);
    }
}