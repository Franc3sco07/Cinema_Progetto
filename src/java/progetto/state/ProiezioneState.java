package progetto.state;


import progetto.Main;
import progetto.Session;
import progetto.view.VisualizzaProiezioni;

public class ProiezioneState implements State {
    @Override
    public void doAction(Context context) {
        Session.getSessioneCorrente().setIdRiferimentoProiezione(null);
        Main.frame.aggiornaPannello(new VisualizzaProiezioni());
        context.setState(this);
    }
}

