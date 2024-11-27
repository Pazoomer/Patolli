package presentation;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * La clase FrameOpciones permite al usuario configurar las opciones antes de iniciar una partida.
 * Los usuarios pueden elegir el tamaño del tablero, el monto de las apuestas y el número de fichas por jugador.
 * Además, ofrece opciones para jugar, ver instrucciones o regresar a la pantalla anterior.
 * 
 * @autor t1pas
 */
public class DialogOpciones extends JDialog {

    private final FrameInicio parent;
    public int tamaño; // Almacena el tamaño del tablero
    public int monto;  // Almacena el monto de las apuestas
    public int fichas; // Almacena la cantidad de fichas por jugador
    //Fondo de la pantalla
    private final String RUTAFONDO="/fondoMoneda.jpg";
    private final FondoPanel fondo=new FondoPanel(RUTAFONDO);

    /**
     * Constructor de FrameOpciones.
     * Inicializa la ventana y asigna valores predeterminados al tamaño del tablero, monto de las apuestas y fichas.
     * @param parent
     */
    public DialogOpciones(FrameInicio parent) {
        super(parent, true); // Inicializa el JDialog con modal
        this.parent=parent;
        this.setResizable(false); // Desactiva la opción de cambiar el tamaño de la ventana
        this.setResizable(false); // Desactiva la opción de cambiar el tamaño de la ventana
        if(fondo!=null){
           this.setContentPane(fondo); 
        }else{
            System.out.println("No cargo la imagen de fondo");
        }
        initComponents(); // Inicializa los componentes de la interfaz
        // Agregar un WindowListener para manejar el evento de cerrar
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Cerrar(); 
            }
        });
        SetTamañoTablero(8); // Establece el tamaño del tablero a 8 por defecto
        SetMontoApuestas(10); // Establece el monto de apuestas a 10 por defecto
        SetFichasJugador(2); // Establece el número de fichas a 2 por defecto
    }

    /**
     * Navega a la pantalla de la sala de juego. Crea una instancia de
     * FrameSala, pasándole los valores seleccionados de tamaño, monto y fichas.
     */
    public void Jugar() {
        parent.PasarPantallaSala(this, tamaño, monto, fichas, null);
    }

    public void Cerrar() {
        parent.CerrarPrograma();
    }

    /**
     * Vuelve a la pantalla anterior de unirse o crear partida. Crea una
     * instancia de FrameUnirseCrear y cambia a esa pantalla.
     */
    public void Volver() {
        parent.PasarPantallaUnirseCrear(this);
    }

    /**
     * Establece el tamaño del tablero basado en la opción seleccionada. Cambia
     * el aspecto visual de la etiqueta que representa el tamaño seleccionado.
     * 
     * @param tamaño El tamaño del tablero (8, 10, o 12).
     */
    public void SetTamañoTablero(int tamaño) {
        this.tamaño = tamaño; // Asigna el tamaño del tablero

        JLabel label = null;
        // Selecciona la etiqueta correspondiente al tamaño del tablero
        switch (tamaño) {
            case 8 -> label = this.lblTamaño8;
            case 10 -> label = this.lblTamaño10;
            case 12 -> label = this.lblTamaño12;
        }

        // Configura el aspecto visual de todas las etiquetas de tamaño
        this.lblTamaño8.setOpaque(true);
        this.lblTamaño8.setBackground(new Color(192, 160, 123));
        this.lblTamaño8.setForeground(Color.WHITE);
        this.lblTamaño8.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        this.lblTamaño10.setOpaque(true);
        this.lblTamaño10.setBackground(new Color(192, 160, 123));
        this.lblTamaño10.setForeground(Color.WHITE);
        this.lblTamaño10.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        this.lblTamaño12.setOpaque(true);
        this.lblTamaño12.setBackground(new Color(192, 160, 123));
        this.lblTamaño12.setForeground(Color.WHITE);
        this.lblTamaño12.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        // Resalta la etiqueta seleccionada
        if (label != null) {
            label.setBackground(Color.BLACK);  // Fondo negro
            label.setForeground(Color.WHITE); // Texto blanco
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }
    }

    /**
     * Establece el monto de las apuestas basado en la opción seleccionada.
     * Cambia el aspecto visual de la etiqueta que representa el monto seleccionado.
     * 
     * @param monto El monto de las apuestas (10, 20, o 40).
     */
    public void SetMontoApuestas(int monto) {
        this.monto = monto; // Asigna el monto de las apuestas

        JLabel label = null;
        // Selecciona la etiqueta correspondiente al monto de apuestas
        switch (monto) {
            case 10 -> label = this.lblMonto10;
            case 20 -> label = this.lblMonto20;
            case 40 -> label = this.lblMonto40;
        }

        // Configura el aspecto visual de todas las etiquetas de monto
        this.lblMonto10.setOpaque(true);
        this.lblMonto10.setBackground(new Color(192, 160, 123));
        this.lblMonto10.setForeground(Color.WHITE);
        this.lblMonto10.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        this.lblMonto20.setOpaque(true);
        this.lblMonto20.setBackground(new Color(192, 160, 123));
        this.lblMonto20.setForeground(Color.WHITE);
        this.lblMonto20.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        this.lblMonto40.setOpaque(true);
        this.lblMonto40.setBackground(new Color(192, 160, 123));
        this.lblMonto40.setForeground(Color.WHITE);
        this.lblMonto40.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        // Resalta la etiqueta seleccionada
        if (label != null) {
            label.setBackground(Color.BLACK);  // Fondo negro
            label.setForeground(Color.WHITE); // Texto blanco
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }
    }

    /**
     * Establece el número de fichas de jugador basado en la opción seleccionada.
     * Cambia el aspecto visual de la etiqueta que representa la cantidad de fichas seleccionada.
     * 
     * @param fichas El número de fichas por jugador (2, 4, o 6).
     */
    public void SetFichasJugador(int fichas) {
        this.fichas = fichas; // Asigna el número de fichas por jugador

        JLabel label = null;
        // Selecciona la etiqueta correspondiente al número de fichas
        switch(fichas){
            case 2 -> label = this.lblFichas2;
            case 4 -> label = this.lblFichas4;
            case 6 -> label = this.lblFichas6;
        }

        // Configura el aspecto visual de todas las etiquetas de fichas
        this.lblFichas2.setOpaque(true);
        this.lblFichas2.setBackground(new Color(192, 160, 123));
        this.lblFichas2.setForeground(Color.WHITE);
        this.lblFichas2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        this.lblFichas4.setOpaque(true);
        this.lblFichas4.setBackground(new Color(192, 160, 123));
        this.lblFichas4.setForeground(Color.WHITE);
        this.lblFichas4.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        this.lblFichas6.setOpaque(true);
        this.lblFichas6.setBackground(new Color(192, 160, 123));
        this.lblFichas6.setForeground(Color.WHITE);
        this.lblFichas6.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        // Resalta la etiqueta seleccionada
        if (label != null) {
            label.setBackground(Color.BLACK);  // Fondo negro
            label.setForeground(Color.WHITE); // Texto blanco
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }
    }



    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMonto10 = new javax.swing.JLabel();
        lblMonto20 = new javax.swing.JLabel();
        lblTamaño10 = new javax.swing.JLabel();
        lblTamaño12 = new javax.swing.JLabel();
        lblFichas2 = new javax.swing.JLabel();
        lblTamaño8 = new javax.swing.JLabel();
        lblFichas4 = new javax.swing.JLabel();
        lblFichas6 = new javax.swing.JLabel();
        lblMonto40 = new javax.swing.JLabel();
        pnlVolver = new javax.swing.JPanel();
        lblVolver = new javax.swing.JLabel();
        pnlOpciones = new javax.swing.JPanel();
        lblOpciones = new javax.swing.JLabel();
        pnlJugar = new javax.swing.JPanel();
        lblJugar = new javax.swing.JLabel();
        pnlFichas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        pnlMonto = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        pnlTamaño = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        lblMonto10.setBackground(new java.awt.Color(192, 160, 123));
        lblMonto10.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblMonto10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMonto10.setText("10");
        lblMonto10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        lblMonto10.setOpaque(true);
        lblMonto10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMonto10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMonto10MousePressed(evt);
            }
        });

        lblMonto20.setBackground(new java.awt.Color(192, 160, 123));
        lblMonto20.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        lblMonto20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMonto20.setText("20");
        lblMonto20.setOpaque(true);
        lblMonto20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMonto20MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMonto20MousePressed(evt);
            }
        });

        lblTamaño10.setBackground(new java.awt.Color(192, 160, 123));
        lblTamaño10.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        lblTamaño10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTamaño10.setText("10");
        lblTamaño10.setOpaque(true);
        lblTamaño10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTamaño10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTamaño10MousePressed(evt);
            }
        });

        lblTamaño12.setBackground(new java.awt.Color(192, 160, 123));
        lblTamaño12.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        lblTamaño12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTamaño12.setText("12");
        lblTamaño12.setOpaque(true);
        lblTamaño12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTamaño12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTamaño12MousePressed(evt);
            }
        });

        lblFichas2.setBackground(new java.awt.Color(192, 160, 123));
        lblFichas2.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblFichas2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFichas2.setText("2");
        lblFichas2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        lblFichas2.setOpaque(true);
        lblFichas2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFichas2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblFichas2MousePressed(evt);
            }
        });

        lblTamaño8.setBackground(new java.awt.Color(192, 160, 123));
        lblTamaño8.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblTamaño8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTamaño8.setText("8");
        lblTamaño8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        lblTamaño8.setOpaque(true);
        lblTamaño8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTamaño8MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTamaño8MousePressed(evt);
            }
        });

        lblFichas4.setBackground(new java.awt.Color(192, 160, 123));
        lblFichas4.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        lblFichas4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFichas4.setText("4");
        lblFichas4.setOpaque(true);
        lblFichas4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFichas4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblFichas4MousePressed(evt);
            }
        });

        lblFichas6.setBackground(new java.awt.Color(192, 160, 123));
        lblFichas6.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        lblFichas6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFichas6.setText("6");
        lblFichas6.setOpaque(true);
        lblFichas6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFichas6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblFichas6MousePressed(evt);
            }
        });

        lblMonto40.setBackground(new java.awt.Color(192, 160, 123));
        lblMonto40.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        lblMonto40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMonto40.setText("40");
        lblMonto40.setOpaque(true);
        lblMonto40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMonto40MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMonto40MousePressed(evt);
            }
        });

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
                .addComponent(lblVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlVolverLayout.setVerticalGroup(
            pnlVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVolverLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(lblVolver)
                .addGap(15, 15, 15))
        );

        pnlOpciones.setBackground(new java.awt.Color(192, 160, 123));
        pnlOpciones.setMaximumSize(new java.awt.Dimension(300, 75));
        pnlOpciones.setMinimumSize(new java.awt.Dimension(300, 75));
        pnlOpciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlOpcionesMouseClicked(evt);
            }
        });

        lblOpciones.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblOpciones.setText("OPCIONES");
        lblOpciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOpcionesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlOpcionesLayout = new javax.swing.GroupLayout(pnlOpciones);
        pnlOpciones.setLayout(pnlOpcionesLayout);
        pnlOpcionesLayout.setHorizontalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblOpciones)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOpcionesLayout.setVerticalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(lblOpciones)
                .addGap(14, 14, 14))
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
                .addContainerGap()
                .addComponent(lblJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlJugarLayout.setVerticalGroup(
            pnlJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJugarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJugar, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
        );

        pnlFichas.setBackground(new java.awt.Color(223, 207, 188));

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea3.setEditable(false);
        jTextArea3.setBackground(new java.awt.Color(223, 207, 188));
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setText("Fichas por\n Jugador");
        jTextArea3.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout pnlFichasLayout = new javax.swing.GroupLayout(pnlFichas);
        pnlFichas.setLayout(pnlFichasLayout);
        pnlFichasLayout.setHorizontalGroup(
            pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFichasLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        pnlFichasLayout.setVerticalGroup(
            pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFichasLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pnlMonto.setBackground(new java.awt.Color(223, 207, 188));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(223, 207, 188));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("Monto de\nApuestas");
        jTextArea2.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout pnlMontoLayout = new javax.swing.GroupLayout(pnlMonto);
        pnlMonto.setLayout(pnlMontoLayout);
        pnlMontoLayout.setHorizontalGroup(
            pnlMontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMontoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        pnlMontoLayout.setVerticalGroup(
            pnlMontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMontoLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pnlTamaño.setBackground(new java.awt.Color(223, 207, 188));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(223, 207, 188));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Tamaño del\n  Tablero");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout pnlTamañoLayout = new javax.swing.GroupLayout(pnlTamaño);
        pnlTamaño.setLayout(pnlTamañoLayout);
        pnlTamañoLayout.setHorizontalGroup(
            pnlTamañoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTamañoLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlTamañoLayout.setVerticalGroup(
            pnlTamañoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTamañoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblMonto10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMonto20, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMonto40, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTamaño8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTamaño10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTamaño12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(pnlVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(pnlJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lblFichas2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblFichas4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(122, 122, 122)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFichas6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlFichas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))))
            .addGroup(layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlFichas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblFichas2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFichas4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFichas6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTamaño10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTamaño12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTamaño8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(125, 125, 125)
                        .addComponent(pnlJugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMonto10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMonto20, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMonto40, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJugarMouseClicked

    }//GEN-LAST:event_lblJugarMouseClicked

    private void pnlJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlJugarMouseClicked
        
    }//GEN-LAST:event_pnlJugarMouseClicked

    private void lblTamaño8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño8MouseClicked
        
    }//GEN-LAST:event_lblTamaño8MouseClicked

    private void lblTamaño10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño10MouseClicked
        
    }//GEN-LAST:event_lblTamaño10MouseClicked

    private void lblTamaño12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño12MouseClicked
        
    }//GEN-LAST:event_lblTamaño12MouseClicked

    private void lblMonto10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto10MouseClicked
        
    }//GEN-LAST:event_lblMonto10MouseClicked

    private void lblMonto20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto20MouseClicked
        
    }//GEN-LAST:event_lblMonto20MouseClicked

    private void lblMonto40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto40MouseClicked
        
    }//GEN-LAST:event_lblMonto40MouseClicked

    private void lblFichas2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas2MouseClicked
        
    }//GEN-LAST:event_lblFichas2MouseClicked

    private void lblFichas4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas4MouseClicked
        
    }//GEN-LAST:event_lblFichas4MouseClicked

    private void lblFichas6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas6MouseClicked
        
    }//GEN-LAST:event_lblFichas6MouseClicked

    private void lblOpcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpcionesMouseClicked
       
    }//GEN-LAST:event_lblOpcionesMouseClicked
    
    private void pnlOpcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOpcionesMouseClicked
        
    }//GEN-LAST:event_pnlOpcionesMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void lblVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMouseClicked
        
    }//GEN-LAST:event_lblVolverMouseClicked

    private void pnlVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlVolverMouseClicked

    }//GEN-LAST:event_pnlVolverMouseClicked

    private void lblVolverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMousePressed
        Volver();
    }//GEN-LAST:event_lblVolverMousePressed

    private void lblJugarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJugarMousePressed
        Jugar();
    }//GEN-LAST:event_lblJugarMousePressed

    private void lblTamaño10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño10MousePressed
        SetTamañoTablero(10);
    }//GEN-LAST:event_lblTamaño10MousePressed

    private void lblTamaño8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño8MousePressed
        SetTamañoTablero(8);
    }//GEN-LAST:event_lblTamaño8MousePressed

    private void lblTamaño12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño12MousePressed
        SetTamañoTablero(12);
    }//GEN-LAST:event_lblTamaño12MousePressed

    private void lblMonto10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto10MousePressed
        SetMontoApuestas(10);
    }//GEN-LAST:event_lblMonto10MousePressed

    private void lblMonto20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto20MousePressed
        SetMontoApuestas(20);
    }//GEN-LAST:event_lblMonto20MousePressed

    private void lblMonto40MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto40MousePressed
        SetMontoApuestas(40);
    }//GEN-LAST:event_lblMonto40MousePressed

    private void lblFichas2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas2MousePressed
        SetFichasJugador(2);
    }//GEN-LAST:event_lblFichas2MousePressed

    private void lblFichas4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas4MousePressed
        SetFichasJugador(4);
    }//GEN-LAST:event_lblFichas4MousePressed

    private void lblFichas6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas6MousePressed
        SetFichasJugador(6);
    }//GEN-LAST:event_lblFichas6MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JLabel lblFichas2;
    private javax.swing.JLabel lblFichas4;
    private javax.swing.JLabel lblFichas6;
    private javax.swing.JLabel lblJugar;
    private javax.swing.JLabel lblMonto10;
    private javax.swing.JLabel lblMonto20;
    private javax.swing.JLabel lblMonto40;
    private javax.swing.JLabel lblOpciones;
    private javax.swing.JLabel lblTamaño10;
    private javax.swing.JLabel lblTamaño12;
    private javax.swing.JLabel lblTamaño8;
    private javax.swing.JLabel lblVolver;
    private javax.swing.JPanel pnlFichas;
    private javax.swing.JPanel pnlJugar;
    private javax.swing.JPanel pnlMonto;
    private javax.swing.JPanel pnlOpciones;
    private javax.swing.JPanel pnlTamaño;
    private javax.swing.JPanel pnlVolver;
    // End of variables declaration//GEN-END:variables
}
