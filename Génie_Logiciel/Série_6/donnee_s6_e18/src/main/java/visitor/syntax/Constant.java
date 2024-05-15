package visitor.syntax;

import visitor.tools.ExpressionVisitor;

public class Constant implements Expression {

    private int value;
	
	public Constant(int v) {
		value = v;
	}
	
	public int getValue() {
		return value;
	}
	
	public void accept(ExpressionVisitor v) {
        v.visitConstant(this);
	}

}
