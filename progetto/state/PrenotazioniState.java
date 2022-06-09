package progetto.state;

import progetto.Main;
import progetto.view.VisualizzaPrenotazioni;

public class PrenotazioniState implements State{
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new VisualizzaPrenotazioni());
        context.setState(this);
    }
}
