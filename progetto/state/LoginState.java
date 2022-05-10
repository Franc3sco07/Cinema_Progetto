package progetto.state;

import progetto.view.GUI;
import progetto.view.Login;

public class LoginState implements State{


    @Override
    public void doAction(Context context) {
        GUI.aggiornaPannello(new Login());
        context.setState(this);
    }
}
