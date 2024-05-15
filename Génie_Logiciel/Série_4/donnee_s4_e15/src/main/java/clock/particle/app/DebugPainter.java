/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock.particle.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Jean
 */
public class DebugPainter extends Painter{
    private ParticleClock window;
    
    private boolean showFps;

    public DebugPainter(ParticleClock window) {
        this.window = window;
        this.showFps = false;
    }

    public void setShowFps(boolean showFps) {
        this.showFps = showFps;
    }

    @Override
    public void paint(Graphics2D g) {
        if(showFps){
            paintFps(g);
        }
    }

    private void paintFps(Graphics2D g){
        int fontSize = 20;
        int offset = 10;
        g.setColor(Color.RED);
        g.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize));
        g.drawString("fps: " + String.valueOf(window.getFps()), offset, fontSize + offset);
    }

    void toggleFps() {
        showFps = !showFps;
    }
}
