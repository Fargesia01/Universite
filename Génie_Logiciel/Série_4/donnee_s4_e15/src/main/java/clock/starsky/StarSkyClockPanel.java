package clock.starsky;

import clock.ClockPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 * A panel in which the star sky clock is drawn.
 *
 * @author Sebastien Anthamatten
 */
public class StarSkyClockPanel extends ClockPanel {
    private static final long serialVersionUID = 3544948857483180340L;

    private int hour, minute, second;
    
    private Font font = new Font("TimesRoman", Font.PLAIN, 10);

    private Point[] pointsArray = new Point[60];
    private boolean arrayInitialized = false;

    private float[] xRand = new float[60];
    private float[] yRand = new float[60];

    private Color planetColor = new Color(193, 177, 110);
    private Color[] planetGradient = {new Color(214, 202, 107),new Color(208, 196, 103),new Color(202, 191, 99),
            new Color(196, 186, 96),new Color(190, 180, 92),new Color(185, 175, 88),
            new Color(179, 170, 85),new Color(173, 165, 81),new Color(167, 159, 78),
            new Color(161, 154, 74),new Color(156, 149, 70),new Color(150, 143, 67),
            new Color(144, 138, 63),new Color(138, 133, 59),new Color(132, 128, 56),
            new Color(127, 122, 52),new Color(121, 117, 49),new Color(115, 112, 45),
            new Color(109, 107, 41),new Color(103, 101, 38),new Color(98, 96, 34),
            new Color(92, 91, 30),new Color(86, 85, 27),new Color(80, 80, 23),
            new Color(74, 75, 20),new Color(69, 70, 16),new Color(63, 64, 12),
            new Color(57, 59, 9),new Color(51, 54, 5),new Color(46, 49, 2)};

    public StarSkyClockPanel() {
        setFont(font);
    }

    @Override
    public void displayTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        repaint();
    }
    
    protected void paintComponent(Graphics g) {

        setupPointsArray(pointsArray);

        // Some geometric calculations.
        int width = getWidth();
        int height = getHeight();

        int diameter = (Math.min(width, height))/6;
        int radius = diameter/2;

        double smoothness = 8;
        double ringSmoothness = smoothness / 4;
        // 30 minutes twice
        int minutePartitions = 30;
        int hourPartitions = 24;
        int starRadius = 2;

        //hour = 23;
        //minute = 59;
        //second = 59;

        // Clear background.
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        // Draw the clock components.
        drawStars(g, pointsArray, starRadius);
        drawUpperRings(g, width, height, diameter, radius, ringSmoothness, hourPartitions);
        drawPlanet(g, width, height, diameter, radius, smoothness, minutePartitions);
        drawLowerRings(g, width, height, diameter, radius, ringSmoothness, hourPartitions);
    }


    private void drawStars(Graphics g, Point[] pointsArray, int r) {
        g.setColor(Color.white);
        for(int i=0; i<second; i++){
            g.fillOval(pointsArray[i].x, pointsArray[i].y, r, r);
        }
    }

    private void drawPlanet(Graphics g, int width, int height, int diameter, int radius, double smoothness, int partitions){
        g.setColor(planetColor);

        // Draws the upper half of the planet
        if(minute < partitions/2) {
            drawRisingPlanetElement(g, width, height, diameter, radius, smoothness, partitions, minute);
        // Draws the lower part of the planet
        }else{
            int maxMinute = 29;
            drawRisingPlanetElement(g, width, height, diameter, radius, smoothness, partitions, maxMinute);
            drawSettingPlanetElement(g, width, height, diameter, radius, smoothness, partitions, minute);
        }
    }

    // Rings to be drawn before the planet to appear behind
    private void drawUpperRings(Graphics g, int width, int height, int diameter, int radius, double smoothness, int partitions){
        drawRingElement(g, width, height, diameter, radius, smoothness, partitions, hour, 0);
    }

    // Rings to be drawn after the planet to appear in front
    private void drawLowerRings(Graphics g, int width, int height, int diameter, int radius, double smoothness, int partitions){
        drawRingElement(g, width, height, diameter, radius, smoothness, partitions, hour, 180);
    }

    // Draws the first half-hour part of the planet
    private void drawRisingPlanetElement(Graphics g, int width, int height, int diameter, int radius, double smoothness, int partitions, int time){
        double step;
        for (int i = (int)(time * smoothness); i >= 0; i--) {
            step = 1 - i / (smoothness * partitions);
            g.setColor(planetGradient[time - (int)(i/smoothness)]);
            g.drawArc(width / 2, height / 2 - (int) (radius * step), diameter, (int) (diameter * step), 0, 180);
        }
    }

    // Draws the last half-hour part of the planet
    private void drawSettingPlanetElement(Graphics g, int width, int height, int diameter, int radius, double smoothness, int partitions, int time){
        double step;
        for(int i=0; i<(time - partitions)*smoothness; i++){
            step = i / (smoothness * partitions);
            g.setColor(planetGradient[(int)(i/smoothness)]);
            g.drawArc(width/2, height/2 - (int)(radius * step), diameter, (int)(diameter * step), 180, 180);
        }
    }

    private void drawRingElement(Graphics g, int width, int height, int diameter, int radius, double smoothness, int partitions, int time, int startAngle){
        double step;
        for (int i = (int)(time * smoothness); i >= 0; i--) {
            step = 1 - i / (smoothness * partitions);
            g.setColor(planetGradient[time - (int)(i/smoothness)]);
            g.drawArc(width/2 - (int)(diameter * step), height/2 - (int)(radius / 3 * step),
                    (int)(2 * diameter * step + diameter) , (int)(diameter / 3 * step) + radius/3,
                    startAngle, 180);
        }
    }

    private void setupPointsArray(Point[] pointsArray){
        Random rnd = new Random();

        int width = getWidth();
        int height = getHeight();

        // Set all the stars for the current minute
        for(int i=0; i<pointsArray.length; i++){
            if(!arrayInitialized){
                xRand[i] = rnd.nextFloat();
                yRand[i] = rnd.nextFloat();
            }

            pointsArray[i] = new Point(10 + (int)((width-20) * xRand[i]),
                    10 + (int)((height-20) * yRand[i]));
        }

        // Generate a new random pattern for the next batch of stars
        if(second == 59)
            arrayInitialized = false;
        else
            arrayInitialized = true;
    }
}
