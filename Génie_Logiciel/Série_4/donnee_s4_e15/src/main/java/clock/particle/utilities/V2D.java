/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock.particle.utilities;

/**
 *
 * @author Dorian
 */
public class V2D {
    private final double x;
    private final double y;    

    public V2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return 
     *      The x component of the vector
     */
    public double x(){
        return x;
    }
    
    /**
     * @return The y component of the vector
     */
    public double y(){
        return y;
    }
    
    /**
     * Adds d to all components of the vector
     * @param d
     *      The value to add
     * @return a new vector
     */
    public V2D plus(double d){
        return new V2D(x+d, y+d);
    }
    
    /**
     * Adds the vector d to the vector componentwise
     * @param d
     *      The vector to add
     * @return a new vector
     */
    public V2D plus(V2D d){
        return new V2D(x + d.x, y+d.y);
    }
    
    /**
     * Subtracts d to all components of the vector
     * @param d
     *      The value to subtract
     * @return a new vector
     */
    public V2D minus(double d){
        return new V2D(x - d, y - d);
    }
    
    /**
     * Subtracts the vector d to the vector componentwise
     * @param d
     *      The vector to subtract
     * @return a new vector
     */
    public V2D minus(V2D d){
        return new V2D(x - d.x, y - d.y);
    }
    
    /**
     * Multiplies d all components of the vector by d
     * @param d
     *      The value to multiply by
     * @return a new vector
     */
    public V2D mult(double d){
        return new V2D(x*d, y*d);
    }
    
    /**
     * Multiplies the vector d to the vector componentwise
     * @param d
     *      The vector to multiply with
     * @return a new vector
     */
    public V2D mult(V2D d){
        return new V2D(x*d.x, y*d.y);
    }
    
    /**
     * Divides all components of the vector by d
     * @param d
     *      The value to divide by
     * @return a new vector
     */
    public V2D div(double d){
        return new V2D(x/d, y/d);
    }
    
    /**
     * Divides the vector d to the vector componentwise
     * @param d
     *      The vector to divide by
     * @return a new vector
     */
    public V2D div(V2D d){
        return new V2D(x/d.x, y/d.y);
    }
    
    /**
     * Computes the norm of the vector
     * @return the norm
     */
    public double norm(){
        return Math.sqrt(x*x + y*y);
    }
    
    /**
     * Returns a normalized vector with the same direction
     * @return a normalized vector
     */
    public V2D normalized(){
        if(this.x == 0 || this.y == 0){
            return new V2D(0,0);
        }
        return div(norm());
    }
    
    /**
     * Computes the distance between this vector and the other
     * @param other
     *      The other vector
     * @return the distance
     */
    public double distTo(V2D other){
        return other.minus(this).norm();
    }
    
    @Override
    public String toString(){
        return "("+x+","+y+")";
    }
    
    @Override
    public boolean equals(Object other){
        if(other instanceof V2D){
            V2D o = (V2D) other;
            return this.x == o.x && this.y == o.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }
    
    public boolean isZero(){
        return x == 0 && y == 0;
    }
}
