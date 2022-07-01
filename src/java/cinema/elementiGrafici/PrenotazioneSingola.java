package cinema.elementiGrafici;

import cinema.controller.ControllerFilm;
import cinema.controller.ControllerPrenotazione;
import cinema.controller.ControllerProiezione;
import cinema.controller.ControllerTransazione;
import cinema.Main;
import cinema.Session;
import cinema.funzioni.GestioneFile;
import cinema.funzioni.TraduttoreMatrice;
import cinema.funzioni.ValidatoreCampi;
import cinema.model.*;
import cinema.state.prenotazione.ModificaPrenotazioneState;
import cinema.state.prenotazione.PrenotazioniState;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Optional;

/**
 * Classe PrenotazioneSingola
 * Classe utilizzare per visualizzare le singole prenotazioni
 */
public class PrenotazioneSingola extends javax.swing.JPanel {
    private Prenotazione datiPrenotazione;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel iconaFilm;

    public PrenotazioneSingola(Prenotazione datiPrenotazione) {
        this.datiPrenotazione = datiPrenotazione;
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        iconaFilm = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Film filmPrenotazione = new ControllerFilm().getFilmByID(datiPrenotazione.getIdFilm()).get();
        //String nomeFilm = new .getNome();
        jLabel1.setText(filmPrenotazione.getNome()); // nome del film
        Optional<Proiezione> el = new ControllerProiezione().getProiezioneByID(datiPrenotazione.getIdProiezione());
        if (el.isPresent()) {
            jLabel2.setText("Sala: " + el.get().getIdSala());
        } else {
            jLabel2.setText("Errore nella Sala");
        }


        jLabel3.setText("Data: " + ValidatoreCampi.DATEFORMAT.format(datiPrenotazione.getData()));

        jLabel4.setText("Posti: " + datiPrenotazione.getPostoAssegnato());

        jLabel5.setText("Codice: " + datiPrenotazione.getId());

        jLabel6.setText("prezzo: " + datiPrenotazione.getPrezzo());

        iconaFilm.setIcon(GestioneFile.apriImmagine(filmPrenotazione.getLocandina()));
        iconaFilm.setBorder(new LineBorder(Color.GRAY, 1));
        jButton1.setText("cancella");
        jButton1.addActionListener(evt -> {
            String opzioni[] = {"Si", "No"};
            JPanel confermaEliminazione = new JPanel();
            JLabel testo = new JLabel("Sei sicuro di voler eliminare la prenotazione");
            testo.setHorizontalAlignment(SwingConstants.CENTER);
            confermaEliminazione.add(testo);
            int res = JOptionPane.showOptionDialog(null, confermaEliminazione, "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opzioni, null);

            if (res == 0) {
                new ControllerPrenotazione().deletePrenotazione(datiPrenotazione.getId());
                Optional<Proiezione> el1 = new ControllerProiezione().getProiezioneByID(datiPrenotazione.getIdProiezione());
                if (el1.isPresent()) {
                    Proiezione proizioneModificata = el1.get();
                    int posti[][] = proizioneModificata.getPostiAttualiOccupati();
                    int postiDaLiberare[][] = TraduttoreMatrice.stringToMatrice(datiPrenotazione.getPostoAssegnato());
                    for (int i = 0; i < postiDaLiberare.length; i++) {
                        posti[postiDaLiberare[i][0]][postiDaLiberare[i][1]] = 1;
                    }
                    proizioneModificata.setPostiAttualiOccupati(posti);
                    proizioneModificata.setPostiLiberi(proizioneModificata.getPostiLiberi() + postiDaLiberare.length);
                    new ControllerProiezione().modifyProiezione(proizioneModificata);

                    if (Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.DIPENDENTE.tipo)) {
                        Optional<Transazione> opTransazione = new ControllerTransazione().getTransazioneByIDPrenotazione(datiPrenotazione.getId());
                        if (opTransazione.isPresent()) {
                            String idTransazione = opTransazione.get().getIdTransazione();
                            new ControllerTransazione().deleteTransazione(idTransazione);
                        }

                    }

                    new PrenotazioniState().doAction(Main.context);
                }
            }
        });

        jButton2.setText("modifica");
        jButton2.addActionListener(evt -> {
            Session.getSessioneCorrente().setIdRiferimentoProiezione(datiPrenotazione.getId());
            new ModificaPrenotazioneState().doAction(Main.context);
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(iconaFilm)
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGap(3, 3, 3)
                                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(iconaFilm)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addGroup(layout.createParallelGroup()
                                                                .addComponent(jButton2)
                                                                .addComponent(jLabel5)
                                                                .addComponent(jLabel1)
                                                        )))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel4)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jButton1)))
                                )
                                .addContainerGap())
        );
    }

}
