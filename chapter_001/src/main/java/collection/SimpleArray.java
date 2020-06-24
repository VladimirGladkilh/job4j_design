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
        if (addCount >= this.container.length) {
            this.container = Arrays.copyOf(this.container, addCount * 2);
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
                return point < addCount ;
            }
            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return (T) this.data[point++];
            }
        };
    }
    public boolean chechIndex(int index) {
        if (index >= addCount || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return index >= 0 && index < addCount;
    }
}
