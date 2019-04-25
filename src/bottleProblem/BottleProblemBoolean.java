package bottleProblem;

public class BottleProblemBoolean {

	public static boolean[][] initBoolMat(int m, int n) {
		int dim = (m + 1) * (n + 1);
		boolean mat[][] = new boolean[dim][dim];
		/* Init the matrix to false. */
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				mat[i][j] = false;
			}
		}

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				int ind = index(i, j, n);
				mat[ind][index(0, j, n)] = true; // emptying the right bottle.
				mat[ind][index(i, 0, n)] = true; // emptying the left bottle.
				mat[ind][index(i, n, n)] = true; // fill the right bottle.
				mat[ind][index(m, j, n)] = true; // fill the left bottle.
				int k = index(Math.max(0, i + j - n), Math.min(n, i + j), n);
				mat[ind][k] = true; // pouring from the left bottle to the right.
				k = index(Math.min(m, i + j), Math.max(0, j + i - m), n);
				mat[ind][k] = true;// pouring from right bottle to the left.
			}
		}
		return mat;
	}

	/* Index calculation function. */
	private static int index(int i, int j, int n) {
		return (n + 1)*i + j;
	}

	public static void printBoolMatrix(boolean[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		boolean mat[][] = initBoolMat(2, 2);
		printBoolMatrix(mat);
	}

}
