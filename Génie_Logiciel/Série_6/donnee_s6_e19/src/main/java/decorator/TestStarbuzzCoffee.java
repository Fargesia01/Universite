package decorator;

import java.util.logging.*;
import decorator.starbuzz.Beverage;
import decorator.starbuzz.DarkRoast;
import decorator.starbuzz.Espresso;
import decorator.starbuzz.HouseBlend;
import decorator.starbuzz.Mocha;
import decorator.starbuzz.Soy;
import decorator.starbuzz.Whip;

public class TestStarbuzzCoffee {
	private static Logger loggingService = Logger.getLogger("TestStarbuzzCoffee");
	public static void main(String args[]) {
		Beverage beverage = new Espresso();
		loggingService.info(beverage.getDescription() + " : $" + beverage.cost());

		Beverage beverage2 = new DarkRoast();
		beverage2 = new Mocha(beverage2);
		beverage2 = new Mocha(beverage2);
		beverage2 = new Whip(beverage2);
		loggingService.info(beverage2.getDescription() + " : $"
				+ beverage2.cost());

		// Beverage beverage3 = new HouseBlend();
		// beverage3 = new Soy(beverage3);
		// beverage3 = new Mocha(beverage3);
		// beverage3 = new Whip(beverage3);
		Beverage beverage3 = new Soy(new Mocha(new Whip(new HouseBlend())));
		loggingService.info(beverage3.getDescription() + " : $"
				+ beverage3.cost());
	}
}
