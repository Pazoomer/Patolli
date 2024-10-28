package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author t1pas
 */
public class ServidorPatolli {
    
    public void crearSala(String codigoSala){
        try {
            //Crea un socket para el servidor en el puerto 4444 con el codigo de la sala
            ServerSocket serverSocket = null;
            serverSocket = new ServerSocket(4444);
	    System.out.println("Sala creada");
            Socket clientSocket = null;

            //Aceptar a los jugadores
            clientSocket = serverSocket.accept();
	    System.out.println("Acepte a un nuevo jugador");

            PrintWriter out = null;
            BufferedReader in = null;

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));            

            String inputLine, outputLine;
            Protocolo kkp = new Protocolo();

            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();            
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
    }  
}
