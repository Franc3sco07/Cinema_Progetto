package cinema.state.amministrazione.admin;

import cinema.Main;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.amministrazione.admin.InserimentoFilm;

public class InserimentoFilmState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new InserimentoFilm());
        context.setState(this);
    }
}
