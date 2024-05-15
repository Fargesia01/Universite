package clock.digital;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import clock.ClockPanel;

/**
 * A panel in which the digital clock is displayed.
 * 
 * @author Andreas Ruppen
 */
public class DigitalClockPanel extends ClockPanel {
    private static final long serialVersionUID = 3257288015469949234L;
    
    private Color bgcolor = Color.black;
    private Color hoursColor = Color.green;
    private Color minutesColor = Color.blue;
    private Color secondsColor = Color.red;
    private Color separationColor = Color.lightGray;
    private Font font = new Font("Arial", Font.BOLD, 36);
    private JLabel hourLabel = new JLabel();
    private JLabel minuteLabel = new JLabel();
    private JLabel secondsLabel = new JLabel();
    private JLabel separationLabel = new JLabel();
    private JLabel separationLabel2 = new JLabel();
    
    /**
     * Creates a new instance of <code>DigitalClockPanel</code>.
     */
    public DigitalClockPanel() {      
        // Set up the panel.
        setBackground(bgcolor);
        setLayout(new FlowLayout());

        // Set up the text labels.
        setUpLabel(hourLabel, hoursColor);
        setUpLabel(minuteLabel, minutesColor);
        setUpLabel(separationLabel2, separationColor);
        setUpLabel(separationLabel, separationColor);
        separationLabel.setText(":");
        setUpLabel(secondsLabel, secondsColor);
        separationLabel2.setText(":");
        
        // Add the labels to the panel.      
        add(hourLabel);
        add(separationLabel);
        add(minuteLabel);
        add(separationLabel2);        
        add(secondsLabel);
    }
    
    private void setUpLabel(JLabel label, Color color) {
    	label.setFont(font);
    	label.setHorizontalAlignment(JLabel.CENTER);
    	label.setForeground(color);
    }
     
    /**
     * Sets the clocks current time.
     */
    public void displayTime(int hour, int minute, int second) {
        secondsLabel.setText(formatNumber(second));
        minuteLabel.setText(formatNumber(minute));
        hourLabel.setText(formatNumber(hour));
    }
       
    /**
     * Formats the given number ensuring there are always two digits.
     */
    private String formatNumber(int number) {
        String str = Integer.toString(number);
        if (number < 10) {
            str = "0" + str;
        }
        return str;
    }
}