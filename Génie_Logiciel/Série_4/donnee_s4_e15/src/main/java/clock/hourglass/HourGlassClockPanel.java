package clock.hourglass;

import clock.ClockPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Matteo Badaracco & Zeno Bardelli, adapted by Pascal Gremaud
 */
public class HourGlassClockPanel extends ClockPanel {
    private static final long serialVersionUID = 3257288015469949234L;

    private Color hcolor = Color.red;
    private Color scolor = Color.blue;
    private Color mcolor = Color.green;
    private Color bgcolor = Color.white;
    private Color lcolor = Color.black;
    private Font font = new Font("TimesRoman", Font.PLAIN, 10);
    
    private final int side = 120;
    private final int gap = 20;
    
    int counterM = 0;
    int counterS = 0;
    int counterH = 0;
    
    int hour;
    int minute;
    int second;
    
    /**
     * Creates a new instance of <code>HourGlassClockPanel</code>.
     */
    public HourGlassClockPanel(){
        setFont(font);
    }
    
    /**
     * Sets the clock's current time.
     */
    public void displayTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        repaint();
    }

    /**
     * Overrides the superclass method by drawing an hourglass clock in the panel.
     */
    protected void paintComponent(Graphics g) {       
        // Some geometric calculations.
        int width = getWidth();
        int height = getHeight();

        // Clear background.
        g.setColor(bgcolor);
        g.fillRect(0, 0, width, height);
        
        // Draw the clock components.
        drawLines(g, width);
        drawHour(g, hour);
        drawMinute(g, minute);
        drawSecond(g, second);

    }
    
    private void drawGlass(Graphics g, int x, int y) {
        g.fillRect(x-2, y-2, side+4, side+4);
        g.fillRect(x-2, y+side+gap, side+4, side+4);
        g.drawLine(x, y+side, x+side, y+side+gap);
        g.drawLine(x+side, y+side, x, y+side+gap);
        for (int i=0; i<side; i++) {
            g.drawLine(x+side/2, y+side+gap/2, x+i, y+side);
        }
    }
    
    private void drawSecond(Graphics g, int s) {
        g.setColor(scolor);
        int x = 290;
        int y = 10;
        drawGlass(g, x, y);
        g.setColor(bgcolor);
        g.fillRect(x, y, side, 2*s);
        g.fillRect(x, y+side+gap, side, 120-2*s);
        
        g.setColor(scolor);
        for (int i=0; i<32; i++) {
            if (counterS==0) {
                g.fillRect(x+62, y+side+gap/2+i*4, 4, 4);
                counterS=1;
            }
            else if (counterS==1) {
                g.fillRect(x+58, y+side+gap/2+i*4, 4, 4);
                counterS=0;
            }
        }     
        counterS = (counterS + 1) % 2;
    }
    
    private void drawMinute(Graphics g, int m) {
        g.setColor(mcolor);
        int x = 150;
        int y = 10;
        drawGlass(g, x, y);
        g.setColor(bgcolor);
        g.fillRect(x, y, side, 2*m);
        g.fillRect(x, y+side+gap, side, 120-2*m);
        
        g.setColor(mcolor);
        for (int i=0; i<32; i++) {
            if (counterM==0) {
                g.fillRect(x+62, y+side+gap/2+i*4, 4, 4);
                counterM=1;
            }
            else if (counterM==1) {
                g.fillRect(x+58, y+side+gap/2+i*4, 4, 4);
                counterM=0;
            }
        }     
        counterM = (counterM + 1) % 2;
    }

    private void drawHour(Graphics g, int h) {
        g.setColor(hcolor);
        int x = 10;
        int y = 10;
        drawGlass(g, x, y);
        g.setColor(bgcolor);
        g.fillRect(x, y, side, 5*h);
        g.fillRect(x, y+side+gap, side, 120-5*h);
        
        g.setColor(hcolor);
        for (int i=0; i<32; i++) {
            if (counterH==0) {
                g.fillRect(x+62, y+side+gap/2+i*4, 4, 4);
                counterH=1;
            }
            else if (counterH==1) {
                g.fillRect(x+58, y+side+gap/2+i*4, 4, 4);
                counterH=0;
            }
        }     
        counterH = (counterH + 1) % 2;
    }
    
    private void drawLines(Graphics g, int width) {
        g.setColor(lcolor);
        g.drawLine(0,10+2*side+gap,width,10+2*side+gap);
        g.drawString("0/4", 420, 10+2*side+gap-2);
        g.drawLine(0, 10+7*side/4+gap, width, 10+7*side/4+gap);
        g.drawString("1/4", 420, 10+7*side/4+gap-2);
        g.drawLine(0,10+3*side/2+gap,width,10+3*side/2+gap);
        g.drawString("2/4", 420, 10+3*side/2+gap-2);
        g.drawLine(0,10+5*side/4+gap,width,10+5*side/4+gap);
        g.drawString("3/4", 420, 10+5*side/4+gap-2);
        g.drawLine(0,10+side+gap,width,10+side+gap);
        g.drawString("4/4", 420, 10+side+gap-2);
    }
    
}
