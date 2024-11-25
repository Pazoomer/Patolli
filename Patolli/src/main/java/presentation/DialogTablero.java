package presentation;

import cliente.CuerpoMensaje;
import cliente.Mensaje;
import cliente.TipoMensaje;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import utils.Utils;

/**
 * Clase que representa el tablero del juego.
 * Esta clase se encarga de inicializar y gestionar el estado del tablero, 
 * así como las interacciones de los jugadores.
 * 
 * @author t1pas
 */
public class DialogTablero extends JDialog {

    //VARIABLES QUE NO SE ENVIAN AL CONTROL
    
    //Clases (Son iguales para todas las instancias, NO pueden cambiar)
    private final FrameInicio parent; //JFrame padre
    private final Utils utils; //Herramientas para calculos
    
    //Opciones (Se definen en la creacion de la pantalla, NO pueden cambiar)
    private final int tamaño; //Tamaño del tablero
    private final int monto; //Cantidad de monto de apuestas al comenzar
    private final int fichas; //Cantidad de fichas por jugador
    private final int jugadores; //Cantidad de jugadores
    private final int miJugador; //Representa el jugador dueño de la pantalla
    private final String codigoSala;
    
    //Banderas (Usados a nivel de interfaz)
    private int resultadoCañas; //Resultado del ultimo lanzamiento de cañas
    private boolean puedeMover; //Indica si el jugador puede mover una ficha
    private int turnosExtra; //Indica los turnos extras del jugador
    private boolean juegoAcabo; //Indica si el juego finalizo
    private final List<Boolean> jugadoresActivos; //Representa si los jugadores siguen jugando
    private List<Integer> podio = new ArrayList<>(); //Representa el orden de ganadores
    private boolean primerLanzamiento = true;

    //Representan la lista de mensajes que se almacenan durante el movimiento de una ficha
    private List<String> mensaje = new ArrayList<>();
    private List<String> titulo = new ArrayList<>(); 

    //Labeles de fichas (Apariencia de fichas)
    private List<JLabel> fichasGato;
    private List<JLabel> fichasConcha;
    private List<JLabel> fichasPiramide;
    private List<JLabel> fichasMazorca;
    
    //Casillas del tablero
    private final List<JLabel> casillas; //Apariencia del tablero
    private List<Integer> casillasOrdenadas; //Arreglo de numeros que traduce el verdadero orden de las casillas a progreso
    
    //Variables de depuracion
    private boolean modoDev=false; //Activa la funcion de tablero enumerado, boton lanzarCañas siempre activado
    private int j=0; //Sirve para enumerar el tablero
    
    //VARIABLES QUE SE ENVIAN AL CONTROL
    
    //Representa al jugador actual
    private int jugador = 0;
    
    //Posicion real de fichas en casillas
    private List<Integer> fichasGatoPosicion;
    private List<Integer> fichasConchaPosicion;
    private List<Integer> fichasPiramidePosicion;
    private List<Integer> fichasMazorcaPosicion;
    
    //Monto de apuestas de jugadores 
    private List<Integer> montoJugadores;
    
