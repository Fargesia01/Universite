package clock.bargraph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import clock.ClockPanel;

/**
 * A panel in which the digital clock is displayed.
 * 
 * @author Andreas Ruppen Original idea from Joël Vogt and Elira Shehu
 */
public class BarGraphClockPanel extends ClockPanel {
	private static final long serialVersionUID = 3257288015469949234L;

	private Color backgroundColor = Color.lightGray;

	private Color hourColor = Color.green;

	private Color minuteColor = Color.blue;

	private Color secondColor = Color.red;

	private int barHeight = 30;

	private int vSpace = 20;

	private int leftMargin = 40;

	private int topMargin = 20;

	private int rightMargin = 80;

	private int hour;

	private int minute;

	private int second;

	/**
	 * Creates a new instance of <code>DigitalClockPanel</code>.
	 */
	public BarGraphClockPanel() {

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

	/**
	 * Overrides the superclass method by drawing an analog clock in the panel.
	 */
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		
		float maxSizeHour = (width - rightMargin) * (24 / 24.0f);
		float maxSizeMinute = (width - rightMargin) * (60 / 60.0f);
		float maxSizeSecond = (width - rightMargin) * (60 / 60.0f);
		
		// clears it all
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		
		// draws the bounding boxes
		g.setColor(this.hourColor);
		g.drawRect(leftMargin, topMargin, (int) maxSizeHour, barHeight);
		
		g.setColor(this.minuteColor);
		g.drawRect(leftMargin, topMargin + barHeight + vSpace,
				(int) maxSizeMinute, barHeight);
		
		g.setColor(this.secondColor);
		g.drawRect(leftMargin, topMargin + 2 * barHeight + 2 * vSpace,
				(int) maxSizeSecond, barHeight);
		
		paintCurrentTime(g);
	}
	
	private void paintCurrentTime(Graphics g) {
		int width = getWidth();
		
		float relSizeHour = (width - rightMargin) * (hour / 24.0f);
		float relSizeMinute = (width - rightMargin) * (minute / 60.0f);
		float relSizeSecond = (width - rightMargin) * (second / 60.0f);
		
		// fills the boxes
		g.setColor(this.hourColor);
		g.fillRect(leftMargin, topMargin, (int) relSizeHour, barHeight);
		
		g.setColor(this.minuteColor);
		g.fillRect(leftMargin, topMargin + barHeight + vSpace,
				(int) relSizeMinute, barHeight);
		
		g.setColor(this.secondColor);
		g.fillRect(leftMargin, topMargin + 2 * barHeight + 2 * vSpace,
				(int) relSizeSecond, barHeight);
	}

}