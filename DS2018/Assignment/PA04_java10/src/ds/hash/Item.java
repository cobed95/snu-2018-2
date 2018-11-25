package ds.hash;

/** This is the data structure of single element in hash table. 
 *  It consists of a (String) key and (MaxHeap) maxHeap.
 *  You are free to add new variables and new functions.
 */
public class Item {
	private String key;
	private MaxHeap<Integer> maxHeap;
	
	// fill in your code
	public Item(String key) {
		this.key = key;
		Integer[] array = new Integer[5];
		this.maxHeap = new MaxHeap<Integer>(array, 0, 5);
	}

	public Item(String key, Integer value) {
		this(key);
		this.insert(value);
	}

	public void insert(Integer value) {
		maxHeap.insert(value);
	}

	public String getKey() {
	    return key;
    }

    public Integer popValue() {
	    Integer value = maxHeap.removemax();
	    if (maxHeap.heapsize() == 0) key = "deleted";
	    return value;
    }

    public Integer getValue() {
	    return maxHeap.peek();
    }

    public int getSize() {
	    return maxHeap.heapsize();
    }
}
