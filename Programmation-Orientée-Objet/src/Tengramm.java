import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.lang.Math;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.SwingUtilities;

public class Tengramm  extends JPanel implements Runnable, MouseListener, MouseMotionListener, MouseWheelListener 
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;


	// ALL TENGRAMM BASIC PIECES

	private static final Triangle tOne = new Triangle(new Point(640, 360), 240, 240, 0, Color.RED);
	private static final Triangle tTwo = new Triangle(new Point(640, 360), 340, 340, 0, Color.BLUE);
	private static final Triangle tThree = new Triangle(new Point(640, 360), 340, 340, 0, Color.GREEN);
	private static final Triangle tFour = new Triangle(new Point(640, 360), 170, 170, 0, Color.YELLOW);
	private static final Triangle tFive = new Triangle(new Point(640, 360), 170, 170, 0, Color.ORANGE);
	private static final Square sOne = new Square(new Point(640, 360), 170, Color.CYAN);
	private static final Parallelogram pOne = new Parallelogram(new Point(640, 360), 240, 120, -120, Color.PINK);



	// ADDING THE CENTER TO IT, MAKING IT A GROUP

	private static final Group gRed = new Group(new Shape[]{tOne, new Circle(Color.BLACK, 20, tOne.center())});
	private static final Group gBlue = new Group(new Shape[]{tTwo, new Circle(Color.BLACK, 20, tTwo.center())});
	private static final Group gGreen = new Group(new Shape[]{tThree, new Circle(Color.BLACK, 20, tThree.center())});
	private static final Group gYellow = new Group(new Shape[]{tFour, new Circle(Color.BLACK, 20, tFour.center())});
	private static final Group gOrange = new Group(new Shape[]{tFive, new Circle(Color.BLACK, 20, tFive.center())});
	private static final Group gPink = new Group(new Shape[]{pOne, new Circle(Color.BLACK, 20, pOne.center())});
	private static final Group gCyan = new Group(new Shape[]{sOne, new Circle(Color.BLACK, 20, sOne.center())});


	private static final Group[] alls = {gRed, gBlue, gGreen, gYellow, gOrange, gPink, gCyan};

	private static boolean square = false;
	private static boolean rectangle = false;
	private static boolean triangle = false;
	private static boolean on = false;

	private static int mouseXStart;
	private static int mouseYStart;


	public Tengramm() 
	{
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		JButton tri = new JButton("Triangle");
		JButton squ = new JButton("Square");
		JButton rec = new JButton("Rectangle");

		tri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setup_triangle();
				tri.setVisible(false);
				rec.setVisible(false);
				squ.setVisible(false);
				repaint();
			}
		});

		squ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setup_square();
				tri.setVisible(false);
				rec.setVisible(false);
				squ.setVisible(false);
				repaint();
			}
		});

		rec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setup_rectangle();
				tri.setVisible(false);
				rec.setVisible(false);
				squ.setVisible(false);
				repaint();
			}
		});
		this.add(tri);
		this.add(squ);
		this.add(rec);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (on)
		{
			for (int i = 6; i >= 0; i--)
				alls[i].display(g);
		}
		g.setColor(Color.BLACK);
		if (square)
		{
			g.drawLine(400, 120, 880, 120);
			g.drawLine(400, 120, 400, 600);
			g.drawLine(400, 600, 880, 600);
			g.drawLine(880, 120, 880, 600);
		}
		else if (triangle)
		{
			g.drawLine(160, 600, 1120, 600);
			g.drawLine(640, 120, 160, 600);
			g.drawLine(640, 120, 1120, 600);
		}
		else if (rectangle)
		{
			g.drawLine(300, 190, 980, 190);
			g.drawLine(300, 190, 300, 530);
			g.drawLine(980, 190, 980, 530);
			g.drawLine(300, 530, 980, 530);
		}
	}


	// MOUSE EVENTS


	@Override
	public void mousePressed(MouseEvent e)
	{
		this.mouseXStart = e.getX();
		this.mouseYStart = e.getY();
		for (int i = 0; i < 7; i++)
		{
			if (alls[i].contains(new Point(e.getX(), e.getY())))
			{
				alls[i].setIsMoved(true);
				reorder(i);
				return ;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (!alls[0].getIsMoved())
			return ;
		if ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0)
		{
			int newX = e.getX() - (e.getX() % 5);
			int newY = e.getY() - (e.getY() % 5); 

			alls[0].translation(new Vector(alls[0].center(), new Point(newX, newY)));

			this.mouseXStart = e.getX();
			this.mouseYStart = e.getY();
		}
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		alls[0].setIsMoved(false);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		for (int i = 0; i < 7; i++)
		{
			if (alls[i].contains(new Point(e.getX(), e.getY())))
			{
				int amount = e.getWheelRotation();
				alls[i].rotation(Angle.inDegrees(amount * 5));
				reorder(i);
				this.repaint();
				return ;
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e){}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mouseClicked(MouseEvent e){}
	@Override
	public void mouseMoved(MouseEvent e){}


	// UTILTY


	private void reorder(int i)
	{
		Group tmp = alls[i];
		for (int j = i; j > 0; j--)
			alls[j] = alls[j - 1];
		alls[0] = tmp;

	}


	// MAIN AND RUN

	 
	@Override
	public void run()
	{
		JFrame frame = new JFrame("Tengramm");
		Tengramm game = new Tengramm();
		frame.getContentPane().add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Tengramm());
	}


	// ALL INITAL SETUPS TO PLAY


	public static void setup_square()
	{
		square = true;
		on = true;
		gRed.translation(new Vector(-610, -330));
		gBlue.translation(new Vector(280, -330));
		gGreen.translation(new Vector(400, -160));
		gGreen.rotation(tThree.center(), Angle.inDegrees(180));
		gYellow.translation(new Vector(280, 90));
		gOrange.rotation(tFive.center(), Angle.inDegrees(180));
		gOrange.translation(new Vector(360, 150));
		gCyan.translation(new Vector(-360, -120));
		gPink.rotation(pOne.center(), Angle.inDegrees(90));
		gPink.translation(new Vector(-460, 120));
		return ;
	}

	public static void setup_rectangle()
	{
		rectangle = true;
		on = true;
		gRed.translation(new Vector(-610, -330));
		gBlue.translation(new Vector(400, -340));
		gBlue.rotation(tTwo.center(), Angle.inDegrees(90));
		gGreen.rotation(tThree.center(), Angle.inDegrees(270));
		gGreen.translation(new Vector(-620, 120));
		gYellow.translation(new Vector(-300, -320));
		gYellow.rotation(tFour.center(), Angle.inDegrees(45));
		gOrange.rotation(tFive.center(), Angle.inDegrees(45));
		gOrange.translation(new Vector(-100, 230));
		gCyan.translation(new Vector(480, 100));
		gPink.translation(new Vector(100, -270));
	}


	public static void setup_triangle()
	{
		triangle = true;
		on = true;
		gRed.translation(new Vector(-610, -330));
		gBlue.rotation(tTwo.center(), Angle.inDegrees(90));
		gBlue.translation(new Vector(400, -340));
		gGreen.rotation(tThree.center(), Angle.inDegrees(270));
		gGreen.translation(new Vector(265, -215));
		gYellow.rotation(tFour.center(), Angle.inDegrees(90));
		gYellow.translation(new Vector(500, 30));
		gOrange.rotation(tFive.center(), Angle.inDegrees(90));
		gOrange.translation(new Vector(120, -300));
		gCyan.rotation(sOne.center(), Angle.inDegrees(45));
		gCyan.translation(new Vector(-250, -200));
		gPink.translation(new Vector(-430, 0));
	}


	// ALL SOLVED SETUP FROM STARTING POSITIONS
	

	public static void setup_rectangle_solved()
	{

	}

	public static void setup_square_solved()
	{
		square = true;
		gBlue.rotation(tTwo.getVertices().get(0).copy(), Angle.inDegrees(45));
		gGreen.rotation(tThree.getVertices().get(0).copy(), Angle.inDegrees(135));
		gPink.translation(new Vector(180, 60));
		gYellow.rotation(tFour.getVertices().get(0).copy(), Angle.inDegrees(225));
		gYellow.translation(new Vector(-120, -120));
		gCyan.rotation(sOne.center(), Angle.inDegrees(45));
		gCyan.translation(new Vector(0, -120));
		gOrange.rotation(tFive.getVertices().get(0).copy(), Angle.inDegrees(315));
		gPink.rotation(pOne.center(), Angle.inDegrees(90));
		gRed.translation(new Vector(80, -240));
		gRed.rotation(tOne.center(), Angle.inDegrees(90));
	}

	public static void setup_triangle_solved()
	{
		triangle = true;
		gRed.rotation(tOne.getVertices().get(0).copy(), Angle.inDegrees(180));
		gBlue.rotation(tTwo.getVertices().get(0).copy(), Angle.inDegrees(45));
		gBlue.translation(new Vector(240, 0));
		gGreen.rotation(tThree.getVertices().get(0).copy(), Angle.inDegrees(45));
		gGreen.translation(new Vector(-240, 0));
		gCyan.rotation(sOne.center(), Angle.inDegrees(45));
		gCyan.translation(new Vector(0, 120));
		gYellow.rotation(tFour.getVertices().get(0).copy(), Angle.inDegrees(225));
		gYellow.translation(new Vector(-120, 120));
		gOrange.rotation(tFive.getVertices().get(0).copy(), Angle.inDegrees(135));
		gOrange.translation(new Vector(240, 0));
		gPink.rotation(pOne.center(), Angle.inDegrees(90));
		gPink.translation(new Vector(60, -60));	
	}
}
