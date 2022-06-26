/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto.view;

import progetto.Main;
import progetto.Session;
import progetto.state.FilmState;
import progetto.state.RegistrazioneState;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Classe Login
 * Classe che gestisce l'accesso al gestionale
 */

public class Login extends javax.swing.JPanel {
    private String emailString = "utente"; //ricordarsi di sostituire
    private String passString = "utente";

    public Login() {
        initComponents();
    }


    private void initComponents() {

        emailField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        registrazione = new javax.swing.JButton();
        login = new javax.swing.JButton();
        emailLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();

        emailField.setText(emailString);
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(emailField.getText().equals(emailString)){
                    emailField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {}
        });

        passwordField.setText(passString);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(new String(passwordField.getPassword()).equals(passString)){
                    passwordField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });

        registrazione.setText("Registrazione");
        registrazione.addActionListener(evt -> new RegistrazioneState().doAction(Main.context));

        login.setText("Login");
        login.addActionListener(evt -> {
            if(Session.logIn(emailField.getText(),new String(passwordField.getPassword()) )!= null) {
                new FilmState().doAction(Main.context);
                Main.frame.showMenu();
            }else{
                JOptionPane.showMessageDialog(null,"La combinazione e-mail password è sbagliata");
            }
        }
        );

        emailLabel.setText("Inserisci e-mail");
        passwordLabel.setText("Inserisci password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                        .addGroup(layout.createParallelGroup()
                                                .addComponent(passwordLabel,130,130,130)
                                                .addComponent(emailLabel,130,130,130)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(40,40,40)
                                                        .addComponent(registrazione, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(130,130,130)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                                                .addComponent(emailField, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(330)
                                                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                )

                                        )
                                .addContainerGap(40,40))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(emailLabel,javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE )
                                        .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup()
                                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 33,javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordLabel,javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(registrazione, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(71, Short.MAX_VALUE))
        );
    }

    private javax.swing.JButton registrazione;
    private javax.swing.JButton login;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JPasswordField passwordField;

}
