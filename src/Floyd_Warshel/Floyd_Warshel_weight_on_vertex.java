package Floyd_Warshel;

import java.util.Arrays;

public class Floyd_Warshel_weight_on_vertex {

	static final int INF = Integer.MAX_VALUE;

	/**
	 * 
	 * @param arrWeight - an array of vertexes weight.
	 * @param boolMat   - matrix connections: boolMat[i]]j] = true iff exist edge.
	 * @return - matrix that weights on the edges.
	 */
	public static int[][] vert2EdgMat(int[] arrWeight, boolean boolMat[][]) {
		int size = boolMat.length;
		int matWEdges[][] = new int[size][size];
		for (int i = 0; i < size; i++)
			Arrays.fill(matWEdges[i], INF);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (boolMat[i][j] && i != j)
					matWEdges[i][j] = arrWeight[i] + arrWeight[j];
			}
		}
		return matWEdges;
	}

	/**
	 * Floyd Warshel algorithm - finds all pair shortest path. Complexity:
	 * O(size^3).
	 */
	public static void FWAlgorithm(int mat[][]) {
		int size = mat.length;
		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
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
	 * @param vertexWeight array of vertices weights
	 * @param matWEdges    matrix of edges weights
	 * @return matWVert - matrix of vertices weights
	 */
	public static int[][] edge2vert(int[] vertexWeight, int matWEdges[][]) {
		int size = matWEdges.length;
		int[][] matWVert = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (matWEdges[i][j] != INF)
					matWVert[i][j] = (matWEdges[i][j] + vertexWeight[i] + vertexWeight[j]) / 2;
			}
		}
		return matWVert;
	}

	/**
	 * @param vertexWeight - array of vertices weights
	 * @param matWVert     matrix of edges weights
	 * @return - matrix of vertices weights
	 */
	public static int[][] vert2Edg(int[] vertexWeight, int matWVert[][]) {
		int n = matWVert.length;
		int[][] matWEdges = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matWVert[i][j] != INF)
					matWEdges[i][j] = 2 * matWVert[i][j] - vertexWeight[i] - vertexWeight[j];
			}
		}
		return matWEdges;
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

	public static void main(String[] args) {
		int vertWeight[] = { 1, 2, 3, 4 };
		boolean boolMat[][] = { { false, true, true, false }, { true, false, false, true },
				{ true, false, false, true }, { false, true, true, false } };
		
		int edgeMat[][] = vert2EdgMat(vertWeight, boolMat);
		FWAlgorithm(edgeMat);
		int vertMat[][] = edge2vert(vertWeight, edgeMat);
		printMatrix(vertMat);
	}
}
