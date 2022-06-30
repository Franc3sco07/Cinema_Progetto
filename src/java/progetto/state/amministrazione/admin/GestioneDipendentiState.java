package progetto.state.amministrazione.admin;

import progetto.Main;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.amministrazione.admin.GestioneDipendenti;

public class GestioneDipendentiState implements State {
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new GestioneDipendenti());
        context.setState(this);
    }
}
