package composite.syntax;

public interface Expression {
	
	public int eval();
	
	public int depth();
	
	public void prettyPrint();
	
	public void prefixPrint();

}
