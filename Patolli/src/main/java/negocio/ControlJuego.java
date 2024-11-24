
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
    //TODO: Conectar con frameInicio

    public ControlJuego(FrameInicio frameInicio) {
        this.frameInicio=frameInicio;
        juego=new Juego();
    }
    
    @Override
    public void conectarse(FrameInicio frameInicio) {
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
    
//    @Override
//    public boolean actualizarCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
//            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
//        return juego.actualizarCambios(montoJugadores, siguienteJugador, fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion);
//    }
//
//    @Override
//    public boolean jugadorSale(int jugador) {
//        return juego.jugadorSale(jugador);
//    }
//
//    @Override
//    public boolean unirseServidor(String codigoSala) {
//        return juego.unirseServidor(codigoSala, frameInicio);
//    }
//
//    @Override
//    public boolean pasarOpciones(int tamaño, int monto, int fichas,int jugadores) {
//        return juego.pasarOpciones(tamaño, monto, fichas,jugadores);
//    }
//
//    
//
//    @Override
//    public void getNumeroJugadores() {
//        juego.getNumeroJugadores();
//    }
//
//    @Override
//    public boolean crearSala(String codigoSala) {
//       return juego.crearSala(codigoSala, frameInicio);
//    }

    
}
