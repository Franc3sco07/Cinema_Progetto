package cinema.state.prenotazione;

import cinema.Main;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.prenotazione.GestionePrenotazionePosti;

public class ModificaPrenotazioneState implements State {


    @Override
    public void doAction(Context context) {
        context.setState(this);
        Main.frame.aggiornaPannello(new GestionePrenotazionePosti());

    }
}
