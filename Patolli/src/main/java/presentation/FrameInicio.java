package presentation;

import cliente.Mensaje;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import negocio.ControlJuego;
import negocio.IControlJuego;

/**
 * La clase FrameInicio representa la pantalla inicial del juego.
 * Proporciona un mecanismo para que el usuario pase a la siguiente pantalla, 
 * que es la pantalla donde puede unirse o crear un juego.
 * 
 * @author t1pas
 */
public class FrameInicio extends javax.swing.JFrame {

    private final IControlJuego controlJuego;
    DialogTablero tablero;
    DialogSala sala;
    DialogUnirseCrear unirseCrear;
    public boolean isHost;
    public boolean volverInicio;
    
    /**
     * Constructor de FrameInicio.
     * Inicializa la ventana y desactiva la opción
     * de cambiar el tamaño de la ventana.
     */
    public FrameInicio() {
        this.setResizable(false);
        initComponents();
        controlJuego = new ControlJuego(this);
    }
    /**
     * El jugador se conecta al servidor
     */
    public void conectarse(){
        volverInicio=false;
        controlJuego.conectarse(this);
    }
    /**
     * El jugador se desconecta del servidor
     * @param codigoSala
     * @param miJugador
     */
    public void desconectar(String codigoSala, int miJugador){
        controlJuego.desconectar(codigoSala,miJugador);
    }
    /**
     * El jugador envia un mensaje a los demas jugadores
     * @param mensaje
     */
    public void enviarMensaje(Mensaje mensaje){
        controlJuego.enviarMensaje(mensaje);
    }
    public void onConectarse(Mensaje mensaje) {

    }
    public void onUnirseSala(Mensaje mensaje) {
        if (sala != null) {
            sala.añadirJugador(1);
        }
    }
    public void onCrearSala(Mensaje mensaje) {

    }
    public void onPasarOpciones(Mensaje mensaje) { 
        if(sala!=null){
            sala.recibirOpciones(mensaje.getBody().getTamaño(), mensaje.getBody().getMonto(), mensaje.getBody().getFichas(), mensaje.getBody().getJugadores(), mensaje.getBody().getCodigoSala());
        }
    }
    public void onPasarCambios(Mensaje mensaje) {
        if(tablero!=null){
            tablero.recibirCambios(mensaje.getBody().getMontoJugadores(), mensaje.getBody().getJugador(), mensaje.getBody().getFichasGatoPosicion(), mensaje.getBody().getFichasConchaPosicion(), mensaje.getBody().getFichasPiramidePosicion(), mensaje.getBody().getFichasMazorcaPosicion());
        }     
    } 
    public void onDesconectarse(Mensaje mensaje){
        JOptionPane.showMessageDialog(null, mensaje.getBody().getRazonDesconexion(), "Se desconecto del servidor", JOptionPane.INFORMATION_MESSAGE);
        if(tablero!=null){
            this.PasarPantallaInicio(tablero);
        }else if(sala!=null){
            this.PasarPantallaInicio(sala);
        }    
    }
    public void onPasarJugadores(Mensaje mensaje) {
        if (unirseCrear != null) {
            unirseCrear.existeSala(mensaje.getBody().isExisteSala());
        }else{
            System.out.println("unirseCrear es null");
        }
        
        int jugadores = mensaje.getBody().getJugadores();

        if (sala != null) {
            sala.setMiJugador(jugadores-1);
        }else{
            System.out.println("Sala es null");
        }
    }
    public void onJugadorSale(Mensaje mensaje){
        if (sala != null) {
            sala.recibirJugadorSale(mensaje.getBody().getJugador());
        }else{
            System.out.println("Sala es null");
        }
        if(tablero!=null){
           tablero.recibirJugadorSale(mensaje.getBody().getJugador());
        }else{
            System.out.println("Tablero es null");
        }          
    }
    /**
     * Cierra la aplicacion por completo
     */
    public void CerrarPrograma() {
        try {
            if (sala != null) {
                sala.dispose();
            }
            if (unirseCrear != null) {
                unirseCrear.dispose();
            }
            if (tablero != null) {
                tablero.dispose();
            }
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                // Limpia recursos aquí (cerrar conexiones, guardar datos, etc.)
                System.out.println("Cerrando el programa...");
            }));
        } finally {
            System.exit(0);
        }
    }
    /**
     * Pasa a la siguiente pantalla, que es FrameUnirseCrear. Crea una nueva
     * instancia de FrameUnirseCrear y esconde la ventana actual
     *
     * @param children
     */
    public void PasarPantallaUnirseCrear(JDialog children) {  
        unirseCrear = new DialogUnirseCrear(this);
        if (children != null) {
            unirseCrear.setLocationRelativeTo(children);
            children.setVisible(false);
        } else {
            unirseCrear.setLocationRelativeTo(this);
        }
        this.setVisible(false);
        unirseCrear.setVisible(true);
    } 
    /**
     * Pasa a la siguiente pantalla, que es DialogFinal. Crea una nueva
     * instancia de DialogFinal y esconde la ventana actual
     *
     * @param children
     * @param podio
     */
    public void PasarPantallaFinal(JDialog children, List podio) {
        DialogFinal dialogFinal = new DialogFinal(this, podio);
        dialogFinal.setLocationRelativeTo(children);
        if (children != null) {
            children.setVisible(false);
        } 
        this.setVisible(false);
        dialogFinal.setVisible(true);
    }
    /**
     * Pasa a la siguiente pantalla, que es DialogComoJugar Crea una nueva
     * instancia de DialogComoJugar y esconde la ventana actual
     *
     * @param children
     */
    public void PasarPantallaComoJugar(JDialog children) {
        DialogComoJugar comoJugar = new DialogComoJugar(this);
        comoJugar.setLocationRelativeTo(children);
        if (children != null) {
            children.setVisible(false);
        }
        this.setVisible(false);
        comoJugar.setVisible(true); 
    } 
    /**
     * Pasa a la siguiente pantalla, que es FrameSala.
     * Crea una nueva instancia de FrameSala y esconde la ventana actual
     * @param children
     * @param tamaño
     * @param monto
     * @param fichas
     * @param codigo
     */
    public void PasarPantallaSala(JDialog children,int tamaño, int monto, int fichas, String codigo) {
        sala = new DialogSala(this,tamaño,monto,fichas,codigo);
        sala.setLocationRelativeTo(children);
        if (children != null) {
            children.setVisible(false);
        }
        this.setVisible(false);
        sala.setVisible(true); 
    } 
    /**
     * Pasa a la siguiente pantalla, que es FrameOpciones.
     * Crea una nueva instancia de FrameOpciones y esconde la ventana actual
     * @param children
     */
    public void PasarPantallaOpciones(JDialog children) {
        
        DialogOpciones opciones = new DialogOpciones(this);
        opciones.setLocationRelativeTo(children);
        if (children != null) {
            children.setVisible(false);
        }
        this.setVisible(false);
        opciones.setVisible(true);       
    } 
    /**
     * Pasa a la siguiente pantalla, que es FrameInicio
     *
     * @param children
     */
    public void PasarPantallaInicio(JDialog children) {
        this.setLocationRelativeTo(children);
        if (children != null) {
            children.setVisible(false);
        }
        if (this.unirseCrear != null) {
            unirseCrear.setVisible(false);
        }
        if (this.sala != null) {
            sala.setVisible(false);
        }
        this.setVisible(true);
    }
    /**
     * Pasa a la siguiente pantalla, que es FrameTablero.
     * Crea una nueva instancia de FrameTablero y esconde la ventana actual
     * @param children
     * @param tamaño
     * @param fichas
     * @param monto
     * @param jugadores
     * @param miJugador
     * @param codigoSala
     */
    public void PasarPantallaTablero(JDialog children,int tamaño, int fichas, int monto, int jugadores, int miJugador, String codigoSala) {
        tablero = new DialogTablero(this, tamaño, fichas, monto, jugadores, miJugador,codigoSala);
        tablero.setLocationRelativeTo(children);
        tablero.setModal(false);
        if (children != null) {
            children.setVisible(false);
        }
        this.setVisible(false);
        tablero.setVisible(true);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTodo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblInstruccion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        pnlTodo.setBackground(new java.awt.Color(255, 255, 255));
        pnlTodo.setMaximumSize(new java.awt.Dimension(800, 600));
        pnlTodo.setMinimumSize(new java.awt.Dimension(800, 600));
        pnlTodo.setPreferredSize(new java.awt.Dimension(800, 600));
        pnlTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTodoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTodoMousePressed(evt);
            }
        });

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patolli.png"))); // NOI18N

        lblInstruccion.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        lblInstruccion.setText("Haga Click Para Jugar");

        javax.swing.GroupLayout pnlTodoLayout = new javax.swing.GroupLayout(pnlTodo);
        pnlTodo.setLayout(pnlTodoLayout);
        pnlTodoLayout.setHorizontalGroup(
            pnlTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTodoLayout.createSequentialGroup()
                .addGroup(pnlTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTodoLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTodoLayout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(lblInstruccion)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        pnlTodoLayout.setVerticalGroup(
            pnlTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTodoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInstruccion)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pnlTodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTodoMouseClicked
        
    }//GEN-LAST:event_pnlTodoMouseClicked

    private void pnlTodoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTodoMousePressed
        PasarPantallaUnirseCrear(null);
    }//GEN-LAST:event_pnlTodoMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblInstruccion;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel pnlTodo;
    // End of variables declaration//GEN-END:variables
}
