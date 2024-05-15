package visitor.syntax;

import visitor.tools.ExpressionVisitor;

public class SubstractionExpression extends ArithmeticExpression {
	
	public SubstractionExpression(Expression left, Expression right) {
		super(left, right);
	}
	
	public void accept(ExpressionVisitor v) {
        v.visitSubstractionExpression(this);
	}

}
