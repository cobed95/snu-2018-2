
public interface Queue<E> {
	
	public void enqueue(E item);
	public E dequeue();
	public E pop();
	public void clear();
	public int size();
	
}
