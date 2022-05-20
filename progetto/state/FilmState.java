package progetto.state;

import progetto.view.GUI;
import progetto.view.Film;

public class FilmState implements State{
    @Override
    public void doAction(Context context) {
        GUI.aggiornaPannello(new Film());
        context.setState(this);
    }
}
