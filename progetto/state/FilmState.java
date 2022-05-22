package progetto.state;

import progetto.Main;
import progetto.view.GUI;
import progetto.view.Film;

public class FilmState implements State{
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new Film());
        context.setState(this);
    }
}
