package progetto.view.amministrazione.admin;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import progetto.Controller.ControllerFilm;
import progetto.Controller.ControllerTransazione;
import progetto.functions.FunzionalitaDate;
import progetto.functions.ValidatoreCampi;

import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

/**
 * Classe VisualizzaVendite
 * Visualizzazione delle vendite del film desiderati in un lasso di tempo variabile
 */
public class VisualizzaVendite extends javax.swing.JPanel {
    private DefaultTableModel modelloVendite;
    private String[] colonne = {"Giorno", "Vendite"};
    private javax.swing.JButton esegui;
    private javax.swing.JComboBox<String> listaFilm;
    private javax.swing.JLabel labelDa;
    private javax.swing.JLabel labelA;
    private javax.swing.JLabel labelFilm;
    private javax.swing.JScrollPane panelloScorrimento;
    private javax.swing.JTable tabellaVendite;
    private JDatePickerImpl datePickerInizio;
    private JDatePickerImpl datePickerFine;

    public VisualizzaVendite() {
        initComponents();
    }

    private void initComponents() {

        labelDa = new javax.swing.JLabel();
        labelA = new javax.swing.JLabel();
        esegui = new javax.swing.JButton();
        panelloScorrimento = new javax.swing.JScrollPane();
        tabellaVendite = new javax.swing.JTable();
        listaFilm = new javax.swing.JComboBox<>();
        labelFilm = new javax.swing.JLabel();
        modelloVendite = new DefaultTableModel(colonne, 0);

        labelFilm.setText("Film:");
        labelDa.setText("Da:");
        labelA.setText("a:");

        DecimalFormat formatoDouble = new DecimalFormat("0.00");
        esegui.setText("Esegui");
        esegui.addActionListener(evt -> {
            modelloVendite.setRowCount(0);
            Date dataInizio = (Date) datePickerInizio.getModel().getValue();
            Date dataFine = FunzionalitaDate.giornoDopo((Date) datePickerFine.getModel().getValue());
            String filmId = ((String) listaFilm.getSelectedItem()).split(",")[0];
            do {
                Double totVendite = new ControllerTransazione().getTotataleVenditeByIDFilmInADay(filmId, dataInizio);
                modelloVendite.addRow(new String[]{(ValidatoreCampi.NOTIMEFORMAT.format(dataInizio)), formatoDouble.format(totVendite) + " €"});
                dataInizio = FunzionalitaDate.giornoDopo(dataInizio);
            } while (FunzionalitaDate.dateSuccesive(dataInizio, dataFine));

        });

        tabellaVendite.setModel(modelloVendite);
        panelloScorrimento.setViewportView(tabellaVendite);
        listaFilm.setModel(new javax.swing.DefaultComboBoxModel<>(new ControllerFilm().getAllFilmNameAndId().toArray(new String[0])));

        //date Picker
        UtilDateModel modelInizio = new UtilDateModel();
        modelInizio.setSelected(true);
        JDatePanelImpl datePanelInizio = new JDatePanelImpl(modelInizio, new Properties());
        UtilDateModel modelFine = new UtilDateModel();
        modelFine.setSelected(true);
        datePickerInizio = new JDatePickerImpl(datePanelInizio, FunzionalitaDate.generaDateLabelFormatter());
        JDatePanelImpl datePanelFine = new JDatePanelImpl(modelFine, new Properties());
        datePickerFine = new JDatePickerImpl(datePanelFine, FunzionalitaDate.generaDateLabelFormatter());
        datePanelInizio.addActionListener(evt -> {
            if (!FunzionalitaDate.dateSuccesive((Date) datePickerInizio.getModel().getValue(), (Date) datePickerFine.getModel().getValue())) {
                LocalDate inizioFilm = ((Date) datePickerInizio.getModel().getValue()).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                modelFine.setDate(inizioFilm.getYear(), inizioFilm.getMonthValue() - 1, inizioFilm.getDayOfMonth());
            }

        });

        datePanelFine.addActionListener(evt -> {
            if (!FunzionalitaDate.dateSuccesive((Date) datePickerInizio.getModel().getValue(), (Date) datePickerFine.getModel().getValue())) {
                LocalDate inizioFilm = ((Date) datePickerInizio.getModel().getValue()).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                modelFine.setDate(inizioFilm.getYear(), inizioFilm.getMonthValue() - 1, inizioFilm.getDayOfMonth());
            }

        });
        //fine date Piker
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelloScorrimento, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelDa, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(datePickerInizio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36)
                                                .addComponent(labelA)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(datePickerFine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(listaFilm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(43, 43, 43)))
                                .addComponent(esegui)
                                .addGap(198, 198, 198))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(esegui)
                                                .addGap(7, 7, 7))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(labelFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(listaFilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(datePickerInizio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelA)
                                        .addComponent(datePickerFine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelDa))
                                .addGap(18, 18, 18)
                                .addComponent(panelloScorrimento, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(37, Short.MAX_VALUE))
        );
    }

}
