package Mock;

/**
 * La interfaz INegocio define las operaciones básicas 
 * para el funcionamiento de un juego de Patolli.
 * Esta interfaz asegura que cualquier clase que la implemente
 * tenga la lógica de negocio necesaria para administrar el flujo del juego.
 * 
 * @author t1pas
 */
public interface INegocio {
    
    /**
     * Determina cuál es el siguiente jugador que debe tomar su turno.
     * Este método podría implementar la lógica para cambiar el turno 
     * en un juego de varios jugadores.
     * 
     * @return El índice o identificador del siguiente jugador.
     */
    public int SiguienteJugador();

    /**
     * Simula el lanzamiento de las cañas (similar a lanzar dados),
     * una mecánica común en el juego de Patolli. El resultado del lanzamiento
     * podría determinar cuántos espacios puede avanzar una ficha.
     * 
     * @return El número de espacios que puede moverse según el resultado del lanzamiento.
     */
    public int LanzarCañas();

    /**
     * Mueve una ficha en el tablero de juego de acuerdo con las reglas de Patolli.
     * Este método manejaría la lógica para verificar movimientos válidos
     * y actualizar la posición de las fichas.
     * 
     * @return Un valor que podría representar el éxito del movimiento o un código de error.
     */
    public int MoverFicha();

    /**
     * Maneja la lógica de apostar dentro del juego de Patolli. Los jugadores
     * apuestan bienes antes o durante el juego, y este método podría encargarse
     * de procesar y registrar esas apuestas.
     * 
     * @return El valor apostado o un código que indique el estado de la apuesta.
     */
    public int Apostar();

}

