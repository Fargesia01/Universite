/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock.particle.clock;

import clock.particle.attraction.Attractor;
import clock.particle.collision.Collider;
import clock.particle.magnet.Magnet;
import clock.particle.app.Painter;
import java.awt.Color;
import java.awt.Graphics2D;
import clock.particle.particles.Particle;

/**
 *
 * @author Dorian
 */
public class ClockModelPainter extends Painter{
    
    private ClockModel cm;
    
    private boolean paintMagnets;
    
    private Color backGroundColor;
    private Color particleColor;

    /**
     * Creates a new painter for the given clock model
     * @param cm
     *      The clock model to paint
     */
    public ClockModelPainter(ClockModel cm) {
        this.cm = cm;
        paintMagnets = false;
        
        
        backGroundColor = Color.LIGHT_GRAY;
        particleColor = Color.YELLOW; //On pourra toujours faire une fonction de la position, je laisse ça pour le moment
    }
    
    /**
     * Paints the model on g
     * @param g 
     */
    @Override
    public void paint(Graphics2D g){   
        g.setColor(backGroundColor);
        g.fillRect(0, 0, cm.getWidth(), cm.getHeight());
        
        cm.getParticles().forEach(p -> paintParticle(p, g));
        
        
        if(paintMagnets){
            cm.getMagnets().forEach(m -> paintMagnet(m, g));
        }
    }
    
    private void paintParticle(Particle p, Graphics2D g){
        Graphics2D g2d = (Graphics2D) g.create();
        if(p.isStopped()){
            g2d.setColor(Color.BLACK); // on fait comment pour la couleur finalement ?
        }else{
            float colorSpeedIntensity = (float)p.getVel().norm()/p.getMaxVel()/2+(float)0.5;
            //setColor(new Color((float)0.5, colorSpeedIntensity, 0));
            //setColor(new Color(colorSpeedIntensity, colorSpeedIntensity, colorSpeedIntensity));
            int s = 2000;
            float c1 = (float) p.getLoc().x()%s /s;
            float c2 = (float) p.getLoc().y()%s /s;
            float c3 = (float) p.getLoc().norm()%s /s;
            //System.out.println(c1 +" "+ c2 + " "+ c3);
            //System.out.println("1-c3 "+(1-c3)+" | speedIntensity: "+colorSpeedIntensity);
            g2d.setColor(new Color((1-c3)*colorSpeedIntensity, (1-c3)*colorSpeedIntensity, (c3)*colorSpeedIntensity));
            //g2d.setColor(particleColor); // pour le moment, une couleur à la con
                                         // on fait comment pour la couleur finalement ?
        }
        g2d.fillOval((int)(p.getLoc().x()-(p.getSize().x()/2)), (int)(p.getLoc().y()-(p.getSize().y()/2)), (int)p.getSize().x(), (int)p.getSize().y());
        //g2d.fillRect((int)(loc.x()-(size.x()/2)), (int)(loc.y()-(size.y()/2)), (int)size.x(), (int)size.y());

        g2d.dispose();
    }
    
    private void paintMagnet(Magnet m, Graphics2D g){
        Graphics2D g2d = (Graphics2D) g.create();
        for(Attractor p : m.getAttractors()){
            paintAttractor(p, g);
        }
        for(Collider s : m.getColliders()){
            paintCollider(s, g);
        }
        g2d.dispose();
    }
    
    private void paintAttractor(Attractor p, Graphics2D g){
        Graphics2D g2d = (Graphics2D) g.create();
        
        g2d.setColor(Color.blue.brighter());
        g2d.fillOval((int)p.getPos().x()-10, (int)p.getPos().y()-10, 20, 20);

        g2d.dispose();
    }
    
    private void paintCollider(Collider c, Graphics2D g){
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawLine((int) c.startPoint().x(), (int) c.startPoint().y(), (int) c.endPoint().x(), (int) c.endPoint().y());

        g2d.dispose();
    }
    
    public void setPaintMagnets(boolean paintMagnets){
        this.paintMagnets = paintMagnets;
    }
    
    public void setBackgroundColor(Color c){
        backGroundColor = c;
    }
    
    public boolean isPaintingMagnets(){
        return paintMagnets;
    }
}