package progetto.state.amministrazione.admin;

import progetto.Main;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.amministrazione.admin.VisualizzaVendite;

public class VisualizzaVenditeState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new VisualizzaVendite());
        context.setState(this);
    }
}
