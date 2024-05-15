package decorator.starbuzz;

/**
 * @author Andreas Ruppen
 */
public abstract class CondimentDecorator extends Beverage {
	public abstract double getAddedCost();
	public String getDescription() {
		return beverage.getDescription() + ", "+getName()+" ("+getAddedCost()+")";
	}
	/**
	 * @uml.property  name="name"
	 */
	public abstract String getName();
	/**
	 * @uml.property  name="beverage"
	 * @uml.associationEnd  
	 */
	protected Beverage beverage;
	public double cost() {
		return beverage.cost();
	}
}
