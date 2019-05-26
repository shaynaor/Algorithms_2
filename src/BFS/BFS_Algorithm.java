package BFS;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * BFS (Breadth First Search) algorithm. Complexity: O(|V|+|E|)
 * 
 * @author shaynaor
 *
 */
public class BFS_Algorithm {
	/* Private data members. */
	private int size; // number of vertexes.
	private ArrayList<Integer> graph[]; // ‫‪the‬‬ ‫‪graph‬‬ ‫‪is‬‬ ‫‪represented‬‬ ‫‪using‬‬ ‫‪adjacency-list‬‬.
	private Queue<Integer> queue; // ‫‪the‬‬ ‫‪queue‬‬ ‫‪to‬‬ ‫‪manage‬‬ ‫‪the‬‬ ‫‪set‬‬ ‫‪of‬‬ ‫‪gray‬‬
									// ‫‪vertices.
	private int color[]; // ‫‪the‬‬ ‫‪color‬‬ ‫‪of‬‬ ‫‪vertex‬‬ ‫‪u‬‬ ‫‪is‬‬ ‫‪stored‬‬ ‫‪in‬‬
							// color[u‬‬‬‬
	private int parent[]; // ‫‪the‬‬ ‫‪predecessor‬‬ ‫)‪(parent‬‬ ‫‪of‬‬ ‫‪vertex‬‬ ‫‪u‬‬ ‫‪is‬‬
							// ‫‪stored‬‬ ‫‪in‬‬ ‫‪p[u].‬‬ ‫‪If‬‬ ‫‪u‬‬ ‫‪has‬‬ ‫‪no‬‬ ‫‪predecessor‬‬
							// ‫‪then‬‬ ‫]‪[u‬‬ ‫=‬ ‫‪nil
	private int dist[]; // ‫‪the‬‬ ‫‪distance‬‬ ‫‪from‬‬ ‫‪the‬‬ ‫‪source‬‬ ‫‪vertex‬‬ ‫‪s‬‬ ‫‪to‬‬
						// ‫‪vertex‬‬ ‫‪u,‬‬ ‫‪computed‬‬ ‫‪by‬‬ ‫‪the‬‬ ‫‪algorithm‬‬ ‫‪is‬‬ ‫‪stored‬‬
						// ‫‪in‬‬ ‫‪d[u].
	private static final int WHITE = 1, GRAY = 2, BLACK = 3, NIL = -1;
	private int source; // ‫‪the‬‬ ‫‪source‬‬ ‫‪vertex‬‬.
	private int partition[];
	private int numComps;
	private int components[];

	/**
	 * Constructor - INIT all data members.
	 * 
	 * @param g -‫‪the‬‬ ‫‪graph‬‬ ‫‪is‬‬ ‫‪represented‬‬ ‫‪using‬‬
	 *          ‫‪adjacency-list‬‬.
	 */
	@SuppressWarnings("unchecked")
	public BFS_Algorithm(ArrayList<Integer> g[]) {
		size = g.length;
		graph = new ArrayList[size];
		for (int i = 0; i < size; i++) {
			graph[i] = new ArrayList<Integer>(g[i]);
		}
		queue = new ArrayBlockingQueue<Integer>(size);
		color = new int[size];
		parent = new int[size];
		dist = new int[size];
		partition = new int[size];
		components = new int[size];
		source = 0;
		numComps = 0;
		// BFS(0); // starts BFS algorithm with the first item(could be any other item).
	}

