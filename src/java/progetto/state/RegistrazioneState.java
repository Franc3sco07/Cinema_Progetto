package progetto.state;

import progetto.Main;
import progetto.view.Registrazione;

public class RegistrazioneState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new Registrazione());
        context.setState(this);
    }
}
