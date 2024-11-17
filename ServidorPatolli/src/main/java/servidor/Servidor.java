package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author t1pas
 */
public class Servidor {

    private final Map<String, List<Socket>> salas = new HashMap<>();  // Diccionario con código de sala como clave y lista de sockets como valor
    private ServerSocket serverSocket;
    
    /**
     * Enciende o apaga el servidor
     * @return Cadena de texto con el procedimiento realizado o un mensaje de error
     */
    public String switchServidor() {
        if(estaEncendido()){
            return this.detenerServidor();
        }else{
            return this.iniciarServidor();
        }
    }
    /**
     * Verifica si el servidor esta corriendo
     * @return Verdadero si el servidor esta encendido
     */
    public boolean estaEncendido() {
        try (Socket socket = new Socket("localhost", 4444)) {
            return true;  
        } catch (IOException e) {
            return false;
        }
    }
    /**
     * Crea un nuevo servidor
     * @return Cadena de texto con el resultado del metodo
     */
    public String iniciarServidor() {
        try {//TODO: cuando un jugador se desconecta por netbeans, da error
            serverSocket = new ServerSocket(4444);
            new Thread(() -> {
                while (true) {
                    try {
                        Socket cliente = serverSocket.accept();
                        System.out.println("Jugador se añadio al servidor");
                        new Thread(() -> manejarCliente(cliente)).start();
                    } catch (IOException e) {
                        System.err.println("Error al aceptar la conexión del cliente: " + e.getMessage());
                    }
                }
            }).start();

        } catch (IOException e) {
            return "No se pudo crear el servidor en el puerto 4444:"+ e.getMessage() ;
        }
        return "Servidor encendido con exito";
    }
    /**
     * Detiene el servidor y desconecta a todos los jugadores
     * @return Cadena de texto con el resultado del metodo
     */
    public String detenerServidor() {
         try {
            // Cerrar las conexiones de todos los clientes de cada sala
            for (Map.Entry<String, List<Socket>> entry : salas.entrySet()) {
                List<Socket> clientes = entry.getValue();
                for (Socket cliente : clientes) {
                    if (cliente != null && !cliente.isClosed()) {
                        cliente.close();
                    }
                }
            }

            // Cerrar el ServerSocket para detener la escucha de nuevas conexiones
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            salas.clear();
            return "Servidor detenido correctamente.";
        } catch (IOException e) {
            return "Error al detener el servidor: " + e.getMessage();
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
                procesarMensaje(inputLine, cliente);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Procesa los mensajes recibidos de los clientes y ejecuta la acción
     * correspondiente.
     *
     * @param mensaje Cadena que representa la accion a tomar
     */
    private void procesarMensaje(String mensaje, Socket cliente) {
        Gson gson = new Gson();
        String[] partesMensaje = gson.fromJson(mensaje, String[].class);
        String comando = partesMensaje[0];
        System.out.println("");
        System.out.println(comando);
        switch (comando) {
            case "crearSala"->{
                String codigoSalaRecibido = partesMensaje[1];
                crearSala(codigoSalaRecibido);
                asignarSala(codigoSalaRecibido, cliente);
            }
            case "codigoSala"->{
                String codigoSalaRecibido = partesMensaje[1];
                asignarSala(codigoSalaRecibido, cliente);
            }
            case "recibirCambios" -> {
                List<Integer> montoJugadores = gson.fromJson(partesMensaje[1], List.class);
                int siguienteJugador = Integer.parseInt(partesMensaje[2]);
                List<Integer> fichasGatoPosicion = gson.fromJson(partesMensaje[3], List.class);
                List<Integer> fichasConchaPosicion = gson.fromJson(partesMensaje[4], List.class);
                List<Integer> fichasPiramidePosicion = gson.fromJson(partesMensaje[5], List.class);
                List<Integer> fichasMazorcaPosicion = gson.fromJson(partesMensaje[6], List.class);

                enviarCambiosAClientes(montoJugadores, siguienteJugador, fichasGatoPosicion,
                        fichasConchaPosicion, fichasPiramidePosicion, fichasMazorcaPosicion, cliente);
            }
            case "jugadorSale"->{
                int jugadorSale = Integer.parseInt(partesMensaje[1]);
                enviarJugadorSale(jugadorSale, cliente);
            }
            case "pasarOpciones"->{
                int tamaño = Integer.parseInt(partesMensaje[1]);
                int monto = Integer.parseInt(partesMensaje[2]);
                int fichas = Integer.parseInt(partesMensaje[3]);
                int jugadores = Integer.parseInt(partesMensaje[4]);
                enviarOpciones(tamaño, monto, fichas, jugadores, cliente);
            }
            case "numeroJugadores"->{
                enviarNumeroJugadores(cliente);
            }
            default ->
                System.out.println("Comando desconocido: " + comando);
        }
    }
    /**
     * Envia el numero de jugadores conectados de la sala al cliente del pa
     * @param clienteParametro
     */
    public boolean enviarNumeroJugadores(Socket clienteParametro){
        List<Socket> sala = obtenerSocketsDeSala(clienteParametro);
        
        Gson gson = new Gson();
        String numeroJugadores = gson.toJson(sala.size());
         
        String mensaje = gson.toJson(new String[]{
            "numeroJugadores",
            numeroJugadores
        });

        try {
            PrintWriter out = new PrintWriter(clienteParametro.getOutputStream(), true);
            out.println(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * Crea una sala con el codigo de la sala
     *
     * @param codigoSala
     */
    public void crearSala(String codigoSala) {
        if (salas.putIfAbsent(codigoSala, new ArrayList<>()) != null) {
            System.out.println("La sala " + codigoSala + " ya existe.");
        }
        System.out.println("Sala creada con el codigo: " + codigoSala);
    }

    /**
     * Asigna el jugador a la sala, si no existe la sala, no hace nada
     *
     * @param codigoSala
     * @param cliente
     */
    public void asignarSala(String codigoSala, Socket cliente) {
        boolean boolResultado = salas.containsKey(codigoSala);
        Gson gson = new Gson();

        // Crear el mensaje JSON para enviar al cliente
        String resultado = gson.toJson(boolResultado);
        String mensaje = gson.toJson(new String[]{
            "ResultadoExisteSala",
            resultado
        });

        try {
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
            out.println(mensaje); // Enviar mensaje al cliente

            if (!boolResultado) {
                // Si la sala no existe, cerrar la conexión con el cliente
                System.out.println("La sala " + codigoSala + " no existe. Cerrando conexión con el cliente...");
                cliente.close();
                return; // Salir del método
            }

            // Si la sala existe, continuar con la lógica
            List<Socket> jugadores = salas.get(codigoSala);
            if (!jugadores.contains(cliente)) {
                jugadores.add(cliente);
                jugadorEntra(cliente);
                System.out.println("Jugador asignado a la sala: " + codigoSala);
            } else {
                System.out.println("El cliente ya está en la sala " + codigoSala);
            }
        } catch (IOException e) {
            System.err.println("Error al manejar la conexión del cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Notifica a todos los jugadores que un jugador entro
     *
     * @param clienteParametro
     * @return Entero con el numero de jugadores
     */
    public int jugadorEntra(Socket clienteParametro) {
        List<Socket> sala = obtenerSocketsDeSala(clienteParametro);
        int jugadoresConectados = sala.size()+1; //1 Más por que se cuenta el cliente que manda el cambio

        Gson gson = new Gson();
        String jugadoresConectadosJson = gson.toJson(jugadoresConectados); 

        String mensaje = gson.toJson(new String[]{
            "jugadorEntra",
            jugadoresConectadosJson
        });

        for (Socket cliente : sala) {
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
     * @param clienteParametro
     * @return Verdad si salio el jugador con exito
     */
    public boolean enviarJugadorSale(int jugador, Socket clienteParametro) {
        List<Socket> sala = obtenerSocketsDeSala(clienteParametro);
        Gson gson = new Gson();
        String jugadorSaleJson = gson.toJson(jugador); 
        
        String mensaje = gson.toJson(new String[]{
            "jugadorSale",
            jugadorSaleJson
        });

        for (Socket cliente : sala) {
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
     * @param clienteParametro
     * @return 
     */
    public boolean enviarCambiosAClientes(List<Integer> montoJugadores, int siguienteJugador, List<Integer> fichasGatoPosicion,
            List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion, List<Integer> fichasMazorcaPosicion, Socket clienteParametro) {

        List<Socket> sala = obtenerSocketsDeSala(clienteParametro);
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
        for (Socket cliente : sala) {
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
     * @param clienteParametro
     * @return Verdadero si los envio con exito 
     */
    public boolean enviarOpciones(int tamaño, int monto, int fichas,int jugadores, Socket clienteParametro){
        List<Socket> sala = obtenerSocketsDeSala(clienteParametro);
        
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

        for (Socket cliente : sala) {
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
     * Obtiene todos los sockets de la misma sala que el socket especificado.
     * @param socket El socket específico del que deseas obtener todos los demás de la misma sala.
     * @return La lista de sockets de la misma sala, o null si el socket no pertenece a ninguna sala.
     */
    public List<Socket> obtenerSocketsDeSala(Socket socket) {
        for (List<Socket> clientes : salas.values()) {
            if (clientes.contains(socket)) {
                List<Socket> clientesExcluyendo = new ArrayList<>(clientes);
                clientesExcluyendo.remove(socket);
                return clientesExcluyendo;
            }
        }
        return null;
    }
}
