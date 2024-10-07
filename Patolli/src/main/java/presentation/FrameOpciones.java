package presentation;

import DTO.OpcionesTablero;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FrameOpciones extends javax.swing.JFrame {

    public OpcionesTablero opcionesTablero=new OpcionesTablero();
    
    public FrameOpciones() {
        initComponents();
        SetTamañoTablero(8);
        SetMontoApuestas(10);
        SetFichasJugador(2);
    }

    public void Jugar() {
        //Pasa a la pantalla sala
    }

    public void ComoJugar() {
        //Pasa a la pantalla Como Jugar
    }

    public void Unirse() {
        //Pasa a la pantalla sala con el codigo 
        if (this.txtCodigo.getText() != null && !"AAAA".equals(this.txtCodigo.getText())) {
            JOptionPane.showMessageDialog(null, "No se encontro la partida", "Partida no encontrada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Uniendose a la partida", "Partida encontrada", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void SetTamañoTablero(int tamaño) {
        //Pone el tamaño del tablero
        opcionesTablero.tamaño = tamaño;

        JLabel label = null;
        switch (tamaño) {
            case 8 -> {
                label = this.lblTamaño8;
            }
            case 10 -> {
                label = this.lblTamaño10;
            }
            case 12 -> {
                label = this.lblTamaño12;
            }

        }
        this.lblTamaño8.setOpaque(true);  // Permitir cambiar el fondo
        this.lblTamaño8.setBackground(new Color(192, 160, 123));  // Fondo azul para mostrar selección
        this.lblTamaño8.setForeground(Color.WHITE); // Texto blanco
        this.lblTamaño8.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        this.lblTamaño10.setOpaque(true);  // Permitir cambiar el fondo
        this.lblTamaño10.setBackground(new Color(192, 160, 123));  // Fondo azul para mostrar selección
        this.lblTamaño10.setForeground(Color.WHITE); // Texto blanco
        this.lblTamaño10.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        this.lblTamaño12.setOpaque(true);  // Permitir cambiar el fondo
        this.lblTamaño12.setBackground(new Color(192, 160, 123));  // Fondo azul para mostrar selección
        this.lblTamaño12.setForeground(Color.WHITE); // Texto blanco
        this.lblTamaño12.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        if (label != null) {
            label.setBackground(Color.BLACK);  // Fondo normal
            label.setForeground(Color.BLACK); // Texto negro
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            label.setForeground(Color.WHITE);
        }

    }

    public void SetMontoApuestas(int monto) {
        //Pone el monto de apuestas
        opcionesTablero.monto = monto;

        JLabel label = null;
        
        switch (monto) {
            case 10 -> {
                label=this.lblMonto10;
            }
            case 20 -> {
                label=this.lblMonto20;
            }
            case 40 -> {
                label=this.lblMonto40;
            }
        }

        this.lblMonto10.setOpaque(true);  // Permitir cambiar el fondo
        this.lblMonto10.setBackground(new Color(192, 160, 123));  // Fondo azul para mostrar selección
        this.lblMonto10.setForeground(Color.WHITE); // Texto blanco
        this.lblMonto10.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        
        this.lblMonto20.setOpaque(true);  // Permitir cambiar el fondo
        this.lblMonto20.setBackground(new Color(192, 160, 123));  // Fondo azul para mostrar selección
        this.lblMonto20.setForeground(Color.WHITE); // Texto blanco
        this.lblMonto20.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        
        this.lblMonto40.setOpaque(true);  // Permitir cambiar el fondo
        this.lblMonto40.setBackground(new Color(192, 160, 123));  // Fondo azul para mostrar selección
        this.lblMonto40.setForeground(Color.WHITE); // Texto blanco
        this.lblMonto40.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        
        if (label != null) {
            label.setBackground(Color.BLACK);  // Fondo normal
            label.setForeground(Color.BLACK); // Texto negro
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            label.setForeground(Color.WHITE);
        }
    }

    public void SetFichasJugador(int fichas) {
        //Pone las fichas de jugador
        opcionesTablero.fichas = fichas;
        
        JLabel label = null;
        
        switch(fichas){
            case 2 -> {
                label=this.lblFichas2;
            }
            case 4 -> {
                label=this.lblFichas4;
            }
            case 6 -> {
                label=this.lblFichas6;
            }
        }
        this.lblFichas2.setOpaque(true);  // Permitir cambiar el fondo
        this.lblFichas2.setBackground(new Color(192, 160, 123));  // Fondo azul para mostrar selección
        this.lblFichas2.setForeground(Color.WHITE); // Texto blanco
        this.lblFichas2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        
        this.lblFichas4.setOpaque(true);  // Permitir cambiar el fondo
        this.lblFichas4.setBackground(new Color(192, 160, 123));  // Fondo azul para mostrar selección
        this.lblFichas4.setForeground(Color.WHITE); // Texto blanco
        this.lblFichas4.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        
        this.lblFichas6.setOpaque(true);  // Permitir cambiar el fondo
        this.lblFichas6.setBackground(new Color(192, 160, 123));  // Fondo azul para mostrar selección
        this.lblFichas6.setForeground(Color.WHITE); // Texto blanco
        this.lblFichas6.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        
        if (label != null) {
            label.setBackground(Color.BLACK);  // Fondo normal
            label.setForeground(Color.BLACK); // Texto negro
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            label.setForeground(Color.WHITE);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlComoJugar = new javax.swing.JPanel();
        lblComoJugar = new javax.swing.JLabel();
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
        pnlUnirse = new javax.swing.JPanel();
        lblUnirse = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(102, 102, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        pnlComoJugar.setBackground(new java.awt.Color(192, 160, 123));
        pnlComoJugar.setMaximumSize(new java.awt.Dimension(300, 75));
        pnlComoJugar.setMinimumSize(new java.awt.Dimension(300, 75));
        pnlComoJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlComoJugarMouseClicked(evt);
            }
        });

        lblComoJugar.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblComoJugar.setText("COMO JUGAR");
        lblComoJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblComoJugarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlComoJugarLayout = new javax.swing.GroupLayout(pnlComoJugar);
        pnlComoJugar.setLayout(pnlComoJugarLayout);
        pnlComoJugarLayout.setHorizontalGroup(
            pnlComoJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComoJugarLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblComoJugar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlComoJugarLayout.setVerticalGroup(
            pnlComoJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComoJugarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblComoJugar)
                .addContainerGap(18, Short.MAX_VALUE))
        );

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
                .addGap(40, 40, 40)
                .addComponent(lblJugar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlJugarLayout.setVerticalGroup(
            pnlJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJugarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblJugar)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnlUnirse.setBackground(new java.awt.Color(192, 160, 123));
        pnlUnirse.setMaximumSize(new java.awt.Dimension(300, 75));
        pnlUnirse.setMinimumSize(new java.awt.Dimension(300, 75));
        pnlUnirse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlUnirseMouseClicked(evt);
            }
        });

        lblUnirse.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblUnirse.setText("UNIRSE");
        lblUnirse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUnirseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlUnirseLayout = new javax.swing.GroupLayout(pnlUnirse);
        pnlUnirse.setLayout(pnlUnirseLayout);
        pnlUnirseLayout.setHorizontalGroup(
            pnlUnirseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnirseLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblUnirse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlUnirseLayout.setVerticalGroup(
            pnlUnirseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnirseLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblUnirse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(223, 207, 188));

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
        jScrollPane4.setViewportView(txtCodigo);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(pnlComoJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnlUnirse, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pnlComoJugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlJugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlUnirse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(67, 67, 67))
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

    private void lblJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJugarMouseClicked
        Jugar();
    }//GEN-LAST:event_lblJugarMouseClicked

    private void pnlJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlJugarMouseClicked
        Jugar();
    }//GEN-LAST:event_pnlJugarMouseClicked

    private void lblTamaño8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño8MouseClicked
        SetTamañoTablero(8);
    }//GEN-LAST:event_lblTamaño8MouseClicked

    private void lblTamaño10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño10MouseClicked
        SetTamañoTablero(10);
    }//GEN-LAST:event_lblTamaño10MouseClicked

    private void lblTamaño12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTamaño12MouseClicked
        SetTamañoTablero(12);
    }//GEN-LAST:event_lblTamaño12MouseClicked

    private void lblMonto10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto10MouseClicked
        SetMontoApuestas(10);
    }//GEN-LAST:event_lblMonto10MouseClicked

    private void lblMonto20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto20MouseClicked
        SetMontoApuestas(20);
    }//GEN-LAST:event_lblMonto20MouseClicked

    private void lblMonto40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonto40MouseClicked
        SetMontoApuestas(40);
    }//GEN-LAST:event_lblMonto40MouseClicked

    private void lblFichas2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas2MouseClicked
        SetFichasJugador(2);
    }//GEN-LAST:event_lblFichas2MouseClicked

    private void lblFichas4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas4MouseClicked
        SetFichasJugador(4);
    }//GEN-LAST:event_lblFichas4MouseClicked

    private void lblFichas6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFichas6MouseClicked
        SetFichasJugador(6);
    }//GEN-LAST:event_lblFichas6MouseClicked

    private void lblComoJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblComoJugarMouseClicked
        ComoJugar();
    }//GEN-LAST:event_lblComoJugarMouseClicked

    private void pnlComoJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlComoJugarMouseClicked
        ComoJugar();
    }//GEN-LAST:event_pnlComoJugarMouseClicked

    private void lblUnirseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUnirseMouseClicked
        Unirse();
    }//GEN-LAST:event_lblUnirseMouseClicked

    private void pnlUnirseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlUnirseMouseClicked
        Unirse();
    }//GEN-LAST:event_pnlUnirseMouseClicked

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
            java.util.logging.Logger.getLogger(FrameOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameOpciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JLabel lblComoJugar;
    private javax.swing.JLabel lblFichas2;
    private javax.swing.JLabel lblFichas4;
    private javax.swing.JLabel lblFichas6;
    private javax.swing.JLabel lblJugar;
    private javax.swing.JLabel lblMonto10;
    private javax.swing.JLabel lblMonto20;
    private javax.swing.JLabel lblMonto40;
    private javax.swing.JLabel lblTamaño10;
    private javax.swing.JLabel lblTamaño12;
    private javax.swing.JLabel lblTamaño8;
    private javax.swing.JLabel lblUnirse;
    private javax.swing.JPanel pnlComoJugar;
    private javax.swing.JPanel pnlJugar;
    private javax.swing.JPanel pnlUnirse;
    private javax.swing.JTextArea txtCodigo;
    // End of variables declaration//GEN-END:variables
}