	/**
	 * BFS algorithm.
	 * 
	 * @param s - the‬‬ ‫‪source‬‬ ‫‪vertex‬‬.
	 */
	public void BFS(int s) {
		source = s;
		int u = 0;
		/* INIT arrays - color, distance, and parent. */
		for (int i = 0; i < size; i++) {
			color[i] = WHITE; // colored all vertices in white.
			dist[i] = NIL; // all distances = -1.
			parent[i] = NIL; // all parent = -1.
		}
		color[source] = GRAY; // paint source vertex in gray.
		dist[source] = 0; // the distance between source to himself ->0.
		parent[source] = NIL; // source has no parent (root).
		queue.add(source); // add source vertex to the queue.
		while (!queue.isEmpty()) {
			u = queue.poll(); // retrieves and removes the head of this queue.
			/* goes over all adj[u]. */
			for (int v : graph[u]) {
				/* if v is not visited yet. */
				if (color[v] == WHITE) {
					color[v] = GRAY; // paint v in gray.
					dist[v] = dist[u] + 1; // update the distance of v.
					parent[v] = u; // set u as a parent of v.
					queue.add(v); // add v to the queue.
				}
			}
			color[u] = BLACK; // after going over all adj[u]- paint u in black.
		}
	}

	/**
	 * This method goes over dist[] and check if one of the cells has NIL value, if
	 * has the graph is not connected else the graph is connected. Complexity:
	 * O(|V|+|E|).
	 * 
	 * @return - true iff graph is connected.
	 */
	public boolean isConnected() {
		BFS(0);
		for (int i = 0; i < dist.length; i++) {
			if (dist[i] == NIL) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param s -source vertex.
	 * @param v - destination vertex.
	 * @return - string the represent the path in BFS from s to v. Complexity:
	 *         O(|V|+|E|).
	 */
	public String printPath(int s, int v) {
		BFS(s); // run BFS with s- source vertex.
		String path = "";
		if (dist[v] == NIL) {
			return "There is no path exist between s to v!!";
		}
		if (s == v) {
			return "" + s;
		} else {
			path += v;
			int parent = this.parent[v];
			while (parent != NIL) {
				path = parent + "->" + path;
				parent = this.parent[parent];
			}

		}
		return path;
	}

	/**
	 * Complexity: O( 2(|V|+|E|) ).
	 * 
	 * @return - the diameter of the graph.
	 */
	public int findDiameter() {
		BFS(0);// BFS with the first element.
		int ind = findMax(); // find the maximum distance.
		BFS(ind);// run BFS again with the max distance.
		ind = findMax();// find max again - the diameter.
		return dist[ind];
	}

	/**
	 * Complexity: O( 2(|V|+|E|) ).
	 * If diameter is even -> radius = diameter /2.
	 * else radius = (diameter+1)/2.
	 * 
	 * @return - the radius of the tree.
	 */
	public int findRadius() {
		int diameter = this.findDiameter();
		if (diameter % 2 == 0) {
			return diameter / 2;
		}
		return (diameter + 1) / 2;
	}

	/**
	 * Complexity: O( 2(|V|+|E|) ).
	 * 
	 * If diameter is even -> the tree have 1 center.
	 * else the graph have 2 centers.
	 * @return - how many centers the tree has.
	 */
	public int howManyCenters() {
		int diameter = this.findDiameter();
		if (diameter % 2 == 0) {
			return 1;
		}
		return 2;
	}

	/**
	 * This function represent naive solution to find index of max in array. number
	 * of comparisons: n-1. complexity: O(n).
	 * 
	 * @param a array.
	 * @return the maximum value in the array
	 */
	private int findMax() {
		int max = dist[0];
		int index = 0;
		for (int i = 1; i < dist.length; i++) {
			if (dist[i] > max) {
				max = dist[i];
				index = i;
			}
		}
		return index;
	}

	public String getAllComponents() {
		numOfConnectedComponents();
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] compsList = new ArrayList[numComps];
		for (int i = 0; i < compsList.length; i++) {
			compsList[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < components.length; i++) {
			int n = components[i];
			compsList[n - 1].add(i);
		}
		String ans = new String();
		for (int i = 0; i < compsList.length; i++) {
			ans = ans + compsList[i] + "\n";
		}
		return ans;
	}

	/**
	 * 
	 * @return - the number of connected components in g graph.
	 */
	public int numOfConnectedComponents() {
		while (hasNextComponent()) {
			numComps++;
			BFS(source);
			for (int i = 0; i < components.length; i++) {
				if (dist[i] != NIL) {
					components[i] = numComps;
				}
			}
		}
		return numComps;
	}

	/**
	 * 
	 * @return - true iff has next vertex.
	 */
	private boolean hasNextComponent() {
		for (int i = 0; i < components.length; i++) {
			if (components[i] == 0) {
				source = i;
				return true;
			}
		}
		return false;
	}

	public boolean isBipartite() {
		int u = 0;
		/* INIT arrays - color, distance, and parent. */
		for (int i = 0; i < size; i++) {
			color[i] = WHITE; // colored all vertices in white.
			dist[i] = NIL; // all distances = -1.
			parent[i] = NIL; // all parent = -1.
			partition[i] = 0;
		}
		source = 0;
		color[source] = GRAY; // paint source vertex in gray.
		dist[source] = 0; // the distance between source to himself ->0.
		parent[source] = NIL; // source has no parent (root).
		partition[source] = 1;
		queue.add(source); // add source vertex to the queue.
		while (!queue.isEmpty()) {
			u = queue.poll(); // retrieves and removes the head of this queue.
			/* goes over all adj[u]. */
			for (int v : graph[u]) {
				/* if found edge in the same group - return false. */
				if (partition[u] == partition[v]) {
					return false;
				}
				/* if v is not visited yet. */
				if (color[v] == WHITE) {
					color[v] = GRAY; // paint v in gray.
					dist[v] = dist[u] + 1; // update the distance of v.
					parent[v] = u; // set u as a parent of v.
					queue.add(v); // add v to the queue.
				}
			}
			color[u] = BLACK; // after going over all adj[u]- paint u in black.
		}
		return true;
	}

	public static void main(String[] args) {
		int size = 7;
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] graph = new ArrayList[size];
		for (int i = 0; i < size; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		graph[0].add(1);
		graph[1].add(0);
		graph[1].add(2);
		graph[1].add(4);
		graph[2].add(1);
		graph[2].add(3);
		graph[3].add(2);
		graph[4].add(1);
		graph[4].add(5);
		graph[5].add(4);
		graph[5].add(6);
		graph[6].add(5);

		BFS_Algorithm bfs = new BFS_Algorithm(graph);
		// bfs.BFS(0);
		System.out.println("Is connected? " + bfs.isConnected()); // yes
		System.out.println("The path is: " + bfs.printPath(0, 6)); // 0->1->4->5->6
		System.out.println("Diameter: " + bfs.findDiameter()); // diameter = 5
		System.out.println("Rdius: " + bfs.findRadius()); // radius = 3
		System.out.println("The graph have " + bfs.howManyCenters() + " centers"); // 2
		System.out.println("Number of components: " + bfs.numOfConnectedComponents() + " \nAnd the components: "
				+ bfs.getAllComponents());// 1 . all vertex

		/////
		System.out.println("-------------------------------------------------------------\n");

		size = 5;
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] graph1 = new ArrayList[size];
		for (int i = 0; i < size; i++) {
			graph1[i] = new ArrayList<Integer>();
		}
		graph1[0].add(1);
		graph1[1].add(0);
		graph1[1].add(2);
		graph1[2].add(1);
		graph1[2].add(3);
		graph1[3].add(2);
		graph1[3].add(4);
		graph1[4].add(3);// 0->1->2->3->4

		BFS_Algorithm bfs1 = new BFS_Algorithm(graph1);
		System.out.println("Is connected? " + bfs1.isConnected()); // Yes
		System.out.println("The path is: " + bfs1.printPath(0, 4)); // 0->1->2->3->4->
		System.out.println("Diameter: " + bfs1.findDiameter()); // diameter = 4
		System.out.println("Rdius: " + bfs1.findRadius()); // radius = 2
		System.out.println("The graph have " + bfs1.howManyCenters() + " centers"); // 1
		System.out.println("Number of components: " + bfs1.numOfConnectedComponents() + " \nAnd the components: "
				+ bfs1.getAllComponents());// 1 . all vertex

	}

}
