package progetto;

import progetto.Controller.*;
import progetto.functions.TraduttoreMatrice;
import progetto.functions.ValidatoreCampi;
import progetto.model.*;
import progetto.state.Context;
import progetto.state.LoginState;
import progetto.view.GUI;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;


public class Main {
    public static Context context = new Context();
    public static GUI frame ;
    //public static String path;
   /* public static void testControllerFIlm(){


        /* ControllerFilm f = new ControllerFilm();
        Collection<Film> film = f.getAllFilms();

        for (Iterator<Film> iterator = film.iterator(); iterator.hasNext(); ){
            System.out.println(iterator.next());
        }

        Film f1 = f.getFilmByID("203");
        System.out.println("id 203" + f1);

        Film f2 = new Film("201", "Francesco", "arancia.png", "bello", "7.50");
        System.out.println("modifica: " + f.modifyFilm(f2));

        String prova = "Francesco, prova.png, bellissimo, 90.90";
        System.out.println("Inserimento: " + f.insertFilm(prova));

        System.out.println("eliminazione: " + f.deleteFilmByID("204"));

        film = f.getAllFilms();
        for (Iterator<Film> iterator = film.iterator(); iterator.hasNext(); ){
            System.out.println(iterator.next());
        }

        ArrayList<String> ids = new ArrayList<>();
        ids.add("203");
        ids.add("201");
        System.out.println("Lista di film con gli id 203 , 201: "+ f.getAllFilmsByIdList(ids) );
    }

    public static void testControllerSala(){
        int[][] provaSala = new int[10][10];
        int i, j;

        for(i=0; i<=9; i=i+1) {
            for(j=0; j<=9; j=j+1) {
                provaSala[i][j]=0;
            }
        }

        String provaMatrice = TraduttoreMatrice.matriceToString(provaSala);
        System.out.println(provaMatrice);
        int[][] matriceTradotta = TraduttoreMatrice.stringToMatrice(provaMatrice);
        Arrays.stream(matriceTradotta).map(Arrays::toString).forEach(System.out::println);
    }

    public static void testControllerPrenotazione(){
        ControllerPrenotazione f = new ControllerPrenotazione();
        Collection<Prenotazione> prenotazioni ;

        System.out.println("generatore id 5");
        prenotazioni = f.getPrenotazioniByIDgeneratore("5");
        System.out.println(prenotazioni);

        System.out.println("film id 13");
        prenotazioni = f.getPrenotazioniByIDFilm("13");
        System.out.println(prenotazioni);

        System.out.println("proiezione id 21");
        prenotazioni = f.getPrenotazioneByIDProiezione("21");
        System.out.println(prenotazioni);

        System.out.println("insert prenotazione");
        System.out.println(f.insertPrenotazione("5, 21, 13, 14, 21: 12; 22: 12;"));

        System.out.println("delete prenotazione");
        System.out.println(f.deletePrenotazione("205"));

        System.out.println("modifica prenotazione");
        //Prenotazione newPrenotazione = new Prenotazione("201", "55","5", "21", "21.05" ,"14, 21: 12; 22: 12;");
        //System.out.println(f.modifyPrenotazione(newPrenotazione));
    }

    public static void testControllerUtente(){
        ControllerUtente f = new ControllerUtente();
        Collection<Utente> utenti;

        Utente giorgio;
        giorgio = f.getUtenteByID("2121");
        System.out.println("Utente selezionato: "+ giorgio);

        String utenteNew = "A, Luca, Nuovo, fra.98@dsada.ut, 3214213211, PPEWJDLSA21930U, banana";
        System.out.println("inserimento utente: "+ f.insertUtente(utenteNew));

        System.out.println("Utente eliminato: " + f.deleteUtenteByID("2125"));

        Utente nuovoUtente = new Utente("2123","A","Domenico", "Modificato", "3214213211","fra.98@dsada.ut", "PPEWJDLSA21930U", "banana");
        System.out.println("modificato:" + f.modifyUtente(nuovoUtente));


        System.out.println("checkEmail");
        System.out.println("checkEmail verifica presenza: " + f.checkEmail("fra.98@dsada.ut"));

        System.out.println("2checkEmail verifica assenza: " + f.checkEmail("fra.52@hotmail.ut"));

        System.out.println("verifica pass e email: " + f.login("fra.98@dsada.ut", "banana" ));


    }

    public static void testControllerProiezione() throws ParseException {
        ControllerProiezione f = new ControllerProiezione();
        Collection<Proiezione> proiezioni;

        proiezioni = f.getProiezioneByIDFilm("2123");
        System.out.println("proiezioni: " + proiezioni);


        //proiezioni = f.getProiezioneByDate(ValidatoreCampi.DATEFORMAT.parse("22-02-2022 12:12:31"));
        System.out.println("proiezioni: " + proiezioni);

        String proiezioneIns = "2123, 2, 21.5, 22-02-2022 12:12:31, 21, 21: 12; 22: 12;";
        System.out.println("proiezione inserita: " +f.insertProiezione(proiezioneIns));


        System.out.println("eliminato: " + f.deleteProiezione("2121"));

        Proiezione prova = new Proiezione("21321","2123", "2", "21.5", ValidatoreCampi.DATEFORMAT.parse("22-02-2022 12:12:31"), 21, TraduttoreMatrice.stringToMatrice("21: 12; 22: 12;"));
        System.out.println("modificato: " + f.modifyProiezione(prova));
        Date oggi = new Date();
        Collection<String> idList = f.getAllIdFilmAfterDate(oggi);
        System.out.println("Lista di film disponibili: "+idList);
    }

    public static void testControllerTransazione(){
        ControllerTransazione f = new ControllerTransazione();
        Collection<Transazione> transazioni;

        transazioni = f.getAllTransazioni();
        System.out.println("Tutte le transazioni: " + transazioni);

        transazioni = f.getTransazioniByFilmID("2134");
        System.out.println("getByFilmID: " + transazioni);

        System.out.println("eliminata: " + f.deleteTransazione("2122"));

        String transIns = "2, 2134, 22-02-2022 12:12:31, 21.2";
        System.out.println("inserimento " + f.insertTransazione(transIns));


    }

    */

    public static void main(String[] args) throws IOException, ParseException {
        frame = new GUI();
       // path = frame.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        new LoginState().doAction(context);
        frame.setVisible(true);
    }


}