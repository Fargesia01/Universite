/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock.particle.magnet;

import clock.particle.attraction.Attractor;
import clock.particle.attraction.Attractor;
import clock.particle.collision.Collider;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import clock.particle.utilities.V2D;

/**
 *
 * @author Dorian
 */
public class Magnet{
    
    private final V2D p1;
    private final V2D p2;
    private final int maxSaturation;
    private int actualSaturation;
    private final List<Attractor> attractors;
    private final List<Collider> colliders;
    
    /**
     * Creates a set of magnets representing the digit
     * @param n
     *      The digit to represent
     * @param pos
     * @param segmentSize
     * @return 
     *      A set of magnets
     */
    public static List<Magnet> getMagnetSetfor(int n, V2D pos, double segmentSize){
        
        List<Magnet> magnets = new ArrayList<>();
        List<Integer> segmentCodes = new ArrayList<>();
        switch(n){
            case 0:
                segmentCodes = Arrays.asList(1,2,3,5,6,7);
                break;
            case 1:
                segmentCodes = Arrays.asList(3,6);
                break;
            case 2:
                segmentCodes = Arrays.asList(1,3,4,5,7);
                break;
            case 3:
                segmentCodes = Arrays.asList(1,3,4,6,7);
                break;
            case 4:
                segmentCodes = Arrays.asList(2,3,4,6);
                break;
            case 5:
                segmentCodes = Arrays.asList(1,2,4,6,7);
                break;
            case 6:
                segmentCodes = Arrays.asList(1,2,4,5,6,7);
                break;
            case 7:
                segmentCodes = Arrays.asList(1,3,6);
                break;
            case 8:
                segmentCodes = Arrays.asList(1,2,3,4,5,6,7);
                break;
            case 9:
                segmentCodes = Arrays.asList(1,2,3,4,6,7);
                break;
            default:
                throw new IllegalArgumentException("The supplied number is not valid: "+n+". It must be a single digit.");
         
        }
        for(int c : segmentCodes){
            magnets.add(displaySegmentMagnet(c, pos, segmentSize));
        }
        return magnets;
    }
    
    public static Magnet displaySegmentMagnet(int n, V2D pos, double segmentSize){
        V2D oneX = new V2D(segmentSize, 0);
        V2D oneY = new V2D(0,segmentSize);
        int magnetSaturation = 50;
        int nbrAttractors = 4;
        switch(n){
            case 1:
                return new Magnet(pos, pos.plus(oneX), nbrAttractors, magnetSaturation, true);
            case 2:
                return new Magnet(pos, pos.plus(oneY), nbrAttractors, magnetSaturation, true);
            case 3:
                return new Magnet(pos.plus(oneX), pos.plus(oneX).plus(oneY), nbrAttractors, magnetSaturation, true);
            case 4:
                return new Magnet(pos.plus(oneY), pos.plus(oneY).plus(oneX), nbrAttractors, magnetSaturation, true);
            case 5:
                return new Magnet(pos.plus(oneY), pos.plus(oneY).plus(oneY), nbrAttractors, magnetSaturation, true);
            case 6:
                return new Magnet(pos.plus(oneX).plus(oneY), pos.plus(oneX).plus(oneY).plus(oneY), nbrAttractors, magnetSaturation, true);
            case 7:
                return new Magnet(pos.plus(oneY).plus(oneY), pos.plus(oneY).plus(oneY).plus(oneX), nbrAttractors, magnetSaturation, true);
            default:
                throw new IllegalArgumentException("The segment number is no valid: "+n+". It must be betwenn 1 and 7 included");
        }
    }
    
    public Magnet(V2D x1, V2D x2, int n, int saturation) {
        this(x1, x2, n, saturation, false);
    }
    
    public Magnet(V2D x1, V2D x2, int n, int saturation, boolean ommitEndAttractors){
        this.p1 = x1;
        this.p2 = x2;
        colliders = new ArrayList<>();
        colliders.add(new Collider(x1, x2));
        
        attractors = new ArrayList<>();
        V2D attractPos = p1;
        V2D attractStep = p2.minus(p1).div(n-1);
        
        if(ommitEndAttractors){
            attractPos = attractPos.plus(attractStep.div(2));
            for(int i = 0; i < n-1; i++){
                attractors.add(new Attractor(attractPos));
                attractPos = attractPos.plus(attractStep);
            }
        }else{
            for(int i = 0; i < n; i++){
                attractors.add(new Attractor(attractPos));
                attractPos = attractPos.plus(attractStep);
            }
        }
        
        
        this.maxSaturation = saturation;
        this.actualSaturation = 0;
    }
    
    public void saturate(){
        if(!isSaturated())
            actualSaturation++;
    }

    public List<Attractor> getAttractors() {
        return Collections.unmodifiableList(attractors);
    }

    public List<Collider> getColliders() {
        return Collections.unmodifiableList(colliders);
    }
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        for(Attractor p : attractors){
            p.render(g);
        }
        for(Collider s : colliders){
            s.render(g);
        }
        g2d.dispose();
    }
    
    public double getSaturation(){
        return ((double)actualSaturation)/maxSaturation;
    }
    
    public boolean isSaturated(){
        return actualSaturation == maxSaturation;
    }
    
    public void reset(){
        actualSaturation = 0;
    }
}
