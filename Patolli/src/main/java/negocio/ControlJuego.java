
package negocio;

import cliente.Mensaje;
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
    public void conectarse() {
        juego.conectarse(frameInicio);
    }

    @Override
    public void enviarMensaje(Mensaje mensaje) {
       juego.enviarMensaje(mensaje);
    }
    
    @Override
    public void desconectar(String codigoSala, int miJugador) {
        juego.desconectar(codigoSala, miJugador);
    }
    
}
