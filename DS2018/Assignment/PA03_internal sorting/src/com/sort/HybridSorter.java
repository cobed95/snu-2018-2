package com.sort;


public class HybridSorter<K extends Comparable<? super K>> {
	InsertionSorter<K> insertionSort = new InsertionSorter<K>();
	QuickSorter<K> quickSort = new QuickSorter<K>();
	private final int RUN = 32;
	/**
	 * Sorts the elements in given array from `left` to `right in lexicographic order
	 * using the hybrid sorting algorithm.
	 */

	public Pair<K, ?> search(Pair<K,?>[] array, int k) {
		
		sort(array, 0, array.length-1);
		return array[k];
	}
	
	public void sort(Pair<K, ?>[] array, int left, int right) {
		
	
		// Fill your code to sort the elements in `array`.
		
	}
	
	public int min(int a, int b) {
		int res = 0;
		
		if (a > b ) res = b;
		else res = a;
		
		return res;
	}
	

}
