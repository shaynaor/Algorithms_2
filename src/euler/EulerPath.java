package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import BFS.BFS_Algorithm;

public class EulerPath {
	private ArrayList<Integer>[] graph; // input graph.
	private ArrayList<Integer> path;// Euler path vertexes.
	private Stack<Integer> st;
	private int deg[]; // degrees array.
	private int numOfvert; // number of vertexes in the graph.
	private int source; // where the path start(odd vertex).

	/* Constructor - INIT data members */
	public EulerPath(ArrayList<Integer>[] g) {
		graph = g;
		path = new ArrayList<Integer>();
		st = new Stack<Integer>();
		numOfvert = graph.length;
		deg = new int[numOfvert];
		for (int i = 0; i < numOfvert; i++) {
			deg[i] = graph[i].size();
		}
	}

	/**
	 * Build Euler path. Complexity: O(|E|*|V|)
	 */
	public void buildEuilerPath() {
		boolean isEuler = checkIfEulerPath();
		if (!isEuler) {
			System.out.println("There is no Euler path in the graph!");
			return;
		} else {
			int v = source;
			int u;
			st.push(v);
			while (!st.isEmpty()) { // O(E).
				v = st.peek();// return the top of the stack
				if (deg[v] == 0) {
					path.add(v); // add v to the Euler path.
					st.pop();// remove v from the stack.
				} else {// deg[v] is not 0.
					u = graph[v].get(0); // u = the first neighbor of v.
					st.add(u); // add u to the stack.
					deg[v]--;// decrease the degree of vertex v.
					deg[u]--;// decrease the degree of vertex u.
					graph[v].remove((Integer)u);// removing edge (v,u). O(|V|)
					graph[u].remove((Integer)v);// removing edge (u,v).
				
				}
			}
		}
	}

	/**
	 * Prints the Euler path.
	 */
	public void printEulerPath() {
		System.out.println("Euler path:");
		System.out.println(path.toString());
	}

	/**
	 * Prints the graph.
	 */
	public void printGraph() {
		System.out.println("The Graph:");
		System.out.println(Arrays.toString(graph));
	}

	/**
	 * Checking if the graph is connected and have two or zero vertex with odd
	 * degrees.
	 * 
	 * @return - true iff if the graph is connected and have two or zero vertex with
	 *         odd degrees.
	 */
	private boolean checkIfEulerPath() {
		int numOfOddVert = 0;
		for (int i = 0; i < numOfvert; i++) {
			if (graph[i].size() == 0) {// checking connected.
				return false;
			}
			if ( (graph[i].size() % 2) != 0) {// counting the number of odd vertexes.
				source = i;
				numOfOddVert++;
			}
		}
		return (numOfOddVert == 2) || (numOfOddVert == 0);
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

		EulerPath path = new EulerPath(graph);
		path.buildEuilerPath();
		path.printGraph();
		path.printEulerPath();
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

		EulerPath path1 = new EulerPath(graph1);
		path1.printGraph();
		path1.buildEuilerPath();
		path1.printEulerPath();
		

	}

}
