
package progetto.view;

import progetto.Controller.ControllerPrenotazione;
import progetto.Controller.ControllerProiezione;
import progetto.Controller.ControllerTransazione;
import progetto.Main;
import progetto.Session;
import progetto.functions.GestioneFile;
import progetto.functions.TraduttoreMatrice;
import progetto.functions.TrasformatoreArrayList;
import progetto.functions.ValidatoreCampi;
import progetto.model.Prenotazione;
import progetto.model.Proiezione;
import progetto.model.Transazione;
import progetto.state.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe GestionePrenotazionePosti
 * Classe che permette la gestione dei posti
 */

public class GestionePrenotazionePosti extends javax.swing.JPanel {
    private ArrayList postiSelezionati = new ArrayList();
    private String imgNome = "poltrona2.png";
    private Color selezionato = new Color(0,125,0);
    private Color cambiato = new Color(125,125,0);
    private ImageIcon icon  ;
    private final int panel_lunghezza = 770;
    private final int panel_altezza = 357;

    private  String proizioneId;

    public GestionePrenotazionePosti() {

        initComponents();
    }


    private void initComponents() {
        //String proizioneId;
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        JPanel jPanel2 = new JPanel();

        if(Main.context.getState() instanceof ModificaPrenotazioneState){
            String idPrenotazione = Session.getSessioneCorrente().getIdRiferimentoProiezione();
            Prenotazione pr = new ControllerPrenotazione().getPrenotazioneById(idPrenotazione);
            proizioneId = pr.getIdProiezione();
        }else{
            proizioneId = Session.getSessioneCorrente().getIdRiferimentoProiezione();
        }

        int posti[][] = new ControllerProiezione().getProiezioneByID(proizioneId).getPostiAttualiOccupati();
        int righe = posti.length;
        int colonne = posti[0].length;
        icon = GestioneFile.apriImmagine(imgNome);
        Image image = icon.getImage();
        int lunghezza_img = panel_lunghezza/(colonne);
        int altezza_img =  panel_altezza/(righe);

        //calcolo dalla grandezza delle icone della poltrona per la prenotazione, calcola anche la grandezza del panel effettiva
        Image newIcon;
        int lunghezza_finale;
        int altezza_finale;
        if(lunghezza_img < altezza_img){
            lunghezza_finale = lunghezza_img*colonne;
            altezza_finale = lunghezza_img*righe;
            newIcon = image.getScaledInstance(lunghezza_img, lunghezza_img,  java.awt.Image.SCALE_SMOOTH);
        }else{
            lunghezza_finale = altezza_img*colonne;
            altezza_finale = altezza_img*righe;
            newIcon = image.getScaledInstance(altezza_img, altezza_img,  java.awt.Image.SCALE_SMOOTH);
        }
        //fine calcolo

        //Gestione se la classe viene creata mentre si è nello state ModificaPrenotazioneState
        if(Main.context.getState() instanceof ModificaPrenotazioneState){
            String idPrenotazione = Session.getSessioneCorrente().getIdRiferimentoProiezione();
            Prenotazione pr = new ControllerPrenotazione().getPrenotazioneById(idPrenotazione);
            int [][] postiDaCambiare = TraduttoreMatrice.stringToMatrice(pr.getPostoAssegnato());
            for(int i=0;i<postiDaCambiare.length;i++){
                posti[postiDaCambiare[i][0]][postiDaCambiare[i][1]] = 3;
                postiSelezionati.add(postiDaCambiare[i][0]+":"+postiDaCambiare[i][1]+";");
            }
        }
        //fine

        icon = new ImageIcon(newIcon);
        jPanel1 = new javax.swing.JPanel(new GridLayout(righe,colonne));
        jPanel1.setSize(lunghezza_finale,altezza_finale);
        JButton tmp ;
        // Creazione della sala nel panello, usando la matrice dei posti.
        // 0= corridoio , 1 = posto disponibile , 2 = posto occupato,  3 = posto già prenotato dall' utente (solo modifica)
        for(int x=0; x<righe;x++){
            for(int y=0;y<colonne;y++){
                tmp = new JButton(icon);
                tmp.setSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()));
                tmp.setBorder(null);
                if(posti[x][y] == 0){

                    tmp.setVisible(false);
                } else if (posti[x][y] == 2){

                    tmp.setBackground(Color.RED);
                    tmp.setEnabled(false);
                    tmp.setToolTipText("Posto occupato");
                }else if(posti[x][y] == 3){

                    tmp.setBackground(selezionato);
                    tmp.setName(x+":"+y);
                    tmp.setToolTipText("Annulla selezione");
                    tmp.addActionListener(evt->{
                        JButton source = (JButton) evt.getSource();
                        if(source.getBackground()==selezionato){

                            String name = source.getName();
                            source.setToolTipText("Seleziona nuovamente posto "+source.getName().replaceAll(":",","));
                            postiSelezionati.remove(name+";");
                            int numbers[][] = TraduttoreMatrice.stringToMatrice(name) ;
                            posti[numbers[0][0]][numbers[0][1]] = 1;
                            if(postiSelezionati.size() < 1){

                                jButton1.setToolTipText("Seleziona un posto prima di continuare");
                                jButton1.setEnabled(false);
                            }
                            source.setBackground(cambiato);
                        }else{

                            String name = source.getName();
                            postiSelezionati.add(name+";");
                            int numbers[][] = TraduttoreMatrice.stringToMatrice(name) ;
                            posti[numbers[0][0]][numbers[0][1]] = 2;

                            if(!jButton1.isEnabled()){
                                jButton1.setToolTipText(null);
                                jButton1.setEnabled(true);
                            }

                            source.setBackground(selezionato);
                            source.setToolTipText("Annulla selezione");

                        }
                    });
                }else {

                    tmp.setName(x+":"+y);
                    tmp.setToolTipText("Seleziona posto "+x+","+y);
                    tmp.addActionListener(evt -> {

                        JButton source = (JButton) evt.getSource();
                        if(source.getBackground()!=selezionato){
                            String name = source.getName();
                            postiSelezionati.add(name+";");
                            int numbers[][] = TraduttoreMatrice.stringToMatrice(name) ;
                            posti[numbers[0][0]][numbers[0][1]] = 2;

                            if(!jButton1.isEnabled()){
                                jButton1.setToolTipText(null);
                                jButton1.setEnabled(true);
                            }

                            source.setBackground(selezionato);
                            source.setToolTipText("Annulla selezione");
                        }else{

                            String name = source.getName();
                            postiSelezionati.remove(name+";");
                            int numbers[][] = TraduttoreMatrice.stringToMatrice(name) ;
                            posti[numbers[0][0]][numbers[0][1]] = 1;
                            source.setBackground(null);
                            source.setToolTipText("Seleziona posto "+source.getName().replaceAll(":",","));
                            if(postiSelezionati.size() < 1){

                                jButton1.setToolTipText("Seleziona un posto prima di continuare");
                                jButton1.setEnabled(false);
                            }
                        }

                    });
                }

                jPanel1.add(tmp);
            }
        }

        jButton1.setText("Prenota");
        jButton1.setEnabled(false);
        jButton1.setToolTipText("Seleziona un posto prima di continuare");
        jButton1.addActionListener(evt -> {
            if(postiSelezionati.size()>0) {

                Proiezione proiezione = new ControllerProiezione().getProiezioneByID(proizioneId);
                float prezzo = Float.parseFloat(proiezione.getPrezzo().trim()) * postiSelezionati.size();
                String prezzoTotale = new String("" + prezzo).replace(",", "\\.");

                if (Main.context.getState() instanceof ModificaPrenotazioneState) {

                    Prenotazione prenotazioneMod = new ControllerPrenotazione().getPrenotazioneById(Session.getSessioneCorrente().getIdRiferimentoProiezione());
                    prenotazioneMod.setPrezzo(prezzoTotale);
                    prenotazioneMod.setPostoAssegnato(TrasformatoreArrayList.arrayListToStringMat(postiSelezionati));
                    new ControllerPrenotazione().modifyPrenotazione(prenotazioneMod);

                    if (Session.getSessioneCorrente().getUtenteLoggato().getTipo().equals("D")) {

                        Transazione transazioneMod = new ControllerTransazione().getTransazioneByIDPrenotazione(prenotazioneMod.getId());
                        transazioneMod.setImporto(prezzoTotale);
                        new ControllerTransazione().modifyTransazione(transazioneMod);
                    }
                } else {

                    String prenotazione = Session.getSessioneCorrente().getUtenteLoggato().getId()
                            + "," + Session.getSessioneCorrente().getIdRiferimentoProiezione()
                            + "," + Session.getSessioneCorrente().getIdRiferimentoFilm()
                            + "," + ValidatoreCampi.DATEFORMAT.format(proiezione.getData())
                            + "," + prezzoTotale
                            + "," + TrasformatoreArrayList.arrayListToStringMat(postiSelezionati);
                    String idPrenotazione = new ControllerPrenotazione().insertPrenotazione(prenotazione);

                    if (Session.getSessioneCorrente().getUtenteLoggato().getTipo().equals("D")) {

                        String transazione = idPrenotazione
                                + "," + Session.getSessioneCorrente().getIdRiferimentoFilm()
                                + "," + ValidatoreCampi.DATEFORMAT.format(proiezione.getData())
                                + "," + prezzoTotale;
                        new ControllerTransazione().insertTransazione(transazione);
                    }
                }

                Proiezione modified = new ControllerProiezione().getProiezioneByID(Session.getSessioneCorrente().getIdRiferimentoProiezione());
                modified.setPostiAttualiOccupati(posti);
                modified.setPostiLiberi(modified.getPostiLiberi() - postiSelezionati.size());
                new ControllerProiezione().modifyProiezione(modified);
                JOptionPane.showMessageDialog(null, "Prenotazione effettuata con sucesso!\n Verrai reindirizzato al catalogo film");
                new FilmState().doAction(Main.context);
            }

        });

        jButton2.setText("Indietro");
        jButton2.setToolTipText("Ritorna alle proiezioni");
        jButton2.addActionListener(evt -> {
            if(Main.context.getState() instanceof ModificaPrenotazioneState){
                if(Session.getSessioneCorrente().getUtenteLoggato().getTipo().equals("D")){
                    new PrenotazioneDipendeteState().doAction(Main.context);
                }else{
                    new PrenotazioniState().doAction(Main.context);
                }
            }else{
                new ProiezioneState().doAction(Main.context);
            }
        });

        jPanel2.setSize(panel_lunghezza,panel_altezza);
        jPanel2.add(jPanel1,CENTER_ALIGNMENT);
        jPanel2.setVisible(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, panel_lunghezza, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, panel_altezza, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );
    }
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
}
