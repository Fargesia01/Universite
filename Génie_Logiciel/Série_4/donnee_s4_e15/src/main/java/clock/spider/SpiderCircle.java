package clock.spider;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Cobra_8
 */
public class SpiderCircle {

    private static final Color[] LAYER_COLORS = new Color[]{
            Color.RED, Color.ORANGE, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA
    };

    /* starting from top-left point and relative to the middle of the JPanel */
    private static final SpiderClockPoint[] HEXAGON_VERTICES = new SpiderClockPoint[]{ // forms a hexagon
            new SpiderClockPoint(-1, -2), new SpiderClockPoint(1, -2), new SpiderClockPoint(2.5F, 0),
            new SpiderClockPoint(1, 2), new SpiderClockPoint(-1, 2), new SpiderClockPoint(-2.5F, 0)
    };

    private static final SpiderDigit[] SPIDER_DIGITS = new SpiderDigit[]{
            new SpiderDigit(0, false, new int[]{1, 1, 1, 1, 1, 1}, Color.WHITE),
            new SpiderDigit(1, false, new int[]{0, 0, 0, 0, 1, 1}, Color.YELLOW),
            new SpiderDigit(2, true, new int[]{1, 1, 0, 1, 1, 0}, Color.ORANGE),
            new SpiderDigit(3, true, new int[]{1, 1, 1, 1, 0, 0}, Color.RED),
            new SpiderDigit(4, true, new int[]{0, 1, 1, 0, 0, 1}, Color.MAGENTA),
            new SpiderDigit(5, true, new int[]{1, 0, 1, 1, 0, 1}, Color.PINK),
            new SpiderDigit(6, true, new int[]{1, 0, 1, 1, 1, 1}, Color.BLUE),
            new SpiderDigit(7, false, new int[]{1, 1, 1, 0, 0, 0}, Color.CYAN),
            new SpiderDigit(8, true, new int[]{1, 1, 1, 1, 1, 1}, Color.GREEN),
            new SpiderDigit(9, true, new int[]{1, 1, 1, 1, 0, 1}, Color.GRAY)
    };

    private final int circleLayer; // value range from 1 to 6

    public SpiderCircle(int cirlceLayer) {
        circleLayer = cirlceLayer;
    }

    public int getCircleLayer() {
        return circleLayer;
    }

    public void paintCircle(JPanel panel, Graphics graphics, int value) {
        /* retrieve the right digit object */
        SpiderDigit spiderDigit = SPIDER_DIGITS[value];
        Color digitColor = getDigitColor(spiderDigit);

        /* prepare the paint brush */
        Graphics2D graphics2 = (Graphics2D) graphics;
        graphics2.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        graphics2.setColor(digitColor);

        /* calculate where to paint the final lines */
        int smallerSide = Math.min(panel.getWidth(), panel.getHeight());
        SpiderClockPoint center = new SpiderClockPoint(panel.getWidth() / 2, panel.getHeight() / 2);
        SpiderClockPoint zoomFactor = new SpiderClockPoint(smallerSide / 52, smallerSide / 50);
        SpiderClockPoint layerTranslation = new SpiderClockPoint(getCircleLayer(), getCircleLayer());
        SpiderClockPoint lineTranslation = zoomFactor.mul(layerTranslation);

        /* draw the necessary lines for this SpiderDigit */
        for (int i = 0; i < 6; i++) {
            if (spiderDigit.getActiveLines()[i] == 0) {
                continue;
            }
            SpiderClockPoint firstPoint = HEXAGON_VERTICES[i].mul(lineTranslation).add(center);
            SpiderClockPoint secondPoint = HEXAGON_VERTICES[(i + 1 == 6) ? 0 : i + 1].mul(lineTranslation).add(center);
            graphics2.drawLine(firstPoint.getXAsInt(), firstPoint.getYAsInt(), secondPoint.getXAsInt(), secondPoint.getYAsInt());
        }

        if (spiderDigit.isMiddleActive()) { // draw the two segments on the middle line if necessary
            SpiderClockPoint innerTranslatin = zoomFactor.mul(layerTranslation.sub(new SpiderClockPoint(1, 1))); // translation for the inner (second) point

            int[] segments = new int[]{2, 5};
            for (int segment : segments) {
                SpiderClockPoint firstPoint = HEXAGON_VERTICES[segment].mul(lineTranslation).add(center);
                SpiderClockPoint secondPoint = HEXAGON_VERTICES[segment].mul(innerTranslatin).add(center);
                graphics2.drawLine(firstPoint.getXAsInt(), firstPoint.getYAsInt(), secondPoint.getXAsInt(), secondPoint.getYAsInt());
            }
        }
    }

    private Color getDigitColor(SpiderDigit spiderDigit) {
        switch (SpiderClock.getColorMode()) {
            case SINGLE_COLOR:
                return SpiderClock.getSingleColor();
            case LAYER_COLOR:
                return LAYER_COLORS[getCircleLayer() - 1];
            case UNIT_COLOR:
                return LAYER_COLORS[((getCircleLayer() - 1) / 2) * 2];
            case UNIT_COLOR_ALTERNATIVE:
                int index = (getCircleLayer() - 1) / 2;
                return index == 0 ? Color.RED : (index == 1 ? Color.ORANGE : Color.PINK);
            case DIGIT_COLOR:
                return spiderDigit.getColor();
            default:
                return SpiderClock.getSingleColor();
        }
    }

    private static final class SpiderDigit {

        private final int digit;
        private final boolean middleActive;
        private final int[] activeLines; // the edge on top of the hexagon is at index 0

        private final Color color;

        public SpiderDigit(int digit, boolean middleActive, int[] activeLines, Color color) {
            this.digit = digit;
            this.middleActive = middleActive;
            this.activeLines = activeLines;

            this.color = color;
        }

        public int getDigit() {
            return digit;
        }

        public boolean isMiddleActive() {
            return middleActive;
        }

        public int[] getActiveLines() {
            return activeLines;
        }

        public Color getColor() {
            return color;
        }
    }
}
