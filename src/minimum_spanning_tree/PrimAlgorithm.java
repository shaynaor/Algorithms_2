package minimum_spanning_tree;

import java.util.ArrayList;

public class PrimAlgorithm {
	/* Private data members. */
	private MinHeap _heap; //Min Node heap.
	private ArrayList<Node>[] _graph; // graph.
	private int _numOfVertex, _numOfEdges, _root;
	private int _key[], _parents[]; //key = array of weights.
	private boolean _visited[]; //for all visited[i] = true iff i not visited yet.
	static final int infinity = Integer.MAX_VALUE, NIL = -1;
	private Edge _tree[];
	
	/* Constructor. */
	public PrimAlgorithm(ArrayList<Node>[] graph , int root) {
		_graph = graph;
		_numOfVertex = graph.length;
		_root = root;
		_key = new int[_numOfVertex];
		_parents = new int[_numOfVertex];
		_visited =  new boolean[_numOfVertex];
		_tree = new Edge [_numOfVertex - 1];
		_numOfEdges = 0;
		for(int i = 0 ; i < _numOfVertex ; i++) {
			_key[i] = infinity;
			_parents[i] = NIL;
			_visited[i] = false;
		}
		_key[_root] = 0;
		_heap = new MinHeap();
		_heap.minHeapInsert(new Node(_root,0));
		for(int i = 1 ; i < _numOfVertex ; i++) {
			_heap.minHeapInsert(new Node(i, infinity));
		}
	}
	
	public void MST_Prim() {
		while(!_heap.isEmpty() && _numOfEdges < _numOfVertex -1) {
			int u = _heap.heapExtractMin().get_index();
			for(int i = 0 ; i < _graph[u].size() ; i++) {
				Node n = _graph[u].get(i);
				int v = n.get_index();
				int weight = n.get_key();
				if(!_visited[v] && weight < _key[v]) {
					_parents[v] = u;
					_key[v] = weight;
					_heap.heapDecreaseKey(n);
				}
			}
			_visited[u] =true;
			Node min = _heap.heapGetMin();
			_tree[_numOfEdges++] = new Edge(_parents[min.get_index()], min.get_index(), min.get_key());
		}
	}
	
	
}
