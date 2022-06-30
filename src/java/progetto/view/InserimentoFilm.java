package progetto.view;

import org.apache.commons.io.FilenameUtils;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import progetto.Controller.ControllerFilm;
import progetto.Controller.ControllerProiezione;
import progetto.Controller.ControllerSala;
import progetto.Main;
import progetto.functions.FunzionalitaDate;
import progetto.functions.GestioneFile;
import progetto.functions.TraduttoreMatrice;
import progetto.functions.ValidatoreCampi;
import progetto.model.Sala;
import progetto.state.InserimentoFilmState;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Classe InserimentoFilm
 * Classe che permette l'inserimento di un film
 */

public class InserimentoFilm extends javax.swing.JPanel {

    private javax.swing.JButton bottoneCerca;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox[] orario;
    private javax.swing.JComboBox<String> nomeSala;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nomeFilmLabel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel prezzoLabel;
    private javax.swing.JLabel orarioLabel;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField nomeFilmTextField;
    private javax.swing.JTextField percorso;
    private javax.swing.JTextField prezzoTextField;
    private javax.swing.JLabel anteprimaLocandina;
    private javax.swing.JFileChooser immagineLogo;
    private javax.swing.JLabel descrizioneLabel;
    private javax.swing.JTextField descrizioneField;
    public InserimentoFilm() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new JLabel();
        nomeFilmLabel = new JLabel();
        jLabel3 = new JLabel();
        prezzoLabel = new JLabel();
        nomeFilmTextField = new JTextField();
        percorso = new JTextField();
        prezzoTextField = new JTextField();
        orario = new JCheckBox[4];
        orario[0] = new JCheckBox();
        orario[1] = new JCheckBox();
        orario[2] = new JCheckBox();
        orario[3] = new JCheckBox();
        orarioLabel = new JLabel();
        nomeSala = new JComboBox<>();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        bottoneCerca = new JButton();
        jLabel8 = new JLabel();
        jButton2 = new JButton();
        anteprimaLocandina = new JLabel();
        descrizioneField = new JTextField();
        descrizioneLabel = new JLabel();

        //creazione dei DATE PICKER per la gestione dell'inserimento della data
        UtilDateModel modelInizio = new UtilDateModel();
        modelInizio.setSelected(true);
        JDatePanelImpl datePanelInizio = new JDatePanelImpl(modelInizio, new Properties());
        UtilDateModel modelFine = new UtilDateModel();
        modelFine.setSelected(true);
        JDatePickerImpl datePickerInizio = new JDatePickerImpl(datePanelInizio, FunzionalitaDate.generaDateLabelFormatter());
        JDatePanelImpl datePanelFine = new JDatePanelImpl(modelFine, new Properties());
        JDatePickerImpl datePickerFine = new JDatePickerImpl(datePanelFine, FunzionalitaDate.generaDateLabelFormatter());
        LocalDate tmp = new Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        modelInizio.setDate(tmp.getYear(), tmp.getMonthValue() - 1, tmp.getDayOfMonth());
        modelFine.setDate(tmp.getYear(), tmp.getMonthValue() - 1, tmp.getDayOfMonth());
        datePanelInizio.addActionListener(evt -> {

            if (FunzionalitaDate.dateSuccesive((Date) datePickerInizio.getModel().getValue(), new Date())) {
                LocalDate inizioFilm = new Date().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                modelInizio.setDate(inizioFilm.getYear(), inizioFilm.getMonthValue() - 1, inizioFilm.getDayOfMonth());
                return;
            }
            if (FunzionalitaDate.dateSuccesive((Date) datePickerFine.getModel().getValue(), (Date) datePickerInizio.getModel().getValue())) {
                LocalDate inizioFilm = ((Date) datePickerInizio.getModel().getValue()).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                modelFine.setDate(inizioFilm.getYear(), inizioFilm.getMonthValue() - 1, inizioFilm.getDayOfMonth());
                return;
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
        // fine creazione date picker

        //Definizione della scelta file
        anteprimaLocandina.setIcon(null);
        anteprimaLocandina.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        anteprimaLocandina.setText("");
        bottoneCerca.setText("Cerca");
        bottoneCerca.addActionListener(evt -> {
            String path = "";
            if (Paths.get(percorso.getText()).isAbsolute()) {
                path = percorso.getText();
            }
            immagineLogo = new JFileChooser(path);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
            immagineLogo.setFileFilter(filtro);
            immagineLogo.showOpenDialog(this.getParent());

            if (immagineLogo.getSelectedFile() != null) {
                percorso.setText(immagineLogo.getSelectedFile().toString());
                if (immagineLogo.getSelectedFile().isFile()) {
                    try {
                        BufferedImage locandinaBuffered = ImageIO.read(new File(immagineLogo.getSelectedFile().toString()));
                        if (locandinaBuffered != null) {
                            ImageIcon locandinaIcon = new ImageIcon(locandinaBuffered.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                            anteprimaLocandina.setIcon(locandinaIcon);
                            percorso.setBorder(new LineBorder(Color.black, 1));
                        } else {
                            percorso.setBorder(new LineBorder(Color.red, 2));
                            anteprimaLocandina.setIcon(null);
                        }
                    } catch (IOException e) {
                    }
                }
            }
        });
        //fine


        jLabel1.setText("Periodo visione");
        nomeFilmLabel.setText("Nome film");
        jLabel3.setText("Inserisci locandina");
        prezzoLabel.setText("prezzo");
        descrizioneField.setText("");
        descrizioneLabel.setText("Descrizione");
        nomeFilmTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nomeFilmTextField.getText().equals("")) {
                    nomeFilmTextField.setBorder(new LineBorder(Color.red, 2));
                } else {
                    nomeFilmTextField.setBorder(new LineBorder(Color.black, 1));
                }
            }
        });

