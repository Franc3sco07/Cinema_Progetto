package cinema.view.prenotazione;

import cinema.controller.ControllerPrenotazione;
import cinema.controller.ControllerProiezione;
import cinema.controller.ControllerTransazione;
import cinema.Main;
import cinema.Session;
import cinema.funzioni.GestioneFile;
import cinema.funzioni.TraduttoreMatrice;
import cinema.funzioni.TrasformatoreArrayList;
import cinema.funzioni.ValidatoreCampi;
import cinema.model.Prenotazione;
import cinema.model.Proiezione;
import cinema.model.TipiUtente;
import cinema.model.Transazione;
import cinema.state.amministrazione.dipendente.PrenotazioneDipendeteState;
import cinema.state.film.FilmState;
import cinema.state.film.ProiezioneState;
import cinema.state.prenotazione.ModificaPrenotazioneState;
import cinema.state.prenotazione.PrenotazioniState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Classe GestionePrenotazionePosti
 * Classe che permette la gestione dei posti
 */

public class GestionePrenotazionePosti extends javax.swing.JPanel {
    private final int panel_lunghezza = 770;
    private final int panel_altezza = 357;
    private ArrayList<String> postiSelezionati = new ArrayList();
    private String imgNome = "poltrona2.png";
    private Color selezionato = new Color(0, 125, 0);
    private Color cambiato = new Color(125, 125, 0);
    private ImageIcon poltronaIcon;
    private int[][] posti;
    private String proizioneId;
    private javax.swing.JButton prenota;
    private javax.swing.JButton indietro;
    private javax.swing.JPanel tmpPanelloPosti;

    public GestionePrenotazionePosti() {

        initComponents();
    }

