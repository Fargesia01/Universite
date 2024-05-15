package clock.binaryShapes;

import clock.ClockPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author gremaupa
 */
public class BinaryShapesClockPanel extends ClockPanel{
    // Labels used
    private JLabel[] hourLabels = new JLabel[6];
    private JLabel[] minuteLabels = new JLabel[6];
    private JLabel[] secondLabels= new JLabel[6];
    private JPanel panel;

    public BinaryShapesClockPanel() {
        panel = new JPanel();
        initLabels();
        panel.setBackground(Color.black);
        panel.setLayout(new GridLayout(3,6));
        
        for (int i = 0; i < 6; i++) {
            panel.add(hourLabels[i]);
        }
        for (int i = 0; i < 6; i++) {
            panel.add(minuteLabels[i]);
        }
        for (int i = 0; i < 6; i++) {
            panel.add(secondLabels[i]);
        }
        this.add(panel);
        this.setBackground(Color.BLACK);
    }
    
        private void initLabels() {
        for (int i = 0; i < 6; i++) {
            hourLabels[i] = new JLabel("");
            minuteLabels[i] = new JLabel("");
            secondLabels[i] = new JLabel("");
        }
        
        Font font = new Font(Font.MONOSPACED, java.awt.Font.PLAIN, 60);
        
        for (JLabel lab : hourLabels) {
            lab.setForeground(Color.white);
            lab.setPreferredSize(new Dimension(100,100));
            lab.setFont(font);
        }
        for (JLabel lab : minuteLabels) {
            lab.setForeground(Color.white);
            lab.setPreferredSize(new Dimension(100,100));
            lab.setFont(font);
        }
        for (JLabel lab : secondLabels) {
            lab.setForeground(Color.white);
            lab.setPreferredSize(new Dimension(100,100));
            lab.setFont(font);
        }
    }
    
    @Override
    public void displayTime(int hour, int minute, int second) {
        for (int i = 0; i < 6; i++) {
            if ((hour & (int) Math.pow(2, i)) > 0) {
                hourLabels[5 - i].setText("○");
            } else {
                hourLabels[5 - i].setText("");
            }
        }
        for (int i = 0; i < 6; i++) {
            if ((minute & (int) Math.pow(2, i)) > 0) {
                minuteLabels[5 - i].setText("×");
            } else {
                minuteLabels[5 - i].setText("");
            }
        }
        for (int i = 0; i < 6; i++) {
            if ((second & (int) Math.pow(2, i)) > 0) {
                secondLabels[5 - i].setText("▢");
            } else {
                secondLabels[5 - i].setText("");
            }
        }   
    }
    
}
