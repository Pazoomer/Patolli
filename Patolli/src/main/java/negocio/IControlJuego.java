
package negocio;

import datos.Partida;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author t1pas
 */
public interface IControlJuego {
    Partida partida=new Partida();
    
    /**
     * Recibe los cambios de la interfaz del jugador actual
     * @param casillas Tablero actualizado
     * @param montoJugadores Monto de apuestas de los jugadores
     * @param jugador Siguiente Jugador
     * @param fichasGato Apariencia de fichas gato
     * @param fichasConcha Apariencia de fichas concha
     * @param fichasPiramide Apariencia de fichas piramide
     * @param fichasMazorca Apariencia de fichas mazorca
     * @param fichasGatoPosicion Posicion de fichas gato
     * @param fichasConchaPosicion Posicion de fichas concha
     * @param fichasPiramidePosicion Posicion de fichas piramide
     * @param fichasMazorcaPosicion Posicion de fichas mazorca
     */
    public void actualizarCambios(List<JLabel> casillas, List<Integer> montoJugadores, int jugador, List<JLabel> fichasGato,
            List<JLabel> fichasConcha, List<JLabel> fichasPiramide, List<JLabel> fichasMazorca, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion);
    
}
