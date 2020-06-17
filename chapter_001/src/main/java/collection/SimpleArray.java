package collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container = new Object[10];
    private int modCount = 0;
    private int addCount = 0;

    public T get(int index) {
        if (chechIndex(index)) {
            return (T) this.container[index];
        }
        return null;
    }
    public void add(T model) {

        if (addCount >= 10) {
            this.container = Arrays.copyOf(this.container, addCount + 1);
        }
        this.container[addCount++] = model;
        modCount++;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>()  {
            private int point = 0;
            private final Object[] data = container;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                return point < data.length && expectedModCount > 0;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) this.data[point++];
            }
        };
    }
    public boolean chechIndex(int index) {
        if (index >= modCount) {
            throw new IndexOutOfBoundsException();
        }
        return index >=0 && index < modCount;
    }
}
