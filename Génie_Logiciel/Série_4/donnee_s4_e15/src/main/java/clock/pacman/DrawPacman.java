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
public class DrawPacman {
    int sekunden;
    int width;
    int height;
    
    private Graphics g;
    
    private Color pointcolor = Color.yellow;
    
    public DrawPacman (Graphics g, int sekunden, int width, int height){
        this.sekunden=sekunden;
        this.g=g;
        this.height=height;
        this.width=width;
        
        pacmanzeichnen();
    }

    private void pacmanzeichnen() {
        g.setColor(pointcolor);
        
        if (sekunden%2==0) {
             
             if (sekunden<10) {
                g.fillArc((width/2)+sekunden*22,(height/2)-125,40,40,320,-280);
            }
             else if (sekunden<20) {
                g.fillArc((width/2)+205,((height/2)-125)+(sekunden-10)*22,40,40,-50,280);
            }
             else if (sekunden<42) {
                g.fillArc((width/2)+205 -(sekunden-20)*22,((height/2)+95),40,40,-150,280);
            }
             else if (sekunden<52) {
                g.fillArc((width/2)-235 ,((height/2)+95-(sekunden-40)*22),40,40,-230,280);
            }
             else if (sekunden<60) {
                g.fillArc((width/2)-235+(sekunden-50)*22 ,(height/2)-125,40,40,320,-280);
            }
        }
        else if (sekunden%2!=0) {
            
             if (sekunden<10) {
                g.fillArc((width/2)+sekunden*22,(height/2)-125,40,40,0,360);
            }
             else if (sekunden<20) {
                g.fillArc((width/2)+205,((height/2)-125)+(sekunden-10)*22,40,40,-0,360);
            }
             else if (sekunden<40) {
                g.fillArc((width/2)+205 -(sekunden-20)*22,((height/2)+95),40,40,-0,360);
            }
             else if (sekunden<50) {
                g.fillArc((width/2)-235 ,((height/2)+95-(sekunden-40)*22),40,40,-0,360);
            }
             else if (sekunden<60) {
                g.fillArc((width/2)-235+(sekunden-50)*22 ,(height/2)-125,40,40,0,360);
            }
            
        }
    }
}
