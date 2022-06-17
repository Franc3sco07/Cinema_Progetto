package progetto.state;

import progetto.Main;
import progetto.view.AccettazioneBigliettiElettronici;

public class AccettazioneBigliettiState implements State {
    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new AccettazioneBigliettiElettronici());
        context.setState(this);
    }
}
