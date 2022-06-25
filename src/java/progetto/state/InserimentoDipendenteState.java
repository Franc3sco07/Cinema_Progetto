package progetto.state;

import progetto.Main;
import progetto.view.InserimentoDipendente;

public class InserimentoDipendenteState implements State{
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new InserimentoDipendente());
        context.setState(this);
    }
}
