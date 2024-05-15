package clock;

import java.util.Vector;

/**
 * <code>Subject</code> is a class for attaching and detaching observers.
 * 
 * @author Andreas Ruppen
 */
public abstract class Subject {
    /** 
	 * @uml.property name="observers"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" aggregation="shared"
	 */
    private Vector<Observer> observers = new Vector<Observer>();

    /**
     * Attaches a new observer to this subject.
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * Detaches am observer from this subject.
     */
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers that are attached to this subject.
     */
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
