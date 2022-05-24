package progetto.state;

import progetto.Main;
import progetto.Session;
import progetto.view.GUI;
import progetto.view.Film;

public class FilmState implements State{
    @Override
    public void doAction(Context context) {
        Session.getSessioneCorrente().setIdRiferimentoProiezione(null);
        Session.getSessioneCorrente().setIdRiferimentoFilm(null);
        Main.frame.aggiornaPannello(new Film());
        context.setState(this);
    }
}
