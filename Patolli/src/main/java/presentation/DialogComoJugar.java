package presentation;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * La clase FrameComoJugar muestra las instrucciones del juego Patolli.
 * Permite a los usuarios navegar por las instrucciones del juego y regresar 
 * a la pantalla de opciones. 
 * 
 * @author t1pas
 */
public class DialogComoJugar extends JDialog {

    private JFrame parent;
    // Página actual de las instrucciones
    private int paginaInstrucciones=0;

    /**
     * Constructor de FrameComoJugar.
     * Inicializa la ventana y desactiva la opción de cambiar el tamaño de la ventana.
     * @param parent
     */
    public DialogComoJugar(JFrame parent) {
        super(parent, true); // Inicializa el JDialog con modal
        this.parent=parent;
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
     * Cierra la ventana actual y regresa a la pantalla de opciones.
     * Instancia una nueva ventana de FrameOpciones.
     */
    public void Volver(){
        if (parent instanceof FrameInicio frameInicio) {
            frameInicio.PasarPantallaOpciones(this);
        }
    }
    
    /**
     * Muestra la siguiente página de las instrucciones.
     * Avanza a la instrucción siguiente y actualiza el texto mostrado.
     * Controla que no se sobrepase el número máximo de páginas.
     */
    public void InstruccionDerecha() {
        // Avanza a la instrucción siguiente
        paginaInstrucciones++;
        if (paginaInstrucciones > 6) {
            paginaInstrucciones = 6; // Limita el valor máximo
        }
        // Actualiza el texto de la instrucción según la página
        switch (paginaInstrucciones) {
            case 1 -> {
                this.txtInstruccion.setText("""
                                            Las apuestas son el punto central de juego, hay varias formas de ganar o perder apuestas, 
                                            
                                            Si te quedas sin apuestas o sin fichas, pierdes automaticamente.""");
            }
            case 2 -> {
                this.txtInstruccion.setText("""
                                            Al inicio del juego tus fichas comenzaran fuera del tablero, para colocar una ficha en el tablero debes lanzar las ca\u00f1as y sacar exactamente 1, si no es el caso pagas una apuesta, estas se colocan en la casilla central del tablero que tienes asignada.
                                            
                                            Si la casilla esta ocupada por una ficha enemiga, no podras sacar una ficha hasta que se desocupe.""");
            }
            case 3 -> {
                this.txtInstruccion.setText("""
                                            Una vez dentro del tablero.
                                            
                                            Cada jugador deber\u00e1 lanzar las 5 ca\u00f1as en su turno correspondiente y dependiendo como caigan podr\u00e1 avanzar el n\u00famero de
                                            casillas, excepto en si salen las 5 boca arriba, en cuyo caso se avanzara 10 casillas, si salen 0 no se avanza.
                                            
                                            Si logras dar una vuelta completa con una ficha, cobras una apuesta a todos los demas jugadores y vuelves a jugar.
                                            
                                            Si no puedes moverte de ninguna forma, pagas 1 apuesta.""");
            }
            case 4 -> {
                this.txtInstruccion.setText("""
                                            Las fichas dentro del tablero siguen un orden para poder moverlas, pero si pagas 1 apuesta, puedes elegir cual de tus fichas mover en vez de la asignada del turno.
                                            
                                            Si sacas un 1, puedes meter al tablero una ficha que este afuera de este en vez de mover la ficha del turno.""");
            }
            case 5 -> {
                this.txtInstruccion.setText("""
                                            Si tu tirada terminara en un espacio con una ficha de otro jugador, tu ficha vueve al punto de inicio del tablero, si este punto de inicio esta ocupado por la ficha de otro jugador entonces tu ficha sale del tablero
                                            
                                            Si tu tirada termina en un espacio con una ficha de otro jugador mientras la ficha del otro jugador esta en una casilla central entonces puedes avanzar y sacas del juego la ficha del otro jugador.
                                            
                                            Si tu tirada terminara en un espacio con una de tus fichas, puedes moverte con normalidad.""");
            }
            case 6 -> {
                this.txtInstruccion.setText("""
                                            Casillas especiales
                                            
                                            Si caes en la casilla del triangulo, tendras que pagar dos apuestas.
                                            
                                            Si caes en la casilla circular del final del tablero, juegas dos turnos seguidos.""");
            }
        }
    }

    /**
     * Muestra la página anterior de las instrucciones.
     * Retrocede a la instrucción anterior y actualiza el texto mostrado.
     * Controla que no se retroceda más allá de la primera página.
     */
    public void InstruccionIzquierda() {
        // Retrocede a la instrucción anterior
        paginaInstrucciones--;
        if (paginaInstrucciones < 0) {
            paginaInstrucciones = 0; // Limita el valor mínimo
        }
        // Actualiza el texto de la instrucción según la página
        switch (paginaInstrucciones) {
            case 0 -> {
                this.txtInstruccion.setText("""
                                            El objetivo del Patolli es ser el primero en darle vueltas al tablero igual a la cantidad de fichas iniciales
                                            O 
                                            Ganar todas las apuestas de los demas jugadores.""");
            }
            case 1 -> {
                this.txtInstruccion.setText("""
                                            Las apuestas son el punto central de juego, hay varias formas de ganar o perder apuestas, 
                                            
                                            Si te quedas sin apuestas o sin fichas, pierdes automaticamente.""");
            }
            case 2 -> {
                this.txtInstruccion.setText("""
                                            Al inicio del juego tus fichas comenzaran fuera del tablero, para colocar una ficha en el tablero debes lanzar las cañas y sacar exactamente 1, si no es el caso pagas una apuesta, estas se colocan en la casilla central del tablero que tienes asignada.
                                            
                                            Si la casilla esta ocupada, no podras sacar una ficha hasta que se desocupe.""");
            }
            case 3 -> {
                this.txtInstruccion.setText("""
                                            Una vez dentro del tablero.
                                            
                                            Cada jugador deberá lanzar las 5 cañas en su turno correspondiente y dependiendo como caigan podrá avanzar el número de
                                            casillas, excepto en si salen las 5 boca arriba, en cuyo caso se avanzara 10 casillas, si salen 0 no se avanza.
                                            
                                            Si logras dar una vuelta completa con una ficha, cobras una apuesta a todos los demas jugadores y vuelves a jugar.
                                            
                                            Si no puedes moverte de ninguna forma, pagas 1 apuesta.""");
            }
            case 4 -> {
                this.txtInstruccion.setText("""
                                            Las fichas dentro del tablero siguen un orden para poder moverlas, pero si pagas 1 apuesta, puedes elegir cual de tus fichas mover en vez de la asignada del turno.
                                            
                                            Si sacas un 1, puedes meter al tablero una ficha que este afuera de este en vez de mover la ficha del turno.""");
            }
            case 5 -> {
                this.txtInstruccion.setText("""
                                            Si tu tirada terminara en un espacio con una ficha de otro jugador, tu ficha vueve al punto de inicio del tablero, si este punto de inicio esta ocupado por la ficha de otro jugador entonces tu ficha sale del tablero.
                                            
                                            Si tu tirada termina en un espacio con una ficha de otro jugador mientras la ficha del otro jugador esta enuna casilla central entonces puedes avanzar y sacas del juego la ficha del otro jugador.
                                            
                                            Si tu tirada terminara en un espacio con una de tus fichas, puedes moverte con normalidad.""");
            }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
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

        jPanel1.setBackground(new java.awt.Color(102, 102, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel2.setBackground(new java.awt.Color(223, 207, 188));
        jPanel2.setMaximumSize(new java.awt.Dimension(300, 75));
        jPanel2.setMinimumSize(new java.awt.Dimension(300, 75));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 75));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        jLabel1.setText("COMO JUGAR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(223, 207, 188));

        jScrollPane1.setBorder(null);

        txtInstruccion.setEditable(false);
        txtInstruccion.setBackground(new java.awt.Color(223, 207, 188));
        txtInstruccion.setColumns(20);
        txtInstruccion.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtInstruccion.setLineWrap(true);
        txtInstruccion.setRows(5);
        txtInstruccion.setText("El objetivo del Patolli es ser el primero en darle vueltas al tablero igual a la cantidad de fichas iniciales\n                                            O \n Ganar todas las apuestas de los demas jugadores.");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblIzquierda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDerecha))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
        });

