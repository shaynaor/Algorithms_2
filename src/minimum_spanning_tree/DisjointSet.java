package minimum_spanning_tree;

import java.util.Arrays;

public class DisjointSet {
	/* Private data members. */
	private int parent[];
	private int rank[];

	/* Constructor. */
	public DisjointSet(int n) {
		parent = new int[n];
		rank = new int[n];
	}

	/**
	 * This method makes a set that containing only one element.
	 * 
	 * @param v - the single element.
	 */
	public void makeSet(int v) {
		parent[v] = v;
		rank[v] = 0;
	}

	/**
	 * This method determine which subset a particular element is in.
	 * 
	 * @param val - the value that we search which subset is in.
	 * @return - the parent of the subset. an item from the subset the
	 *         representative as the parent of the subset.
	 */
	public int find(int v) {
		int p = parent[v];
		if (v == p) {
			return v;
		}
		return parent[v] = find(parent[p]);
	}
	/**
	 * This method makes union between to two different subsets.
	 * @return - true if can execute the union iff rootU != rooV. 
	 */
	public boolean union(int u, int v) {
		boolean ans = false;
		int rootU = find(u); //root of u.
		int rootV = find(v); //root of v.
		if(rootU == rootV) {//if same root (in the same subset). return false.
			ans = false;
		}else {// else -> u , v in different subsets
			ans = true;
			if(rank[rootU] > rank[rootV]) {//if the subset of u bigger then the subset of v.
				parent[rootV] = rootU;//rootU become the root of subset  v.
			}else if(rank[rootU] < rank[rootV]) {//if the subset of v bigger then the subset of v.
				parent[rootU] = rootV;
			}
			else {//rank[rootU] == rank[rootV]
				parent[rootV] = rootU;
				rank[rootU]++;
			}
		}
		return ans;
	}
	
	/* Returns true iff v and u in the same subset. */
	public boolean connected(int v, int u) {
		return find(v) == find(u);
	}	
	
}
