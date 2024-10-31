package negocio;

import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IControlJuego {

    /**
     * Desactiva el servidor con el codigo de sala
     */
    public void destruirServidor();

    /**
     * Crea un nuevo servidor con el codigo de la sala
     *
     * @param codigoSala Contraseña para unirse al servidor
     * @return
     */
    public boolean crearServidor(String codigoSala);

    /**
     * Esta instancia de programa se une al servidor con el codigo de sala
     *
     * @param codigoSala Contraseña del servidor
     * @return
     */
    public boolean unirseServidor(String codigoSala);

    /**
     * Recibe los cambios de la interfaz del jugador actual y los manda a las
     * pantallas de los demas jugadores
     *
     * @param montoJugadores Monto de apuestas de los jugadores
     * @param siguienteJugador Siguiente Jugador
     * @param fichasGatoPosicion Posicion de fichas gato
     * @param fichasConchaPosicion Posicion de fichas concha
     * @param fichasPiramidePosicion Posicion de fichas piramide
     * @param fichasMazorcaPosicion Posicion de fichas mazorca
     * @return
     */
    public boolean actualizarCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion);

    /**
     * Saca del juego al jugador del parametro
     *
     * @param jugador Jugador a sacar del juego
     * @return
     */
    public boolean jugadorSale(int jugador);

    /**
     * Un jugador se une a la partida
     *
     * @return Devuelve el numero de jugador que es
     */
    public int jugadorEntra();

    /**
     * Pasa los ajustes de opciones
     *
     * @param tamaño
     * @param monto
     * @param fichas
     * @param jugadores
     * @return
     */
    public boolean pasarOpciones(int tamaño, int monto, int fichas, int jugadores);

}
