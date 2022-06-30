package progetto.state.amministrazione.admin;

import progetto.Main;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.amministrazione.admin.InserimentoFilm;

public class InserimentoFilmState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new InserimentoFilm());
        context.setState(this);
    }
}
