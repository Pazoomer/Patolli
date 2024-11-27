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
    //Fondo de la pantalla
    private final String RUTAFONDO="/fondoTierraGruesa.jpg";
    private final FondoPanel fondo=new FondoPanel(RUTAFONDO);
    /**
     * Creates new form DialogFinal
     * @param parent
     * @param podio
     */
    public DialogFinal(FrameInicio parent, List podio) {
        super(parent, false);
        this.parent=parent;
        this.podio=podio;
        if(fondo!=null){
           this.setContentPane(fondo); 
        }else{
            System.out.println("No cargo la imagen de fondo");
        }
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
            switch (podio.get(i)) {
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
        inicializarImagen(this.lblPrimerLugar, podioTexto.get(0), 253, 217);
        inicializarImagen(this.lblSegundoLugar, podioTexto.get(1), 119, 102);
        if (podioTexto.size() > 2) {
            inicializarImagen(this.lblTercerLugar, podioTexto.get(2), 119, 102);
        }
        if (podioTexto.size() > 3) {
            inicializarImagen(this.lblCuartoLugar, podioTexto.get(3), 119, 102);
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
    private void salir(){
        parent.PasarPantallaUnirseCrear(this);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolver = new javax.swing.JButton();
        lblPrimerLugar = new javax.swing.JLabel();
        lblSegundoLugar = new javax.swing.JLabel();
        lblTercerLugar = new javax.swing.JLabel();
        lblCuartoLugar = new javax.swing.JLabel();
        pnlSegundoTexto = new javax.swing.JPanel();
        lblSegundo = new javax.swing.JLabel();
        pnlTercerTexto = new javax.swing.JPanel();
        lblTercero = new javax.swing.JLabel();
        pnlCuartoTexto = new javax.swing.JPanel();
        lblCuarto = new javax.swing.JLabel();
        pnlPrimerTexto = new javax.swing.JPanel();
        lblGana = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnVolver.setBackground(new java.awt.Color(192, 160, 123));
        btnVolver.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        btnVolver.setText("VOLVER AL MENU");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        lblPrimerLugar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSegundoLugar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTercerLugar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCuartoLugar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlSegundoTexto.setBackground(new java.awt.Color(192, 160, 123));

        lblSegundo.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblSegundo.setText("SEGUNDO");

        javax.swing.GroupLayout pnlSegundoTextoLayout = new javax.swing.GroupLayout(pnlSegundoTexto);
        pnlSegundoTexto.setLayout(pnlSegundoTextoLayout);
        pnlSegundoTextoLayout.setHorizontalGroup(
            pnlSegundoTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSegundoTextoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSegundo)
                .addContainerGap())
        );
        pnlSegundoTextoLayout.setVerticalGroup(
            pnlSegundoTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSegundoTextoLayout.createSequentialGroup()
                .addComponent(lblSegundo)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pnlTercerTexto.setBackground(new java.awt.Color(192, 160, 123));

        lblTercero.setBackground(new java.awt.Color(255, 255, 255));
        lblTercero.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblTercero.setText("TERCER");

        javax.swing.GroupLayout pnlTercerTextoLayout = new javax.swing.GroupLayout(pnlTercerTexto);
        pnlTercerTexto.setLayout(pnlTercerTextoLayout);
        pnlTercerTextoLayout.setHorizontalGroup(
            pnlTercerTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTercerTextoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTercero)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTercerTextoLayout.setVerticalGroup(
            pnlTercerTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTercerTextoLayout.createSequentialGroup()
                .addComponent(lblTercero)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pnlCuartoTexto.setBackground(new java.awt.Color(192, 160, 123));

        lblCuarto.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblCuarto.setText("CUARTO");

        javax.swing.GroupLayout pnlCuartoTextoLayout = new javax.swing.GroupLayout(pnlCuartoTexto);
        pnlCuartoTexto.setLayout(pnlCuartoTextoLayout);
        pnlCuartoTextoLayout.setHorizontalGroup(
            pnlCuartoTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuartoTextoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblCuarto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCuartoTextoLayout.setVerticalGroup(
            pnlCuartoTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuartoTextoLayout.createSequentialGroup()
                .addComponent(lblCuarto)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pnlPrimerTexto.setBackground(new java.awt.Color(192, 160, 123));

        lblGana.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblGana.setText("PRIMER LUGAR");

        javax.swing.GroupLayout pnlPrimerTextoLayout = new javax.swing.GroupLayout(pnlPrimerTexto);
        pnlPrimerTexto.setLayout(pnlPrimerTextoLayout);
        pnlPrimerTextoLayout.setHorizontalGroup(
            pnlPrimerTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrimerTextoLayout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addComponent(lblGana, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlPrimerTextoLayout.setVerticalGroup(
            pnlPrimerTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrimerTextoLayout.createSequentialGroup()
                .addComponent(lblGana, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrimerLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPrimerTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTercerTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSegundoTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCuartoTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTercerLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCuartoLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSegundoLugar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107))
            .addGroup(layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(btnVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSegundoLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTercerLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCuartoLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(pnlSegundoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(pnlTercerTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblPrimerLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlPrimerTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlCuartoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        salir();
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
    private javax.swing.JPanel pnlCuartoTexto;
    private javax.swing.JPanel pnlPrimerTexto;
    private javax.swing.JPanel pnlSegundoTexto;
    private javax.swing.JPanel pnlTercerTexto;
    // End of variables declaration//GEN-END:variables
}
