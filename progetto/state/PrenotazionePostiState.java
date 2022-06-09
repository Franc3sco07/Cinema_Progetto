package progetto.state;

import progetto.Main;
import progetto.view.GestionePrenotazionePosti;

public class PrenotazionePostiState implements State{
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new GestionePrenotazionePosti());
        context.setState(this);
    }
}