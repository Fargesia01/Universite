package clock.digital2;

import clock.ClockPanel;

import java.awt.*;


/**
 * @author Daniel Borcard Daniel.Borcard@unifr.ch
 * @version 1.0
 */
public class DigitalClockPanel2 extends ClockPanel {

    /** the hours to be displayed **/
    int hour;
    /** the minutes to be displayed **/
    int minute;
    /** the seconds to be displayed **/
    int second;

    /** The color for the seconds **/
    private final Color shcolor = Color.ORANGE;
    /**The color for the minutes and hours **/
    private final Color mhcolor = Color.white;
    /*** The color of the background **/
    private final Color bgcolor = Color.DARK_GRAY;
    /** The color of the numbers when disabled **/
    private final Color numberColor = new Color(100,100 ,100 );
    /** The font of the numbers **/
    private final Font font = new Font("Arial", Font.BOLD, 20);

    /** Position of the small numbers **/
    private int[][] positions = new int[60][2];


    /**
     * The main method to paint the custom clock panel
     * @param g The graphic component to be changed
     */
    @Override
    protected void paintComponent(Graphics g) {
        // used for some calculations.
        int width = getWidth();
        int height = getHeight();

        // Clear background.
        g.setColor(bgcolor);
        g.fillRect(0, 0, width, height);

        //Set the font of the numbers
        g.setFont(font);

        //Draw the differents components
        drawNumbers(g,width,height);
        drawMinute(g);
        drawHour(g);
        drawSecond(g);

    }

    /**
     * Color the number form 0 to 59.
     * @param g The graphic component to be changed
     * @param index The number of type int to be changed beween 0 and 59
     * @param color The color, of type Color, of the number
     */
    private void colorPosition(Graphics g, int index, Color color){
        g.setColor(color);
        int amountBeforeSpace = 10;
        if (index<amountBeforeSpace){
            g.drawString(String.format("0%d",index),positions[index][0],positions[index][1]);
        }else{
            g.drawString(Integer.toString(index),positions[index][0],positions[index][1]);
        }
    }

