package clock.roman;

import java.awt.Dimension;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

/**
 * An roman digital clock observing a clock timer.
 * 
 * @author Julien Thomet, adapted by Andreas Ruppen
 */
public class RomanClock extends ClockFrame {
	
    private static final long serialVersionUID = 3258408447900069937L;

    /**
     * Creates a new instance of <code>RomanClock</code> that observes the 
     * given clock timer.
     */
    public RomanClock(ClockTimer timer) {   
    	super(timer);
    }
    
    /**
     * Implements the factory method to return an instance of a clock panel.
     */
	protected ClockPanel createClockPanel()
	{
		 RomanClockPanel clockPanel = new RomanClockPanel();
	     clockPanel.setPreferredSize(new Dimension(200, 80));
	     return clockPanel;
	}

    /**
     * Implements the primitive operation for setting the frame's title.
     */
	protected void setFrameTitle()
	{
		setTitle("Roman Clock");
	}
}

