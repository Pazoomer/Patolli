
package negocio;

import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author t1pas
 */
public class ControlJuego implements IControlJuego {

    Juego juego=new Juego();

    @Override
    public boolean actualizarCambios(List<JLabel> casillas, List<Integer> montoJugadores, int jugador, List<JLabel> fichasGato, List<JLabel> fichasConcha, List<JLabel> fichasPiramide, List<JLabel> fichasMazorca, List<Integer> fichasGatoPosicion, List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
        return juego.actualizarCambios(casillas, montoJugadores, jugador, fichasGato, fichasConcha, fichasPiramide, fichasMazorca, fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion);
    }

    @Override
    public boolean jugadorSale(int jugador) {
        return juego.jugadorSale(jugador);
    }

    @Override
    public boolean crearServidor(String codigoSala) {
        return juego.crearServidor(codigoSala);
    }

    @Override
    public boolean unirseServidor(String codigoSala) {
        return juego.unirseServidor(codigoSala);
    }

    @Override
    public void destruirServidor() {
        juego.destruirServidor();
    }
    
}
