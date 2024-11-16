package presentation;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import utils.Utils;

/**
 * Clase que representa la sala de juego.
 * Maneja la interacción de los jugadores, el código de la sala y la transición
 * a otras pantallas del juego.
 */
public class DialogSala extends JDialog {

    private final FrameInicio parent;
    public int tamaño;      // Tamaño del tablero de juego.
    public int monto;       // Monto de apuestas.
    public int fichas;      // Número de fichas del jugador.
    public int jugadores = 0; // Contador de jugadores en la sala.
    public int miJugador=-1; //Representa el jugador dueño de la pantalla
    public String codigo;   // Código de la sala.
    private boolean banderaMiJugador = true; //Sirve como candado para que solo se pueda modificar miJugador cuando se conecta a una sala

    /**
     * Constructor de la clase FrameSala.
     * Inicializa la sala con el tamaño del tablero, monto de apuestas, número de fichas y código de la sala.
     *
     * @param parent
     * @param tamaño Tamaño del tablero de juego.
     * @param monto Monto de apuestas.
     * @param fichas Número de fichas del jugador.
     * @param codigo Código de la sala. Si es null, se genera uno nuevo.
     */
    public DialogSala(FrameInicio parent, int tamaño, int monto, int fichas, String codigo) {
        this.setResizable(false);
        initComponents();
        // Agregar un WindowListener para manejar el evento de cerrar
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cerrar(); 
            }
        });
       
        this.parent = parent;
        this.tamaño = tamaño;
        this.monto = monto;
        this.fichas = fichas;

        // Si se proporciona un código, se utiliza; de lo contrario, se genera uno nuevo.
        if (codigo != null) {
            this.codigo = codigo;
        } else {
            this.codigo = Utils.GenerarCodigoSala();
        }
        this.lblCodigo.setText("CODIGO: " + this.codigo);

        // Inicializa la visibilidad de los componentes para los jugadores.
        this.lblP2.setVisible(false);
        this.conchaIcono.setVisible(false);
        this.lblP3.setVisible(false);
        this.piramideIcono.setVisible(false);
        this.lblP4.setVisible(false);
        this.mazorcaIcono.setVisible(false);
        
        ajustesHost();    
    }
    /**
     * Ajusta la pantalla dependiendo si es o no el host
     */
    private void ajustesHost() {
        if (parent.isHost) {
            if(!parent.crearSala(codigo)){
                //TODO: No se llama por que sale un error antes
                JOptionPane.showMessageDialog(null, "El servidor no responde, intentelo de nuevo más tarde", "No se pudo acceder al servidor", JOptionPane.INFORMATION_MESSAGE);
                this.volver();
            }
        } else {
            this.lblContexto.setText("Espera a que el host inicie la partida");
        }
        parent.getNumeroJugadores();
    }
    public void setContexto(){
        switch(miJugador){
            case 0->{
                this.lblContexto.setText("Eres el JAGUAR");
            }
            case 1->{
                this.lblContexto.setText("Eres la CONCHA");
            }
            case 2->{
                this.lblContexto.setText("Eres la PIRAMIDE");
            }
            case 3->{
                this.lblContexto.setText("Eres la MAZORCA");
            }
        }
        
    }
    /**
     * Método que inicia el juego.
     * Si hay más de un jugador, pasa a la pantalla del tablero; de lo contrario,
     * muestra un mensaje informando que se
     * necesitan más jugadores.
     */
    public void jugar() {
        if (miJugador!=0) {
            JOptionPane.showMessageDialog(null, "Solo el creador de la sala puede iniciar el juego", "No eres el host", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (jugadores > 1) {
            subirOpciones();
            parent.PasarPantallaTablero(this, tamaño, monto, fichas, jugadores, miJugador);

        } else {
            JOptionPane.showMessageDialog(null, "Se necesitan dos jugadores para jugar", "Faltan jugadores", JOptionPane.INFORMATION_MESSAGE);
        }
    } 
    /**
     * Método para añadir un jugador a la sala.
     * Actualiza la cantidad de jugadores y la visibilidad de los iconos correspondientes.
     *
     * @param numeroJugadores El número de jugadores a añadir (puede ser negativo para restar).
     * @return Entero con los jugadores actuales despues de agregar
     */
    public int añadirJugador(int numeroJugadores) {
        jugadores += numeroJugadores;

        // Ajustar el número de jugadores al rango permitido (1-4)
        if (jugadores < 1) {
            jugadores = 1;
        } else if (jugadores > 4) {
            jugadores = 4;
        }

        // Configurar visibilidad según el número de jugadores
        this.lblP2.setVisible(jugadores >= 2);
        this.conchaIcono.setVisible(jugadores >= 2);

        this.lblP3.setVisible(jugadores >= 3);
        this.piramideIcono.setVisible(jugadores >= 3);

        this.lblP4.setVisible(jugadores >= 4);
        this.mazorcaIcono.setVisible(jugadores >= 4);

        return jugadores;
    }
    /**
     * Manda el valor del tablero, el monto de los jugadores y el
     * siguientejugador al control del juego
     *
     * @return Verdadero si se actualizo con exito
     */
    private boolean subirOpciones() {
        parent.subirOpciones(tamaño, monto, fichas, jugadores);
        return true;
    }
    /**
     * Recibe las ocpiones del control e inicia el tablero con estas
     *
     * @param tamaño
     * @param monto
     * @param fichas
     * @param jugadores
     */
    public void recibirOpciones(int tamaño, int monto, int fichas, int jugadores) {
        parent.PasarPantallaTablero(this, tamaño, monto, fichas, jugadores, miJugador);
    }
    /**
     * Recibe la notifiación de que el numero de mi jugador es el del parametro
     * @param numeroJugadores Mi numero de jugador
     * @return el numero de jugador recibido
     */
    public int setMiJugador(int numeroJugadores) {
        if (banderaMiJugador) {
            banderaMiJugador = false;
            miJugador = numeroJugadores;
            añadirJugador(miJugador);
            System.out.println("Soy el jugador numero: "+miJugador);
            this.lblJugar.setEnabled(miJugador==0);
        }
        return numeroJugadores;
    } 
    /**
     * Recibe la notificación de que un jugador salio de la sala
     *
     * @param jugador Jugador que salio
     */
    public void recibirJugadorSale(int jugador) {
        if (jugador != miJugador) {
            añadirJugador(-1);
            if (miJugador > jugador) {
                miJugador--;
            }
        }
    }
    /**
     * Regresa a la pantalla de opciones.
     */
    public void volver() {
        parent.enviarJugadorSale(miJugador);
        parent.PasarPantallaOpciones(this);
    }
    /**
     * Cierra el programa
     */
    public void cerrar() {
        parent.enviarJugadorSale(miJugador);
        parent.CerrarPrograma();
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
        lblContexto = new javax.swing.JLabel();
        TODObtnAñadirJugador = new javax.swing.JButton();
        TODObtnEliminarJugador = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
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
                .addContainerGap()
                .addComponent(lblVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlVolverLayout.setVerticalGroup(
            pnlVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVolverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVolver, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
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
        lblJugar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
                .addComponent(lblJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlJugarLayout.setVerticalGroup(
            pnlJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblJugar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
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

        lblContexto.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        lblContexto.setText("Se necesitan 2 jugadores para iniciar");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(pnlVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblContexto)
                .addGap(62, 62, 62))
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
                    .addComponent(piramideIcono)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(conchaIcono, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(gatoIcon))
                    .addComponent(mazorcaIcono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(lblContexto)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlJugar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlVolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
    * Maneja el evento de clic en la etiqueta "Volver".
    * Llama al método Volver() para regresar a la pantalla de opciones.
    *
    * @param evt el evento de mouse que se ha producido
    */
    private void lblVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMouseClicked
        volver();
    }//GEN-LAST:event_lblVolverMouseClicked
    /**
    * Maneja el evento de clic en el panel "Volver".
    * Llama al método {@link #Volver()} para regresar a la pantalla de opciones.
    *
    * @param evt el evento de mouse que se ha producido
    */
    private void pnlVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlVolverMouseClicked
       
    }//GEN-LAST:event_pnlVolverMouseClicked
    /**
    * Maneja el evento de clic en la etiqueta "Jugar".
    * Llama al método Jugar para iniciar el juego.
    *
    * @param evt el evento de mouse que se ha producido
    */
    private void lblJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJugarMouseClicked
        jugar();
    }//GEN-LAST:event_lblJugarMouseClicked
    /**
    * Maneja el evento de clic en el panel "Jugar".
    * Llama al método Jugar para iniciar el juego.
    *
    * @param evt el evento de mouse que se ha producido
    */
    private void pnlJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlJugarMouseClicked
    
    }//GEN-LAST:event_pnlJugarMouseClicked
    /**
    * Maneja el evento de clic en el botón para añadir un jugador.
    * Llama al método AñadirJugador con el parámetro 1
    * para incrementar el número de jugadores en la sala.
    *
    * @param evt el evento de mouse que se ha producido
    */
    private void TODObtnAñadirJugadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TODObtnAñadirJugadorMouseClicked
        añadirJugador(1);
    }//GEN-LAST:event_TODObtnAñadirJugadorMouseClicked
    /**
    * Maneja el evento de clic en el botón para eliminar un jugador.
    * Llama al método {@link #AñadirJugador(int)} con el parámetro -1
    * para decrementar el número de jugadores en la sala.
    *
    * @param evt el evento de mouse que se ha producido
    */
    private void TODObtnEliminarJugadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TODObtnEliminarJugadorMouseClicked
       añadirJugador(-1);
    }//GEN-LAST:event_TODObtnEliminarJugadorMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
 
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton TODObtnAñadirJugador;
    private javax.swing.JButton TODObtnEliminarJugador;
    private javax.swing.JLabel conchaIcono;
    private javax.swing.JLabel gatoIcon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblContexto;
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
