package servidor;

import cliente.CuerpoMensaje;
import cliente.HiloCliente;
import cliente.Mensaje;
import cliente.TipoMensaje;
import cliente.Usuario;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;
import logger.IChatLogger;
import logger.LoggerFactory;
import cliente.PlantillaConexion;
import cliente.IConexion;
import cliente.IObservador;
import java.io.IOException;
import presentation.FrameInicio;
import usuarioUtils.UsuarioUtils;

/**
 *
 * @author 
 */
public class ConexionCliente implements PlantillaConexion, IObservador, IConexion, Serializable {

    private HiloCliente cliente; 
    private final Usuario usuario;
    private final int PORT=4444;
    private final String HOST="localhost";
    private final FrameInicio frameInicio;

    private final IChatLogger logger = LoggerFactory.getLogger(ConexionCliente.class);
    
    ReentrantLock lock = new ReentrantLock();

    public ConexionCliente(FrameInicio frameInicio) {
        this.frameInicio=frameInicio;
        usuario = new Usuario();
        usuario.setNombre(UsuarioUtils.generarNombreAletorio(8));
    }

    @Override
    public void init(){
        try {
            Socket clientSocket = new Socket(HOST, PORT);
            
            cliente = new HiloCliente(clientSocket);
            cliente.setUser(usuario);
            
            Thread patolliThread = new Thread(cliente);
            patolliThread.start();
            
            cliente.subscribe(this); 
            
            CuerpoMensaje cuerpo = new CuerpoMensaje();
            cuerpo.setCodigoSala(usuario.getNombre());

            var connectionMessage = new Mensaje.Builder().sender(usuario).messageType(TipoMensaje.CONECTARSE).body(cuerpo).build();
            sendMessage(connectionMessage);
            
            System.out.println("Usuario conectado");
        } catch (IOException ex) {
            logger.error(String.format(" %s : el servidor no responde", ex.getMessage()));
            frameInicio.volverInicio=true;
        }
    }

    @Override
    public void onUpdate(Object obj) {
        Mensaje mensaje=(Mensaje)obj;
        System.out.println("Mensaje recibido: "+ mensaje.getMessageType());
        proccessMessage(mensaje);
    }

    @Override
    public void sendMessage(Mensaje mensaje) {
        if (cliente!=null) {
            mensaje.setSender(usuario);
            cliente.sendMessage(mensaje);
        }
    }

    @Override
    public void disconnect() {
        if(cliente!=null){
           cliente.disconnect(); 
        }
    }

    @Override
    public void onDisconnect(Mensaje mensaje) {
        frameInicio.onDesconectarse(mensaje);
    }
    
    @Override
    public void onConectarse(Mensaje mensaje) {
        frameInicio.onConectarse(mensaje);
    }
    
    @Override
    public void onUnirseSala(Mensaje mensaje) {
        frameInicio.onUnirseSala(mensaje);
    }
    
    @Override
    public void onCrearSala(Mensaje mensaje) {
        frameInicio.onCrearSala(mensaje);
    }
    
    @Override
    public void onPasarOpciones(Mensaje mensaje) {
        frameInicio.onPasarOpciones(mensaje);
    }
    
    @Override
    public void onPasarJugadores(Mensaje mensaje) {
        frameInicio.onPasarJugadores(mensaje);
    }

    @Override
    public void onPasarCambios(Mensaje mensaje) {
        frameInicio.onPasarCambios(mensaje);
    }

    @Override
    public void onJugadorSale(Mensaje mensaje) {
        frameInicio.onJugadorSale(mensaje);
    }
}
