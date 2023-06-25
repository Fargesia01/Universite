import java.util.ArrayList;
import java.lang.Math;
import java.awt.Color;

public class Triangle extends Polygon
{
	private double a;
	private double b;
	private double c;
	private double width;
	private double height;
	private double shift;


	// CONSTRUCTORS


	public Triangle(Point topVortex, double width, double height, double shift, Color color)
	{
		super();

		this.vertices.add(new Point(topVortex));
		this.vertices.add(new Point(topVortex.getX() + width, topVortex.getY()));
		this.vertices.add(new Point(topVortex.getX(), topVortex.getY() + height));
		this.col = color;
		this.pos = center();
		this.width = width;
		this.height = height;
		this.shift = shift;
		this.a = width;
		this.b = Math.sqrt(Math.pow(height, 2) + Math.pow(width - shift, 2));
		this.c = Math.sqrt(Math.pow(height, 2) + Math.pow(shift, 2));
	}

	
	// UTILITY


	public double semiperimeter()
	{
		return ((this.a + this.b + this.c) / 2);
	}
	
	public double area()
	{
		double p = this.semiperimeter();

		return (Math.sqrt(p * (p - a) * (p - b) * (p - c)));
	}

	public double heift()
	{
		return (2 * area() / a);
	}

	public double shift()
	{
		return (Math.sqrt(Math.pow(this.c, 2) - Math.pow(this.heift(), 2)));
	}

	public static Vector[]	path(double width, double height, double shift)
	{
		Vector[] path = new Vector[2];

		path[0] = new Vector(width, 0);
		path[1] = new Vector(new Point(width, 0), new Point(shift, height));

		return (path);
	}
}
