package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import negocio.ControlApuestas;
import negocio.ControlJuego;
import negocio.IControlApuestas;
import negocio.IControlJuego;
import utils.Utils;

/**
 * Clase que representa el tablero del juego.
 * Esta clase se encarga de inicializar y gestionar el estado del tablero, 
 * así como las interacciones de los jugadores.
 * 
 * @author t1pas
 */
public class FrameTablero extends javax.swing.JFrame {

    Utils utils;
    public int tamaño;
    public int monto;
    public int fichas;
    public int jugadores;
    public IControlJuego controlJuego;
    public IControlApuestas controlApuestas;
    
    //TODO: Eliminar atributo
    int jugador=0;

    /**
     * Constructor de la clase FrameTablero.
     *
     * @param tamaño el tamaño del tablero
     * @param monto el monto de las apuestas
     * @param fichas el número de fichas disponibles
     * @param jugadores el número de jugadores
     */
    public FrameTablero(int tamaño, int monto, int fichas, int jugadores) {
        this.setResizable(false);
        initComponents();
        utils = new Utils();
        this.tamaño = tamaño;
        this.monto = monto;
        this.fichas = fichas;
        this.jugadores = jugadores;
        controlApuestas=new ControlApuestas();
        controlJuego=new ControlJuego();
        inicializarGui();
        SiguienteJugador();
    }

