package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author t1pas
 */
public class ServidorPatolli {

    private final List<Socket> clientesConectados = new ArrayList<>();  // Lista para almacenar los clientes conectados
    private ServerSocket serverSocket;
    private final String codigoSala;

    public ServidorPatolli(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    public void crearServidor() {
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Servidor creado, esperando jugadores...");

            // Hilo para aceptar múltiples clientes
            new Thread(() -> {
                while (true) {
                    try {
                        Socket cliente = serverSocket.accept();
                        clientesConectados.add(cliente);  // Añadir cliente a la lista
                        System.out.println("Nuevo jugador conectado.");

                        // Manejar la comunicación con este cliente en un hilo separado
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
    
    public void detenerServidor() {
        try {
            // Cerrar el ServerSocket
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Servidor detenido.");
            }

            // Cerrar todos los sockets de los clientes
            for (Socket cliente : clientesConectados) {
                if (cliente != null && !cliente.isClosed()) {
                    cliente.close();
                    System.out.println("Conexión con cliente cerrada.");
                }
            }
            clientesConectados.clear(); // Limpiar la lista de clientes conectados

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void manejarCliente(Socket cliente) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibido del cliente: " + inputLine);
                // Aquí puedes procesar los mensajes entrantes si lo necesitas
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean jugadorSale(int jugador){
        // Aquí puedes convertir los objetos a String o JSON antes de enviarlos
        String mensaje = "Jugador Sale";  // Este mensaje debe contener los datos actualizados

        for (Socket cliente : clientesConectados) {
            try {
                PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                out.println(mensaje);  // Enviar los cambios a cada cliente
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         //TODO
        return false;
    }

    // Método para actualizar cambios a todos los clientes conectados
    public boolean enviarCambiosAClientes(List<JLabel> casillas, List<Integer> montoJugadores, int jugador,
                                       List<JLabel> fichasGato, List<JLabel> fichasConcha, List<JLabel> fichasPiramide,
                                       List<JLabel> fichasMazorca, List<Integer> fichasGatoPosicion,
                                       List<Integer> fichasConchaPosicion, List<Integer> fichasPiramidePosicion,
                                       List<Integer> fichasMazorcaPosicion) {
        // Aquí puedes convertir los objetos a String o JSON antes de enviarlos
        String mensaje = "Cambios actualizados";  // Este mensaje debe contener los datos actualizados

        for (Socket cliente : clientesConectados) {
            try {
                PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                out.println(mensaje);  // Enviar los cambios a cada cliente
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //TODO
        return false;
    }
}