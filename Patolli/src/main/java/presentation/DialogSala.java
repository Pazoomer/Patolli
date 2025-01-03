package presentation;

import cliente.CuerpoMensaje;
import cliente.Mensaje;
import cliente.TipoMensaje;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import utils.Utils;

/**
 * Clase que representa la sala de juego.
 * Maneja la interacción de los jugadores, el código de la sala y la transición
 * a otras pantallas del juego.
 */
public final class DialogSala extends JDialog {

    private final FrameInicio parent;
    public int tamaño;      // Tamaño del tablero de juego.
    public int monto;       // Monto de apuestas.
    public int fichas;      // Número de fichas del jugador.
    public int jugadores = 0; // Contador de jugadores en la sala.
    public int miJugador=-1; //Representa el jugador dueño de la pantalla
    public String codigo;   // Código de la sala.
    private boolean banderaMiJugador = true; //Sirve como candado para que solo se pueda modificar miJugador cuando se conecta a una sala
    //Fondo de la pantalla
    private final String RUTAFONDO="/fondoMural.jpg";
    private final FondoPanel fondo=new FondoPanel(RUTAFONDO);
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
        if(fondo!=null){
           this.setContentPane(fondo); 
        }else{
            System.out.println("No cargo la imagen de fondo");
        }
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
            this.codigo = Utils.generarCodigoSala();
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
        if(parent.volverInicio){
            volver();
        }
    }
    /**
     * Ajusta la pantalla dependiendo si es o no el host
     */
    private void ajustesHost() {
        if (parent.isHost) {
            parent.conectarse();

            CuerpoMensaje cuerpo = new CuerpoMensaje();
            cuerpo.setCodigoSala(this.codigo);

            TipoMensaje tipo = TipoMensaje.CREAR_SALA;

            Mensaje mensaje = new Mensaje.Builder()
                    .body(cuerpo)
                    .messageType(tipo)
                    .build();
            parent.enviarMensaje(mensaje);
        } else {
            this.lblContexto.setText("Espera a que el host inicie la partida");
        }
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
            parent.PasarPantallaTablero(this, tamaño, monto, fichas, jugadores, miJugador,codigo);
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
    private void subirOpciones() {
        CuerpoMensaje cuerpo = new CuerpoMensaje();
        cuerpo.setCodigoSala(codigo);
        cuerpo.setFichas(fichas);
        cuerpo.setJugadores(jugadores);
        cuerpo.setMonto(monto);
        cuerpo.setTamaño(tamaño);
        
        TipoMensaje tipo = TipoMensaje.PASAR_OPCIONES; 

        Mensaje mensaje = new Mensaje.Builder()
                .body(cuerpo)
                .messageType(tipo)
                .build();
        parent.enviarMensaje(mensaje);
    }
    /**
     * Recibe las ocpiones del control e inicia el tablero con estas
     *
     * @param tamaño
     * @param monto
     * @param fichas
     * @param jugadores
     * @param codigo
     */
    public void recibirOpciones(int tamaño, int monto, int fichas, int jugadores, String codigo) {
        parent.PasarPantallaTablero(this, tamaño, monto, fichas, jugadores, miJugador,codigo);
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
            
            //Añade un jugador extra
            if(!parent.isHost){
                añadirJugador(miJugador);
            }
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
            parent.desconectar(this.codigo,miJugador);
            parent.PasarPantallaOpciones(this);
    }
    
    public void confirmarVolver(){
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres volver?", "Salir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            volver();
        }
    }

    /**
     * Cierra el programa
     */
    public void cerrar() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            parent.desconectar(this.codigo, miJugador);
            parent.CerrarPrograma();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCodigo = new javax.swing.JPanel();
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
        pnlInstruccionTexto = new javax.swing.JPanel();
        lblContexto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pnlCodigo.setBackground(new java.awt.Color(192, 160, 123));
        pnlCodigo.setMaximumSize(new java.awt.Dimension(300, 75));
        pnlCodigo.setMinimumSize(new java.awt.Dimension(300, 75));

        lblCodigo.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblCodigo.setText("CODIGO: J5BA2C");

        javax.swing.GroupLayout pnlCodigoLayout = new javax.swing.GroupLayout(pnlCodigo);
        pnlCodigo.setLayout(pnlCodigoLayout);
        pnlCodigoLayout.setHorizontalGroup(
            pnlCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCodigoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblCodigo)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pnlCodigoLayout.setVerticalGroup(
            pnlCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCodigoLayout.createSequentialGroup()
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblJugarMousePressed(evt);
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
            .addComponent(lblJugar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
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

        pnlInstruccionTexto.setBackground(new java.awt.Color(192, 160, 123));

        lblContexto.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        lblContexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContexto.setText("Invita amigos usando el codigo de la sala");

        javax.swing.GroupLayout pnlInstruccionTextoLayout = new javax.swing.GroupLayout(pnlInstruccionTexto);
        pnlInstruccionTexto.setLayout(pnlInstruccionTextoLayout);
        pnlInstruccionTextoLayout.setHorizontalGroup(
            pnlInstruccionTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInstruccionTextoLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(lblContexto))
        );
        pnlInstruccionTextoLayout.setVerticalGroup(
            pnlInstruccionTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstruccionTextoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContexto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(231, Short.MAX_VALUE)
                .addComponent(pnlCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(pnlInstruccionTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(lblP1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(107, 107, 107)
                            .addComponent(lblP2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(108, 108, 108)
                            .addComponent(lblP3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblP4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(gatoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(conchaIcono)
                            .addGap(45, 45, 45)
                            .addComponent(piramideIcono)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mazorcaIcono)))
                    .addGap(38, 38, 38)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(pnlCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
                .addComponent(pnlInstruccionTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlJugar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlVolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(122, 122, 122)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblP1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblP2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblP3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblP4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(43, 43, 43)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(piramideIcono)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(conchaIcono, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(gatoIcon))
                        .addComponent(mazorcaIcono))
                    .addContainerGap(196, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMouseClicked
       
    }//GEN-LAST:event_lblVolverMouseClicked

    private void pnlVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlVolverMouseClicked
       
    }//GEN-LAST:event_pnlVolverMouseClicked

    private void lblJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJugarMouseClicked
        
    }//GEN-LAST:event_lblJugarMouseClicked

    private void pnlJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlJugarMouseClicked
    
    }//GEN-LAST:event_pnlJugarMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
 
    }//GEN-LAST:event_formWindowClosed

    private void lblVolverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMousePressed
         confirmarVolver();
    }//GEN-LAST:event_lblVolverMousePressed

    private void lblJugarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJugarMousePressed
        jugar();
    }//GEN-LAST:event_lblJugarMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel conchaIcono;
    private javax.swing.JLabel gatoIcon;
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
    private javax.swing.JPanel pnlCodigo;
    private javax.swing.JPanel pnlInstruccionTexto;
    private javax.swing.JPanel pnlJugar;
    private javax.swing.JPanel pnlVolver;
    // End of variables declaration//GEN-END:variables
}
