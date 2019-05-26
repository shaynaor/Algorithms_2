package huffmanCode;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Huffman Code Algorithm.
 * Complexity: O(n). n- the number of letters.
 * the array of frequencies is sorted.
 *
 */
public class HuffmanCode {
	
	/* Inner Node class.*/
	class Node{
		
		/* Private data members. */
		private int _freq; //frequency.
		private int _pos;
		
		/* Constructor. */
		public Node(int freq, int pos) {
			_freq = freq;
			_pos = pos;
		}
		
	}/* End Node inner class. */
	
	/* Private data members. */
	private int _mat[][];//freq - left - right - parent.
	private char _letters[]; // array of given letters.
	private int _numOfLetters; //number of letters.
	private int _size; // size of matrix 2*numOfLetters-1.
	private ArrayBlockingQueue<Node> _q1;//queue1 - the smallest element in front of the queue.
	private ArrayBlockingQueue<Node> _q2;//queue2 - the smallest element in front of the queue.
	private String _code[];//Array of Huffman codes.
	
	/* Constructor. */
	public HuffmanCode(int freq[], char letters[]) {
		_numOfLetters = letters.length;
		_size = 2 * _numOfLetters - 1;
		_letters = new char [_numOfLetters];
		_code = new String[_numOfLetters];
		_mat = new int [_size][4];
		_q1 = new ArrayBlockingQueue<Node>(_size);
		_q2 = new ArrayBlockingQueue<Node>(_size);
		for(int i = 0 ; i < _numOfLetters ; i++) {
			_mat[i][0] = freq[i];
			_letters[i] = letters[i];
			_code[i] = "";
			_q1.add(new Node(freq[i], i));
		}
	}
	
	public void buildTable() {
		Node m1 = _q1.remove();//m1 - the first min element.
		Node m2 = _q1.remove();//m2 - the second min element.
		int parent = _numOfLetters; // parent of m1 and m2.
		int parentFreq = m1._freq + m2._freq; // freq parent is the sum of children's freq.
		_q2.add(new Node(parentFreq, parent)); // add parent to q2.
		_mat[parent][0] = parentFreq; // update the parent freq.
		_mat[parent][1] = m1._pos;//set m1 as left child of parent.
		_mat[parent][2] = m2._pos;//set m2 as right child of parent.
		_mat[m1._pos][3] = parent; //set parent to parent of m1.
		_mat[m2._pos][3] = parent; //set parent to parent of m2.
		++parent;
		while(_q1.size() + _q2.size() > 1) {
			m1 = getMin();//m1 - the min element.
			m2 = getMin();//m2 - the min element.
			parentFreq = m1._freq + m2._freq; // freq parent is the sum of children's freq.
			_q2.add(new Node(parentFreq, parent)); // add parent to q2.
			_mat[parent][0] = parentFreq; // update the parent freq.
			_mat[parent][1] = m1._pos;//set m1 as left child of parent.
			_mat[parent][2] = m2._pos;//set m2 as right child of parent.
			_mat[m1._pos][3] = parent; //set parent to parent of m1.
			_mat[m2._pos][3] = parent; //set parent to parent of m2.
			++parent;
		}
	}
	
	
	
	
	/**
	 * 
	 * @return - The minimum freq in queue1 and queue2.
	 */
	private Node getMin() {
		Node m1,m2;
		if(_q1.isEmpty()) {
			m1 = _q2.remove();
		}
		else if(_q2.isEmpty()) {
			m1 = _q1.remove();
		}
		else {
			m1 = _q1.peek();
			m2 = _q2.peek();
			if(m1._freq > m2._freq) {
				m1 = _q2.remove(); 
			}
			else {
				m1 = _q1.remove();
			}
		}
		return m1;
	}
	
	/**
	 * Build Huffman code from the table.
	 * Complexity: O(n).
	 */
	public void buildHuffmanCode() {
		int child , parent;
		for(int i = 0 ; i < _numOfLetters ; i++) {
			child = i;
			parent = _mat[child][3];//the parent of the child.
			while(parent != 0) {//while parent exist.
				if(_mat[parent][1] == child) {//if left child - add "0"
					_code[i] = "0" + _code[i];
				}else { ////if right child - add "1"
					_code[i] =  "1" + _code[i];
				}
				child = parent;
				parent = _mat[child][3];
			}
		}
	}
	
	/**
	 * Prints Huffman code.
	 */
	public void printHuffmanCode() {
		for(int i = 0 ; i < _numOfLetters ; i++) {
			System.out.println("The letter: " + _letters[i] + " has the Huffman code: " + _code[i]);
		}
	}

	public static void main(String[] args) {
		int freq[] = {5,9,12,13,16,45};
		char letter[] = {'f','e','c','b','d','a'};
		HuffmanCode hc = new HuffmanCode(freq, letter);
		/* 
		 * need to be:
		 * a = 0
		 * b = 101
		 * c = 100
		 * d = 111
		 * e = 1101 
		 * f = 1100
		 */
		hc.buildTable();
		hc.buildHuffmanCode();
		hc.printHuffmanCode();
		
	}

}
