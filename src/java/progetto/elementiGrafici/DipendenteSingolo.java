/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package progetto.elementiGrafici;

import progetto.Controller.ControllerUtente;
import progetto.Main;
import progetto.model.Utente;
import progetto.state.GestioneDipendentiState;

import javax.swing.*;

/**
 *
 * @author francesco
 */
public class DipendenteSingolo extends javax.swing.JPanel {

    private Utente utente;
    /**
     * Creates new form dipendenteSingolo
     */
    public DipendenteSingolo(Utente dipendenteStaff) {
        this.utente = dipendenteStaff;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText(utente.getCognome());

        jLabel2.setText(utente.getNome());

        jLabel3.setText(utente.getEmail());

        jButton1.setText("licenzia");
        jButton1.addActionListener(evt -> {
            Object s [] = {"Si","No"};
            JPanel confermaEliminazione = new JPanel();
            JLabel testo = new JLabel("Sei sicuro di voler licenziare il dipendente");
            testo.setHorizontalAlignment(SwingConstants.CENTER);
            confermaEliminazione.add(testo);
            int res = JOptionPane.showOptionDialog(null,confermaEliminazione,"Conferma",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,s,null);
            if (res == 0){
                new ControllerUtente().deleteUtenteByID(utente.getId());
                new GestioneDipendentiState().doAction(Main.context);
            }

        });

        if( utente.getTipo().equals("D") ){
            jButton2.setText("promuovi");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    utente.setTipo("A");
                    new ControllerUtente().modifyUtente(utente);
                    new GestioneDipendentiState().doAction(Main.context);
                }
            });
        } else {
            jButton2.setText("demansiona");
            jButton2.addActionListener(evt -> {
                utente.setTipo("D");
                new ControllerUtente().modifyUtente(utente);
                new GestioneDipendentiState().doAction(Main.context);
            });

        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(25,25,25)
                                    .addGroup(layout.createParallelGroup()
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                    ))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(45,45,45)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}