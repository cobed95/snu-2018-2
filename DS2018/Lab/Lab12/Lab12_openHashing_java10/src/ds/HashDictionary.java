package ds;

/** Dictionary implemented using hashing. */
public class HashDictionary<Key extends Comparable<? super Key>, E> implements Dictionary<Key, E> {
	private static final int defaultSize = 10;
	private HashTable<Key, E> T; // The hash table
	private int count; // # of records now in table
	private int maxsize; // Maximum size of dictionary

	public HashDictionary() {
		this(defaultSize);
	}

	HashDictionary(int sz) {
		T = new HashTable<Key, E>(sz);
		count = 0;
		maxsize = sz;
	}

	public void clear() { /** Reinitialize */
		T = new HashTable<Key, E>(maxsize);
		count = 0;
	}

	public void insert(Key k, E e) { /** Insert an element */
		T.hashInsert(k, e);
		count++;
	}

	public E remove(Key k) { /** Remove an element */
		E temp = T.hashRemove(k);
		if (temp != null)
			count--;
		return temp;
	}

	/** Find a record with key value "k" */
	public E find(Key k) {
		return T.hashSearch(k);
	}

	/** Return number of values in the hash table */
	public int size() {
		return count;
	}
}
