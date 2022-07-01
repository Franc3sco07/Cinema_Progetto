package cinema.state.accesso;

import cinema.Main;
import cinema.Session;
import cinema.state.Context;
import cinema.state.State;
import cinema.view.accesso.Login;

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
