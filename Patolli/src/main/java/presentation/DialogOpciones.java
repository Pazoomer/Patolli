package presentation;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * La clase FrameOpciones permite al usuario configurar las opciones antes de iniciar una partida.
 * Los usuarios pueden elegir el tamaño del tablero, el monto de las apuestas y el número de fichas por jugador.
 * Además, ofrece opciones para jugar, ver instrucciones o regresar a la pantalla anterior.
 * 
 * @autor t1pas
 */
public class DialogOpciones extends JDialog {

    private FrameInicio parent;
    public int tamaño; // Almacena el tamaño del tablero
    public int monto;  // Almacena el monto de las apuestas
    public int fichas; // Almacena la cantidad de fichas por jugador

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
        initComponents(); // Inicializa los componentes de la interfaz
        // Agregar un WindowListener para manejar el evento de cerrar
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Cerrar(); // Llama a tu método Cerrar() cuando se intente cerrar la ventana
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        lblTamaño8 = new javax.swing.JLabel();
        lblTamaño12 = new javax.swing.JLabel();
        lblTamaño10 = new javax.swing.JLabel();
        lblMonto10 = new javax.swing.JLabel();
        lblMonto20 = new javax.swing.JLabel();
        lblMonto40 = new javax.swing.JLabel();
        lblFichas2 = new javax.swing.JLabel();
        lblFichas4 = new javax.swing.JLabel();
        lblFichas6 = new javax.swing.JLabel();
        pnlJugar = new javax.swing.JPanel();
        lblJugar = new javax.swing.JLabel();
        pnlOpciones = new javax.swing.JPanel();
        lblOpciones = new javax.swing.JLabel();
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

        jPanel3.setBackground(new java.awt.Color(223, 207, 188));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(223, 207, 188));

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel5.setBackground(new java.awt.Color(223, 207, 188));

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

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
        });

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
                .addGap(40, 40, 40)
                .addComponent(lblOpciones)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOpcionesLayout.setVerticalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblOpciones)
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
                .addComponent(lblVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );
        pnlVolverLayout.setVerticalGroup(
            pnlVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVolverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVolver, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTamaño8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTamaño10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTamaño12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblMonto10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMonto20, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMonto40, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnlJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblFichas2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFichas4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFichas6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(pnlVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(555, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTamaño8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTamaño12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTamaño10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonto10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonto20, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonto40, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFichas4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFichas2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFichas6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(pnlJugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(474, 474, 474)
                    .addComponent(pnlVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(51, 51, 51)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Método llamado cuando se hace clic en la etiqueta 'Jugar'.
     * Inicia el juego pasando a la pantalla correspondiente.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJugarMouseClicked
        Jugar();
    }//GEN-LAST:event_lblJugarMouseClicked
    /**
     * Método llamado cuando se hace clic en el panel 'Jugar'.
     * Inicia el juego pasando a la pantalla correspondiente.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void pnlJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlJugarMouseClicked
        
    }//GEN-LAST:event_pnlJugarMouseClicked
    /**
     * Método llamado cuando se hace clic en la etiqueta para seleccionar un
     * tablero de tamaño 8.
     * Actualiza el tamaño del tablero a 8.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblTamaño8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño8MouseClicked
        SetTamañoTablero(8);
    }//GEN-LAST:event_lblTamaño8MouseClicked
    /**
     * Método llamado cuando se hace clic en la etiqueta para seleccionar un
     * tablero de tamaño 10.
     * Actualiza el tamaño del tablero a 10.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblTamaño10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño10MouseClicked
        SetTamañoTablero(10);
    }//GEN-LAST:event_lblTamaño10MouseClicked
    /**
     * Método llamado cuando se hace clic en la etiqueta para seleccionar un
     * tablero de tamaño 12.
     * Actualiza el tamaño del tablero a 12.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblTamaño12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño12MouseClicked
        SetTamañoTablero(12);
    }//GEN-LAST:event_lblTamaño12MouseClicked
    /**
     * Método llamado cuando se hace clic en la etiqueta para seleccionar un
     * monto de apuesta de 10.
     * Actualiza el monto de apuestas a 10.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblMonto10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto10MouseClicked
        SetMontoApuestas(10);
    }//GEN-LAST:event_lblMonto10MouseClicked
    /**
     * Método llamado cuando se hace clic en la etiqueta para seleccionar un
     * monto de apuesta de 20.
     * Actualiza el monto de apuestas a 20.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblMonto20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto20MouseClicked
        SetMontoApuestas(20);
    }//GEN-LAST:event_lblMonto20MouseClicked
    /**
     * Método llamado cuando se hace clic en la etiqueta para seleccionar un
     * monto de apuesta de 40.
     * Actualiza el monto de apuestas a 40.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblMonto40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto40MouseClicked
        SetMontoApuestas(40);
    }//GEN-LAST:event_lblMonto40MouseClicked
    /**
     * Método llamado cuando se hace clic en la etiqueta para seleccionar 2 fichas.
     * Actualiza el número de fichas del jugador a 2.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblFichas2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas2MouseClicked
        SetFichasJugador(2);
    }//GEN-LAST:event_lblFichas2MouseClicked
    /**
     * Método llamado cuando se hace clic en la etiqueta para seleccionar 4 fichas.
     * Actualiza el número de fichas del jugador a 4.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblFichas4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas4MouseClicked
        SetFichasJugador(4);
    }//GEN-LAST:event_lblFichas4MouseClicked
    /**
     * Método llamado cuando se hace clic en la etiqueta para seleccionar 6 fichas.
     * Actualiza el número de fichas del jugador a 6.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblFichas6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas6MouseClicked
        SetFichasJugador(6);
    }//GEN-LAST:event_lblFichas6MouseClicked

   /**
     * Método llamado cuando se hace clic en la etiqueta 'Volver'.
     * Regresa a la pantalla anterior.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblOpcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpcionesMouseClicked
       
    }//GEN-LAST:event_lblOpcionesMouseClicked
    
    private void pnlOpcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOpcionesMouseClicked
        
    }//GEN-LAST:event_pnlOpcionesMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed
    /**
     * Método llamado cuando se hace clic en el panel 'Volver'. Regresa a la
     * pantalla anterior.
     *
     * @param evt Evento del mouse que se genera al hacer clic.
     */
    private void lblVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMouseClicked
        Volver();
    }//GEN-LAST:event_lblVolverMouseClicked

    private void pnlVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlVolverMouseClicked

    }//GEN-LAST:event_pnlVolverMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
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
    private javax.swing.JPanel pnlJugar;
    private javax.swing.JPanel pnlOpciones;
    private javax.swing.JPanel pnlVolver;
    // End of variables declaration//GEN-END:variables
}
