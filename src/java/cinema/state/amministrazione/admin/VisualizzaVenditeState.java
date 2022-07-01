package cinema.state.amministrazione.admin;

import cinema.Main;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.amministrazione.admin.VisualizzaVendite;

public class VisualizzaVenditeState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new VisualizzaVendite());
        context.setState(this);
    }
}
