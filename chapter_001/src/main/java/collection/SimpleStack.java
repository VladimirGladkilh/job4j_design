package collection;

public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();
    private int size = 0;

    public int getSize() {
        return size;
    }

    public T pop() {
        size--;
        return linked.deleteLast();
    }
    public T pull() {
        size--;
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.add(value);
        size++;
    }
}