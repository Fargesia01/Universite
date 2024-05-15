package clock.pacman;

import clock.ClockPanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Cyril Michel, adapted by Pascal Gremaud
 */
public class PacmanClockPanel extends ClockPanel{
    
    private Color bgcolor = Color.black;
    private Color balkencolor = Color.blue;
    
    int hour;
    int minute;
    int second;

    /**
     * Creates a new instance of <code>PacmanClockPanel</code>.
     */
    public PacmanClockPanel() {

    }
    
    protected void  paintComponent(Graphics g){
        
        int width = getWidth();
        int height = getHeight();
        // Clear background.
        g.setColor(bgcolor);
        g.fillRect(0, 0, width, height);
        
        g.setColor(balkencolor);
        g.drawRect( width/2, (height/2)-20, 10, 10);
        g.drawRect( width/2, (height/2)+20, 10, 10);
        
        Numbers stunde2 = new Numbers(g, hour%10, (width/2)-70 , height/2);
        Numbers stunde1 = new Numbers(g, hour/10, (width/2)-170 , height/2);
        Numbers minuten2 = new Numbers(g, minute/10, (width/2)+40 , height/2);
        Numbers minuten1 = new Numbers(g, minute%10, (width/2)+140 , height/2); 
        
        Sekunden sekunden = new Sekunden(g, second, width, height);
        
        DrawPacman pacman = new DrawPacman(g, second, width, height);
        
    }
    
    /**
     * Sets the clocks current time.
     */
    public void displayTime(int hour, int minute, int second) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
            repaint();
    }
    
}
