package Mock;

/**
 * La clase Negocio implementa la interfaz INegocio y representa
 * la lógica de negocio principal para el juego de Patolli.
 * Actualmente, los métodos están pendientes de implementación.
 * 
 * @author t1pas
 */
public class Negocio implements INegocio {

    /**
     * Determina cuál es el siguiente jugador en el turno.
     * Una vez implementado, este método debería manejar la lógica
     * para cambiar el turno de forma cíclica entre los jugadores.
     * 
     * @return El índice o identificador del siguiente jugador.
     * @throws UnsupportedOperationException Si el método no está implementado.
     */
    @Override
    public int SiguienteJugador() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * Simula el lanzamiento de las cañas, utilizado para determinar
     * cuántos espacios puede moverse una ficha en el tablero.
     * Una vez implementado, debería devolver el resultado del lanzamiento.
     * 
     * @return Un entero que indica cuántos espacios puede avanzar el jugador.
     * @throws UnsupportedOperationException Si el método no está implementado.
     */
    @Override
    public int LanzarCañas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Mueve una ficha de un jugador en el tablero según las reglas de Patolli.
     * Este método debería validar si el movimiento es posible y actualizar
     * la posición de la ficha.
     * 
     * @return Un código que indica el éxito del movimiento o algún estado relacionado.
     * @throws UnsupportedOperationException Si el método no está implementado.
     */
    @Override
    public int MoverFicha() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Maneja las apuestas que los jugadores hacen en el juego de Patolli.
     * Una vez implementado, este método debería registrar la cantidad apostada
     * y gestionar la lógica asociada a las apuestas.
     * 
     * @return Un valor que representa la cantidad apostada o un código de éxito/error.
     * @throws UnsupportedOperationException Si el método no está implementado.
     */
    @Override
    public int Apostar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
