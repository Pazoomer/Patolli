
package negocio;

import datos.Partida;

/**
 *
 * @author t1pas
 */
public interface IControlJuego {
    Partida partida=new Partida();
    
    /**
     * Saca el jugador del juego
     * @param numJugador Jugador a sacar
     * @return Verdadero si logro desconectar al jugador
     */
    public boolean salir(int numJugador);
    
    /**
     * Calcula el resultados de las cañas
     * @return Entero con el resultado de las cañas
     */
    public int calcularResultadoCañas();
    
}
