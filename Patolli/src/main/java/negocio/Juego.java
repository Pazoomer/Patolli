
package negocio;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import servidor.ClientePatolli;
import servidor.ServidorPatolli;

/**
 *
 * @author t1pas
 */
public class Juego {
    
    ServidorPatolli servidor;

    public boolean actualizarCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
        return servidor.enviarCambiosAClientes(montoJugadores, siguienteJugador,
                fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion,
                fichasMazorcaPosicion);
    }

    public boolean jugadorSale(int jugador) {
        System.out.println("Jugador sale del juego: " + jugador);
        return servidor.jugadorSale(jugador);
    }

    public boolean crearServidor(String codigoSala, JFrame frameInicio) {
        servidor = new ServidorPatolli(codigoSala);
        servidor.crearServidor();

        ClientePatolli cliente = new ClientePatolli(codigoSala, frameInicio);
        return cliente.conectar();
    }

    public boolean unirseServidor(String codigoSala, JFrame frameInicio) {
        ClientePatolli cliente = new ClientePatolli(codigoSala, frameInicio);
        return cliente.conectar();
    }
    
    public void destruirServidor() {
        servidor.detenerServidor();
    }
    
    public boolean pasarOpciones(int tamaño, int monto, int fichas,int jugadores){
        return servidor.pasarOpciones(tamaño, monto, fichas,jugadores);
    }
    
    public int jugadorEntra(){
        return servidor.jugadorEntra();
    }
}
