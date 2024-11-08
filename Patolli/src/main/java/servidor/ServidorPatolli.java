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
        System.out.println("SOY EL SERVIDOR");
        try {
            serverSocket = new ServerSocket(4444);
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
        //TODO: NO funciona
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
     * Escucha los cambios del cliente y manda a que se procesen
     * @param cliente Socket de este cliente
     */
    private void manejarCliente(Socket cliente) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                procesarMensaje(inputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Procesa los mensajes recibidos de los clientes y ejecuta la acción
     * correspondiente.
     * @param mensaje Cadena que representa la accion a tomar
     */
    private void procesarMensaje(String mensaje) {
        Gson gson = new Gson();
        String[] partesMensaje = gson.fromJson(mensaje, String[].class);

        String comando = partesMensaje[0];
        
        switch (comando) {
            case "recibirCambios" -> {
                List<Integer> montoJugadores = gson.fromJson(partesMensaje[1], List.class);
                int siguienteJugador = Integer.parseInt(partesMensaje[2]);
                List<Integer> fichasGatoPosicion = gson.fromJson(partesMensaje[3], List.class);
                List<Integer> fichasConchaPosicion = gson.fromJson(partesMensaje[4], List.class);
                List<Integer> fichasPiramidePosicion = gson.fromJson(partesMensaje[5], List.class);
                List<Integer> fichasMazorcaPosicion = gson.fromJson(partesMensaje[6], List.class);

                enviarCambiosAClientes(montoJugadores, siguienteJugador, fichasGatoPosicion,
                        fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion);
            }
            case "jugadorSale"->{
                int jugadorSale = Integer.parseInt(partesMensaje[1]);
                enviarJugadorSale(jugadorSale);
            }
            case "pasarOpciones"->{
                int tamaño = Integer.parseInt(partesMensaje[1]);
                int monto = Integer.parseInt(partesMensaje[2]);
                int fichas = Integer.parseInt(partesMensaje[3]);
                int jugadores = Integer.parseInt(partesMensaje[4]);
                enviarOpciones(tamaño, monto, fichas, jugadores);
            }
            default ->
                System.out.println("Comando desconocido: " + comando);
        }
    }

    /**
     * Notifica a todos los jugadores que un jugador entro
     *
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
        return jugadoresConectados - 1;
    }

    /**
     * Notifica a todos los jugadores que un jugador salio
     *
     * @param jugador Posicion del jugador a sacar
     * @return Verdad si salio el jugador con exito
     */
    public boolean enviarJugadorSale(int jugador) {
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
     * Envia las opciones del juego a todos los clientes
     * @param tamaño Tamaño del tablero
     * @param monto Monto de apuestas inicial
     * @param fichas Fichas por jugador
     * @param jugadores Numero de jugadores inicial
     * @return Verdadero si los envio con exito 
     */
    public boolean enviarOpciones(int tamaño, int monto, int fichas,int jugadores){
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