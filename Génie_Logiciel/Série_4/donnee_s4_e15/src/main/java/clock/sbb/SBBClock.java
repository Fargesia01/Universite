package clock.sbb;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

import java.awt.Dimension;

/**
 * A SBB clock observing a clock timer.
 *
 * @author Werner Schmid
 * @author Eguy Njoyim
 */
public class SBBClock extends ClockFrame {

    public SBBClock(ClockTimer timer) {
        super(timer);
    }

    @Override
    protected ClockPanel createClockPanel() {
        SBBClockPanel clockPanel = new SBBClockPanel();
        clockPanel.setPreferredSize(new Dimension(400, 400));
        return clockPanel;
    }

    protected void setFrameTitle()
    {
        setTitle("SBB Clock");
    }
}
