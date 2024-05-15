package visitor.syntax;

import visitor.tools.ExpressionVisitor;

public abstract class ArithmeticExpression implements Expression {

	private Expression left;

	private Expression right;

	protected ArithmeticExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}
	
	public void accept(ExpressionVisitor v) {
		//This default behaviour does nothing. We could have declared this method abstract.
		//TODO: overwrite this method in each subclass appropriately !
	}

}
