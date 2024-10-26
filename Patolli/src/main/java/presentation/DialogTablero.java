package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
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
public class DialogTablero extends JDialog {

    //Clases (Son iguales para todas las instancias, NO se envian al control)
    private final JFrame parent; //JFrame padre
    private final IControlJuego controlJuego; //Control del juego
    private final IControlApuestas controlApuestas; //Control de apuestas
    private final Utils utils; //Herramientas para calculos
    
    //Opciones (Se definen en la creacion de la pantalla, NO se envian al control)
    private final int tamaño; //Tamaño del tablero
    private final int monto; //Cantidad de monto de apuestas al comenzar
    private final int fichas; //Cantidad de fichas por jugador
    private final int jugadores; //Cantidad de jugadores
    private final int miJugador; //Representa el jugador dueño de la pantalla
    private List<Integer> casillasOrdenadas; //Arreglo de numeros que traduce el verdadero orden de las casillas a progreso
    
    //Banderas (Usados a nivel de interfaz, NO se envian al control)
    private int resultadoCañas; //Resultado del ultimo lanzamiento de cañas
    private boolean puedeMover; //Indica si el jugador puede mover una ficha
    private int turnosExtra; //Indica los turnos extras del jugador
    
    //Bandera jugador (Usado a nivel de interfaz, se envia al control)
    private int jugador = 0; //Representa al jugador actual
      
    //Labeles de fichas (Apariencia de fichas, Se envian al control)
    private List<JLabel> fichasGato;
    private List<JLabel> fichasConcha;
    private List<JLabel> fichasPiramide;
    private List<JLabel> fichasMazorca;
    
    //Posición de fichas (Posicion real de fichas, Se envian al control)
    private List<Integer> fichasGatoPosicion;
    private List<Integer> fichasConchaPosicion;
    private List<Integer> fichasPiramidePosicion;
    private List<Integer> fichasMazorcaPosicion;
    
    //Monto de jugadores (Valor de apuestas, Se envia al control)
    private List<Integer> montoJugadores; //Lista de apuestas de los jugadores
    
    //Casillas del tablero (Apariencia del tablero, Se envia al control)
    private List<JLabel> casillas;  //Lista de labeles que forma el tablero de forma visual
    //Tamaño        8           
    //Totales       68          
    //Inicio        (Arriba: 15,    Abajo: 16,       Derecha: 40,   Izquieda: 55)          
    //Doble turno   (Arriba: 0,1,   Abajo: 30,31,    Derecha: 39,47   Izquieda: 48,56)          
    //Pagar apuesta (Arriba: 6,7,   Abajo: 24,25,    Derecha: 36,44   Izquieda: 51,59)         
    //Centrales     (64,65,66,67)
    
    //Tamaño        10           
    //Totales       84          
    //Inicio        (Arriba: 19,    Abajo: 20,       Derecha: 50,   Izquieda: 69)          
    //Doble turno   (Arriba: 0,1,   Abajo: 38,39,    Derecha: 49,59   Izquieda: 60,70)          
    //Pagar apuesta (Arriba: 6,7,   Abajo: 32,33,    Derecha: 46,56   Izquieda: 63,73)         
    //Centrales     (80,81,82,83) 
    
