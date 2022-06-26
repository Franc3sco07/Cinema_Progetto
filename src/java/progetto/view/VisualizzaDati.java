package progetto.view;

import progetto.Controller.ControllerPrenotazione;
import progetto.Controller.ControllerProiezione;
import progetto.Controller.ControllerTransazione;
import progetto.Controller.ControllerUtente;
import progetto.Main;
import progetto.Session;
import progetto.functions.TraduttoreMatrice;
import progetto.model.Prenotazione;
import progetto.model.Proiezione;
import progetto.model.Utente;
import progetto.state.LoginState;
import progetto.state.ModificaPasswordState;
import progetto.state.VisualizzaDatiState;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Classe VisualizzaDati
 * Gestione dei dati dei singoli utenti: visualizzazione, modifica e eliminazione di account
 */
public class VisualizzaDati extends javax.swing.JPanel {
    private Utente utenteCorrente;
    public VisualizzaDati() {
        utenteCorrente = Session.getSessioneCorrente().getUtenteLoggato();
        initComponents();
    }
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nomeTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cognomeTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        cellulareTextField = new javax.swing.JTextField();
        cfTextField = new javax.swing.JTextField();
        bottoneSalva = new javax.swing.JButton();
        bottoneAnnulla = new javax.swing.JButton();
        eliminaAccount = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        modificaPassword = new javax.swing.JButton();

        jLabel1.setText("Nome");

        nomeTextField.setText(utenteCorrente.getNome());
        nomeTextField.setEditable(false);

        jLabel2.setText("Cognome");
        jLabel3.setText("e-mail");
        jLabel4.setText("numero cellulare");
        jLabel5.setText("codice fiscale");

        cognomeTextField.setText(utenteCorrente.getCognome());
        cognomeTextField.setEditable(false);

        emailTextField.setText(utenteCorrente.getEmail());
        emailTextField.setEditable(false);
        cellulareTextField.setText(utenteCorrente.getNumeroCellulare());
        cellulareTextField.setEditable(false);

        cfTextField.setText(utenteCorrente.getCodiceFiscale());
        cfTextField.setEditable(false);

        bottoneSalva.setText("Salva");
        bottoneSalva.addActionListener(evt -> {
            utenteCorrente.setCodiceFiscale(cfTextField.getText());
            utenteCorrente.setCognome(cognomeTextField.getText());
            utenteCorrente.setEmail(emailTextField.getText());
            utenteCorrente.setNome(nomeTextField.getText());
            utenteCorrente.setNumeroCellulare(cellulareTextField.getText());


            if(new ControllerUtente().modifyUtente(utenteCorrente).equals("ok")){
                new VisualizzaDatiState().doAction(Main.context);
                Session.getSessioneCorrente().setUtenteLoggato(this.utenteCorrente);
            }else{
                JOptionPane.showMessageDialog(null, "Controllare i dati immessi");
            }

        });

        bottoneAnnulla.setText("Annulla");
        bottoneAnnulla.addActionListener(evt -> new VisualizzaDatiState().doAction(Main.context));

        eliminaAccount.setText("elimina account");
        eliminaAccount.addActionListener(evt -> {
            String s [] = {"Si","No"};
            boolean eliminaAccount;
            JPanel confermaEliminazione = new JPanel();
            JLabel testo = new JLabel("Sei sicuro di voler eliminare il tuo account");
            testo.setHorizontalAlignment(SwingConstants.CENTER);
            confermaEliminazione.add(testo);
            int res = JOptionPane.showOptionDialog(null,confermaEliminazione,"Conferma",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,s,null);

            if(res == 0){
                eliminaAccount = true;
            }else{
                eliminaAccount = false;
            }
            if (eliminaAccount){
                Collection<Prenotazione> prenotazioni = new ControllerPrenotazione().getPrenotazioniByIDgeneratoreAfterDate(utenteCorrente.getId(),new Date());
                if(prenotazioni.size()>=1){

                    testo.setText("Ci sono delle prenotazioni effettute, perderai tutte le prenotazioni, continuare?");
                    res = JOptionPane.showOptionDialog(null,confermaEliminazione,"Conferma",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,s,null);
                    if(res == 0){
                        Prenotazione tmp;

                        for (Iterator<Prenotazione> iterator = prenotazioni.iterator(); iterator.hasNext(); ){
                            tmp = iterator.next();
                            Proiezione proizioneModificata = new ControllerProiezione().getProezioneByID(tmp.getIdProiezione());
                            int posti [][] = proizioneModificata.getPostiAttualiOccupati();
                            int postiDaLiberare[][] = TraduttoreMatrice.stringToMatrice(tmp.getPostoAssegnato());
                            for (int i= 0; i<postiDaLiberare.length;i++){
                                posti[postiDaLiberare[i][0]][postiDaLiberare[i][1]] = 1;
                            }

                            proizioneModificata.setPostiAttualiOccupati(posti);
                            proizioneModificata.setPostiLiberi(proizioneModificata.getPostiLiberi()+postiDaLiberare.length);
                            new ControllerProiezione().modifyProiezione(proizioneModificata);

                            if(Session.getSessioneCorrente().getUtenteLoggato().getTipo().equals("D")){
                                String idTransazione = new ControllerTransazione().getTransazioneByIDPrenotazione(tmp.getId()).getIdTransazione();
                                new ControllerTransazione().deleteTransazione(idTransazione);
                            }
                        }
                        prenotazioni.stream().forEach(x-> new ControllerPrenotazione().deletePrenotazione(x.getId()));
                    }else{
                        eliminaAccount=false;
                    }
                }

            }

            if (eliminaAccount){
                new ControllerUtente().deleteUtenteByID(utenteCorrente.getId());
                JOptionPane.showMessageDialog(null,"Eliminazione del account completata, verrai disconesso");
                new LoginState().doAction(Main.context);
            }
        });

        jButton4.setText("Modifica");
        jButton4.addActionListener(evt -> {
                nomeTextField.setEditable(true);
                cognomeTextField.setEditable(true);
                emailTextField.setEditable(true);
                cfTextField.setEditable(true);
                cellulareTextField.setEditable(true);
                bottoneAnnulla.setVisible(true);
                bottoneSalva.setVisible(true);
        });

        if(!Session.getSessioneCorrente().getUtenteLoggato().getTipo().equals("U") ){
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
                            .addGap(50,50,50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(modificaPassword, 200,200,200)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(20,20,20)
                                        .addComponent(jButton4, 160,160,160)))
                            .addGap(200,200,200)
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
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cfTextField, 400,400,400)
                            .addComponent(cellulareTextField, 400,400,400)
                            .addComponent(cognomeTextField, 400,400,400)
                            .addComponent(nomeTextField, 400,400,400)
                            .addComponent(emailTextField, 400,400,400))
                       )))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cognomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cellulareTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bottoneAnnulla)
                    .addComponent(jButton4)
                    .addComponent(bottoneSalva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modificaPassword)
                    .addComponent(eliminaAccount))
                .addGap(47, 47, 47))
        );

    }
    private javax.swing.JButton bottoneSalva;
    private javax.swing.JButton bottoneAnnulla;
    private javax.swing.JButton eliminaAccount;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton modificaPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JTextField cognomeTextField;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField cellulareTextField;
    private javax.swing.JTextField cfTextField;
}
