package bottleProblem;

import java.util.Arrays;

/**
 * Bottle problem - using Floyd Warshel algorithm
 * Complexity O((M*N)^3)
 * @author shaynaor
 *
 */
public class BottleProblem {
	private int m, n, size;//number of rows, columns, size of the adjacency matrix.
	private int[][] mat; // The adjacency matrix.
	private String[][] path;
	private static final int INF = Integer.MAX_VALUE;
	

	/**
	 * Constructor.
	 * 
	 * @param m - the capacity of the second bottle.
	 * @param n - the capacity of the first bottle. 
	 * Call to buildMatrix() - and build the graph.
	 */
	public BottleProblem(int m, int n) {
		this.m = m;
		this.n = n;
		this.size = (m + 1) * (n + 1);
		buidMatrix();
		buidPath();
	}
	
	/** 
	 * Floyd Warshel algorithm - finds all pair shortest path.
	 * O(size^3)
	 */
	private void buidPath() {
		for(int k = 0 ; k < size ; k++) {
			for(int i = 0 ; i < size; i++) {
				for(int j = 0 ; j < size; j++) {
					if(mat[i][k] != INF && mat[k][j] != INF) {
						if(mat[i][j] > mat[i][k] + mat[k][j]) {
							mat[i][j] = mat[i][k] + mat[k][j];
							path[i][j] = path[i][k] + path[k][j];
						}
					}
				}
			}
		}
	}

	private void buidMatrix() {
		mat = new int[size][size];
		path = new String [size][size];
		/* init the adjacency matrix  to infinity and 
		 * the paths matrix to - "" */
		for(int i = 0 ; i < mat.length ; i++) {
			Arrays.fill(mat[i], INF);
			Arrays.fill(path[i], "");
		}
		
		/* Fill the adjacency matrix:
		 * 1 - if possible 
		 * 0 - else  */
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				setPath(i, j, 0, j); // emptying the left bottle.
				setPath(i, j, i, 0); // emptying the right bottle.
				setPath(i, j, i, n); // fill the right bottle.
				setPath(i, j, m, j); // fill the left bottle.
				setPath(i, j, Math.min(m, i + j), Math.max(0, i + j - m)); // pouring from right bottle to the left.
				setPath(i, j, Math.max(0, i + j - n), Math.min(n, i + j));// pouring from the left bottle to the right.
			}
		} 
	}
	/* i, j - source, from index.
	 * k, l - target, go to index */
	private void setPath(int i, int j, int k, int l) {
		int from = index(i, j);
		int to = index(k, l);
		mat[from][to] = 1;
		path[from][to] = "->(" + k +"," + l +")"; 
	}

	private int index(int i, int j) {
		return i * (n + 1) + j;
	}
	/* Print the adjacency matrix */
	public void printMat() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print((mat[i][j] == INF ? "∞" : mat[i][j]) + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Returns the string represents the path from (0,0) to (x,y)
	 */
	public String getPath(int x, int y) {
		if(mat[index(0,0)][index(x,y)] == INF) {
			return "no path!";
		}
		return "(0,0)" + path[index(0,0)][index(x,y)];
	}

	public static void main(String[] args) {
		BottleProblem b = new BottleProblem(1, 1);
		b.printMat();
		System.out.println("\n\n");
		BottleProblem b1 = new BottleProblem(2, 1);
		b1.printMat();

		System.out.println("\n\n");
		BottleProblem b2 = new BottleProblem(3, 5);
		b2.printMat();
		System.out.println(b2.getPath(0, 4));
		
		System.out.println("\n\n");
		BottleProblem b3 = new BottleProblem(3, 8);
		b3.printMat();
		System.out.println(b3.getPath(0, 4));
	}
}
