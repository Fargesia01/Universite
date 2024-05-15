package clock;

import clock.timer.ClockTimer;
import clock.util.PositionManager;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * An abstract superclass for all clock frames. The creation of the clock panel 
 * is deferred to the subclasses.
 * 
 * @author Andreas Ruppen
 */
public abstract class ClockFrame extends JFrame implements Observer {
    /**
	 * @uml.property  name="timer"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private ClockTimer timer;
    /** 
	 * @uml.property name="clockPanel"
	 * @uml.associationEnd multiplicity="(1 1)" aggregation="composite"
	 */
    private ClockPanel clockPanel = null;

    /**
     * Creates a new instance of <code>ClockFrame</code> that observes the
     * given clock timer.
     */
    public ClockFrame(ClockTimer timer) {
        super();
        this.timer = timer;

        // Set up the window.
        setFrameTitle();
        addWindowListener(new DetachOnClosingWindowListener());

        // Create and set up the analog clock panel.
        clockPanel = createClockPanel();

        // Add the panel to the window.
        getContentPane().add(clockPanel, BorderLayout.CENTER);

        // Set screen position.
        PositionManager.getUniqueInstance().addFrameToLayout(this);

        // Attach the analog clock to the clock timer.
        timer.attach(this);
        update();

        // Display the window.
        pack();
        setVisible(true);
    }

    /**
     * Defines an abstract factory method which creates and returns a clock
     * panel. Must be implemented by the subclasses.
     */
    protected abstract ClockPanel createClockPanel();

    /**
     * Defines an abstract primitive operation for setting the frame's title.
     * Must be implemented by the subclasses.
     */
    protected void setFrameTitle() {
        setTitle("Clock Observer (default name)");
    }

    /**
     * Updates the clock.
     */
    public void update() {
        int hour = timer.getHour();
        int minute = timer.getMinute();
        int second = timer.getSecond();
        clockPanel.displayTime(hour, minute, second);
    }

    /**
     * A window listener that detaches the clock from the timer when the window
     * is being closed.
     */
    private class DetachOnClosingWindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            timer.detach(ClockFrame.this);
            PositionManager.getUniqueInstance().removeFrameFromLayout(ClockFrame.this);
        }
    }
}