    /**
     * Constructor de la clase FrameTablero.
     *
     * @param parent JFrame inicio
     * @param tamaño el tamaño del tablero
     * @param monto el monto de las apuestas
     * @param fichas el número de fichas disponibles
     * @param jugadores el número de jugadores
     * @param miJugador el jugador dueño de esta pantalla
     * @param codigoSala el codigo de la sala
     */
    public DialogTablero(FrameInicio parent, int tamaño, int monto, int fichas, int jugadores, int miJugador, String codigoSala) {
        super(parent, false); 
        this.parent=parent;
        this.setResizable(false);

        //AQUI SE CAMBIA EL ESTILO DE LA PANTALLA
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        initComponents();
        utils = new Utils();
        this.tamaño = tamaño;
        this.monto = monto;
        this.miJugador=miJugador;
        this.fichas = fichas;
        this.jugadores = jugadores;
        this.codigoSala=codigoSala;
        casillas = new ArrayList<>();
        jugadoresActivos= new ArrayList<>();
        for (int i = 0; i < jugadores; i++) {
            jugadoresActivos.add(true);
        }
        inicializarGui();
    
        if(miJugador==0 || modoDev){
            this.btnLanzarCañas.setEnabled(true);
        }else{
            this.btnLanzarCañas.setEnabled(false);
        }
        
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
     * Solo se llama una vez durante la inicialización
     */
    private void inicializarGui() {
        // Tablero
        inicializarAspa(tableroArriba, tamaño, 2, false);
        inicializarAspa(tableroAbajo, tamaño, 2, true);
        inicializarAspa(tableroDerecha, 2, tamaño, true);
        inicializarAspa(tableroIzquierda, 2, tamaño, false);
        inicializarAspa(tableroCentro, 2, 2, true);
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
            final int numFicha = i; // Guarda el índice de la ficha actual
            final int numJugador = 3; // Guarda el índice del jugador
            fichasMazorca.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickearFicha(numFicha,numJugador); // Envía el índice o referencia de la ficha
                }
            });
        }
        
        for (int i = 0; i < fichasConcha.size(); i++) {
            final int numFicha = i; // Guarda el índice de la ficha actual
            final int numJugador = 1; // Guarda el índice del jugador
            fichasConcha.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    clickearFicha(numFicha,numJugador); // Envía el índice o referencia de la ficha
                }
            });
        }
        
        for (int i = 0; i < fichasPiramide.size(); i++) {
            final int numFicha = i; // Guarda el índice de la ficha actual
            final int numJugador = 2; // Guarda el índice del jugador
            fichasPiramide.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickearFicha(numFicha,numJugador); // Envía el índice o referencia de la ficha
                }
            });
        }
        for (int i = 0; i < fichasGato.size(); i++) {
            final int numFicha = i; // Guarda el índice de la ficha actual
            final int numJugador = 0; // Guarda el índice del jugador
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
        //Label mi jugador
        switch (miJugador) {
            case 0 -> {
                this.inicializarImagen(this.lblMiJugador, "/cat.png", 64, 60);
            }
            case 1 -> {
                this.inicializarImagen(this.lblMiJugador, "/concha.png", 64, 60);
            }
            case 2 -> {
                this.inicializarImagen(this.lblMiJugador, "/piramide.png", 64, 60);
            }
            case 3 -> {
                this.inicializarImagen(this.lblMiJugador, "/mazorca.png", 64, 60);
            }
        }
    }
    /**
     * Le da un orden logico a la lista de casillas para mejor manipulacion
     * Solo se llama una vez durante la inicialización
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
                int[] arr = new int[]{15, 13, 11, 9, 7, 5, 3, 1, 0, 2, 4, 6, 8, 10, 12, 14, 64,
                    55, 54, 53, 52, 51, 50, 49, 48, 56, 57, 58, 59, 60, 61, 62, 63, 66,
                    16, 18, 20, 22, 24, 26, 28, 30, 31, 29, 27, 25, 23, 21, 19, 17, 67,
                    40, 41, 42, 43, 44, 45, 46, 47, 39, 38, 37, 36, 35, 34, 33, 32, 65};
                for (int i : arr) {
                    casillasOrdenadas.add(i);
                }

            }
            case 10 -> {
                //Tamaño 10 -> 84 elementos
                //19, 17, 15, 13, 11, 9, 7, 5, 3, 1, 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 80
                //69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 82
                //20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 39, 37, 35, 33, 31, 29, 27, 25, 23, 21, 83
                //50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 81
                int[] arr = new int[]{19, 17, 15, 13, 11, 9, 7, 5, 3, 1, 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 80,
                    69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 82,
                    20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 39, 37, 35, 33, 31, 29, 27, 25, 23, 21, 83,
                    50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 81};
                for (int i : arr) {
                    casillasOrdenadas.add(i);
                }
            }
            case 12 -> {
                //Tamaño 12 -> 100 elementos
                //23, 21, 19, 17, 15, 13, 11, 9, 7, 5, 3, 1, 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 96
                //83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 98
                //24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 47, 45, 43, 41, 39, 37, 35, 33, 31, 29, 27, 25, 99
                //60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 97
                int[] arr = new int[]{23, 21, 19, 17, 15, 13, 11, 9, 7, 5, 3, 1, 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 96,
                    83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 98,
                    24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 47, 45, 43, 41, 39, 37, 35, 33, 31, 29, 27, 25, 99,
                    60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 97};
                for (int i : arr) {
                    casillasOrdenadas.add(i);
                }
            }
        }
    }
    /**
     * Intenta mover la ficha a la que se le hizo click si se cumplen las condiciones
     * @param numeroFicha Numero de la ficha a moverse
     * @param numJugador Numero del jugador dueño de la ficha
     */
    private void clickearFicha(int numeroFicha, int numJugador) {
        if(juegoAcabo && !modoDev){
            JOptionPane.showMessageDialog(null, "La partida termino", "Juego finalizado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (jugador != miJugador && !modoDev) {
            JOptionPane.showMessageDialog(null, "No es tu turno", "No puedes mover", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (jugador == numJugador || modoDev) {
            moverFicha(numeroFicha, numJugador);
        }else{
            JOptionPane.showMessageDialog(null, "La ficha es de otro jugador", "No puedes mover esa ficha", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Inicializa un tablero específico con un diseño de cuadrícula.
     * Solo se llama durante la inicialización de la partida
     *
     * @param tablero el panel que representa el tablero
     * @param filas el número de filas en el tablero
     * @param columnas el número de columnas en el tablero
     * @param invertir indica si se debe invertir el color de las casillas
     */
    private void inicializarAspa(JPanel tablero, int filas, int columnas, boolean invertir) {
        tablero.setLayout(new GridLayout(filas, columnas));
        tablero.setPreferredSize(tablero.getSize());
        tablero.setMinimumSize(tablero.getSize());
        tablero.setMaximumSize(tablero.getSize());

        for (int i = 1; i <= filas * columnas; i++) {
            JLabel label = new JLabel(""); // Crea un nuevo JLabel
            label.setBorder(new LineBorder(Color.BLACK, 1)); // Añadir borde negro
            label.setOpaque(true); // Hacer el fondo visible
            label.setBackground(Color.WHITE);
            if (modoDev) {
                label.setText(String.valueOf(j));
                j++;
            }
            

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

        for (int i = 0; i < totalCasillas; i++) {
            if (i < casillasLaterales) {
                tableroArriba.add(casillas.get(i));
            } else if (i < 2 * casillasLaterales) {
                tableroAbajo.add(casillas.get(i));
            } else if (i < 3 * casillasLaterales) {
                tableroDerecha.add(casillas.get(i));
            } else if (i < 4 * casillasLaterales) {
                tableroIzquierda.add(casillas.get(i));
            } else if (i >= totalCasillas - 4) {
                tableroCentro.add(casillas.get(i));
            }
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
    private void actualizarSiguienteJugador() {
        switch (jugador) {
            case 0 -> {
                this.inicializarImagen(this.lblIconJugadorActual, "/cat.png", 64, 60);
            }
            case 1 -> {
                this.inicializarImagen(this.lblIconJugadorActual, "/concha.png", 64, 60);
            }
            case 2 -> {
                this.inicializarImagen(this.lblIconJugadorActual, "/piramide.png", 64, 60);
            }
            case 3 -> {
                this.inicializarImagen(this.lblIconJugadorActual, "/mazorca.png", 64, 60);
            }
        }
    }
    /**
     * Actualiza el monto de apuestas en la interfaz grafica
     */
    private void actualizarApuestas() {
        for (int i = 0; i < jugadores; i++) {
            if (montoJugadores.get(i) < 0) {
                montoJugadores.set(i, 0);
            }
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
        revisarJugadorSale();
    }
    /**
     * Simula el lanzamiento de las cañas y muestra el resultado en el campo de
     * texto.
     */
    public void lanzarCañas() {
        if(juegoAcabo && !modoDev) {
            JOptionPane.showMessageDialog(null, "La partida termino", "Juego finalizado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (primerLanzamiento) {
            primerLanzamiento = false;
            resultadoCañas = 1;
        } else {
            resultadoCañas = utils.generarLanzamiento();
        }
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
        if(!modoDev){
          this.btnLanzarCañas.setEnabled(false);  
        }       
        iluminarFichas(true);
    }
    /**
     * Avanza al siguiente jugador en el turno. Ilumina el jugador del turno
     * actual e ilumina el botón de lanzar cañas.
     */
    public void siguienteJugador() {
        this.lblTurnosExtra.setText("Turnos Extra: "+turnosExtra);
        //Si no tiene turnos extra
        if (turnosExtra <= 0) {
            jugador = (jugador + 1) % jugadores;
        } else {
            turnosExtra--;
            this.btnLanzarCañas.setEnabled(true);
        }
        mostrarMensajesAlmacenados();
        subirCambios();
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
        if (fichasIluminables != null && fichasIluminablesPosicion != null && this.resultadoCañas!=0) {
            for (int i = 0; i < fichas; i++) {
                if (iluminar) {
                    //Si la ficha esta fuera del tablero, no se ilumina
                    if (fichasIluminablesPosicion.get(i) != -1) {
                        //Si la ficha fuera a terminar en una casilla ocupada, no se ilumina
                        if (casillas.get(getPosicionReal(fichasIluminablesPosicion.get(i))).getIcon() == null) {
                            puedeMover = true;
                            fichasIluminables.get(i).setBorder(BorderFactory.createLineBorder(Color.GREEN, 3)); 
                        } 
                        //A menos que haya sacado un 1 en las cañas, siempre y cuando la casilla de inicio este despejada
                    } else if (this.resultadoCañas == 1 && casillaInicialLibre) {
                        fichasIluminables.get(i).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                        puedeMover = true;
                    }
                } else {
                    fichasIluminables.get(i).setBorder(null); 
                }
            }
        }
        if (!puedeMover && iluminar) {
            montoJugadores.set(miJugador, montoJugadores.get(miJugador) -1);
            JOptionPane.showMessageDialog(null, "Pagas 1 apuesta", "No puedes moverte", JOptionPane.INFORMATION_MESSAGE);
            siguienteJugador();
        }
        if (iluminar) {
            puedeMover = false;
        }
    }
    /**
     * Simula el movimiento de una ficha.
     *
     * @param numFicha Identificador de la ficha a mover
     * @param numJugador Dueño de la ficha
     */
    public void moverFicha(int numFicha, int numJugador) {
        JLabel miFicha = getListaFichasMiJugador().get(numFicha);
        Border borde = miFicha.getBorder();
        if (borde instanceof LineBorder lineBorder) {
            Color colorBorde = lineBorder.getLineColor(); // Obtiene el color del borde

            if (colorBorde.equals(Color.GREEN)) {
                //Si la ficha tiene borde verde, significa que va a moverse por el tablero

                int posicionRelativa = getListaFichasPosicionMiJugador().get(numFicha);

                //Calcula la posicion anterior
                int posicionAnterior = casillasOrdenadas.indexOf(posicionRelativa);
                //Obtiene la casilla real donde va la ficha
                int casillaTermina = getPosicionReal(posicionRelativa);

                //Hacer un nuevo label en el tablero
                JLabel labelAux = new JLabel();
                labelAux.setBorder(new LineBorder(Color.BLACK, 1));
                labelAux.setOpaque(true);
                Color colorCasilla = casillas.get(casillaTermina).getBackground();
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
                    montoJugadores.set(miJugador, montoJugadores.get(miJugador) -2);
                    mensaje.add("Pagas 2 apuestas");
                    titulo.add("Casilla pagar apuesta");
                }

                //Casilla doble turno
                if (colorCasilla.equals(Color.BLUE)) {
                    turnosExtra = turnosExtra + 2;
                    mensaje.add("Obtienes 2 turnos extra");
                    titulo.add("Casilla doble turno");
                }

                //Vuelta al tablero
                int casillaVuelta = casillasOrdenadas.indexOf(getCasillaInicialMiJugador());
                boolean dioVuelta = false;
                if (miJugador == 0 && posicionAnterior > casillasOrdenadas.indexOf(casillaTermina)) {
                    dioVuelta = true;
                } else if (posicionAnterior < casillaVuelta && casillaVuelta < casillasOrdenadas.indexOf(casillaTermina)) {
                    dioVuelta = true;
                }
                if (dioVuelta) {
                    int jugadoresAct=0;
                    for (int i = 0; i < jugadores; i++) {
                        if(jugadoresActivos.get(i)){
                            jugadoresAct++;
                        }
                    }
                    montoJugadores.set(miJugador, montoJugadores.get(miJugador) + jugadoresAct - 1);
                    for (int i = 0; i < jugadores; i++) {
                        if (miJugador != i) {
                            montoJugadores.set(i, montoJugadores.get(i) - 1);
                        }
                    }
                    mensaje.add("Cobraste 1 apuesta a todos los demas jugadores");
                    titulo.add("Vuelta al tablero");
                }
            } else if (colorBorde.equals(Color.YELLOW)) {
                //Si la ficha tiene borde amarillo, significa que va a entrar al tablero
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
            }else{
                //Si la ficha no tiene borde, no puede moverse por que otra ficha le estorba
                JOptionPane.showMessageDialog(null, "La ficha no puede terminar en una casilla ocupada, elige otra ficha", "Casilla ocupada", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        iluminarFichas(false);
        actualizarTablero();
        siguienteJugador();
    }  
    /**
     * Muestra los resultados del movimiento de una ficha almacenados en mensaje
     * y titulo
     */
    private void mostrarMensajesAlmacenados() {
        for (int i = 0; i < mensaje.size(); i++) {
            JOptionPane.showMessageDialog(null, mensaje.get(i), titulo.get(i), JOptionPane.INFORMATION_MESSAGE);
        }
        mensaje.clear();
        titulo.clear();
    }
    /**
     * Si un jugador tiene 0 o menos de monto de apuesta, sale del juego
     */
    private void revisarJugadorSale() {
        for (int i = 0; i < montoJugadores.size(); i++) {
            if (montoJugadores.get(i) <= 0) {
                if (jugadoresActivos.get(i)) {
                    jugadoresActivos.set(i, false);
                    
                    switch(i){
                        case 0->{
                            for (int k = 0; k < fichasGatoPosicion.size(); k++) {
                                fichasGatoPosicion.set(k, -1);
                            }
                        }
                        case 1->{
                            for (int k = 0; k < fichasConchaPosicion.size(); k++) {
                               fichasConchaPosicion.set(k, -1);
                            }
                        }
                        case 2->{
                            for (int k = 0; k < fichasPiramidePosicion.size(); k++) {
                               fichasPiramidePosicion.set(k, -1);
                            }
                        }
                        case 3->{
                            for (int k = 0; k < fichasMazorcaPosicion.size(); k++) {
                               fichasMazorcaPosicion.set(k, -1);
                            }
                        }
                    }

                    if (i == miJugador) {
                        JOptionPane.showMessageDialog(null, "Te has quedado sin apuestas, no puedes jugar más", "Perdiste", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, getNombreJugador(i) + " Se ha quedado sin apuestas y sale de la partida", "Un jugador sale de la partida", JOptionPane.INFORMATION_MESSAGE);
                    }         
                    
                    podio.add(i);
                    System.out.println("Podio añadido: "+i);
                }
            }
        }
        revisarFinDelJuego();
    }
    /**
     * Revisa si solo queda un jugador activo, si no es asi, entonces
     * Salta a los jugadores que perdieron para que el siguiente jugador activo pueda jugar
     */
    private void revisarFinDelJuego() {
        boolean ganar = true;
        int siguienteJugador = -1;
        int jugadorEncontrado = -1;
        for (int i = 0; i < jugadores; i++) {      
            if (jugadoresActivos.get(jugador)) {
                if (jugadorEncontrado == -1) {
                    //Aqui entra la primera vez que encuentra al jugador
                    siguienteJugador = jugador;
                } else {
                    //Aqui entra la segunda vez que encuentra al jugador
                    ganar = false;
                }
                jugadorEncontrado = jugador;
            }
            jugador = (jugador + 1) % jugadores;
        }
        //Si solo se encontro un jugador disponible, entonces ese jugador gana
        if (ganar) {
            ganar(siguienteJugador);
        } else { //De otra forma entonces el turno pasa al siguiente jugador que puede jugar
            jugador = siguienteJugador;
            if (miJugador == jugador) {
                this.btnLanzarCañas.setEnabled(true);
            }
        }
    }
    /**
     * Termina el juego y muestra los resultados
     *
     * @param jugadorGanador Jugador que gana la partida
     */
    private void ganar(int jugadorGanador) {
        if (!juegoAcabo) {
            juegoAcabo = true;
            System.out.println("Ganador: " + jugadorGanador);
            podio.add(jugadorGanador);
            Collections.reverse(podio);
            parent.desconectar(this.codigoSala,miJugador);
            parent.PasarPantallaFinal(this, podio);
        }
    }
    /**
     * Devuelve el nombre de un jugador segun miJugador
     * @return 
     */
    private String getNombreJugador(int jugador){
        switch(jugador){
            case 0->{
                return "Jaguar";
            }
            case 1->{
                return "Conchas";
            }
            case 2->{
                return "Piramide";
            }
            case 3->{
                return "Maiz";
            }
        }
        return "Error";
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
     * Obtiene la lista de posiciones de fichas por el jugador dueño de la
     * pantalla
     *
     * @return Lista de Integers que reprsenta las posiciones de las fichas en
     * el tablero
     */
    private List<Integer> getListaFichasPosicionMiJugador() {
        List<Integer> listaCasillasPosicion = new ArrayList<>();
        switch (miJugador) {
            case 0 -> {
                listaCasillasPosicion = this.fichasGatoPosicion;
            }
            case 1 -> {
                listaCasillasPosicion = this.fichasConchaPosicion;
            }
            case 2 -> {
                listaCasillasPosicion = this.fichasPiramidePosicion;
            }
            case 3 -> {
                listaCasillasPosicion = this.fichasMazorcaPosicion;
            }
        }
        return listaCasillasPosicion;
    }
    /**
     * Obtiene la lista JLabels de fichas por el jugador dueño de la pantalla
     *
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
     * Transforma la posicion relativa del tablero a su posicion de orden de
     * progreso
     *
     * @param posicionRelativa numero de Casilla ordenada segun la vista del
     * tablero
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
     * Manda el valor del tablero, el monto de los jugadores y el
     * siguientejugador al control del juego
     *
     * @return Verdadero si se actualizo con exito
     */
    private void subirCambios() {
        if(!this.juegoAcabo){
            revisarJugadorSale();
            revisarFinDelJuego();
        }
        this.actualizarSiguienteJugador(); //Actualiza la vista para el siguiente jugador 

        CuerpoMensaje cuerpo = new CuerpoMensaje();
        cuerpo.setCodigoSala(this.codigoSala);
        cuerpo.setFichasConchaPosicion(fichasConchaPosicion);
        cuerpo.setFichasGatoPosicion(fichasGatoPosicion);
        cuerpo.setFichasMazorcaPosicion(fichasMazorcaPosicion);
        cuerpo.setFichasPiramidePosicion(fichasPiramidePosicion);
        cuerpo.setMontoJugadores(montoJugadores);
        cuerpo.setJugador(jugador);

        TipoMensaje tipo = TipoMensaje.PASAR_CAMBIOS;

        Mensaje mensajeAServidor = new Mensaje.Builder()
                .body(cuerpo)
                .messageType(tipo)
                .build();
        parent.enviarMensaje(mensajeAServidor);
    }

    /**
     * Recibe los cambios del control y los coloca en la pantalla
     *
     * @param montoJugadores Monto de apuestas de los jugadores
     * @param siguienteJugador Siguiente Jugador
     * @param fichasGatoPosicion Posicion de fichas gato
     * @param fichasConchaPosicion Posicion de fichas concha
     * @param fichasPiramidePosicion Posicion de fichas piramide
     * @param fichasMazorcaPosicion Posicion de fichas mazorca
     * @return Verdadero si no hubo ningun problema
     */
    public boolean recibirCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {

        this.montoJugadores = montoJugadores;
        this.jugador = siguienteJugador;
        
        this.fichasGatoPosicion = fichasGatoPosicion;
        this.fichasConchaPosicion = fichasConchaPosicion;
        this.fichasPiramidePosicion = fichasPiramidePosicion;
        this.fichasMazorcaPosicion = fichasMazorcaPosicion;
        
        this.actualizarApuestas(); //Coloca el monto de apuestas correspondiente
        this.actualizarCasillas(); //Actualiza el valor de la lista de casillas y la lista de fichas de label
        this.actualizarTablero(); //Actualiza el tablero con la lista de casillas
        this.actualizarSiguienteJugador(); //Actualiza la vista para el siguiente jugador 

        //Si es mi turno, habilita el lanzar cañas
        if (jugadoresActivos.get(miJugador)) {
            if (jugador == miJugador) {
                this.btnLanzarCañas.setEnabled(true);
            }
        }

        return true;
    }
    /**
     * Actualiza el valor de la lista de casillas con las posiciones de fichas
     */
    public void actualizarCasillas() {
        //Dejar las fichas de casillas sin iconos ni listeners 
        for (JLabel casilla : casillas) {
            casilla.setIcon(null);

            MouseListener[] listeners = casilla.getMouseListeners();
            for (MouseListener listener : listeners) {
                casilla.removeMouseListener(listener);
            }
        }
        // Actualizar todas las fichas en casillas
        actualizarFicha(fichasGatoPosicion, fichasGato, "/cat.png", 0);
        actualizarFicha(fichasConchaPosicion, fichasConcha, "/concha.png", 1);
        actualizarFicha(fichasPiramidePosicion, fichasPiramide, "/piramide.png", 2);
        actualizarFicha(fichasMazorcaPosicion, fichasMazorca, "/mazorca.png", 3);
    }
    /**
     * Actualiza la vista de todas las casillas con las posiciones
     * @param posiciones Posiciiones de las fichas en el tablero
     * @param fichas Lista de fichas del jugador
     * @param iconPath Path a la imagen del icono de jugador
     * @param numeroJugador Numero del jugador
     */
    private void actualizarFicha(List<Integer> posiciones, List<JLabel> fichas, String iconPath, int numeroJugador) {
        for (int i = 0; i < posiciones.size(); i++) {
            int posicion = posiciones.get(i);
            if (posicion != -1) {
                JLabel labelAux = new JLabel();
                labelAux.setBorder(new LineBorder(Color.BLACK, 1));
                labelAux.setOpaque(true);
                labelAux.setBackground(casillas.get(posicion).getBackground());
                labelAux.setSize(fichas.get(i).getSize());

                final int index = i;
                labelAux.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        clickearFicha(index, numeroJugador);
                    }
                });

                inicializarImagen(labelAux, iconPath, 15, 15);
                casillas.set(posicion, labelAux);

                JLabel fichaTablero = fichas.get(i);
                fichas.set(i, labelAux);
                if (!(fichaTablero.getBorder() instanceof LineBorder)) {
                    fichaTablero.setVisible(false);
                }
            }
        }
    }
    /**
     * Recibe la notificacion de que un jugador sale de la partida por medios
     * distintos a perder en el juego
     *
     * @param jugador Jugador que sale de la partida
     */
    public void recibirJugadorSale(int jugador) {
        if(this.juegoAcabo){
            return;
        }
        if (jugador != miJugador) {
            montoJugadores.set(jugador, 0);
            JOptionPane.showMessageDialog(null, getNombreJugador(jugador) + " ha abandona la partida, se removeran sus apuestas y fichas", "Un jugador abandono la partida", JOptionPane.INFORMATION_MESSAGE);
            revisarJugadorSale();
        }
    }
    /**
     * Cierra la aplicación después de confirmar la salida con el usuario.
     */
    public void salir() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres volver?", "Salir", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
                parent.desconectar(this.codigoSala,miJugador);
                parent.PasarPantallaInicio(this);
        }
    }
    /**
     * Cierra la ventana y comunica la desconexion del jugador
     */
    public void cerrar() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            parent.desconectar(this.codigoSala,miJugador);
            parent.CerrarPrograma();
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
        lblArrowUp = new javax.swing.JLabel();
        lblArrowUp2 = new javax.swing.JLabel();
        lblArrowD1 = new javax.swing.JLabel();
        lblArrowD2 = new javax.swing.JLabel();
        lblArrowL1 = new javax.swing.JLabel();
        lblArrowR1 = new javax.swing.JLabel();
        lblArrowR2 = new javax.swing.JLabel();
        lblArrowL2 = new javax.swing.JLabel();
        lblMiJugador = new javax.swing.JLabel();
        lblTuJugador = new javax.swing.JLabel();
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
            .addGap(0, 219, Short.MAX_VALUE)
        );
        tableroIzquierdaLayout.setVerticalGroup(
            tableroIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        lblTurnoDe.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblTurnoDe.setText("Turno de:");

        lblIconJugadorActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInicioTigre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInicioPiramide.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInicioMazorca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInicioConcha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTurnosExtra.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblTurnosExtra.setText("Turnos Extra: 0");

        lblArrowUp.setBackground(new java.awt.Color(0, 0, 0));
        lblArrowUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowUBlack.png"))); // NOI18N

        lblArrowUp2.setBackground(new java.awt.Color(0, 0, 0));
        lblArrowUp2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowUBlack.png"))); // NOI18N

        lblArrowD1.setBackground(new java.awt.Color(0, 0, 0));
        lblArrowD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowDBlack.png"))); // NOI18N

        lblArrowD2.setBackground(new java.awt.Color(0, 0, 0));
        lblArrowD2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowDBlack.png"))); // NOI18N

        lblArrowL1.setBackground(new java.awt.Color(0, 0, 0));
        lblArrowL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowLBlack.png"))); // NOI18N

        lblArrowR1.setBackground(new java.awt.Color(0, 0, 0));
        lblArrowR1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowRBlack.png"))); // NOI18N

        lblArrowR2.setBackground(new java.awt.Color(0, 0, 0));
        lblArrowR2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowRBlack.png"))); // NOI18N

        lblArrowL2.setBackground(new java.awt.Color(0, 0, 0));
        lblArrowL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowLBlack.png"))); // NOI18N

        lblMiJugador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTuJugador.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblTuJugador.setText("Tu jugador:");

        javax.swing.GroupLayout pnlTableroLayout = new javax.swing.GroupLayout(pnlTablero);
        pnlTablero.setLayout(pnlTableroLayout);
        pnlTableroLayout.setHorizontalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableroIzquierda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTurnosExtra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTableroLayout.createSequentialGroup()
                                .addComponent(lblTurnoDe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblIconJugadorActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(35, 35, 35)
                        .addComponent(lblArrowD2)
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableroLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableroLayout.createSequentialGroup()
                                .addComponent(lblArrowR1)
                                .addGap(45, 45, 45)
                                .addComponent(lblInicioConcha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableroLayout.createSequentialGroup()
                                .addComponent(lblArrowL1)
                                .addGap(46, 46, 46)
                                .addComponent(lblInicioMazorca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblArrowD1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableroArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tableroCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableroLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(lblTuJugador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMiJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(19, 19, 19))
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tableroDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlTableroLayout.createSequentialGroup()
                                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblInicioTigre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblArrowUp))
                                        .addGap(33, 33, 33)
                                        .addComponent(lblArrowL2)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addComponent(tableroAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addComponent(lblInicioPiramide, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(lblArrowR2))
                            .addComponent(lblArrowUp2))
                        .addContainerGap())))
        );
        pnlTableroLayout.setVerticalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableroLayout.createSequentialGroup()
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblIconJugadorActual, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(lblTurnoDe)))
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTurnosExtra))
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblArrowD2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInicioMazorca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArrowL1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTableroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tableroArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lblMiJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(lblTuJugador)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblArrowL2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlTableroLayout.createSequentialGroup()
                                .addComponent(lblArrowUp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblInicioTigre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tableroDerecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableroIzquierda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableroCentro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInicioConcha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArrowR1))
                        .addGap(31, 31, 31)
                        .addComponent(lblArrowD1))
                    .addComponent(tableroAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTableroLayout.createSequentialGroup()
                        .addGroup(pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInicioPiramide, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArrowR2))
                        .addGap(27, 27, 27)
                        .addComponent(lblArrowUp2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addContainerGap(17, Short.MAX_VALUE)
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
                .addContainerGap(24, Short.MAX_VALUE))
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
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(lblCaña1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCaña2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCaña3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCaña4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCaña5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
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
    private javax.swing.JLabel lblArrowD1;
    private javax.swing.JLabel lblArrowD2;
    private javax.swing.JLabel lblArrowL1;
    private javax.swing.JLabel lblArrowL2;
    private javax.swing.JLabel lblArrowR1;
    private javax.swing.JLabel lblArrowR2;
    private javax.swing.JLabel lblArrowUp;
    private javax.swing.JLabel lblArrowUp2;
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
    private javax.swing.JLabel lblMiJugador;
    private javax.swing.JLabel lblPiramideApuesta;
    private javax.swing.JLabel lblTuJugador;
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
