package dS;

import java.util.Arrays;

public class Sort<E extends Comparable<? super E>>{
	
	private E[] arr;
	private QBS<E> search;
	private int len;
	
	public Sort() {
		arr = newArray(100);
		search = new QBS<E>();
		len = 0;
	}
	
	/** Initialize Array */
	public void clear() {
		// fill your code

	 
	}
 
	public void insert(E e) {
		// fill your code
		

	} 
	
	public int remove(E e) {
		// fill your code
		

	}
	
	public int find(E e) {
		//fill your code
	

	}
	
	public int size() {
		// fill your code
		
	}
	
	public void print() {
		for (int i=0; i<len; i++) {
			System.out.print(arr[i].toString());
			System.out.print(" ");
		}
	}	
	
	static <E> E[] newArray(int length, E... array) {
		return Arrays.copyOf(array, length);
	}
}
