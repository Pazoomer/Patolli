
package servidor;

import cliente.CuerpoMensaje;
import cliente.Mensaje;
import cliente.TipoMensaje;
import presentation.FrameInicio;

/**
 *
 * @author t1pas
 */
public class Cliente {

    ConexionCliente conexionCliente;
    
    public Cliente(FrameInicio frameInicio) {
        conexionCliente= new ConexionCliente(frameInicio);
    }
    
    public void init(){
        conexionCliente.init();
    }
    
    public void sendMessage(Mensaje mensaje){
        conexionCliente.sendMessage(mensaje);
    }
    
    public void disconnect(String codigoSala){
        CuerpoMensaje cuerpo = new CuerpoMensaje();
        cuerpo.setCodigoSala(codigoSala);
        
        TipoMensaje tipo = TipoMensaje.DESCONECTARSE;
        Mensaje mensaje = new Mensaje.Builder()
                .messageType(tipo)
                .build();
        conexionCliente.sendMessage(mensaje);
        conexionCliente.disconnect();
    }
}
