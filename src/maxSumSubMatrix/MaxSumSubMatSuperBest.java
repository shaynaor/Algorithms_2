package maxSumSubMatrix;

/**
 * Find a sub matrix with the maximum sum in given matrix. Using best algorithm.
 * 
 * @author shaynaor
 *
 */
public class MaxSumSubMatSuperBest {
	/**
	 * Complexity O(m*n^2)
	 * 
	 * @param mat- integer matrix.
	 */
	public static void maxSumSubMatSuperBest(int mat[][]) {

		int[][] help = buildHelp(mat);// O(m*n)
		int m = help.length, n = help[0].length;
		int i = 0, sum = 0, startRow = 0, endRow = 1, startCol = 0, endCol = 0;
		int maxSum = mat[0][0];
		for (int j = 0; j < n; j++) {
			for (int q = j; q < n; q++) {
				i = 0;
				for (int p = 0; p < m; p++) {
					sum = sumMinor(help, i, j, p, q);
					if (sum < 0) {
						i = p + 1;
					} else if (sum > maxSum) {
						maxSum = sum;
						endRow = p;
						startRow = i;
						startCol = j;
						endCol = q;
					}
				}
			}
		}
		System.out.println(
				"startRow: " + startRow + ", startCol: " + startCol + ", endRow: " + endRow + ", endCol: " + endCol);
		System.out.println("Max Sum: " + maxSum);
	}

	/**
	 * Complexity: O (m*n)
	 * 
	 * @param mat- integer matrix.
	 * @return - help matrix - matrix of sub matrix sum.
	 */
	public static int[][] buildHelp(int mat[][]) {
		int m = mat.length, n = mat[0].length;
		int help[][] = new int[m][n];
		help[0][0] = mat[0][0];
		/* First column */
		for (int i = 1; i < m; i++) {
			help[i][0] = help[i - 1][0] + mat[i][0];
		}
		/* First row */
		for (int j = 1; j < n; j++) {
			help[0][j] = help[0][j - 1] + mat[0][j];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				help[i][j] = mat[i][j] + help[i - 1][j] + help[i][j - 1] - help[i - 1][j - 1];
			}
		}
		return help;
	}

	/**
	 * 
	 * Complexity O(1).
	 * 
	 * @param help matrix - matrix of sub matrix sum.
	 * @param i    -start row index.
	 * @param j    - start col index.
	 * @param p    - end row index.
	 * @param q    - end col index.
	 * @return - the sum of sub matrix.
	 */
	public static int sumMinor(int help[][], int i, int j, int p, int q) {
		if (i == 0 && j == 0) {
			return help[p][q];
		} else if (i == 0 && j > 0) {
			return help[p][q] - help[p][j - 1];
		} else if (i > 0 && j == 0) {
			return help[p][q] - help[i - 1][q];
		} else {
			return help[p][q] - help[p][j - 1] - help[i - 1][q] + help[i - 1][j - 1];
		}
	}

	public static void main(String[] args) {
		int mat[][] = { { 0, -2, -7, 0 }, { 9, 2, -6, 2 }, { -4, 1, -4, 1 }, { -1, 8, 0, -2 } };// max sub mat is
		// {{9,2},{-4,1},{-1,8}}
		// ans the sum is: 15
		maxSumSubMatSuperBest(mat);

	}

}
