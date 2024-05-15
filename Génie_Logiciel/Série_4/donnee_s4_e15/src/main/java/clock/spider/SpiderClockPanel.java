package clock.spider;

import clock.ClockPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Cobra_8
 */
public class SpiderClockPanel extends ClockPanel {

    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    private static BufferedImage BACKGROUND_IMAGE = null;

    int hour;
    int minute;
    int second;

    static {
        try {
            BACKGROUND_IMAGE = ImageIO.read(new File("assets/watch.png"));
        } catch (IOException ex) {
            BACKGROUND_IMAGE = null;
        }
    }

    private final SpiderCircle[] spiderWeb;

    public SpiderClockPanel() {
        spiderWeb = new SpiderCircle[6];
        for (int i = 0; i < 6; i++) {
            spiderWeb[i] = new SpiderCircle(i + 1);
        }

        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    public void displayTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        repaint();
    }

    /**
     * Overrides the superclass method by drawing a spider web clock onto the
     * panel
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        // Clear background.
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        if (BACKGROUND_IMAGE != null) {
            int smallerSide = Math.min(getWidth(), getHeight());
            SpiderClockPoint imageLocation = new SpiderClockPoint((getWidth() / 2) - (smallerSide / 2), (getHeight() / 2) - (smallerSide / 2));
            graphics.drawImage(BACKGROUND_IMAGE, imageLocation.getXAsInt(), imageLocation.getYAsInt(), smallerSide, smallerSide, null);
        }

        for (SpiderCircle circle : spiderWeb) {
            circle.paintCircle(this, graphics, getValue(circle.getCircleLayer() - 1));
        }
    }

    /**
     * Returns the value for the appropriate digit (starting from 0 which
     * returns the last digit for the current second and going to 5 with returns
     * the first digit for the current hour)
     */
    protected int getValue(int digit) {
        int relevantValue = Math.floorDiv(digit, 2) == 0 ? this.second : (Math.floorDiv(digit, 2) == 1 ? this.minute : this.hour);
        return digit % 2 != 0 ? Math.floorDiv(relevantValue, 10) : relevantValue % 10;
    }
}
