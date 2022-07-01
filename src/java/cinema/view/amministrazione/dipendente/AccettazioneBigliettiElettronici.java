package cinema.view.amministrazione.dipendente;

import cinema.controller.ControllerPrenotazione;
import cinema.controller.ControllerTransazione;
import cinema.eccezioni.BigliettoInfoException;
import cinema.elementiGrafici.BigliettoSingolo;
import cinema.funzioni.ValidatoreCampi;
import cinema.model.Prenotazione;
import cinema.model.Transazione;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Optional;

/**
 * Classe AccettazioneBigliettiElettronici
 * Gestione dei biglietti generati dagli utenti da parte del dipendente
 */

public class AccettazioneBigliettiElettronici extends javax.swing.JPanel {

    private javax.swing.JButton verifica;
    private javax.swing.JButton annulla;
    private javax.swing.JButton conferma;
    private javax.swing.JLabel labelCodice;
    private javax.swing.JPanel pannelloCarte;
    private javax.swing.JTextField codiceField;
    private javax.swing.JPanel panelloBiglietto;
    private javax.swing.JPanel panelloVuoto;

    public AccettazioneBigliettiElettronici() {
        initComponents();
    }

    private void initComponents() {
        panelloBiglietto = new javax.swing.JPanel();
        labelCodice = new javax.swing.JLabel();
        codiceField = new javax.swing.JTextField();
        verifica = new javax.swing.JButton();
        annulla = new javax.swing.JButton();
        conferma = new javax.swing.JButton();
        pannelloCarte = new javax.swing.JPanel(new CardLayout());
        panelloVuoto = new javax.swing.JPanel();

        labelCodice.setText("Inserisci codice");
        codiceField.setText("");

        verifica.setText("Verifica");
        verifica.addActionListener(evt -> {
            Optional<Transazione> opTransazione = new ControllerTransazione().getTransazioneByIDPrenotazione(codiceField.getText());
            if (opTransazione.isPresent()) {
                JOptionPane.showMessageDialog(null, "Il biglietto è stato già utilizzato");
                return;
            }
            Optional<Prenotazione> el = new ControllerPrenotazione().getPrenotazioneById(codiceField.getText());
            if (el.isPresent()) {
                Prenotazione biglietto = el.get();
                if (pannelloCarte.getComponentCount() > 1) {
                    pannelloCarte.remove(panelloBiglietto);
                }

                annulla.setEnabled(true);
                conferma.setEnabled(true);

                CardLayout cl = (CardLayout) (pannelloCarte.getLayout());
                try{
                    panelloBiglietto = new BigliettoSingolo(biglietto);
                }catch (BigliettoInfoException e){
                    JOptionPane.showMessageDialog(null, "Il biglietto non valido");
                    return;

                }

                pannelloCarte.add(panelloBiglietto);

                cl.next(pannelloCarte);

            } else {
                JOptionPane.showMessageDialog(null, "Il biglietto non esiste");
            }

        });

        annulla.setText("Annulla");
        annulla.setEnabled(false);
        annulla.addActionListener(evt -> {
            annulla.setEnabled(false);
            conferma.setEnabled(false);
            codiceField.setText("");
            pannelloCarte.remove(panelloBiglietto);
        });

        conferma.setText("Conferma");
        conferma.setEnabled(false);
        conferma.addActionListener(evt -> {
            Optional<Prenotazione> el = new ControllerPrenotazione().getPrenotazioneById(codiceField.getText());
            if (el.isPresent()) {
                Prenotazione biglietto = el.get();
                String transazione = biglietto.getId() + "," +
                        biglietto.getIdFilm() + "," +
                        ValidatoreCampi.DATEFORMAT.format(biglietto.getData()) + "," +
                        biglietto.getPrezzo();
                new ControllerTransazione().insertTransazione(transazione);

                annulla.setEnabled(false);
                conferma.setEnabled(false);
                codiceField.setText("");
                pannelloCarte.remove(panelloBiglietto);
            }

        });

        pannelloCarte.setBorder(new LineBorder(Color.gray, 2));
        pannelloCarte.add(panelloVuoto);
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panelloVuoto);


        panelloVuoto.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 252, Short.MAX_VALUE)
        );


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelCodice, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(codiceField, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(39, 39, 39)
                                                .addComponent(verifica, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(annulla, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(conferma, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(23, 23, 23))))
                        .addComponent(pannelloCarte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelCodice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(codiceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(verifica))
                                .addGap(18, 18, 18)
                                .addComponent(pannelloCarte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(annulla)
                                        .addComponent(conferma))
                                .addGap(22, 22, 22))
        );
    }

}
