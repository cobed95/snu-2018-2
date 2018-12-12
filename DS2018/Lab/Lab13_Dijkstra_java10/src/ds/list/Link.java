package ds.list;


/**
 * Singly linked list node
 */
public class Link<E> {
    private E element;        // Value for this node
    private Link<E> next;     // Pointer to next node in list

    // Constructors
    public Link(E it, Link<E> nextval) {
        element = it;
        next = nextval;
    }

    public Link(Link<E> nextval) {
        next = nextval;
    }

    public Link<E> next() {
        return next;
    }

    public Link<E> setNext(Link<E> nextval) {
        return next = nextval;
    }

    public E element() {
        return element;
    }

    public E setElement(E it) {
        return element = it;
    }
}
