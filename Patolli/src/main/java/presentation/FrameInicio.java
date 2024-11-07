package presentation;

import java.util.List;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
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
     * Detiene el servidor y desconecta a todos los jugadores
     */
    public void detenerServidor() {
        controlJuego.destruirServidor();
    }

    /**
     * Crea un nuevo servidor
     *
     * @param codigoSala
     * @return
     */
    public boolean crearServidor(String codigoSala) {
        isHost = true;
        return controlJuego.crearServidor(codigoSala);
    }

    /**
     * Se une al servidor
     *
     * @param codigoSala
     * @return
     */
    public boolean unirseServidor(String codigoSala) {
        isHost = false;
        return controlJuego.unirseServidor(codigoSala);
    }

    /**
     * Sube los cambios para los demas jugadores
     *
     * @param siguienteJugador
     * @param montoJugadores
     * @param fichasGatoPosicion
     * @param fichasConchaPosicion
     * @param fichasPiramidePosicion
     * @param fichasMazorcaPosicion
     * @return
     */
    public boolean subirCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
        return controlJuego.actualizarCambios(montoJugadores, siguienteJugador, fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion);
    }

    /**
     * Recibe los cambios de otro jugador
     *
     * @param montoJugadores
     * @param siguienteJugador
     * @param fichasGatoPosicion
     * @param fichasConchaPosicion
     * @param fichasPiramidePosicion
     * @param fichasMazorcaPosicion
     * @return Verdadero si los cambios se recibieron con exito
     */
    public boolean recibirCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
        if (tablero==null){
            return false;
        }
        return tablero.recibirCambios(montoJugadores, siguienteJugador, fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion);
    }

    /**
     * El jugador sale de la partida
     *
     * @param jugador
     * @return
     */
    public boolean jugadorSale(int jugador) {
        return controlJuego.jugadorSale(jugador);
    }
    
    /**
     * Un jugador entra a la partida
     * @param numeroJugadores
     * @return 
     */
    public int jugadorEntra(int numeroJugadores) {
        if (sala != null) {
            sala.AñadirJugador(1);
            return sala.setMiJugador(numeroJugadores-1);
        }
        return -1;
    }
    
    /**
     * Pasa los ajustes de una partida
     * @param tamaño
     * @param monto
     * @param fichas
     * @param jugadores
     * @return 
     */
    public boolean subirOpciones(int tamaño, int monto, int fichas,int jugadores){
        return controlJuego.pasarOpciones(tamaño, monto, fichas,jugadores);
    }
    
    /**
     * Pasa los ajustes de una partida
     * @param tamaño
     * @param monto
     * @param fichas
     * @param jugadores 
     */
    public void recibirOpciones(int tamaño, int monto, int fichas,int jugadores){
        sala.recibirOpciones(tamaño, monto, fichas, jugadores);
        System.out.println("Termine inicio");
    }

    /**
     * Cierra la aplicacion por completo
     */
    public void CerrarPrograma() {
        if (isHost) {
            //TODO
            //controlJuego.destruirServidor();
        }
        this.dispose();
    }
    
    /**
     * Pasa a la siguiente pantalla, que es FrameUnirseCrear. Crea una nueva
     * instancia de FrameUnirseCrear y esconde la ventana actual
     *
     * @param children
     */
    public void PasarPantallaUnirseCrear(JDialog children) {
        this.setVisible(false);
        DialogUnirseCrear unirseCrear = new DialogUnirseCrear(this);
        if (children != null) {
            unirseCrear.setLocationRelativeTo(children);
            children.setVisible(false);
        } else {
            unirseCrear.setLocationRelativeTo(this);
        }
        unirseCrear.setVisible(true);
    }

    /**
     * Pasa a la siguiente pantalla, que es DialogComoJugar Crea una nueva
     * instancia de DialogComoJugar y esconde la ventana actual
     *
     * @param children
     */
    public void PasarPantallaComoJugar(JDialog children) {
        this.setVisible(false);
        DialogComoJugar comoJugar = new DialogComoJugar(this);
        comoJugar.setLocationRelativeTo(children);
        if (children != null) {
            children.setVisible(false);
        }
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
        this.setVisible(false);
        sala = new DialogSala(this,tamaño,monto,fichas,codigo);
        sala.setLocationRelativeTo(children);
        if (children != null) {
            children.setVisible(false);
        }
        sala.setVisible(true); 
    } 
    
    /**
     * Pasa a la siguiente pantalla, que es FrameOpciones.
     * Crea una nueva instancia de FrameOpciones y esconde la ventana actual
     * @param children
     */
    public void PasarPantallaOpciones(JDialog children) {
        this.setVisible(false);
        DialogOpciones opciones = new DialogOpciones(this);
        opciones.setLocationRelativeTo(children);
        if (children != null) {
            children.setVisible(false);
        }
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
    public void PasarPantallaTablero(JDialog children,int tamaño, int fichas, int monto, int jugadores, int miJugador) {
        this.setVisible(false);
        tablero = new DialogTablero(this, tamaño, fichas, monto, jugadores, miJugador);
        tablero.setLocationRelativeTo(children);
        tablero.setModal(false);
        if (children != null) {
            children.setVisible(false);
        }
        tablero.setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTodo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblInstrucccion = new javax.swing.JLabel();

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
        lblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoMouseClicked(evt);
            }
        });

        lblInstrucccion.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblInstrucccion.setText("Haga Click Para Empezar");
        lblInstrucccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInstrucccionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlTodoLayout = new javax.swing.GroupLayout(pnlTodo);
        pnlTodo.setLayout(pnlTodoLayout);
        pnlTodoLayout.setHorizontalGroup(
            pnlTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTodoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblLogo)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTodoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInstrucccion)
                .addGap(283, 283, 283))
        );
        pnlTodoLayout.setVerticalGroup(
            pnlTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTodoLayout.createSequentialGroup()
                .addComponent(lblLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInstrucccion))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    /**
     * Este método se ejecuta cuando se hace clic en la etiqueta lblLogo.
     * Llama al método PasarPantalla() para pasar a la pantalla de opciones.
     * 
     * @param evt El evento de clic del ratón.
     */
    private void lblLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMouseClicked
        PasarPantallaUnirseCrear(null);
    }//GEN-LAST:event_lblLogoMouseClicked
    /**
     * Este método se ejecuta cuando se hace clic en la etiqueta lblInstruccion.
     * Llama al método PasarPantalla() para pasar a la pantalla de opciones.
     * 
     * @param evt El evento de clic del ratón.
     */
    private void lblInstrucccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInstrucccionMouseClicked
        PasarPantallaUnirseCrear(null);
    }//GEN-LAST:event_lblInstrucccionMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblInstrucccion;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel pnlTodo;
    // End of variables declaration//GEN-END:variables
}
