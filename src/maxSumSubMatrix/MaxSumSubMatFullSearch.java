package maxSumSubMatrix;

/**
 * Find a sub matrix with the maximum sum in given matrix. Using full search
 * algorithm.
 * 
 * @author shaynaor
 *
 */
public class MaxSumSubMatFullSearch {

	public static void maxSumSubMatFullSearch(int mat[][]) {
		int m = mat.length;
		int n = mat[0].length;
		int sum = 0, maxSum = 0, startRow = 0, startCol = 0, endRow = 0, endCol = 0;
		/* Goes over all rows. */
		for (int i = 0; i < m; i++) {
			/* Goes over all cols. */
			for (int j = 0; j < n; j++) {
				/* All sub rows */
				for (int k = i; k < m; k++) {
					/* All sub cols */
					for (int l = j; l < n; l++) {
						/* for - for to calculate the sum of the sub-matrix. */
						for (int r = i; r <= k; r++) {
							for (int c = j; c <= l; c++) {
								sum += mat[r][c];
							}
						}
						/*
						 * If the current sum is bugger then max sum update the max sum and the indexes.
						 */
						if (maxSum < sum) {
							maxSum = sum;
							startRow = i;
							startCol = j;
							endRow = k;
							endCol = l;
						}
						sum = 0;
					}
				}
			}
		}
		System.out.println(
				"startRow: " + startRow + ", startCol: " + startCol + ", endRow: " + endRow + ", endCol: " + endCol);
		System.out.println("Max Sum: " + maxSum);
	}

	public static void main(String[] args) {
		int mat[][] = { { 0, -2, -7, 0 }, { 9, 2, -6, 2 }, { -4, 1, -4, 1 }, { -1, 8, 0, -2 } };// max sub mat is
																								// {{9,2},{-4,1},{-1,8}}
																								// ans the sum is: 15
		maxSumSubMatFullSearch(mat);
	}
}
