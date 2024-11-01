package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

/**
 *
 * @author t1pas
 */
public class ServidorPatolli {

    private final List<Socket> clientesConectados = new ArrayList<>();  // Lista para almacenar los clientes conectados
    private ServerSocket serverSocket;
    private final String codigoSala;

    /**
     * Constructor con el codigo de sala
     * @param codigoSala 
     */
    public ServidorPatolli(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    /**
     * Crea un nuevo servidor
     */
    public void crearServidor() {
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Servidor creado, esperando jugadores...");

            new Thread(() -> {
                while (true) {
                    try {
                        Socket cliente = serverSocket.accept();
                        clientesConectados.add(cliente);  
                        jugadorEntra();

                        new Thread(() -> manejarCliente(cliente)).start();
                    } catch (IOException e) {
                        System.err.println("Error al aceptar la conexión del cliente: " + e.getMessage());
                    }
                }
            }).start();

        } catch (IOException e) {
            System.err.println("No se pudo crear el servidor en el puerto 4444: " + e.getMessage());
        }
    }
    
    /**
     * Detiene el servidor y desconecta a todos los jugadores
     */
    public void detenerServidor() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Servidor detenido.");
            }

            for (Socket cliente : clientesConectados) {
                if (cliente != null && !cliente.isClosed()) {
                    cliente.close();
                    System.out.println("Conexión con cliente cerrada.");
                }
            }
            clientesConectados.clear(); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Maneja
     * @param cliente 
     */
    private void manejarCliente(Socket cliente) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibido del cliente: " + inputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Notifica a todos los jugadores que un jugador entro
     * @return Entero con el numero de jugadores
     */
    public int jugadorEntra() {

        int jugadoresConectados = clientesConectados.size();

        Gson gson = new Gson();
        String jugadoresConectadosJson = gson.toJson(jugadoresConectados);

        String mensaje = gson.toJson(new String[]{
            "jugadorEntra",
            jugadoresConectadosJson
        });
        
        for (Socket cliente : clientesConectados) {
            try {
                PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                out.println(mensaje);  
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Numero de jugador:"+ clientesConectados.size());
        return jugadoresConectados-1;
    }
    /**
     * 
     * @param jugador
     * @return 
     */
    public boolean jugadorSale(int jugador){
        String mensaje = "jugadorSale";

        for (Socket cliente : clientesConectados) {
            try {
                PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                out.println(mensaje); 
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo para actualizar a los clientes conectados
     * @param siguienteJugador
     * @param montoJugadores
     * @param fichasGatoPosicion
     * @param fichasConchaPosicion
     * @param fichasPiramidePosicion
     * @param fichasMazorcaPosicion
     * @return 
     */
    public boolean enviarCambiosAClientes(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {

        Gson gson = new Gson();
       
        String montoJugadoresJson = gson.toJson(montoJugadores);
        String jugadorJson = gson.toJson(siguienteJugador);
        String fichasGatoPosicionJson = gson.toJson(fichasGatoPosicion);
        String fichasConchaPosicionJson = gson.toJson(fichasConchaPosicion);
        String fichasPiramidePosicionJson = gson.toJson(fichasPiramidePosicion);
        String fichasMazorcaPosicionJson = gson.toJson(fichasMazorcaPosicion);
        
        String mensaje = gson.toJson(new String[]{
            "recibirCambios",
            montoJugadoresJson,
            jugadorJson,
            fichasGatoPosicionJson,
            fichasConchaPosicionJson,
            fichasPiramidePosicionJson,
            fichasMazorcaPosicionJson
        });
        System.out.println("A punto de subir cambios");
        //TODO: Los jugadores invitados no estan enviando los cambios, el host los esta enviando dos veces
        //Ninguno lo esta recibiendo
        for (Socket cliente : clientesConectados) {
            try {
                PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                out.println(mensaje); 
                System.out.println("Cambios enviados");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * @param tamaño
     * @param monto
     * @param fichas
     * @param jugadores
     * @return 
     */
    public boolean pasarOpciones(int tamaño, int monto, int fichas,int jugadores){
        Gson gson = new Gson();
       
        String tamañoJson = gson.toJson(tamaño);
        String montoJson = gson.toJson(monto);
        String fichasJson = gson.toJson(fichas);
        String jugadoresJson=gson.toJson(jugadores);

        String mensaje = gson.toJson(new String[]{
            "pasarOpciones",
            tamañoJson,
            montoJson,
            fichasJson,
            jugadoresJson
        });

        for (Socket cliente : clientesConectados) {
            try {
                PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                out.println(mensaje);  
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
}