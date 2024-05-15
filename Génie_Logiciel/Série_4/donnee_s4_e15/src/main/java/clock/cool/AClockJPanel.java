package clock.cool;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class AClockJPanel
 * We can set the time from an ClockTimerObservable
 */
public abstract class AClockJPanel extends JPanel {

    protected int hour;
    protected int minute;
    protected int second;

    /**
     * Set the time from the ClockTimerObservable
     * @param hour hour
     * @param minute minute
     * @param second second
     */
    public void setTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * Override of paintComponent from JPanel
     * @param g the <code>Graphics</code> object
     */
    @Override
    protected abstract void paintComponent(Graphics g);
}
