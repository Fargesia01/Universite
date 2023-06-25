import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Transformation
{
	protected Point	pos;
	protected Color col;
	protected boolean isMoved = false;

	public abstract boolean	contains(Point src);
	public abstract	Point	center(); 
	public abstract void	display(Graphics g);

	// DEFAULT TRANSFORMATIONS 

	public void	rotation(Angle angle)
	{
		Point tmp = this.center();
		this.rotation(this.center(), angle);
		this.setPos(tmp);
	}

	public void	homomthetie(double factor)
	{
		this.homothetie(this.center(), factor);
	}


	// GETTERS AND SETTERS AND TOSTRING


	@Override
	public String toString()
	{
		String out = "This is a shape centered at " + this.pos.getX() + 
			", "+ this.pos.getY() + " in " + this.col;
		return (out);
	}

	public void	setColor(Color col)
	{
		this.col = col;
	}

	public Color	getCol()
	{
		return (this.col);
	}

	public void	setPos(Point pos)
	{
		this.pos = pos;
	}

	public Point	getPos()
	{
		return (this.pos);
	}

	public boolean	getIsMoved()
	{
		return (this.isMoved);
	}

	public void setIsMoved(boolean isMoved)
	{
		this.isMoved = isMoved;
	}
}
