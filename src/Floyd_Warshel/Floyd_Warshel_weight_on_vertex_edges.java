package Floyd_Warshel;

public class Floyd_Warshel_weight_on_vertex_edges {
	static final int INF = Integer.MAX_VALUE;
	
	/**
	 * Floyd Warshel algorithm - finds all pair shortest path. Complexity:
	 * O(size^3).
	 */
	public static void FWAlgorithm(int mat[][]) {
		int n = mat.length;
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (mat[i][k] != INF && mat[k][j] != INF) {
						if (mat[i][j] > mat[i][k] + mat[k][j]) {
							mat[i][j] = mat[i][k] + mat[k][j];
						}
					}
				}
			}
		}
	}
	
	/**
	 * Algorithm used (formula):
	 * p(a,b)= f(a) + 2w(a,b) + f(b).
	 * @param matWeightEdges 
	 * @param arrWeightsVert - array of weights of the vertices.
	 * @return matrix with weight on the edges.
	 */
	public static int[][] weightOnVertAndEdgToEdg(int[][] matWeightEdges, int[] arrWeightsVert) {
		int n = matWeightEdges.length;
		int[][] mat = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matWeightEdges[i][j] != INF) {
					mat[i][j] = arrWeightsVert[i] + (2 * matWeightEdges[i][j]) + arrWeightsVert[j];
				} else
					mat[i][j] = INF;
			}
		}
		return mat;
	}

	public static int[][] edge2VertWeights(int[] vertexWeight, int matWEdges[][]) {
		int n = matWEdges.length;
		int[][] matWVert = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matWEdges[i][j] != INF)
					matWVert[i][j] = (matWEdges[i][j] + vertexWeight[i] + vertexWeight[j]) / 2;
				else
					matWVert[i][j] = INF;
			}
		}
		return matWVert;
	}

	public static int[][] toSourceWeights(int[][] weightsEdges, int[] weightsVertices) {
		int[][] m = weightOnVertAndEdgToEdg(weightsEdges, weightsVertices);
		printMatrix(m);
		System.out.println();
		FWAlgorithm(m);
		int ans[][] = edge2VertWeights(weightsVertices, m);
		return ans;
	}
	
	public static void printMatrix(int[][] mat) {
		System.out.println();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print((mat[i][j] == INF ? "∞" : mat[i][j]) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}


}
