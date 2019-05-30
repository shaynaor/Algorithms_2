package minimum_spanning_tree;

public class MinHeap {
	/* Private data members. */
	private int _infinity = Integer.MAX_VALUE;
	static final int INIT_SIZE = 10;
	private Node _a[];
	private int _size;

	/* Constructor. */
	public MinHeap(Node arr[]) {
		_size = arr.length;
		_a = new Node[_size];
		for (int i = 0; i < _size; i++) {
			_a[i] = arr[i];
		}
	}

	/** Default constructor. */
	public MinHeap() {
		_a = new Node[0];
	}

	/* ------Getters.------ */
	/** returns the heap size */
	public int getSize() {
		return _size;
	}

	/** returns the heap array */
	public Node[] getA() {
		return _a;
	}

	/** parent returns the parent of vertex i */
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/** leftChild returns the left child of vertex i */
	private int leftChild(int i) {
		return 2 * i + 1;
	}

	/** rightChild returns the right child of vertex i */
	private int rightChild(int i) {
		return 2 * i + 2;
	}

	/** returns the heap minimum */
	public Node heapMinimum() {
		return _a[0];
	}

	/** returns true if the heap is empty, otherwise false */
	public boolean isEmpty() {
		return _size == 0;
	}

	/** the minHeapfy function maintains the min-heap property */
	private void minHeapify(int v, int heapSize) {
		int smallest;
		int left = leftChild(v);
		int right = rightChild(v);
		if (left < heapSize && _a[left].get_key() < _a[v].get_key()) {
			smallest = left;
		} else {
			smallest = v;
		}
		if (right < heapSize && _a[right].get_key() < _a[smallest].get_key()) {
			smallest = right;
		}
		if (smallest != v) {
			swap(v, smallest);
			minHeapify(smallest, heapSize);
		}
	}

	/** the heap minimum element extraction */
	public Node heapExtractMin() {
		int min = _infinity;
		Node node = null;
		if (!isEmpty()) {
			node = _a[0];
			min = node.get_key();
			_a[0] = _a[_size - 1];
			_size--;
			minHeapify(0, _size);
		}
		return node;
	}

	public Node heapGetMin() {
		return _a[0];
	}

	/** the heapDecreaseKey implements the Decrease Key operation */
	public void heapDecreaseKey(Node node) {
		int v = node.get_index();
		int i = 0;
		while (i < _size && v != _a[i].get_index())
			i++;
		if (node.get_key() < _a[i].get_key()) {
			_a[i] = node;
			while (i > 0 && _a[parent(i)].get_key() > _a[i].get_key()) {
				swap(i, parent(i));
				i = parent(i);
			}
		}
	}

	/** minHeapInsert function implements the Insert-Key operation */
	public void minHeapInsert(Node node) {// O(log2(n))
		resize(1);
		_a[_size - 1] = new Node(node);
		_a[_size - 1].set_key(_infinity);
		heapDecreaseKey(node);
	}

	/** increment an array */
	private void resize(int increment) {
		Node temp[] = new Node[_size + increment];
		for (int i = 0; i < _size; i++) {
			temp[i] = _a[i];
		}
		_a = temp;
		_size = _size + increment;
	}

	/** exchange two array elements */
	private void swap(int i, int j) {
		Node temp = _a[i];
		_a[i] = _a[j];
		_a[j] = temp;
	}
}
