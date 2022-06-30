package progetto.state.film;


import progetto.Main;
import progetto.Session;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.film.VisualizzaProiezioni;

public class ProiezioneState implements State {
    @Override
    public void doAction(Context context) {
        Session.getSessioneCorrente().setIdRiferimentoProiezione(null);
        Main.frame.aggiornaPannello(new VisualizzaProiezioni());
        context.setState(this);
    }
}

