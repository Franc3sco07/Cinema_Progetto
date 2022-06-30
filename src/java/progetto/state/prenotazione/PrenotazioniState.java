package progetto.state.prenotazione;

import progetto.Main;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.prenotazione.VisualizzaPrenotazioni;

public class PrenotazioniState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new VisualizzaPrenotazioni());
        context.setState(this);
    }
}
