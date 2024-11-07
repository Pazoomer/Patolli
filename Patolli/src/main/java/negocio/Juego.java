
package negocio;

import java.util.List;
import javax.swing.JFrame;
import servidor.ClientePatolli;
import servidor.ServidorPatolli;

/**
 *
 * @author t1pas
 */
public class Juego {
    
    ServidorPatolli servidor=new ServidorPatolli("------");
    ClientePatolli cliente;

    public boolean actualizarCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
        return cliente.enviarCambiosAClientes(montoJugadores, siguienteJugador,
                fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion,
                fichasMazorcaPosicion);
    }

    public boolean jugadorSale(int jugador) {
        return cliente.enviarJugadorSale(jugador);
    }

    public boolean crearServidor(String codigoSala, JFrame frameInicio) {
        servidor = new ServidorPatolli(codigoSala);
        servidor.crearServidor();

        cliente = new ClientePatolli(codigoSala, frameInicio);
        return cliente.conectar();
    }

    public boolean unirseServidor(String codigoSala, JFrame frameInicio) {
        cliente = new ClientePatolli(codigoSala, frameInicio);
        return cliente.conectar();
    }
    
    public void destruirServidor() {
        servidor.detenerServidor();
    }
    
    public boolean pasarOpciones(int tamaño, int monto, int fichas,int jugadores){
         return cliente.enviarOpciones(tamaño, monto, fichas,jugadores);
    }
    
    public int jugadorEntra(){
        return servidor.jugadorEntra();
    }
}
