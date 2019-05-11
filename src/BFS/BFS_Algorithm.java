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
	public BFS_Algorithm(ArrayList<Integer> g[]) {
		size = g.length;
		graph = new ArrayList[size];
		for (int i = 0; i < size; i++) {
			graph[i] = new ArrayList<Integer>(g[i]);
		}
		queue = new ArrayBlockingQueue<Integer>(size);
		color = new int[size];
		parent = new int[size];
		partition = new int[size];
		components = new int[size];
		source = 0;
		numComps = 0;
		BFS(g[0].get(0)); // starts BFS algorithm with the first item(could be any other item).
	}

	/**
	 * BFS algorithm.
	 * 
	 * @param s - the‬‬ ‫‪source‬‬ ‫‪vertex‬‬.
	 */
	public void BFS(int s) {
		source = s;
		/* INIT arrays */
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
			int u = queue.peek(); // retrieves and removes the head of this queue.
			queue.poll();
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
	 * has the graph is not connected else the graph is connected.
	 * 
	 * @return - true iff graph is connected.
	 */
	public boolean isConnected() {
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
	 * @return - string the represent the path in BFS from s to v.
	 */
	public String printPath(int s, int v) {
		BFS(s); // run BFS with s- source vertex.
		String path = "";
		if (dist[v] == NIL) {
			return "There is no path exist between s to v!!";
		} else if (s == v) {
			return "" + s;
		} else {
			path += v;
			int parent = this.parent[v];
			while(parent !=NIL) {
				path = parent + "->" + path;
				parent = this.parent[parent];
			}
			
		}
		return "";
	}

}
