package minimum_spanning_tree;

import java.util.Arrays;

public class KruskalAlgorithm {
	/* Private data members. */
	Edge _graph[];
	Edge _tree[];
	DisjointSet _vertexSet;
	int _numOfEdges, _numOfVertices, _numOfEdgesInTree;
		
	/* Constructor. */
	public KruskalAlgorithm(Edge graph[], int numOfVertices ) {
		_graph = graph;
		_numOfEdges = graph.length;
		_numOfEdgesInTree = 0;
		_numOfVertices = numOfVertices;
		_vertexSet = new DisjointSet(numOfVertices);
		_tree = new Edge[_numOfVertices - 1];
		Arrays.sort(_graph);//O (|E|log|V|)
		for(int i  = 0 ; i < _numOfVertices ; i++) {//O(|V)
			_vertexSet.makeSet(i);
		}
	}
	
	/**
	 * This method creates the minimum spanning tree.
	 * Add edge to the tree iff this edge don't close a circle. 
	 */
	public void createMinSpanningTree() {
		int i = 0;
		while( (_numOfEdgesInTree < _numOfVertices - 1) && (i < _numOfEdges) ) {
			if(_vertexSet.union(_graph[i].get_u(), _graph[i].get_v())) { // O(|E|log|V|)
				_tree[_numOfEdgesInTree++] = _graph[i];
			}
			i++;
		}
	}
	/**
	 * 
	 * @return - the sum of all edges in the minimum spanning tree.
	 */
	public double calcTreeWeight() {
		double weight = 0;
		for(int i = 0 ; i < _numOfEdgesInTree ; i++) {
			weight += _tree[i].get_weight();
		}
		return weight;
	}
	
	public void printTree() {
		for(int i = 0 ; i < _numOfEdgesInTree ; i++) {
			System.out.println(_tree[i].toString());
		}
	}
	
	public static void main(String[] args) {
		int numVert = 8;
		Edge graph[] = new Edge[8];
		graph[0] = new Edge(4, 6, 2);
		graph[1] = new Edge(0, 1, 6);
		graph[2] = new Edge(6, 7, 8);
		graph[3] = new Edge(2, 3, 9);
		graph[4] = new Edge(6, 0, 11);
		graph[5] = new Edge(3, 4, 14);
		graph[6] = new Edge(4, 5, 21);
		graph[7] = new Edge(1, 2, 19);
		
		KruskalAlgorithm kru = new KruskalAlgorithm(graph, numVert);
		kru.createMinSpanningTree();
		System.out.println(kru.calcTreeWeight());
		
		
		System.out.println("*-----------------------------*");
		
		
		numVert = 8;
		Edge graph1[] = new Edge[9];
		graph1[0] = new Edge(0, 1, 1);
		graph1[1] = new Edge(0, 2, 7);
		graph1[2] = new Edge(2, 3, 2);
		graph1[3] = new Edge(1, 3, 5);
		graph1[4] = new Edge(1, 4, 6);
		graph1[5] = new Edge(4, 5, 3);
		graph1[6] = new Edge(5, 7, 8);
		graph1[7] = new Edge(7, 6, 4);
		graph1[8] = new Edge(4, 6, 9);
		KruskalAlgorithm kru1 = new KruskalAlgorithm(graph1, numVert);
		kru1.createMinSpanningTree();
		System.out.println(kru1.calcTreeWeight());//
		
	}

}
