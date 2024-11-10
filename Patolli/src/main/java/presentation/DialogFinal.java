package presentation;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author t1pas
 */
public class DialogFinal extends javax.swing.JDialog {

    private final FrameInicio parent;
    private final List<Integer> podio;
    /**
     * Creates new form DialogFinal
     * @param parent
     * @param podio
     */
    public DialogFinal(FrameInicio parent, List podio) {
        super(parent, false);
        this.parent=parent;
        this.podio=podio;
        initComponents();
        inicializarGUI();
    }

    /**
     * Inicializa las imagenes del podio
     */
    private void inicializarGUI() {
        String posicionARuta = null;
        List<String> podioTexto = new ArrayList<>();
        for (int i = 0; i < podio.size(); i++) {
            switch (i) {
                case 0 -> {
                    posicionARuta = "/cat.png";
                }
                case 1 -> {
                    posicionARuta = "/concha.png";
                }
                case 2 -> {
                    posicionARuta = "/piramide.png";
                }
                case 3 -> {
                    posicionARuta = "/mazorca.png";
                }
            }
            podioTexto.add(posicionARuta);
        }
        inicializarImagen(this.lblPrimerLugar, podioTexto.get(podioTexto.size() - 1), 253, 217);
        inicializarImagen(this.lblSegundoLugar, podioTexto.get(podioTexto.size() - 2), 119, 102);
        if (podioTexto.size() > 2) {
            inicializarImagen(this.lblTercerLugar, podioTexto.get(podioTexto.size() - 3), 119, 102);
        }
        if (podioTexto.size() > 3) {
            inicializarImagen(this.lblCuartoLugar, podioTexto.get(podioTexto.size() - 4), 119, 102);
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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFinal = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        lblPrimerLugar = new javax.swing.JLabel();
        lblGana = new javax.swing.JLabel();
        lblSegundoLugar = new javax.swing.JLabel();
        lblSegundo = new javax.swing.JLabel();
        lblTercerLugar = new javax.swing.JLabel();
        lblCuartoLugar = new javax.swing.JLabel();
        lblTercero = new javax.swing.JLabel();
        lblCuarto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlFinal.setBackground(new java.awt.Color(102, 102, 0));

        btnVolver.setBackground(new java.awt.Color(192, 160, 123));
        btnVolver.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        btnVolver.setText("VOLVER AL MENU");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        lblPrimerLugar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblGana.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblGana.setText("PRIMER LUGAR");

        lblSegundoLugar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSegundo.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblSegundo.setText("SEGUNDO");

        lblTercerLugar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCuartoLugar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTercero.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblTercero.setText("TERCER");

        lblCuarto.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblCuarto.setText("CUARTO");

        javax.swing.GroupLayout pnlFinalLayout = new javax.swing.GroupLayout(pnlFinal);
        pnlFinal.setLayout(pnlFinalLayout);
        pnlFinalLayout.setHorizontalGroup(
            pnlFinalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFinalLayout.createSequentialGroup()
                .addGroup(pnlFinalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFinalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblGana, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFinalLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(lblPrimerLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(pnlFinalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFinalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTercero)
                        .addComponent(lblCuarto))
                    .addComponent(lblSegundo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 38, Short.MAX_VALUE)
                .addGroup(pnlFinalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFinalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTercerLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCuartoLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSegundoLugar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
            .addGroup(pnlFinalLayout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(btnVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFinalLayout.setVerticalGroup(
            pnlFinalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFinalLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(pnlFinalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrimerLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFinalLayout.createSequentialGroup()
                        .addComponent(lblSegundoLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTercerLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCuartoLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFinalLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblSegundo)
                        .addGap(66, 66, 66)
                        .addComponent(lblTercero)
                        .addGap(58, 58, 58)
                        .addGroup(pnlFinalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCuarto)
                            .addComponent(lblGana, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        parent.PasarPantallaInicio(this);
    }//GEN-LAST:event_btnVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblCuarto;
    private javax.swing.JLabel lblCuartoLugar;
    private javax.swing.JLabel lblGana;
    private javax.swing.JLabel lblPrimerLugar;
    private javax.swing.JLabel lblSegundo;
    private javax.swing.JLabel lblSegundoLugar;
    private javax.swing.JLabel lblTercerLugar;
    private javax.swing.JLabel lblTercero;
    private javax.swing.JPanel pnlFinal;
    // End of variables declaration//GEN-END:variables
}
