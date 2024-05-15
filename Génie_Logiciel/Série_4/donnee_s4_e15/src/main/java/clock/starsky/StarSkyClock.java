package clock.starsky;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

import java.awt.Dimension;

/**
 * A star sky clock observing a clock timer.
 *
 * @author Sebastien Anthamatten
 */
public class StarSkyClock extends ClockFrame {
    private static final long serialVersionUID = 3258408447900069937L;

    public StarSkyClock(ClockTimer timer) {
        super(timer);
    }

    @Override
    protected ClockPanel createClockPanel() {
        StarSkyClockPanel clockPanel = new StarSkyClockPanel();
        clockPanel.setPreferredSize(new Dimension(1000, 600));
        return clockPanel;
    }

    protected void setFrameTitle()
    {
        setTitle("StarSky Clock");
    }
}