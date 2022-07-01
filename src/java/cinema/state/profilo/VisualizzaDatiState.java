package cinema.state.profilo;

import cinema.Main;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.profilo.VisualizzaDati;

public class VisualizzaDatiState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new VisualizzaDati());
        context.setState(this);
    }
}
