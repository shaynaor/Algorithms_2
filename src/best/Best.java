package best;

public class Best {
	/**
	 * This function finds a segment with the maximum sum value.
	 * 
	 * @param a- given array.
	 * @return - the sum of the segment with the maximum sum. Complexity: O(n).
	 */
	public static int best(int a[]) {
		int n = a.length, i = 0;
		/* Begin to sum from the first positive element in the array. */
		while (i < n && a[i] <= 0) {
			i++;
		}
		/* If all the values negative - find and return max value in the array. */
		if (i == n) {
			int index = findMax(a);
			return a[index];
		}

		int max = a[0];
		int sum = 0;
		while (i < n) {
			sum += a[i];
			if (sum <= 0)
				sum = 0;
			else if (sum > max)
				max = sum;
			i++;
		}
		return max;
	}

	/**
	 * Same like best algorithm above but this algorithm returns array that contains
	 * the first index, last index, number of elements and the sum of segment with
	 * the maximum sum value.
	 * 
	 * @param a - given array.
	 * @return - write above.
	 */
	public static int[] bestPlus(int a[]) {
		int n = a.length;
		int i = 0;
		/* Begin to sum from the first positive element in the array. */
		while (i < n && a[i] <= 0) {
			i++;
		}
		/* If all the values negative. */
		if (i == n) {
			int index = findMax(a);
			int ans[] = { index, index + 1, 1, a[index] };
			return ans;
		}
		int sum = 0, maxSum = a[i], startIndex = i, endIndex = i + 1, count = 0, countMax = 1;
		while (i < n) {
			sum += a[i];
			count++;
			if (sum < 0) {
				sum = 0;
				count = 0;
			} else if (sum > maxSum) {
				maxSum = sum;
				endIndex = i + 1;
				countMax = count;
			}
			i++;
		}
		startIndex = endIndex - countMax;
		int[] ans = { startIndex, endIndex, countMax, maxSum };
		return ans;
	}

	/**
	 * This function represent naive solution to find index of max in array. number
	 * of comparisons: n-1. complexity: O(n).
	 * 
	 * @param a array.
	 * @return the maximum value in the array
	 */
	public static int findMax(int[] a) {
		int max = a[0];
		int index = 0;
		for (int i = 1; i < a.length; i++) {
			if (a[i] > max) {
				max = a[i];
				index = i;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		int a[] = { 3, -2, 5, 1 };
		System.out.println(best(a));

		int a1[] = { -3, -2, -5, -1 };
		System.out.println(best(a1));

		int a2[] = { 1, 7, 3, -13, 2, 1, 10, -2, 1, -20 };
		System.out.println(best(a2));

	}

}
