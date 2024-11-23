
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
    
    public void desconectar(String codigoSala){
        cliente.disconnect(codigoSala);
    }
    
    public void enviarMensaje(Mensaje mensaje){
        cliente.sendMessage(mensaje);
    }

//    public boolean unirseServidor(String codigoSala, FrameInicio frameInicio) { 
//        cliente = new ClientePatolli(codigoSala, frameInicio);
//        cliente.conectar();
//        cliente.enviarCodigoSala();
//        System.out.println("cliente conectado");
//        return true;
//    }
//    
//    
//    
//    public boolean jugadorSale(int jugador) {
//        return cliente.enviarJugadorSale(jugador);
//    }
//    
//    public boolean pasarOpciones(int tamaño, int monto, int fichas,int jugadores){
//         return cliente.enviarOpciones(tamaño, monto, fichas,jugadores);
//    }
//    
//    public boolean actualizarCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
//            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
//        return cliente.enviarCambiosAClientes(montoJugadores, siguienteJugador,
//                fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion,
//                fichasMazorcaPosicion);
//    }
//    public void getNumeroJugadores(){
//        cliente.getNumeroJugadores();
//    }
//    public boolean crearSala(String codigoSala, FrameInicio frameInicio) {
//        cliente = new ClientePatolli(codigoSala, frameInicio);
//        cliente.conectar();
//        cliente.crearSala(codigoSala);
//        System.out.println("Sala creada");
//        return true;
//    }
}
