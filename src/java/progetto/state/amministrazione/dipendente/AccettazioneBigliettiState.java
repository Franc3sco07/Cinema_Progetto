package progetto.state.amministrazione.dipendente;

import progetto.Main;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.amministrazione.dipendente.AccettazioneBigliettiElettronici;

public class AccettazioneBigliettiState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new AccettazioneBigliettiElettronici());
        context.setState(this);
    }
}
