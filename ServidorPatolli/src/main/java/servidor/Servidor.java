package servidor;

import cliente.IConexion;
import cliente.IObservador;
import cliente.Mensaje;
import cliente.PlantillaConexion;
import java.util.concurrent.locks.ReentrantLock;
import logger.LoggerFactory;
import logger.IPatolliLogger;
/**
 * Maneja la lógica del servidor (iniciar, procesar mensajes)
 * @author t1pas
 */
public class Servidor implements IConexion, IObservador, PlantillaConexion {

    private ControladorMensajes controladorMensajes;
    ReentrantLock lock = new ReentrantLock();

    private final IPatolliLogger logger = LoggerFactory.getLogger(Servidor.class);

    @Override
    public void init() {
        try {
            System.out.println("Iniciando el servidor...");
            controladorMensajes = new ControladorMensajes();
            controladorMensajes.init();
            controladorMensajes.subscribe(this);
            System.out.println("Servidor iniciado");
        } catch (Exception ex) {
            logger.error(String.format("Error al iniciar el servidor: ", ex.getMessage()));
        }
    }

    @Override
    public void sendMessage(Mensaje message) {
        controladorMensajes.proccessMessage(message);
    }

    @Override
    public void onUpdate(Object obj) {
        lock.lock();
        try {
            proccessMessage((Mensaje) obj);
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void onConectarse(Mensaje mensaje) {
        controladorMensajes.onConectarse(mensaje);
    }

    @Override
    public void onCrearSala(Mensaje mensaje) {
        controladorMensajes.onCrearSala(mensaje);
    }

    @Override
    public void onUnirseSala(Mensaje mensaje) {
       controladorMensajes.onUnirseSala(mensaje);
    }

    @Override
    public void onPasarOpciones(Mensaje mensaje) {
        controladorMensajes.onPasarOpciones(mensaje);
    }

    @Override
    public void onPasarJugadores(Mensaje mensaje) {
        controladorMensajes.onPasarJugadores(mensaje);
    }
    
    @Override
    public void onPasarCambios(Mensaje mensaje) {
       controladorMensajes.onPasarCambios(mensaje);
    }

    @Override
    public void onJugadorSale(Mensaje mensaje) {
        controladorMensajes.onJugadorSale(mensaje);
    }
}
