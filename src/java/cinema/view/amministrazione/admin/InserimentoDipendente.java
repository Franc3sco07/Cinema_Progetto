package cinema.view.amministrazione.admin;

import cinema.controller.ControllerUtente;
import cinema.Main;
import cinema.funzioni.ValidatoreCampi;
import cinema.model.TipiUtente;
import cinema.state.amministrazione.admin.GestioneDipendentiState;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Classe InserimentoDipendente
 * Classe che permette la registrazione di un dipendente
 */

public class InserimentoDipendente extends javax.swing.JPanel {

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelCognome;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelCF;
    private javax.swing.JLabel labelCellulare;
    private javax.swing.JLabel labelPass;
    private javax.swing.JLabel labelVPass;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField verificaPasswordField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField nomeField;
    private javax.swing.JTextField cognomeField;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField codiceFiscaleField;
    private javax.swing.JTextField numeroCellulareField;

    public InserimentoDipendente() {
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        labelNome = new javax.swing.JLabel();
        labelCognome = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelCF = new javax.swing.JLabel();
        labelCellulare = new javax.swing.JLabel();
        labelPass = new javax.swing.JLabel();
        labelVPass = new javax.swing.JLabel();
        nomeField = new javax.swing.JTextField();
        cognomeField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        codiceFiscaleField = new javax.swing.JTextField();
        numeroCellulareField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        verificaPasswordField = new javax.swing.JPasswordField();


        labelNome.setText("nome");

        labelCognome.setText("cognome");

        labelEmail.setText("e-mail");

        labelCF.setText("codice fiscale");

        labelCellulare.setText("numero cellulare");

        labelPass.setText("password");

        labelVPass.setText("verifica password");

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
                        emailField.setToolTipText("Questa e-mail ?? gi?? stata registrata");
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

        passwordField.setText("");
        passwordField.setToolTipText("<html>La password deve contenere almeno:<ul><li>un carattere speciale;</li><li>una lettere maiuscola;</li><li>una lettere minuscola;</li><li>un numero;</li></ul>e deve essere lunga tra 6 a 20 caratteri</html>");
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (ValidatoreCampi.isValidPassword(new String(passwordField.getPassword()))) {
                    passwordField.setBorder(new LineBorder(Color.green, 2));
                } else {
                    passwordField.setBorder(new LineBorder(Color.red, 2));
                }
            }
        });


        verificaPasswordField.setText("");
        verificaPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(new String(verificaPasswordField.getPassword()))) {
                    verificaPasswordField.setBorder(new LineBorder(Color.green, 2));
                } else {
                    verificaPasswordField.setBorder(new LineBorder(Color.red, 2));
                    verificaPasswordField.setToolTipText("Le password devono coincidere");
                }
            }
        });

        jButton1.setText("Conferma");
        jButton1.addActionListener(evt -> {
            if (controllaCampi()) {
                String nuovoUtente = TipiUtente.DIPENDENTE.tipo + ","
                        + nomeField.getText() + ","
                        + cognomeField.getText() + ","
                        + emailField.getText() + ","
                        + numeroCellulareField.getText() + ","
                        + codiceFiscaleField.getText() + ","
                        + new String(passwordField.getPassword());

                new ControllerUtente().insertUtente(nuovoUtente);

                JOptionPane.showMessageDialog(null, "Dipendente inserito con successo");
                new GestioneDipendentiState().doAction(Main.context);
            }
        });

        jButton2.setText("Indietro");
        jButton2.addActionListener(evt -> new GestioneDipendentiState().doAction(Main.context));


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
                                                                                        .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                .addComponent(labelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(labelCognome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(labelCF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(labelCellulare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(labelPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(labelVPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(16, 16, 16))
                                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                        .addComponent(codiceFiscaleField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                                                                                        .addComponent(emailField)
                                                                                                        .addComponent(nomeField, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                        .addComponent(cognomeField, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                        .addComponent(numeroCellulareField)
                                                                                                        .addComponent(passwordField)
                                                                                                        .addComponent(verificaPasswordField)))))
                                                                )
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
                                        .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelCognome)
                                        .addComponent(cognomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelCF)
                                        .addComponent(codiceFiscaleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelCellulare)
                                        .addComponent(numeroCellulareField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelPass)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelVPass)
                                        .addComponent(verificaPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton2)
                                        .addComponent(jButton1))
                                .addContainerGap()));


    }

    private boolean controllaCampi() {
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
        if (((LineBorder) passwordField.getBorder()).getLineColor() != Color.green) {
            return false;
        }
        if (((LineBorder) verificaPasswordField.getBorder()).getLineColor() != Color.green) {
            return false;
        }

        return true;
    }
}
