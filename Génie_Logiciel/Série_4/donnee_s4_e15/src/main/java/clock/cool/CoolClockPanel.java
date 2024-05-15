package clock.cool;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A JPanel in which the CoolClockDesign is drawn
 */
public class CoolClockPanel extends AClockJPanel {

    // First line
    private final WordLabel IL = new WordLabel("IL", true);
    private final WordLabel N = new WordLabel("N", false);
    private final WordLabel EST = new WordLabel("EST", true);
    private final WordLabel O1 = new WordLabel("O", false);
    private final WordLabel DEUX = new WordLabel("DEUX", false);
    // Second line
    private final WordLabel QUATRE = new WordLabel("QUATRE", false);
    private final WordLabel TROIS = new WordLabel("TROIS", false);
    // Third line
    private final WordLabel NEUF = new WordLabel("NEUF", false);
    private final WordLabel UNE = new WordLabel("UNE", false);
    private final WordLabel SEPT = new WordLabel("SEPT", false);
    // Fourth line
    private final WordLabel HUIT = new WordLabel("HUIT", false);
    private final WordLabel SIX = new WordLabel("SIX", false);
    private final WordLabel CINQ1 = new WordLabel("CINQ", false);
    // Fifth line
    private final WordLabel MI = new WordLabel("MI", false);
    private final WordLabel DI = new WordLabel("DI", false);
    private final WordLabel X = new WordLabel("X", false);
    private final WordLabel MINUIT = new WordLabel("MINUIT", false);
    // Sixth line
    private final WordLabel ONZE = new WordLabel("ONZE", false);
    private final WordLabel R1 = new WordLabel("R", false);
    private final WordLabel HEURES = new WordLabel("HEURES", false);
    // Seventh line
    private final WordLabel MOINS = new WordLabel("MOINS", false);
    private final WordLabel O2 = new WordLabel("O", false);
    private final WordLabel LE = new WordLabel("LE", false);
    private final WordLabel DIX = new WordLabel("DIX", false);
    // Eighth line
    private final WordLabel ET1 = new WordLabel("ET", false);
    private final WordLabel R2 = new WordLabel("R", false);
    private final WordLabel QUART = new WordLabel("QUART", false);
    private final WordLabel PMD = new WordLabel("PMD", false);
    // Ninth line
    private final WordLabel VINGT = new WordLabel("VINGT", false);
    private final WordLabel DASH = new WordLabel("-", false);
    private final WordLabel CINQ2 = new WordLabel("CINQ", false);
    private final WordLabel U = new WordLabel("U", false);
    // Tenth line
    private final WordLabel ET2 = new WordLabel("ET", false);
    private final WordLabel S = new WordLabel("S", false);
    private final WordLabel DEMIE = new WordLabel("DEMIE", false);
    private final WordLabel PAM = new WordLabel("PAM", false);

    // List of all lines
    private final List<Line> lines;

    /**
     * Creates a new instance of <code>CoolClockPanel</code>
     */
    public CoolClockPanel() {
        // Background color
        this.setBackground(Color.BLACK);
        // Layout
        this.setLayout(new GridLayout(0, 1));
        // Filling the list
        lines = new ArrayList<>();
        Line LINE_01 = new Line(IL, N, EST, O1, DEUX);
        lines.add(LINE_01);
        Line LINE_02 = new Line(QUATRE, TROIS);
        lines.add(LINE_02);
        Line LINE_03 = new Line(NEUF, UNE, SEPT);
        lines.add(LINE_03);
        Line LINE_04 = new Line(HUIT, SIX, CINQ1);
        lines.add(LINE_04);
        Line LINE_05 = new Line(MI, DI, X, MINUIT);
        lines.add(LINE_05);
        Line LINE_06 = new Line(ONZE, R1, HEURES);
        lines.add(LINE_06);
        Line LINE_07 = new Line(MOINS, O2, LE, DIX);
        lines.add(LINE_07);
        Line LINE_08 = new Line(ET1, R2, QUART, PMD);
        lines.add(LINE_08);
        Line LINE_09 = new Line(VINGT, DASH, CINQ2, U);
        lines.add(LINE_09);
        Line LINE_10 = new Line(ET2, S, DEMIE, PAM);
        lines.add(LINE_10);
        // Adding all lines to the CoolClockPanel
        for (Line l : lines) this.add(l);
    }

