package servidor;

/**
 *
 * @author t1pas
 */
public class ServidorPatolli implements IServidorPatolli{

    Servidor servidor;
    
    public ServidorPatolli(){
        servidor=new Servidor();
    }
    
    @Override
    public String switchServidor() {
        return servidor.switchServidor();
    }
    
}
