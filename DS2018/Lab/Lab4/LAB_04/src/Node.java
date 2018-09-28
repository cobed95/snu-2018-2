
public class Node<E> {

	private E item = null;
	private Node<E> next = null;

	public Node(E newItem, Node<E> next) {
		this.item = newItem;
		this.next = next;
	}

	public Node<E> getNext() {
		return this.next;
	}

	public E getItem() {
		return this.item;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public void setItem(E item) {
		this.item = item;
	}
}
