package progetto;

import progetto.Controller.ControllerFilm;
import progetto.Controller.ControllerPrenotazione;
import progetto.functions.TraduttoreMatrice;
import progetto.model.Film;
import progetto.model.Prenotazione;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void testControllerFIlm(){
        ControllerFilm f = new ControllerFilm();
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
        Prenotazione newPrenotazione = new Prenotazione("201", "55","5", "21", "13", "14, 21: 12; 22: 12;");
        System.out.println(f.modifyPrenotazione(newPrenotazione));
    }





    public static void testControllerUtente(){

    }
    public static void testControllerProiezione(){

    }
    public static void testControllerTransazione(){

    }


    public static void main(String[] args) throws IOException {
        //**** verifica trim()
        testControllerFIlm();
    }
}
