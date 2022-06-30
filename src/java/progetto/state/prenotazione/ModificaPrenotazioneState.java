package progetto.state.prenotazione;

import progetto.Main;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.prenotazione.GestionePrenotazionePosti;

public class ModificaPrenotazioneState implements State {


    @Override
    public void doAction(Context context) {
        context.setState(this);
        Main.frame.aggiornaPannello(new GestionePrenotazionePosti());

    }
}
