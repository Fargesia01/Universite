package decorator.starbuzz;

public class DarkRoast extends Beverage {
	/**
	 * @uml.property  name="mycost"
	 */
	private double mycost = .99;
	public DarkRoast() {
		description = "Dark Roast Coffee ("+mycost+")";
	}
 
	public double cost() {
		return .99;
	}
}

