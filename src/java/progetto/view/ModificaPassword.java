package progetto.view;

import progetto.Controller.ControllerUtente;
import progetto.Main;
import progetto.Session;
import progetto.functions.ValidatoreCampi;
import progetto.model.Utente;
import progetto.state.LoginState;
import progetto.state.VisualizzaDatiState;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class ModificaPassword extends javax.swing.JPanel {
    private Utente utenteCorrente;

    public ModificaPassword() {
        utenteCorrente = Session.getSessioneCorrente().getUtenteLoggato();
        initComponents();
    }

    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        nuovaPassword = new javax.swing.JPasswordField();
        passwordCorrente = new javax.swing.JPasswordField();
        confermaPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel4.setText("Conferma password");
        jLabel5.setText("Inserisci password corrente");
        jLabel6.setText("Inserisci nuova password");

        jButton1.setText("Indietro");
        jButton1.addActionListener(evt -> new VisualizzaDatiState().doAction(Main.context));

        jButton2.setText("Conferma password");
        jButton2.addActionListener(evt -> {
            if(utenteCorrente.getPassword().equals(new String(passwordCorrente.getPassword()))){
                if(((LineBorder) confermaPassword.getBorder()).getLineColor() == Color.green && ((LineBorder) nuovaPassword.getBorder()).getLineColor() == Color.green) {
                    utenteCorrente.setPassword(new String(nuovaPassword.getPassword()) );
                    JOptionPane.showMessageDialog(null,"sarai disconesso dalla sessione attuale");
                    new ControllerUtente().modifyUtente(utenteCorrente);
                    new LoginState().doAction(Main.context);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Passowrd utente sbagliata");
            }
        });

        passwordCorrente.setText("");

        nuovaPassword.setText("");
        nuovaPassword.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {

                if (ValidatoreCampi.isValidPassword(new String(nuovaPassword.getPassword()))){
                    nuovaPassword.setBorder(new LineBorder(Color.green,2));
                } else {
                    nuovaPassword.setBorder(new LineBorder(Color.red,2));
                }
            }
        });
        confermaPassword.setText("");
        confermaPassword.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {}
            @Override
            public void focusLost(FocusEvent e) {
                if ( new String(confermaPassword.getPassword()).equals(new String(confermaPassword.getPassword()))){
                    confermaPassword.setBorder(new LineBorder(Color.green,2));
                } else {
                    confermaPassword.setBorder(new LineBorder(Color.red,2));
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
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nuovaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confermaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordCorrente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordCorrente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuovaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confermaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(117, 117, 117))
        );
    }
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField nuovaPassword;
    private javax.swing.JPasswordField passwordCorrente;
    private javax.swing.JPasswordField confermaPassword;
}
