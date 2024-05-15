package clock;

import javax.swing.JPanel;

/**
 * An abstract superclass for all clock panels that defines an abstract method
 * to set the clock's time.
 * 
 * @author Andreas Ruppen
 */
public abstract class ClockPanel extends JPanel {

    /**
     * Creates a new instance of <code>ClockPanel</code>.
     */
    public ClockPanel() {
        super();
    }

    /**
     * Defines an abstract method for setting the clock's time. Must be
     * implemented by the subclasses.
     */
    public abstract void displayTime(int hour, int minute, int second);
}
