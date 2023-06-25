import java.awt.Color;
import java.lang.Math;
import java.awt.Graphics;

public class Circle extends Shape
{
	private double radius;


	// CONSTRUCTORS


	public Circle(Color col, double radius, Point center)
	{
		this.radius = radius;
		this.pos = center;
		this.col = col;
	}


	// TRANSFORMATIONS


	@Override
	public void	translation(Vector dir)
	{
		this.pos.translate(dir);
	}

	@Override
	public void	rotation(Point center, Angle angle)
	{
		this.pos.rotate(angle, center);
	}

	@Override
	public void	homothetie(Point ref, double factor)
	{
		this.radius = Math.abs(this.radius * factor);
		this.pos.scale(factor, ref);
	}

	
	// UTILITY


	@Override
	public boolean	contains(Point src)
	{
		if (this.pos.distance(src) > this.radius)
			return (false);
		else
			return (true);
	}


	// GETTER, SETTER, DEFAULT CONSTRUCTOR AND TO STRING

	
	@Override
	public void	display(Graphics g)
	{
		g.setColor(this.col);
		g.fillOval((int)this.pos.getX(), (int)this.pos.getY(), (int)this.radius, 
			(int)this.radius);
	}

	@Override
	public String toString()
	{
		String out = "This is a circle centered at " + this.pos.getX() + 
			", " + this.pos.getY() + " with a radius of " + this.radius;
		return (out);
	}

	@Override
	public Point	center()
	{
		return (new Point(this.pos));
	}

	public double	getDiameter()
	{
		return (this.radius * 2.0);
	
	}
	public void	setRadius(double radius)
	{
		this.radius = radius;
	}

	public double	getRadius()
	{
		return (this.radius);
	}
}