    /**
     * Overrides the superclass method by drawing an analog clock in the panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Update the lines color
        updateTime();
        // Update labels font, color foreground and reset lines color
        for (Line label : lines) {
            label.updateLabels(getWidth()/12);
        }
    }

    /**
     * Update the lines color
     */
    private void updateTime() {
        // It is
        IL.isOn = true;
        EST.isOn = true;
        // Shadowing the field inside another hour integer, needed for time update
        int hour = this.minute > 30 ? this.hour + 1 : this.hour;
        // Hours
        if (hour == 1 || hour == 13) {
            UNE.isOn = true; HEURES.isOn = true;
        } else if (hour == 2 || hour == 14) {
            DEUX.isOn = true; HEURES.isOn = true;
        } else if (hour == 3 || hour == 15) {
            TROIS.isOn = true; HEURES.isOn = true;
        } else if (hour == 4 || hour == 16) {
            QUATRE.isOn = true; HEURES.isOn = true;
        } else if (hour == 5 || hour == 17) {
            CINQ1.isOn = true; HEURES.isOn = true;
        } else if (hour == 6 || hour == 18) {
            SIX.isOn = true; HEURES.isOn = true;
        } else if (hour == 7 || hour == 19) {
            SEPT.isOn = true; HEURES.isOn = true;
        } else if (hour == 8 || hour == 20) {
            HUIT.isOn = true; HEURES.isOn = true;
        } else if (hour == 9 || hour == 21) {
            NEUF.isOn = true; HEURES.isOn = true;
        } else if (hour == 10 || hour == 22) {
            DI.isOn = true; X.isOn = true; HEURES.isOn = true;
        } else if (hour == 11 || hour == 23) {
            ONZE.isOn = true; HEURES.isOn = true;
        } else if (hour == 12) {
            MI.isOn = true; DI.isOn = true;
        } else if (hour == 24) {
            MINUIT.isOn = true;
        }
        // Minutes
        if (this.minute < 5) {
            return;
        } else if (this.minute < 10) {
            CINQ2.isOn = true;
        } else if (this.minute < 15) {
            DIX.isOn = true;
        } else if (this.minute < 20) {
            ET1.isOn = true; QUART.isOn = true;
        } else if (this.minute < 25) {
            VINGT.isOn = true;
        } else if (this.minute < 30) {
            VINGT.isOn = true; DASH.isOn = true; CINQ2.isOn = true;
        } else if (this.minute < 35) {
            ET2.isOn = true; DEMIE.isOn = true;
        } else if (this.minute < 40) {
            MOINS.isOn = true; VINGT.isOn = true; DASH.isOn = true; CINQ2.isOn = true;
        } else if (this.minute < 45) {
            MOINS.isOn = true; VINGT.isOn = true;
        } else if (this.minute < 50) {
            MOINS.isOn = true; QUART.isOn = true;
        } else if (this.minute < 55) {
            MOINS.isOn = true; DIX.isOn = true;
        } else {
            MOINS.isOn = true; CINQ2.isOn = true;
        }
    }

    /**
     * Nested class Line extends JPanel
     */
    private static class Line extends JPanel {

        // List of labels
        private final List<WordLabel> labels;

        /**
         * Creates a new instance of <code>Line</code>
         * @param wordLabels the labels for the Line
         */
        public Line(WordLabel... wordLabels) {
            // Layout
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            // Background
            this.setBackground(Color.BLACK);
            // Filling the list
            labels = Arrays.asList(wordLabels);
            // Adding to the Line Panel
            for (WordLabel label : labels) this.add(label);
        }

        /**
         * Update labels font, color foreground and reset lines color
         * @param size the size for the font
         */
        public void updateLabels(int size) {
            for (WordLabel label : labels) {
                // Font
                Font font = new Font("Monospaced", Font.PLAIN, size);
                label.setFont(font);
                // Color foreground
                if (label.isOn) label.setForeground(Color.WHITE);
                else label.setForeground(Color.DARK_GRAY);
                // Reset isOne, will be updated again in updateTime
                label.isOn = false;
            }
        }

    }

    /**
     * Nested WordLabel class extends JLabel
     */
    private static class WordLabel extends JLabel {

        // To know if we have to color it, depends on the time
        private boolean isOn;

        /**
         * Creates a new instance of <code>WordLabel</code>
         * @param word a String to display
         * @param isOn a boolean to know if we have to color the word depending on the time
         */
        public WordLabel(String word, boolean isOn) {
            this.isOn = isOn;
            setText(word);
        }

    }

}
