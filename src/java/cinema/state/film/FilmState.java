package cinema.state.film;

import cinema.Main;
import cinema.Session;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.film.FilmView;

public class FilmState implements State {
    @Override
    public void doAction(Context context) {
        context.setState(this);
        Session.getSessioneCorrente().setIdRiferimentoProiezione(null);
        Session.getSessioneCorrente().setIdRiferimentoFilm(null);
        Main.frame.aggiornaPannello(new FilmView());

    }
}
