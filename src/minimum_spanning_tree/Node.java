package minimum_spanning_tree;

public class Node {
	/* Private data members. */
	private int _index; // the vertex number.
	private int _key;

	/* Constructor. */
	public Node(int index, int key) {
		_index = index;
		_key = key;
	}

	public Node() {
		_key = 0;
		_index = -1;
	}

	/* Copy constructor. */
	public Node(Node other) {
		this(other._index, other._key);
	}

	/* Getters */
	public int get_index() {
		return _index;
	}

	public int get_key() {
		return _key;
	}

	public void set_key(int _key) {
		this._key = _key;
	}

}
