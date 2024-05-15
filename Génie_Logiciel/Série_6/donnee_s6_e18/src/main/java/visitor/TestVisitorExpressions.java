package visitor;

import visitor.syntax.AdditionExpression;
import visitor.syntax.Constant;
import visitor.syntax.DivisionExpression;
import visitor.syntax.Expression;
import visitor.syntax.MultiplicationExpression;
import visitor.syntax.SubstractionExpression;
import visitor.tools.PrettyPrintVisitor;
import visitor.tools.PrefixPrintVisitor;
import visitor.tools.CalculationVisitor;
import visitor.tools.DepthVisitor;
import java.util.logging.*;

public class TestVisitorExpressions {
	
	private static Logger loggingService = Logger.getLogger("VisitorTest");

	public static void main(String[] args) {

		Expression mye1 = new SubstractionExpression(
				new Constant(10),
				new Constant(6));
		
		Expression mye2 = new AdditionExpression(
				new DivisionExpression(
						new Constant(50),
						new Constant(2)),
				new SubstractionExpression(
						new MultiplicationExpression(
								new Constant(2),
								mye1),
						new Constant(17)));
		
		Expression mye3 = new SubstractionExpression(
				new MultiplicationExpression(
						new Constant(3),
						new Constant(7)),
				new MultiplicationExpression(
						new Constant(5),
						mye2));
		
		Expression mye4 = new DivisionExpression(
				new DivisionExpression(
						new Constant(16),
						new Constant(2)),
				new Constant(4));
		
		Expression myExample1 = new AdditionExpression(
				mye3,
				mye4);
		
		Expression myExample2 = new DivisionExpression(
				new Constant(12),
				new SubstractionExpression(
						new AdditionExpression(
								new Constant(5),
								new Constant(2)),
						new MultiplicationExpression(
								new Constant(2),
								new Constant(2))));
		
		PrettyPrintVisitor v1 = new PrettyPrintVisitor();
		PrefixPrintVisitor v2 = new PrefixPrintVisitor();
		CalculationVisitor v3 = new CalculationVisitor();
		DepthVisitor v4 = new DepthVisitor();
		
		// myExample1
		myExample1.accept(v1);
        System.out.println("Pretty Print ex1: " + v1.getResult());

        myExample1.accept(v2);
        System.out.println("Prefix Print ex1: "+ v2.getResult());

        myExample1.accept(v3);
        System.out.println("Result ex1: " + v3.getResult());

        myExample1.accept(v4);
        System.out.println("Depth ex1: " + v4.getResult());
		
		// myExample2
                v1 = new PrettyPrintVisitor();
                v2 = new PrefixPrintVisitor();
                v3 = new CalculationVisitor();
                v4 = new DepthVisitor();
		myExample2.accept(v1);
        System.out.println("Pretty Print ex2: " + v1.getResult());

        myExample2.accept(v2);
        System.out.println("Prefix Print ex2: " + v2.getResult());

        myExample2.accept(v3);
        System.out.println("Result ex2: " + v3.getResult());

        myExample2.accept(v4);
        System.out.println("Depth ex2: " + v4.getResult());
		
	}

}
