public class SortedList<E> implements List<E> {
    private Link<E> head;
    private Link<E> tail;
    protected Link<E> curr;
    private int count;
    private int maxCapacity;

    public SortedList() {
        this.curr = this.tail = this.head = new Link<E>(null);
        this.count = 0;
    }

    public void clear() {
        this.moveToStart();
        while (this.curr != tail) {
            this.remove();
        }
        this.head.setNext(null);
        this.curr = this.tail = this.head = new Link<E>(null);
    }

    public void insert(E element) {
        assert (count < maxCapacity) : "List capacity exceeded.";
        this.curr.setNext(Link.get(element, this.curr.next()));
        if (this.tail == this.curr) this.tail = this.curr.next();
        this.count++;
    }

    public void append(E element) {
        assert (count < maxCapacity) : "List capacity exceeded.";
        this.tail.setNext(Link.get(element, null));
        this.tail = this.tail.next();
    }

    public E remove() {
        if (curr.next() == null) return null;
        E removed = curr.next().element();
        if (this.tail == this.curr.next()) this.tail = this.curr;
        Link<E> temp = this.curr.next();
        this.curr.setNext(this.curr.next().next());
        temp.release();
        this.count--;
        return removed;
    }
}
