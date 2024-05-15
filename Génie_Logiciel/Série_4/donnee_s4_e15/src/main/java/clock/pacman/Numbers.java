/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock.pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Cyril Michel
 */
public class Numbers {
    
    private Color balkencolor = Color.blue;
    
    int number;
    int x;
    int y;
    private Graphics g;
    
    public Numbers(Graphics g, int number, int x, int y){
        this.g=g;
        this.number=number;
        this.x=x;
        this.y=y;
        
        zahlzeichnen();
                
    }
    
    private void zahlzeichnen(){
        if (number==1) {
            eins();
        }
        else if (number==2) {
            zwei();
        }
        else if (number==3) {
            drei();
        }
        else if (number==4) {
            vier();
        }
        else if (number==5) {
            fuenf();
        }
        else if (number==6) {
            sechs();
        }
        else if (number==7) {
            sieben();
        }
        else if (number==8) {
            acht();
        }
        else if (number==9) {
            neun();
        }
        else if (number==0) {
            zero();
        }
    }
    
    private void acht (){
        g.setColor(balkencolor);
        g.drawRect(x, y, 40, 10);
        g.drawRect(x+45, y-45, 10, 40);
        g.drawRect(x+45, y+15, 10, 40);
        g.drawRect(x-15, y-45, 10, 40);
        g.drawRect(x-15, y+15, 10, 40);
        g.drawRect(x, y-60, 40, 10);
        g.drawRect(x, y+60, 40, 10);
    }
    
     private void zero (){
        g.setColor(balkencolor);
        
        g.drawRect(x+45, y-45, 10, 40);
        g.drawRect(x+45, y+15, 10, 40);
        g.drawRect(x-15, y-45, 10, 40);
        g.drawRect(x-15, y+15, 10, 40);
        g.drawRect(x, y-60, 40, 10);
        g.drawRect(x, y+60, 40, 10);
    }
     
      private void eins (){
        g.setColor(balkencolor);
        
        g.drawRect(x+45, y-45, 10, 40);
        g.drawRect(x+45, y+15, 10, 40);
        
    }
      
       private void zwei (){
        g.setColor(balkencolor);
        g.drawRect(x, y, 40, 10);
        g.drawRect(x+45, y-45, 10, 40);
        
        g.drawRect(x-15, y+15, 10, 40);
        g.drawRect(x, y-60, 40, 10);
        g.drawRect(x, y+60, 40, 10);
    }
       
        private void drei (){
        g.setColor(balkencolor);
        g.drawRect(x, y, 40, 10);
        g.drawRect(x+45, y-45, 10, 40);
        g.drawRect(x+45, y+15, 10, 40);
        
        g.drawRect(x, y-60, 40, 10);
        g.drawRect(x, y+60, 40, 10);
    }
        
         private void vier (){
        g.setColor(balkencolor);
        g.drawRect(x, y, 40, 10);
        g.drawRect(x+45, y-45, 10, 40);
        g.drawRect(x+45, y+15, 10, 40);
        g.drawRect(x-15, y-45, 10, 40);
        
        
        
    }
         
          private void fuenf (){
        g.setColor(balkencolor);
        g.drawRect(x, y, 40, 10);
        
        g.drawRect(x+45, y+15, 10, 40);
        g.drawRect(x-15, y-45, 10, 40);
        
        g.drawRect(x, y-60, 40, 10);
        g.drawRect(x, y+60, 40, 10);
    }
          
           private void sechs (){
        g.setColor(balkencolor);
        g.drawRect(x, y, 40, 10);
        
        g.drawRect(x+45, y+15, 10, 40);
        g.drawRect(x-15, y-45, 10, 40);
        g.drawRect(x-15, y+15, 10, 40);
        g.drawRect(x, y-60, 40, 10);
        g.drawRect(x, y+60, 40, 10);
    }
           
            private void sieben (){
        g.setColor(balkencolor);
        
        g.drawRect(x+45, y-45, 10, 40);
        g.drawRect(x+45, y+15, 10, 40);
        
        g.drawRect(x, y-60, 40, 10);
        
    }
            
             private void neun (){
        g.setColor(balkencolor);
        g.drawRect(x, y, 40, 10);
        g.drawRect(x+45, y-45, 10, 40);
        g.drawRect(x+45, y+15, 10, 40);
        g.drawRect(x-15, y-45, 10, 40);
        
        g.drawRect(x, y-60, 40, 10);
        g.drawRect(x, y+60, 40, 10);
    }
}
