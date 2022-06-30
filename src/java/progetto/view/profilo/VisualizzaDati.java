package progetto.view.profilo;

import progetto.Controller.ControllerPrenotazione;
import progetto.Controller.ControllerProiezione;
import progetto.Controller.ControllerTransazione;
import progetto.Controller.ControllerUtente;
import progetto.Main;
import progetto.Session;
import progetto.functions.TraduttoreMatrice;
import progetto.model.*;
import progetto.state.accesso.LoginState;
import progetto.state.profilo.ModificaPasswordState;
import progetto.state.profilo.VisualizzaDatiState;

import javax.swing.*;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

/**
 * Classe VisualizzaDati
 * Gestione dei dati dei singoli utenti: visualizzazione, modifica e eliminazione di account
 */
public class VisualizzaDati extends javax.swing.JPanel implements Cloneable {
    private Utente utenteCorrente;
    private javax.swing.JButton bottoneSalva;
    private javax.swing.JButton bottoneAnnulla;
    private javax.swing.JButton eliminaAccount;
    private javax.swing.JButton modifica;
    private javax.swing.JButton modificaPassword;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelCognome;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelCellulare;
    private javax.swing.JLabel labelCF;
    private javax.swing.JTextField nomeField;
    private javax.swing.JTextField cognomeTextField;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField cellulareField;
    private javax.swing.JTextField cfField;

    public VisualizzaDati() {
        utenteCorrente = Session.getSessioneCorrente().getUtenteConesso();
        initComponents();
    }

