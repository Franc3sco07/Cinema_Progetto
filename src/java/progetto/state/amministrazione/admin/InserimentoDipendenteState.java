package progetto.state.amministrazione.admin;

import progetto.Main;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.amministrazione.admin.InserimentoDipendente;

public class InserimentoDipendenteState implements State {
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new InserimentoDipendente());
        context.setState(this);
    }
}