    /**
     * @param g The graphic component to be changed
     * @param bigPos The index of the big number from 1 (for the 10's of the hour) to 4 (for the seconds)
     * @param number The number from 0 to 9 of type int to be displayed.
     */
    private void colorBigNumber(Graphics g, int bigPos,int number){
        //Each individual number that must be painted is set  manually this can be further improved to display letters
        switch (number){
            case 0:
                colorPosition(g,(bigPos-1)*15,mhcolor);
                colorPosition(g,(bigPos-1)*15+1,mhcolor);
                colorPosition(g,(bigPos-1)*15+2,mhcolor);
                colorPosition(g,(bigPos-1)*15+3,mhcolor);
                colorPosition(g,(bigPos-1)*15+4,mhcolor);
                colorPosition(g,(bigPos-1)*15+5,mhcolor);
                colorPosition(g,(bigPos-1)*15+9,mhcolor);
                colorPosition(g,(bigPos-1)*15+10,mhcolor);
                colorPosition(g,(bigPos-1)*15+11,mhcolor);
                colorPosition(g,(bigPos-1)*15+12,mhcolor);
                colorPosition(g,(bigPos-1)*15+13,mhcolor);
                colorPosition(g,(bigPos-1)*15+14,mhcolor);
                break;
            case 1:
                colorPosition(g,(bigPos-1)*15+1,mhcolor);
                colorPosition(g,(bigPos-1)*15+5,mhcolor);
                colorPosition(g,(bigPos-1)*15+6,mhcolor);
                colorPosition(g,(bigPos-1)*15+7,mhcolor);
                colorPosition(g,(bigPos-1)*15+8,mhcolor);
                colorPosition(g,(bigPos-1)*15+9,mhcolor);
                colorPosition(g,(bigPos-1)*15+4,mhcolor);
                colorPosition(g,(bigPos-1)*15+14,mhcolor);
                break;
            case 2:
                colorPosition(g,(bigPos-1)*15,mhcolor);
                colorPosition(g,(bigPos-1)*15+2,mhcolor);
                colorPosition(g,(bigPos-1)*15+3,mhcolor);
                colorPosition(g,(bigPos-1)*15+4,mhcolor);
                colorPosition(g,(bigPos-1)*15+5,mhcolor);
                colorPosition(g,(bigPos-1)*15+7,mhcolor);
                colorPosition(g,(bigPos-1)*15+9,mhcolor);
                colorPosition(g,(bigPos-1)*15+10,mhcolor);
                colorPosition(g,(bigPos-1)*15+11,mhcolor);
                colorPosition(g,(bigPos-1)*15+12,mhcolor);
                colorPosition(g,(bigPos-1)*15+14,mhcolor);
                break;
            case 3:
                colorPosition(g,(bigPos-1)*15,mhcolor);
                colorPosition(g,(bigPos-1)*15+2,mhcolor);
                colorPosition(g,(bigPos-1)*15+4,mhcolor);
                colorPosition(g,(bigPos-1)*15+5,mhcolor);
                colorPosition(g,(bigPos-1)*15+7,mhcolor);
                colorPosition(g,(bigPos-1)*15+9,mhcolor);
                colorPosition(g,(bigPos-1)*15+10,mhcolor);
                colorPosition(g,(bigPos-1)*15+11,mhcolor);
                colorPosition(g,(bigPos-1)*15+12,mhcolor);
                colorPosition(g,(bigPos-1)*15+13,mhcolor);
                colorPosition(g,(bigPos-1)*15+14,mhcolor);
                break;
            case 4:
                colorPosition(g,(bigPos-1)*15,mhcolor);
                colorPosition(g,(bigPos-1)*15+1,mhcolor);
                colorPosition(g,(bigPos-1)*15+2,mhcolor);
                colorPosition(g,(bigPos-1)*15+7,mhcolor);
                colorPosition(g,(bigPos-1)*15+10,mhcolor);
                colorPosition(g,(bigPos-1)*15+11,mhcolor);
                colorPosition(g,(bigPos-1)*15+12,mhcolor);
                colorPosition(g,(bigPos-1)*15+13,mhcolor);
                colorPosition(g,(bigPos-1)*15+14,mhcolor);
                break;
            case 5:
                colorPosition(g,(bigPos-1)*15,mhcolor);
                colorPosition(g,(bigPos-1)*15+1,mhcolor);
                colorPosition(g,(bigPos-1)*15+2,mhcolor);
                colorPosition(g,(bigPos-1)*15+4,mhcolor);
                colorPosition(g,(bigPos-1)*15+5,mhcolor);
                colorPosition(g,(bigPos-1)*15+7,mhcolor);
                colorPosition(g,(bigPos-1)*15+9,mhcolor);
                colorPosition(g,(bigPos-1)*15+10,mhcolor);
                colorPosition(g,(bigPos-1)*15+12,mhcolor);
                colorPosition(g,(bigPos-1)*15+13,mhcolor);
                colorPosition(g,(bigPos-1)*15+14,mhcolor);
                break;
            case 6:
                colorPosition(g,(bigPos-1)*15,mhcolor);
                colorPosition(g,(bigPos-1)*15+1,mhcolor);
                colorPosition(g,(bigPos-1)*15+2,mhcolor);
                colorPosition(g,(bigPos-1)*15+3,mhcolor);
                colorPosition(g,(bigPos-1)*15+4,mhcolor);
                colorPosition(g,(bigPos-1)*15+5,mhcolor);
                colorPosition(g,(bigPos-1)*15+7,mhcolor);
                colorPosition(g,(bigPos-1)*15+9,mhcolor);
                colorPosition(g,(bigPos-1)*15+10,mhcolor);
                colorPosition(g,(bigPos-1)*15+12,mhcolor);
                colorPosition(g,(bigPos-1)*15+13,mhcolor);
                colorPosition(g,(bigPos-1)*15+14,mhcolor);
                break;
            case 7:
                colorPosition(g,(bigPos-1)*15,mhcolor);
                colorPosition(g,(bigPos-1)*15+5,mhcolor);
                colorPosition(g,(bigPos-1)*15+7,mhcolor);
                colorPosition(g,(bigPos-1)*15+8,mhcolor);
                colorPosition(g,(bigPos-1)*15+9,mhcolor);
                colorPosition(g,(bigPos-1)*15+10,mhcolor);
                colorPosition(g,(bigPos-1)*15+11,mhcolor);
                break;
            case 8:
                colorPosition(g,(bigPos-1)*15,mhcolor);
                colorPosition(g,(bigPos-1)*15+1,mhcolor);
                colorPosition(g,(bigPos-1)*15+2,mhcolor);
                colorPosition(g,(bigPos-1)*15+3,mhcolor);
                colorPosition(g,(bigPos-1)*15+4,mhcolor);
                colorPosition(g,(bigPos-1)*15+5,mhcolor);
                colorPosition(g,(bigPos-1)*15+7,mhcolor);
                colorPosition(g,(bigPos-1)*15+9,mhcolor);
                colorPosition(g,(bigPos-1)*15+10,mhcolor);
                colorPosition(g,(bigPos-1)*15+11,mhcolor);
                colorPosition(g,(bigPos-1)*15+12,mhcolor);
                colorPosition(g,(bigPos-1)*15+13,mhcolor);
                colorPosition(g,(bigPos-1)*15+14,mhcolor);
                break;
            case 9:
                colorPosition(g,(bigPos-1)*15,mhcolor);
                colorPosition(g,(bigPos-1)*15+1,mhcolor);
                colorPosition(g,(bigPos-1)*15+2,mhcolor);
                colorPosition(g,(bigPos-1)*15+4,mhcolor);
                colorPosition(g,(bigPos-1)*15+5,mhcolor);
                colorPosition(g,(bigPos-1)*15+7,mhcolor);
                colorPosition(g,(bigPos-1)*15+9,mhcolor);
                colorPosition(g,(bigPos-1)*15+10,mhcolor);
                colorPosition(g,(bigPos-1)*15+11,mhcolor);
                colorPosition(g,(bigPos-1)*15+12,mhcolor);
                colorPosition(g,(bigPos-1)*15+13,mhcolor);
                colorPosition(g,(bigPos-1)*15+14,mhcolor);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + number);
        }

    }

    /**
     * @param g The graphic component to be changed
     * @param width The width of the windows, used to set the clock in the center
     * @param height The height of the windows, used to set the clock in the center
     */
    private void drawNumbers(Graphics g,int width,int height){
        // s is the current number to be processed
        int s =0;
        //the space is used to add a space between the big parts for a better readability
        int space=0;
        int columns = 12;
        int rows = 5;
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                //add spaces between the big numbers
                if (s>=15 && s<=29 && space==0){
                    space+=10;
                }else if (s>=30 && s<=44 && space==10){
                    space+=10;
                } else if(s>=45 && s<=59 && space==20){
                    space+=10;
                }
                //Some values are set manually
                positions[s][0]=width/2+25*j+space-170;
                positions[s][1]=height/2+20*i-40;
                s++;

            }
        }

        //color each small numbers
        for (int i = 0; i < positions.length; i++) {
            colorPosition(g,i,numberColor);
        }

        //add a small line between the hours and the minutes
        g.setColor(mhcolor);
        g.drawLine((positions[25][0]+positions[35][0])/2-2,positions[25][1],(positions[25][0]+positions[35][0])/2-2,positions[29][1]-5);

    }

    private void drawSecond(Graphics g) {
        colorPosition(g,second,shcolor);
    }

    private void drawMinute(Graphics g) {
        if (minute<10){
            colorBigNumber(g,3,0);
            colorBigNumber(g,4,minute);
        }else if (minute<20){
            colorBigNumber(g,3,1);
            colorBigNumber(g,4,minute-10);
        } else if (minute<30){
            colorBigNumber(g,3,2);
            colorBigNumber(g,4,minute-20);
        }else if (minute<40){
            colorBigNumber(g,3,3);
            colorBigNumber(g,4,minute-30);
        }else if (minute<50){
            colorBigNumber(g,3,4);
            colorBigNumber(g,4,minute-40);
        }else if (minute<60){
            colorBigNumber(g,3,5);
            colorBigNumber(g,4,minute-50);
        }

    }

    private void drawHour(Graphics g) {
        if (hour<10){
            colorBigNumber(g,1,0);
            colorBigNumber(g,2,hour);
        }else if (hour<20){
            colorBigNumber(g,1,1);
            colorBigNumber(g,2,hour-10);
        } else if (hour<30){
            colorBigNumber(g,1,2);
            colorBigNumber(g,2,hour-20);
        }
    }

    @Override
    public void displayTime(int hour, int minute, int second) {
        this.hour=hour;
        this.minute = minute;
        this.second=second;

        repaint();
    }
}

