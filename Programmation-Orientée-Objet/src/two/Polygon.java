import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Polygon extends Shape
{
	protected ArrayList<Point> vertices = new ArrayList<>();


	// CONSTRUCTORS


	public Polygon(ArrayList<Point> vertices, Color color)
	{
		this.col = color;
		this.vertices = new ArrayList<Point>(vertices);
		this.pos = this.center();
	}


	// UTILITY


	@Override
	public boolean	contains(Point src)
	{
		int	nbr = 0;
		Segment[] list = new Segment[this.vertices.size()];

		for (int i = 0; i < this.vertices.size(); i++)
		{
			if (i == this.vertices.size() - 1)
				list[i] = new Segment(this.vertices.get(i), 
					this.vertices.get(0));
			else
				list[i] = new Segment(this.vertices.get(i), 
					this.vertices.get(i + 1));
		}
		for(int i = 0; i < list.length; i++)
		{
			if (list[i].cross(new Segment(src, new Point(1500, 200))))
				nbr++;
		}
		if (nbr % 2 == 0)
			return (false);
		else
			return (true);
	}

	public boolean overlaps(Polygon src)
	{
		for (Point p : src.getVertices())
		{
			if (this.contains(p))
				return (true);
		}
		return (false);
	}

	
	// TRANSFORMATIONS


	@Override
	public void	translation(Vector dir)
	{
		for (Point p : this.vertices)
		{
			p.translate(dir);
		}
		this.pos = this.center();
	}

	@Override
	public void	rotation(Point center, Angle angle)
	{
		for (Point p : this.vertices)
		{
			p.rotate(angle, center);
		}
		this.pos.rotate(angle, center);
	}

	@Override
	public void	homothetie(Point ref, double factor)
	{
		for (Point p : this.vertices)
		{
			p.scale(factor, ref);
		}
		this.pos = this.center();
	}


	// GETTERS, SETTERS, TOSTRING AND DISPLAY
	

	@Override
	public void	display(Graphics g)
	{
		g.setColor(this.col);
		g.fillPolygon(this.getVerticesX(), this.getVerticesY(), this.getVertices().size());
	}

	@Override
	public String toString()
	{
		String out = "This is a polygon centered at " + this.pos.getX() + 
			", " + this.pos.getY() + " in " + this.col + 
				" with " + this.vertices.size() + " points.";
		return (out);
	}	

	@Override
	public Point	center()
	{
		double	x = 0;
		double	y = 0;

		if (this.vertices.size() == 0)
			return (null);
		for (Point p : this.vertices)
		{
			x += p.getX();
			y += p.getY();
		}
		x /= this.vertices.size();
		y /= this.vertices.size();
		return (new Point(x, y));
	}

	public ArrayList<Point>	getVertices()
	{
		return (this.vertices);
	}

	public int[]	getVerticesX()
	{
		int[] x = new int[this.vertices.size()];

		for (int i = 0; i < this.vertices.size(); i++)
		{
			x[i] = (int)this.vertices.get(i).getX();
		}
		return (x);
	}

	public int[] getVerticesY()
	{
		int[] y = new int[this.vertices.size()];

		for (int i = 0; i < this.vertices.size(); i++)
		{
			y[i] = (int)this.vertices.get(i).getY();
		}
		return (y);
	}

	public void	setVertices(ArrayList<Point> src)
	{
		this.vertices.addAll(src);
	}
	
	protected Polygon(){};
}
