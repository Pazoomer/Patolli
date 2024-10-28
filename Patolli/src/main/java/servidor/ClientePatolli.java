package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author t1pas
 */
public class ClientePatolli {

    private final String codigoSala;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientePatolli(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    public boolean conectar() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 4444);
                System.out.println("Conectado al servidor en la sala: " + codigoSala);

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                String fromServer;
                while ((fromServer = in.readLine()) != null) {

                    // Parsear los datos recibidos
                    String[] datos = fromServer.split(",");  // Suponiendo que se envían separados por comas

                    // Llamar al método recibirCambios con los datos recibidos
                    recibirCambios(datos);
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
        //Todo
        return false;
    }

    // Método que se llama cuando el servidor envía los cambios
    public void recibirCambios(String[] datos) {
        // Aquí colocarías la lógica para actualizar la pantalla con los datos recibidos
        System.out.println("Actualizando pantalla con nuevos cambios del servidor...");
    }
    
    public void jugadorSale(){
        System.out.println("Jugador salio");
    }
    
    public void unirsePartida(){
        System.out.println("Unirse a la partida");
    }
}