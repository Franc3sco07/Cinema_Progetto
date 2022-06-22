package progetto.state;

import progetto.Main;
import progetto.view.GestionePrenotazionePosti;
import progetto.view.VisualizzaPrenotazioni;

public class ModificaPrenotazioneState implements State {


    @Override
    public void doAction(Context context) {
        context.setState(this);
        Main.frame.aggiornaPannello(new GestionePrenotazionePosti());

    }
}
