package progetto.state.prenotazione;

import progetto.Main;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.prenotazione.GestionePrenotazionePosti;

public class PrenotazionePostiState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new GestionePrenotazionePosti());
        context.setState(this);
    }
}