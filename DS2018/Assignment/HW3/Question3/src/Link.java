public class Link<E> {
    private E element;
    private Link<E> next;
    private static Link freelist = null;

    public Link(E element, Link<E> next) {
        this.element = element;
        this.next = next;
    }

    public Link(Link<E> next) {
        this.next = next;
    }

    public Link<E> next() {
        return this.next;
    }

    public Link<E> setNext(Link<E> next) {
        this.next = next;
        return this.next;
    }

    public E element() {
        return this.element;
    }

    public E setElement(E element) {
        this.element = element;
        return this.element;
    }

    public static <E> Link<E> get(E element, Link<E> next) {
        if (freelist == null) return new Link<E>(element, next);
        Link<E> temp = freelist;
        freelist = freelist.next();
        temp.setElement(element);
        temp.setNext(next);
        return temp;
    }

    public void release() {
        this.element = null;
        this.next = freelist;
        freelist = this;
    }
}
