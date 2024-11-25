package negocio;

import cliente.Mensaje;

/**
 *
 * @author t1pas
 */
public interface IControlJuego {

    public void conectarse();
    public void desconectar(String codigoSala, int miJugador);
    public void enviarMensaje(Mensaje mensaje);
}
