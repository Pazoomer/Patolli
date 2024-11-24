package negocio;

import cliente.Mensaje;
import presentation.FrameInicio;

/**
 *
 * @author t1pas
 */
public interface IControlJuego {

    public void conectarse(FrameInicio frameInicio);
    public void desconectar(String codigoSala, int miJugador);
    public void enviarMensaje(Mensaje mensaje);
}
