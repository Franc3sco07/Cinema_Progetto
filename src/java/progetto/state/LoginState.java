package progetto.state;

import progetto.Main;
import progetto.Session;
import progetto.view.Login;

import java.awt.*;

public class LoginState implements State{


    @Override
    public void doAction(Context context) {
        if(Session.getSessioneCorrente()!=null){
            Main.frame.hideMenu();
            Session.getSessioneCorrente().logOut();
        }

        Main.frame.aggiornaPannello(new Login());
        context.setState(this);
    }
}
