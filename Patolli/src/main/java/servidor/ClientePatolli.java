package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import presentation.FrameInicio;

/**
 *
 * @author t1pas
 */
public class ClientePatolli {

    private final String codigoSala;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final FrameInicio frameInicio;
    
    /**
     * Constructor que inicializa el codigo de la sala
     * @param codigoSala 
     * @param frameInicio
     */
    public ClientePatolli(String codigoSala, FrameInicio frameInicio) {
        this.codigoSala = codigoSala;
        this.frameInicio = frameInicio;
    }
    /**
     * Conecta esta instancia con el servidor
     * @return Verdadero si la conexion fue exitosa
     */
    public boolean conectar() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 4444);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                String fromServer;
                while ((fromServer = in.readLine()) != null) {
                    Gson gson = new Gson();
                    JsonElement jsonElement = gson.fromJson(fromServer, JsonElement.class);
                    
                    if (jsonElement.isJsonArray()) {
                        
                        String[] datos = gson.fromJson(jsonElement, String[].class);
                        System.out.println(datos[0]);
                        switch (datos[0]) {
                            case "pasarOpciones" -> {
                                int tamaño = gson.fromJson(datos[1], Integer.class);
                                int monto = gson.fromJson(datos[2], Integer.class);
                                int fichas = gson.fromJson(datos[3], Integer.class);
                                int jugadores = gson.fromJson(datos[4], Integer.class);

                                recibirOpciones(tamaño, monto, fichas, jugadores);
                            }
                            case "recibirCambios" -> {
                                List<Integer> montoJugadores = gson.fromJson(datos[1], new TypeToken<List<Integer>>() {
                                }.getType());
                                int siguienteJugador = gson.fromJson(datos[2], Integer.class);
                                List<Integer> fichasGatoPosicion = gson.fromJson(datos[3], new TypeToken<List<Integer>>() {
                                }.getType());
                                List<Integer> fichasConchaPosicion = gson.fromJson(datos[4], new TypeToken<List<Integer>>() {
                                }.getType());
                                List<Integer> fichasPiramidePosicion = gson.fromJson(datos[5], new TypeToken<List<Integer>>() {
                                }.getType());
                                List<Integer> fichasMazorcaPosicion = gson.fromJson(datos[6], new TypeToken<List<Integer>>() {
                                }.getType());

                                recibirCambios(montoJugadores, siguienteJugador, fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion);
                            }
                            case "jugadorEntra"->{
                                int numeroJugadores = gson.fromJson(datos[1], Integer.class);
                                recibirJugadorEntra(numeroJugadores);
                            }
                            case "jugadorSale"->{
                                int numeroJugador = gson.fromJson(datos[1], Integer.class);
                                recibirJugadorSale(numeroJugador);
                            }
                            case "numeroJugadores"->{
                                int numeroJugadores = gson.fromJson(datos[1], Integer.class);
                                setNumeroJugadores(numeroJugadores);
                            }
                        }
                    } else {
                        System.out.println("Formato JSON inesperado");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        enviarCodigoSala();
        return true;
    }
    /**
     * El cliente se deconecta del servidor
     */
    public void desconectar() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Envia un mensaje al servidor
     * @param mensaje Cadena a enviar
     */
    public void enviarMensaje(String mensaje) {
        if (out != null) {
            out.println(mensaje); 
        }
    }
    /**
     * Envia el codigo de la sala al servidor para que lo coloque en la sala correcta
     * @return Verdadero si se envio con exito
     */
    public boolean enviarCodigoSala(){
        Gson gson = new Gson();
       
        String jsonCodigoSala = gson.toJson(codigoSala);
        
        String mensaje = gson.toJson(new String[]{
            "codigoSala",
            jsonCodigoSala
        });
        enviarMensaje(mensaje);
        return true;
    }
    /**
     * Notifica a todos los jugadores que un jugador salio
     *
     * @param jugador Posicion del jugador a sacar
     * @return 
     */
    public boolean enviarJugadorSale(int jugador) {
        Gson gson = new Gson();
       
        String jugadorJson = gson.toJson(jugador);
        
        String mensaje = gson.toJson(new String[]{
            "jugadorSale",
            jugadorJson
        });
        enviarMensaje(mensaje);
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
        
        enviarMensaje(mensaje);
        return true;
    }
    /**
     * Envia las opciones de la partida
     * @param tamaño Tamaño del tablero
     * @param monto Monto inicial de apuestas
     * @param fichas Fichas de los jugadores
     * @param jugadores Numero de jugadores iniciales
     * @return 
     */
    public boolean enviarOpciones(int tamaño, int monto, int fichas, int jugadores) {
        Gson gson = new Gson();

        String tamañoJson = gson.toJson(tamaño);
        String montoJson = gson.toJson(monto);
        String fichasJson = gson.toJson(fichas);
        String jugadoresJson = gson.toJson(jugadores);

        String mensaje = gson.toJson(new String[]{
            "pasarOpciones",
            tamañoJson,
            montoJson,
            fichasJson,
            jugadoresJson
        });

        enviarMensaje(mensaje);
        
        return true;
    }
    /**
     * Recibe los cambios de un jugador y los manda a todos los otros jugadores
     *
     * @param montoJugadores
     * @param siguienteJugador
     * @param fichasGatoPosicion
     * @param fichasConchaPosicion
     * @param fichasPiramidePosicion
     * @param fichasMazorcaPosicion
     */
    public void recibirCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
            frameInicio.recibirCambios(montoJugadores, siguienteJugador, fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion);
    }
    /**
     * Recibe las opciones de la partida
     * @param tamaño Tamaño del tablero
     * @param monto Monto inicial de apuestas
     * @param fichas Fichas de los jugadores
     * @param jugadores Numero de jugadores iniciales
     */
    public void recibirOpciones(int tamaño, int monto, int fichas, int jugadores) {
            frameInicio.recibirOpciones(tamaño, monto, fichas, jugadores);
    }
    /**
     * Recibe la notificacion de que un jugador entro
     *
     * @param numeroJugadores
     */
    public void recibirJugadorEntra(int numeroJugadores) {
            frameInicio.jugadorEntra(numeroJugadores);
    }
    /**
     * Recibe la notificacion de que un jugador salio
     *
     * @param numeroJugadores
     */
    public void recibirJugadorSale(int numeroJugadores) {
        frameInicio.recibirJugadorSale(numeroJugadores);
    }
    /**
     * Devuelve el numero de jugadores conectados al servidor
     */
    public void getNumeroJugadores() {
        Gson gson = new Gson();

        String mensaje = gson.toJson(new String[]{
            "numeroJugadores"
        });
        enviarMensaje(mensaje);
    }
    /**
     * Devuelve el numero de jugadores conectados al servidor
     */
    public void setNumeroJugadores(int numeroJugadores) {
        frameInicio.recibirNumeroJugadores(numeroJugadores);
    }
}
