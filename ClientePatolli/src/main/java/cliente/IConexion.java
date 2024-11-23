package cliente;

/**
 *
 * @author
 */
public interface IConexion {
    
    void sendMessage(Mensaje message);
    void init();
    default void disconnect() {};
}
