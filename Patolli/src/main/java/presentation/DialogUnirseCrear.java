package presentation;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Clase que representa la interfaz de usuario para unirse o crear una partida.
 * Esta clase permite a los jugadores ingresar un código para unirse a una partida existente 
 * o crear una nueva partida.
 * 
 * @author t1pas
 */
public class DialogUnirseCrear extends JDialog {

    JFrame parent;
    /**
     * Crea una nueva instancia de FrameUnirseCrear.
     * Inicializa los componentes de la interfaz gráfica.
     * @param parent
     */
    public DialogUnirseCrear(JFrame parent) {
        super(parent, true); // Inicializa el JDialog con modal
        this.parent = parent;
        this.setResizable(false); // Desactiva la opción de cambiar el tamaño de la ventana
        initComponents();
        // Agregar un WindowListener para manejar el evento de cerrar
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Cerrar(); // Llama a tu método Cerrar() cuando se intente cerrar la ventana
            }
        });
    }
    
    /**
     * Intenta unirse a una partida existente utilizando el código proporcionado.
     * Si el código es válido, se muestra un mensaje de éxito. 
     * De lo contrario, se muestra un mensaje de error indicando que la partida no fue encontrada.
     */
    public void Unirse() {
        // Pasa a la pantalla sala con el código 
        if (this.txtCodigo.getText() != null && !"AAAA".equals(this.txtCodigo.getText())) {
            JOptionPane.showMessageDialog(null, "No se encontró la partida", "Partida no encontrada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Uniéndose a la partida", "Partida encontrada", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Crea una nueva partida y muestra la pantalla de opciones.
     */
    public void Crear() {
        if (parent instanceof FrameInicio frameInicio) { // Reemplaza MiFrameClase por la clase real del JFrame que tiene el método
            frameInicio.PasarPantallaOpciones(this); // Llama al método directamente
        }
    }
    
    public void Cerrar() {
        if (parent instanceof FrameInicio frameInicio) {
            frameInicio.CerrarPrograma();
        }
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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
    /**
     * Maneja el evento de clic del mouse en la etiqueta "Unirse".
     * Llama al método Unirse para intentar unirse a una partida.
     *
     * @param evt el evento de clic del mouse generado al hacer clic en la etiqueta
     */
    private void lblUnirseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUnirseMouseClicked
        Unirse();
    }//GEN-LAST:event_lblUnirseMouseClicked
    /**
     * Maneja el evento de clic del mouse en el panel "Unirse".
     * Llama al método Unirse para intentar unirse a una partida.
     *
     * @param evt el evento de clic del mouse generado al hacer clic en el panel
     */
    private void pnlUnirseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlUnirseMouseClicked
        Unirse();
    }//GEN-LAST:event_pnlUnirseMouseClicked
    /**
     * Maneja el evento de clic del mouse en la etiqueta "Crear".
     * Llama al método Crear para iniciar una nueva partida.
     *
     * @param evt el evento de clic del mouse generado al hacer clic en la etiqueta
     */
    private void lblCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearMouseClicked
        Crear();
    }//GEN-LAST:event_lblCrearMouseClicked
    /**
     * Maneja el evento de clic del mouse en el panel "Crear".
     * Llama al método Crear para iniciar una nueva partida.
     *
     * @param evt el evento de clic del mouse generado al hacer clic en el panel
     */
    private void pnlCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCrearMouseClicked
        Crear();
    }//GEN-LAST:event_pnlCrearMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCrear;
    private javax.swing.JLabel lblUnirse;
    private javax.swing.JPanel pnlCrear;
    private javax.swing.JPanel pnlUnirse;
    private javax.swing.JTextArea txtCodigo;
    // End of variables declaration//GEN-END:variables
}
