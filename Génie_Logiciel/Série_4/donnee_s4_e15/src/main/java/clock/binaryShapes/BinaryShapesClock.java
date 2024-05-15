/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock.binaryShapes;

import clock.ClockFrame;
import clock.ClockPanel;
import clock.timer.ClockTimer;

/**
 *
 * @author remogeissbuehler, adapted by Pascal Gremaud
 */
public class BinaryShapesClock extends ClockFrame{

    public BinaryShapesClock(ClockTimer timer) {
        super(timer);
    }
    
    @Override
    protected ClockPanel createClockPanel() {
        BinaryShapesClockPanel clockPanel = new BinaryShapesClockPanel();
        return clockPanel;
    }
    
    protected void setFrameTitle() {
        setTitle("Binary Shapes Clock");
    }
    
}
