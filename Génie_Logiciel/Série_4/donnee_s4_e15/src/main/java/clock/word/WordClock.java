package clock.word;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

import java.awt.Dimension;

/**
 * A word clock observing a clock timer.
 *
 * @author David Frischer
 * @author Linda Studer
 */
public class WordClock extends ClockFrame {
    private static final long serialVersionUID = 3258408447900069937L;

    public WordClock(ClockTimer timer) {
        super(timer);
    }

    @Override
    protected ClockPanel createClockPanel() {
        WordClockPanel clockPanel = new WordClockPanel();
        clockPanel.setPreferredSize(new Dimension(600, 600));
        return clockPanel;
    }

    protected void setFrameTitle()
    {
        setTitle("Word Clock");
    }
}
