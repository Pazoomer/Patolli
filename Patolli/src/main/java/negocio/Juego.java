
package negocio;

import java.util.List;
import javax.swing.JLabel;
import servidor.ClientePatolli;
import servidor.ServidorPatolli;

/**
 *
 * @author t1pas
 */
public class Juego {
    
    ServidorPatolli servidor;

    public boolean actualizarCambios(List<JLabel> casillas, List<Integer> montoJugadores, int jugador, List<JLabel> fichasGato, List<JLabel> fichasConcha, List<JLabel> fichasPiramide, List<JLabel> fichasMazorca, List<Integer> fichasGatoPosicion, List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
        return servidor.enviarCambiosAClientes(casillas, montoJugadores, jugador, fichasGato, fichasConcha, fichasPiramide,
                fichasMazorca, fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion,
                fichasMazorcaPosicion);
    }

    public boolean jugadorSale(int jugador) {
        System.out.println("Jugador sale del juego: "+jugador);
        return servidor.jugadorSale(jugador);
    }

    public boolean crearServidor(String codigoSala) {
        servidor=new ServidorPatolli(codigoSala);
        servidor.crearServidor();

        ClientePatolli cliente=new ClientePatolli(codigoSala);
        return cliente.conectar();
    }

    public boolean unirseServidor(String codigoSala) {
        ClientePatolli cliente=new ClientePatolli(codigoSala);
        return cliente.conectar();
    }
    
    public void destruirServidor() {
        servidor.detenerServidor();
    }
}
