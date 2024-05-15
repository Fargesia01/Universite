package clock.spider;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

import java.awt.Color;

/**
 *
 * @author Cobra_8
 */
public class SpiderClock extends ClockFrame {

    private static final SpiderColorMode COLOR_MODE = SpiderColorMode.UNIT_COLOR_ALTERNATIVE;
    private static final Color SINGLE_COLOR = Color.ORANGE;

    public SpiderClock(ClockTimer timer) {
        super(timer);
    }

    @Override
    public ClockPanel createClockPanel() {
        return new SpiderClockPanel();
    }

    public static SpiderColorMode getColorMode() {
        return COLOR_MODE;
    }

    public static Color getSingleColor() {
        return SINGLE_COLOR;
    }

    public enum SpiderColorMode {
        SINGLE_COLOR, LAYER_COLOR, UNIT_COLOR, UNIT_COLOR_ALTERNATIVE, DIGIT_COLOR;
    }

}
