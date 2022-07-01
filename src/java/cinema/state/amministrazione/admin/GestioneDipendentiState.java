package cinema.state.amministrazione.admin;

import cinema.Main;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.amministrazione.admin.GestioneDipendenti;

public class GestioneDipendentiState implements State {
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new GestioneDipendenti());
        context.setState(this);
    }
}
