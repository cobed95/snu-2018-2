package ds;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HashTable<Key extends Comparable<? super Key>, E> {
	private int M; // Size of the table
	private KVpair<Key, E>[] T; // Array of the elements

	@SuppressWarnings("unchecked") // Allow the generic array allocation
	public HashTable(int m) {
		// Fill in your codes
        M = m;
        T = newArray(m);
	}

	private <E> E[] newArray(int length, E... array) {
	    return Arrays.copyOf(array, length);
    }

	/** Insert record r into T */
	public void hashInsert(Key k, E r) {
		// Fill in your codes
	}

	/** Search for the record with key k */
	public E hashSearch(Key k) {
		// Fill in your codes
		return null;
	}

	/** Remove a record with key value k from the hash table */
	public E hashRemove(Key k) {:while ()
		// Fill in your codes
		return null;
	}

	/**
	 * If the key is an Integer, then use a simple mod function for the hash
	 * function. If the key is a String, then use folding.
	 */
	private int hash(Key k) {
		// Fill in your codes

		return 0;
	}
}

