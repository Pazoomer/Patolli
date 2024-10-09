
package presentation;

import javax.swing.JOptionPane;
import utils.Utils;


public class FrameSala extends javax.swing.JFrame {

    public int tamaño;
    public int monto;
    public int fichas;
    public int jugadores=1;
    public String codigo;

    public FrameSala(int tamaño, int monto, int fichas, String codigo) {
        this.setResizable(false);
        initComponents();
        this.tamaño = tamaño;
        this.monto = monto;
        this.fichas = fichas;

        if (codigo != null) {
            this.codigo = codigo;
        } else {
            this.codigo = Utils.GenerarCodigoSala();
        }
        this.lblCodigo.setText("CODIGO: " + this.codigo);

        this.lblP2.setVisible(false);
        this.conchaIcono.setVisible(false);
        this.lblP3.setVisible(false);
        this.piramideIcono.setVisible(false);
        this.lblP4.setVisible(false);
        this.mazorcaIcono.setVisible(false);
    }

    public void Volver() {
        //Pasa a la pantalla de opciones
        FrameOpciones opciones = new FrameOpciones();
        opciones.setVisible(true);
        this.dispose();
    }
    
    public void Jugar() {
        //Pasa a la pantalla de tablero
        if (jugadores > 1) {
            FrameTablero tablero = new FrameTablero(tamaño, monto, fichas, jugadores);
            tablero.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Se necesitan dos jugadores para jugar", "Faltan jugadores", JOptionPane.INFORMATION_MESSAGE);

        }

    }
    
