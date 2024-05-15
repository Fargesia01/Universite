/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock.particle.clock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import clock.particle.magnet.Magnet;
import clock.particle.worker.Worker;
import clock.particle.particles.Particle;
import clock.particle.particles.ParticleSystem;
import clock.particle.utilities.V2D;

/**
 *
 * @author Jean
 */
public final class ClockModel {
    
    private List<ParticleSystem> particleSystems;
    private final int nbParticles;
    private Calendar actualTime;
    
    private final int width;
    private final int height;
    
    ExecutorService executor;
    
    private enum systems{
        SECONDS, MINUTES, HOURS;
    }

    /**
     * Creates a new clock model
     * @param width
     *      The width of the model
     * @param height 
     *      The height of the model
     */
    public ClockModel(int width, int height){
        actualTime = Calendar.getInstance();
        nbParticles = 600;
        this.width = width;
        this.height = height;        

        ParticleSystem systemSeconds = new ParticleSystem(width, height, nbParticles, 30, 30, 0.9);
        ParticleSystem systemMinutes = new ParticleSystem(width, height, nbParticles, 20, 30, 0.6);
        ParticleSystem systemHours = new ParticleSystem(width, height, nbParticles, 20, 30, 0.4);
        
        particleSystems = new ArrayList<>();
        particleSystems.add(systemSeconds);
        particleSystems.add(systemMinutes);
        particleSystems.add(systemHours);
        //executor = Executors.newFixedThreadPool(3);
        
        setTime(actualTime, true);
    }
    
    /**
     * @return The list of particles of all particle systems of the model
     */
    public List<Particle> getParticles(){
        List<Particle> particles = new ArrayList<>();
        for(ParticleSystem ps : particleSystems){
            particles.addAll(ps.getParticles());
        }
        return Collections.unmodifiableList(particles);
    }
    
    /**
     * @return The list of magnets of all particle systems of the model
     */
    public List<Magnet> getMagnets(){
        List<Magnet> magnets = new ArrayList<>();
        for(ParticleSystem ps : particleSystems){
            magnets.addAll(ps.getMagnets());
        }
        return magnets;
    }
    
    /**
     * Deletes all the magnets from the particle systems of the model
     */
    public void deleteAllMagnets() {
        for (ParticleSystem particleSystem : particleSystems) {
            particleSystem.deleteMagnets();
        }
    }
    
    /**
     * Deletes the magnets from the hours particle system
     */
    public void deleteMagnetsHours() {
        particleSystems.get(systems.HOURS.ordinal()).deleteMagnets();
    }
    
    /**
     * Deletes the magnets from the minutes particle system
     */
    public void deleteMagnetsMinutes() {
        particleSystems.get(systems.MINUTES.ordinal()).deleteMagnets();
    }
    
    /**
     * Deletes the magnets from the seconds particle system
     */
    public void deleteMagnetsSeconds() {
        particleSystems.get(systems.SECONDS.ordinal()).deleteMagnets();
    }

    /**
     * Scatters the particles of all the particle systems of the model
     */
    public void scatterAll() {
        for (ParticleSystem particleSystem : particleSystems) {
            particleSystem.scatter();
        }
    }
    
     /**
     * Scatters the particles of the seconds particle systems of the model
     */
    public void scatterSeconds(){
        particleSystems.get(systems.SECONDS.ordinal()).scatter();
    }
    
    /**
     * Scatters the particles of the minutes particle systems of the model
     */
    public void scatterMinutes(){
        particleSystems.get(systems.MINUTES.ordinal()).scatter();
    }
    
    /**
     * Scatters the particles of the hours particle systems of the model
     */
    public void scatterHours(){
        particleSystems.get(systems.HOURS.ordinal()).scatter();
    }
    
    // SET TIME
    public void setTime(Calendar newTime) {
        setTime(newTime, false);
    }
    
    public void setTime(Calendar newTime, boolean forceUpdate) {
        int digitSpace = 50;
        int magnet_size = (width - 3*digitSpace)/10;
        int yPos = (height - 2*magnet_size)/2;
        if( forceUpdate || newTime.get(Calendar.HOUR_OF_DAY) != actualTime.get(Calendar.HOUR_OF_DAY)){
            int hours = newTime.get(Calendar.HOUR_OF_DAY);
            
            particleSystems.get(systems.HOURS.ordinal()).deleteMagnets();
            particleSystems.get(systems.HOURS.ordinal()).scatter();
            
            Timer t = new Timer("timer", true);
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    List<Magnet> newMagnets = new ArrayList<>();
                    newMagnets.addAll(Magnet.getMagnetSetfor(hours/10, new V2D(magnet_size, yPos), magnet_size));
                    newMagnets.addAll(Magnet.getMagnetSetfor(hours%10, new V2D(2*magnet_size + digitSpace, yPos), magnet_size));
                    particleSystems.get(systems.HOURS.ordinal()).setMagnets(newMagnets);
                }
            }, 500);
        }
        if( forceUpdate || newTime.get(Calendar.MINUTE) != actualTime.get(Calendar.MINUTE)){
            int minutes = newTime.get(Calendar.MINUTE);
            
            particleSystems.get(systems.MINUTES.ordinal()).deleteMagnets();
            particleSystems.get(systems.MINUTES.ordinal()).scatter();
            
            Timer t = new Timer("timer", true);
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    List<Magnet> newMagnets = new ArrayList<>();
                    newMagnets.addAll(Magnet.getMagnetSetfor(minutes/10, new V2D(4*magnet_size + digitSpace, yPos), magnet_size));
                    newMagnets.addAll(Magnet.getMagnetSetfor(minutes%10, new V2D(5*magnet_size + 2*digitSpace, yPos), magnet_size));
                    particleSystems.get(systems.MINUTES.ordinal()).setMagnets(newMagnets);
                }
            }, 500);            
        }
        if( forceUpdate || newTime.get(Calendar.SECOND) != actualTime.get(Calendar.SECOND)){
            int seconds = newTime.get(Calendar.SECOND);
            
            particleSystems.get(systems.SECONDS.ordinal()).deleteMagnets();
            particleSystems.get(systems.SECONDS.ordinal()).scatter(3);
            
            Timer t = new Timer("timer", true);
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    List<Magnet> newMagnets = new ArrayList<>();
                    newMagnets.addAll(Magnet.getMagnetSetfor(seconds/10, new V2D(7*magnet_size + 2*digitSpace, yPos), magnet_size));
                    newMagnets.addAll(Magnet.getMagnetSetfor(seconds%10, new V2D(8*magnet_size + 3*digitSpace, yPos), magnet_size));
                    particleSystems.get(systems.SECONDS.ordinal()).setMagnets(newMagnets);

                }
            }, 200);
        }
        actualTime = (GregorianCalendar) newTime.clone();
    }
    
    // UPDATE PHYSICAL MODEL
    public void update(double delta) {
        
        //System.out.println("UPDATE");
        executor = Executors.newWorkStealingPool();
        for (ParticleSystem ps : particleSystems) {
            //System.out.println("PS");
            Runnable worker = new Worker(ps.getParticles(), ps.getMagnets(), ps.getAccelerationFactor(), ps.getMaxVel(), ps.getHitProbability());
            executor.submit(worker);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClockModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* OLD METHOD
        for (ParticleSystem ps : particleSystems) {
            ps.update(delta);
        }
        */
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}
