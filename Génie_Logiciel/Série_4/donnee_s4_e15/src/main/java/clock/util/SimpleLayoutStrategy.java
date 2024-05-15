package clock.util;

import java.awt.Point;
import java.util.Vector;
import javax.swing.JFrame;

/**
 * A simple window position managing strategy.
 * 
 * @author Andreas Ruppen
 */
public class SimpleLayoutStrategy implements LayoutStrategy {

	/**
	 * @uml.property  name="defaultFramePosition"
	 */
	private Point defaultFramePosition;
    /**
	 * @uml.property  name="currentFramePosition"
	 */
    private Point currentFramePosition;
	
    /**
     * Creates a new instance of <code>SimpleLayoutStrategy</code>.
     */
	public SimpleLayoutStrategy() {
		defaultFramePosition = new Point (280, 80);
        currentFramePosition = new Point(defaultFramePosition);
	}

    /**
     * Adds the given frame to the layout.
     */
	public void addFrameToLayout(Vector<JFrame> frames, JFrame frame){
        frame.setLocation(currentFramePosition);
        currentFramePosition.translate(20, 20);
	}
    
    /**
     * Removes the given frame from the layout.
     */
    public void removeFrameFromLayout(Vector<JFrame> frames, JFrame frame) {
        // Do nothing...
    }
    
    /**
     * Lays out the frames in the given vector.
     */
    public void doLayout(Vector<JFrame> frames) {
        currentFramePosition = new Point(defaultFramePosition);
        for (JFrame frame : frames) {
            addFrameToLayout(frames, frame);
        }
    }
    
    /**
     * Returns a string describing this layout strategy.
     */
    public String getDescription() {
        return "Simple Layout";
    }
}
