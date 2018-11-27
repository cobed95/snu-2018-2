package ds.hash;


import java.lang.Comparable;

/** Max-heap implementation */
public class MaxHeap<E extends Comparable<? super E>> {
	private E[] Heap;   // Pointer to the heap array
	private int size;   // Maximum size of the heap
	private int n;      // Number of things in heap

	/** Constructor supporting preloading of heap contents */
	public MaxHeap(E[] h, int num, int max){ 
		Heap = h;  
		n = num;  
		size = max;  
		buildheap(); 
	}

	/** @return Current size of the heap */
	public int heapsize() { 
		return n; 
	}
	
	/** Re-define the heapsize. Used for reconstructing heap after sorting. */
	public void setSize(int heapsize) {
		n = heapsize;
	}
	
	/** @return True if pos a leaf position, false otherwise */
	public boolean isLeaf(int pos){ 
		return (pos >= n/2) && (pos < n); 
	}

	/** @return Position for left child of pos */
	public int leftchild(int pos) {
		assert pos < n/2 : "Position has no left child";
		return 2*pos + 1;
	}

	/** @return Position for right child of pos */
	public int rightchild(int pos) {
		assert pos < (n-1)/2 : "Position has no right child";
		return 2*pos + 2;
	}

	/** @return Position for parent */
	public int parent(int pos) {
		assert pos > 0 : "Position has no parent";
		return (pos-1)/2;
	}

	private void swap(E[] A, int p1, int p2) {
		E temp = A[p1];
		A[p1] = A[p2];
		A[p2] = temp;
	}
	
	/** Heapify contents of Heap */
	public void buildheap(){ 
		for (int i=n/2-1; i>=0; i--) 
			siftdown(i); 
	}
	
	/** Insert val into heap */
	public void insert(E val) {
		assert n < size: "Heap is full.";
		int pos = n++;
		Heap[pos] = val;
		while (pos != 0 && Heap[pos].compareTo(Heap[parent(pos)]) > 0) {
		    swap(pos, parent(pos));
		    pos = parent(pos);
        }
	}

	/** Put element in its correct place */
	private void siftdown(int pos) {
		assert pos < n: "Illegal heap position.";
		int largest = pos;
		if (pos < n / 2 && Heap[leftchild(pos)].compareTo(Heap[pos]) > 0)
		    largest = leftchild(pos);
		if (pos < (n - 1) / 2 && Heap[rightchild(pos)].compareTo(Heap[largest]) > 0)
		    largest = rightchild(pos);
		if (largest != pos) {
		    swap(pos, largest);
		    siftdown(largest);
        }
	}

	private void swap(int i, int j) {
	    E temp = Heap[i];
	    Heap[i] = Heap[j];
	    Heap[j] = temp;
    }

	/** Remove and return maximum value */
	public E removemax() {
        E max = Heap[0];
        swap(0, n - 1);
        n--;
        siftdown(0);
		return max;
	}
	
	/** Remove and return element at specified position */
	public E remove(int pos) {
        assert pos < n && pos >= 0: "Illegal heap position.";
        if (pos == n - 1) {
            E result = Heap[pos];
            n--;
            return result;
        } else if (pos >= n / 2) {
            E result = Heap[pos];
            swap(pos, n - 1);
            n--;
            while (pos >= 0 && Heap[pos].compareTo(Heap[parent(pos)]) > 0) {
                swap (pos, parent(pos));
                pos = parent(pos);
            }
            return result;
        } else {
            int larger = leftchild(pos);
            if (pos < (n - 1) / 2 && Heap[rightchild(pos)].compareTo(Heap[larger]) > 0)
                larger = rightchild(pos);
            swap(pos, larger);
            return remove(larger);
        }
	}
	
	/**  and return a position of element */
	public int find(E value){
		for (int i = 0; i < n; i++)
		    if (Heap[i] == value) return i;
		return -1;
	}

	/** print the Heap */
	public void printHeap() {
	    if (n >= 0) {
	        System.out.print(Heap[0]);
	        for (int i = 1; i < n; i++)
	            System.out.print(" " + Heap[i]);
        }
	}

	public E peek() {
	    return Heap[0];
    }
}
