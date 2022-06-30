package progetto.state.profilo;

import progetto.Main;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.profilo.VisualizzaDati;

public class VisualizzaDatiState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new VisualizzaDati());
        context.setState(this);
    }
}
