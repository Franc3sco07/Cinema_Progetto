package cinema.state.profilo;

import cinema.Main;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.profilo.ModificaPassword;

public class ModificaPasswordState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new ModificaPassword());
        context.setState(this);
    }
}
