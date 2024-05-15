package decorator.starbuzz;

public class HouseBlend extends Beverage {
	/**
	 * @uml.property  name="mycost"
	 */
	private double mycost = .89;
	public HouseBlend() {
		description = "House Blend Coffee ("+mycost+")";
	}
 
	public double cost() {
		return mycost;
	}
}

