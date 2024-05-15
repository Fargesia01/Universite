package clock.bargraph;


import java.awt.Dimension;


import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

/**
 * A digital clock observing a clock timer.
 * 
 * @author Andreas Ruppen
 */
public class BarGraphClock extends ClockFrame {
	private static final long serialVersionUID = 3256723966037931316L;

    /**
     * Creates a new instance of <code>DigitalClock</code> that observes the 
     * given clock timer.
     */
    public BarGraphClock(ClockTimer timer) {
        super(timer);
    }

    /**
     * Implements the factory method to return an instance of a clock panel.
     */
    protected ClockPanel createClockPanel() {
    	BarGraphClockPanel clockPanel = new BarGraphClockPanel();
        clockPanel.setPreferredSize(new Dimension(200, 180));
        return clockPanel;
    }

    /**
     * Implements the primitive operation for setting the frame's title.
     */
    protected void setFrameTitle() {
        setTitle("Bars Clock");
    }
}