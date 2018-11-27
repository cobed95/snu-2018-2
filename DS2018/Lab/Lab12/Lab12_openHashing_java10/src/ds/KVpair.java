package ds;
/** Container class for a key-value pair */
class KVpair<Key, E> {
	private Key k;
	private E e;
	private KVpair<Key, E> next;

	/** Constructors */
	KVpair() {
		k = null;
		e = null;
		next = null;
	}

	KVpair(Key kval, E eval) {
		k = kval;
		e = eval;
		next = null;
	}

	/** Data member access functions */
	public Key key() {
		return k;
	}

	public E value() {
		return e;
	}
	
	public KVpair<Key, E> next() {
		return next;
	}
	
	public void set_next(KVpair<Key, E> next) {
		this.next = next;
	}
}
