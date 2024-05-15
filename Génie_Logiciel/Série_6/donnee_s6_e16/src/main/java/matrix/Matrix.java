package matrix;
import iterator.Iterable;
import iterator.Iterator;

public class Matrix implements Iterable {
	
	private int rows, columns;
	private Object mat[][];
	
	public Matrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		
		mat = new Object[rows][columns];
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	/** Return a given item of the m x n matrix (m rows, n columns).
	 * @param row The row of the item to return (from 1 to m).
	 * @param column The column of the item to return (from 1 to n).
	 * @return The object at position (row,column) in the matrix.
	 */
	public Object getItem(int row, int column) {
		return mat[row-1][column-1];
	}
	
	
	/** Inserts a given object in the m x n matrix (m rows, n columns).
	 * @param row The row where to insert the item (from 1 to m).
	 * @param column The column where to insert the item (from 1 to n).
	 * @param o The object to put in the matrix at the given position.
	 */
	public void putItem(int row, int column, Object o) {
		mat[row-1][column-1] = o;
	}

	public Iterator createIterator() {
		//TODO: Add your code here !!
		return null;
	}

}
