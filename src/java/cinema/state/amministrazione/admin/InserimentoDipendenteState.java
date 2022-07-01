package cinema.state.amministrazione.admin;

import cinema.Main;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.amministrazione.admin.InserimentoDipendente;

public class InserimentoDipendenteState implements State {
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new InserimentoDipendente());
        context.setState(this);
    }
}
