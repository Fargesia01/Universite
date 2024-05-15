package clock.util;

import java.awt.Point;
import java.util.Vector;
import javax.swing.JFrame;

/**
 * A singelton for managing the positions of application windows. The actual 
 * positioning strategy is delegated to an implementation of layout strategy.
 * 
 * @author Andreas Ruppen
 */
public class PositionManager {
    private static PositionManager INSTANCE = new PositionManager();
    
    /**
	 * @uml.property  name="layoutStrategy"
	 * @uml.associationEnd  
	 */
    private LayoutStrategy layoutStrategy = new SimpleLayoutStrategy();
    private Vector<JFrame> placedFrames = new Vector<JFrame>();
    
    /**
	 * @uml.property  name="mainWindowPosition"
	 */
    private Point mainWindowPosition;

    /**
     * Creates a new instance of <code>PositionManager</code>.
     */
    protected PositionManager() {
        mainWindowPosition = new Point (100, 100);
    }
    
    /**
     * Returns the single instance of the <code>PositionManager</code> class.
     */
    public static PositionManager getUniqueInstance() {
        return INSTANCE;
    }
    
    /**
	 * Returns the position for the main window.
	 * @uml.property  name="mainWindowPosition"
	 */
    public Point getMainWindowPosition() {
        return mainWindowPosition;
    }
    
    /**
     * Adds a new frame to the layout.
     */
    public void addFrameToLayout(JFrame frame) {
        placedFrames.addElement(frame);
        layoutStrategy.addFrameToLayout(placedFrames, frame);
    }
    
    /**
     * Removes a frame from the layout.
     */
    public void removeFrameFromLayout(JFrame frame) {
        placedFrames.remove(frame);
        layoutStrategy.removeFrameFromLayout(placedFrames, frame);
    }
     
    /**
	 * Sets the layout strategy.
	 * @uml.property  name="layoutStrategy"
	 */
    public void setLayoutStrategy(LayoutStrategy newLayoutStrategy) {
        this.layoutStrategy = newLayoutStrategy;
        layoutStrategy.doLayout(placedFrames);
    }


    public Point getClockWindowPosition() {
        return mainWindowPosition;

    }
}