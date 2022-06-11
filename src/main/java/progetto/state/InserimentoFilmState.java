package progetto.state;

import progetto.Main;
import progetto.view.InserimentoFilm;

public class InserimentoFilmState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new InserimentoFilm());
        context.setState(this);
    }
}
