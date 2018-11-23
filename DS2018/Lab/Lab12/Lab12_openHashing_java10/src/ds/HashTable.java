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
        KVpair<Key, E> pair = new KVpair<>(k, r);
        int h = hash(k);
        if (T[h] != null) {
            KVpair<Key, E> ptr = T[h];
            while (ptr.next() != null) ptr = ptr.next();
            ptr.set_next(pair);
        } else T[h] = pair;
	}

	/** Search for the record with key k */
	public E hashSearch(Key k) {
		// Fill in your codes
        int h = hash(k);
        KVpair<Key, E> pair = T[h];
        while (pair.next() != null && pair.key() != k) pair = pair.next();
        return pair.value();
	}

	/** Remove a record with key value k from the hash table */
	public E hashRemove(Key k) {
		// Fill in your codes
        int h = hash(k);
        KVpair<Key, E> pair = T[h];
        if (pair.key() == k) {
            E value = pair.value();
            if (pair.next() == null) T[h] = null;
            return value;
        } else {
            while (pair.next() != null && pair.next().key() != k) pair = pair.next();
            E value = pair.next().value();
            pair.set_next(pair.next().next());
            return value;
        }
	}

	/**
	 * If the key is an Integer, then use a simple mod function for the hash
	 * function. If the key is a String, then use folding.
	 */
	private int hash(Key k) {
		// Fill in your codes
        if (k instanceof Integer) {
            int key = (Integer) k;
            return key % M;
        } else if (k instanceof String) {
            String key = (String) k;
            return foldString(key);
        }
		return -1;
	}

	private int foldString(String s) {
	    int intLength = s.length() / 4;
	    int sum = 0;
	    for (int j = 0; j < intLength; j++) {
	        char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
	        int mult = 1;
	        for (int k = 0; k < c.length; k++) {
	            sum += c[k] * mult;
	            mult *= 256;
            }
        }
        return Math.abs(sum) % M;
    }
}

