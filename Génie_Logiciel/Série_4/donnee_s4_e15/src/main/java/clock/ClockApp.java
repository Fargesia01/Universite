package clock;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import clock.analog.AnalogClock;
import clock.bargraph.BarGraphClock;
import clock.binary.BinaryClock;
import clock.binaryShapes.BinaryShapesClock;
import clock.circular.CircularClock;
import clock.cool.CoolClock;
import clock.digital.DigitalClock;
import clock.digitalNG.DigitalClockNG;
import clock.digitalLCD.DigitalClockLCD;
import clock.delayed.DelayedClock;
import clock.roman.RomanClock;
import clock.hourglass.HourGlassClock;
import clock.pacman.PacmanClock;
import clock.particle.app.ParticleClock;
import clock.sbb.SBBClock;
import clock.spider.SpiderClock;
import clock.digital2.DigitalClock2;
import clock.starsky.StarSkyClock;
import clock.tron.TronClock;
import clock.timer.ClockTimer;
import clock.util.LayoutStrategy;
import clock.util.PositionManager;
import clock.word.WordClock;

/**
 * The <code>ClockApp</code> class represents the application's main window.
 * 
 * @author Andreas Ruppen
 */
public class ClockApp extends JFrame {
    private static final long serialVersionUID = 3257286920185786678L;
    private ClockTimer timer;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu layoutMenu;
    private JPanel panel;
    private JButton startButton;
    private JButton stopButton;
    private JButton analogClockButton;
    private JButton digitalClockButton;
    private JButton digitalClockNGButton;
    private JButton digitalClockLCDButton;
    private JButton binaryClockButton;
    private JButton binaryShapesClockButton;
    private JButton bargraphClockButton;
    private JButton delayedClockButton;
    private JButton romanClockButton;
    private JButton hourGlassClockButton;
    private JButton pacmanClockButton;
    private JButton particleClockButton;
    private JButton tronClockButton;
    private JButton starskyClockButton;
    private JButton wordClockButton;
    private JButton sbbClockButton;
    private JButton spiderClockButton;
    private JButton digitalClock2Button;
    private JButton circularClockButton;
    private JButton coolClockButton;

    /**
     * Creates a new instance of <code>ClockApp</code>.
     */
    public ClockApp() {
        // Create the timer;
        timer = new ClockTimer();

        // Set up the window.
        setTitle("Clock Timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and set up the menu bar.
        menuBar = new JMenuBar();
        addMenus();
        
        // Add the menu bar to the window.
        setJMenuBar(menuBar);
        
        // Create and set up the panel.
        panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        addWidgets();

        // Add the panel to the window.
        getContentPane().add(panel, BorderLayout.CENTER);

        // Set screen position.
        setLocation(PositionManager.getUniqueInstance().getMainWindowPosition());

        // Display the window.
        pack();
        setVisible(true);
    }

    /**
     * Create and add the menus.
     */
    private void addMenus() {
        // Create and set up the file menu.
        fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        
        // Create and set up the layout menu.
        layoutMenu = new JMenu("Layout");
        ButtonGroup layoutMenuItemGroup = new ButtonGroup();
        boolean isFirst = true;
        for (LayoutStrategy strategy : getLayoutStrategies()) {
            JRadioButtonMenuItem layoutMenuItem = 
                    new JRadioButtonMenuItem(strategy.getDescription());
            layoutMenuItem.addActionListener(
                    new ChangeLayoutStrategyActionListener(strategy));
            layoutMenuItemGroup.add(layoutMenuItem);
            layoutMenu.add(layoutMenuItem);
            
            // Set the first layout strategy to be selected.
            if (isFirst) {
                layoutMenuItem.setSelected(true);
                layoutMenuItem.doClick();
                isFirst = false;
            }
        }
        
        // Add the menus to the menu bar.
        menuBar.add(fileMenu);
        menuBar.add(layoutMenu);
    }
    
    /**
     * Create and add the widgets.
     */
    private void addWidgets() {
        // Create and set up the start button.
        startButton = new JButton("Start timer");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        // Create and set up the stop button.
        stopButton = new JButton("Stop timer");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });

