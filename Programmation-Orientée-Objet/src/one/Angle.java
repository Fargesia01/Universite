import java.lang.Math;

public class	Angle
{
	// Value in radians
	private double angle;


	// CONSTRUCTORS


	public	Angle(double dx, double dy)
	{
		this.angle = Math.atan2(dy, dx);
	}

	public static Angle inDegrees(double value)
	{
		Angle angle = new Angle();

		angle.angle = value * Math.PI / 180;
		return (angle);
	}

	public static Angle inRadians(double value)
	{
		Angle angle = new Angle();

		angle.angle = value;
		return (angle);
	}

	public static Angle vectors(Vector first, Vector second)
	{
		double dotProduct = first.dotProduct(second);
		double norms = first.getNorm() * second.getNorm();
		double angle = Math.acos(dotProduct / norms);
		return (Angle.inRadians(angle));
	}


	//  OPERTATORS FUNCTIONS


	public Angle plus(Angle other)
	{
		double sum = this.angle + other.asDegrees();
		if (sum > 360)
			sum -= 360;
		return (Angle.inDegrees(sum));
	}

	public Angle minus(Angle other)
	{
		double sum = this.angle - other.asDegrees();
		if (sum < 0)
			sum = 360 + sum;
		return (Angle.inDegrees(sum));
	}

	public Angle times(double factor)
	{
		double sum = this.angle * factor;
		if (sum < 0)
			sum = 360 + sum;
		return (Angle.inDegrees(sum));
	}


	// MATH FUNCTIONS


	public double sin()
	{
		return (Math.sin(this.angle));
	}

	public double cos()
	{
		return (Math.cos(this.angle));
	}


	// GETTERS, SETTERS, DEFAULT CONSTRUCTOR AND TOSTRING


	@Override
	public String toString()
	{
		String out = "This angle is equal to " + this.asDegrees() + 
			" degrees or " + this.asRadians() + " radians.";
		return (out);
	}

	public double asDegrees()
	{
		return (this.angle * 180 / Math.PI);
	}

	public double asRadians()
	{
		return (this.angle);
	}
	
	private Angle(){};
}
