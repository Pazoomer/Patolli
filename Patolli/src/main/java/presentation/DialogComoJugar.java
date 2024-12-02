package presentation;

import javax.swing.JDialog;

/**
 * La clase FrameComoJugar muestra las instrucciones del juego Patolli.
 * Permite a los usuarios navegar por las instrucciones del juego y regresar 
 * a la pantalla de opciones. 
 * 
 * @author t1pas
 */
public final class DialogComoJugar extends JDialog {

    private final FrameInicio parent;
    // Página actual de las instrucciones
    private int paginaInstrucciones=0;
    //Fondo de la pantalla
    private final String RUTAFONDO="/fondoPiramide2.jpeg";
    private final FondoPanel fondo=new FondoPanel(RUTAFONDO);

    /**
     * Constructor de FrameComoJugar.
     * Inicializa la ventana y desactiva la opción de cambiar el tamaño de la ventana.
     * @param parent
     */
    public DialogComoJugar(FrameInicio parent) {
        super(parent, true);
        this.parent=parent;
        this.setResizable(false);
        if(fondo!=null){
           this.setContentPane(fondo); 
        }else{
            System.out.println("No cargo la imagen de fondo");
        }
        initComponents();
        pasarInstrucciones(0);
        // Agregar un WindowListener para manejar el evento de cerrar
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Cerrar();
            }
        });
    }
    
    /**
     * Cierra la ventana actual y regresa a la pantalla de opciones.
     * Instancia
     * una nueva ventana de FrameOpciones.
     */
    public void Volver() {
        parent.PasarPantallaUnirseCrear(this);
    }

    /**
     * Muestra la siguiente página de las instrucciones.
     * Avanza a la instrucción siguiente y actualiza el texto mostrado.
     * Controla que no se sobrepase el número máximo de páginas.
     * @param i
     */
    public void pasarInstrucciones(int i) {
        // Avanza a la instrucción siguiente
        paginaInstrucciones += i;
        if (paginaInstrucciones > 4) {
            paginaInstrucciones = 4; // Limita el valor máximo
        } else if (paginaInstrucciones < 0) {
            paginaInstrucciones = 0; // Limita el valor mínimo
        }
        // Actualiza el texto de la instrucción según la página
        this.lblIzquierda.setVisible(true);
        this.lblDerecha.setVisible(true);
        switch (paginaInstrucciones) {
            case 0->{
                this.txtInstruccion.setText("""
                                            Objetivo del juego
                                            
                                            El objetivo del patolli es dejar a los demas jugadores sin apuestas.
                                            
                                            Hay varias formas de ganar o perder apuestas que dependeran de como muevas tus fichas.""");
                this.lblIzquierda.setVisible(false);
            }
            case 1 -> {
                this.txtInstruccion.setText("""
                                            Para empezar a jugar
                                            
                                            Al inicio del juego tus fichas comenzaran fuera del tablero, para colocar una ficha en el tablero debes lanzar las ca\u00f1as y sacar exactamente 1, si no es el caso pagas una apuesta, estas se colocan en la casilla amarilla del tablero que tienes asignada.
                                            
                                            Si la casilla esta ocupada por una ficha, no podras sacar una ficha hasta que se desocupe.""");
            }
            case 2 -> {
                this.txtInstruccion.setText("""
                                            Una vez dentro del tablero
                                            
                                            Cada jugador deber\u00e1 lanzar las 5 ca\u00f1as en su turno correspondiente y dependiendo como caigan podr\u00e1 avanzar el n\u00famero de casillas, excepto si salen las 5 boca arriba, en cuyo caso se avanzara 10 casillas, si salen 0 no se avanza.
                                            
                                            """);
            }
            case 3 -> {
                this.txtInstruccion.setText("""  
                                            Reglas de movimiento
                                            
                                            Si tu ficha fuera a terminar en una casilla ocupada por otra ficha, no podras mover esa ficha.
                                                                                        
                                            Si no puedes moverte de ninguna forma, pagas 1 apuesta.
                                            
                                            Si sacas un 1, puedes meter al tablero una ficha que este afuera del tablero en vez de mover una ficha.""");
            }
            case 4 -> {
                this.txtInstruccion.setText("""
                                            Reglas especiales
                                            
                                            Si logras dar una vuelta completa con una ficha, cobras una apuesta a todos los demas jugadores y vuelves a jugar.
                                            
                                            Si caes en la casilla roja, tendras que pagar dos apuestas.
                                            
                                            Si caes en la casilla azul, juegas dos turnos adicionales seguidos.""");
                this.lblDerecha.setVisible(false);
            }
        }
    }

    public void Cerrar() {
        parent.CerrarPrograma();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlInstrucciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInstruccion = new javax.swing.JTextArea();
        lblDerecha = new javax.swing.JLabel();
        lblIzquierda = new javax.swing.JLabel();
        pnlVolver = new javax.swing.JPanel();
        lblVolver = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pnlTitulo.setBackground(new java.awt.Color(223, 207, 188));
        pnlTitulo.setMaximumSize(new java.awt.Dimension(300, 75));
        pnlTitulo.setMinimumSize(new java.awt.Dimension(300, 75));
        pnlTitulo.setPreferredSize(new java.awt.Dimension(300, 75));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        jLabel1.setText("COMO JUGAR");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(47, 47, 47))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnlInstrucciones.setBackground(new java.awt.Color(223, 207, 188));

        jScrollPane1.setBorder(null);

        txtInstruccion.setEditable(false);
        txtInstruccion.setBackground(new java.awt.Color(223, 207, 188));
        txtInstruccion.setColumns(20);
        txtInstruccion.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtInstruccion.setLineWrap(true);
        txtInstruccion.setRows(5);
        txtInstruccion.setText("Objetivo del PATOLLI\n\nEl objetivo del Patolli es dejar a los demas jugadores sin apuestas.\n\nHay varias formas de ganar o perder apuestas que dependeran de como muevas tus fichas.");
        txtInstruccion.setWrapStyleWord(true);
        txtInstruccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(txtInstruccion);

        lblDerecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowR.png"))); // NOI18N
        lblDerecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDerechaMouseClicked(evt);
            }
        });

        lblIzquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowL.png"))); // NOI18N
        lblIzquierda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIzquierdaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlInstruccionesLayout = new javax.swing.GroupLayout(pnlInstrucciones);
        pnlInstrucciones.setLayout(pnlInstruccionesLayout);
        pnlInstruccionesLayout.setHorizontalGroup(
            pnlInstruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstruccionesLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(pnlInstruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlInstruccionesLayout.createSequentialGroup()
                        .addComponent(lblIzquierda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDerecha))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        pnlInstruccionesLayout.setVerticalGroup(
            pnlInstruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstruccionesLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(pnlInstruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDerecha, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIzquierda, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(19, 19, 19))
        );

        pnlVolver.setBackground(new java.awt.Color(192, 160, 123));
        pnlVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlVolverMouseClicked(evt);
            }
        });

        lblVolver.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblVolver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVolver.setText("VOLVER");
        lblVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolverMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblVolverMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlVolverLayout = new javax.swing.GroupLayout(pnlVolver);
        pnlVolver.setLayout(pnlVolverLayout);
        pnlVolverLayout.setHorizontalGroup(
            pnlVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVolverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVolver, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlVolverLayout.setVerticalGroup(
            pnlVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVolverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVolver, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(pnlInstrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(pnlInstrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(pnlVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * Este método se ejecuta cuando se hace clic en la etiqueta lblVolver.
 * Llama al método Volver(), que regresa a la pantalla de opciones.
 * 
 * @param evt El evento de clic del ratón.
 */
    private void lblVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMouseClicked
        
    }//GEN-LAST:event_lblVolverMouseClicked

    private void pnlVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlVolverMouseClicked
        
    }//GEN-LAST:event_pnlVolverMouseClicked
/**
 * Este método se ejecuta cuando se hace clic en la etiqueta lblIzquierda.
 * Llama al método InstruccionIzquierda() para mostrar la página anterior
 * de las instrucciones del juego.
 * 
 * @param evt El evento de clic del ratón.
 */
    private void lblIzquierdaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIzquierdaMouseClicked
        pasarInstrucciones(-1);
    }//GEN-LAST:event_lblIzquierdaMouseClicked
/**
 * Este método se ejecuta cuando se hace clic en la etiqueta lblDerecha.
 * Llama al método InstruccionDerecha() para mostrar la siguiente página
 * de las instrucciones del juego.
 * 
 * @param evt El evento de clic del ratón.
 */
    private void lblDerechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDerechaMouseClicked
        pasarInstrucciones(1);
    }//GEN-LAST:event_lblDerechaMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
      
    }//GEN-LAST:event_formWindowClosed

    private void lblVolverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMousePressed
        Volver();
    }//GEN-LAST:event_lblVolverMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDerecha;
    private javax.swing.JLabel lblIzquierda;
    private javax.swing.JLabel lblVolver;
    private javax.swing.JPanel pnlInstrucciones;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JPanel pnlVolver;
    private javax.swing.JTextArea txtInstruccion;
    // End of variables declaration//GEN-END:variables
}
