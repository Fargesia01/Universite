package visitor.tools;

import visitor.syntax.AdditionExpression;
import visitor.syntax.Constant;
import visitor.syntax.DivisionExpression;
import visitor.syntax.MultiplicationExpression;
import visitor.syntax.SubstractionExpression;


public interface ExpressionVisitor {
	
	void visitConstant(Constant e);
	
	void visitAdditionExpression(AdditionExpression e);
	
	void visitSubstractionExpression(SubstractionExpression e);
	
	void visitMultiplicationExpression(MultiplicationExpression e);
	
	void visitDivisionExpression(DivisionExpression e);

}
