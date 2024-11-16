
package negocio;

import java.util.List;
import presentation.FrameInicio;
import servidor.ClientePatolli;

/**
 *
 * @author t1pas
 */
public class Juego {
   
    ClientePatolli cliente;

    public boolean unirseServidor(String codigoSala, FrameInicio frameInicio) {
        System.out.println("cliente conectado");
        cliente = new ClientePatolli(codigoSala, frameInicio);
        return cliente.conectar();
    }
    
    public void desconectar(){
        cliente.desconectar();
    }
    
    public boolean jugadorSale(int jugador) {
        return cliente.enviarJugadorSale(jugador);
    }
    
    public boolean pasarOpciones(int tamaño, int monto, int fichas,int jugadores){
         return cliente.enviarOpciones(tamaño, monto, fichas,jugadores);
    }
    
    public boolean actualizarCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
        return cliente.enviarCambiosAClientes(montoJugadores, siguienteJugador,
                fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion,
                fichasMazorcaPosicion);
    }
    public void getNumeroJugadores(){
        cliente.getNumeroJugadores();
    }
}
