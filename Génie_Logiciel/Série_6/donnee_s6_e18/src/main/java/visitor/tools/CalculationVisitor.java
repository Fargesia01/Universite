package visitor.tools;

import visitor.syntax.AdditionExpression;
import visitor.syntax.Constant;
import visitor.syntax.DivisionExpression;
import visitor.syntax.MultiplicationExpression;
import visitor.syntax.SubstractionExpression;


public class CalculationVisitor implements ExpressionVisitor {
	
	protected double result;
	
	public double getResult() {
		return (result);
	}

	public void visitConstant(Constant e) {
        result = e.getValue();
	}

	public void visitAdditionExpression(AdditionExpression e) {
        e.getLeft().accept(this);
        double left = getResult();
        e.getRight().accept(this);
        double right = getResult();
        result = left + right;
	}
	
	public void visitSubstractionExpression(SubstractionExpression e) {
        e.getLeft().accept(this);
        double left = getResult();
        e.getRight().accept(this);
        double right = getResult();
        result = left - right;
	}
	
	public void visitMultiplicationExpression(MultiplicationExpression e) {
        e.getLeft().accept(this);
        double left = getResult();
        e.getRight().accept(this);
        double right = getResult();
        result = left * right;
	}
	
	public void visitDivisionExpression(DivisionExpression e) {
        e.getLeft().accept(this);
        double left = getResult();
        e.getRight().accept(this);
        double right = getResult();
        result = left / right;
	}

}
