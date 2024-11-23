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
        controlJuego.conectarse(this); //Se conecta con esta pantalla
    }
    /**
     * El jugador se desconecta del servidor
     * @param codigoSala
     */
    public void desconectar(String codigoSala){
        controlJuego.desconectar(codigoSala);
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
//    /**
//     * Sube los cambios para los demas jugadores
//     *
//     * @param siguienteJugador
//     * @param montoJugadores
//     * @param fichasGatoPosicion
//     * @param fichasConchaPosicion
//     * @param fichasPiramidePosicion
//     * @param fichasMazorcaPosicion
//     * @return
//     */
//    public boolean subirCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
//            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
//        return controlJuego.actualizarCambios(montoJugadores, siguienteJugador, fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion);
//    }
//
//    /**
//     * Recibe los cambios de otro jugador
//     *
//     * @param montoJugadores
//     * @param siguienteJugador
//     * @param fichasGatoPosicion
//     * @param fichasConchaPosicion
//     * @param fichasPiramidePosicion
//     * @param fichasMazorcaPosicion
//     * @return Verdadero si los cambios se recibieron con exito
//     */
//    public boolean recibirCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
//            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
//        if (tablero==null){
//            return false;
//        }
//        return tablero.recibirCambios(montoJugadores, siguienteJugador, fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion);
//    }
//
//    /**
//     * El jugador sale de la partida
//     *
//     * @param jugador
//     * @return
//     */
//    public boolean enviarJugadorSale(int jugador) {
//        return true;
//        //TODO: return controlJuego.jugadorSale(jugador);
//    }
//    
//    /**
//     * El jugador sale de la partida
//     *
//     * @param jugador
//     */
//    public void recibirJugadorSale(int jugador) {
//        if (tablero != null) {
//            tablero.recibirJugadorSale(jugador);
//        } else if (sala != null) {
//            sala.recibirJugadorSale(jugador);
//        }
//    }
//    /**
//     * Recibe el numero de jugadores para actualizar la interfaz de la sala
//     * @param numeroJugadores 
//     */
//    public void recibirNumeroJugadores(int numeroJugadores){
//        if (sala != null) {
//            sala.añadirJugador(1);
//            sala.setMiJugador(numeroJugadores);
//            sala.setContexto();
//        }
//    }
//    
//    /**
//     * Pide el numero de jugadores al servidor
//     */
//    public void getNumeroJugadores(){
//        this.controlJuego.getNumeroJugadores();
//    }
//    
//    /**
//     * Un jugador entra a la partida
//     * @param numeroJugadores
//     * @return 
//     */
//    public int jugadorEntra(int numeroJugadores) {
//        if (sala != null) {                
//            sala.añadirJugador(1);
//            return numeroJugadores;
//        }
//        return -1;
//    }
//    
//    /**
//     * Pasa los ajustes de una partida
//     * @param tamaño
//     * @param monto
//     * @param fichas
//     * @param jugadores
//     * @return 
//     */
//    public boolean subirOpciones(int tamaño, int monto, int fichas,int jugadores){
//        return controlJuego.pasarOpciones(tamaño, monto, fichas,jugadores);
//    }
//    
//    /**
//     * Pasa los ajustes de una partida
//     * @param tamaño
//     * @param monto
//     * @param fichas
//     * @param jugadores 
//     */
//    public void recibirOpciones(int tamaño, int monto, int fichas,int jugadores){
//        sala.recibirOpciones(tamaño, monto, fichas, jugadores);
//    }
    /**
     * Cierra la aplicacion por completo
     */
    public void CerrarPrograma() {
        //if (//isHost) {
            //TODO:
            //controlJuego.destruirServidor();
        //}
        this.dispose();
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
     * @param children
     */
    public void PasarPantallaInicio(JDialog children) {
        this.setLocationRelativeTo(children);
        if (children != null) {
            children.setVisible(false);
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
    /**
     * Este método se ejecuta cuando se hace clic en el panel pnlTodo.
     * Llama al método PasarPantalla() para pasar a la pantalla de opciones.
     * 
     * @param evt El evento de clic del ratón.
     */
    private void pnlTodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTodoMouseClicked
        PasarPantallaUnirseCrear(null);
    }//GEN-LAST:event_pnlTodoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblInstruccion;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel pnlTodo;
    // End of variables declaration//GEN-END:variables
}
