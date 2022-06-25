package progetto.state;

import progetto.Main;
import progetto.view.ModificaPassword;

public class ModificaPasswordState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new ModificaPassword());
        context.setState(this);
    }
}
