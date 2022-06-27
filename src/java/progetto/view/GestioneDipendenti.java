
package progetto.view;

import progetto.Controller.ControllerUtente;
import progetto.Main;
import progetto.Session;
import progetto.elementiGrafici.DipendenteSingolo;
import progetto.model.Utente;
import progetto.state.InserimentoDipendenteState;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Classe GestioneDipendenti
 * Classe che permette agli amministratori di gestire i dipendenti
 */

public class GestioneDipendenti extends javax.swing.JPanel {

    public GestioneDipendenti() {
        initComponents();
    }

    private void initComponents() {

        JPanel infoPannello = new JPanel();
        infoPannello.setLayout(new BoxLayout(infoPannello, BoxLayout.Y_AXIS));

        Collection<Utente> staff = new ControllerUtente().getAllStaff(Session.getSessioneCorrente().getUtenteConesso().getId());
        Utente tmpStaff;
        staff = staff.stream().sorted(Utente::compareTo).collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
        //generazione in grafica dei dei vari dipedenti
        for (Iterator<Utente> iterator = staff.iterator(); iterator.hasNext(); ){
            tmpStaff = iterator.next();
            JPanel j = new DipendenteSingolo(tmpStaff);
            infoPannello.add(j);
            j.setBorder(new MatteBorder(0,0,1,0, Color.gray));
            j.setOpaque(false);
        }

        jScrollPane1 = new javax.swing.JScrollPane(infoPannello);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jButton1 = new javax.swing.JButton();

        jButton1.setText("Inserisci dipendente");
        jButton1.addActionListener(evt -> new InserimentoDipendenteState().doAction(Main.context));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(53, 527, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;

}
