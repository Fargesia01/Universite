import java.awt.Graphics;
import java.awt.Color;

public class Segment implements Transformation
{
	private Point[] edges;

	
	// CONSTRUCTORS


	public Segment(Point from, Point to)
	{
		this.edges = new Point[]{from, to};
	}


	// UTILITY

	
	public boolean	cross(Segment other)
	{
		Point a = this.edges[0];
		Point b = this.edges[1];
		Point c = other.edges[0];
		Point d = other.edges[1];
		
		double det = (b.getX() - a.getX()) * (d.getY() - c.getY()) - 
			(d.getX() - c.getX()) * (b.getY() - a.getY());
			if (det == 0)
				return false;
		double lambda = ((d.getY() - c.getY()) * (d.getX() - a.getX()) + 
			(c.getX() - d.getX()) * (d.getY() - a.getY())) / det;
		double gamma = ((a.getY() - b.getY()) * (d.getX() - a.getX()) + 
			(b.getX() - a.getX()) * (d.getY() - a.getY())) / det;
		return (0 < lambda && lambda < 1) && (0 < gamma && gamma < 1);
	}


	// TRANSFORMATIONS

	
	@Override
	public void	translation(Vector dir)
	{
		this.edges[0].translate(dir);
		this.edges[1].translate(dir);
	}

	@Override
	public void	rotation(Point center, Angle angle)
	{
		this.edges[0].rotate(angle, center);
		this.edges[1].rotate(angle, center);
	}

	@Override
	public void	homothetie(Point ref, double factor)
	{
		return ;
	}


	// GETTER, SETTER, TOSTRING, DISPLAY


	public void display(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawLine((int)this.edges[0].getX(), (int)this.edges[0].getY(), 
			(int)this.edges[1].getX(), (int)this.edges[1].getY());
	}

	@Override
	public String toString()
	{
		String out = "This is a segment starting at " + edges[0].getX() + 
		 	"x, " + edges[0].getY() + "y and going to " + edges[1].getX() + 
				 "x, " + edges[1].getY() + "y.";
		return (out);
	}

	public void	setEdges(Point from, Point to)
	{
		this.edges[0] = from;
		this.edges[1] = to;
	}

	public Point[]	getEdges()
	{
		return (this.edges);
	}
}
