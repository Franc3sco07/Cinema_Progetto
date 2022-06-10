package progetto.state;

import progetto.Main;
import progetto.view.GestioneDipendenti;

public class GestioneDipendentiStante implements State{
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new GestioneDipendenti());
        context.setState(this);
    }
}
