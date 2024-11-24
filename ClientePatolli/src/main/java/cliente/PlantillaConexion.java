package cliente;

/**
 *
 * @author
 */
public interface PlantillaConexion {
    
    default void proccessMessage(Mensaje mensaje) {
        switch (mensaje.getMessageType()) {
            case CONECTARSE ->onConectarse(mensaje);
            case CREAR_SALA ->onCrearSala(mensaje);
            case UNIRSE_SALA ->onUnirseSala(mensaje);
            case PASAR_OPCIONES ->onPasarOpciones(mensaje);
            case PASAR_CAMBIOS ->onPasarCambios(mensaje);
            case PASAR_JUGADORES ->onPasarJugadores(mensaje);
            case DESCONECTARSE ->onDisconnect(mensaje);
            case JUGADOR_SALE->onJugadorSale(mensaje);
        }
    }
    
    void onConectarse(Mensaje mensaje);
    
    void onCrearSala(Mensaje mensaje);
    
    void onUnirseSala(Mensaje mensaje);
    
    void onPasarOpciones(Mensaje mensaje);
    
    void onPasarJugadores(Mensaje mensaje);

    void onPasarCambios(Mensaje mensaje);
    
    void onJugadorSale(Mensaje mensaje);
    
    default void onDisconnect(Mensaje mensaje){}
}
