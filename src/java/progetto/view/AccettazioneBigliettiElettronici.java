
package progetto.view;

import progetto.Controller.ControllerPrenotazione;
import progetto.Controller.ControllerTransazione;
import progetto.elementiGrafici.BigliettoSingolo;
import progetto.functions.ValidatoreCampi;
import progetto.model.Prenotazione;
import progetto.model.Transazione;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AccettazioneBigliettiElettronici extends javax.swing.JPanel {

    public AccettazioneBigliettiElettronici() {
        initComponents();
    }

    private void initComponents() {
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        pannelloCarte = new javax.swing.JPanel(new CardLayout());
        jPanel3 = new javax.swing.JPanel();

        jLabel1.setText("Inserisci codice");
        jTextField1.setText("");

        jButton1.setText("Verifica");
        jButton1.addActionListener(evt ->{
            Transazione usato = new ControllerTransazione().getTransazioneByIDPrenotazione(jTextField1.getText());

            if( usato != null) {
                JOptionPane.showMessageDialog(null,"Il biglietto è stato gia utilizzato");
                return;
            }

            Prenotazione biglietto = new ControllerPrenotazione().getPrenotazioneById(jTextField1.getText());
            if(biglietto != null){

                if(pannelloCarte.getComponentCount()>1){
                    pannelloCarte.remove(jPanel2);
                }

                jButton2.setEnabled(true);
                jButton3.setEnabled(true);

                CardLayout cl = (CardLayout)(pannelloCarte.getLayout());
                jPanel2 = new BigliettoSingolo(biglietto);
                pannelloCarte.add(jPanel2);


                cl.next(pannelloCarte);

            }else{
                JOptionPane.showMessageDialog(null,"Il biglietto non esiste");
            }

        } );

        jButton2.setText("Annulla");
        jButton2.setEnabled(false);
        jButton2.addActionListener(evt -> {
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
            jTextField1.setText("");
            pannelloCarte.remove(jPanel2);
        });

        jButton3.setText("Conferma");
        jButton3.setEnabled(false);
        jButton3.addActionListener(evt -> {
            Prenotazione biglietto = new ControllerPrenotazione().getPrenotazioneById(jTextField1.getText());
            String transazione = biglietto.getId()+ "," +
                    biglietto.getIdFilm()+","+
                    ValidatoreCampi.DATEFORMAT.format(biglietto.getData()) +","+
                    biglietto.getPrezzo();
            new ControllerTransazione().insertTransazione(transazione);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
            jTextField1.setText("");
            pannelloCarte.remove(jPanel2);
        });

        pannelloCarte.setBorder(new LineBorder(Color.gray,2));
        pannelloCarte.add(jPanel3);
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel3);


        jPanel3.setLayout(jPanel1Layout);
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
            .addComponent(pannelloCarte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(pannelloCarte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(22, 22, 22))
        );
    }
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pannelloCarte;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;

}
