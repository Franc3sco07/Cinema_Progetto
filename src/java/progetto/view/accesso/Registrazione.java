package progetto.view.accesso;

import progetto.Controller.ControllerUtente;
import progetto.Main;
import progetto.functions.ValidatoreCampi;
import progetto.model.TipiUtente;
import progetto.state.accesso.LoginState;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Classe Registrazione
 * Gestione della registrazione di un utente
 */
public class Registrazione extends javax.swing.JPanel {
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField nomeField;
    private javax.swing.JTextField cognomeField;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField codiceFiscaleField;
    private javax.swing.JTextField numeroCellulareField;

    public Registrazione() {
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nomeField = new javax.swing.JTextField();
        cognomeField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        codiceFiscaleField = new javax.swing.JTextField();
        numeroCellulareField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("nome");
        jLabel2.setText("cognome");
        jLabel3.setText("e-mail");
        jLabel4.setText("codice fiscale");
        jLabel5.setText("numero cellulare");
        jLabel6.setText("password");
        jLabel7.setText("verifica password");

        nomeField.setText("");
        nomeField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ValidatoreCampi.isValidString(nomeField.getText())) {
                    nomeField.setBorder(new LineBorder(Color.green, 2));
                } else {
                    nomeField.setBorder(new LineBorder(Color.red, 2));
                }
            }
        });

        cognomeField.setText("");
        cognomeField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ValidatoreCampi.isValidString(cognomeField.getText())) {
                    cognomeField.setBorder(new LineBorder(Color.green, 2));
                } else {
                    cognomeField.setBorder(new LineBorder(Color.red, 2));
                }
            }
        });

        emailField.setText("");
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ValidatoreCampi.isValidEmail(emailField.getText())) {
                    ControllerUtente n = new ControllerUtente();
                    if (n.checkEmail(emailField.getText())) {
                        emailField.setBorder(new LineBorder(Color.green, 2));
                        emailField.setToolTipText("");
                    } else {
                        emailField.setBorder(new LineBorder(Color.red, 2));
                        emailField.setToolTipText("Questa e-mail è già stata registrata");
                    }
                } else {
                    emailField.setBorder(new LineBorder(Color.red, 2));
                    emailField.setToolTipText("Formato e-mail non riconosciuto");
                }
            }
        });

        codiceFiscaleField.setText(""); // codice fiscale
        codiceFiscaleField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ValidatoreCampi.isValidCodiceFiscale(codiceFiscaleField.getText())) {
                    codiceFiscaleField.setBorder(new LineBorder(Color.green, 2));
                } else {
                    codiceFiscaleField.setBorder(new LineBorder(Color.red, 2));
                }
            }
        });

        numeroCellulareField.setText("");// numero di cellulare
        numeroCellulareField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ValidatoreCampi.isValidNumberCell(numeroCellulareField.getText())) {
                    numeroCellulareField.setBorder(new LineBorder(Color.green, 2));
                } else {
                    numeroCellulareField.setBorder(new LineBorder(Color.red, 2));
                }
            }
        });

        jPasswordField1.setText("");
        jPasswordField1.setToolTipText("<html>La password deve contenere almeno:<ul><li>un carattere speciale;</li><li>una lettere maiuscola;</li><li>una lettere minuscola;</li><li>un numero;</li></ul>e deve essere lunga tra 6 a 20 caratteri</html>");
        jPasswordField1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (ValidatoreCampi.isValidPassword(new String(jPasswordField1.getPassword()))) {
                    jPasswordField1.setBorder(new LineBorder(Color.green, 2));
                } else {
                    jPasswordField1.setBorder(new LineBorder(Color.red, 2));
                }
            }
        });

        jPasswordField2.setText("");
        jPasswordField2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(jPasswordField1.getPassword()).equals(new String(jPasswordField2.getPassword()))) {
                    jPasswordField2.setBorder(new LineBorder(Color.green, 2));
                } else {
                    jPasswordField2.setBorder(new LineBorder(Color.red, 2));
                    jPasswordField2.setToolTipText("Le password devono coincidere");
                }
            }
        });

        jButton1.setText("Registrati");
        jButton1.addActionListener(evt -> {
            if (controllaCampi()) {
                String nuovoUtente = TipiUtente.UTENTE.tipo + ","
                        + nomeField.getText() + ","
                        + cognomeField.getText() + ","
                        + emailField.getText() + ","
                        + numeroCellulareField.getText() + ","
                        + codiceFiscaleField.getText() + ","
                        + new String(jPasswordField1.getPassword());

                new ControllerUtente().insertUtente(nuovoUtente);

                JOptionPane.showMessageDialog(null, "Registrazione effettuata con successo!\n Verrai reindirizzato al login");
                new LoginState().doAction(Main.context);
            }
        });

        jButton2.setText("Indietro");
        jButton2.addActionListener(evt -> new LoginState().doAction(Main.context));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(16, 16, 16)
                                                                                        )
                                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                        .addComponent(codiceFiscaleField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                                                                        .addComponent(emailField)
                                                                                                        .addComponent(nomeField, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                        .addComponent(cognomeField, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                        .addComponent(numeroCellulareField)
                                                                                                        .addComponent(jPasswordField1)))))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        )
                                                )
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                )
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(cognomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(codiceFiscaleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(numeroCellulareField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton2)
                                        .addComponent(jButton1))
                                .addContainerGap())
        );
    }

    private boolean controllaCampi() {
        try {
            if (((LineBorder) nomeField.getBorder()).getLineColor() != Color.green) {
                return false;
            }
            if (((LineBorder) cognomeField.getBorder()).getLineColor() != Color.green) {
                return false;
            }
            if (((LineBorder) emailField.getBorder()).getLineColor() != Color.green) {
                return false;
            }
            if (((LineBorder) codiceFiscaleField.getBorder()).getLineColor() != Color.green) {
                return false;
            }
            if (((LineBorder) numeroCellulareField.getBorder()).getLineColor() != Color.green) {
                return false;
            }
            if (((LineBorder) jPasswordField1.getBorder()).getLineColor() != Color.green) {
                return false;
            }
            if (((LineBorder) jPasswordField2.getBorder()).getLineColor() != Color.green) {
                return false;
            }
        } catch (ClassCastException e) {
            return false;
        }
        return true;
    }

}
