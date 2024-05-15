package clock.digitalLCD;

import java.awt.Dimension;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

/**
 * A digital LCD clock observing a clock timer.
 * 
 * @author jean-roch, adapted by Pascal Gremaud
 */
public class DigitalClockLCD extends ClockFrame {
	
    private static final long serialVersionUID = 3258408447900069937L;

    public DigitalClockLCD(ClockTimer timer) {   
    	super(timer);
    }
    
    /**
     * Implements the factory method to return an instance of a clock panel.
     */
	protected ClockPanel createClockPanel()
	{
		 DigitalClockPanelLCD clockPanel = new DigitalClockPanelLCD();
	     clockPanel.setPreferredSize(new Dimension(200, 80));
	     return clockPanel;
	}

    /**
     * Implements the primitive operation for setting the frame's title.
     */
	protected void setFrameTitle()
	{
		setTitle("Digital LCD Clock");
	}
}

