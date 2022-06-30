package progetto;

import progetto.state.Context;
import progetto.state.accesso.LoginState;
import progetto.view.GUI;

import java.io.IOException;
import java.text.ParseException;


public class Main {
    public static Context context = new Context();
    public static GUI frame;

    public static void main(String[] args) throws IOException, ParseException {
        frame = new GUI();
        new LoginState().doAction(context);
        frame.setVisible(true);
    }


}