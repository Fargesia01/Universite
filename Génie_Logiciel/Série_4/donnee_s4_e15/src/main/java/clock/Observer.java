package clock;

/**
 * <code>Observer</code> defines an interface for objects that should be 
 * notified of changes by a subject.
 * 
 * @author Andreas Ruppen
 */
public interface Observer {

    /**
     * Invoked by a subject when its state changed.
     */
    public void update();
}
