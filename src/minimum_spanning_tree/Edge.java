package minimum_spanning_tree;

/**
 * Edge class represent an edge: (u,v) with weight on the edge.
 *
 */
public class Edge implements Comparable<Edge> {
	/* Private data members. */
	private int _u, _v;// edge: (u,v).
	private int _weight;// weight on the edge.

	/* Constructor. */
	public Edge(int u, int v, int weight) {
		_u = u;
		_v = v;
		_weight = weight;
	}

	@Override
	public int compareTo(Edge edge) {
		if(_weight < edge._weight) { return -1;}
		else if(_weight > edge._weight) {return 1;}
		return 0;
	}
	
	/* This method return true iff this == edge */
	public boolean equals(Edge edge) {
		return ( (_u == edge._u) && (_v == edge._v) ) || ( (_u == edge._v) && (_v == edge._u) );
	}

	/* Getters */
	public int get_u() {
		return _u;
	}

	public int get_v() {
		return _v;
	}

	public int get_weight() {
		return _weight;
	}

	@Override
	public String toString() {
		return "Edge [" + _u + ", " + _v + ", _weight=" + _weight + "]";
	}

}
