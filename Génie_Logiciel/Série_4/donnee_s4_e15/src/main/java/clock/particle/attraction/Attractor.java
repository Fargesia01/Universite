/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock.particle.attraction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import clock.particle.utilities.V2D;

/**
 *
 * @author Dorian
 */
public class Attractor{
    private final V2D pos;

    public Attractor(V2D pos) {
        this.pos = pos;
    }
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        
        g2d.setColor(Color.blue.brighter());
        g2d.fillOval((int)pos.x()-10, (int)pos.y()-10, 20, 20);

        g2d.dispose();
    }

    public V2D getPos() {
        return pos;
    }
    
}
