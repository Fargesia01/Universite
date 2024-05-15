package clock.circular;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

import java.awt.*;

/**
 * A digital clock observing a clock timer.
 *
 * @author David Gauch
 */
public class CircularClock extends ClockFrame {

    public CircularClock(ClockTimer timer){
        super(timer);
    }

    protected ClockPanel createClockPanel() {
        CircularClockPanel specialClockPanel = new CircularClockPanel();
        specialClockPanel.setPreferredSize(new Dimension(500, 500));

        return specialClockPanel;
    }

}
