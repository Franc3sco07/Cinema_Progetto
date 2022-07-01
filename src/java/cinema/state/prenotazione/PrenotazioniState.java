package cinema.state.prenotazione;

import cinema.Main;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.prenotazione.VisualizzaPrenotazioni;

public class PrenotazioniState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new VisualizzaPrenotazioni());
        context.setState(this);
    }
}