    public void AñadirJugador(int numeroJugadores){
        jugadores=jugadores+numeroJugadores;
        switch(jugadores){
            case 1->{
                this.lblP2.setVisible(false);
                this.conchaIcono.setVisible(false);
            }
            case 2 -> {
                this.lblP2.setVisible(true);
                this.conchaIcono.setVisible(true);
                
                this.lblP3.setVisible(false);
                this.piramideIcono.setVisible(false);
                this.lblP4.setVisible(false);
                this.mazorcaIcono.setVisible(false);
            }
            case 3->{
                this.lblP3.setVisible(true);
                this.piramideIcono.setVisible(true);
                
                this.lblP4.setVisible(false);
                this.mazorcaIcono.setVisible(false);
            }
            case 4->{
                this.lblP4.setVisible(true);
                this.mazorcaIcono.setVisible(true);
            }
            case 5->{
                AñadirJugador(-1);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        pnlVolver = new javax.swing.JPanel();
        lblVolver = new javax.swing.JLabel();
        pnlJugar = new javax.swing.JPanel();
        lblJugar = new javax.swing.JLabel();
        lblP1 = new javax.swing.JLabel();
        lblP2 = new javax.swing.JLabel();
        lblP3 = new javax.swing.JLabel();
        lblP4 = new javax.swing.JLabel();
        gatoIcon = new javax.swing.JLabel();
        piramideIcono = new javax.swing.JLabel();
        mazorcaIcono = new javax.swing.JLabel();
        conchaIcono = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TODObtnAñadirJugador = new javax.swing.JButton();
        TODObtnEliminarJugador = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(102, 102, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel2.setBackground(new java.awt.Color(192, 160, 123));
        jPanel2.setMaximumSize(new java.awt.Dimension(300, 75));
        jPanel2.setMinimumSize(new java.awt.Dimension(300, 75));

        lblCodigo.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblCodigo.setText("CODIGO: J5BA2C");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblCodigo)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblCodigo)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnlVolver.setBackground(new java.awt.Color(192, 160, 123));
        pnlVolver.setMaximumSize(new java.awt.Dimension(300, 75));
        pnlVolver.setMinimumSize(new java.awt.Dimension(300, 75));
        pnlVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlVolverMouseClicked(evt);
            }
        });

        lblVolver.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblVolver.setText("VOLVER");
        lblVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolverMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlVolverLayout = new javax.swing.GroupLayout(pnlVolver);
        pnlVolver.setLayout(pnlVolverLayout);
        pnlVolverLayout.setHorizontalGroup(
            pnlVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVolverLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlVolverLayout.setVerticalGroup(
            pnlVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVolverLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblVolver)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnlJugar.setBackground(new java.awt.Color(192, 160, 123));
        pnlJugar.setMaximumSize(new java.awt.Dimension(300, 75));
        pnlJugar.setMinimumSize(new java.awt.Dimension(300, 75));
        pnlJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlJugarMouseClicked(evt);
            }
        });

        lblJugar.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblJugar.setText("JUGAR");
        lblJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblJugarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlJugarLayout = new javax.swing.GroupLayout(pnlJugar);
        pnlJugar.setLayout(pnlJugarLayout);
        pnlJugarLayout.setHorizontalGroup(
            pnlJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJugarLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblJugar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlJugarLayout.setVerticalGroup(
            pnlJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJugarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblJugar)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        lblP1.setBackground(new java.awt.Color(192, 160, 123));
        lblP1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP1.setText("P1");
        lblP1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        lblP1.setOpaque(true);

        lblP2.setBackground(new java.awt.Color(192, 160, 123));
        lblP2.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP2.setText("P2");
        lblP2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        lblP2.setOpaque(true);

        lblP3.setBackground(new java.awt.Color(192, 160, 123));
        lblP3.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblP3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP3.setText("P3");
        lblP3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        lblP3.setOpaque(true);

        lblP4.setBackground(new java.awt.Color(192, 160, 123));
        lblP4.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblP4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP4.setText("P4");
        lblP4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        lblP4.setOpaque(true);

        gatoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cat.png"))); // NOI18N

        piramideIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/piramide.png"))); // NOI18N

        mazorcaIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mazorca.png"))); // NOI18N

        conchaIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/concha.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel10.setText("Se necesitan 2 jugadores para");

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel13.setText("iniciar");

        TODObtnAñadirJugador.setText("(TODO:) Añadir Jugador");
        TODObtnAñadirJugador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TODObtnAñadirJugadorMouseClicked(evt);
            }
        });

        TODObtnEliminarJugador.setText("(TODO:) Eliminar Jugador");
        TODObtnEliminarJugador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TODObtnEliminarJugadorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(lblP1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(lblP2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(lblP3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblP4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(pnlVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(147, 147, 147)
                .addComponent(pnlJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TODObtnAñadirJugador)
                    .addComponent(gatoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(conchaIcono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(piramideIcono))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(mazorcaIcono)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(TODObtnEliminarJugador)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(TODObtnAñadirJugador))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(TODObtnEliminarJugador)))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblP1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblP2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblP3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblP4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(piramideIcono)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(conchaIcono, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(gatoIcon)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pnlJugar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pnlVolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(mazorcaIcono)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMouseClicked
        Volver();
    }//GEN-LAST:event_lblVolverMouseClicked

    private void pnlVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlVolverMouseClicked
        Volver();
    }//GEN-LAST:event_pnlVolverMouseClicked

    private void lblJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJugarMouseClicked
        Jugar();
    }//GEN-LAST:event_lblJugarMouseClicked

    private void pnlJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlJugarMouseClicked
        Jugar();
    }//GEN-LAST:event_pnlJugarMouseClicked

    private void TODObtnAñadirJugadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TODObtnAñadirJugadorMouseClicked
        AñadirJugador(1);
    }//GEN-LAST:event_TODObtnAñadirJugadorMouseClicked

    private void TODObtnEliminarJugadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TODObtnEliminarJugadorMouseClicked
       AñadirJugador(-1);
    }//GEN-LAST:event_TODObtnEliminarJugadorMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameSala(8,10,2,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton TODObtnAñadirJugador;
    private javax.swing.JButton TODObtnEliminarJugador;
    private javax.swing.JLabel conchaIcono;
    private javax.swing.JLabel gatoIcon;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblJugar;
    private javax.swing.JLabel lblP1;
    private javax.swing.JLabel lblP2;
    private javax.swing.JLabel lblP3;
    private javax.swing.JLabel lblP4;
    private javax.swing.JLabel lblVolver;
    private javax.swing.JLabel mazorcaIcono;
    private javax.swing.JLabel piramideIcono;
    private javax.swing.JPanel pnlJugar;
    private javax.swing.JPanel pnlVolver;
    // End of variables declaration//GEN-END:variables
}
