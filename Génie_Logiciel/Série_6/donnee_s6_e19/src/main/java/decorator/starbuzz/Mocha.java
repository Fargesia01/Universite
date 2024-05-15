package decorator.starbuzz;

public class Mocha extends CondimentDecorator {
	
	/**
	 * @uml.property  name="addedCost"
	 */
	private double addedCost = .20;
 
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}
 
	
	public String getName() {
		return "Mocha";
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
