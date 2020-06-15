package collection;

import java.util.*;
import java.util.function.Consumer;

public class DynamicStore<E> implements Iterable<E> {
    int size = 0;
    int modCount = 0;
    Node<E> first;
    Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }


    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    public E get(int index) {
        if (checkElement(index)) {
            if (index < (size >> 1)) {
                Node<E> x = first;
                for (int i = 0; i < index; i++)
                    x = x.next;
                return x.item;
            } else {
                Node<E> x = last;
                for (int i = size - 1; i > index; i--)
                    x = x.prev;
                return x.item;
            }
        }
        return null;
    }
    public boolean checkElement(int index) {
        return index >= 0 && index <= size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int nextIndex=0;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(nextIndex++);
            }
        };
    }


}
