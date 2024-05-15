package decorator.starbuzz;

public class Espresso extends Beverage {
  /**
 * @uml.property  name="mycost"
 */
private double mycost = 1.99;
	public Espresso() {
		description = "Espresso ("+mycost+")";
	}
  
	public double cost() {
		return mycost;
	}
}

