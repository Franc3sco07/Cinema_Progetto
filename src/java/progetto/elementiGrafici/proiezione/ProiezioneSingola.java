package progetto.elementiGrafici.proiezione;

import progetto.Controller.ControllerProiezione;
import progetto.Main;
import progetto.Session;
import progetto.functions.ValidatoreCampi;
import progetto.model.Proiezione;
import progetto.model.TipiUtente;
import progetto.state.prenotazione.PrenotazionePostiState;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Classe ProiezioneSingola
 * Classe utilizzare per visualizzare le singole proiezioni
 */

public class ProiezioneSingola extends javax.swing.JPanel {
    private Proiezione datiProiezione;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField prezzoField;

    public ProiezioneSingola(Proiezione proiezione) {
        this.datiProiezione = proiezione;
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        prezzoField = new javax.swing.JTextField();

        jLabel1.setText("Data: " + ValidatoreCampi.DATEFORMAT.format(datiProiezione.getData()) + "     prezzo: ");
        prezzoField.setText(datiProiezione.getPrezzo());

        jLabel2.setText("Sala: " + datiProiezione.getIdSala() + "      posti disponibili: " + datiProiezione.getPostiLiberi());
        prezzoField.setEditable(false);

        if (Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.ADMIN.tipo)) {
            jButton1.setText("Modifica");
            jButton1.addActionListener(evt -> {
                if (!prezzoField.isEditable()) {
                    prezzoField.setEditable(true);
                    jButton1.setText("Salva");
                } else {
                    String tmp = prezzoField.getText();
                    if (ValidatoreCampi.isNumeric(tmp)) {
                        datiProiezione.setPrezzo(tmp);
                        new ControllerProiezione().modifyProiezione(datiProiezione);
                        prezzoField.setEditable(false);
                        jButton1.setText("Modifica");
                        prezzoField.setBorder(new JTextField().getBorder());
                    } else {
                        prezzoField.setBorder(new LineBorder(Color.red, 2));
                    }
                }
            });


        } else {
            jButton1.setText("Prenota");
            jButton1.addActionListener(evt -> {
                Session.getSessioneCorrente().setIdRiferimentoProiezione(datiProiezione.getId());
                new PrenotazionePostiState().doAction(Main.context);
            });
            prezzoField.setBorder(null);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(prezzoField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(prezzoField)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jButton1)
                                                )
                                                .addGap(10, 10, 10))
                                )
                                .addContainerGap())
        );
    }

}
