package progetto.state;

import progetto.Main;
import progetto.view.VisualizzaVendite;

public class VisualizzaVenditeState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new VisualizzaVendite());
        context.setState(this);
    }
}
