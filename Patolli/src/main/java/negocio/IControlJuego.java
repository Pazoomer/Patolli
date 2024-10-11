
package negocio;

import datos.Partida;

/**
 *
 * @author t1pas
 */
public interface IControlJuego {
    Partida partida=new Partida();
    int moverFicha();
    boolean salir();
}