        javax.swing.GroupLayout pnlVolverLayout = new javax.swing.GroupLayout(pnlVolver);
        pnlVolver.setLayout(pnlVolverLayout);
        pnlVolverLayout.setHorizontalGroup(
            pnlVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVolverLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblVolver)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        pnlVolverLayout.setVerticalGroup(
            pnlVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVolverLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblVolver)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(pnlVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
/**
 * Este método se ejecuta cuando se hace clic en la etiqueta lblVolver.
 * Llama al método Volver(), que regresa a la pantalla de opciones.
 * 
 * @param evt El evento de clic del ratón.
 */
    private void lblVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMouseClicked
        Volver();
    }//GEN-LAST:event_lblVolverMouseClicked
/**
 * Este método se ejecuta cuando se hace clic en el panel pnlVolver.
 * Al igual que lblVolverMouseClicked, llama al método Volver() para regresar
 * a la pantalla de opciones.
 * 
 * @param evt El evento de clic del ratón.
 */
    private void pnlVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlVolverMouseClicked
        Volver();
    }//GEN-LAST:event_pnlVolverMouseClicked
/**
 * Este método se ejecuta cuando se hace clic en la etiqueta lblIzquierda.
 * Llama al método InstruccionIzquierda() para mostrar la página anterior
 * de las instrucciones del juego.
 * 
 * @param evt El evento de clic del ratón.
 */
    private void lblIzquierdaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIzquierdaMouseClicked
        InstruccionIzquierda();
    }//GEN-LAST:event_lblIzquierdaMouseClicked
/**
 * Este método se ejecuta cuando se hace clic en la etiqueta lblDerecha.
 * Llama al método InstruccionDerecha() para mostrar la siguiente página
 * de las instrucciones del juego.
 * 
 * @param evt El evento de clic del ratón.
 */
    private void lblDerechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDerechaMouseClicked
        InstruccionDerecha();
    }//GEN-LAST:event_lblDerechaMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
      
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDerecha;
    private javax.swing.JLabel lblIzquierda;
    private javax.swing.JLabel lblVolver;
    private javax.swing.JPanel pnlVolver;
    private javax.swing.JTextArea txtInstruccion;
    // End of variables declaration//GEN-END:variables
}
