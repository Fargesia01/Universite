package clock.digitalNG;

import clock.ClockPanel;
import java.awt.geom.*;
import java.awt.*;

public class DigitalClockPanelNG extends ClockPanel {

    private static Color secondsColor = Color.red;
    private static Color minutesColor = Color.blue;
    private static Color hoursColor = Color.green;
    private static Color bgcolor = Color.white;
    private int hour;
    private int minute;
    private int second;
    
    private Graphics2D g2;
    private int horizontalbarlength = 50;
    private int barwidth = 7;
    private int verticalbarlength = 60;
    private Rectangle2D rect1;
    private Rectangle2D rect2;
    private Rectangle2D rect3;
    private Rectangle2D rect4;
    private Rectangle2D rect5;
    private Rectangle2D rect6;
    private Rectangle2D rect7;
    private Rectangle2D rect8;
    private Rectangle2D rect9;
    private Rectangle2D rect10;
    private Rectangle2D rect11;
    private Rectangle2D rect12;
    private Rectangle2D rect13;
    private Rectangle2D rect14;

    public DigitalClockPanelNG() {

    }

    public void setTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    @Override
    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        g.setColor(bgcolor);
        g.fillRect(0, 0, width, height);
        drawHourDigits(g, hour, hoursColor);
        drawMinuteDigits(g, minute, minutesColor);
        drawSecondDigits(g, second, secondsColor);

    }

    private void drawOutline1(Graphics g, double x, double y, Color c) {
        g2 = (Graphics2D) g;

        rect1 = new Rectangle2D.Double(x, y, barwidth, verticalbarlength);
        g2.setPaint(c);
        g2.draw(rect1);
        rect2 = new Rectangle2D.Double(x + 62, y, barwidth, verticalbarlength);
        g2.setPaint(c);
        g2.draw(rect2);
        rect3 = new Rectangle2D.Double(x, y + 67, barwidth, verticalbarlength);
        g2.setPaint(c);
        g2.draw(rect3);
        rect4 = new Rectangle2D.Double(x + 62, y + 67, barwidth, verticalbarlength);
        g2.setPaint(c);
        g2.draw(rect4);

        rect5 = new Rectangle2D.Double(x + 9, y - 6, horizontalbarlength, barwidth);
        g2.setPaint(c);
        g2.draw(rect5);
        rect6 = new Rectangle2D.Double(x + 9, y + 61, horizontalbarlength, barwidth);
        g2.setPaint(c);
        g2.draw(rect6);
        rect7 = new Rectangle2D.Double(x + 9, y + 127, horizontalbarlength, barwidth);
        g2.setPaint(c);
        g2.draw(rect7);
    }

    private void drawOutline2(Graphics g, double x, double y, Color c) {
        g2 = (Graphics2D) g;

        rect8 = new Rectangle2D.Double(x, y, barwidth, verticalbarlength);
        g2.setPaint(c);
        g2.draw(rect8);
        rect9 = new Rectangle2D.Double(x + 62, y, barwidth, verticalbarlength);
        g2.setPaint(c);
        g2.draw(rect9);
        rect10 = new Rectangle2D.Double(x, y + 67, barwidth, verticalbarlength);
        g2.setPaint(c);
        g2.draw(rect10);
        rect11 = new Rectangle2D.Double(x + 62, y + 67, barwidth, verticalbarlength);
        g2.setPaint(c);
        g2.draw(rect11);

        rect12 = new Rectangle2D.Double(x + 9, y - 6, horizontalbarlength, barwidth);
        g2.setPaint(c);
        g2.draw(rect12);
        rect13 = new Rectangle2D.Double(x + 9, y + 61, horizontalbarlength, barwidth);
        g2.setPaint(c);
        g2.draw(rect13);
        rect14 = new Rectangle2D.Double(x + 9, y + 127, horizontalbarlength, barwidth);
        g2.setPaint(c);
        g2.draw(rect14);
    }

    private void drawHourDigits(Graphics g, int hr, Color c) {
        drawOutline1(g, 10, 10, c);
        drawOutline2(g, 100, 10, c);

        fill(g, hour, c);
    }

    private void drawMinuteDigits(Graphics g, int min, Color c) {
        drawOutline1(g, 185, 10, c);

        drawOutline2(g, 275, 10, c);

        fill(g, min, c);
    }

    private void drawSecondDigits(Graphics g, int sec, Color c) {
        drawOutline1(g, 360, 10, c);

        drawOutline2(g, 450, 10, c);

        fill(g, sec, c);
    }
    
    private void fill(Graphics g, int cipher, Color c) {
        if (cipher < 10) {
            g2.fill(rect1);
            g2.fill(rect2);
            g2.fill(rect3);
            g2.fill(rect4);
            g2.fill(rect5);
            g2.fill(rect7);
        } else if (cipher < 20) {
            g2.fill(rect4);
            g2.fill(rect2);
        } else if (cipher < 30) {
            g2.fill(rect5);
            g2.fill(rect2);
            g2.fill(rect6);
            g2.fill(rect3);
            g2.fill(rect7);
        } else if (cipher < 40) {
            g2.fill(rect5);
            g2.fill(rect2);
            g2.fill(rect6);
            g2.fill(rect4);
            g2.fill(rect7);
        } else if (cipher < 50) {
            g2.fill(rect1);
            g2.fill(rect2);
            g2.fill(rect6);
            g2.fill(rect4);
        } else if (cipher < 60) {
            g2.fill(rect5);
            g2.fill(rect1);
            g2.fill(rect6);
            g2.fill(rect4);
            g2.fill(rect7);
        }
        if (cipher%10 == 0) {
            g2.fill(rect8);
            g2.fill(rect12);
            g2.fill(rect9);
            g2.fill(rect10);
            g2.fill(rect11);
            g2.fill(rect14);
        } else if (cipher%10 == 1) {
            g2.fill(rect9);
            g2.fill(rect11);
        } else if (cipher%10 == 2) {
            g2.fill(rect9);
            g2.fill(rect10);
            g2.fill(rect13);
            g2.fill(rect12);
            g2.fill(rect14);
        } else if (cipher%10 == 3) {
            g2.fill(rect13);
            g2.fill(rect9);
            g2.fill(rect11);
            g2.fill(rect12);
            g2.fill(rect14);
        } else if (cipher%10 == 4) {
            g2.fill(rect8);
            g2.fill(rect9);
            g2.fill(rect13);
            g2.fill(rect11);
        } else if (cipher%10 == 5) {
            g2.fill(rect8);
            g2.fill(rect13);
            g2.fill(rect11);
            g2.fill(rect12);
            g2.fill(rect14);
        } else if (cipher%10 == 6) {
            g2.fill(rect8);
            g2.fill(rect13);
            g2.fill(rect10);
            g2.fill(rect11);
            g2.fill(rect12);
            g2.fill(rect14);
        } else if (cipher%10 == 7) {
            g2.fill(rect9);
            g2.fill(rect11);
            g2.fill(rect12);
        } else if (cipher%10 == 8) {
            g2.fill(rect8);
            g2.fill(rect9);
            g2.fill(rect10);
            g2.fill(rect11);
            g2.fill(rect12);
            g2.fill(rect13);
            g2.fill(rect14);
        } else if (cipher%10 == 9) {
            g2.fill(rect8);
            g2.fill(rect9);
            g2.fill(rect13);
            g2.fill(rect11);
            g2.fill(rect12);
            g2.fill(rect14);
        }
    }

    @Override
    public void displayTime(int hour, int minute, int second) {
        setTime(hour, minute, second);
        repaint();
    }
}
