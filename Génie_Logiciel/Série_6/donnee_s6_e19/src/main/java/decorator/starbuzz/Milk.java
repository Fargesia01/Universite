package decorator.starbuzz;

public class Milk extends CondimentDecorator {
	
	/**
	 * @uml.property  name="addedCost"
	 */
	private double addedCost = .10;

	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}


	public String getName() {
		return "Milk";
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
