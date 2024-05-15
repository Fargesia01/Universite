package clock.tron;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

import java.awt.Dimension;

/**
 * An digital clock observing a clock timer.
 *
 * @author Ryan
 */
public class TronClock extends ClockFrame {

    public TronClock(ClockTimer timer) {
        super(timer);
        /**
        // Create and set up the window.
        setTitle("Digital Clock");
        addWindowListener(new DetachOnClosingWindowListener());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the analog clock panel.
        tronClockPanel = new TronClockPanel();
        tronClockPanel.setPreferredSize(new Dimension(240, 300));

        // Add the panel to the window.
        getContentPane().add(tronClockPanel, BorderLayout.CENTER);

        // Set screen position.
        setLocation(PositionManager.getUniqueInstance().getClockWindowPosition());

        // Display the window.
        pack();
        setVisible(true);
        **/
    }


    /**
     * Creates a new instance of <code>TronClock</code> that observes the
     * given clock timer. constructor
     */
        protected ClockPanel createClockPanel()
    {
         TronClockPanel clockPanel = new TronClockPanel();
         clockPanel.setPreferredSize(new Dimension(240, 300));
         return clockPanel;
    }
    
    protected void setFrameTitle() {
        setTitle("Tron Clock");
    }

}
