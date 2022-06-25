package progetto.state;

import progetto.Main;
import progetto.view.GestionePrenotazionePosti;

public class ModificaPrenotazioneState implements State {


    @Override
    public void doAction(Context context) {
        context.setState(this);
        Main.frame.aggiornaPannello(new GestionePrenotazionePosti());

    }
}
