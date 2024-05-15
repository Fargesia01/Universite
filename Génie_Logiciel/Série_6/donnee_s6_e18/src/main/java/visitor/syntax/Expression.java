package visitor.syntax;

import visitor.tools.ExpressionVisitor;

public interface Expression {
	
	public void accept(ExpressionVisitor v);

}
