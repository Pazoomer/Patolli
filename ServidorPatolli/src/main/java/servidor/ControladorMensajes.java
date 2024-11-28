package servidor;

import cliente.CuerpoMensaje;
import cliente.HiloCliente;
import cliente.IObservador;
import cliente.Mensaje;
import cliente.PlantillaConexion;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import cliente.Observable;
import cliente.TipoMensaje;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import logger.LoggerFactory;
import logger.IPatolliLogger;

/**
 * Se ocupa de la conexión de los clientes y la comunicación entre ellos.
 * @author t1pas
 */
public class ControladorMensajes extends Observable implements PlantillaConexion, Runnable, IObservador {

    private ServerSocket server;
    private final Map<String,List<HiloCliente>> salas;
    private final String SALA_DE_ESPERA="sala de espera";
    private final int PORT=4444;
    private final Thread serverThread;
    private final ReentrantLock lock = new ReentrantLock();
    
    private final IPatolliLogger logger = LoggerFactory.getLogger(ControladorMensajes.class);
    
    public ControladorMensajes() {
        serverThread = new Thread(this);
        salas = new HashMap<>(); 
        salas.put(SALA_DE_ESPERA, new ArrayList<>()); // Inicializa la lista para la sala de espera
    }
    
    public void init() {
        try {
            server = new ServerSocket(PORT);
            serverThread.start();
        } catch (IOException ex) {
            logger.error(String.format("Error al iniciar el servidor: %s", ex.getMessage()));
        }
    }
    
