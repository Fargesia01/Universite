package clock.digitalLCD;

import java.awt.Font;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.*;

import javax.swing.JLabel;

import clock.ClockPanel;

/**
 * A panel in which the digital clock is drawn
 * 
 * @author jean-roch, adapted by Pascal Gremaud
 */
public class DigitalClockPanelLCD extends ClockPanel {
       
    private Font LCD_Font;
    private File fileName = new File("src/main/java/clock/digitalLCD/DS-DIGII.TTF");
    private JLabel timeDisplay;
    int hour;
    int minute;
    int second;
  
    
    /**
     * Creates a new instance of <code>RomanClockPanel</code>.
     */
    public DigitalClockPanelLCD() {
        try{
            LCD_Font = Font.createFont(Font.TRUETYPE_FONT, fileName).deriveFont(48f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        this.setBackground(new java.awt.Color(0,20,0));
        this.setForeground(new java.awt.Color(255,255,255));
        timeDisplay = new JLabel();
        this.add(timeDisplay);
        timeDisplay.setText("");
        timeDisplay.setForeground(Color.green);
	timeDisplay.setFont(LCD_Font);
    }
        

    /**
     * Sets the clocks current time.
     */
	public void displayTime(int hour, int minute, int second)
	{
            String s = "";
            if (hour < 10) {s += "0";}
            s += Integer.toString(hour);
            s += ":";

            if (minute < 10) {s += "0";}
            s += Integer.toString(minute);
            s += ":";

            if (second < 10) {s += "0";}
            s += Integer.toString(second);
            timeDisplay.setText(s);
	}
}
