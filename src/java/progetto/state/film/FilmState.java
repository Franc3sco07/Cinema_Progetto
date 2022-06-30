package progetto.state.film;

import progetto.Main;
import progetto.Session;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.film.FilmView;

public class FilmState implements State {
    @Override
    public void doAction(Context context) {
        context.setState(this);
        Session.getSessioneCorrente().setIdRiferimentoProiezione(null);
        Session.getSessioneCorrente().setIdRiferimentoFilm(null);
        Main.frame.aggiornaPannello(new FilmView());

    }
}
