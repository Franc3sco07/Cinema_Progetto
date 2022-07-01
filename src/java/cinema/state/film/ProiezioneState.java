package cinema.state.film;


import cinema.Main;
import cinema.Session;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.film.VisualizzaProiezioni;

public class ProiezioneState implements State {
    @Override
    public void doAction(Context context) {
        Session.getSessioneCorrente().setIdRiferimentoProiezione(null);
        Main.frame.aggiornaPannello(new VisualizzaProiezioni());
        context.setState(this);
    }
}

