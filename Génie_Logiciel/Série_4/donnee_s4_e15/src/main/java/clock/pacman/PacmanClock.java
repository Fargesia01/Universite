package clock.pacman;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;
import java.awt.Dimension;

/**
 *
 * @author Cyril Michel, adapted by Pascal Gremaud
 */
public class PacmanClock extends ClockFrame{
    private static final long serialVersionUID = 3256723966037931316L;
    
    public PacmanClock(ClockTimer timer) {
        super(timer);
    }

    /**
     * Creates a new instance of <code>PacmanClock</code> that observes the 
     * given clock timer.
     */
    protected ClockPanel createClockPanel() {
        PacmanClockPanel clockPanel = new PacmanClockPanel();
        clockPanel.setPreferredSize(new Dimension(510, 300));
        return clockPanel;
    }

    /**
     * Implements the primitive operation for setting the frame's title.
     */
    protected void setFrameTitle() {
        setTitle("Pacman Clock");
    }
    
}
