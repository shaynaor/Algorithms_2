package best;

public class MaxDiffProblem {
	
	public static int maxDiff(int a[]) {
		int n  = a.length;
		int t[] = new int [n-1];
		for(int i = 1 ; i < n ; i++) {
			t[i-1] = a[i] - a[i-1];
		}
		int bestSum = Best.best(t);
		return bestSum;
	}
	public static void main(String [] args) {
		int a[] = { 2,3,1,7,9,5};
		System.out.println(maxDiff(a));
	}
}