    /**
     * Inicializa la interfaz gráfica de usuario del tablero.
     * Configura los componentes del tablero y establece el estado inicial
     * de los elementos visuales, como fichas y montos de apuesta.
     */
    private void inicializarGui() {
        // Tablero
        inicializarTablero(tableroArriba, tamaño, 2, false);
        inicializarTablero(tableroAbajo, tamaño, 2, true);
        inicializarTablero(tableroDerecha, 2, tamaño, true);
        inicializarTablero(tableroIzquierda, 2, tamaño, false);
        inicializarTablero(tableroCentro, 2, 2, true);

        //Cañas
        this.inicializarImagen(this.lblCaña1, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña2, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña3, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña4, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña5, "/cañaLisa.png", 27, 54);
        
        // Monto
        this.lblConchaApuesta.setText("Apuestas: " + monto);
        this.lblGatoApuestas.setText("Apuestas: " + monto);
        this.lblMazorcaApuesta.setText("Apuestas: " + monto);
        this.lblPiramideApuesta.setText("Apuestas: " + monto);

        // Iconos
        inicializarImagen(this.iconGato, "/cat.png", 120, 120);
        inicializarImagen(this.iconConcha, "/concha.png", 120, 120);
        inicializarImagen(this.iconPiramide, "/piramide.png", 120, 120);
        inicializarImagen(this.iconMazorca, "/mazorca.png", 120, 120);

        // Fichas
        int contadorFichas = 0;

        List<JLabel> fichasConcha = new ArrayList<>();
        fichasConcha.add(this.fichaConcha1);
        fichasConcha.add(this.fichaConcha2);
        fichasConcha.add(this.fichaConcha3);
        fichasConcha.add(this.fichaConcha4);
        fichasConcha.add(this.fichaConcha5);
        fichasConcha.add(this.fichaConcha6);

        List<JLabel> fichasGato = new ArrayList<>();
        fichasGato.add(this.fichaGato1);
        fichasGato.add(this.fichaGato2);
        fichasGato.add(this.fichaGato3);
        fichasGato.add(this.fichaGato4);
        fichasGato.add(this.fichaGato5);
        fichasGato.add(this.fichaGato6);

        List<JLabel> fichasPiramide = new ArrayList<>();
        fichasPiramide.add(this.fichaPiramide1);
        fichasPiramide.add(this.fichaPiramide2);
        fichasPiramide.add(this.fichaPiramide3);
        fichasPiramide.add(this.fichaPiramide4);
        fichasPiramide.add(this.fichaPiramide5);
        fichasPiramide.add(this.fichaPiramide6);

        List<JLabel> fichasMazorca = new ArrayList<>();
        fichasMazorca.add(this.fichaMazorca1);
        fichasMazorca.add(this.fichaMazorca2);
        fichasMazorca.add(this.fichaMazorca3);
        fichasMazorca.add(this.fichaMazorca4);
        fichasMazorca.add(this.fichaMazorca5);
        fichasMazorca.add(this.fichaMazorca6);

        for (int i = 0; i < fichasConcha.size(); i++) {
            if (contadorFichas >= fichas) {
                // Remueve el label
                this.pnlConcha.remove(fichasConcha.get(i));
                this.pnlGato.remove(fichasGato.get(i));
                this.pnlPiramide.remove(fichasPiramide.get(i));
                this.pnlMazorca.remove(fichasMazorca.get(i));
            } else {
                // Inicializa la imagen
                inicializarImagen(fichasConcha.get(i), "/concha.png", 20, 20);
                inicializarImagen(fichasGato.get(i), "/cat.png", 20, 20);
                inicializarImagen(fichasPiramide.get(i), "/piramide.png", 20, 20);
                inicializarImagen(fichasMazorca.get(i), "/mazorca.png", 20, 20);
            }
            contadorFichas++;
        }
        
        for (int i = 0; i < fichasMazorca.size(); i++) {
            final int numFicha = i; // Guardar el índice de la ficha actual
            final int numJugador = 3; // Guardar el índice de la ficha actual
            fichasMazorca.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickearFicha(numFicha,numJugador); // Envía el índice o referencia de la ficha
                }
            });
        }
        
        for (int i = 0; i < fichasConcha.size(); i++) {
            final int numFicha = i; // Guardar el índice de la ficha actual
            final int numJugador = 1; // Guardar el índice de la ficha actual
            fichasMazorca.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickearFicha(numFicha,numJugador); // Envía el índice o referencia de la ficha
                }
            });
        }
        
        for (int i = 0; i < fichasPiramide.size(); i++) {
            final int numFicha = i; // Guardar el índice de la ficha actual
            final int numJugador = 2; // Guardar el índice de la ficha actual
            fichasMazorca.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickearFicha(numFicha,numJugador); // Envía el índice o referencia de la ficha
                }
            });
        }
        
        for (int i = 0; i < fichasGato.size(); i++) {
            final int numFicha = i; // Guardar el índice de la ficha actual
            final int numJugador = 0; // Guardar el índice de la ficha actual
            fichasMazorca.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickearFicha(numFicha,numJugador); // Envía el índice o referencia de la ficha
                }
            });
        }
        
        // Jugadores
        for (int i = 0; i < -jugadores + 4; i++) {
            switch (2 - i) {
                case 2 -> {
                    this.pnlJugadores.remove(this.pnlMazorca);
                }
                case 1 -> {
                    this.pnlJugadores.remove(this.pnlPiramide);
                }
                case 0 -> {
                    this.pnlJugadores.remove(this.pnlConcha);
                }
            }
        }
    }
    
    private void clickearFicha(int numeroFicha, int numJugador){
        //TODO: Usar el nombre de
        System.out.println("Ficha "+numeroFicha+" Jugador"+numJugador);
       
    }

    /**
     * Inicializa un tablero específico con un diseño de cuadrícula.
     *
     * @param tablero el panel que representa el tablero
     * @param filas el número de filas en el tablero
     * @param columnas el número de columnas en el tablero
     * @param invertir indica si se debe invertir el color de las casillas
     */
    private void inicializarTablero(JPanel tablero, int filas, int columnas, boolean invertir) {
        tablero.setLayout(new GridLayout(filas, columnas));
        tablero.setPreferredSize(tablero.getSize());
        tablero.setMinimumSize(tablero.getSize());
        tablero.setMaximumSize(tablero.getSize());

        for (int i = 1; i <= filas * columnas; i++) {
            JLabel label = new JLabel(""); // Crea un nuevo JLabel
            label.setBorder(new LineBorder(Color.BLACK, 1)); // Añadir borde negro
            label.setOpaque(true); // Hacer el fondo visible
            label.setBackground(Color.WHITE);
            
            // Lógica para asignar colores a las casillas del tablero
            if (filas * columnas > 6) {
                // Colocar casilla inicial (Amarilla)
                if (invertir) {
                    if (columnas > filas) {
                        if (i == columnas + 1) {
                            label.setBackground(Color.YELLOW); // DERECHA
                        }
                    } else {
                        if (i == 1) {
                            label.setBackground(Color.YELLOW); // ABAJO
                        }
                    }
                } else {
                    if (columnas > filas) {
                        if (i == columnas) {
                            label.setBackground(Color.YELLOW); // IZQUIERDA
                        }
                    } else {
                        if (i == filas * columnas) {
                            label.setBackground(Color.YELLOW); // ARRIBA
                        }
                    }
                }
                
                // Colocar casilla doble turno (Azul)
                if (invertir) {
                    if (columnas > filas) {
                        if (i == columnas || i == columnas * filas) {
                            label.setBackground(Color.BLUE); // DERECHA
                        }
                    } else {
                        if (i == filas * columnas || i == filas * columnas - 1) {
                            label.setBackground(Color.BLUE); // ABAJO
                        }
                    }
                } else {
                    if (columnas > filas) {
                        if (i == 1 || i == columnas + 1) {
                            label.setBackground(Color.BLUE); // IZQUIERDA
                        }
                    } else {
                        if (i == 1 || i == 2) {
                            label.setBackground(Color.BLUE); // ARRIBA
                        }
                    }
                }
                
                // Colocar casilla pagar apuesta (ROJA)
                if (invertir) {
                    if (columnas > filas) {
                        if (i == columnas - 3 || i == columnas * filas - 3) {
                            label.setBackground(Color.RED); // DERECHA
                        }
                    } else {
                        if (i == filas * columnas - 7 || i == filas * columnas - 6) {
                            label.setBackground(Color.RED); // ABAJO
                        }
                    }
                } else {
                    if (columnas > filas) {
                        if (i == 4 || i == columnas + 4) {
                            label.setBackground(Color.RED); // IZQUIERDA
                        }
                    } else {
                        if (i == 7 || i == 8) {
                            label.setBackground(Color.RED); // ARRIBA
                        }
                    }
                }
            }

            // Añadir el JLabel al tablero
            tablero.add(label);
        }
    }

    /**
     * Inicializa una imagen en un JLabel con las dimensiones especificadas.
     *
     * @param ficha el JLabel donde se establecerá la imagen
     * @param url la URL de la imagen
     * @param x el ancho de la imagen
     * @param y la altura de la imagen
     */
    private void inicializarImagen(JLabel ficha, String url, int x, int y) {
        try {
            // Cargar la imagen desde la URL
            ImageIcon icon = new ImageIcon(getClass().getResource(url));

            // Escalar la imagen a las dimensiones (x, y)
            Image img = icon.getImage().getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);

            // Crear un nuevo ImageIcon con la imagen escalada
            icon = new ImageIcon(img);

            ficha.setText("");
            // Asignar el ícono al JLabel
            ficha.setIcon(icon);

            // Establecer el tamaño del JLabel para asegurarte de que mantenga las dimensiones
            ficha.setPreferredSize(new Dimension(x, y));
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    /**
     * Cierra la aplicación después de confirmar la salida con el usuario.
     */
    public void Salir() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Realmente quieres salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            FrameInicio inicio = new FrameInicio();
            inicio.setVisible(true);
            this.dispose();
        }
    }

    /**
     * Simula el lanzamiento de las cañas y muestra el resultado en el campo de
     * texto.
     */
    public void LanzarCañas() {
        // Lanza las cañas
        int resultado = utils.GenerarLanzamiento();
        this.txtResultado.setText(String.valueOf(resultado));

        this.inicializarImagen(this.lblCaña1, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña2, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña3, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña4, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña5, "/cañaLisa.png", 27, 54);
        
        switch (resultado) {
            case 10:
                this.inicializarImagen(this.lblCaña5, "/cañaMarcada.png", 27, 54);
            case 4:
                this.inicializarImagen(this.lblCaña4, "/cañaMarcada.png", 27, 54);
            case 3:
                this.inicializarImagen(this.lblCaña3, "/cañaMarcada.png", 27, 54);
            case 2:
                this.inicializarImagen(this.lblCaña2, "/cañaMarcada.png", 27, 54);
            case 1:
                this.inicializarImagen(this.lblCaña1, "/cañaMarcada.png", 27, 54);
        }
    }

    /**
     * Avanza al siguiente jugador en el turno. Ilumina el jugador del turno
     * actual e ilumina el botón de lanzar cañas.
     */
    public void SiguienteJugador() {
        //TODO: Implementar con negocio
        
        jugador++;
        if (jugador >= jugadores+1) {
            jugador = 1;
        }

        switch (jugador) {
            case 1 -> {
                this.inicializarImagen(this.lblIconJugadorActual, "/cat.png", 64, 60);
            }
            case 2 -> {
                this.inicializarImagen(this.lblIconJugadorActual, "/concha.png", 64, 60);
            }
            case 3 -> {
                this.inicializarImagen(this.lblIconJugadorActual, "/piramide.png", 64, 60);
            }
            case 4 -> {
                this.inicializarImagen(this.lblIconJugadorActual, "/mazorca.png", 64, 60);
            }
        }


    }

    /**
     * Permite a los jugadores realizar apuestas.
     */
    public void Apostar() {
        //TODO: Implementar con negocio
        
    }

    /**
     * Ilumina las fichas del jugador actual, 
     * resaltando las fichas que pueden moverse y las que no.
     */
    public void IluminarFichas() {
        //TODO: implementar con el negocio
    }

    /**
     * Simula el movimiento de una ficha.
     */
    public void MueveFicha() {
        // TODO: implementar con el negocio
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTablero = new javax.swing.JPanel();
        tableroArriba = new javax.swing.JPanel();
        tableroCentro = new javax.swing.JPanel();
        tableroAbajo = new javax.swing.JPanel();
        tableroDerecha = new javax.swing.JPanel();
        tableroIzquierda = new javax.swing.JPanel();
        btnSiguienteJugador = new javax.swing.JButton();
        btnPagarApuesta = new javax.swing.JButton();
        lblTurnoDe = new javax.swing.JLabel();
        lblIconJugadorActual = new javax.swing.JLabel();
        pnlJugadores = new javax.swing.JPanel();
        pnlConcha = new javax.swing.JPanel();
        iconConcha = new javax.swing.JLabel();
        lblConchaApuesta = new javax.swing.JLabel();
        fichaConcha2 = new javax.swing.JLabel();
        fichaConcha3 = new javax.swing.JLabel();
        fichaConcha1 = new javax.swing.JLabel();
        fichaConcha6 = new javax.swing.JLabel();
        fichaConcha4 = new javax.swing.JLabel();
        fichaConcha5 = new javax.swing.JLabel();
        pnlPiramide = new javax.swing.JPanel();
        iconPiramide = new javax.swing.JLabel();
        fichaPiramide5 = new javax.swing.JLabel();
        fichaPiramide6 = new javax.swing.JLabel();
        fichaPiramide3 = new javax.swing.JLabel();
        fichaPiramide4 = new javax.swing.JLabel();
        fichaPiramide1 = new javax.swing.JLabel();
        fichaPiramide2 = new javax.swing.JLabel();
        lblPiramideApuesta = new javax.swing.JLabel();
        pnlMazorca = new javax.swing.JPanel();
        iconMazorca = new javax.swing.JLabel();
        fichaMazorca5 = new javax.swing.JLabel();
        fichaMazorca6 = new javax.swing.JLabel();
        fichaMazorca3 = new javax.swing.JLabel();
        fichaMazorca4 = new javax.swing.JLabel();
        fichaMazorca2 = new javax.swing.JLabel();
        fichaMazorca1 = new javax.swing.JLabel();
        lblMazorcaApuesta = new javax.swing.JLabel();
        pnlGato = new javax.swing.JPanel();
        fichaGato2 = new javax.swing.JLabel();
        fichaGato1 = new javax.swing.JLabel();
        fichaGato3 = new javax.swing.JLabel();
        fichaGato5 = new javax.swing.JLabel();
        fichaGato6 = new javax.swing.JLabel();
        fichaGato4 = new javax.swing.JLabel();
        lblGatoApuestas = new javax.swing.JLabel();
        iconGato = new javax.swing.JLabel();
        pnlBotones = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnLanzarCañas = new javax.swing.JButton();
        txtResultado = new javax.swing.JTextField();
        lblCaña1 = new javax.swing.JLabel();
        lblCaña3 = new javax.swing.JLabel();
        lblCaña2 = new javax.swing.JLabel();
        lblCaña5 = new javax.swing.JLabel();
        lblCaña4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 0));

        pnlTablero.setBackground(new java.awt.Color(102, 102, 0));
        pnlTablero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableroArriba.setBackground(new java.awt.Color(102, 102, 0));
        tableroArriba.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout tableroArribaLayout = new javax.swing.GroupLayout(tableroArriba);
        tableroArriba.setLayout(tableroArribaLayout);
        tableroArribaLayout.setHorizontalGroup(
            tableroArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        tableroArribaLayout.setVerticalGroup(
            tableroArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );

        tableroCentro.setBackground(new java.awt.Color(102, 102, 0));
        tableroCentro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout tableroCentroLayout = new javax.swing.GroupLayout(tableroCentro);
        tableroCentro.setLayout(tableroCentroLayout);
        tableroCentroLayout.setHorizontalGroup(
            tableroCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        tableroCentroLayout.setVerticalGroup(
            tableroCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        tableroAbajo.setBackground(new java.awt.Color(102, 102, 0));
        tableroAbajo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout tableroAbajoLayout = new javax.swing.GroupLayout(tableroAbajo);
        tableroAbajo.setLayout(tableroAbajoLayout);
        tableroAbajoLayout.setHorizontalGroup(
            tableroAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        tableroAbajoLayout.setVerticalGroup(
            tableroAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        tableroDerecha.setBackground(new java.awt.Color(102, 102, 0));
        tableroDerecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout tableroDerechaLayout = new javax.swing.GroupLayout(tableroDerecha);
        tableroDerecha.setLayout(tableroDerechaLayout);
        tableroDerechaLayout.setHorizontalGroup(
            tableroDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );
        tableroDerechaLayout.setVerticalGroup(
            tableroDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        tableroIzquierda.setBackground(new java.awt.Color(102, 102, 0));
        tableroIzquierda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout tableroIzquierdaLayout = new javax.swing.GroupLayout(tableroIzquierda);
        tableroIzquierda.setLayout(tableroIzquierdaLayout);
        tableroIzquierdaLayout.setHorizontalGroup(
            tableroIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );
        tableroIzquierdaLayout.setVerticalGroup(
            tableroIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        btnSiguienteJugador.setText("TODO: SiguienteJugador()");
        btnSiguienteJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteJugadorActionPerformed(evt);
            }
        });

        btnPagarApuesta.setText("TODO: PagarApuesta()");
        btnPagarApuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarApuestaActionPerformed(evt);
            }
        });

        lblTurnoDe.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblTurnoDe.setText("Turno de:");

        lblIconJugadorActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlTableroLayout = new javax.swing.GroupLayout(pnlTablero);
        pnlTablero.setLayout(pnlTableroLayout);
        pnlTableroLayout.setHorizontalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableroIzquierda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnSiguienteJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPagarApuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addComponent(lblTurnoDe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblIconJugadorActual, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableroAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tableroArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tableroCentro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tableroDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlTableroLayout.setVerticalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableroLayout.createSequentialGroup()
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tableroArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblTurnoDe))
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblIconJugadorActual, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tableroCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tableroDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tableroIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tableroAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnSiguienteJugador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPagarApuesta)))
                .addContainerGap())
        );

        pnlJugadores.setBackground(new java.awt.Color(102, 102, 0));
        pnlJugadores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlConcha.setBackground(new java.awt.Color(102, 102, 0));
        pnlConcha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        iconConcha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/concha.png"))); // NOI18N

        lblConchaApuesta.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lblConchaApuesta.setText("Apuestas:");

        fichaConcha2.setText("2");

        fichaConcha3.setText("3");

        fichaConcha1.setText("1");

        fichaConcha6.setText("6");

        fichaConcha4.setText("4");

        fichaConcha5.setText("5");

        javax.swing.GroupLayout pnlConchaLayout = new javax.swing.GroupLayout(pnlConcha);
        pnlConcha.setLayout(pnlConchaLayout);
        pnlConchaLayout.setHorizontalGroup(
            pnlConchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConchaLayout.createSequentialGroup()
                .addComponent(iconConcha, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlConchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlConchaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblConchaApuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConchaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(pnlConchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlConchaLayout.createSequentialGroup()
                                .addComponent(fichaConcha1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fichaConcha2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlConchaLayout.createSequentialGroup()
                                .addComponent(fichaConcha3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fichaConcha4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlConchaLayout.createSequentialGroup()
                                .addComponent(fichaConcha5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fichaConcha6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        pnlConchaLayout.setVerticalGroup(
            pnlConchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconConcha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlConchaLayout.createSequentialGroup()
                .addComponent(lblConchaApuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlConchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fichaConcha5)
                    .addComponent(fichaConcha6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlConchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fichaConcha3)
                    .addComponent(fichaConcha4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlConchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fichaConcha1)
                    .addComponent(fichaConcha2)))
        );

        pnlPiramide.setBackground(new java.awt.Color(102, 102, 0));
        pnlPiramide.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        iconPiramide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/piramide.png"))); // NOI18N

        fichaPiramide5.setText("5");

        fichaPiramide6.setText("6");

        fichaPiramide3.setText("3");

        fichaPiramide4.setText("4");

        fichaPiramide1.setText("1");

        fichaPiramide2.setText("2");

        lblPiramideApuesta.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lblPiramideApuesta.setText("Apuestas:");

        javax.swing.GroupLayout pnlPiramideLayout = new javax.swing.GroupLayout(pnlPiramide);
        pnlPiramide.setLayout(pnlPiramideLayout);
        pnlPiramideLayout.setHorizontalGroup(
            pnlPiramideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPiramideLayout.createSequentialGroup()
                .addComponent(iconPiramide, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPiramideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPiramideLayout.createSequentialGroup()
                        .addComponent(fichaPiramide1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fichaPiramide2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPiramideLayout.createSequentialGroup()
                        .addComponent(fichaPiramide3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fichaPiramide4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPiramideLayout.createSequentialGroup()
                        .addComponent(fichaPiramide5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fichaPiramide6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPiramideApuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        pnlPiramideLayout.setVerticalGroup(
            pnlPiramideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPiramideLayout.createSequentialGroup()
                .addComponent(iconPiramide, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPiramideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPiramideApuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPiramideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fichaPiramide5)
                    .addComponent(fichaPiramide6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPiramideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fichaPiramide3)
                    .addComponent(fichaPiramide4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPiramideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fichaPiramide1)
                    .addComponent(fichaPiramide2))
                .addContainerGap())
        );

        pnlMazorca.setBackground(new java.awt.Color(102, 102, 0));
        pnlMazorca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        iconMazorca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mazorca.png"))); // NOI18N
        iconMazorca.setText("jLabel5");

        fichaMazorca5.setText("5");

        fichaMazorca6.setText("6");

        fichaMazorca3.setText("3");

        fichaMazorca4.setText("4");

        fichaMazorca2.setText("2");

        fichaMazorca1.setText("1");

        lblMazorcaApuesta.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lblMazorcaApuesta.setText("Apuestas:");

        javax.swing.GroupLayout pnlMazorcaLayout = new javax.swing.GroupLayout(pnlMazorca);
        pnlMazorca.setLayout(pnlMazorcaLayout);
        pnlMazorcaLayout.setHorizontalGroup(
            pnlMazorcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMazorcaLayout.createSequentialGroup()
                .addComponent(iconMazorca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMazorcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMazorcaLayout.createSequentialGroup()
                        .addComponent(fichaMazorca1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fichaMazorca2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMazorcaLayout.createSequentialGroup()
                        .addComponent(fichaMazorca3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fichaMazorca4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMazorcaLayout.createSequentialGroup()
                        .addComponent(fichaMazorca5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fichaMazorca6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMazorcaApuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        pnlMazorcaLayout.setVerticalGroup(
            pnlMazorcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMazorcaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMazorcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlMazorcaLayout.createSequentialGroup()
                        .addComponent(lblMazorcaApuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlMazorcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fichaMazorca5)
                            .addComponent(fichaMazorca6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMazorcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fichaMazorca3)
                            .addComponent(fichaMazorca4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMazorcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fichaMazorca1)
                            .addComponent(fichaMazorca2)))
                    .addComponent(iconMazorca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlGato.setBackground(new java.awt.Color(102, 102, 0));
        pnlGato.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        fichaGato2.setText("2");

        fichaGato1.setText("1");

        fichaGato3.setText("3");

        fichaGato5.setText("5");

        fichaGato6.setText("6");

        fichaGato4.setText("4");

        lblGatoApuestas.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lblGatoApuestas.setText("Apuestas:");

        iconGato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cat.png"))); // NOI18N

        javax.swing.GroupLayout pnlGatoLayout = new javax.swing.GroupLayout(pnlGato);
        pnlGato.setLayout(pnlGatoLayout);
        pnlGatoLayout.setHorizontalGroup(
            pnlGatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGatoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconGato, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlGatoLayout.createSequentialGroup()
                            .addComponent(fichaGato1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fichaGato2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlGatoLayout.createSequentialGroup()
                            .addGroup(pnlGatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(fichaGato5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fichaGato3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlGatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fichaGato4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fichaGato6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblGatoApuestas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlGatoLayout.setVerticalGroup(
            pnlGatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGatoLayout.createSequentialGroup()
                        .addComponent(iconGato, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(pnlGatoLayout.createSequentialGroup()
                        .addComponent(lblGatoApuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlGatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fichaGato5)
                            .addComponent(fichaGato6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlGatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fichaGato4)
                            .addComponent(fichaGato3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlGatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fichaGato1)
                            .addComponent(fichaGato2)))))
        );

        javax.swing.GroupLayout pnlJugadoresLayout = new javax.swing.GroupLayout(pnlJugadores);
        pnlJugadores.setLayout(pnlJugadoresLayout);
        pnlJugadoresLayout.setHorizontalGroup(
            pnlJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJugadoresLayout.createSequentialGroup()
                .addGroup(pnlJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJugadoresLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlGato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJugadoresLayout.createSequentialGroup()
                        .addContainerGap(29, Short.MAX_VALUE)
                        .addGroup(pnlJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlConcha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlPiramide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlMazorca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlJugadoresLayout.setVerticalGroup(
            pnlJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJugadoresLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(pnlGato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlConcha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPiramide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlMazorca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlBotones.setBackground(new java.awt.Color(102, 102, 0));
        pnlBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnSalir.setBackground(new java.awt.Color(192, 160, 123));
        btnSalir.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnLanzarCañas.setBackground(new java.awt.Color(192, 160, 123));
        btnLanzarCañas.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnLanzarCañas.setText("Lanzar Cañas");
        btnLanzarCañas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLanzarCañasMouseClicked(evt);
            }
        });
        btnLanzarCañas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanzarCañasActionPerformed(evt);
            }
        });

        txtResultado.setEditable(false);
        txtResultado.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        txtResultado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblCaña1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cañaLisa.png"))); // NOI18N

        lblCaña3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cañaLisa.png"))); // NOI18N

        lblCaña2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cañaLisa.png"))); // NOI18N

        lblCaña5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cañaLisa.png"))); // NOI18N

        lblCaña4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cañaLisa.png"))); // NOI18N

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCaña1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCaña2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lblCaña3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCaña4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCaña5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLanzarCañas, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlBotonesLayout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnLanzarCañas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(lblCaña2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lblCaña3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lblCaña4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lblCaña5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(lblCaña1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlJugadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlJugadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
    * Maneja el evento de acción cuando se hace clic en el botón "Salir".
    * Llama al método Salir para cerrar la aplicación.
    *
    * @param evt el evento de acción generado al hacer clic en el botón
    */
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        Salir();
    }//GEN-LAST:event_btnSalirActionPerformed
    /**
    * Maneja el evento de acción cuando se hace clic en el botón "Lanzar Cañas".
    * Llama al método LanzarCañas para simular el lanzamiento de las cañas.
    *
    * @param evt el evento de acción generado al hacer clic en el botón
    */
    private void btnLanzarCañasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarCañasActionPerformed
        LanzarCañas();
    }//GEN-LAST:event_btnLanzarCañasActionPerformed
    /**
    * Maneja el evento de clic del mouse en el botón "Salir".
    * Llama al método Salir para cerrar la aplicación.
    *
    * @param evt el evento de clic del mouse generado al hacer clic en el botón
    */
    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        Salir();
    }//GEN-LAST:event_btnSalirMouseClicked
    /**
    * Maneja el evento de clic del mouse en el botón "Lanzar Cañas".
    * Llama al método LanzarCañas para simular el lanzamiento de las cañas.
    *
    * @param evt el evento de clic del mouse generado al hacer clic en el botón
    */
    private void btnLanzarCañasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLanzarCañasMouseClicked
        LanzarCañas();
    }//GEN-LAST:event_btnLanzarCañasMouseClicked
    /**
    * Maneja el evento de acción cuando se hace clic en el botón "Pagar Apuesta".
    * Llama al método Apostar para permitir a los jugadores realizar apuestas.
    *
    * @param evt el evento de acción generado al hacer clic en el botón
    */
    private void btnPagarApuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarApuestaActionPerformed
        Apostar();
    }//GEN-LAST:event_btnPagarApuestaActionPerformed
    /**
    * Maneja el evento de acción cuando se hace clic en el botón "Siguiente Jugador".
    * Llama al método SiguienteJugador para avanzar al siguiente jugador en el turno.
    *
    * @param evt el evento de acción generado al hacer clic en el botón
    */
    private void btnSiguienteJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteJugadorActionPerformed
        SiguienteJugador();
    }//GEN-LAST:event_btnSiguienteJugadorActionPerformed

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
            java.util.logging.Logger.getLogger(FrameTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameTablero(8, 10, 4, 3).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLanzarCañas;
    private javax.swing.JButton btnPagarApuesta;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSiguienteJugador;
    private javax.swing.JLabel fichaConcha1;
    private javax.swing.JLabel fichaConcha2;
    private javax.swing.JLabel fichaConcha3;
    private javax.swing.JLabel fichaConcha4;
    private javax.swing.JLabel fichaConcha5;
    private javax.swing.JLabel fichaConcha6;
    private javax.swing.JLabel fichaGato1;
    private javax.swing.JLabel fichaGato2;
    private javax.swing.JLabel fichaGato3;
    private javax.swing.JLabel fichaGato4;
    private javax.swing.JLabel fichaGato5;
    private javax.swing.JLabel fichaGato6;
    private javax.swing.JLabel fichaMazorca1;
    private javax.swing.JLabel fichaMazorca2;
    private javax.swing.JLabel fichaMazorca3;
    private javax.swing.JLabel fichaMazorca4;
    private javax.swing.JLabel fichaMazorca5;
    private javax.swing.JLabel fichaMazorca6;
    private javax.swing.JLabel fichaPiramide1;
    private javax.swing.JLabel fichaPiramide2;
    private javax.swing.JLabel fichaPiramide3;
    private javax.swing.JLabel fichaPiramide4;
    private javax.swing.JLabel fichaPiramide5;
    private javax.swing.JLabel fichaPiramide6;
    private javax.swing.JLabel iconConcha;
    private javax.swing.JLabel iconGato;
    private javax.swing.JLabel iconMazorca;
    private javax.swing.JLabel iconPiramide;
    private javax.swing.JLabel lblCaña1;
    private javax.swing.JLabel lblCaña2;
    private javax.swing.JLabel lblCaña3;
    private javax.swing.JLabel lblCaña4;
    private javax.swing.JLabel lblCaña5;
    private javax.swing.JLabel lblConchaApuesta;
    private javax.swing.JLabel lblGatoApuestas;
    private javax.swing.JLabel lblIconJugadorActual;
    private javax.swing.JLabel lblMazorcaApuesta;
    private javax.swing.JLabel lblPiramideApuesta;
    private javax.swing.JLabel lblTurnoDe;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlConcha;
    private javax.swing.JPanel pnlGato;
    private javax.swing.JPanel pnlJugadores;
    private javax.swing.JPanel pnlMazorca;
    private javax.swing.JPanel pnlPiramide;
    private javax.swing.JPanel pnlTablero;
    private javax.swing.JPanel tableroAbajo;
    private javax.swing.JPanel tableroArriba;
    private javax.swing.JPanel tableroCentro;
    private javax.swing.JPanel tableroDerecha;
    private javax.swing.JPanel tableroIzquierda;
    private javax.swing.JTextField txtResultado;
    // End of variables declaration//GEN-END:variables
}
