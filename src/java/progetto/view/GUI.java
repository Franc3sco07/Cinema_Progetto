
package progetto.view;

import progetto.Main;
import progetto.Session;
import progetto.state.*;

import javax.swing.*;

/**
 * Frame dell'applicazione
 */

public class GUI extends javax.swing.JFrame {
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
     * Metodo per generare il menu in base al tipo di utente conesso
     */
    public void showMenu(){
        jMenuBar1 = new javax.swing.JMenuBar();
        profiloMenu = new javax.swing.JMenuItem();
        jMenuLogout = new javax.swing.JMenuItem();
        filmMenu = new javax.swing.JMenuItem();
        filmMenu.setText("Film");
        filmMenu.addActionListener(evt -> new FilmState().doAction(Main.context));
        jMenuBar1.add(filmMenu);

        if (Session.getSessioneCorrente().getUtenteLoggato().getTipo().equals("U")){
            prenotazioniMenu = new javax.swing.JMenuItem();
            prenotazioniMenu.setText("Prenotazioni"); //visibile solo per gli utenti
            prenotazioniMenu.addActionListener(evt-> new PrenotazioniState().doAction(Main.context));
            jMenuBar1.add(prenotazioniMenu);
        }

        if (Session.getSessioneCorrente().getUtenteLoggato().getTipo().equals("D")){
            gestioneBigliettiMenu = new javax.swing.JMenuItem();
            gestioneBigliettiMenu.setText("Gestione Biglietti"); //visibile solo per i dipendenti
            gestioneBigliettiMenu.addActionListener(evt-> new PrenotazioniState().doAction(Main.context));
            accettazioneBiglietti = new javax.swing.JMenuItem();
            accettazioneBiglietti.setText("Accettazione biglietti"); //visibile solo per i dipendenti
            accettazioneBiglietti.addActionListener(evt-> new AccettazioneBigliettiState().doAction(Main.context));
            jMenuBar1.add(gestioneBigliettiMenu);
            jMenuBar1.add(accettazioneBiglietti);
        }

        if (Session.getSessioneCorrente().getUtenteLoggato().getTipo().equals("A")){
            visualizzaVendite = new javax.swing.JMenuItem();
            visualizzaDipendenti = new javax.swing.JMenuItem();
            inserisciFilm = new javax.swing.JMenuItem();
            visualizzaVendite.setText("Visualizza vendite");
            visualizzaVendite.addActionListener(evt -> new VisualizzaVenditeState().doAction(Main.context));
            jMenuBar1.add(visualizzaVendite);
            visualizzaDipendenti.setText("Visualizza dipendenti");
            visualizzaDipendenti.addActionListener(evt -> new GestioneDipendentiState().doAction(Main.context));
            jMenuBar1.add(visualizzaDipendenti);
            inserisciFilm.setText("Inserimento Film");
            inserisciFilm.addActionListener(evt -> new InserimentoFilmState().doAction(Main.context));
            jMenuBar1.add(inserisciFilm);
        }

        profiloMenu.setText("Profilo");
        profiloMenu.addActionListener(evt -> new VisualizzaDatiState().doAction(Main.context));
        jMenuBar1.add(profiloMenu);
        jMenuLogout.setText("Logout");
        jMenuLogout.addActionListener(evt -> {
            Main.frame.hideMenu();
            Session.getSessioneCorrente().logOut();
            new LoginState().doAction(Main.context);
        });
        jMenuBar1.add(jMenuLogout);
        jMenuBar1.setVisible(true);
        setJMenuBar(jMenuBar1);

    }

    /**
     * Metodo per eliminare il menu, e lo rimuoverlo dalla grafica
     */
    public void hideMenu(){
        jMenuBar1 = null;
        this.setJMenuBar(null);
    }

    /**
     * Metodo per poter aggiornare la grafica
     * @param nuovoPanello il panello che aggiornera la grafica
     */
    public void aggiornaPannello(JPanel nuovoPanello){
        this.getContentPane().removeAll();
        this.add(nuovoPanello);
        this.setResizable(false);
        this.pack();
        this.getContentPane().requestFocusInWindow();
    }

    private javax.swing.JMenuItem filmMenu;
    private javax.swing.JMenuItem prenotazioniMenu;
    private javax.swing.JMenuItem profiloMenu;
    private javax.swing.JMenuItem gestioneBigliettiMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem visualizzaVendite;
    private javax.swing.JMenuItem visualizzaDipendenti;
    private javax.swing.JMenuItem jMenuLogout;
    private javax.swing.JMenuItem inserisciFilm;
    private javax.swing.JMenuItem accettazioneBiglietti;

}
