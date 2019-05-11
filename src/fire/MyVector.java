package fire;

import java.util.Arrays;

/**
 * Simple implementation to vector
 * 
 * @author shaynaor
 *
 */
public class MyVector {
	/* Private data members. */
	private int[] _data;
	private int _size;

	public MyVector(int size) {
		_data = new int[size];
		_size = 0;
	}

	/**
	 * Adding method to the vector
	 * 
	 * @param value - value to be added.
	 */
	public void add(int value) {
		_data[_size++] = value;
	}

	/**
	 * 
	 * @param index -
	 * @return
	 */
	public int get(int index) {
		if (inRange(index)) {
			return _data[index];
		}
		throw new RuntimeException("ERROR- Out of range!!!");
	}

	/**
	 * 
	 * @param index
	 * @param val
	 */
	public void set(int index, int val) {
		if (inRange(index)) {
			_data[index] = val;
		}
	}

	/**
	 * 
	 * @return - the vector size.
	 */
	public int size() {
		return _size;
	}

	public void remove(int x) {
	}
	
	/**
	 * 
	 * @param index 
	 * @return - true iff - index in range.
	 */
	private boolean inRange(int index) {
		return (_size > 0) && (index >= 0) && (index < _size);
	}

	public String toString() {
		return Arrays.toString(_data);
	}
}
