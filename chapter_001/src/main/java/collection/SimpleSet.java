package collection;

import java.util.*;

public class SimpleSet<E> implements Iterable<E> {
    private SimpleArray<E> container = new SimpleArray<E>();
    private int modCount = 0;
    private int addCount = 0;

    public E get(int index) {
        if (checkIndex(index)) {
            return this.container.get(index);
        }
        return null;
    }

    public int getSize() {
        return addCount;
    }

    private boolean find(E model) {
        for (E mod:this.container) {
            if (mod.equals(model)) {
                return true;
            }
        }
        return false;
    }
    public void add(E model) {
        if (!find(model)) {
            this.container.add(model);
            addCount++;
            modCount++;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>()  {
            private int point = 0;
            private final SimpleArray<E> data = container;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                return point < addCount ;
            }
            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return (E) this.data.get(point++);
            }
        };
    }
    public boolean checkIndex(int index) {
        if (index >= addCount || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }
}
