package com.sort;

public class QuickSorter<K extends Comparable<? super K>>{

	public void sort(Pair<K, ?>[] array, int left, int right) {
		// Fill your code to sort the elements in `array`.
        if (right - (left - 1) > 32) {
            int pivotIdx = findPivot(array, left, right);
            swap(array, pivotIdx, right);
            int k = partition(array, left - 1, right, array[right]);
            swap(array, k, right);
            if (k - left > 1) sort(array, left, k - 1);
            if (right - k > 1) sort(array, k + 1, right);
        }
	}

	public int findPivot(Pair<K, ?>[] array, int left, int right) {
	    return (left + right) / 2;
    }

    public int partition(Pair<K, ?>[] array, int left, int right, Pair<K, ?> pivot) {
	    do {
            while (array[++left].getKey().compareTo(pivot.getKey()) < 0) ;
            while (right != 0 && array[--right].getKey().compareTo(pivot.getKey()) > 0) ;
            swap(array, left, right);
        } while (left < right);
	    swap(array, left, right);
	    return left;
    }

    public <E> void swap(E[] array, int i, int j) {
	    E temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
    }
	
	// Hint: Maybe you need to create the helper methods.
	
	
}
