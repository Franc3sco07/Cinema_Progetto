package progetto.state.amministrazione.dipendente;

import progetto.Main;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.amministrazione.dipendente.PrenotazioniDipendente;

public class PrenotazioneDipendeteState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new PrenotazioniDipendente());
        context.setState(this);
    }
}