    private void initComponents() {

        labelNome = new javax.swing.JLabel();
        nomeField = new javax.swing.JTextField();
        labelCognome = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelCellulare = new javax.swing.JLabel();
        labelCF = new javax.swing.JLabel();
        cognomeTextField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        cellulareField = new javax.swing.JTextField();
        cfField = new javax.swing.JTextField();
        bottoneSalva = new javax.swing.JButton();
        bottoneAnnulla = new javax.swing.JButton();
        eliminaAccount = new javax.swing.JButton();
        modifica = new javax.swing.JButton();
        modificaPassword = new javax.swing.JButton();

        labelNome.setText("Nome");
        labelCognome.setText("Cognome");
        labelEmail.setText("e-mail");
        labelCellulare.setText("numero cellulare");
        labelCF.setText("codice fiscale");

        cognomeTextField.setText(utenteCorrente.getCognome());
        cognomeTextField.setEditable(false);
        nomeField.setText(utenteCorrente.getNome());
        nomeField.setEditable(false);


        emailField.setText(utenteCorrente.getEmail());
        emailField.setEditable(false);
        cellulareField.setText(utenteCorrente.getNumeroCellulare());
        cellulareField.setEditable(false);

        cfField.setText(utenteCorrente.getCodiceFiscale());
        cfField.setEditable(false);

        bottoneSalva.setText("Salva");
        bottoneSalva.addActionListener(evt -> {
            utenteCorrente.setCodiceFiscale(cfField.getText());
            utenteCorrente.setCognome(cognomeTextField.getText());
            utenteCorrente.setEmail(emailField.getText());
            utenteCorrente.setNome(nomeField.getText());
            utenteCorrente.setNumeroCellulare(cellulareField.getText());


            if (new ControllerUtente().modifyUtente(utenteCorrente).equals("ok")) {
                new VisualizzaDatiState().doAction(Main.context);
                Session.getSessioneCorrente().setUtenteConesso(this.utenteCorrente);
            } else {
                JOptionPane.showMessageDialog(null, "Controllare i dati immessi");
            }
        });

        bottoneAnnulla.setText("Annulla");
        //new VisualizzaDatiState().doAction(Main.context)
        bottoneAnnulla.addActionListener(evt -> {
            restore();
        });

        eliminaAccount.setText("elimina account");
        eliminaAccount.addActionListener(evt -> {
            String opzioni[] = {"Si", "No"};
            //boolean eliminaAccount;
            JPanel confermaEliminazione = new JPanel();
            JLabel testo = new JLabel("Sei sicuro di voler eliminare il tuo account");
            testo.setHorizontalAlignment(SwingConstants.CENTER);
            confermaEliminazione.add(testo);
            int res = JOptionPane.showOptionDialog(null, confermaEliminazione, "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opzioni, null);

            if (res != 0) {
                return;
            }
            Collection<Prenotazione> prenotazioni = new ControllerPrenotazione().getPrenotazioniByIDgeneratoreAfterDate(utenteCorrente.getId(), new Date());
            if (prenotazioni.size() >= 1) {
                    testo.setText("Ci sono delle prenotazioni effettute, perderai tutte le prenotazioni, continuare?");
                    res = JOptionPane.showOptionDialog(null, confermaEliminazione, "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opzioni, null);
                    if (res == 0) {
                        prenotazioni.stream().forEach(s -> eliminaDatiPrenotazione(s));
                    } else {
                        return;
                    }}
            new ControllerUtente().deleteUtenteByID(utenteCorrente.getId());
            JOptionPane.showMessageDialog(null, "Eliminazione del account completata, verrai disconesso");
            new LoginState().doAction(Main.context);
        });

        modifica.setText("Modifica");
        modifica.addActionListener(evt -> {
            nomeField.setEditable(true);
            cognomeTextField.setEditable(true);
            emailField.setEditable(true);
            cfField.setEditable(true);
            cellulareField.setEditable(true);
            bottoneAnnulla.setVisible(true);
            bottoneSalva.setVisible(true);
        });

        if (!Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.UTENTE.tipo)) {
            eliminaAccount.setVisible(false);
        }
        bottoneAnnulla.setVisible(false);
        bottoneSalva.setVisible(false);

        modificaPassword.setText("Modifica password");
        modificaPassword.addActionListener(evt -> new ModificaPasswordState().doAction(Main.context));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(modificaPassword, 200, 200, 200)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(modifica, 160, 160, 160)))
                                                .addGap(200, 200, 200)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(bottoneAnnulla)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(bottoneSalva)
                                                                .addGap(20, 20, 20))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(eliminaAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(40, 40, 40))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE)
                                                        .addComponent(labelCellulare, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                        .addComponent(labelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                        .addComponent(labelCognome, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                        .addComponent(labelCF, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(cfField, 400, 400, 400)
                                                        .addComponent(cellulareField, 400, 400, 400)
                                                        .addComponent(cognomeTextField, 400, 400, 400)
                                                        .addComponent(nomeField, 400, 400, 400)
                                                        .addComponent(emailField, 400, 400, 400))
                                        )))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelNome)
                                        .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cognomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelCognome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelEmail)
                                        .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(labelCellulare, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cellulareField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelCF, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cfField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bottoneAnnulla)
                                        .addComponent(modifica)
                                        .addComponent(bottoneSalva))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(modificaPassword)
                                        .addComponent(eliminaAccount))
                                .addGap(47, 47, 47))
        );

    }

    private void restore() {
        Main.frame.aggiornaPannello(new VisualizzaDati());
    }

    private void eliminaDatiPrenotazione(Prenotazione pr ){
            Optional<Proiezione> op = new ControllerProiezione().getProiezioneByID(pr.getIdProiezione());
            if(op.isEmpty()){
                return;
            }
            Proiezione proizioneModificata = op.get();
            int posti[][] = proizioneModificata.getPostiAttualiOccupati();
            int postiDaLiberare[][] = TraduttoreMatrice.stringToMatrice(pr.getPostoAssegnato());
            for (int i = 0; i < postiDaLiberare.length; i++) {
                posti[postiDaLiberare[i][0]][postiDaLiberare[i][1]] = 1;
            }

            proizioneModificata.setPostiAttualiOccupati(posti);
            proizioneModificata.setPostiLiberi(proizioneModificata.getPostiLiberi() + postiDaLiberare.length);
            new ControllerProiezione().modifyProiezione(proizioneModificata);
            new ControllerPrenotazione().deletePrenotazione(pr.getId());

    }
}
