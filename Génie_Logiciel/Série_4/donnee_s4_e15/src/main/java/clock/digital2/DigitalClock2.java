package clock.digital2;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

import java.awt.*;

/**
 * @author Daniel Borcard Daniel.Borcard@unifr.ch
 * @version 1.0
 */
public class DigitalClock2 extends ClockFrame {
    public DigitalClock2(ClockTimer timer){
        super(timer);
    }

    /**
     * The constructor of the custom lock
     * @return Returns a clockpanel of the custom clock
     */
    @Override
    protected ClockPanel createClockPanel(){
        DigitalClockPanel2 clockPanel = new DigitalClockPanel2();
        clockPanel.setPreferredSize(new Dimension(400, 200));
        return clockPanel;
    }

    /**
     * Set the title of the custom clock windows.
     */
    @Override
    protected void setFrameTitle(){setTitle("Just another digital clock");}

}
