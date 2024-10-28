
package negocio;

import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author t1pas
 */
public class ControlJuego implements IControlJuego {

    @Override
    public void actualizarCambios(List<JLabel> casillas, List<Integer> montoJugadores, int jugador, List<JLabel> fichasGato, List<JLabel> fichasConcha, List<JLabel> fichasPiramide, List<JLabel> fichasMazorca, List<Integer> fichasGatoPosicion, List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
        //Actualiza las pantallas de todos los jugadores (excepto de quien envio los cambios)
    }

    @Override
    public void jugadorSale(int jugador) {
        //Remueve del juego al jugador de todas las pantallas (excepto de quien salio del juego)
        System.out.println("Jugador sale del juego: "+jugador);
    }
    
}
