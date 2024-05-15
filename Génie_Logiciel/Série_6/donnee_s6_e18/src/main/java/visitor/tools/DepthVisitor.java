package visitor.tools;

import visitor.syntax.AdditionExpression;
import visitor.syntax.Constant;
import visitor.syntax.DivisionExpression;
import visitor.syntax.MultiplicationExpression;
import visitor.syntax.SubstractionExpression;


public class DepthVisitor implements ExpressionVisitor {
	
	protected double depth;
	
	public double getResult() {
		return (depth);
	}

	public void visitConstant(Constant e) {
        return ;
	}

	public void visitAdditionExpression(AdditionExpression e) {
        depth++;
        e.getLeft().accept(this);
        e.getRight().accept(this);
	}
	
	public void visitSubstractionExpression(SubstractionExpression e) {
        depth++;
        e.getLeft().accept(this);
        e.getRight().accept(this);
	}
	
	public void visitMultiplicationExpression(MultiplicationExpression e) {
        depth++;
        e.getLeft().accept(this);
        e.getRight().accept(this);
	}
	
	public void visitDivisionExpression(DivisionExpression e) {
        depth++;
        e.getLeft().accept(this);
        e.getRight().accept(this);
	}

}
