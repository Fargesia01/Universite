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
public class Sekunden {
    
    int sekunden;
    int width;
    int height;
    
    private Graphics g;
    
    private Color pointcolor = Color.white;
    
    public Sekunden (Graphics g, int sekunden, int width, int height){
        this.sekunden=sekunden;
        this.g=g;
        this.height=height;
        this.width=width;
        
        sekundenzeichnen();
    }

    private void sekundenzeichnen() {
         g.setColor(pointcolor);
        int zahler1=-22;
        while (sekunden<60 && zahler1>=-220) {            
            g.fillOval((width/2)+zahler1, (height/2)-110, 7, 7);
            zahler1=zahler1-44;
            sekunden=sekunden+2;
        }
        int zahler2=-88;
        while (sekunden<60 && zahler2<=110) {            
            g.fillOval((width/2)-220, (height/2)+zahler2, 7, 7);
            zahler2=zahler2+44;
            sekunden=sekunden+2;
        }
        int zahler3=-200;
        while (sekunden<60 && zahler3<=220) {            
            g.fillOval((width/2)+zahler3, (height/2)+110, 7, 7);
            zahler3=zahler3+44;
            sekunden=sekunden+2;
        }
        int zahler4=90;
        while (sekunden<60 && zahler4>=-90) {            
            g.fillOval((width/2)+220, (height/2)+zahler4, 7, 7);
            zahler4=zahler4-44;
            sekunden=sekunden+2;
        }
        int zahler5=200;
        while (sekunden<60 && zahler5>=0) {            
            g.fillOval((width/2)+zahler5, (height/2)-110, 7, 7);
            zahler5=zahler5-44;
            sekunden=sekunden+2;
        }
        
        
       
        
       
        
    }
}
