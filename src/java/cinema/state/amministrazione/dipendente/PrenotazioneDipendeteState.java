package cinema.state.amministrazione.dipendente;

import cinema.Main;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.amministrazione.dipendente.PrenotazioniDipendente;

public class PrenotazioneDipendeteState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new PrenotazioniDipendente());
        context.setState(this);
    }
}