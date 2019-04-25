package Floyd_Warshel;

/**
 * Floyd Warshel algorithm - receives matrix of integers values that represent
 * the weight on edges in graph and the algorithm finds all pair shortest path.
 * Complexity: O(n^3).
 * 
 * @author shaynaor
 *
 */
public class Floyd_Warshel_weight_on_edges {
	static final int INF = Integer.MAX_VALUE;

	/**
	 * Floyd Warshel algorithm. Complexity: O(n^3).
	 * 
	 * @param mat - neighbor matrix.
	 */
	public static void FWmat(int[][] mat) {
		int size = mat.length;
		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (mat[i][k] != INF && mat[k][j] != INF) {
						mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
					}
				}
			}
		}
	}

	/**
	 * Floyd Warshel algorithm. Complexity: O(n^3).
	 * 
	 * @param mat - neighbor matrix.
	 * @return - matrix that represent the shortest path between all pair of vertex.
	 */
	public static String[][] FWAlgorithm(int[][] mat) {
		int size = mat.length;
		String path[][] = new String[size][size];
		/* init path matrix. */
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (mat[i][j] != INF)
					path[i][j] = "->" + j;
				else
					path[i][j] = "";
			}
		}
		/* matrix- mat, path building */
		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (mat[i][k] != INF && mat[k][j] != INF) {
						if (mat[i][j] > mat[i][k] + mat[k][j]) {
							mat[i][j] = mat[i][k] + mat[k][j];
							path[i][j] = path[i][k] + path[k][j];
						}
					}
				}
			}
		}
		return path;
	}

	public static double getPathWeight(int mat[][], int from, int to) {
		return mat[from][to];
	}

	public static String getPath(String path[][], int from, int to) {
		return from + path[from][to];
	}

	/**
	 * his function calculate how much connectivity components in the matrix. Works
	 * on square matrix. ComplexityO(n^2)
	 * 
	 * @return number of connected components
	 */
	public static int numConnectComponents(int mat[][]) {
		int size = mat.length;
		int connectComp[] = new int[size];
		int numComponentes = 0;

		for (int i = 0; i < size; i++) {
			if (connectComp[i] == 0) {
				numComponentes++;
				connectComp[i] = numComponentes;
			}
			for (int j = i + 1; j < size; j++) {
				/* vertex j is not defined yet and the path exists */
				if (connectComp[j] == 0 && mat[i][j] != INF) {
					connectComp[j] = numComponentes;
				}
			}
		}
		return numComponentes;
	}

	/**
	 * Complexity O(n)
	 * 
	 * @param mat - boolean matrix
	 * @return true iff the graph is connected.
	 */
	public static boolean isConnectedOn(int mat[][]) {
		for (int i = 0; i < mat.length; i++) {
			if (mat[i][0] == INF)
				return false;
		}
		return true;
	}

	public static void printMatrix(int mat[][]) {
		System.out.println();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print((mat[i][j] == INF ? "∞" : mat[i][j]) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int mat[][] = { { 0, 3, INF, 7 }, { 8, 0, 2, INF }, { 5, INF, 0, 1 }, { 2, INF, INF, 0 } };

		System.out.println("The matrix before changes:");
		printMatrix(mat);
		FWmat(mat);
		System.out.println("Matrix after connectivity function changes: ");
		printMatrix(mat);
		System.out.println(getPathWeight(mat, 1, 0));

		int mat1[][] = { { 0, 3, INF, 7 }, { 8, 0, 2, INF }, { 5, INF, 0, 1 }, { 2, INF, INF, 0 } };

		System.out.println("The matrix1 before changes:");
		printMatrix(mat1);
		String path[][] = FWAlgorithm(mat1);
		System.out.println("Matrix1 after connectivity function changes: ");
		printMatrix(mat);
		System.out.println(getPath(path, 1, 0));

		System.out.println("is the graph connected?  " + isConnectedOn(mat));
		System.out.println("Number of componentes: " + numConnectComponents(mat));
		System.out.println();
	}
}
