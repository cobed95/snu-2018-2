package com.sort;

public class InsertionSorter<K extends Comparable<? super K>> {

	/**
	 * Sorts the elements in given array from `left` to `right` in lexicograph order using insertion sort algorithm.
	 */
	
	public void sort(Pair<K, ?>[] array, int left, int right) {
		// Fill your code to sort the elements in `array`.
		for (int i = left + 1; i <= right; i++) {
		    Pair<K, ?> data = array[i];
		    K key = data.getKey();
			int j = i - 1;
			while (j >= 0 && key.compareTo(array[j].getKey()) < 0) {
			    array[j + 1] = array[j];
			    j--;
            }
            array[j + 1] = data;
		}
		
	}
	
	// Hint: Maybe you need to create the helper methods.
	
	
}