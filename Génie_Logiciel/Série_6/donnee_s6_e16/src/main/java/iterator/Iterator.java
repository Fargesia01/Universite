package iterator;

public interface Iterator {
	
	public abstract void first();
	
	public abstract void next();
	
	public abstract boolean isDone();
	
	public abstract Object currentItem();

}
