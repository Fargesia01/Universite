package visitor.syntax;

import visitor.tools.ExpressionVisitor;

public class DivisionExpression extends ArithmeticExpression {
	
	public DivisionExpression(Expression left, Expression right) {
		super(left, right);
	}
	
	public void accept(ExpressionVisitor v) {
        v.visitDivisionExpression(this);
	}

}
