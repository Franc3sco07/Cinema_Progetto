package cinema.view.profilo;

import cinema.controller.ControllerUtente;
import cinema.Main;
import cinema.Session;
import cinema.funzioni.ValidatoreCampi;
import cinema.model.Utente;
import cinema.state.accesso.LoginState;
import cinema.state.profilo.VisualizzaDatiState;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Classe ModificaPassword
 * Classe che gestisce la modifica della password
 */


public class ModificaPassword extends javax.swing.JPanel {
    private Utente utenteCorrente;
    private javax.swing.JButton indietro;
    private javax.swing.JButton conferma;
    private javax.swing.JLabel labelConferma;
    private javax.swing.JLabel labelPassowrd;
    private javax.swing.JLabel labelNuovaPassoword;
    private javax.swing.JPasswordField nuovaPassword;
    private javax.swing.JPasswordField passwordCorrente;
    private javax.swing.JPasswordField confermaPassword;

    public ModificaPassword() {
        utenteCorrente = Session.getSessioneCorrente().getUtenteConesso();
        initComponents();
    }

    private void initComponents() {

        indietro = new javax.swing.JButton();
        conferma = new javax.swing.JButton();
        nuovaPassword = new javax.swing.JPasswordField();
        passwordCorrente = new javax.swing.JPasswordField();
        confermaPassword = new javax.swing.JPasswordField();
        labelConferma = new javax.swing.JLabel();
        labelPassowrd = new javax.swing.JLabel();
        labelNuovaPassoword = new javax.swing.JLabel();

        labelConferma.setText("Conferma password");
        labelPassowrd.setText("Inserisci password corrente");
        labelNuovaPassoword.setText("Inserisci nuova password");

        indietro.setText("Indietro");
        indietro.addActionListener(evt -> new VisualizzaDatiState().doAction(Main.context));

        conferma.setText("Conferma password");
        conferma.addActionListener(evt -> {
            if (utenteCorrente.getPassword().equals(new String(passwordCorrente.getPassword()))) {
                if (((LineBorder) confermaPassword.getBorder()).getLineColor() == Color.green && ((LineBorder) nuovaPassword.getBorder()).getLineColor() == Color.green) {
                    utenteCorrente.setPassword(new String(nuovaPassword.getPassword()));
                    JOptionPane.showMessageDialog(null, "sarai disconesso dalla sessione attuale");
                    new ControllerUtente().modifyUtente(utenteCorrente);
                    new LoginState().doAction(Main.context);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Passowrd utente sbagliata");
            }
        });

        passwordCorrente.setText("");

        nuovaPassword.setText("");
        nuovaPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (ValidatoreCampi.isValidPassword(new String(nuovaPassword.getPassword()))) {
                    nuovaPassword.setBorder(new LineBorder(Color.green, 2));
                } else {
                    nuovaPassword.setBorder(new LineBorder(Color.red, 2));
                }
            }
        });
        confermaPassword.setText("");
        confermaPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(confermaPassword.getPassword()).equals(new String(confermaPassword.getPassword()))) {
                    confermaPassword.setBorder(new LineBorder(Color.green, 2));
                } else {
                    confermaPassword.setBorder(new LineBorder(Color.red, 2));
                    confermaPassword.setToolTipText("Le password devono coincidere");
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(34, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(labelConferma, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(labelNuovaPassoword))
                                                        .addComponent(labelPassowrd, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(39, 39, 39)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nuovaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(confermaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passwordCorrente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(89, 89, 89)
                                                .addComponent(indietro, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(conferma, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelPassowrd, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordCorrente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nuovaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelNuovaPassoword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelConferma, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(confermaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(indietro)
                                        .addComponent(conferma))
                                .addGap(117, 117, 117))
        );
    }
}