    @Override
    public void run() {
        try {
            while (true) {                
                Socket clientSocket = server.accept();

                // Crear y configurar el cliente
                HiloCliente client = new HiloCliente(clientSocket);
                //client.getUser().setNombre(nombreUsuario);

                // Iniciar el hilo del cliente
                Thread connectionThread = new Thread(client);
                connectionThread.start();

                // Agregar el cliente a la sala de espera
                salas.get(SALA_DE_ESPERA).add(client);
                client.subscribe(this);
            }
        } catch (IOException ex) {
            logger.error(String.format("Error al iniciar la conexion al servidor: %s", ex.getMessage()));
        } //catch (ClassNotFoundException ex) {
           // Logger.getLogger(ControladorMensajes.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }
    /**
     * Le coloca el usuario en la sala de espera
     * @param mensaje Mensaje con el usuario
     */
    @Override
    public void onConectarse(Mensaje mensaje) {
        lock.lock();
        try {
            if (mensaje != null && mensaje.getSender() != null) {
                HiloCliente clienteDisponible = salas.get(SALA_DE_ESPERA).stream()
                        .filter(c -> c.getUser().getNombre() == null)
                        .findFirst()
                        .orElse(null);

                if (clienteDisponible != null) {
                    clienteDisponible.setUser(mensaje.getSender());
                    System.out.println("Cliente en la sala de espera: " + mensaje.getSender().getNombre());
                } else {
                    System.out.println("No hay espacio disponible para asignar el usuario en la sala de espera.");
                }
            } else {
                System.out.println("El mensaje o el que envía es null");
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void onDisconnect(Mensaje mensaje) {
        lock.lock();
        try {
            String codigoSala = mensaje.getBody().getCodigoSala();
            var user = salas.get(codigoSala).stream().filter(c -> c.getUser().equals(mensaje.getSender())).findFirst().orElse(null);
            if (user != null) {       
                salas.get(codigoSala).remove(user);
                user.disconnect();

                //Mensaje para los demas jugadores de la sala
                CuerpoMensaje cuerpoRespuesta = new CuerpoMensaje();
                cuerpoRespuesta.setJugador(mensaje.getBody().getJugador());
                TipoMensaje tipoRespuesta = TipoMensaje.JUGADOR_SALE;
                Mensaje mensajeRespuesta = new Mensaje.Builder()
                        .body(cuerpoRespuesta)
                        .messageType(tipoRespuesta)
                        .build();
                salas.get(codigoSala).forEach(cliente -> {
                    cliente.sendMessage(mensajeRespuesta);
                }
                );
                notifyObservers(mensaje);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void onUpdate(Object obj) {
        Mensaje mensaje=(Mensaje)obj;
        System.out.println("Mensaje recibido: "+mensaje.getMessageType());
        proccessMessage(mensaje);
    }

    @Override
    public void notifyObservers(Object obj) {
        observers.forEach(o -> o.onUpdate(obj));
    } 

    @Override
    public void onCrearSala(Mensaje mensaje) {
        lock.lock();
        try {
            String codigoSala = mensaje.getBody().getCodigoSala();

            if (codigoSala == null || codigoSala.isEmpty()) {
                System.out.println("El código de la sala no puede estar vacío.");
                return;
            }

            if (salas.containsKey(codigoSala)) {
                System.out.println("La sala " + codigoSala + " ya existe.");
            } else {
                salas.put(codigoSala, new ArrayList<>());
                System.out.println("Sala " + codigoSala + " creada exitosamente.");
                onUnirseSala(mensaje); // Unir al cliente a la nueva sala
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void onUnirseSala(Mensaje mensaje) {
        lock.lock();
        try {
            String codigoSala = mensaje.getBody().getCodigoSala();
            
            var user = salas.get(SALA_DE_ESPERA).stream()
                    .filter(c -> c.getUser().equals(mensaje.getSender()))
                    .findFirst()
                    .orElse(null);

            if (user == null) {
                System.out.println("El cliente no está en la sala de espera.");
                return;
            }

            if (codigoSala == null || !salas.containsKey(codigoSala)) {
                System.out.println("La sala " + codigoSala + " no existe.");
                //Mensaje para el jugador que no se pudo unir
                CuerpoMensaje cuerpoNegativo = new CuerpoMensaje();
                cuerpoNegativo.setRazonDesconexion("La sala " + codigoSala + " no existe.");
                TipoMensaje tipoRespuesta = TipoMensaje.DESCONECTARSE;
                Mensaje mensajeNegativo = new Mensaje.Builder()
                        .body(cuerpoNegativo)
                        .messageType(tipoRespuesta)
                        .build();
                salas.get(SALA_DE_ESPERA).remove(user);
                user.sendMessage(mensajeNegativo);
                user.disconnect();
                return;
            }

            // Mover al cliente a la nueva sala
            salas.get(SALA_DE_ESPERA).remove(user);
            salas.get(codigoSala).add(user);
            System.out.println("El cliente " + mensaje.getSender().getNombre() + " se unió a la sala " + codigoSala);
            
            //Mensaje para los jugador que ya estaban en la sala
            CuerpoMensaje cuerpoOtrosJugadores = new CuerpoMensaje();
            TipoMensaje tipoOtrosJugadores = TipoMensaje.UNIRSE_SALA;
            Mensaje mensajeOtrosJugadores = new Mensaje.Builder()
                    .body(cuerpoOtrosJugadores)
                    .messageType(tipoOtrosJugadores)
                    .build();
            
            //Mensaje para el jugador que se unio
            CuerpoMensaje cuerpoRespuesta = new CuerpoMensaje();
            cuerpoRespuesta.setJugadores(salas.get(codigoSala).size());
            cuerpoRespuesta.setExisteSala(true);
            TipoMensaje tipoRespuesta = TipoMensaje.PASAR_JUGADORES;
            Mensaje mensajeRespuesta = new Mensaje.Builder()
                    .body(cuerpoRespuesta)
                    .messageType(tipoRespuesta)
                    .build();
            
            //Notificar a los jugadores en la sala
            salas.get(codigoSala).forEach(cliente -> {
                if (cliente.equals(user)) {
                    cliente.sendMessage(mensajeRespuesta);
                }else{
                    cliente.sendMessage(mensajeOtrosJugadores);
                }
            });  
            
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void onPasarOpciones(Mensaje mensaje) {
        lock.lock();
        try {
            String codigoSala = mensaje.getBody().getCodigoSala();

            if (codigoSala == null) {
                System.out.println("El cliente no está en ninguna sala.");
                return;
            }

            // Enviar el mensaje a los demás jugadores en la sala
            List<HiloCliente> clientesEnSala = salas.get(codigoSala);
            clientesEnSala.forEach(cliente -> {
                if (!cliente.getUser().equals(mensaje.getSender())) {
                    cliente.sendMessage(mensaje);
                }
            });

        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void onPasarJugadores(Mensaje mensaje) {

    }

    @Override
    public void onPasarCambios(Mensaje mensaje) {
        String codigoSala = mensaje.getBody().getCodigoSala();

        if (codigoSala == null) {
            System.out.println("El cliente no está en ninguna sala.");
            return;
        }

        // Enviar el mensaje a los demás jugadores en la sala
        List<HiloCliente> clientesEnSala = salas.get(codigoSala);
        clientesEnSala.forEach(cliente -> {
            if (!cliente.getUser().equals(mensaje.getSender())) {
                cliente.sendMessage(mensaje);
            }
        });
    }

    @Override
    public void onJugadorSale(Mensaje mensaje) {
        
    }
}

