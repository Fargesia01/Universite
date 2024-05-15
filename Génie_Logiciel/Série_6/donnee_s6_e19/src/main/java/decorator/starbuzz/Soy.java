package decorator.starbuzz;

public class Soy extends CondimentDecorator {
	
	/**
	 * @uml.property  name="addedCost"
	 */
	private double addedCost = .15;

	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	
	public String getName() {
		return "Soy";
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
