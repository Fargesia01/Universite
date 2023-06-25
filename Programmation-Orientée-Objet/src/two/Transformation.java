public interface Transformation
{
	public void	translation(Vector dir);
	public void	rotation(Point center, Angle angle);
	public void	homothetie(Point ref, double factor);
}
