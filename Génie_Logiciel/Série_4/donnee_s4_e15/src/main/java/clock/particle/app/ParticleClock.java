package clock.particle.app;

import clock.ClockFrame;
import clock.Observer;
import clock.ClockPanel;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.swing.JFrame;
import clock.particle.clock.ClockModel;
import clock.particle.clock.ClockModelPainter;
import clock.timer.ClockTimer;
import clock.util.PositionManager;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicBoolean;
 

/**
 * A particle clock. 
 * 
 * @authors Jean Lanbert-Hétault et Dorian Guyot, adapté par Pascal Gremaud
 */

public final class ParticleClock extends JFrame implements Runnable, Observer{
    
    private final int width;
    private final int height;
    private Canvas canvas;
    private BufferStrategy bufferstrat = null;
        
    private ClockModel clockModel;
    
    private boolean running;
    private int fps = 0;
    private int lastFpsTime = 0;
    private int actualFps = 0;
    
    private Calendar cal;
    
    private boolean deleteMagnets;
    private boolean updateTime;
    
    private final ClockModelPainter cmp;
    private final DebugPainter dp;
    
    private ClockTimer timer;
    private Thread thread;
    
    // Control the thread state using an atomic variable
    private final AtomicBoolean threadRunning = new AtomicBoolean(false);
    
 
    //public ParticleClock(int width, int height){
    public ParticleClock(ClockTimer timer){
        this.timer = timer;
        
        running = true;
        threadRunning.set(true);
        
        deleteMagnets = false;
        updateTime = false;
        
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        DisplayMode dm = gs[0].getDisplayMode();
        
        width = (int)(dm.getWidth() * 0.6);
        height = (int)(dm.getHeight() * 0.4);
        
        canvas = new Canvas();
        canvas.setIgnoreRepaint(true);
        int nHeight = dm.getHeight() / 2;
        int nWidth = dm.getWidth() / 2;
        setBounds(nWidth-(width/2), nHeight-(height/2), width, height);
        canvas.setBounds(nWidth-(width/2), nHeight-(height/2), width, height);
 
        add(canvas);
        setVisible(true);
        
        canvas.createBufferStrategy(2);
        canvas.requestFocus();
        bufferstrat = canvas.getBufferStrategy();
        
        clockModel = new ClockModel(width, height);
        
        cmp = new ClockModelPainter(clockModel);
        dp = new DebugPainter(this);
        
        cal = new GregorianCalendar();
        
        setTitle("Particle Clock");
        setIgnoreRepaint(true);
        setResizable(false);
        
        setVisible(true);
        pack();
        setupInputEvents();
        addWindowListener(new DetachOnClosingWindowListener());
        timer.attach(this);
        PositionManager.getUniqueInstance().addFrameToLayout(this);
        
    }
    
    public void startClock(){
        thread = new Thread(this);
        thread.start();
    }
    
    
    public void setupInputEvents(){
        canvas.addMouseListener(new MouseListener(){
 
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        canvas.addKeyListener(new KeyListener(){//keylistener
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                switch (code) {
                    case 'P':
                        running = !running;
                        break;
                    case 'R':
                        //reset
                        break;
                    case 'E':
                        clockModel.setTime(Calendar.getInstance(), true);
                        break;
                    case 'M':
                        cmp.setPaintMagnets(!cmp.isPaintingMagnets());
                        break;
                    case 'F':
                        dp.toggleFps();
                        break;
                    case 'D':
                        cmp.setPaintMagnets(!cmp.isPaintingMagnets());
                        dp.toggleFps();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
 
        });
    }
    
    @Override
    public void run(){
        // Never stop the thread, instead check whether or not it should run manually
        while(threadRunning.get()){
            long lastLoopTime = System.nanoTime();
            final int TARGET_FPS = 60;
            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;


            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

             // update our FPS counter if a second has passed since
            // we last recorded
            if (lastFpsTime >= 1000000000){
               actualFps = fps;
               lastFpsTime = 0;
               fps = 0;
            }

            // update the game logic
            if(running){
                clockModel.update(delta);
                // if a key was pressed
                update(delta);
            }

            // draw everyting
            render();

            // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
            try{
                long timeToWait = lastLoopTime + OPTIMAL_TIME - System.nanoTime();
                if((timeToWait > 0)){
                    Thread.sleep( timeToWait/1000000 );
                }
            } catch (InterruptedException ex) {
                      Logger.getLogger(ParticleClock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

 
    public void update(double delta){        
        if(deleteMagnets){
            clockModel.deleteAllMagnets();
            clockModel.scatterAll();
            deleteMagnets = false;
        }
        if(updateTime){
            updateTime = false;
            clockModel.setTime(cal);
        }
    }
 
    public void render(){
        do{
            do{
                //this.setTitle(String.valueOf(actualFps));
                Graphics2D g2d = (Graphics2D) bufferstrat.getDrawGraphics();
                cmp.paint(g2d);
                dp.paint(g2d);
                g2d.dispose();
             }while(bufferstrat.contentsRestored());
              bufferstrat.show();
        }while(bufferstrat.contentsLost());
    }
    
    @Override
    public void update() {
        int hour = timer.getHour();
        int minute = timer.getMinute();
        int second = timer.getSecond();
        cal.set(0,0,0, hour, minute, second);
        updateTime = true;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFps() {
        return actualFps;
    }

    private class DetachOnClosingWindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            timer.detach(ParticleClock.this);
            PositionManager.getUniqueInstance().removeFrameFromLayout(ParticleClock.this);
            threadRunning.set(false);
        }
    }

}