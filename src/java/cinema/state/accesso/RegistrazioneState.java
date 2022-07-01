package cinema.state.accesso;

import cinema.Main;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.accesso.Registrazione;

public class RegistrazioneState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new Registrazione());
        context.setState(this);
    }
}
