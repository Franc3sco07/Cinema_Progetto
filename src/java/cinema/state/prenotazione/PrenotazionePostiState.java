package cinema.state.prenotazione;

import cinema.Main;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.prenotazione.GestionePrenotazionePosti;

public class PrenotazionePostiState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new GestionePrenotazionePosti());
        context.setState(this);
    }
}