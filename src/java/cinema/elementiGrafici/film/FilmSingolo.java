package cinema.elementiGrafici.film;

import cinema.controller.ControllerFilm;
import cinema.controller.ControllerPrenotazione;
import cinema.controller.ControllerProiezione;
import cinema.Main;
import cinema.Session;
import cinema.funzioni.GestioneFile;
import cinema.model.Film;
import cinema.model.Prenotazione;
import cinema.model.Proiezione;
import cinema.model.TipiUtente;
import cinema.state.amministrazione.dipendente.PrenotazioneDipendeteState;
import cinema.state.film.FilmState;
import cinema.state.film.ProiezioneState;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe FilmSingolo
 * Classe utilizzare per visualizzare i singoli film
 */

public class FilmSingolo extends javax.swing.JPanel {
    ImageIcon logoFilm;
    private Film datiFilm;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel nomeDescrizioneFilm;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton bottoneModifica;
    private javax.swing.JComponent componeteVariabile;

    public FilmSingolo(Film datiFilm) {
        this.datiFilm = datiFilm;
        this.logoFilm = GestioneFile.apriImmagine(datiFilm.getLocandina().trim());
        initComponents();
    }

    private void initComponents() {

        nomeDescrizioneFilm = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        nomeDescrizioneFilm.setText("<html> <h2>" + datiFilm.getNome() + "</h2><br />  " + datiFilm.getInfo() + "</html>");

        jLabel2.setIcon(logoFilm);
        jLabel2.setBorder(new LineBorder(Color.GRAY, 1));

        if (Main.context.getState() instanceof FilmState) {
            jButton1.setText("Proiezioni");
        } else {
            jButton1.setText("Biglietti");
        }

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (Main.context.getState() instanceof FilmState) {
                    Session.getSessioneCorrente().setIdRiferimentoFilm(datiFilm.getId());
                    new ProiezioneState().doAction(Main.context);
                } else {
                    Session.getSessioneCorrente().setIdRiferimentoFilm(datiFilm.getId());
                    new PrenotazioneDipendeteState().doAction(Main.context);
                }
            }
        });
        if (Session.getSessioneCorrente().getUtenteConesso().getTipo().equals(TipiUtente.ADMIN.tipo)) {
            bottoneModifica = new javax.swing.JButton();
            bottoneModifica.setText("Elimina");
            bottoneModifica.addActionListener(evt -> {
                String opzioni[] = {"Si", "No"};
                JPanel confermaEliminazione = new JPanel();
                JLabel testo = new JLabel("<html>  Sei sicuro di voler eliminare il film?<br>Eliminer?? tutte le prenotazioni e proiezioni</html>");
                testo.setHorizontalAlignment(SwingConstants.CENTER);
                confermaEliminazione.add(testo);
                int res = JOptionPane.showOptionDialog(null, confermaEliminazione, "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opzioni, null);
                if (res == 0) {
                    Collection<Proiezione> proiezioniDaEliminare = new ControllerProiezione().getProiezioneByIDFilm(datiFilm.getId());
                    Collection<String> ids = proiezioniDaEliminare.stream().map(Proiezione::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
                    ids.stream().forEach(x -> new ControllerProiezione().deleteProiezione(x));

                    Collection<Prenotazione> prenotazioniDaEliminare = new ControllerPrenotazione().getPrenotazioniByIDFilm(datiFilm.getId());
                    ids = prenotazioniDaEliminare.stream().map(Prenotazione::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
                    ids.stream().forEach(x -> new ControllerProiezione().deleteProiezione(x));

                    new ControllerFilm().deleteFilmByID(datiFilm.getId());
                    new FilmState().doAction(Main.context);
                }

            });

            componeteVariabile = bottoneModifica;
        } else {
            jLabel3 = new javax.swing.JLabel();
            jLabel3.setText("prezzo: " + datiFilm.getPrezzo() + "???");
            componeteVariabile = jLabel3;
        }


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(nomeDescrizioneFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(componeteVariabile, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(nomeDescrizioneFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(componeteVariabile)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton1))
                                )
                                .addContainerGap())
        );
    }
}
