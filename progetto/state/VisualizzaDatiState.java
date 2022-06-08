package progetto.state;

import progetto.view.VisualizzaDati;
import progetto.Main;

public class VisualizzaDatiState implements State{
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new VisualizzaDati());
        context.setState(this);
    }
}
