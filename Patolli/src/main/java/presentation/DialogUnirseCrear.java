package presentation;

import cliente.CuerpoMensaje;
import cliente.Mensaje;
import cliente.TipoMensaje;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Clase que representa la interfaz de usuario para unirse o crear una partida.
 * Esta clase permite a los jugadores ingresar un código para unirse a una partida existente 
 * o crear una nueva partida.
 * 
 * @author t1pas
 */
public class DialogUnirseCrear extends JDialog {

    FrameInicio parent;
    //Fondo de la pantalla
    private final String RUTAFONDO="/fondoPiramide.jpeg";
    private final FondoPanel fondo=new FondoPanel(RUTAFONDO);
    
    /**
     * Crea una nueva instancia de FrameUnirseCrear.
     * Inicializa los componentes de la interfaz gráfica.
     * @param parent
     */
    public DialogUnirseCrear(FrameInicio parent) {
        super(parent, true);
        this.parent = parent;
        this.setResizable(false);
        if (fondo != null) {
            this.setContentPane(fondo);
        } else {
            System.out.println("No cargo la imagen de fondo");
        }
        initComponents();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Cerrar();
            }
        });
    }
    
    /**
     * Intenta unirse a una partida existente utilizando el código proporcionado.
     * Si el código es válido, se muestra un mensaje de éxito. De
     * lo contrario, se muestra un mensaje de error indicando que la partida no
     * fue encontrada.
     */
    public void Unirse() {
        parent.isHost = false;

        parent.conectarse();
        
        CuerpoMensaje cuerpo = new CuerpoMensaje();
        cuerpo.setCodigoSala(this.txtCodigo.getText().toUpperCase());
        
        TipoMensaje tipo = TipoMensaje.UNIRSE_SALA; 

        Mensaje mensaje = new Mensaje.Builder()
                .body(cuerpo)
                .messageType(tipo)
                .build();
        parent.enviarMensaje(mensaje);
    }

    public void existeSala(boolean resultado){
        if(parent.isHost){
            return;
        }
        if(resultado){
            JOptionPane.showMessageDialog(null, "Uniéndose a la partida", "Partida encontrada", JOptionPane.INFORMATION_MESSAGE);  
            parent.PasarPantallaSala(this, -1, -1, -1, this.txtCodigo.getText());
        }else{
            JOptionPane.showMessageDialog(null, "No se encontró la partida", "Partida no encontrada", JOptionPane.INFORMATION_MESSAGE);
        
        }
    }

    /**
     * Crea una nueva partida y muestra la pantalla de opciones.
     */
    public void Crear() {
        parent.isHost=true;
        parent.PasarPantallaOpciones(this);
    }
    
    /**
     * Navega a la pantalla con las instrucciones del juego.
     * Crea una instancia de FrameComoJugar y cambia a esa pantalla.
     */
    public void ComoJugar() {
            parent.PasarPantallaComoJugar(this);
    }
    
    public void Cerrar() {
            parent.CerrarPrograma();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlUnirse = new javax.swing.JPanel();
        lblUnirse = new javax.swing.JLabel();
        pnlCrear = new javax.swing.JPanel();
        lblCrear = new javax.swing.JLabel();
        pnlCodigo = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextArea();
        pnlComoJugar = new javax.swing.JPanel();
        lblComoJugar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pnlUnirse.setBackground(new java.awt.Color(192, 160, 123));
        pnlUnirse.setMaximumSize(new java.awt.Dimension(300, 75));
        pnlUnirse.setMinimumSize(new java.awt.Dimension(300, 75));

        lblUnirse.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblUnirse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUnirse.setText("UNIRSE");
        lblUnirse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblUnirseMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlUnirseLayout = new javax.swing.GroupLayout(pnlUnirse);
        pnlUnirse.setLayout(pnlUnirseLayout);
        pnlUnirseLayout.setHorizontalGroup(
            pnlUnirseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnirseLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblUnirse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlUnirseLayout.setVerticalGroup(
            pnlUnirseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUnirseLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(lblUnirse)
                .addGap(14, 14, 14))
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
        lblCrear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCrear.setText("CREAR");
        lblCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblCrearMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlCrearLayout = new javax.swing.GroupLayout(pnlCrear);
        pnlCrear.setLayout(pnlCrearLayout);
        pnlCrearLayout.setHorizontalGroup(
            pnlCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrearLayout.createSequentialGroup()
                .addComponent(lblCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCrearLayout.setVerticalGroup(
            pnlCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrearLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblCrear, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCodigo.setBackground(new java.awt.Color(223, 207, 188));

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
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(txtCodigo);

        javax.swing.GroupLayout pnlCodigoLayout = new javax.swing.GroupLayout(pnlCodigo);
        pnlCodigo.setLayout(pnlCodigoLayout);
        pnlCodigoLayout.setHorizontalGroup(
            pnlCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCodigoLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        pnlCodigoLayout.setVerticalGroup(
            pnlCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCodigoLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlComoJugar.setBackground(new java.awt.Color(192, 160, 123));
        pnlComoJugar.setMaximumSize(new java.awt.Dimension(300, 75));
        pnlComoJugar.setMinimumSize(new java.awt.Dimension(300, 75));
        pnlComoJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlComoJugarMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlComoJugarMousePressed(evt);
            }
        });

        lblComoJugar.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblComoJugar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComoJugar.setText("COMO JUGAR");
        lblComoJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblComoJugarMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblComoJugarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlComoJugarLayout = new javax.swing.GroupLayout(pnlComoJugar);
        pnlComoJugar.setLayout(pnlComoJugarLayout);
        pnlComoJugarLayout.setHorizontalGroup(
            pnlComoJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComoJugarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblComoJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlComoJugarLayout.setVerticalGroup(
            pnlComoJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComoJugarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblComoJugar, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(pnlUnirse, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(pnlCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(pnlCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(pnlComoJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(pnlComoJugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlUnirse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void pnlComoJugarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlComoJugarMousePressed

    }//GEN-LAST:event_pnlComoJugarMousePressed

    private void pnlComoJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlComoJugarMouseClicked

    }//GEN-LAST:event_pnlComoJugarMouseClicked

    private void lblComoJugarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblComoJugarMousePressed
        ComoJugar();
    }//GEN-LAST:event_lblComoJugarMousePressed

    private void lblComoJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblComoJugarMouseClicked

    }//GEN-LAST:event_lblComoJugarMouseClicked

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // Obtén el texto actual del campo
        String texto = txtCodigo.getText().replaceAll("\\s+", ""); // Elimina espacios en blanco

        // Verifica si tiene más de 4 caracteres
        if (texto.length() >= 4) {
            // Limita a los primeros 4 caracteres
            txtCodigo.setText(texto.substring(0, 4));
            evt.consume(); // Evita que se escriban más caracteres
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

   /**
     * Maneja el evento de clic del mouse en el panel "Crear".
     * Llama al método Crear para iniciar una nueva partida.
     *
     * @param evt el evento de clic del mouse generado al hacer clic en el panel
     */
    private void pnlCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCrearMouseClicked

    }//GEN-LAST:event_pnlCrearMouseClicked

    private void lblCrearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearMousePressed
        Crear();
    }//GEN-LAST:event_lblCrearMousePressed

    private void lblUnirseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUnirseMousePressed
        Unirse();
    }//GEN-LAST:event_lblUnirseMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblComoJugar;
    private javax.swing.JLabel lblCrear;
    private javax.swing.JLabel lblUnirse;
    private javax.swing.JPanel pnlCodigo;
    private javax.swing.JPanel pnlComoJugar;
    private javax.swing.JPanel pnlCrear;
    private javax.swing.JPanel pnlUnirse;
    private javax.swing.JTextArea txtCodigo;
    // End of variables declaration//GEN-END:variables
}
