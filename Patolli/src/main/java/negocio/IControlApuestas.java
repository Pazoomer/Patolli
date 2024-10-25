package negocio;

/**
 *
 * @author t1pas
 */
public interface IControlApuestas {

    /**
     * El jugador cobra la apuesta
     * @param jugador Jugador a cobrar la apuesta
     * @param cantidad Cantidad que cobra el jugador
     * @return Verdadero si el jugador cobro la apuesta
     */
    Integer cobrarApuestaBanco(int jugador, int cantidad);

    /**
     * El jugador cobra la apuesta a otro jugador
     * @param jugadorCobrar Jugador a cobrar la apuesta
     * @param jugadorPagar Jugador a pagar la apuesta
     * @param cantidad Cantidad que se cobra al jugador
     * @return Verdadero se el jugador cobro la apuesta al otro jugador
     */
    Integer pagarApuestaJugador(int jugadorCobrar, int jugadorPagar, int cantidad);
}