        descrizioneField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (descrizioneField.getText().equals("")) {
                    descrizioneField.setBorder(new LineBorder(Color.red, 2));
                } else {
                    descrizioneField.setBorder(new LineBorder(Color.black, 1));
                }
            }
        });

        percorso.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    BufferedImage locandinaBuffered = ImageIO.read(new File(percorso.getText()));
                    if (locandinaBuffered != null) {
                        ImageIcon locandinaIcon = new ImageIcon(locandinaBuffered.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                        anteprimaLocandina.setIcon(locandinaIcon);
                        percorso.setBorder(new LineBorder(Color.black, 1));
                    }
                } catch (IOException ioException) {
                    percorso.setBorder(new LineBorder(Color.red, 2));
                    anteprimaLocandina.setIcon(null);
                }
            }
        });

        prezzoTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ValidatoreCampi.isNumeric(prezzoTextField.getText())) {
                    prezzoTextField.setBorder(new LineBorder(Color.black, 1));
                } else {
                    prezzoTextField.setBorder(new LineBorder(Color.red, 2));
                }

            }
        });

        orario[0].setText("16:30");
        orario[1].setText("18:30");
        orario[2].setText("20:30");
        orario[3].setText("22:30");

        orarioLabel.setText("Seleziona orari proiezione");

        Collection<String> ids = new ControllerSala().getAllSala().stream()
                .sorted(Sala::comapareTo)
                .map(x -> x.getId())
                .toList();

        String[] idSale = ids.toArray(new String[0]);
        nomeSala.setModel(new DefaultComboBoxModel<>(idSale));

        jLabel6.setText("Sala");
        jLabel7.setText("fine:");
        jLabel8.setText("inizio:");

        jButton2.setText("Inserisci film");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ArrayList<String> listaOre = new ArrayList<>();
                boolean success = true;
                try {
                    if (((LineBorder) nomeFilmTextField.getBorder()).getLineColor() == Color.red) {
                        success = false;
                    }
                    if (((LineBorder) descrizioneField.getBorder()).getLineColor() == Color.red) {
                        success = false;
                    }
                    if (((LineBorder) prezzoTextField.getBorder()).getLineColor() == Color.red) {
                        success = false;
                    }
                    if (((LineBorder) percorso.getBorder()).getLineColor() == Color.red) {
                        success = false;
                    }

                    for (int i = 0; i < orario.length; i++) {
                        if (orario[i].isSelected()) {
                            listaOre.add(orario[i].getText());
                        }
                    }
                    if (listaOre.size() == 0) {
                        orarioLabel.setBorder(new LineBorder(Color.red, 2));
                        success = false;
                    } else {
                        orarioLabel.setBorder(null);
                    }
                } catch (ClassCastException e) {
                    success = false;
                }


                if (success) {
                    String nomeLocandina = FilenameUtils.getBaseName(percorso.getText()) + "." + FilenameUtils.getExtension(percorso.getText());
                    Image locandina = ((ImageIcon) anteprimaLocandina.getIcon()).getImage();
                    if (!GestioneFile.salvaImmagine(locandina, nomeLocandina)) {
                        JOptionPane.showMessageDialog(null, "Errore nel salvataggio dell'immaggine");
                        return;
                    }
                    String film = nomeFilmTextField.getText() + "," +
                            nomeLocandina + "," +
                            descrizioneField.getText() + "," +
                            prezzoTextField.getText();
                    String idFilm = new ControllerFilm().insertFilm(film);

                    Date dataInizio = (Date) datePickerInizio.getModel().getValue();
                    Date dataFine = FunzionalitaDate.giornoDopo((Date) datePickerFine.getModel().getValue());
                    Date dataProiezione;
                    String ora;
                    Optional<Sala> opSala = new ControllerSala().getSalaByID((String) nomeSala.getSelectedItem());
                    if (opSala.isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    Sala sala = opSala.get();
                    String proiezione;
                    do {

                        for (Iterator<String> iterator = listaOre.iterator(); iterator.hasNext(); ) {
                            ora = iterator.next() + ":00";
                            dataProiezione = FunzionalitaDate.modificaOrario(dataInizio, ora);
                            proiezione = idFilm + ", " +
                                    sala.getId() + ", " +
                                    prezzoTextField.getText() + ", " +
                                    ValidatoreCampi.DATEFORMAT.format(dataProiezione) + ", " +
                                    sala.getNumeroPosti() + ", " +
                                    TraduttoreMatrice.matriceToString(sala.getDisposizionePosti());
                            new ControllerProiezione().insertProiezione(proiezione);

                        }
                        dataInizio = FunzionalitaDate.giornoDopo(dataInizio);
                    } while (FunzionalitaDate.dateSuccesive(dataInizio, dataFine));
                    JOptionPane.showMessageDialog(null, "Film inserrito");
                    new InserimentoFilmState().doAction(Main.context);
                }

            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(descrizioneLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(orarioLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(prezzoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(28, 28, 28))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(datePickerInizio, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(38, 38, 38)
                                                                .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(datePickerFine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(percorso, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(bottoneCerca)
                                                                        .addComponent(anteprimaLocandina, 100, 100, 100))

                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(nomeFilmTextField, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(prezzoTextField, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(descrizioneField, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(orario[0], GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(orario[1], GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(orario[2], GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(orario[3], GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(nomeSala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(36, 36, 36))))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(nomeFilmLabel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(551, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(nomeFilmTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(datePickerInizio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(datePickerFine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(percorso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bottoneCerca))
                                .addGap(17, 17, 17)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(anteprimaLocandina, 100, 100, 100)
                                                .addComponent(prezzoLabel)
                                                .addComponent(prezzoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(32, 32, 32)
                                                        .addGroup(layout.createParallelGroup()
                                                                .addComponent(descrizioneLabel)
                                                                .addComponent(descrizioneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(34, 34, 34)
                                        .addComponent(orarioLabel)
                                        //.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(orario[0])
                                                .addComponent(orario[1])
                                                .addComponent(orario[2])
                                                .addComponent(orario[3]))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(nomeSala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel6)
                                                .addComponent(jButton2)))
                                .addGap(23, 23, 23))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(nomeFilmLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(336, Short.MAX_VALUE)))
        );
    }
}