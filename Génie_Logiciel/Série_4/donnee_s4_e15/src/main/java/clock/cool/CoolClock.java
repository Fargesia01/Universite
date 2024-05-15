package clock.cool;



import clock.timer.ClockTimer;

import java.awt.*;

/**
 * A Cool Clock (Word Clock) observing a clock timer
 * @author Paul Ricci & Julien Richoz
 */
public class CoolClock extends AClockJFrameObserver {

    /**
     * Creates a new instance of <code>CoolClock</code> that observes the
     * given clock timer
     */
    public CoolClock(ClockTimer observableTimer) {
        super(new ClockTimerObservable(), "Cool Clock !");
    }

    /**
     * DESIGN PATTERN : FACTORY METHOD
     * Each subclass has to define this factory method
     * @return a new ClockJPanel
     */
    @Override
    protected AClockJPanel createClockPanel() {
        CoolClockPanel coolClockPanel = new CoolClockPanel();
        coolClockPanel.setPreferredSize(new Dimension(200, 200));
        return coolClockPanel;
    }
}
