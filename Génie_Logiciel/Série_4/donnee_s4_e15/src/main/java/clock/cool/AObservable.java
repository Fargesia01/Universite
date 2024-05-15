package clock.cool;

import java.util.List;
import java.util.Vector;

/**
 * DESIGN PATTERN : OBSERVER
 * GOAL : WHEN A SUBJECT CHANGES, OBSERVERS ARE NOTIFIED AND UPDATED
 * We can add, delete and notify all observer
 */
public abstract class AObservable {

    // Observer List
    private final List<IObserver> observers = new Vector<>();

    /**
     * Add an observer to the List
     */
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    /**
     * Delete an observer from the List
     */
    public void deleteObserver(IObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notify all observers from the List
     */
    public void notifyObservers() {
        for (IObserver o : observers) o.update();
    }


}
