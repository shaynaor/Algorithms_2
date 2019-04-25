package Floyd_Warshel;

public class Floyd_Warshel_Boolean {
	/**
	 * Build reachability matrix. From a neighbor matrix to a connectivity matrix.
	 * 
	 * @param bm - boolean matrix
	 */
	public static void FWB(boolean[][] bm) {
		int n = bm.length;
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					bm[i][j] = bm[i][j] || (bm[i][k] && bm[k][j]);
				}
			}
		}

	}
	
	/**
	 * After FW run this function returns true if path between i and j exist, 
	 * else return false.
	 * @param mat boolean neighbor matrix -after FW.
	 * @param i - from index.
	 * @param j - to index.
	 * @return true iff path exist.
	 */
	public static boolean pathExist(boolean mat[][], int i , int j) {
		return mat[i][j];
	}

	/**
	 * Complexity O(n)
	 * After FW run this function returns is the graph is connected.
	 * @param mat - boolean matrix
	 * @return true iff the graph is connected.
	 */
	public static boolean isConnected(boolean mat[][]) {
		for (int i = 0; i < mat.length; i++) {
			if (!mat[i][0])
				return false;
		}
		return true;
	}

	/**
	 * This function calculate how much connectivity components in the matrix. Works
	 * on square matrix. ComplexityO(n^2)
	 * 
	 * @param mat - a boolean matrix.
	 */
	public static int numConnectComponents(boolean[][] mat) {
		int n = mat.length;
		int connectComp[] = new int[n];
		int numComponentes = 0;

		for (int i = 0; i < n; i++) {
			if (connectComp[i] == 0) {
				numComponentes++;
				connectComp[i] = numComponentes;
			}
			for (int j = i + 1; j < n; j++) {
				/* Vertex j is not defined yet and the path exists */
				if (connectComp[j] == 0 && mat[i][j]) {
					connectComp[j] = numComponentes;
				}
			}
		}
		return numComponentes;
	}

	public static void printBoolMatrix(boolean mat[][]) {
		System.out.println();
		for(int i = 0 ; i < mat.length ; i ++) {
			for(int j = 0 ; j < mat[0].length ; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		boolean[][] mat = { { true, true, true, false }, 
				{ true, true, false, true }, 
				{ true, false, true, true },
				{ false, true, true, true } };
		System.out.println("The matrix before changes:");
		printBoolMatrix(mat);
		FWB(mat);
		System.out.println("Matrix after connectivity function changes: ");
		printBoolMatrix(mat);
		System.out.println("is the graph connected?  " + isConnected(mat));
		System.out.println("Number of componentes: " + numConnectComponents(mat) );
		System.out.println();

/*
		boolean[][] mat1 = { { false, false, false, false }, 
				{ false, false, false, false  }, 
				{ false, false, false, false  },
				{ false, false, false, false  } };

		System.out.println("Matrix1 before changes:");
		printBoolMatrix(mat1);
		FWB(mat1);
		System.out.println("The matrix1 after connectivity function changes: ");
		printBoolMatrix(mat1);
		System.out.println("is the graph connected?  " + isConnectedOn(mat1));
		System.out.println("Number of componentes: " + numConnectComponents(mat1) );


		boolean[][] mat2 = { { false, false, false, false }, 
				{ false, false, true, false  }, 
				{ false, false, false, false  },
				{ false, false, false, false  } };

		System.out.println("Matrix2 before changes:");
		printBoolMatrix(mat2);
		FWB(mat2);
		System.out.println("The matrix1 after connectivity function changes: ");
		printBoolMatrix(mat2);
		System.out.println("is the graph connected?  " + isConnectedOn(mat2));
		System.out.println("Number of componentes: " + numConnectComponents(mat2) ); */

	}
}
