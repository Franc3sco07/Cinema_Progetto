package progetto.state;

import progetto.Main;
import progetto.view.VisualizzaDati;

public class VisualizzaDatiState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new VisualizzaDati());
        context.setState(this);
    }
}
