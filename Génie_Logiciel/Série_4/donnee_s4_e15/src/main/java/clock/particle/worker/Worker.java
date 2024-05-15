/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock.particle.worker;

import clock.particle.attraction.Attractor;
import clock.particle.collision.Collider;
import clock.particle.magnet.Magnet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import clock.particle.particles.Particle;
import clock.particle.utilities.V2D;

/**
 *
 * @author Jean
 */
public class Worker implements Runnable{
    
    private List<Particle> particles;
    private List<Magnet> magnets;
    private double accelerationFactor;
    private double maxVel;
    private double hitProbability;

    private double delta = 0;
    
    private final boolean DEBUG = false;
    
    public Worker(List<Particle> particles, List<Magnet> magnets, double accelerationFactor, double maxVel, double hitProbability) {
        this.particles = particles;
        this.magnets = magnets;
        this.accelerationFactor = accelerationFactor;
        this.maxVel = maxVel;
        this.hitProbability = hitProbability;
    }
    
    @Override
    public void run() {
        // TRAJECTOIRE
        if(DEBUG)log("Calcul de trajectoire");
        for(Particle p : particles){
            //System.out.println(p.getVel());
            if(p.isStopped()){
                continue;
            }
            V2D acc = new V2D(0, 0);
            for(Magnet m : magnets){
                if(m.isSaturated()){
                    continue;
                }
                for(Attractor a : m.getAttractors()){
                    double dist = p.getLoc().distTo(a.getPos());
                    acc = acc.plus(
                            new V2D(a.getPos().x()-p.getLoc().x(), a.getPos().y()-p.getLoc().y()).normalized().div(dist)
                    );
                }
            }
            p.setAcc(acc.plus(acc.mult(accelerationFactor)));
        }
        /////
        if(DEBUG)log("Actualisation des particules");
        particles.forEach((p) -> p.update(delta));
        
        // COLLISIONS
        if(DEBUG)log("Calcul des collisions");
        for(Magnet m : magnets){
            if(m.isSaturated()){
                continue;
            }
            for(Collider sc : m.getColliders()){
                for(Particle p : particles){
                    if(p.isStopped()){
                        continue;
                    }
                    if(Math.random() > hitProbability){
                        continue;
                    }
                    if(sc.collidesWith(p.getOldLoc(), p.getLoc())){
                        //System.out.println("STOOOOP");
                        p.stop();
                        m.saturate();
                    }
                }
            }
        }
        if(DEBUG){
            log("DONE");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void log(String message){
        System.out.println(Thread.currentThread().getName() +": " + message);
    }
    
}
