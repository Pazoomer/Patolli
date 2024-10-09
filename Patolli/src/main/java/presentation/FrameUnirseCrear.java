
package presentation;

import javax.swing.JOptionPane;

/**
 *
 * @author t1pas
 */
public class FrameUnirseCrear extends javax.swing.JFrame {

    /**
     * Creates new form FrameUnirseCrear
     */
    public FrameUnirseCrear() {
        initComponents();
    }
    
    public void Unirse(){
        //Pasa a la pantalla sala con el codigo 
        if (this.txtCodigo.getText() != null && !"AAAA".equals(this.txtCodigo.getText())) {
            JOptionPane.showMessageDialog(null, "No se encontro la partida", "Partida no encontrada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Uniendose a la partida", "Partida encontrada", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void Crear(){
        FrameOpciones opciones=new FrameOpciones();
        opciones.setVisible(true);
        this.dispose();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlUnirse = new javax.swing.JPanel();
        lblUnirse = new javax.swing.JLabel();
        pnlCrear = new javax.swing.JPanel();
        lblCrear = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 0));

        jPanel1.setBackground(new java.awt.Color(102, 102, 0));

        pnlUnirse.setBackground(new java.awt.Color(192, 160, 123));
        pnlUnirse.setMaximumSize(new java.awt.Dimension(300, 75));
        pnlUnirse.setMinimumSize(new java.awt.Dimension(300, 75));
        pnlUnirse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlUnirseMouseClicked(evt);
            }
        });

        lblUnirse.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblUnirse.setText("UNIRSE");
        lblUnirse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUnirseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlUnirseLayout = new javax.swing.GroupLayout(pnlUnirse);
        pnlUnirse.setLayout(pnlUnirseLayout);
        pnlUnirseLayout.setHorizontalGroup(
            pnlUnirseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnirseLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblUnirse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlUnirseLayout.setVerticalGroup(
            pnlUnirseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnirseLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblUnirse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCrear.setBackground(new java.awt.Color(192, 160, 123));
        pnlCrear.setMaximumSize(new java.awt.Dimension(300, 75));
        pnlCrear.setMinimumSize(new java.awt.Dimension(300, 75));
        pnlCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCrearMouseClicked(evt);
            }
        });

        lblCrear.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblCrear.setText("CREAR");
        lblCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCrearMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlCrearLayout = new javax.swing.GroupLayout(pnlCrear);
        pnlCrear.setLayout(pnlCrearLayout);
        pnlCrearLayout.setHorizontalGroup(
            pnlCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrearLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblCrear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCrearLayout.setVerticalGroup(
            pnlCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrearLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblCrear)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(223, 207, 188));

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtCodigo.setBackground(new java.awt.Color(223, 207, 188));
        txtCodigo.setColumns(20);
        txtCodigo.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        txtCodigo.setLineWrap(true);
        txtCodigo.setRows(5);
        txtCodigo.setText("####");
        txtCodigo.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtCodigo);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(pnlUnirse, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addComponent(pnlCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlUnirse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(179, 179, 179))
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

    private void lblUnirseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUnirseMouseClicked
        Unirse();
    }//GEN-LAST:event_lblUnirseMouseClicked

    private void pnlUnirseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlUnirseMouseClicked
        Unirse();
    }//GEN-LAST:event_pnlUnirseMouseClicked

    private void lblCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearMouseClicked
        Crear();
    }//GEN-LAST:event_lblCrearMouseClicked

    private void pnlCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCrearMouseClicked
        Crear();
    }//GEN-LAST:event_pnlCrearMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FrameUnirseCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameUnirseCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameUnirseCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameUnirseCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameUnirseCrear().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCrear;
    private javax.swing.JLabel lblUnirse;
    private javax.swing.JLabel lblUnirse1;
    private javax.swing.JLabel lblUnirse2;
    private javax.swing.JLabel lblUnirse3;
    private javax.swing.JLabel lblUnirse4;
    private javax.swing.JPanel pnlCrear;
    private javax.swing.JPanel pnlUnirse;
    private javax.swing.JPanel pnlUnirse1;
    private javax.swing.JPanel pnlUnirse2;
    private javax.swing.JPanel pnlUnirse3;
    private javax.swing.JPanel pnlUnirse4;
    private javax.swing.JTextArea txtCodigo;
    // End of variables declaration//GEN-END:variables
}
