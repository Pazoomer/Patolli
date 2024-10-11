package presentation;

import javax.swing.JDialog;

/**
 * La clase FrameInicio representa la pantalla inicial del juego.
 * Proporciona un mecanismo para que el usuario pase a la siguiente pantalla, 
 * que es la pantalla donde puede unirse o crear un juego.
 * 
 * @author t1pas
 */
public class FrameInicio extends javax.swing.JFrame {

    /**
     * Constructor de FrameInicio.
     * Inicializa la ventana y desactiva la opción de cambiar el tamaño de la ventana.
     */
    public FrameInicio() {
        this.setResizable(false); // Evita redimensionar la ventana
        initComponents();
    }
    
    public void CerrarPrograma() {
        this.dispose();
    }
    
    /**
     * Pasa a la siguiente pantalla, que es FrameUnirseCrear. Crea una nueva
     * instancia de FrameUnirseCrear y esconde la ventana actual
     *
     * @param children
     */
    public void PasarPantallaUnirseCrear(JDialog children) {
        if (children != null) {
            children.dispose();
        }
        this.setVisible(false);
        DialogUnirseCrear unirseCrear = new DialogUnirseCrear(this);
        unirseCrear.setVisible(true); // Muestra la nueva pantalla
        
    }

    /**
     * Pasa a la siguiente pantalla, que es DialogComoJugar Crea una nueva
     * instancia de DialogComoJugar y esconde la ventana actual
     *
     * @param children
     */
    public void PasarPantallaComoJugar(JDialog children) {
        if (children != null) {
            children.dispose();
        }
        this.setVisible(false);
        DialogComoJugar comoJugar = new DialogComoJugar(this);
        comoJugar.setVisible(true); // Muestra la nueva pantalla
        
    } 
    
    /**
     * Pasa a la siguiente pantalla, que es FrameSala.
     * Crea una nueva instancia de FrameSala y esconde la ventana actual
     * @param children
     * @param tamaño
     * @param monto
     * @param fichas
     * @param codigo
     */
    public void PasarPantallaSala(JDialog children,int tamaño, int monto, int fichas, String codigo) {
        if (children != null) {
            children.dispose();
        }
        this.setVisible(false);
        DialogSala sala = new DialogSala(this,tamaño,monto,fichas,codigo);
        sala.setVisible(true); // Muestra la nueva pantalla
        
    } 
    
    /**
     * Pasa a la siguiente pantalla, que es FrameOpciones.
     * Crea una nueva instancia de FrameOpciones y esconde la ventana actual
     * @param children
     */
    public void PasarPantallaOpciones(JDialog children) {
        if (children != null) {
            children.dispose();
        }
        this.setVisible(false);
        DialogOpciones opciones = new DialogOpciones(this);
        opciones.setVisible(true); // Muestra la nueva pantalla
        
    } 
    
    /**
     * Pasa a la siguiente pantalla, que es FrameInicio
     * @param children
     */
    public void PasarPantallaInicio(JDialog children) {
        if (children != null) {
            children.dispose();
        }
        this.setVisible(true);
    } 
    
    /**
     * Pasa a la siguiente pantalla, que es FrameTablero.
     * Crea una nueva instancia de FrameTablero y esconde la ventana actual
     * @param children
     * @param tamaño
     * @param fichas
     * @param monto
     * @param jugadores
     */
    public void PasarPantallaTablero(JDialog children,int tamaño, int fichas, int monto, int jugadores) {
        if (children != null) {
            children.dispose();
        }
        this.setVisible(false);
        DialogTablero tablero = new DialogTablero(this,tamaño,fichas,monto, jugadores);
        tablero.setVisible(true); // Muestra la nueva pantalla
    } 

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTodo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblInstrucccion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        pnlTodo.setBackground(new java.awt.Color(255, 255, 255));
        pnlTodo.setMaximumSize(new java.awt.Dimension(800, 600));
        pnlTodo.setMinimumSize(new java.awt.Dimension(800, 600));
        pnlTodo.setPreferredSize(new java.awt.Dimension(800, 600));
        pnlTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTodoMouseClicked(evt);
            }
        });

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patolli.png"))); // NOI18N
        lblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoMouseClicked(evt);
            }
        });

        lblInstrucccion.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblInstrucccion.setText("Haga Click Para Empezar");
        lblInstrucccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInstrucccionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlTodoLayout = new javax.swing.GroupLayout(pnlTodo);
        pnlTodo.setLayout(pnlTodoLayout);
        pnlTodoLayout.setHorizontalGroup(
            pnlTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTodoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblLogo)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTodoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInstrucccion)
                .addGap(283, 283, 283))
        );
        pnlTodoLayout.setVerticalGroup(
            pnlTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTodoLayout.createSequentialGroup()
                .addComponent(lblLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInstrucccion)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Este método se ejecuta cuando se hace clic en el panel pnlTodo.
     * Llama al método PasarPantalla() para pasar a la pantalla de opciones.
     * 
     * @param evt El evento de clic del ratón.
     */
    private void pnlTodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTodoMouseClicked
        PasarPantallaUnirseCrear(null);
    }//GEN-LAST:event_pnlTodoMouseClicked

    /**
     * Este método se ejecuta cuando se hace clic en la etiqueta lblLogo.
     * Llama al método PasarPantalla() para pasar a la pantalla de opciones.
     * 
     * @param evt El evento de clic del ratón.
     */
    private void lblLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMouseClicked
        PasarPantallaUnirseCrear(null);
    }//GEN-LAST:event_lblLogoMouseClicked
    /**
     * Este método se ejecuta cuando se hace clic en la etiqueta lblInstruccion.
     * Llama al método PasarPantalla() para pasar a la pantalla de opciones.
     * 
     * @param evt El evento de clic del ratón.
     */
    private void lblInstrucccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInstrucccionMouseClicked
        PasarPantallaUnirseCrear(null);
    }//GEN-LAST:event_lblInstrucccionMouseClicked

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
            java.util.logging.Logger.getLogger(FrameInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new FrameInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblInstrucccion;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel pnlTodo;
    // End of variables declaration//GEN-END:variables
}
