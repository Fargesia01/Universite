
package clock.delayed;

import clock.ClockFrame;
import clock.ClockPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JSlider;

/**
 *
 * @author papinutt, adapted by Pascal Gremaud
 */
public class DelayedClockPanel extends ClockPanel{
    

    private final Color ccolor = Color.white;
    private final Color ncolor = Color.black;
    private final Color shcolor = Color.black;
    private final Color mhcolor = Color.black;
    private final Color hhcolor = Color.black;
    private final Color lmhcolor = Color.red;
    private final Color lhhcolor = Color.red;
    private final Color bgcolor = Color.white;
    private final Font font = new Font("TimesRoman", Font.PLAIN, 10);

    private final double sa = Math.PI / 2;
    private final double sda = Math.PI / 30;
    private final double mda = sda / 60;
    private final double hda = mda / 12;
    private final double nda = Math.PI / 6;
    
    private final int border = 10;
    private final int elementsHeight = 80;

    int hour;
    int minute;
    int second;

    int latetime;
    int latehour;
    int lateminute;	
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSlider jSlider1;
    
    public DelayedClockPanel() {
        super();
        setPreferredSize( new Dimension(300, 300 + elementsHeight));
        setBackground(Color.white);
        latetime = 0;
        initComponents();
    }
    
    @Override
    public void displayTime(int hour, int minute, int second) {
        this.latehour = hour + Math.round((minute+latetime) / 60);
        this.lateminute = (minute+latetime) % 60;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        repaint();
    }
    
    private void initComponents() {
        jSlider1 = new javax.swing.JSlider();        
        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMinorTickSpacing(2);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(0);
        jSlider1.setBackground(bgcolor);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        
        jLabel1 = new javax.swing.JLabel();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("By how many minutes are you late?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 223, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
            )
        );
    }
    

    
    protected void paintComponent(Graphics g) {
        // Some geometric calculations.
        g.setColor(bgcolor);
        int width = getWidth();
        int height = getHeight();
        g.fillRect(0, 0, width, height);
        int cWidth = this.getWidth()/ 2 - border;
        int cHeight = (this.getHeight() - elementsHeight) / 2 - border;
        int r = Math.min(cWidth, cHeight);
        Point c = new Point(r + border, r + border);

        // Draw the clock components.
        drawClockNumbers(g, c, r);
        drawSecondHand(g, c, r, second);
        drawMinuteHand(g, c, r, minute, lateminute, second);
        drawHourHand(g, c, r, hour, latehour, minute, lateminute, second);
    }

    
    /**
     * Draws the clock numbers.
     */
    private void drawClockNumbers(Graphics g, Point c, int r) {
            // Draw the clock circle.
            g.setColor(ccolor);
            g.fillOval(c.x - r, c.y - r, 2 * r, 2 * r);
            g.setColor(ncolor);
            g.drawOval(c.x - r, c.y - r, 2 * r, 2 * r);

            // Draw the clock numbers.
            FontMetrics fm = getFontMetrics(font);
            int fa = fm.getMaxAscent();
            int fh = (fm.getMaxAscent() + fm.getMaxDescent()) / 2;
            int nr = (80 * r) / 100;
            for (int i = 0; i < 12; i++) {
                    String ns = Integer.toString((i == 0) ? 12 : i);
                    int nx = (int) ((Math.cos((i * nda) - sa) * nr) + c.x);
                    int ny = (int) ((Math.sin((i * nda) - sa) * nr) + c.y);
                    int w = fm.stringWidth(ns) / 2;

                    g.drawString(ns, nx - w, ny + fa - fh);
            }
    }

    /**
     * Draws the clock's second hand.
     */
    private void drawSecondHand(Graphics g, Point c, int r, int s) {
            int sr = (int) (r*.9);
            int sx = (int) ((Math.cos((s * sda) - sa) * sr) + c.x);
            int sy = (int) ((Math.sin((s * sda) - sa) * sr) + c.y);

            g.setColor(shcolor);
            g.drawLine(c.x, c.y, sx, sy);
    }

    /**
     * Draws the clock's minute hand.
     */
    private void drawMinuteHand(Graphics g, Point c, int r, int m, int lm, int s) {
            int ms = m * 60;
            int mr = (int) (.9 * (r/2));
            int mx = (int) ((Math.cos(((ms + s) * mda) - sa) * mr) + c.x);
            int my = (int) ((Math.sin(((ms + s) * mda) - sa) * mr) + c.y);
            //Also tried to implement it with fillPolygon but result was not
            // good unfortunately
            g.setColor(mhcolor);
            g.drawLine(c.x, c.y-3, mx, my-3);
            g.drawLine(c.x-3, c.y, mx-1, my);
            int lms = lm * 60;
            int lmr = (int) (.9 * (r/3));
            int lmx = (int) ((Math.cos(((lms + s) * mda) - sa) * lmr) + mx);
            int lmy = (int) ((Math.sin(((lms + s) * mda) - sa) * lmr) + my);
            g.setColor(lmhcolor);
            g.drawLine(mx,my-3, lmx, lmy);
            g.drawLine(mx-3,my, lmx, lmy);
    }

    /**
     * Draws the clock's hour hand.
     */
    private void drawHourHand(Graphics g, Point c, int r, int h, int lh, int m, int lm, int s) {
            int ms = m * 60;
            int hs = h * 60 * 60;
            int hr = (int) (.7 * (r/2));
            int hx = (int) ((Math.cos(((hs + ms + s) * hda) - sa) * hr) + c.x);
            int hy = (int) ((Math.sin(((hs + ms + s) * hda) - sa) * hr) + c.y);

            g.setColor(hhcolor);
            g.drawLine(c.x, c.y-3, hx, hy-3);
            g.drawLine(c.x-3, c.y, hx-3, hy);

            int lms = lm * 60;
            int lhs = lh * 60 * 60;
            int lhr = (int) (.7 * (r/3));
            int lhx = (int) ((Math.cos(((lhs + lms + s) * hda) - sa) * lhr) + hx);
            int lhy = (int) ((Math.sin(((lhs + lms + s) * hda) - sa) * lhr) + hy);

            g.setColor(lhhcolor);
            g.drawLine(hx, hy-3, lhx, lhy);
            g.drawLine(hx-3, hy, lhx, lhy);
    }
    
    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {
        JSlider source = (JSlider)evt.getSource();
        latetime = (int)source.getValue();
    }
    
}
