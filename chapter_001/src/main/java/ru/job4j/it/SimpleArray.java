package ru.job4j.it;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] objects;
    private int len = 0;
    private int modCount = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T model) {
        this.objects[len++] = model;
        modCount++;
    }
    public void set(int index, T model) {
        if (checkIndex(index)) {
            this.objects[index] = model;
            modCount++;
        }
    }
    public void remove(int index) {
        if (checkIndex(index)) {
            System.arraycopy(this.objects, index + 1, this.objects, index, this.objects.length - index - 1);
            len--;
            modCount++;
        }
    }
    public T get(int index) {
        if (checkIndex(index)) {
            return (T) this.objects[index];
        } else {
            return null;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int point = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < len;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) objects[point++];
            }
        };
    }

    public boolean checkIndex(int index) {
        return index >= 0 && index < this.len;
    }
}
