public interface List<E> {
    public void clear();
    public void insert(E item);
    public void append();
    public E remove();
    public void moveToStart();
    public void moveToEnd();
    public void prev();
    public void next();
    public int length();
    public int currPos();
    public void moveToPos(int pos);
    public E getValue();
}
