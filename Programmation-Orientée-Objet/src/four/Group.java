import java.awt.Graphics;

public class Group extends Shape
{
	private Shape[]	list;


	// CONSTRUCTORS


	public Group(Shape[] list)
	{
		this.col = null;
		this.list = list;
		this.pos = new Point(-200, -200);
	}


	// TRANSFORMATIONS

	
	@Override
	public void	rotation(Point center, Angle angle)
	{
		for (int i = 0; i < this.list.length; i++)
		{
			this.list[i].rotation(center, angle);
		}
		return ;
	}

	@Override
	public void	translation(Vector dir)
	{
		for (int i = 0; i < this.list.length; i++)
		{
			this.list[i].translation(dir);
		}
		return ;
	}

	@Override
	public void	homothetie(Point ref, double factor)
	{
		for (int i = 0; i < this.list.length; i++)
		{
			this.list[i].homothetie(ref, factor);
		}
		return ;
	}

	// UTILITY


	public boolean	contains(Point src)
	{
		for (int i = 0; i < this.list.length; i++)
		{
			if (list[i].contains(src))
				return (true);
		}
		return (false);
	}


	// GETTER AND SETTER AND DISPLAY ETC


	public void display(Graphics g)
	{
		for (int i = 0; i < this.list.length; i++)
		{
			list[i].display(g);
		}
	}

	public Shape[]	getList()
	{
		return (this.list);
	}

	public void	setList(Shape[] list)
	{
		this.list = list;
	}

	public Point	center()
	{
		int resultx = 0;
		int resulty = 0;

		for (int i = 0; i < list.length; i++)
		{
			resultx += list[i].center().getX();
			resulty += list[i].center().getY();
		}
		resultx /= list.length;
		resulty /= list.length;
		return (new Point(resultx, resulty));
	}
}
