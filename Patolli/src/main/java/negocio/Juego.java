
package negocio;

import cliente.Mensaje;
import presentation.FrameInicio;
import servidor.Cliente;

/**
 *
 * @author t1pas
 */
public class Juego {
   
    Cliente cliente;

    public void conectarse(FrameInicio frameInicio) {
        cliente=new Cliente(frameInicio);
        cliente.init();
    }
    
    public void desconectar(String codigoSala, int miJugador){
        cliente.disconnect(codigoSala,miJugador);
    }
    
    public void enviarMensaje(Mensaje mensaje){
        cliente.sendMessage(mensaje);
    }

}
