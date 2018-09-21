package com.datalab;

public class CombChoose {
	/**
	 * Solves Combination n choose k
	 * 
	 * @param n
	 *            The number of distinguishable objects
	 * @param k
	 *            The number of choices from objects
	 * @return
	 * 			  nCk
	 */
	public static int solve(int n, int k) {
		if (n <= 0 || k < 0)
			throw new AssertionError("Invalid input!");
		// [TODO] Fill your code to solve Combination n choose k
		if (k == 0 || k == n) return 1;
		else return solve(n - 1, k) + solve(n - 1, k - 1);
	}
}