    private void initComponents() {
        //String proizioneId;
        prenota = new javax.swing.JButton();
        indietro = new javax.swing.JButton();
        JPanel panelloPosti = new JPanel();

        if (Main.context.getState() instanceof ModificaPrenotazioneState) {
            String idPrenotazione = Session.getSessioneCorrente().getIdRiferimentoProiezione();
            Optional<Prenotazione> el = new ControllerPrenotazione().getPrenotazioneById(idPrenotazione);
            if (el.isPresent()) {
                proizioneId = el.get().getIdProiezione();
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            proizioneId = Session.getSessioneCorrente().getIdRiferimentoProiezione();
        }
        Optional<Proiezione> op = new ControllerProiezione().getProiezioneByID(proizioneId);
        if (op.isPresent()) {
            posti = op.get().getPostiAttualiOccupati();
        } else {
            throw new IllegalArgumentException();
        }

        int righe = posti.length;
        int colonne = posti[0].length;
        poltronaIcon = GestioneFile.apriImmagine(imgNome);
        Image image = poltronaIcon.getImage();
        int lunghezza_img = panel_lunghezza / (colonne);
        int altezza_img = panel_altezza / (righe);

        //calcolo dalla grandezza delle icone della poltrona per la prenotazione, calcola anche la grandezza del panel effettiva
        Image newIcon;
        int lunghezza_finale;
        int altezza_finale;
        if (lunghezza_img < altezza_img) {
            lunghezza_finale = lunghezza_img * colonne;
            altezza_finale = lunghezza_img * righe;
            newIcon = image.getScaledInstance(lunghezza_img, lunghezza_img, java.awt.Image.SCALE_SMOOTH);
        } else {
            lunghezza_finale = altezza_img * colonne;
            altezza_finale = altezza_img * righe;
            newIcon = image.getScaledInstance(altezza_img, altezza_img, java.awt.Image.SCALE_SMOOTH);
        }
        //fine calcolo

        //Gestione se la classe viene creata mentre si ?? nello state ModificaPrenotazioneState
        if (Main.context.getState() instanceof ModificaPrenotazioneState) {
            String idPrenotazione = Session.getSessioneCorrente().getIdRiferimentoProiezione();
            Optional<Prenotazione> op1 = new ControllerPrenotazione().getPrenotazioneById(idPrenotazione);
            if (op1.isPresent()) {
                Prenotazione pr = op1.get();
                int[][] postiDaCambiare = TraduttoreMatrice.stringToMatrice(pr.getPostoAssegnato());
                for (int i = 0; i < postiDaCambiare.length; i++) {
                    posti[postiDaCambiare[i][0]][postiDaCambiare[i][1]] = 3;
                    postiSelezionati.add(postiDaCambiare[i][0] + ":" + postiDaCambiare[i][1] + ";");
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        //fine

        poltronaIcon = new ImageIcon(newIcon);
        tmpPanelloPosti = new javax.swing.JPanel(new GridLayout(righe, colonne));
        tmpPanelloPosti.setSize(lunghezza_finale, altezza_finale);
        JButton postoDaInserire;
        // Creazione della sala nel panello, usando la matrice dei posti.
        // 0= corridoio
        // 1 = posto disponibile
        // 2 = posto occupato
        // 3 = posto gi?? prenotato dall' utente (solo modifica)
        for (int x = 0; x < righe; x++) {
            for (int y = 0; y < colonne; y++) {
                postoDaInserire = new JButton(poltronaIcon);
                postoDaInserire.setSize(new Dimension(poltronaIcon.getIconWidth(), poltronaIcon.getIconHeight()));
                postoDaInserire.setBorder(null);
                if (posti[x][y] == 0) {

                    postoDaInserire.setVisible(false);
                } else if (posti[x][y] == 2) {

                    postoDaInserire.setBackground(Color.RED);
                    postoDaInserire.setEnabled(false);
                    postoDaInserire.setToolTipText("Posto occupato");
                } else if (posti[x][y] == 3) {

                    postoDaInserire.setBackground(selezionato);
                    postoDaInserire.setName(x + ":" + y);
                    postoDaInserire.setToolTipText("Annulla selezione");
                    postoDaInserire.addActionListener(this::gestoreBottonePostoPrenotato);
                } else {

                    postoDaInserire.setName(x + ":" + y);
                    postoDaInserire.setToolTipText("Seleziona posto " + x + "," + y);
                    postoDaInserire.addActionListener(this::gestoreBottonePostoLibero);
                }

                tmpPanelloPosti.add(postoDaInserire);
            }
        }

        if (Main.context.getState() instanceof ModificaPrenotazioneState) {
            prenota.setText("Modifica");
            prenota.setToolTipText("Modifica la prenotazione prima di continuare");
        } else {
            prenota.setText("Prenota");
            prenota.setToolTipText("Seleziona un posto prima di continuare");
        }
        prenota.setEnabled(false);
        prenota.addActionListener(evt -> {
            if (postiSelezionati.size() > 0) {
                Optional<Proiezione> op2 = new ControllerProiezione().getProiezioneByID(proizioneId);
                if (op2.isEmpty()) {
                    throw new IllegalArgumentException();
                }
                Proiezione proiezione = op2.get();
                float prezzo = Float.parseFloat(proiezione.getPrezzo().trim()) * postiSelezionati.size();
                String prezzoTotale = new String("" + prezzo).replace(",", "\\.");

                if (Main.context.getState() instanceof ModificaPrenotazioneState) {
                    for (String itr : postiSelezionati) {
                        int[][] p = TraduttoreMatrice.stringToMatrice(itr);
                        posti[p[0][0]][p[0][1]] = 2;
                    }

                    Optional<Prenotazione> op3 = new ControllerPrenotazione().getPrenotazioneById(Session.getSessioneCorrente().getIdRiferimentoProiezione());
                    if (op3.isPresent()) {
                        Prenotazione prenotazioneMod = op3.get();
                        prenotazioneMod.setPrezzo(prezzoTotale);
                        prenotazioneMod.setPostoAssegnato(TrasformatoreArrayList.arrayListToStringMat(postiSelezionati));
                        new ControllerPrenotazione().modifyPrenotazione(prenotazioneMod);
                        JOptionPane.showMessageDialog(null, "Modifica effettuata con sucesso!\n Verrai reindirizzato alle prenotazioni\n");
                        if (Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.DIPENDENTE.tipo)) {
                            Optional<Transazione> opTransazione = new ControllerTransazione().getTransazioneByIDPrenotazione(prenotazioneMod.getId());
                            if (opTransazione.isPresent()) {
                                Transazione transazioneMod = opTransazione.get();
                                transazioneMod.setImporto(prezzoTotale);
                                new ControllerTransazione().modifyTransazione(transazioneMod);
                            } else {
                                String transazione = prenotazioneMod.getId()
                                        + "," + Session.getSessioneCorrente().getIdRiferimentoFilm()
                                        + "," + ValidatoreCampi.DATEFORMAT.format(proiezione.getData())
                                        + "," + prezzoTotale;
                                new ControllerTransazione().insertTransazione(transazione);
                            }

                        }
                        new PrenotazioniState().doAction(Main.context);
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    String prenotazione = Session.getSessioneCorrente().getUtenteConesso().getId()
                            + "," + Session.getSessioneCorrente().getIdRiferimentoProiezione()
                            + "," + Session.getSessioneCorrente().getIdRiferimentoFilm()
                            + "," + ValidatoreCampi.DATEFORMAT.format(proiezione.getData())
                            + "," + prezzoTotale
                            + "," + TrasformatoreArrayList.arrayListToStringMat(postiSelezionati);
                    String idPrenotazione = new ControllerPrenotazione().insertPrenotazione(prenotazione);

                    if (Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.DIPENDENTE.tipo)) {

                        String transazione = idPrenotazione
                                + "," + Session.getSessioneCorrente().getIdRiferimentoFilm()
                                + "," + ValidatoreCampi.DATEFORMAT.format(proiezione.getData())
                                + "," + prezzoTotale;
                        new ControllerTransazione().insertTransazione(transazione);
                    }
                    JOptionPane.showMessageDialog(null, "Prenotazione effettuata con sucesso!\n Verrai reindirizzato al catalogo film");
                    new FilmState().doAction(Main.context);
                }
                Optional<Proiezione> op4 = new ControllerProiezione().getProiezioneByID(proizioneId);
                if (op4.isPresent()) {
                    Proiezione modified = op4.get();
                    modified.setPostiAttualiOccupati(posti);
                    modified.setPostiLiberi(modified.getPostiLiberi() - postiSelezionati.size());
                    new ControllerProiezione().modifyProiezione(modified);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        });

        indietro.setText("Indietro");
        indietro.setToolTipText("Ritorna alle proiezioni");
        indietro.addActionListener(evt -> {
            if (Main.context.getState() instanceof ModificaPrenotazioneState) {
                if (Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.DIPENDENTE.tipo)) {
                    new PrenotazioneDipendeteState().doAction(Main.context);
                } else {
                    new PrenotazioniState().doAction(Main.context);
                }
            } else {
                new ProiezioneState().doAction(Main.context);
            }
        });

        panelloPosti.setSize(panel_lunghezza, panel_altezza);
        panelloPosti.add(tmpPanelloPosti, CENTER_ALIGNMENT);
        panelloPosti.setVisible(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelloPosti, javax.swing.GroupLayout.DEFAULT_SIZE, panel_lunghezza, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(indietro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(prenota, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelloPosti, javax.swing.GroupLayout.DEFAULT_SIZE, panel_altezza, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(indietro)
                                        .addComponent(prenota))
                                .addContainerGap())
        );
    }

    private void gestoreBottonePostoLibero(ActionEvent evt) {
        JButton source = (JButton) evt.getSource();
        if (source.getBackground() != selezionato) {
            String name = source.getName();
            postiSelezionati.add(name + ";");
            int numbers[][] = TraduttoreMatrice.stringToMatrice(name);
            posti[numbers[0][0]][numbers[0][1]] = 2;

            if (!prenota.isEnabled()) {
                prenota.setToolTipText(null);
                prenota.setEnabled(true);
            }

            source.setBackground(selezionato);
            source.setToolTipText("Annulla selezione");
        } else {

            String name = source.getName();
            postiSelezionati.remove(name + ";");
            int numbers[][] = TraduttoreMatrice.stringToMatrice(name);
            posti[numbers[0][0]][numbers[0][1]] = 1;
            source.setBackground(null);
            source.setToolTipText("Seleziona posto " + source.getName().replaceAll(":", ","));
            if (postiSelezionati.size() < 1) {

                prenota.setToolTipText("Seleziona un posto prima di continuare");
                prenota.setEnabled(false);
            }
        }
    }

    private void gestoreBottonePostoPrenotato(ActionEvent evt) {
        JButton source = (JButton) evt.getSource();
        if (source.getBackground() == selezionato) {

            String name = source.getName();
            source.setToolTipText("Seleziona nuovamente posto " + source.getName().replaceAll(":", ","));
            postiSelezionati.remove(name + ";");
            int numbers[][] = TraduttoreMatrice.stringToMatrice(name);
            posti[numbers[0][0]][numbers[0][1]] = 1;
            if (postiSelezionati.size() < 1) {
                prenota.setToolTipText("Seleziona un posto prima di continuare");
                prenota.setEnabled(false);
            }
            if (!prenota.isEnabled()) {
                prenota.setToolTipText(null);
                prenota.setEnabled(true);
            }
            source.setBackground(cambiato);
        } else {

            String name = source.getName();
            postiSelezionati.add(name + ";");
            int numbers[][] = TraduttoreMatrice.stringToMatrice(name);
            posti[numbers[0][0]][numbers[0][1]] = 2;

            if (!prenota.isEnabled()) {
                prenota.setToolTipText(null);
                prenota.setEnabled(true);
            }
            source.setBackground(selezionato);
            source.setToolTipText("Annulla selezione");

        }


    }
}
