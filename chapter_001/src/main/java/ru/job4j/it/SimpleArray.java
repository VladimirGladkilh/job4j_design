package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int len=0;


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
        Iterator<T> iterator = new Iterator<>() {
            private int point = 0;
            private Object[] data = objects;

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
        };
        return iterator;
    }

    public boolean checkIndex(int index) {
        return index >= 0 && index < len;
    }
}
