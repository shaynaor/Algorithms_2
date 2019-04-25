package best;

public class GazStationProblem {
	/**
	 * 
	 * @param a - a[i] = amount of gas in station i.
	 * @param b - b[i] = amount of needed gas to moving from station i to i+1
	 * @return true iff we can complete a circle.
	 */
	public static boolean gasStationProblem(int a[], int b[]) {
		int n = a.length;
		boolean ans = true;
		/* c[i] = a[i] - b[i] */
		int c[] = new int[n];
		int sum = 0;
		for (int i = 0; i < n; i++) {
			c[i] = a[i] - b[i];
			sum += c[i];
		}
		/* If sigma(b) > sigma(a)  - cannot close a circle. */
		if (sum < 0) {
			System.out.println("Not enough gas!!");
			ans = false;
		} else {
			int res[] = Best_cycle.best_cyclePlus(c);
			int startIndex = res[0];
			System.out.println("Start index: " + startIndex);
			int cSum = c[startIndex];
			int index = (startIndex + 1) % n;
			int k = index;
			boolean notCloseCircle = true;
			for (int i = 0; ans && notCloseCircle ; i++) {
				index = (k + i) % n;
				System.out.println(index);
				cSum += c[index];
				if (cSum < 0) {
					System.err.print("Error!!!");
					ans = false;
				}
				if(i != 0 && index == startIndex+1) 
					notCloseCircle = false;
				
			}
			System.out.println("The amount of gas after: " + cSum   );
		}
		return ans;
	}

	public static void main(String[] args) {
		int a[] = { 3, 6, 2, 8 };
		int b[] = { 5, 4, 3, 4 };
		System.out.println(gasStationProblem(a, b));

	}

}
