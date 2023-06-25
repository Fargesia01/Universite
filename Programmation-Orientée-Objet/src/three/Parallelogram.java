import java.util.ArrayList;
import java.awt.Color;

public class Parallelogram extends Polygon
{
	private double width;
	private double height;
	private double shift;

	
	// CONSTRUCTORS


	public Parallelogram(Point center, double width, double height, double shift, Color color)
	{
		super();

		this.vertices.add(new Point(center.getX() - (width / 2) + (shift / 2), center.getY() + (height / 2)));
		this.vertices.add(new Point(center.getX() + (width / 2) + (shift / 2), center.getY() + (height / 2)));
		this.vertices.add(new Point(center.getX() + (width / 2) - (shift / 2), center.getY() - (height / 2)));
		this.vertices.add(new Point(center.getX() - (width / 2) - (shift / 2), center.getY() - (height / 2)));

		this.col = color;
		this.pos = center;
		this.height = height;
		this.width = width;
		this.shift = shift;
	}

	public static Vector[]	path(double width, double height, double shift)
	{
		Vector[] path = new Vector[3];
		
		path[0] = new Vector(width, 0);
		path[1] = new Vector(new Point(width, 0), new Point(width + shift, height));
		path[2] = new Vector(new Point(width + shift, height), new Point(shift, height));
		return (path);
	}


	// GETTERS, SETTERS, TOSTRING AND DISPLAY


	public double	getShift()
	{
		return (this.shift);
	}

	public double getHeight()
	{
		return (this.height);
	}

	public double getWidth()
	{
		return (this.width);
	}

	public void setShift(double nouv)
	{
		this.shift = nouv;
	}

	public void setHeight(double nouv)
	{
		this.height = nouv;
	}

	public void setWidth(double nouv)
	{
		this.width = nouv;
	}
}
