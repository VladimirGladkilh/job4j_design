package collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    Object[] container = new Object[]{};
    int modCount = 0;
    int size = 0;

    public SimpleArray() {
        this.container = Arrays.copyOf(this.container, size++);;
    }

    public T get(int index) {
        if (chechIndex(index)) {
            return (T) this.container[index];
        }
        return null;
    }
    public void add(T model) {
        this.container = Arrays.copyOf(this.container, size++);
        this.container[modCount++] = model;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>()  {
            private int point = 0;
            private final Object[] data = container;
            @Override
            public boolean hasNext() {
                return point < this.data.length;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (data.length < container.length) {
                    throw new ConcurrentModificationException();
                }
                return (T) this.data[point++];
            }
        };
    }
    public boolean chechIndex(int index) {
        if (index >= this.container.length) {
            throw new IndexOutOfBoundsException();
        }
        return index >=0 && index < this.container.length;
    }
}
