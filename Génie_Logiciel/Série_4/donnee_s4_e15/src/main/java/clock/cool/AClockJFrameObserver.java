package clock.cool;


import clock.util.PositionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

/**
 * Abstract class AClockJFrameObserver.
 * This class contains a ClockTimerObservable.
 * When instantiated, it is added to the ClockTimerObservable with addObserver(this).
 * Every tick, the ClockTimerObservable notify its Observers.
 */
public abstract class AClockJFrameObserver extends JFrame implements IObserver {

    private final ClockTimerObservable observableTimer;         // Observable
    private final AClockJPanel clockJPanel;                     // ClockJPanel to paint
    private static int openedClocks = 0;                        // Opened Clocks Tracker
    private static Logger loggingService = Logger.getLogger("Clock Observer");  // Logger

    /**
     * Construct the ClockJFrameObserver
     * @param observableTimer a ClockTimerObservable
     * @param title a String title for the JFrame
     */
    protected AClockJFrameObserver(ClockTimerObservable observableTimer, String title) {
        this.observableTimer = observableTimer;                     // Create a reference to the Observable (~= pointer)
        this.observableTimer.addObserver(this);                     // Add the Observer to the Observable
        this.clockJPanel = createClockPanel();                      // Factory Method to create a ClockPanel
        updateClockJPanel();                                        // Update the time when clock is created
        openedClocks++;                                             // Increment the tracker
        setJFrame(title);                                           // JFrame settings
        //loggingService.info("Opened clocks : " + openedClocks);     // Print the amount of opened clocks
    }

    /**
     * JFrame settings
     * @param title a String title for the JFrame
     */
    private void setJFrame(String title) {
        setTitle(title);                                                            // Set Title
        addWindowListener(new DetachOnClosingWindowListener());                     // Detach the observer
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                          // Close operation
        setLocation(PositionManager.getUniqueInstance().getClockWindowPosition());  // Location with Singleton
        getContentPane().add(this.clockJPanel, BorderLayout.CENTER);                // Add Panel to JFrame
        pack();                                                                     // Pack the JFrame
        setVisible(true);                                                           // Display the window
    }

    /**
     * DESIGN PATTERN : FACTORY METHOD
     * Each subclass has to define this factory method
     * @return a new ClockJPanel
     */
    protected abstract AClockJPanel createClockPanel();

    /**
     * Update the clock panel time
     */
    private void updateClockJPanel() {
        this.clockJPanel.setTime(
                this.observableTimer.getHour(),
                this.observableTimer.getMinute(),
                this.observableTimer.getSecond()
        );
        this.clockJPanel.repaint();
    }

    /**
     * Update is called when an Observable subject changes
     */
    @Override
    public void update() {
        updateClockJPanel();
    }

    /**
     * A window listener that detaches the clock from the timer
     * when the window is being closed
     */
    private class DetachOnClosingWindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            observableTimer.deleteObserver(AClockJFrameObserver.this);
            openedClocks--;
            //loggingService.info("Opened clocks : " + openedClocks);     // Print the amount of opened clocks
            if (openedClocks == 0) {
                //loggingService.info("Application closing");
                System.exit(0);
            }
        }
    }

}
