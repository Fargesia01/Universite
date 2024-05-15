package decorator.starbuzz;

public class Decaf extends Beverage {
	/**
	 * @uml.property  name="mycost"
	 */
	private double mycost = 1.05;
	public Decaf() {
		description = "Decaf Coffee ("+mycost+")";
	}
 
	public double cost() {
		return mycost;
	}
}

