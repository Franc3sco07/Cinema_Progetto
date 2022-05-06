package progetto.Controller;

import progetto.database.Gestione_db;
import progetto.model.Utente;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ControllerUtente {
    private final String tableName = "Utente.csv";

    public Utente getUtenteByID(String IDUtente){
        String stringaUtente = Gestione_db.getRow(tableName, IDUtente);

        String[] datiFilm = stringaUtente.split(",");
        if (datiFilm.length > 1) {
            return stringToUtente( stringaUtente );
        }
        return null;
    }

    public Utente login(String email, String password){
        BufferedReader in = Gestione_db.getTable(tableName);
        Utente utente;

        try {
            String l;
            while ((l = in.readLine()) != null) {
                utente = stringToUtente(l);
                if( utente.getEmail().equals(email) && utente.getPassword().equals(password)){
                    return utente;
                }
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}

        return null;
    }

    public String insertUtente(String nuovoUtente){
        return Gestione_db.insertRow(tableName, nuovoUtente);
    }

    public String deleteUtenteByID(String IDutente){
        return Gestione_db.deleteRow(IDutente, tableName);
    }

    public String modifyUtente(Utente utenteModificato){
        return Gestione_db.modifyRow(utenteModificato.getId(), tableName, utenteModificato.toString() );
    }

    private Utente stringToUtente(String utenteString){
        String[] datiUtente = utenteString.split(",");
        return new Utente(datiUtente[0], datiUtente[1], datiUtente[2], datiUtente[3], datiUtente[4], datiUtente[5], datiUtente[6], datiUtente[7]);
    }

    // checkEmail no duplicate email
    public boolean checkEmail(String email){
        ArrayList<Utente> utenti = new ArrayList<>();

        BufferedReader in = Gestione_db.getTable(tableName);
        try {
            String l;
            while ((l = in.readLine()) != null) {
                utenti.add(stringToUtente( l ));
            }
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}

        for(Iterator<Utente> iterator = utenti.iterator(); iterator.hasNext();){
            //System.out.println(iterator.next());
            if (email.equals(iterator.next().getEmail())) {
                return false;
            }
        }
        return true;
    }

}
