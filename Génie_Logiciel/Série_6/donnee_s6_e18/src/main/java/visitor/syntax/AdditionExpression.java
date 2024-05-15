package visitor.syntax;

import visitor.tools.ExpressionVisitor;


public class AdditionExpression extends ArithmeticExpression {

	public AdditionExpression(Expression left, Expression right) {
		super(left, right);
	}

	public void accept(ExpressionVisitor v) {
        v.visitAdditionExpression(this);
	}


}
