package cliente;

import java.io.IOException;
import logger.LoggerFactory;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import logger.IPatolliLogger;

/**
 *
 * @author 
 */
public class HiloCliente extends Observable implements Runnable, Serializable {

    private transient Socket clientSocket;
    private transient ObjectInputStream in;
    private transient ObjectOutputStream out;
    private transient IPatolliLogger logger = LoggerFactory.getLogger(getClass());
    
    private Usuario usuario;
    
    private boolean connected;

    public HiloCliente(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.connected = true;
        this.usuario=new Usuario();
        init();
    }
    
    public HiloCliente(String host, int port) {
        try {
            connected = true;
            clientSocket = new Socket(host, port);
            init();
        } catch (IOException ex) {
            logger.error(String.format("Error al conectarse al servidor: %s", ex.getMessage()));
        }
    }
    
    public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void notifyObservers(Object obj) {
        observers.forEach(o -> o.onUpdate(obj));
    }

    public void sendMessage(Mensaje mensaje) {
        if (mensaje != null) {
            System.out.println("Mensaje enviado: " + mensaje.getMessageType());
        }
        try {
            out.writeObject(mensaje);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error(String.format("Error al mandar mensaje: %s",ex.getMessage()));
        }
    }

    public void disconnect() {
        try {
            connected = false;
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            logger.error(String.format("Error al desconectarse: %s",e.getMessage()));
        }
    }
    
    public Usuario getUser() {
        return usuario;
    }

    @Override
    public void run() {
        try {
            proccessMessage();
        } catch (Exception ex) {
            System.out.println("Se cerro la conexion");
        }
    }

    private void init() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error(String.format("Error al inicializar el hilo: %s",ex.getMessage()));
        }
    }

    private void proccessMessage() throws Exception {
        while (connected) {
            Mensaje mensaje = (Mensaje) in.readObject();
            if (mensaje.getMessageType() == TipoMensaje.DESCONECTARSE) {
                connected = false;
                disconnect();
            }
            notifyObservers(mensaje);
        }
    }
}
