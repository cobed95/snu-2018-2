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
		arr = newArray(100);
		len = 0;
	}
 
	public void insert(E e) {
		// fill your code
        if (len == 0) {
            arr[0] = e;
            len++;
        } else if (e.compareTo(arr[0]) < 0) {
            for (int i = len; i >= 0; i--)
                arr[i + 1] = arr[i];
            arr[0] = e;
            len++;
        } else if (e.compareTo(arr[len - 1]) > 0) {
            arr[len] = e;
            len++;
        } else {
            int idx = search.qbs(arr, e, len);
            if (e.compareTo(arr[idx]) != 0 && len <= arr.length) {
                for (int i = len; i >= idx; i--)
                    arr[i + 1] = arr[i];
                arr[idx] = e;
                len++;
            }
        }
	}
	
	public int remove(E e) {
		// fill your code
		int idx = search.qbs(arr, e, len);
		if (e.compareTo(arr[idx]) == 0) {
		    for (int i = idx; i < len - 1; i++)
		        arr[i] = arr[i + 1];
		    len--;
		    return idx;
        } else return -1;
	}
	
	public int find(E e) {
		//fill your code
		int idx = search.qbs(arr, e, len);
		if (e.compareTo(arr[idx]) != 0)
		    return -1;
		else return idx;
	}
	
	public int size() {
		// fill your code
		return len;
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
