package decorator.starbuzz;
 
public class Whip extends CondimentDecorator {
	
	/**
	 * @uml.property  name="addedCost"
	 */
	private double addedCost = .1;
 
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
 

	public String getName() {
		return "Whip";
	}
 
	/**
	 * @return  the addedCost
	 * @uml.property  name="addedCost"
	 */
	public double getAddedCost(){
		return addedCost;
	}
 
	public double cost() {
		return addedCost + super.cost();
	}
}
