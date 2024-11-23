package controlarServidor;

import servidor.IServidorPatolli;
import servidor.ServidorPatolli;

/**
 *
 * @author t1pas
 */
public class IniciarServidor {

    public static void main(String[] args) {
        IServidorPatolli servidor=new ServidorPatolli();
        servidor.initServidor();
    }  
}
