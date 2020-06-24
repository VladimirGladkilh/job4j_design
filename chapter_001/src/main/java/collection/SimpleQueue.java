package collection;


import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        if (size == 0 ) {
            throw new NoSuchElementException();
        }
        if (out.getSize() == 0) {
            int k = in.getSize();
            for (int i = 0; i < k; i++) {
                T outVal = in.pop();
                out.push(outVal);
            }
        }
        size--;
        return out.pop();
    }

    public void push(T value) {
        size++;
        in.push(value);       
    }
}