/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock.particle.particles;

import clock.particle.attraction.Attractor;
import clock.particle.collision.Collider;
import clock.particle.magnet.Magnet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import clock.particle.utilities.V2D;

/**
 *
 * @author Jean
 */
public class ParticleSystem {
    
    private List<Particle> particles;
    private List<Magnet> magnets;
    private double accelerationFactor;
    private double maxVel;
    private double hitProbability;

    /**
     * Creates a new particle system from the given particles and the given magnets
     * @param width
     * @param height
     * @param nbParticles
     * @param accelerationFactor
     * @param maxVel
     * @param hitProbability
     */
    public ParticleSystem(int width, int height, int nbParticles, double accelerationFactor, double maxVel, double hitProbability) {
        this.particles = Particle.scatterParticles(width, height, nbParticles);
        this.magnets = new ArrayList<>();
        this.accelerationFactor = accelerationFactor;
        this.maxVel = maxVel;
        this.hitProbability = hitProbability;
    }
    
    public ParticleSystem(int width, int height, int nbParticles){
        this(width, height, nbParticles, 20, 30, 0.6);
    }

    /**
     * Updates the magnets of the system
     * @param magnets
     *      The new magnets of the system
     */
    public void setMagnets(List<Magnet> magnets) {
        this.magnets = magnets;
    }
    
    /**
     * Updates the positions of the particles
     * @param delta
     *      Elapsed time since last update
     * @deprecated Update with threads instead (see ClockModel.update())
     */
    public void update(double delta){
        // TRAJECTOIRE
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
        particles.forEach((p) -> p.update(delta));
        
        // COLLISIONS
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
    }

    /**
     * Remoces all magnets from the particle system
     */
    public void deleteMagnets() {
        magnets.clear();
    }

    /**
     * Frees and gives a random velocity to all particles in the system
     */
    public void scatter() {
        for(Particle p : particles){
            p.free();
            p.setVel(new V2D((Math.random()*2-1)*3*p.getMaxVel(), (Math.random()*2-1)*3*p.getMaxVel()));
        }
    }
    
    /**
     * Frees and gives the velocity vel and a random direction to all particles in the system
     */
    public void scatter(double vel) {
        for(Particle p : particles){
            p.free();
            p.setVel(new V2D((Math.random()*2-1)*3*vel, (Math.random()*2-1)*3*vel));
        }
    }
    
    /**
     * @return The list of particles of the system
     */
    public List<Particle> getParticles(){
        return Collections.unmodifiableList(particles);
    }
    
    /**
     * @return The list of magnets of the system
     */
    public List<Magnet> getMagnets(){
        return Collections.unmodifiableList(magnets);
    }

    public double getAccelerationFactor() {
        return accelerationFactor;
    }

    public double getMaxVel() {
        return maxVel;
    }

    public double getHitProbability() {
        return hitProbability;
    }
    
    
}
