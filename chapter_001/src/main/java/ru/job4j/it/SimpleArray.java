package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    Object[] objects;
    int len=0;

    class SimpleArrayIterator implements Iterator<T> {
        private int point = 0;
        private Object[] data;

        public SimpleArrayIterator(Object[] data) {
            this.data = data;
        }

        @Override
        public boolean hasNext() {
            return point < data.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) data[point++];
        }
    }
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T model) {

        this.objects[len++] = model;
    }
    public void set(int index, T model) {
        if (checkIndex(index)) {
            this.objects[index] = model;
        }
    }
    public void remove(int index) {
        if (checkIndex(index)) {
            this.objects[index] = null;
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
        return new SimpleArrayIterator(this.objects);
    }

    public boolean checkIndex(int index) {
        return index >= 0 && index < this.objects.length;
    }
}
