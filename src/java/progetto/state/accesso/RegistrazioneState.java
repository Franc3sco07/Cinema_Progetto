package progetto.state.accesso;

import progetto.Main;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.accesso.Registrazione;

public class RegistrazioneState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new Registrazione());
        context.setState(this);
    }
}
