package visitor.syntax;

import visitor.tools.ExpressionVisitor;

public class MultiplicationExpression extends ArithmeticExpression {
	
	public MultiplicationExpression(Expression left, Expression right) {
		super(left, right);
	}
	
	public void accept(ExpressionVisitor v) {
        v.visitMultiplicationExpression(this);
	}

}
