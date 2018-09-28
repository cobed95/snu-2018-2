
public class MyQueue<E> implements Queue<E> {

	private Node<E> first = null;
	private Node<E> last = null;
	private int size = 0;

	@Override
	public void enqueue(E item) {
		// TODO Auto-generated method stub
        if (this.isEmpty()) {
            this.last = new Node(item, null);
            this.first = this.last;
        } else {
            this.last.setNext(new Node(item, null));
            this.last = this.last.getNext();
        }
        this.size++;
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
        E dequeued = this.first.getItem();
        this.first = this.first.getNext();
        this.size--;
		return dequeued;
	}
	
	@Override
	public E pop() {
		// TODO Auto-generated method stub
        E popped = this.last.getItem();
        if (this.size == 1) {
            this.first = null;
            this.last = null;
        } else {
            Node<E> cursor = this.first;
            for (int i = 0; i < this.size - 2; i++) {
                cursor = cursor.getNext();
            }
            this.last = cursor;
            this.last.setNext(null);
        }
        this.size--;
		return popped;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public String toString() {
		Node<E> cursor = first;
		StringBuffer sb = new StringBuffer("(");
		while (cursor != null) {
			sb.append(cursor.getItem());
			if (cursor != last) {
				sb.append(' ');
			}
			cursor = cursor.getNext();
		}
		sb.append(")");
		return sb.toString();
	}

}
