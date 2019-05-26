package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class EulerCycle {
	private ArrayList<Integer>[] graph; // input graph.
	private ArrayList<Integer> path;// Euler cycle vertexes.
	private Stack<Integer> st;
	private int deg[]; // degrees array.
	private int numOfvert; // number of vertexes in the graph.

	/* Constructor - INIT data members */
	public EulerCycle(ArrayList<Integer>[] g) {
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
	 * Build Euler cycle. Complexity: O(|E|*|V|)
	 */
	public void buildEuilerCycle() {
		boolean isEuler = checkIfEulerCycle();
		if (!isEuler) {
			System.out.println("There is no Euler cycle in the graph!");
			return;
		} else {
			int v = 0;
			int u;
			st.push(v);
			while (!st.isEmpty()) { // O(E).
				v = st.peek();// return the top of the stack
				if (deg[v] == 0) {
					path.add(v); // add v to the Euler cycle.
					st.pop();// remove v from the stack.
				} else {// deg[v] is not 0.
					u = graph[v].get(0); // u = the first neighbor of v.
					st.add(u); // add u to the stack.
					deg[v]--;// decrease the degree of vertex v.
					deg[u]--;// decrease the degree of vertex u.
					graph[v].remove((Integer) u);// removing edge (v,u). O(|V|)
					graph[u].remove((Integer) v);// removing edge (u,v).

				}
			}
		}
	}

	/**
	 * Prints the Euler cycle.
	 */
	public void printEulerPath() {
		System.out.println("Euler cycle:");
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
	 * Checking if the graph is connected and all the vertexes have even degrees.
	 * 
	 * @return - true iff if the graph is connected and all the vertexes have even
	 *         degrees.
	 */
	private boolean checkIfEulerCycle() {
		for (int i = 0; i < numOfvert; i++) {
			if (graph[i].size() == 0) {// checking connected.
				return false;
			}
			if ((graph[i].size() % 2) != 0) {//checking if all degrees even.
				return false;
			}
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

		EulerCycle cycle = new EulerCycle(graph);
		cycle.printGraph();
		cycle.buildEuilerCycle();;
		cycle.printEulerPath();
		
		
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

		EulerCycle cycle1 = new EulerCycle(graph1);
		cycle1.printGraph();
		cycle1.buildEuilerCycle();
		cycle1.printEulerPath();
		
		
		
		System.out.println("-------------------------------------------------------------\n");
		
		size = 5;
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] graph2 = new ArrayList[size];
		for (int i = 0; i < size; i++) {
			graph2[i] = new ArrayList<Integer>();
		}
		graph2[0].add(1);
		graph2[0].add(4);
		graph2[1].add(0);
		graph2[1].add(2);
		graph2[2].add(1);
		graph2[2].add(3);
		graph2[3].add(2);
		graph2[3].add(4);
		graph2[4].add(3);
		graph2[4].add(0);// 0->1->2->3->4->0
		

		EulerCycle cycle2 = new EulerCycle(graph2);
		cycle2.printGraph();
		cycle2.buildEuilerCycle();
		cycle2.printEulerPath();
		
		
		
		
		
		
		
		

	}
}
