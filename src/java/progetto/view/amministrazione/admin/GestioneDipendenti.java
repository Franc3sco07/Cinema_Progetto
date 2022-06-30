package progetto.view.amministrazione.admin;

import progetto.Controller.ControllerUtente;
import progetto.Main;
import progetto.Session;
import progetto.elementiGrafici.DipendenteSingolo;
import progetto.model.Utente;
import progetto.state.amministrazione.admin.InserimentoDipendenteState;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Collection;

/**
 * Classe GestioneDipendenti
 * Classe che permette agli amministratori di gestire i dipendenti
 */

public class GestioneDipendenti extends javax.swing.JPanel {
    private javax.swing.JButton inserisciDipedente;
    private javax.swing.JScrollPane panelloScorrimento;

    public GestioneDipendenti() {
        initComponents();
    }

    private void initComponents() {

        JPanel infoPannello = new JPanel();
        infoPannello.setLayout(new BoxLayout(infoPannello, BoxLayout.Y_AXIS));

        Collection<Utente> staff = new ControllerUtente().getAllStaff(Session.getSessioneCorrente().getUtenteConesso().getId());
        staff.stream().sorted(Utente::compareTo)
                .map(s -> GenerazioneDipedente(s))
                .forEach(s -> infoPannello.add(s));

        panelloScorrimento = new javax.swing.JScrollPane(infoPannello);
        panelloScorrimento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        inserisciDipedente = new javax.swing.JButton();

        inserisciDipedente.setText("Inserisci dipendente");
        inserisciDipedente.addActionListener(evt -> new InserimentoDipendenteState().doAction(Main.context));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelloScorrimento, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(53, 527, Short.MAX_VALUE)
                                .addComponent(inserisciDipedente)
                                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(panelloScorrimento, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inserisciDipedente)
                                .addGap(19, 19, 19))
        );
    }

    private JPanel GenerazioneDipedente(Utente dipedente) {
        JPanel j = new DipendenteSingolo(dipedente);
        j.setBorder(new MatteBorder(0, 0, 1, 0, Color.gray));
        return j;

    }

}
