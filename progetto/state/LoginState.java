package progetto.state;

import progetto.Main;
import progetto.view.GUI;
import progetto.view.Login;

public class LoginState implements State{


    @Override
    public void doAction(Context context) {
        Main.frame.aggiornaPannello(new Login());
        context.setState(this);
    }
}
