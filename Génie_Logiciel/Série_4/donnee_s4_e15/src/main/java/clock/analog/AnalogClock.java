package clock.analog;

import java.awt.Dimension;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

/**
 * An analog clock observing a clock timer.
 * 
 * @author Andreas Ruppen
 */
public class AnalogClock extends ClockFrame {
	private static final long serialVersionUID = 3258408447900069937L;

    /**
     * Creates a new instance of <code>AnalogClock</code> that observes the
     * given clock timer.
     */
    public AnalogClock(ClockTimer timer) {
        super(timer);
    }

    /**
     * Implements the factory method to return an instance of a clock panel.
     */
    protected ClockPanel createClockPanel() {
        AnalogClockPanel clockPanel = new AnalogClockPanel();
        clockPanel.setPreferredSize(new Dimension(200, 200));
        return clockPanel;
    }

    /**
     * Implements the primitive operation for setting the frame's title.
     */
    protected void setFrameTitle() {
        setTitle("Analog Clock");
    }
}