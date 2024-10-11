
package negocio;

/**
 *
 * @author t1pas
 */
public class ControlJuego implements IControlJuego {

    Juego juego=new Juego();
    
    @Override
    public int moverFicha() {
       return juego.moverFicha();
    }

    @Override
    public boolean salir() {
        return juego.salir();
    }
    
}
