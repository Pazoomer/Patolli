package cliente;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public abstract class Observable {

    protected List<IObservador> observers = new ArrayList<>();

    public void subscribe(IObservador observer) {
        this.observers.add(observer);
    }

    public void unsubscribe(IObservador observer) {
        this.observers.remove(observer);
    }

    public abstract void notifyObservers(Object obj);   
}
