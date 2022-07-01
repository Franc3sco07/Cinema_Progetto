package cinema.view;

import cinema.Main;
import cinema.Session;
import cinema.model.TipiUtente;
import cinema.state.accesso.LoginState;
import cinema.state.amministrazione.admin.GestioneDipendentiState;
import cinema.state.amministrazione.admin.InserimentoFilmState;
import cinema.state.amministrazione.admin.VisualizzaVenditeState;
import cinema.state.amministrazione.dipendente.AccettazioneBigliettiState;
import cinema.state.film.FilmState;
import cinema.state.prenotazione.PrenotazioniState;
import cinema.state.profilo.VisualizzaDatiState;

import javax.swing.*;

/**
 * Frame dell'applicazione
 */

public class GUI extends javax.swing.JFrame {
    private javax.swing.JMenuItem filmMenu;
    private javax.swing.JMenuItem prenotazioniMenu;
    private javax.swing.JMenuItem profiloMenu;
    private javax.swing.JMenuItem gestioneBigliettiMenu;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem visualizzaVendite;
    private javax.swing.JMenuItem visualizzaDipendenti;
    private javax.swing.JMenuItem logout;
    private javax.swing.JMenuItem inserisciFilm;
    private javax.swing.JMenuItem accettazioneBiglietti;

    public GUI() {
        initComponents();
        this.getContentPane().removeAll();
        this.pack();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    /**
     * Metodo per generare il menu in base al tipo di utente connesso
     */
    public void showMenu() {
        menu = new javax.swing.JMenuBar();
        profiloMenu = new javax.swing.JMenuItem();
        logout = new javax.swing.JMenuItem();
        filmMenu = new javax.swing.JMenuItem();
        filmMenu.setText("Film");
        filmMenu.addActionListener(evt -> new FilmState().doAction(Main.context));
        menu.add(filmMenu);

        if (Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.UTENTE.tipo)) {
            prenotazioniMenu = new javax.swing.JMenuItem();
            prenotazioniMenu.setText("Prenotazioni"); //visibile solo per gli utenti
            prenotazioniMenu.addActionListener(evt -> new PrenotazioniState().doAction(Main.context));
            menu.add(prenotazioniMenu);
        }

        if (Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.DIPENDENTE.tipo)) {
            gestioneBigliettiMenu = new javax.swing.JMenuItem();
            gestioneBigliettiMenu.setText("Gestione biglietti"); //visibile solo per i dipendenti
            gestioneBigliettiMenu.addActionListener(evt -> new PrenotazioniState().doAction(Main.context));
            accettazioneBiglietti = new javax.swing.JMenuItem();
            accettazioneBiglietti.setText("Convalida biglietti"); //visibile solo per i dipendenti
            accettazioneBiglietti.addActionListener(evt -> new AccettazioneBigliettiState().doAction(Main.context));
            menu.add(gestioneBigliettiMenu);
            menu.add(accettazioneBiglietti);
        }

        if (Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.ADMIN.tipo)) {
            visualizzaVendite = new javax.swing.JMenuItem();
            visualizzaDipendenti = new javax.swing.JMenuItem();
            inserisciFilm = new javax.swing.JMenuItem();
            visualizzaVendite.setText("Visualizza vendite");
            visualizzaVendite.addActionListener(evt -> new VisualizzaVenditeState().doAction(Main.context));
            menu.add(visualizzaVendite);
            visualizzaDipendenti.setText("Visualizza dipendenti");
            visualizzaDipendenti.addActionListener(evt -> new GestioneDipendentiState().doAction(Main.context));
            menu.add(visualizzaDipendenti);
            inserisciFilm.setText("Inserimento film");
            inserisciFilm.addActionListener(evt -> new InserimentoFilmState().doAction(Main.context));
            menu.add(inserisciFilm);
        }

        profiloMenu.setText("Profilo");
        profiloMenu.addActionListener(evt -> new VisualizzaDatiState().doAction(Main.context));
        menu.add(profiloMenu);
        logout.setText("Logout");
        logout.addActionListener(evt -> {
            Main.frame.hideMenu();
            Session.getSessioneCorrente().logOut();
            new LoginState().doAction(Main.context);
        });
        menu.add(logout);
        menu.setVisible(true);
        setJMenuBar(menu);

    }

    /**
     * Metodo per eliminare il menu, e rimuoverlo dalla grafica
     */
    public void hideMenu() {
        menu = null;
        this.setJMenuBar(null);
    }

    /**
     * Metodo per poter aggiornare la grafica
     *
     * @param nuovoPanello il panello che aggiornerà la grafica
     */
    public void aggiornaPannello(JPanel nuovoPanello) {
        this.getContentPane().removeAll();
        this.add(nuovoPanello);
        this.setResizable(false);
        this.pack();
        this.getContentPane().requestFocusInWindow();
    }

}
