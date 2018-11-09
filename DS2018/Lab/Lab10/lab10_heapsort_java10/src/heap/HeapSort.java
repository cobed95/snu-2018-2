package heap;

import java.util.Arrays;

public class HeapSort<E extends Comparable<? super E>> {
	private MaxHeap<E> maxHeap;
	private int n;
	private E[] array;

	private static final int SORT_A = 2;
	private static final int SORT_D = 3;

	public HeapSort(int n){
		array = newArray(n);
		maxHeap = new MaxHeap<E>(array, 0, n);
		this.n = n;
	}
	
	public void add(E value){
		maxHeap.insert(value);
		maxHeap.printHeap();
		// fill your code
		
	}
	
	public void remove(E value){
		int pos = maxHeap.find(value);
		maxHeap.remove(pos);
		maxHeap.printHeap();
		// fill your code
		
	}
	
	public void sort(int order){
        int heapSize = maxHeap.heapsize();
	    if (order == SORT_A) {
	        String result = "";
	        if (heapSize >= 0) {
                result += maxHeap.removemax();
                for (int i = 1; i < heapSize; i++)
                    result = maxHeap.removemax() + " " + result;
                System.out.println(result);
                maxHeap.setSize(heapSize);
                maxHeap.buildheap();
                System.out.print("        ");
                maxHeap.printHeap();

            }
        } else if (order == SORT_D) {
            String result = "";
            if (heapSize >= 0) {
                System.out.print(maxHeap.removemax());
                for (int i = 1; i < heapSize; i++)
                    System.out.print(" " + maxHeap.removemax());
                maxHeap.setSize(heapSize);
                maxHeap.buildheap();
                System.out.println();
                System.out.print("        ");
                maxHeap.printHeap();
            }
        }
		// fill your code
		
	}
	
	//This function is for allocating an generic array of size n
	private static <E> E[] newArray(int length, E... array)
	{
	    return Arrays.copyOf(array, length);
	}
}
