package decorator.starbuzz;

public abstract class Beverage {
	/**
	 * @uml.property  name="description"
	 */
	String description = "Unknown Beverage";
  
	/**
	 * @return  the description
	 * @uml.property  name="description"
	 */
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
}
