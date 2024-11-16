package controlarServidor;

import servidor.IServidorPatolli;
import servidor.ServidorPatolli;

/**
 *
 * @author t1pas
 */
public class SwitchServidor {

    public static void main(String[] args) {
        IServidorPatolli servidor=new ServidorPatolli();
        System.out.println(servidor.switchServidor());
    }  
}
