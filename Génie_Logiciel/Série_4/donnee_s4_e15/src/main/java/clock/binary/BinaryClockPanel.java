package clock.binary;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;

import clock.ClockPanel;

/**
 * A panel in which the digital clock is displayed.
 * 
 * @author Andreas Ruppen Original idea from Andreas Ruppen, Tony Svensson and
 *         Joseph Schaeppi
 */
public class BinaryClockPanel extends ClockPanel {
	private static final long serialVersionUID = 3257288015469949234L;

	private Color zerobitColor = Color.lightGray;

	private Color onebitColorMinute = Color.blue;
	private Color onebitColorHour = Color.green;
	private Color onebitColorSecond = Color.red;

	// The largest number to represent is 59 (for graphicBinaryMinute and graphicBinarySecond)
	// So one need 6 bits. The largest number with 6 bits is 2^6-1=63
	// (i.e. 111111)
	private int maxBits = 6;

	private TextField[] graphicBinaryHour = new TextField[maxBits];
	private TextField[] graphicBinaryMinute = new TextField[maxBits];
	private TextField[] graphicBinarySecond = new TextField[maxBits];
	
	private int hour;
	private int minute;
	private int second;

	/**
	 * Creates a new instance of <code>BinaryClockPanel</code>.
	 */
	public BinaryClockPanel() {
		// Set up the panel.
		setLayout(createGridBagLayout());
		// Create, set up and add the textfields to the panel
		for (int i = 0; i < maxBits; i++) {
			graphicBinaryHour[i] = new TextField();
			initTextField(graphicBinaryHour[i]);
			graphicBinaryMinute[i] = new TextField();
			initTextField(graphicBinaryMinute[i]);
			graphicBinarySecond[i] = new TextField();
			initTextField(graphicBinarySecond[i]);
			add(graphicBinaryHour[i], createGridBagConstraints(i,0));
			add(graphicBinaryMinute[i], createGridBagConstraints(i,1));
			add(graphicBinarySecond[i], createGridBagConstraints(i,2));
		}

	}
	
	private GridBagLayout createGridBagLayout() {
		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.rowWeights = new double[] { 1, 1, 1 };
		thisLayout.rowHeights = new int[] { 7, 7, 7 };
		thisLayout.columnWeights = new double[] { 1, 1, 1, 1, 1, 1 };
		thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7 };
		return thisLayout;
	}
	
	private GridBagConstraints createGridBagConstraints(int column, int row) {
		int gridx = column;
        int gridy = row;
        int gridwidth = 1;
        int gridheight = 1;
        double weightx = 0.0;
        double weighty = 0.0;
        int anchor = GridBagConstraints.CENTER;
        int fill = GridBagConstraints.BOTH;
        Insets insets = new Insets(0, 0, 0, 0);
        int ipadx = 0;
        int ipady = 0;
		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady);
	}

	private void initTextField(TextField t) {
		setToZero(t);
		t.setEditable(false);
	}

	/**
	 * Sets the clocks current time.
	 */
	public void displayTime(int nowHour, int nowMinute, int nowSecond) {
		if (nowSecond != second) {
			second = nowSecond;
			drawBits(graphicBinarySecond, second, onebitColorSecond);
		}
		if (nowMinute != minute) {
			minute = nowMinute;
			drawBits(graphicBinaryMinute, minute, onebitColorMinute);
		}
		if (nowHour != hour) {
			hour = nowHour;
			drawBits(graphicBinaryHour, hour, onebitColorHour);
		}
	}

	private void drawBits(TextField[] binaryDigits, int number, Color oneColor) {
		if (number == 0)
			for (int j = 0; j < maxBits; j++)
				setToZero(binaryDigits[j]);
		else {
			int rest = 0;
			int k = maxBits - 1;
			while (number != 0) {
				rest = number % 2;
				number = number / 2;
				if (rest == 0)
					setToZero(binaryDigits[k--]);
				else
					setToOne(binaryDigits[k--], oneColor);
			}
		}
	}

	private void setToZero(TextField t) {
		t.setBackground(zerobitColor);
	}
	
	private void setToOne(TextField t, Color color) {
		t.setBackground(color);
	}

}