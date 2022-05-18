package progetto.state;

import progetto.view.GUI;
import progetto.view.Registrazione;

public class RegistrazioneState implements State{
    @Override
    public void doAction(Context context) {
        GUI.aggiornaPannello(new Registrazione());
        context.setState(this);
    }
}
