package best;

import java.util.Arrays;

public class Best_cycle {
	/**
	 * This function finds a segment with the maximum sum value in a circle array.
	 * Algorithm used: Max{ best(a) , sum - (-best(-a)) }
	 * 
	 * @param a- given circle array.
	 * @return - the sum of the segment with the maximum sum. Complexity: O(n).
	 */
	public static int best_cycle(int a[]) {
		int n = a.length, sum = 0;
		/* Calculate the sum of the values in the array. */
		for (int i = 0; i < n; i++) {
			sum += a[i];
		}
		int sum1 = Best.best(a);
		int aNeg[] = new int[n];

		/* aNeg = -a */
		for (int i = 0; i < n; i++) {
			aNeg[i] = (-1) * a[i];
		}
		int sumNeg = Best.best(aNeg);
		/* sum2 = sum - (-sumNeg) */
		int sum2 = sum + sumNeg;
		return Math.max(sum1, sum2);
	}
	
	/**
	 * Same like best_cycle algorithm above but this algorithm returns array that contains
	 * the first index, last index, number of elements and the sum of segment with
	 * the maximum sum value.
	 * 
	 * @param a - given cycle array.
	 * @return - write above.
	 */
	public static int[] best_cyclePlus(int a[]) {
		int n = a.length, sum = 0;
		/* Calculate the sum of the values in the array. */
		for (int i = 0; i < n; i++) {
			sum += a[i];
		}
		int arrBest[] = Best.bestPlus(a);
		int sum1 = arrBest[3];
		int aNeg[] = new int[n];

		/* aNeg = -a */
		for (int i = 0; i < n; i++) {
			aNeg[i] = (-1) * a[i];
		}
		int arrBestNeg[] = Best.bestPlus(aNeg);
		int sumNeg = arrBestNeg[3];
		/* sum2 = sum - (-sumNeg) */
		int sum2 = sum + sumNeg;
		/* If the first(no cycle) best bigger */
		if (sum1 > sum2)
			return arrBest;
		/* Else return the array of the cycle best. */
		int ansCycle[] = {arrBestNeg[1], arrBestNeg[0], n-arrBestNeg[2] , sum+ arrBestNeg[3]};
		return ansCycle;
			
			
	}

	public static void main(String[] args) {
		int a[] = { 1, 2, -100, 5, 1, 2, -7 };
		int a1[] = { 1, 2, -1, 5 };
		System.out.println(best_cycle(a));
		System.out.println(best_cycle(a1));
		int ans[] = best_cyclePlus(a);
		System.out.println(ans[3]);
		
		int ans0[] = best_cyclePlus(a);
		System.out.println(Arrays.toString(ans0));
		
		int ans1[] = best_cyclePlus(a1);
		System.out.println(Arrays.toString(ans1));
	}

}
