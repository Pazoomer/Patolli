package cliente;

import java.io.IOException;
import logger.IChatLogger;
import logger.LoggerFactory;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author 
 */
public class HiloCliente extends Observable implements Runnable, Serializable {

    private transient Socket clientSocket;
    private transient ObjectInputStream in;
    private transient ObjectOutputStream out;
    private transient IChatLogger logger = LoggerFactory.getLogger(getClass());
    
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
            logger.error(ex.getMessage());
        }
    }

    public void disconnect() {
        try {
            sendMessage(new Mensaje.Builder().sender(usuario).messageType(TipoMensaje.DESCONECTARSE).build());
            connected = false;
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
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
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }

    private void init() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }

    private void proccessMessage() throws Exception {
        while (connected) {
            Mensaje mensaje = (Mensaje) in.readObject();
            //if (mensaje.getMessageType() == TipoMensaje.CONECTARSE) {
            //    usuario = mensaje.getSender();
            //    mensaje.setMessageType(TipoMensaje.ACTUALIZAR_USUARIO);
            //}
            if (mensaje.getMessageType() == TipoMensaje.DESCONECTARSE) {
                connected = false;
                disconnect();
            }
            notifyObservers(mensaje);
        }
    }
}
