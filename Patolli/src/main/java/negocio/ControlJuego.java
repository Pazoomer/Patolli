
package negocio;

import java.util.List;
import javax.swing.JFrame;
import presentation.FrameInicio;

/**
 *
 * @author t1pas
 */
public class ControlJuego implements IControlJuego {

    Juego juego;
    FrameInicio frameInicio;

    public ControlJuego(FrameInicio frameInicio) {
        this.frameInicio=frameInicio;
        juego=new Juego();
    }
    

    @Override
    public boolean actualizarCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
        return juego.actualizarCambios(montoJugadores, siguienteJugador, fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion);
    }

    @Override
    public boolean jugadorSale(int jugador) {
        return juego.jugadorSale(jugador);
    }

    @Override
    public boolean crearServidor(String codigoSala) {
        return juego.crearServidor(codigoSala, frameInicio);
    }

    @Override
    public boolean unirseServidor(String codigoSala) {
        return juego.unirseServidor(codigoSala, frameInicio);
    }

    @Override
    public void destruirServidor() {
        juego.destruirServidor();
    }

    @Override
    public boolean pasarOpciones(int tamaño, int monto, int fichas,int jugadores) {
        return juego.pasarOpciones(tamaño, monto, fichas,jugadores);
    }

    @Override
    public int jugadorEntra() {
    return juego.jugadorEntra();
    }

}
