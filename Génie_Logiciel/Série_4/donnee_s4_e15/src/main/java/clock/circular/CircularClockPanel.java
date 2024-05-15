package clock.circular;

import clock.ClockPanel;

import java.awt.*;

public class CircularClockPanel extends ClockPanel {
    private static final long serialVersionUID = 3544948897483180340L;

    private Color backgroundColor= Color.black;
    private Color hourColor =Color.red;
    private Color minuteColor = Color.yellow;
    private Color secondColor = Color.green;
    private Font font = new Font("TimesRoman", Font.PLAIN, 10);

    private double sa = Math.PI / 2;
    private double sda = Math.PI / 30;
    private double nda = Math.PI / 30;
    private double ndh=Math.PI/12;

    int hour;
    int minute;
    int second;

    /**
     * Creates a new instance of <code>SpecialClockPanel</code>.
     */
    public CircularClockPanel() {
        setFont(font);
    }

    /**
     * Sets the clocks current time.
     */
    @Override
    public void displayTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        repaint();
    }

    /**
     * Overrides the superclass method by drawing an analog clock in the panel.
     */
    protected void paintComponent(Graphics g) {
        // Some geometric calculations.
        int width = getWidth();
        int height = getHeight();
        int border = 10;

        Point c = new Point(width / 2, height / 2);
        int r = (Math.min(width, height) / 2) - border;

        // Clear background.
        g.setColor(backgroundColor);
        g.fillRect(0, 0, width, height);

        // Draw the clock components.
        drawSecondCircles(g, c, r,second);
        drawMinuteCircles(g,c,r,minute);
        drawHourCircles(g,c,r,hour);

    }

    /**
     * Draws the second circles.
     */
    private void drawSecondCircles(Graphics g, Point c, int r,int s) {
        // Draw the clock circle.
        g.setColor(secondColor);

        // Draw circles.
        FontMetrics fm = getFontMetrics(font);
        int fa = fm.getMaxAscent();
        int fh = (fm.getMaxAscent() + fm.getMaxDescent()) / 2;
        int nr = (80 * r) / 100;
        for (int i = 0; i < 60; i++) {
            int nx = (int) ((Math.cos((i * nda) - sa) * nr) + c.x);
            int ny = (int) ((Math.sin((i * nda) - sa) * nr) + c.y);
            if(s>=i){
                g.fillOval(nx,ny+fa -fh,10,10);
            }
            g.drawOval(nx,ny+fa -fh,10,10);
        }
    }

    /**
     * Draws the minute circles.
     */
    private void drawMinuteCircles(Graphics g, Point c, int r,int m) {
        // Draw the clock circle.
        g.setColor(minuteColor);

        // Draw circles.
        FontMetrics fm = getFontMetrics(font);
        int fa = fm.getMaxAscent();
        int fh = (fm.getMaxAscent() + fm.getMaxDescent()) / 2;
        int nr = (60* r) / 100;
        for (int i = 0; i < 60; i++) {
            int nx = (int) ((Math.cos((i * nda) - sa) * nr) + c.x);
            int ny = (int) ((Math.sin((i * nda) - sa) * nr) + c.y);
            if(m>i){
                g.fillOval(nx,ny+fa -fh,12,12);
            }
            g.drawOval(nx,ny+fa -fh,12,12);
        }
    }

    /**
     * Draws the hour circles.
     */
    private void drawHourCircles(Graphics g, Point c, int r,int h) {
        // Draw the clock circle.
        g.setColor(hourColor);

        // Draw circles.
        FontMetrics fm = getFontMetrics(font);
        int fa = fm.getMaxAscent();
        int fh = (fm.getMaxAscent() + fm.getMaxDescent()) / 2;
        int nr = (40 * r) / 100;
        for (int i = 0; i < 24; i++) {
            int nx = (int) ((Math.cos((i * ndh) - sa) * nr) + c.x);
            int ny = (int) ((Math.sin((i * ndh) - sa) * nr) + c.y);
            if (h > i) {
                g.fillOval(nx, ny + fa - fh, 20, 20);
            }
            g.drawOval(nx, ny + fa - fh, 20, 20);
        }
    }

}


