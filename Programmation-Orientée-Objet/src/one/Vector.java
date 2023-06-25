public class Vector
{
	private double dx;
	private double dy;


	// CONSTRUCTORS


	public Vector(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}

	public Vector(Point pt)
	{
		this.dx = pt.getX();
		this.dy = pt.getY();
	}

	public Vector(Point from, Point to)
	{
		this.dx = to.getX() - from.getX();
		this.dy = to.getY() - from.getY();
	}

	
	// OPERATOR METHODS
	

	public Vector plus(Vector v)
	{
		return (new Vector(this.getX() + v.getX(), this.getY() + v.getY()));
	}

	public Vector minus(Vector v)
	{
		return (new Vector(this.getX() - v.getX(), this.getY() - v.getY()));
	}

	public Vector times(double v)
	{
		return (new Vector(this.getX() * v, this.getY() * v));
	}


	// UTILITY


	public double dotProduct(Vector other)
	{
		return ((dx * other.getX()) + (dy * other.getY()));
	}
	
	public double getNorm()
	{
		return (Math.sqrt((dx * dx) + (dy * dy)));
	}

	
	// GETTERS, SETTERS, TOSTRING, DISPLAY


	@Override
	public String toString()
	{
		String out = "Vector coming from 0 to " + this.dx + this.dy;
		return (out);
	}

	public double getX()
	{
		return (this.dx);
	}

	public double getY()
	{
		return (this.dy);
	}
}
