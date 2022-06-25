package progetto.state;

import progetto.Main;
import progetto.view.PrenotazioniDipendente;

public class PrenotazioneDipendeteState implements State{
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new PrenotazioniDipendente());
        context.setState(this);
    }
}