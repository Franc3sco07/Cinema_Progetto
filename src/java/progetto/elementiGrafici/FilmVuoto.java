package progetto.elementiGrafici;

/**
 * Classe FilmVuoto
 * JPanel senza elementi grafici
 * utilizzata per far si che i pannelli FilmSingolo abbiano le stesse dimensioni
 */

public class FilmVuoto extends javax.swing.JPanel {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

    public FilmVuoto() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                        .addComponent(jLabel1))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addContainerGap(43, Short.MAX_VALUE))
        );
    }

}