        // Create and set up the analog clock button.
        analogClockButton = new JButton("analog clock");
        analogClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AnalogClock(timer);
            }
        });

        // Create and set up the digital clock button.
        digitalClockButton = new JButton("digital clock");
        digitalClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DigitalClock(timer);
            }
        });
		
	// Create and set up the digital clock button for the Next Generation.
        digitalClockNGButton = new JButton("digital clock NG");
        digitalClockNGButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DigitalClockNG(timer);
            }
        });
        
        // Create and set up the digital lcd clock button.
        digitalClockLCDButton = new JButton("lcd digital clock");
        digitalClockLCDButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DigitalClockLCD(timer);
            }
        });
        
        // Create and set up the binary clock button.
        binaryClockButton = new JButton("binary clock");
        binaryClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BinaryClock(timer);
            }
        });
        
        // Create and set up the binary clock button.
        binaryShapesClockButton = new JButton("binary shapes clock");
        binaryShapesClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BinaryShapesClock(timer);
            }
        });
        
        // Create and set up the bargraph clock button.
        bargraphClockButton = new JButton("bar graph clock");
        bargraphClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BarGraphClock(timer);
            }
        });
        
        // Create and set up the delayed clock button.
        delayedClockButton = new JButton("delayed clock");
        delayedClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DelayedClock(timer);
            }
        });

	// Create and set up the Roman clock button.
        romanClockButton = new JButton("roman numbers clock");
        romanClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RomanClock(timer);
            }
        });

	// Create and set up the Hourglass clock button.
        hourGlassClockButton = new JButton("hourglass clock");
        hourGlassClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HourGlassClock(timer);
            }
        });

	// Create and set up the Pacman clock button.
        pacmanClockButton = new JButton("pacman clock");
        pacmanClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PacmanClock(timer);
            }
        });
        
        // Create and set up the Particle clock button.
        particleClockButton = new JButton("particle clock");
        particleClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ParticleClock particleClock = new ParticleClock(timer);
                (new Thread(particleClock)).start();
            }
        });
        
        // Create and set up the Tron clock button.
        tronClockButton = new JButton("tron clock");
        tronClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TronClock(timer);
            }
        });
        
        // Create and set up the Starsky clock button.
        starskyClockButton = new JButton("star sky clock");
        starskyClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StarSkyClock(timer);
            }
        });
        
        // Create and set up the Word clock button.
        wordClockButton = new JButton("word clock");
        wordClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WordClock(timer);
            }
        });
        
        // Create and set up the SBB clock button.
        sbbClockButton = new JButton("SBB clock");
        sbbClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SBBClock(timer);
            }
        });

        // Create and set up the Spider clock button.
        spiderClockButton = new JButton("Spider clock");
        spiderClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SpiderClock(timer);
            }
        });

        // Create and set up the Digital clock button.
        digitalClock2Button = new JButton("Digital NG+ clock");
        digitalClock2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DigitalClock2(timer);
            }
        });

        // Create and set up the Circular clock button.
        circularClockButton = new JButton("Circular clock");
        circularClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CircularClock(timer);
            }
        });

        // Create and set up the COOL clock button.
        // created by Ricci Paul and Richoz Julien
        coolClockButton = new JButton("Cool clock");
        coolClockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CoolClock(timer);
            }
        });
        
        // Add the widgets to the content panel.
        panel.add(startButton);
        panel.add(stopButton);
        panel.add(analogClockButton);
        panel.add(digitalClockButton);
        panel.add(digitalClockNGButton);
        panel.add(digitalClockLCDButton);
        panel.add(binaryClockButton);
        panel.add(binaryShapesClockButton);
        panel.add(bargraphClockButton);
        panel.add(delayedClockButton);
        panel.add(romanClockButton);
        panel.add(hourGlassClockButton);
        panel.add(pacmanClockButton);
        panel.add(particleClockButton);
        panel.add(tronClockButton);
        panel.add(starskyClockButton);
        panel.add(wordClockButton);
        panel.add(sbbClockButton);
        panel.add(spiderClockButton);
        panel.add(digitalClock2Button);
        panel.add(circularClockButton);
        panel.add(coolClockButton);
    }
    
    /**
     * Create a vector that contain an instance of every layout strategy in 
     * the clock.util package.
     */
    private Vector<LayoutStrategy> getLayoutStrategies() {
        Vector<LayoutStrategy> strategies = new Vector<LayoutStrategy>();
        
        String packageName = "clock.util";
        URL url = getClass().getResource("/" + packageName.replace('.', '/'));
        String urlString = url.getPath().replaceAll("%20", " ");
        File dir = new File(urlString);
        if (dir.isDirectory()) {
            String[] fileNames = dir.list();
            for (int i = 0; i < fileNames.length; i++) {
                String fn = fileNames[i];
                if (fileNames[i].endsWith(".class")) {
                    String className = 
                        packageName + "." + fn.substring(0, fn.length() - 6);
                    try {
                        Class clazz = Class.forName(className);
                        if (isInterfaceOfClass(LayoutStrategy.class, clazz)) {
                            LayoutStrategy strategy = 
                                (LayoutStrategy) clazz.newInstance();
                            strategies.add(strategy);
                        }
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    } catch (InstantiationException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return strategies;
    }
    
    /**
     * Determines if the given class implements the given interface.
     */
    private boolean isInterfaceOfClass(Class interfaze, Class clazz) {
        Class[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            if (interfaze.equals(interfaces[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * The application's main method.
     */
    public static void main(String[] args) {
        new ClockApp();
    }
    
    /**
     * An action listener that sets the corresponding layout strategy to the
     * position manager when a layout menu item is clicked.
     */
    private class ChangeLayoutStrategyActionListener implements ActionListener {
        private LayoutStrategy strategy;
        
        /**
         * Creates a new instance of the action listener.
         */
        public ChangeLayoutStrategyActionListener(LayoutStrategy strategy) {
            this.strategy = strategy;
        }
        
        /**
         * Invoked when the action occurs.
         */
        public void actionPerformed(ActionEvent e) {
            PositionManager.getUniqueInstance().setLayoutStrategy(strategy);
        }
    }
}
