import java.lang.Math;

public class Point
{
	private double x;
	private double y;


	// ALL CONSTRUCTORS


	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public Point(Point p)
	{
		this.x = p.getX();
		this.y = p.getY();
	}

	public Point copy()
	{
		return (new Point(this.x, this.y));
	}


	// TRANSFORMATIONS
	

	public void	translate(Vector delta)
	{
		this.x += delta.getX();
		this.y += delta.getY();
	}

	public void	rotate(Angle angle, Point center)
	{
		double tmp = this.x;

		this.x = angle.cos() * (this.x - center.getX()) -
			angle.sin() * (this.y - center.getY()) + center.getX();
		this.y = angle.sin() * (tmp - center.getX()) + 
			angle.cos() * (this.y - center.getY()) + center.getY();
	}

	public void	scale(double factor, Point center)
	{
		double dx = this.x - center.getX();
		double dy = this.y - center.getY();

		this.x = center.getX() + dx * factor;
		this.y = center.getY() + dy * factor;
		return ;
	}


	// UTILITY METHODS


	public double	distance(Point other)
	{
		return (Math.sqrt(Math.pow(other.getX() - this.x, 2) + 
			Math.pow(other.getY() - this.y, 2)));
	}

	public boolean	equals(Point other)
	{
		return (this.x == other.getX() && this.y == other.getY() ? true : false);
	}


	// OPERATORS FUNCTIONS


	public Point	plus(Point other)
	{
		return (new Point(this.x + other.getX(), this.y + other.getY()));
	}

	public Point	minus(Point other)
	{
		return (new Point(this.x - other.getX(), this.y - other.getY()));
	}

	public Point	times(double factor)
	{
		return (new Point(this.x * factor, this.y * factor));
	}


	// GETTER AND SETTERS AND DEFAULT CONSTRUCTOR

	@Override
	public String toString()
	{
		String out = "X coordinate: " + this.x + " and y coordinate: " + this.y + ".";
		return (out);
	}

	public void	setX(double x)
	{
		this.x = x;
	}

	public void	setY(double y)
	{
		this.y = y;
	}

	public double	getX()
	{
		return (this.x);
	}

	public double	getY()
	{
		return (this.y);
	}
	private Point()
	{
		this.x = 0;
		this.y = 0;
	}
}
