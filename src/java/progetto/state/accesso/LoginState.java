package progetto.state.accesso;

import progetto.Main;
import progetto.Session;
import progetto.state.Context;
import progetto.state.State;
import progetto.view.accesso.Login;

public class LoginState implements State {


    @Override
    public void doAction(Context context) {
        if (Session.getSessioneCorrente() != null) {
            Main.frame.hideMenu();
            Session.getSessioneCorrente().logOut();
        }

        Main.frame.aggiornaPannello(new Login());
        context.setState(this);
    }
}
