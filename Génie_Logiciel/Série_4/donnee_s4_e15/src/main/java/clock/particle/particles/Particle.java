package clock.particle.particles;

import clock.particle.utilities.V2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
 
public class Particle {
 
    private V2D oldLoc;
    private V2D loc;
    private V2D vel;
    private final int maxVel;
    private V2D acc;
    private V2D size;
    private Color color;
    private boolean isStopped;
    
    private static final double PARTICLE_SIZE_RATIO = 0.020;//0.018;
  
    public Particle(double x, double y, double dx, double dy, double size, Color c, int maxVel){
        this.loc = this.oldLoc = new V2D(x,y);
        this.vel = new V2D(dx,dy);
        this.acc = new V2D(0,0);
        this.size = new V2D(size,size);
        this.color = c;
        this.isStopped = false;
        this.maxVel = maxVel;
    }
    
    /**
     * Generates a list of particles, scattered randomly
     * @param n
     *      The number of particles
     * @return 
     *      A list of particles
     */
    public static List<Particle> scatterParticles(int width, int height, int n){
        ArrayList<Particle> l = new ArrayList<>();
        int maxVel = height/64;
        for(int i = 0; i < n; i++){
            l.add(new Particle(Math.random()*width, Math.random()*height,Math.random()*12-6,Math.random()*12-6,PARTICLE_SIZE_RATIO * height,Color.yellow, maxVel));
            //System.out.println(Main.width+ " " + Main.heigth);
        }
        return l;
    }
 
    /**
     * Updates the velocity and the location of the particle
     * @param delta 
     *      Not yet used
     */
    public void update(double delta){
        if(!isStopped){
            vel = vel.plus(acc).mult(0.99); //Le facteur de 0.999 représente la perte d'énergie
            if(vel.norm() > maxVel){
                vel = vel.normalized().mult(maxVel);
            }
            
            oldLoc = loc;
            loc = loc.plus(vel);
        }
        float colorSpeedIntensity = (float)vel.norm()/maxVel;
        //setColor(new Color((float)0.5, colorSpeedIntensity, 0));
        //setColor(new Color(colorSpeedIntensity, colorSpeedIntensity, colorSpeedIntensity));
        int s = 2000;
        float c1 = (float) getLoc().x()%s /s;
        float c2 = (float) getLoc().y()%s /s;
        float c3 = (float) getLoc().norm()%s /s;
        //System.out.println(c1 +" "+ c2 + " "+ c3);
        setColor(new Color((1-Math.abs(c3))*colorSpeedIntensity, (1-Math.abs(c3))*colorSpeedIntensity, (Math.abs(c3))*colorSpeedIntensity));
    }
    
    /**
     * Sets a new location for the particle
     * @param newLoc
     *      The new location
     */
    public void setLoc(V2D newLoc){
        loc = newLoc;
    }
    
    /**
     * Sets a new velocity for the particle
     * @param newVel 
     *      The new velocity
     */
    public void setVel(V2D newVel){
        if(!isStopped){
            if(newVel.norm() > maxVel){
                vel = newVel.normalized().mult(maxVel);
            }else{
                vel = newVel;   
            }
        }
    }
 
    /**
     * Sets a new acceleration for the particle
     * @param newAcc 
     *      The new acceleration
     */
    public void setAcc(V2D newAcc){
        if(!isStopped){
            acc = newAcc;
        }
    }
 
    /**
     * Sets the new size of the particle
     * @param newSize 
     *      The new size
     */
    public void setSize(V2D newSize){
        size = newSize;
    }
 
    /**
     * @return The location of the particle
     */
    public V2D getLoc(){
        return loc;
    }
    
    /**
     * @return The old location of the particle
     */
    public V2D getOldLoc(){
        return oldLoc;
    }
 
    /**
     * @return The velocity of the particle
     */
    public V2D getVel(){
        return vel;
    }
    
    public void setColor(Color c){
        this.color = c;
    }
    
    public void stop(){
        setAcc(new V2D(0, 0));
        setVel(new V2D(0, 0));
        isStopped = true;
    }

    public int getMaxVel() {
        return maxVel;
    }
    
    public void free(){
        isStopped = false;
    }
    
    public boolean isStopped(){
        return isStopped;
    }
    
    public V2D getSize(){
        return size;
    }
}