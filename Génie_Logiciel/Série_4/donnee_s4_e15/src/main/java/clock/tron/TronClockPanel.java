package clock.tron;

import clock.ClockPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;


/**
 * A panel in which the tron clock is drawn.
 * 
 * @author Ryan
 */
public class TronClockPanel extends ClockPanel {
    
    private Font font;
    private File fileName = new File("src/main/java/clock/tron/Tr2n.ttf");
    
    int hour;
    int minute;
    int second;
    int millisecond;
    
    /**
     * Creates a new instance of <code>DigitalClockPanel</code>.
     */
    public TronClockPanel() {
        
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, fileName).deriveFont(Font.PLAIN, 30);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fileName));
            this.setFont(font);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        
    }

    
    /**
     * Overrides the superclass method by drawing a tron clock in the panel.
     */
    protected void paintComponent(Graphics g) {       
        // Some geometric calculations.
        int width = getWidth();
        int height = getHeight();

        Point c = new Point(width / 4, height / 4);

        // Clear background.
        g.setColor(new Color(9, 47, 54));
        g.fillRect(0, 0, width, height);
        
        //set thickness of line (Graphics2D is needed)
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(7));
        
        // Draw the clock components.
        drawSecondCircle(g2, c, second);
        drawMinuteCircle(g2, c, minute);
        drawHourCircle(g2, c, hour);
        drawAMPM(g2, c, hour);
        drawTime(g2, c, hour, minute, second);
        repaint();
    }

    
    /**
     * Draws the clock's second hand.
     */
    private void drawSecondCircle(Graphics2D g2, Point c, int s) {
        
        g2.setColor(new Color(0,255, 255));
        g2.drawArc(c.x-50, c.y-50, 200, 200, 90, -6 * s);
    }
    
    /**
     * Draws the clock's minute hand.
     */
    private void drawMinuteCircle(Graphics g2, Point c, int m) {
        g2.setColor(new Color(0,255, 255));
        g2.drawArc(c.x-25, c.y-25, 150, 150, 90, -6 * m);
    }
    
    /**
     * Draws the clock's hour hand.
     */
    private void drawHourCircle(Graphics g2, Point c, int h) {
        g2.setColor(new Color(0,255, 255));
        //here, special formula to transform 24h clock to 12 hour clock for a better proportion of the arc
        g2.drawArc(c.x, c.y, 100, 100, 90, -30 * ((h+11)%12+1));
    }
    
    private void drawAMPM(Graphics g2, Point c, int h){
        int sx = c.x+30;
        int sy = c.y+60;
        g2.setColor(new Color(0, 255, 255));
        
        //we want to know if it is AM or PM, just divide the hour(int) by 12. 
        if(h/12 == 0){
            g2.drawString("AM", sx, sy);
        }
        else{
            g2.drawString("PM", sx, sy);
        }
    }
    
    public void displayTime(int hour, int minute, int second)
    {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        
    }
    
    private void drawTime(Graphics g2, Point c, int h, int m, int s){
        int sx = c.x-40;
        int sy = c.y+200;
        String hour = String.format("%02d", h);
        String minute = String.format("%02d", m);
        String second = String.format("%02d", s);

        g2.drawString(hour + " : " + minute + " : "
                + second, sx, sy);
    }
}
