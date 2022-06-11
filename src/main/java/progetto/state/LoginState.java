package progetto.state;

import progetto.Main;
import progetto.Session;
import progetto.view.GUI;
import progetto.view.Login;

public class LoginState implements State{


    @Override
    public void doAction(Context context) {
        if(Session.getSessioneCorrente()!=null){
            Session.getSessioneCorrente().logOut();
        }

        Main.frame.aggiornaPannello(new Login());
        context.setState(this);
    }
}
