
package clock.delayed;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;
import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author papinutt, adapted by Pascal Gremaud
 */
public class DelayedClock extends ClockFrame {

    public DelayedClock(ClockTimer timer) {
        super(timer);
    }
    
    @Override
    protected ClockPanel createClockPanel() {
        DelayedClockPanel clockPanel = new DelayedClockPanel();
        return clockPanel;
    }
    
    protected void setFrameTitle() {
        setTitle("Delayed Clock");
    }
    
}
