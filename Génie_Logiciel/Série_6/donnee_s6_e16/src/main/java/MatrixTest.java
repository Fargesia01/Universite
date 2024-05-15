import matrix.Matrix;

public class MatrixTest {

	public static void main(String[] args) {
		int rows = 4;
		int columns = 5;
		Matrix matrix = new Matrix(rows, columns);
		for (int i=1; i<=rows;i++) {
			for (int j=1; j<=columns; j++) {
				matrix.putItem(i, j , (String.valueOf(i)) + "-" + (String.valueOf(j)));
			}
		}
	
		//TODO: Add your code here !!
		//invoking createiterator() on matrix
		//using this iterator to display the matrix elements
	
	}

}
