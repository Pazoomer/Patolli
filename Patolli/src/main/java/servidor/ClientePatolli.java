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
import javax.swing.JFrame;
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
    private final JFrame frameInicio;
    
    /**
     * Constructor que inicializa el codigo de la sala
     * @param codigoSala 
     * @param frameInicio 
     */
    public ClientePatolli(String codigoSala, JFrame frameInicio) {
        this.codigoSala = codigoSala;
        this.frameInicio=frameInicio;
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
                        switch (datos[0]) {
                            case "pasarOpciones" -> {
                                int tamaño = gson.fromJson(datos[1], Integer.class);
                                int monto = gson.fromJson(datos[2], Integer.class);
                                int fichas = gson.fromJson(datos[3], Integer.class);
                                int jugadores = gson.fromJson(datos[4], Integer.class);

                                pasarOpciones(tamaño, monto, fichas, jugadores);
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
                        }
                    } else if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()) {
                        String dato = jsonElement.getAsString();
                        switch (dato) {
                            case "jugadorSale" -> {
                                jugadorSale();
                            }
                            case "jugadorEntra" -> {
                                jugadorEntra();
                            }
                        }
                    } else {
                        System.out.println("Formato JSON inesperado");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Cerrar socket y flujos al finalizar
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
        jugadorEntra();
        return true;
    }

    /**
     * Recibe los cambios de un jugador y los manda a todos los otros jugadores
     * @param montoJugadores
     * @param siguienteJugador
     * @param fichasGatoPosicion
     * @param fichasConchaPosicion
     * @param fichasPiramidePosicion
     * @param fichasMazorcaPosicion
     */
    public void recibirCambios(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion) {
        
        System.out.println("Monto de jugadores");
        for (Integer integer : montoJugadores) {
            System.out.println("Monto de jugador: " + integer);
        }
        System.out.println("Siguiente Jugador: " + siguienteJugador);

        System.out.println("Fichas Gato Posicion");
        for (Integer integer : fichasGatoPosicion) {
            System.out.println("Posicion: " + integer);
        }
        System.out.println("Fichas Concha Posicion");
        for (Integer integer : fichasConchaPosicion) {
            System.out.println("Posicion: " + integer);
        }
        System.out.println("Fichas Piramide Posicion");
        for (Integer integer : fichasPiramidePosicion) {
            System.out.println("Posicion: " + integer);
        }
        System.out.println("Fichas Mazorca Posicion ");
        for (Integer integer : fichasMazorcaPosicion) {
            System.out.println("Posicion: " + integer);
        }
        if (frameInicio instanceof FrameInicio inicio) {
            inicio.recibirCambios(montoJugadores, siguienteJugador, fichasGatoPosicion, fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion);
        }
    }
    /**
     * 
     * @param tamaño
     * @param monto
     * @param fichas
     * @param jugadores 
     */
    public void pasarOpciones(int tamaño, int monto, int fichas, int jugadores) {
        System.out.println("Tamaño: " + tamaño);
        System.out.println("Monto: " + monto);
        System.out.println("Fichas: " + fichas);
        System.out.println("Jugadores: " + jugadores);
        if (frameInicio instanceof FrameInicio inicio) {
            inicio.recibirOpciones(tamaño, monto, fichas, jugadores);
        }
    }
    /**
     * 
     */
    public void jugadorEntra(){
        System.out.println("Jugador entro");
        if (frameInicio instanceof FrameInicio inicio) {
            inicio.jugadorEntra();
        }
    }
    /**
     * Notifica la salida de un jugador
     */
    public void jugadorSale(){
        System.out.println("Jugador salio");
    }
}