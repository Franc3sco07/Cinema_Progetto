package cinema.view.amministrazione.admin;

import org.apache.commons.io.FilenameUtils;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import cinema.controller.ControllerFilm;
import cinema.controller.ControllerProiezione;
import cinema.controller.ControllerSala;
import cinema.Main;
import cinema.funzioni.FunzionalitaDate;
import cinema.funzioni.GestioneFile;
import cinema.funzioni.TraduttoreMatrice;
import cinema.funzioni.ValidatoreCampi;
import cinema.model.Sala;
import cinema.state.amministrazione.admin.InserimentoFilmState;

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
    private javax.swing.JButton inserisci;
    private javax.swing.JCheckBox[] orario;
    private javax.swing.JComboBox<String> nomeSala;
    private javax.swing.JLabel labelPeriodo;
    private javax.swing.JLabel labelNomeFilm;
    private javax.swing.JLabel labelLocandina;
    private javax.swing.JLabel labelPrezzo;
    private javax.swing.JLabel labelOrario;
    private javax.swing.JLabel labelSala;
    private javax.swing.JLabel labelFine;
    private javax.swing.JLabel labelInizio;
    private javax.swing.JTextField nomeFilmField;
    private javax.swing.JTextField percorsoField;
    private javax.swing.JTextField prezzoField;
    private javax.swing.JLabel anteprimaLocandina;
    private javax.swing.JFileChooser immagineLogo;
    private javax.swing.JLabel labelDescrizione;
    private javax.swing.JTextField descrizioneField;

    public InserimentoFilm() {
        initComponents();
    }

    private void initComponents() {

        labelPeriodo = new JLabel();
        labelNomeFilm = new JLabel();
        labelLocandina = new JLabel();
        labelPrezzo = new JLabel();
        nomeFilmField = new JTextField();
        percorsoField = new JTextField();
        prezzoField = new JTextField();
        orario = new JCheckBox[4];
        orario[0] = new JCheckBox();
        orario[1] = new JCheckBox();
        orario[2] = new JCheckBox();
        orario[3] = new JCheckBox();
        labelOrario = new JLabel();
        nomeSala = new JComboBox<>();
        labelSala = new JLabel();
        labelFine = new JLabel();
        bottoneCerca = new JButton();
        labelInizio = new JLabel();
        inserisci = new JButton();
        anteprimaLocandina = new JLabel();
        descrizioneField = new JTextField();
        labelDescrizione = new JLabel();


        labelPeriodo.setText("Periodo visione");
        labelNomeFilm.setText("Nome film");
        labelLocandina.setText("Inserisci locandina");
        labelPrezzo.setText("prezzo");
        labelDescrizione.setText("Descrizione");
        labelSala.setText("Sala");
        labelFine.setText("fine:");
        labelInizio.setText("inizio:");
        labelOrario.setText("Seleziona orari proiezione");

        descrizioneField.setText("");
        nomeFilmField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nomeFilmField.getText().equals("")) {
                    nomeFilmField.setBorder(new LineBorder(Color.red, 2));
                } else {
                    nomeFilmField.setBorder(new LineBorder(Color.black, 1));
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

        percorsoField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    BufferedImage locandinaBuffered = ImageIO.read(new File(percorsoField.getText()));
                    if (locandinaBuffered != null) {
                        ImageIcon locandinaIcon = new ImageIcon(locandinaBuffered.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                        anteprimaLocandina.setIcon(locandinaIcon);
                        percorsoField.setBorder(new LineBorder(Color.black, 1));
                    }
                } catch (IOException ioException) {
                    percorsoField.setBorder(new LineBorder(Color.red, 2));
                    anteprimaLocandina.setIcon(null);
                }
            }
        });

        prezzoField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ValidatoreCampi.isNumeric(prezzoField.getText())) {
                    prezzoField.setBorder(new LineBorder(Color.black, 1));
                } else {
                    prezzoField.setBorder(new LineBorder(Color.red, 2));
                }

            }
        });

        orario[0].setText("16:30");
        orario[1].setText("18:30");
        orario[2].setText("20:30");
        orario[3].setText("22:30");

        Collection<String> ids = new ControllerSala().getAllSala().stream()
                .sorted(Sala::comapareTo)
                .map(x -> x.getId())
                .toList();

        nomeSala.setModel(new DefaultComboBoxModel<>(ids.toArray(new String[0])));

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
            if (Paths.get(percorsoField.getText()).isAbsolute()) {
                path = percorsoField.getText();
            }
            immagineLogo = new JFileChooser(path);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
            immagineLogo.setFileFilter(filtro);
            immagineLogo.showOpenDialog(this.getParent());

            if (immagineLogo.getSelectedFile() != null) {
                percorsoField.setText(immagineLogo.getSelectedFile().toString());
                if (immagineLogo.getSelectedFile().isFile()) {
                    try {
                        BufferedImage locandinaBuffered = ImageIO.read(new File(immagineLogo.getSelectedFile().toString()));
                        if (locandinaBuffered != null) {
                            ImageIcon locandinaIcon = new ImageIcon(locandinaBuffered.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                            anteprimaLocandina.setIcon(locandinaIcon);
                            percorsoField.setBorder(new LineBorder(Color.black, 1));
                        } else {
                            percorsoField.setBorder(new LineBorder(Color.red, 2));
                            anteprimaLocandina.setIcon(null);
                        }
                    } catch (IOException e) {
                    }
                }
            }
        });
        //fine

        inserisci.setText("Inserisci film");
        inserisci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ArrayList<String> listaOre = new ArrayList<>();
                boolean success = true;
                try {
                    if (((LineBorder) nomeFilmField.getBorder()).getLineColor() == Color.red) {
                        success = false;
                    }
                    if (((LineBorder) descrizioneField.getBorder()).getLineColor() == Color.red) {
                        success = false;
                    }
                    if (((LineBorder) prezzoField.getBorder()).getLineColor() == Color.red) {
                        success = false;
                    }
                    if (((LineBorder) percorsoField.getBorder()).getLineColor() == Color.red) {
                        success = false;
                    }

                    for (int i = 0; i < orario.length; i++) {
                        if (orario[i].isSelected()) {
                            listaOre.add(orario[i].getText());
                        }
                    }
                    if (listaOre.size() == 0) {
                        labelOrario.setBorder(new LineBorder(Color.red, 2));
                        success = false;
                    } else {
                        labelOrario.setBorder(null);
                    }
                } catch (ClassCastException e) {
                    success = false;
                }

                if (success) {
                    String nomeLocandina = FilenameUtils.getBaseName(percorsoField.getText()) + "." + FilenameUtils.getExtension(percorsoField.getText());
                    Image locandina = ((ImageIcon) anteprimaLocandina.getIcon()).getImage();
                    if (!GestioneFile.salvaImmagine(locandina, nomeLocandina)) {
                        JOptionPane.showMessageDialog(null, "Errore nel salvataggio dell'immaggine");
                        return;
                    }
                    String film = nomeFilmField.getText() + "," +
                            nomeLocandina + ", " +
                            descrizioneField.getText() + "," +
                            prezzoField.getText();
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
                                    prezzoField.getText() + ", " +
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
                                                                        .addComponent(labelDescrizione, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(labelOrario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(labelPrezzo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(labelLocandina, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(28, 28, 28))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(labelPeriodo, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(labelInizio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(datePickerInizio, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(38, 38, 38)
                                                                .addComponent(labelFine, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(datePickerFine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(percorsoField, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(bottoneCerca)
                                                                        .addComponent(anteprimaLocandina, 100, 100, 100))

                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(nomeFilmField, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(prezzoField, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(descrizioneField, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(orario[0], GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(labelSala, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
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
                                                                .addComponent(inserisci, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(36, 36, 36))))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(labelNomeFilm, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(551, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(nomeFilmField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelPeriodo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(datePickerInizio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(datePickerFine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelFine)
                                        .addComponent(labelInizio))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelLocandina, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(percorsoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bottoneCerca))
                                .addGap(17, 17, 17)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(anteprimaLocandina, 100, 100, 100)
                                                .addComponent(labelPrezzo)
                                                .addComponent(prezzoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(32, 32, 32)
                                                        .addGroup(layout.createParallelGroup()
                                                                .addComponent(labelDescrizione)
                                                                .addComponent(descrizioneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(34, 34, 34)
                                        .addComponent(labelOrario)
                                        //.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(orario[0])
                                                .addComponent(orario[1])
                                                .addComponent(orario[2])
                                                .addComponent(orario[3]))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(nomeSala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(labelSala)
                                                .addComponent(inserisci)))
                                .addGap(23, 23, 23))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(labelNomeFilm, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(336, Short.MAX_VALUE)))
        );
    }
}