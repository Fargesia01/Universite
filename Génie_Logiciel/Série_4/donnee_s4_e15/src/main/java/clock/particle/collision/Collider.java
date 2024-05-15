/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock.particle.collision;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import clock.particle.utilities.V2D;

/**
 *
 * @author Dorian
 */
public class Collider{

    private final V2D x1, x2;
    Line2D l;
    public Collider(V2D x1, V2D x2) {
        this.x1 = x1;
        this.x2 = x2;
        l = new Line2D.Double(x1.x(), x1.y(), x2.x(), x2.y());
    }

    
    public boolean collidesWith(V2D y1, V2D y2) {
        Line2D trajectory = new Line2D.Double(y1.x(), y1.y(), y2.x(), y2.y());
        return l.intersectsLine(trajectory);
    }
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawLine((int) x1.x(), (int) x1.y(), (int) x2.x(), (int) x2.y());

        g2d.dispose();
    }
    
    public V2D startPoint(){
        return x1;
    }
    
    public V2D endPoint(){
        return x2;
    }
}
