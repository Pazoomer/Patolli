
package servidor;

import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author t1pas
 */
    public class ProtocoloPatolli {

        private List<JLabel> casillas;
        private List<Integer> montoJugadores;
        private int jugador;

        private List<JLabel> fichasGato;
        private List<JLabel> fichasConcha;
        private List<JLabel> fichasPiramide;
        private List<JLabel> fichasMazorca;

        private List<Integer> fichasGatoPosicion;
        private List<Integer> fichasConchaPosicion;
        private List<Integer> fichasPiramidePosicion;
        private List<Integer> fichasMazorcaPosicion;

        public String processInput(String theInput) {
            // Procesa la entrada, por ejemplo, si theInput contiene posiciones de fichas
            if (theInput == null) {
                return "Esperando acciones de los jugadores.";
            } else {
                // Lógica para actualizar el juego según la entrada recibida
                return "Acción procesada: " + theInput;
            }
        }
    }
