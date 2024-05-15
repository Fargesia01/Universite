package clock.roman;

import java.awt.Font;
import javax.swing.JLabel;

import javax.swing.JPanel;

import clock.ClockPanel;

/**
 * A panel in which the digital clock with roman numbers is drawn.
 * 
 * @author Julien Thomet, adapted by Andreas Ruppen
 */
public class RomanClockPanel extends ClockPanel {
    private static final long serialVersionUID = 3544948857483180340L;  
       
    int hour;
    int minute;
    private JLabel timeDisplay;
    int second;
    
    // Array containing roman numbers
    private final static String[] BASIC_ROMAN_NUMBERS = { "L", "XL", "X", "IX", "V", "IV", "I" };
    // Array containing correspondant arab numbers
    private final static int[] BASIC_VALUES = { 50, 40, 10, 9, 5, 4, 1 };    
    
    /**
     * Creates a new instance of <code>RomanClockPanel</code>.
     */
    public RomanClockPanel() {
        this.setBackground(new java.awt.Color(0,0,0));
        this.setForeground(new java.awt.Color(255,255,255));
			timeDisplay = new JLabel();
			this.add(timeDisplay);
			timeDisplay.setText("");
			timeDisplay.setForeground(new java.awt.Color(255,255,255));
			timeDisplay.setFont(new java.awt.Font("Courier New", Font.BOLD, 14));
    }
        
    /**
     * Converts an arab number in romand number.
     * Source : http://www.moxlotus.alternatifs.eu/programmation/convertisseur.html
     */
    public String toRomanValue(int value) {
    	String romanString = "";
    	int remainder = value;
    	for (int i = 0; i < BASIC_VALUES.length; i++) {
    		while (remainder >= BASIC_VALUES[i]) {
    			romanString += BASIC_ROMAN_NUMBERS[i];
    			remainder -= BASIC_VALUES[i];
    		}
    	}        
    	return romanString;
    }

    /**
     * Sets the clocks current time.
     */
	public void displayTime(int hour, int minute, int second)
	{
		timeDisplay.setText(toRomanValue(hour)
				+ " : " + 
				toRomanValue(minute)
				+ " : " + 
				toRomanValue(second));
	}
}
