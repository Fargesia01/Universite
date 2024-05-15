package visitor.tools;

import visitor.syntax.AdditionExpression;
import visitor.syntax.Constant;
import visitor.syntax.DivisionExpression;
import visitor.syntax.MultiplicationExpression;
import visitor.syntax.SubstractionExpression;


public class PrettyPrintVisitor implements ExpressionVisitor {
	
	protected StringBuffer result = new StringBuffer();
	
	public String getResult() {
		return result.toString();
	}

	public void visitConstant(Constant e) {
		result.append(e.getValue());
	}

	public void visitAdditionExpression(AdditionExpression e) {
		result.append("( ");
		e.getLeft().accept(this);
		result.append(" + ");
		e.getRight().accept(this);
		result.append(" )");
	}
	
	public void visitSubstractionExpression(SubstractionExpression e) {
		result.append("( ");
		e.getLeft().accept(this);
		result.append(" - ");
		e.getRight().accept(this);
		result.append(" )");
	}
	
	public void visitMultiplicationExpression(MultiplicationExpression e) {
		result.append("( ");
		e.getLeft().accept(this);
		result.append(" x ");
		e.getRight().accept(this);
		result.append(" )");
	}
	
	public void visitDivisionExpression(DivisionExpression e) {
		result.append("( ");
		e.getLeft().accept(this);
		result.append(" : ");
		e.getRight().accept(this);
		result.append(" )");
	}

}