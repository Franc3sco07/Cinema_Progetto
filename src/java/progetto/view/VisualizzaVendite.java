
package progetto.view;

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
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

/**
 * Classe VisualizzaVendite
 * Visualizzazione delle vendite del film desiderati in un lasso di tempo variabile
 */
public class VisualizzaVendite extends javax.swing.JPanel {
    private DefaultTableModel modelloVendite ;
    private String [] colonne = { "Giorno", "Vendite"};
    public VisualizzaVendite() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        modelloVendite = new DefaultTableModel(colonne, 0);

        jLabel1.setText("Da:");
        jLabel2.setText("a:");

        DecimalFormat formatoDouble = new DecimalFormat("0.00");
        jButton1.setText("Esegui");
        jButton1.addActionListener(evt -> {
            modelloVendite.setRowCount(0);
            Date dataInizio = (Date) datePickerInizio.getModel().getValue();
            Date dataFine = FunzionalitaDate.giornoDopo((Date) datePickerFine.getModel().getValue());
            String filmId = ((String) jComboBox1.getSelectedItem()).split(",")[0];
            Collection<Double> vendite;
            do{
                vendite = new ControllerTransazione().getAllVenditeByIDFilmInADay(filmId,dataInizio);
                Double totVendite = vendite.stream().mapToDouble(Double::doubleValue).sum();
                modelloVendite.addRow(new String[]{(ValidatoreCampi.NOTIMEFORMAT.format(dataInizio)),formatoDouble.format(totVendite)+" €"});
                dataInizio = FunzionalitaDate.giornoDopo(dataInizio);
            }while(FunzionalitaDate.dateSuccesive(dataInizio,dataFine));

        });

        jTable1.setModel(modelloVendite);
        jScrollPane1.setViewportView(jTable1);
        //date Picker
        UtilDateModel modelInizio = new UtilDateModel();
        modelInizio.setSelected(true);
        JDatePanelImpl datePanelInizio = new JDatePanelImpl(modelInizio,new Properties());
        UtilDateModel modelFine = new UtilDateModel();
        modelFine.setSelected(true);
        datePickerInizio = new JDatePickerImpl(datePanelInizio, FunzionalitaDate.generaDateLabelFormatter());
        JDatePanelImpl datePanelFine = new JDatePanelImpl(modelFine,new Properties());
        datePickerFine= new JDatePickerImpl(datePanelFine, FunzionalitaDate.generaDateLabelFormatter());
        datePanelInizio.addActionListener(evt -> {
            if(!FunzionalitaDate.dateSuccesive((Date) datePickerInizio.getModel().getValue(),(Date)datePickerFine.getModel().getValue())){
                LocalDate inizioFilm= ((Date) datePickerInizio.getModel().getValue()).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                modelFine.setDate(inizioFilm.getYear(),inizioFilm.getMonthValue() -1,inizioFilm.getDayOfMonth());
            }

        });

        datePanelFine.addActionListener(evt -> {
            if(!FunzionalitaDate.dateSuccesive((Date) datePickerInizio.getModel().getValue(),(Date)datePickerFine.getModel().getValue())){
                LocalDate inizioFilm= ((Date) datePickerInizio.getModel().getValue()).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                modelFine.setDate(inizioFilm.getYear(),inizioFilm.getMonthValue() -1,inizioFilm.getDayOfMonth());
            }

        });
        //fine date Piker

        String[] nomeFilms = new ControllerFilm().getAllFilmNameAndId().toArray(new String[0]);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(nomeFilms));
        jLabel3.setText("Film:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(datePickerInizio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(datePickerFine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(43, 43, 43)))
                .addComponent(jButton1)
                .addGap(198, 198, 198))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datePickerInizio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(datePickerFine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private JDatePickerImpl datePickerInizio;
    private JDatePickerImpl datePickerFine;

}