    //Tamaño        12           
    //Totales       100          
    //Inicio        (Arriba: 23,    Abajo: 24,       Derecha: 60,   Izquieda: 83)          
    //Doble turno   (Arriba: 0,1,   Abajo: 46,47,    Derecha: 59,71   Izquieda: 72,84)          
    //Pagar apuesta (Arriba: 6,7,   Abajo: 40,41,    Derecha: 56,68   Izquieda: 75,87)         
    //Centrales     (96,97,98,99)
    /**
     * Constructor de la clase FrameTablero.
     *
     * @param parent JFrame inicio
     * @param tamaño el tamaño del tablero
     * @param monto el monto de las apuestas
     * @param fichas el número de fichas disponibles
     * @param jugadores el número de jugadores
     * @param miJugador el jugador dueño de esta pantalla
     */
    public DialogTablero(JFrame parent, int tamaño, int monto, int fichas, int jugadores, int miJugador) {
        super(parent, true); 
        this.parent=parent;
        this.setResizable(false);
        initComponents();
        utils = new Utils();
        this.tamaño = tamaño;
        this.monto = monto;
        //TODO
        //this.miJugador=miJugador;
        this.miJugador=0;
        this.fichas = fichas;
        this.jugadores = jugadores;
        casillas = new ArrayList<>();
        controlApuestas=new ControlApuestas();
        controlJuego=new ControlJuego();
        inicializarGui();
        siguienteJugador(); 
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cerrar(); 
            }
        });
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
        reacomodarCasillas();

        //Cañas
        this.inicializarImagen(this.lblCaña1, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña2, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña3, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña4, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña5, "/cañaLisa.png", 27, 54);
        
        // Monto
        montoJugadores=new ArrayList<>();
        for (int i = 0; i < jugadores; i++) {
             montoJugadores.add(monto);
        }
        this.lblConchaApuesta.setText("Apuestas: " + monto);
        this.lblGatoApuestas.setText("Apuestas: " + monto);
        this.lblMazorcaApuesta.setText("Apuestas: " + monto);
        this.lblPiramideApuesta.setText("Apuestas: " + monto);
        
        //Limites de panel
        this.pnlJugadores.setLayout(null);
        this.pnlGato.setLayout(null);
        this.pnlConcha.setLayout(null);
        this.pnlMazorca.setLayout(null);
        this.pnlPiramide.setLayout(null);
        
        // Iconos
        inicializarImagen(this.iconGato, "/cat.png", 120, 120);
        inicializarImagen(this.iconConcha, "/concha.png", 120, 120);
        inicializarImagen(this.iconPiramide, "/piramide.png", 120, 120);
        inicializarImagen(this.iconMazorca, "/mazorca.png", 120, 120);
        
        inicializarImagen(this.lblInicioTigre, "/cat.png", 35, 35);
        inicializarImagen(this.lblInicioConcha, "/concha.png", 35, 35);
        inicializarImagen(this.lblInicioPiramide, "/piramide.png", 35, 35);
        inicializarImagen(this.lblInicioMazorca, "/mazorca.png", 35, 35);

        //Posicion de fichas
        fichasGatoPosicion = new ArrayList<>();
        fichasConchaPosicion = new ArrayList<>();
        fichasPiramidePosicion = new ArrayList<>();
        fichasMazorcaPosicion = new ArrayList<>();

        for (int i = 0; i < fichas; i++) {
            fichasGatoPosicion.add(-1);
            fichasConchaPosicion.add(-1);
            fichasPiramidePosicion.add(-1);
            fichasMazorcaPosicion.add(-1);
        }

        // Labeles de Fichas
        int contadorFichas = 0;

        fichasConcha = new ArrayList<>();
        fichasConcha.add(this.fichaConcha1);
        fichasConcha.add(this.fichaConcha2);
        fichasConcha.add(this.fichaConcha3);
        fichasConcha.add(this.fichaConcha4);
        fichasConcha.add(this.fichaConcha5);
        fichasConcha.add(this.fichaConcha6);

        fichasGato = new ArrayList<>();
        fichasGato.add(this.fichaGato1);
        fichasGato.add(this.fichaGato2);
        fichasGato.add(this.fichaGato3);
        fichasGato.add(this.fichaGato4);
        fichasGato.add(this.fichaGato5);
        fichasGato.add(this.fichaGato6);

        fichasPiramide = new ArrayList<>();
        fichasPiramide.add(this.fichaPiramide1);
        fichasPiramide.add(this.fichaPiramide2);
        fichasPiramide.add(this.fichaPiramide3);
        fichasPiramide.add(this.fichaPiramide4);
        fichasPiramide.add(this.fichaPiramide5);
        fichasPiramide.add(this.fichaPiramide6);

        fichasMazorca = new ArrayList<>();
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
            fichasConcha.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    clickearFicha(numFicha,numJugador); // Envía el índice o referencia de la ficha
                }
            });
        }
        
        for (int i = 0; i < fichasPiramide.size(); i++) {
            final int numFicha = i; // Guardar el índice de la ficha actual
            final int numJugador = 2; // Guardar el índice de la ficha actual
            fichasPiramide.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickearFicha(numFicha,numJugador); // Envía el índice o referencia de la ficha
                }
            });
        }
        for (int i = 0; i < fichasGato.size(); i++) {
            final int numFicha = i; // Guardar el índice de la ficha actual
            final int numJugador = 0; // Guardar el índice de la ficha actual
            fichasGato.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickearFicha(numFicha, numJugador); // Envía el índice o referencia de la ficha
                }
            });
        }

        // Jugadores
        for (int i = 0; i < -jugadores + 4; i++) {
            switch (2 - i) {
                case 2 -> {
                    this.pnlJugadores.remove(this.pnlMazorca);
                    this.lblInicioMazorca.setVisible(false);
                }
                case 1 -> {
                    this.pnlJugadores.remove(this.pnlPiramide);
                    this.lblInicioPiramide.setVisible(false);
                }
                case 0 -> {
                    this.pnlJugadores.remove(this.pnlConcha);
                    this.lblInicioConcha.setVisible(false);
                }
            }
        }
        
    }
    /**
     * Le da un orden logico a la lista de casillas para mejor manipulacion
     */
    private void reacomodarCasillas() {
        casillasOrdenadas = new ArrayList<>();
        switch (tamaño) {
            case 8 -> {
                //Tamaño 8 -> 68 elementos
                //15, 13, 11, 9, 7, 5, 3, 1, 0, 2, 4, 6, 8, 10, 12, 14, 64
                //55, 54, 53, 52, 51, 50, 49, 48, 56, 57, 58, 59, 60, 61, 62, 63, 66
                //16, 18, 20, 22, 24, 26, 28, 30, 31, 29, 27, 25, 23, 21, 19, 17, 67
                //40, 41, 42, 43, 44, 45, 46, 47, 39, 38, 37, 36, 35, 34, 33, 32, 65
                casillasOrdenadas.add(15);
                casillasOrdenadas.add(13);
                casillasOrdenadas.add(11);
                casillasOrdenadas.add(9);
                casillasOrdenadas.add(7);
                casillasOrdenadas.add(5);
                casillasOrdenadas.add(3);
                casillasOrdenadas.add(1);
                casillasOrdenadas.add(0);
                casillasOrdenadas.add(2);
                casillasOrdenadas.add(4);
                casillasOrdenadas.add(6);
                casillasOrdenadas.add(8);
                casillasOrdenadas.add(10);
                casillasOrdenadas.add(12);
                casillasOrdenadas.add(14);
                casillasOrdenadas.add(64);

                casillasOrdenadas.add(55);
                casillasOrdenadas.add(54);
                casillasOrdenadas.add(53);
                casillasOrdenadas.add(52);
                casillasOrdenadas.add(51);
                casillasOrdenadas.add(50);
                casillasOrdenadas.add(49);
                casillasOrdenadas.add(48);
                casillasOrdenadas.add(56);
                casillasOrdenadas.add(57);
                casillasOrdenadas.add(58);
                casillasOrdenadas.add(59);
                casillasOrdenadas.add(60);
                casillasOrdenadas.add(61);
                casillasOrdenadas.add(62);
                casillasOrdenadas.add(63);
                casillasOrdenadas.add(66);

                casillasOrdenadas.add(16);
                casillasOrdenadas.add(18);
                casillasOrdenadas.add(20);
                casillasOrdenadas.add(22);
                casillasOrdenadas.add(24);
                casillasOrdenadas.add(26);
                casillasOrdenadas.add(28);
                casillasOrdenadas.add(30);
                casillasOrdenadas.add(31);
                casillasOrdenadas.add(29);
                casillasOrdenadas.add(27);
                casillasOrdenadas.add(25);
                casillasOrdenadas.add(23);
                casillasOrdenadas.add(21);
                casillasOrdenadas.add(19);
                casillasOrdenadas.add(17);
                casillasOrdenadas.add(67);

                casillasOrdenadas.add(40);
                casillasOrdenadas.add(41);
                casillasOrdenadas.add(42);
                casillasOrdenadas.add(43);
                casillasOrdenadas.add(44);
                casillasOrdenadas.add(45);
                casillasOrdenadas.add(46);
                casillasOrdenadas.add(47);
                casillasOrdenadas.add(39);
                casillasOrdenadas.add(38);
                casillasOrdenadas.add(37);
                casillasOrdenadas.add(36);
                casillasOrdenadas.add(35);
                casillasOrdenadas.add(34);
                casillasOrdenadas.add(33);
                casillasOrdenadas.add(32);
                casillasOrdenadas.add(65);
            }
            case 10 -> {
            }
            case 12 -> {
            }

        }
    }
    /**
     * Intenta mover la ficha a la que se le hizo click si se cumplen las condiciones
     * @param numeroFicha
     * @param numJugador
     */
    private void clickearFicha(int numeroFicha, int numJugador) {
        System.out.println("Ficha " + numeroFicha + " Jugador " + numJugador);
        moverFicha(numeroFicha, numJugador);
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

            // Añadir el JLabel al tablero y a la lista de casillas
            tablero.add(label);
            casillas.add(label);
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
     * Actualiza la vista del tablero con la lista de casillas
     */
    private void actualizarTablero() {
        // Limpiar el tablero actual antes de volver a agregar las casillas
        this.tableroArriba.removeAll();
        this.tableroAbajo.removeAll();
        this.tableroDerecha.removeAll();
        this.tableroIzquierda.removeAll();
        this.tableroCentro.removeAll();

        int totalCasillas = casillas.size();
        int casillasLaterales = (totalCasillas - 4) / 4; // Número de casillas para cada lado (excepto el centro)

        // Panel de Arriba (primer cuarto de las casillas)
        for (int i = 0; i < casillasLaterales; i++) {
            tableroArriba.add(casillas.get(i));
        }

        // Panel de Abajo (segundo cuarto de las casillas)
        for (int i = casillasLaterales; i < 2 * casillasLaterales; i++) {
            tableroAbajo.add(casillas.get(i));
        }

        // Panel de Derecha (tercer cuarto de las casillas)
        for (int i = 2 * casillasLaterales; i < 3 * casillasLaterales; i++) {
            tableroDerecha.add(casillas.get(i));
        }

        // Panel de Izquierda (último cuarto de las casillas laterales)
        for (int i = 3 * casillasLaterales; i < 4 * casillasLaterales; i++) {
            tableroIzquierda.add(casillas.get(i));
        }

        // Panel del Centro (las últimas 4 casillas)
        for (int i = totalCasillas - 4; i < totalCasillas; i++) {
            tableroCentro.add(casillas.get(i));
        }

        // Refrescar todos los paneles para que los cambios sean visibles
        tableroArriba.revalidate();
        tableroDerecha.revalidate();
        tableroAbajo.revalidate();
        tableroIzquierda.revalidate();
        tableroCentro.revalidate();

        tableroArriba.repaint();
        tableroDerecha.repaint();
        tableroAbajo.repaint();
        tableroIzquierda.repaint();
        tableroCentro.repaint();
    }
    /**
     * Actualoza la vista de siguiente jugador de GUI
     */
    private void actualizarSiguienteJugador(){
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
     * Actualiza el monto de apuestas en la interfaz grafica
     */
    private void actualizarApuestas() {
        for (int i = 0; i < jugadores; i++) {
            switch (i) {
                case 0 -> {
                    this.lblGatoApuestas.setText("Apuestas: " + montoJugadores.get(i));
                }
                case 1 -> {
                    this.lblConchaApuesta.setText("Apuestas: " + montoJugadores.get(i));
                }
                case 2 -> {
                    this.lblPiramideApuesta.setText("Apuestas: " + montoJugadores.get(i));
                }
                case 3 -> {
                    this.lblMazorcaApuesta.setText("Apuestas: " + montoJugadores.get(i));
                }
            }
        }
    }
    /**
     * Actualiza el label de turnos extra del GUI
     */
    private void actualizarTurnosExtra(){
        this.lblTurnosExtra.setText("Turnos Extra: "+turnosExtra);
    }
    /**
     * Cierra la aplicación después de confirmar la salida con el usuario.
     */
    public void salir() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Realmente quieres salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            if (parent instanceof FrameInicio frameInicio) {
                frameInicio.PasarPantallaInicio(this);
            }
            
        }
    }
    /**
     * Simula el lanzamiento de las cañas y muestra el resultado en el campo de
     * texto.
     */
    public void lanzarCañas() {

        resultadoCañas = utils.GenerarLanzamiento();
        this.txtResultado.setText(String.valueOf(resultadoCañas));

        this.inicializarImagen(this.lblCaña1, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña2, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña3, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña4, "/cañaLisa.png", 27, 54);
        this.inicializarImagen(this.lblCaña5, "/cañaLisa.png", 27, 54);

        switch (resultadoCañas) {
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
        this.btnLanzarCañas.setEnabled(false);
        iluminarFichas(true);
    }
    /**
     * Avanza al siguiente jugador en el turno. Ilumina el jugador del turno
     * actual e ilumina el botón de lanzar cañas.
     */
    public void siguienteJugador() {
        actualizarTurnosExtra();
        //Si no tiene turnos extra
        if (turnosExtra <= 0) {
            //Pasa el turno al siguiente jugador
            jugador++;
            if (jugador >= jugadores + 1) {
                jugador = 0;
            }
            //TODO: Borrar esta linea
            this.btnLanzarCañas.setEnabled(true);

        } else {
            turnosExtra--;
            this.btnLanzarCañas.setEnabled(true);
        }

        actualizarApuestas();
        actualizarSiguienteJugador();
        subirCambios();
    }
    /**
     * Cierra la ventana y comunica la desconexion del jugador
     */
    public void cerrar() {
        if (parent instanceof FrameInicio frameInicio) {
            frameInicio.CerrarPrograma();
        }
    }
    /**
     * Ilumina las fichas del jugador actual, resaltando las fichas que pueden
     * moverse y las que no.
     * @param iluminar Indica si se quiere iluminar las fichas o dejar de iluminarlas
     */
    public void iluminarFichas(boolean iluminar) {
        List<JLabel> fichasIluminables = getListaFichasMiJugador();
        List<Integer> fichasIluminablesPosicion = getListaFichasPosicionMiJugador();
        boolean casillaInicialLibre = casillas.get(getCasillaInicialMiJugador()).getIcon() == null;
        if (fichasIluminables != null && fichasIluminablesPosicion != null) {
            for (int i = 0; i < fichas; i++) {
                if (iluminar) {
                    //Si la ficha esta fuera del tablero, no se ilumina
                    if (fichasIluminablesPosicion.get(i) != -1) {
                        //Si la ficha fuera a terminar en una casilla ocupada, no se ilumina
                        if (casillas.get(getPosicionReal(fichasIluminablesPosicion.get(i))).getIcon() == null) {
                            puedeMover = true;
                            fichasIluminables.get(i).setBorder(BorderFactory.createLineBorder(Color.BLUE, 3)); // Borde azul
                        } 
                        //A menos que haya sacado un 1 en las cañas, siempre y cuando la casilla de inicio este despejada
                    } else if (this.resultadoCañas == 1 && casillaInicialLibre) {
                        fichasIluminables.get(i).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3)); // Borde amarillo 
                        puedeMover = true;
                    }
                } else {
                    fichasIluminables.get(i).setBorder(null); // Sin borde
                }
            }
        }
        //Si no puede mover nada
        if (!puedeMover && iluminar) {
            //Paga 1 apuesta
            cobrarApuesta(miJugador, -1);
            JOptionPane.showMessageDialog(null, "Pagas 1 apuesta", "No puedes moverte", JOptionPane.INFORMATION_MESSAGE);
 
            //Termina el turno
            siguienteJugador();
        }
    }
    /**
     * Simula el movimiento de una ficha.
     *
     * @param numFicha Identificador de la ficha a mover
     * @param numJugador Dueño de la ficha
     */
    public void moverFicha(int numFicha, int numJugador) {
        JLabel miFicha=getListaFichasMiJugador().get(numFicha);
        Border borde = miFicha.getBorder();
        if (borde instanceof LineBorder lineBorder) {
            Color colorBorde = lineBorder.getLineColor(); // Obtiene el color del borde

            if (colorBorde.equals(Color.BLUE)) {
                //Si la ficha tiene borde azul, significa que va a moverse por el tablero
                
                int posicionRelativa = getListaFichasPosicionMiJugador().get(numFicha);   
                
                //Calcula la posicion anterior
                int posicionAnterior = casillasOrdenadas.indexOf(posicionRelativa);
                //Obtiene la casilla real donde va la ficha
                int casillaTermina = getPosicionReal(posicionRelativa);

                //Hacer un nuevo label en el tablero
                JLabel labelAux = new JLabel();
                labelAux.setBorder(new LineBorder(Color.BLACK, 1)); 
                labelAux.setOpaque(true); 
                Color colorCasilla=casillas.get(casillaTermina).getBackground();
                labelAux.setBackground(colorCasilla);
                labelAux.setIcon(miFicha.getIcon());
                labelAux.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        clickearFicha(numFicha, numJugador);
                    }
                });
                
                //Quitar el viejo label del tablero
                JLabel labelEmpty = new JLabel();
                labelEmpty.setBorder(new LineBorder(Color.BLACK, 1)); 
                labelEmpty.setOpaque(true); 
                labelEmpty.setBackground(casillas.get(getListaFichasPosicionMiJugador().get(numFicha)).getBackground());
      
                casillas.set(casillaTermina, labelAux);
                casillas.set(getListaFichasPosicionMiJugador().get(numFicha), labelEmpty);

                //Actualizar la posicion en la lista
                getListaFichasPosicionMiJugador().set(numFicha, casillaTermina);
                getListaFichasMiJugador().set(numFicha, labelAux);

                //Casilla pagar apuesta
                if (colorCasilla.equals(Color.RED)) {
                    cobrarApuesta(miJugador, -2);
                    JOptionPane.showMessageDialog(null, "Pagas 2 apuestas", "Casilla pagar apuesta", JOptionPane.INFORMATION_MESSAGE);
                }

                //Casilla doble turno
                if (colorCasilla.equals(Color.BLUE)) {
                    ganaTurnoExtra(2);
                    JOptionPane.showMessageDialog(null, "Obtienes 2 turnos extra", "Casilla doble turno", JOptionPane.INFORMATION_MESSAGE);
                }

                //Vuelta al tablero
                if (posicionAnterior > casillasOrdenadas.indexOf(casillaTermina)) {
                    cobrarApuesta(miJugador, jugadores);
                    for (int i = 0; i < jugadores; i++) {
                        if (miJugador != i) {
                            cobrarApuesta(i, -1);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Cobraste 1 apuesta a todos los demas jugadores", "Vuelta al tablero", JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (colorBorde.equals(Color.YELLOW)) {
                //Si la ficha tiene borde amarillo, significa que va a entrar al tablero
                
                //Hacer un nuevo label en el tablero
                JLabel labelAux = new JLabel();
                labelAux.setBorder(new LineBorder(Color.BLACK, 1)); 
                labelAux.setOpaque(true); 
                labelAux.setBackground(casillas.get(getCasillaInicialMiJugador()).getBackground());
                labelAux.setIcon(miFicha.getIcon());
                labelAux.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        clickearFicha(numFicha, numJugador); 
                    }
                });
                casillas.set(getCasillaInicialMiJugador(), labelAux);

                //Actualizar la posicion en la lista
                getListaFichasPosicionMiJugador().set(numFicha, getCasillaInicialMiJugador());
                getListaFichasMiJugador().set(numFicha, labelAux);
                
                //Esconder la ficha del tablero del jugador
                miFicha.setVisible(false);
            }
        }
        iluminarFichas(false);
        actualizarTablero();
        actualizarApuestas();
        actualizarTurnosExtra();
        siguienteJugador();
    }
    /**
     * Aumenta el valor de turnos Extra
     * @param numTurnos entero a aumentar a turnos extra
     */
    public void ganaTurnoExtra(int numTurnos){
        turnosExtra+=numTurnos;
    }
    /**
     * Cambia el monto de los jugadores
     * @param jugadorCobra numero que indica el jugador a cobrar la apuesta
     * @param monto cantidad de la apuesta
     */
    public void cobrarApuesta(int jugadorCobra, int monto) {
        montoJugadores.set(jugadorCobra, montoJugadores.get(jugadorCobra) + monto);
    }
    /**
     * No implementado aun
     * @param numJugador
     * @param numFicha 
     */
    public void eliminarFicha(int numJugador, int numFicha){
        //TODO: Lo hace la interfaz
    }
    /**
     * Devuelve la posicion de la casilla inicial del jugador dueño de la
     * pantalla
     *
     * @return entero con la posicion relativa de la casilla inicial
     */
    private int getCasillaInicialMiJugador() {
        switch (miJugador) {
            case 0 -> {
                switch (tamaño) {
                    case 8 -> {
                        return 15;
                    }
                    case 10 -> {
                        return 19;
                    }
                    case 12 -> {
                        return 23;
                    }
                }
            }
            case 1 -> {
                switch (tamaño) {
                    case 8 -> {
                        return 16;
                    }
                    case 10 -> {
                        return 20;
                    }
                    case 12 -> {
                        return 24;
                    }
                }
            }
            case 2 -> {
                switch (tamaño) {
                    case 8 -> {
                        return 40;
                    }
                    case 10 -> {
                        return 50;
                    }
                    case 12 -> {
                        return 60;
                    }
                }
            }
            case 3 -> {
                switch (tamaño) {
                    case 8 -> {
                        return 55;
                    }
                    case 10 -> {
                        return 69;
                    }
                    case 12 -> {
                        return 83;
                    }
                }
            }
        }
        return -1;
    }
    /**
     * Obtiene la lista de posiciones de fichas por el jugador dueño de la pantalla
     * @return Lista de Integers que reprsenta las posiciones de las fichas en el tablero
     */
    private List<Integer> getListaFichasPosicionMiJugador(){
        List<Integer> listaCasillasPosicion=new ArrayList<>();
        switch (miJugador) {
            case 0 -> {
                listaCasillasPosicion=this.fichasGatoPosicion;
            }
            case 1 -> {
                listaCasillasPosicion=this.fichasConchaPosicion;
            }
            case 2 -> {
                listaCasillasPosicion=this.fichasPiramidePosicion;
            }
            case 3 -> {
                listaCasillasPosicion=this.fichasMazorcaPosicion;
            }
        }
        return listaCasillasPosicion;
    }
    /**
     * Obtiene la lista JLabels de fichas por el jugador dueño de la pantalla
     * @return la lista JLabels dueño de la pantalla
     */
    private List<JLabel> getListaFichasMiJugador() {
        List<JLabel> listaCasillas = new ArrayList<>();
        switch (miJugador) {
            case 0 -> {
                listaCasillas = this.fichasGato;
            }
            case 1 -> {
                listaCasillas = this.fichasConcha;
            }
            case 2 -> {
                listaCasillas = this.fichasPiramide;
            }
            case 3 -> {
                listaCasillas = this.fichasMazorca;
            }
        }
        return listaCasillas;
    }
    /**
     * Transforma la posicion relativa del tablero a su posicion de orden de progreso
     * @param posicionRelativa numero de Casilla ordenada segun la vista del tablero
     * @return numero de progreso en el tablero
     */
    private int getPosicionReal(int posicionRelativa) {
        int casillaTermina = casillasOrdenadas.indexOf(posicionRelativa);
        casillaTermina += resultadoCañas;
        casillaTermina = casillaTermina % ((tamaño * 8) + 4);
        casillaTermina = casillasOrdenadas.get(casillaTermina);
        return casillaTermina;
    }
    /**
     * Manda el valor del tablero, el monto de los jugadores y el siguientejugador al control del juego
     * @return Verdadero si se actualizo con exito
     */
    private void subirCambios(){
        this.controlJuego.actualizarCambios(casillas, montoJugadores,jugador,fichasGato,fichasConcha,fichasPiramide,fichasMazorca,
        fichasGatoPosicion,fichasConchaPosicion,fichasPiramidePosicion,fichasMazorcaPosicion);
    }
    /**
     * Recibe los cambios del control y los coloca en la pantalla
     * @param casillas Tablero actualizado
     * @param montoJugadores Monto de apuestas de los jugadores
     * @param jugador Siguiente Jugador
     * @param fichasGato Apariencia de fichas gato
     * @param fichasConcha Apariencia de fichas concha
     * @param fichasPiramide Apariencia de fichas piramide
     * @param fichasMazorca Apariencia de fichas mazorca
     * @param fichasGatoPosicion Posicion de fichas gato
     * @param fichasConchaPosicion Posicion de fichas concha
     * @param fichasPiramidePosicion Posicion de fichas piramide
     * @param fichasMazorcaPosicion Posicion de fichas mazorca
     */
    public void recibirCambios(List<JLabel> casillas, List<Integer> montoJugadores, int jugador, List<JLabel> fichasGato,
            List<JLabel> fichasConcha, List<JLabel> fichasPiramide, List<JLabel> fichasMazorca, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {

        this.casillas = casillas;
        this.montoJugadores = montoJugadores;
        this.jugador = jugador;
        this.fichasGato=fichasGato;
        this.fichasConcha=fichasConcha;
        this.fichasPiramide=fichasPiramide;
        this.fichasMazorca=fichasMazorca;
        this.fichasGatoPosicion=fichasGatoPosicion;
        this.fichasConchaPosicion=fichasConchaPosicion;
        this.fichasPiramidePosicion=fichasPiramidePosicion;
        this.fichasMazorcaPosicion=fichasMazorcaPosicion;
        
        this.actualizarTablero();
        this.actualizarApuestas();
        this.actualizarSiguienteJugador();

        //Si es mi turno, habilita el lanzar cañas
        if(jugador==miJugador){
            this.btnLanzarCañas.setEnabled(true);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTablero = new javax.swing.JPanel();
        tableroArriba = new javax.swing.JPanel();
        tableroCentro = new javax.swing.JPanel();
        tableroAbajo = new javax.swing.JPanel();
        tableroDerecha = new javax.swing.JPanel();
        tableroIzquierda = new javax.swing.JPanel();
        lblTurnoDe = new javax.swing.JLabel();
        lblIconJugadorActual = new javax.swing.JLabel();
        lblInicioTigre = new javax.swing.JLabel();
        lblInicioPiramide = new javax.swing.JLabel();
        lblInicioMazorca = new javax.swing.JLabel();
        lblInicioConcha = new javax.swing.JLabel();
        lblTurnosExtra = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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
            .addGap(0, 0, Short.MAX_VALUE)
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

        lblTurnoDe.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblTurnoDe.setText("Turno de:");

        lblIconJugadorActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTurnosExtra.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblTurnosExtra.setText("Turnos Extra: 0");

        javax.swing.GroupLayout pnlTableroLayout = new javax.swing.GroupLayout(pnlTablero);
        pnlTablero.setLayout(pnlTableroLayout);
        pnlTableroLayout.setHorizontalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableroIzquierda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableroLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInicioMazorca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInicioConcha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTurnosExtra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTableroLayout.createSequentialGroup()
                                .addComponent(lblTurnoDe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblIconJugadorActual, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addComponent(tableroAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInicioPiramide, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableroArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tableroCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableroDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInicioTigre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlTableroLayout.setVerticalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableroLayout.createSequentialGroup()
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblIconJugadorActual, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(lblTurnoDe)))
                        .addGap(18, 18, 18)
                        .addComponent(lblTurnosExtra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblInicioMazorca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableroArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInicioTigre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tableroDerecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableroIzquierda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableroCentro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addComponent(lblInicioConcha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableroAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInicioPiramide, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6))
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
        salir();
    }//GEN-LAST:event_btnSalirActionPerformed
    /**
    * Maneja el evento de acción cuando se hace clic en el botón "Lanzar Cañas".
    * Llama al método LanzarCañas para simular el lanzamiento de las cañas.
    *
    * @param evt el evento de acción generado al hacer clic en el botón
    */
    private void btnLanzarCañasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarCañasActionPerformed
        lanzarCañas();
    }//GEN-LAST:event_btnLanzarCañasActionPerformed
    /**
    * Maneja el evento de clic del mouse en el botón "Salir".
    * Llama al método Salir para cerrar la aplicación.
    *
    * @param evt el evento de clic del mouse generado al hacer clic en el botón
    */
    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        salir();
    }//GEN-LAST:event_btnSalirMouseClicked
    /**
    * Maneja el evento de clic del mouse en el botón "Lanzar Cañas".
    * Llama al método LanzarCañas para simular el lanzamiento de las cañas.
    *
    * @param evt el evento de clic del mouse generado al hacer clic en el botón
    */
    private void btnLanzarCañasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLanzarCañasMouseClicked

    }//GEN-LAST:event_btnLanzarCañasMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
  
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLanzarCañas;
    private javax.swing.JButton btnSalir;
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
    private javax.swing.JLabel lblInicioConcha;
    private javax.swing.JLabel lblInicioMazorca;
    private javax.swing.JLabel lblInicioPiramide;
    private javax.swing.JLabel lblInicioTigre;
    private javax.swing.JLabel lblMazorcaApuesta;
    private javax.swing.JLabel lblPiramideApuesta;
    private javax.swing.JLabel lblTurnoDe;
    private javax.swing.JLabel lblTurnosExtra;
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
