package clock.word;

import clock.ClockPanel;
import java.awt.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * A panel in which the word clock is drawn.
 *
 * @author David Frischer
 * @author Linda Studer
 */
public class WordClockPanel extends ClockPanel {
    private static final long serialVersionUID = 6270394210109016536L;
    
    private int hour, minute;

    String[] line0 = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
    String[] line1 = new String[]{"", "I", "T", "L", "I", "S", "A", "S", "T", "I", "M", "E"};
    String[] line2 = new String[]{"", "A", "C", "Q", "U", "A", "R", "T", "E", "R", "D", "C"};
    String[] line3 = new String[]{"", "T", "W", "E", "N", "T", "Y", "F", "I", "V", "E", "X"};
    String[] line4 = new String[]{"", "H", "A", "L", "F", "B", "T", "E", "N", "F", "T", "O"};
    String[] line5 = new String[]{"", "P", "A", "S", "T", "E", "R", "U", "N", "I", "N", "E"};
    String[] line6 = new String[]{"", "O", "N", "E", "S", "I", "X", "T", "H", "R", "E", "E"};
    String[] line7 = new String[]{"", "F", "O", "U", "R", "F", "I", "V", "E", "T", "W", "O"};
    String[] line8 = new String[]{"", "E", "I", "G", "H", "T", "E", "L", "E", "V", "E", "N"};
    String[] line9 = new String[]{"", "S", "E", "V", "E", "N", "T", "W", "E", "L", "V", "E"};
    String[] line10 = new String[]{"", "T", "E", "N", "S", "E", "O'", "C", "L", "O", "C", "K"};

    String[][] textlines = { line0, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10 };

    private Color bgcolor = Color.darkGray;
    private Color txtcolor = new Color(90, 90, 90);
    //private Color timecolor = Color.MAGENTA;
    private Color timecolor = new Color(201, 46, 244);

    private Font font = new Font("Arial", Font.PLAIN, 20);

    /**
     * Creates a new instance of <code>LetterClockPanel</code>.
     */
    public WordClockPanel() {
        setFont(font);
    }

    @Override
    public void displayTime(int hour, int minute, int second) {
            this.hour = hour;
            this.minute = minute;
            repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Some geometric calculations.
        int width = getWidth();
        int height = getHeight();
        
        super.paintComponent(g);
        int border = 10;

        // Clear background.
        g.setColor(bgcolor);
        g.fillRect(0, 0, width, height);

        // Draw the clock components.

        Hashtable<String, Color> colordict = getColorDict();

        int x = border;
        int y = border;
        int xspace = (width - 2*border) / line1.length;
        int yspace = (height - 2*border) / textlines.length;

        for (int row = 0; row < textlines.length; row++) {
            for (int l = 0; l < textlines[0].length; l++) {
                String coor = Integer.toString(row)+Integer.toString(l);
                Color col = colordict.get(coor);
                g.setColor(col);
                g.drawString (textlines[row][l], x, y);
                x += xspace;
            }
            x = border;
            y += yspace;
        }
    }

    private Hashtable<String, Color> getColorDict () {
        Hashtable<String, Color> colorDict = new Hashtable<>();

        Hashtable<String, LinkedList> wordToCoor = new Hashtable<>();

        // create dict with normal colour
        for (int row = 0; row < textlines.length; row++) {
            for (int l = 0; l < textlines[0].length; l++) {
                String coor = Integer.toString(row)+Integer.toString(l);
                colorDict.put(coor, txtcolor);
            }
        }

        // change the colour of certain characters:
        LinkedList<String> changecolor = new LinkedList<String>(Arrays.asList("11", "12", "14", "15"));

        // determine which letters need changing
        // highlight FIVE
        if (minute <= 5 || 20 < minute && minute <= 25 || 30 < minute && minute <= 35 || 50 < minute && minute <= 55) {
            changecolor.add("37");
            changecolor.add("38");
            changecolor.add("39");
            changecolor.add("310");
        }

        // highlight TEN
        if (5 < minute && minute <= 10 || 45 < minute && minute <= 50) {
            changecolor.add("46");
            changecolor.add("47");
            changecolor.add("48");
        }

        // highlight QUARTER
        if (10 < minute && minute <= 15 || 40 < minute && minute <= 45) {
            changecolor.add("23");
            changecolor.add("24");
            changecolor.add("25");
            changecolor.add("26");
            changecolor.add("27");
            changecolor.add("28");
            changecolor.add("29");
        }

        // highlight TWENTY
        if (15 < minute && minute <= 25 || 30 < minute && minute <= 40) {
            changecolor.add("31");
            changecolor.add("32");
            changecolor.add("33");
            changecolor.add("34");
            changecolor.add("35");
            changecolor.add("36");
        }

        // highlight HALF
        if (25 < minute && minute <= 30) {
            changecolor.add("41");
            changecolor.add("42");
            changecolor.add("43");
            changecolor.add("44");
        }

        // highlight TO or PAST or O CLOCK
        if (minute > 30 && minute <= 55) {
            changecolor.add("410");
            changecolor.add("411");
        } else if (minute < 30 && minute > 0) {
            changecolor.add("51");
            changecolor.add("52");
            changecolor.add("53");
            changecolor.add("54");
        } else {
            changecolor.add("106");
            changecolor.add("107");
            changecolor.add("108");
            changecolor.add("109");
            changecolor.add("1010");
            changecolor.add("1011");
        }

        // highlight hours
        switch (hour) {
            case 1:
            case 13:
                changecolor.add("61");
                changecolor.add("62");
                changecolor.add("63");
                break;

            case 2:
            case 14:
                changecolor.add("79");
                changecolor.add("710");
                changecolor.add("711");
                break;

            case 3:
            case 15:
                changecolor.add("67");
                changecolor.add("68");
                changecolor.add("69");
                changecolor.add("610");
                changecolor.add("611");
                break;

            case 4:
            case 16:
                changecolor.add("71");
                changecolor.add("72");
                changecolor.add("73");
                changecolor.add("74");
                break;

            case 5:
            case 17:
                changecolor.add("75");
                changecolor.add("76");
                changecolor.add("77");
                changecolor.add("78");
                break;

            case 6:
            case 18:
                changecolor.add("64");
                changecolor.add("65");
                changecolor.add("66");
                break;

            case 7:
            case 19:
                changecolor.add("91");
                changecolor.add("92");
                changecolor.add("93");
                changecolor.add("94");
                changecolor.add("95");
                break;

            case 8:
            case 20:
                changecolor.add("81");
                changecolor.add("82");
                changecolor.add("83");
                changecolor.add("84");
                changecolor.add("85");
                break;

            case 9:
            case 21:
                changecolor.add("58");
                changecolor.add("59");
                changecolor.add("510");
                changecolor.add("511");
                break;

            case 10:
            case 22:
                changecolor.add("101");
                changecolor.add("102");
                changecolor.add("103");
                break;

            case 11:
            case 23:
                changecolor.add("86");
                changecolor.add("87");
                changecolor.add("88");
                changecolor.add("89");
                changecolor.add("810");
                changecolor.add("811");
                break;

            case 12:
            case 24:
                changecolor.add("96");
                changecolor.add("97");
                changecolor.add("98");
                changecolor.add("99");
                changecolor.add("910");
                changecolor.add("911");
        }

        // change the colour of the letters that are highlighted
        String[] changecolorArray = new String[changecolor.size()];
        changecolorArray = changecolor.toArray(changecolorArray);
        for (String s : changecolorArray) {
            colorDict.put(s, timecolor);
        }

        return colorDict;
    }

}
