package cliente;

/**
 *
 * @author 
 */
public enum TipoMensaje {
    CONECTARSE(1),
    DESCONECTARSE(2),
    CREAR_SALA(3),
    UNIRSE_SALA(4),
    PASAR_OPCIONES(5),
    PASAR_CAMBIOS(6),
    PASAR_JUGADORES(7),
    JUGADOR_SALE(8);

    private final int type;
    
    private TipoMensaje(int type) {
        this.type = type;
    }    
}
