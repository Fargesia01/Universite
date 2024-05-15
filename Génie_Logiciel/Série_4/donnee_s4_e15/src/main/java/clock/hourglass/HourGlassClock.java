package clock.hourglass;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;
import java.awt.Dimension;

/**
 *
 * @author Matteo Badaracco & Zeno Bardelli, adapted by Pascal Gremaud
 */
public class HourGlassClock extends ClockFrame {
    private static final long serialVersionUID = 3256723966037931316L;

    /**
     * Creates a new instance of <code>HourGlassClock</code> that observes the 
     * given clock timer.
     */
    public HourGlassClock(ClockTimer timer) {
        super(timer);
    }

    /**
     * Implements the factory method to return an instance of a clock panel.
     */
    protected ClockPanel createClockPanel() {
        HourGlassClockPanel clockPanel = new HourGlassClockPanel();
        clockPanel.setPreferredSize(new Dimension(440, 280));
        return clockPanel;
    }

    /**
     * Implements the primitive operation for setting the frame's title.
     */
    protected void setFrameTitle() {
        setTitle("HourGlass Clock");
    }
    
}
