package collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicStore<E> implements Iterable<E> {
    private int size = 0;
    private int modCount = 0;
    private int lastIndex = 0;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        private final E item;
        private Node<E> next;
        private final Node<E> prev;

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
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        if (checkElement(index)) {
            if (index >= lastIndex) {
                Node<E> x = first;
                for (int i = lastIndex; i < index; i++) {
                    x = x.next;
                }
                lastIndex = index;
                return x.item;
            } else {
                Node<E> x = last;
                for (int i = lastIndex - 1; i > index; i--) {
                    x = x.prev;
                }
                lastIndex = index;
                return x.item;
            }

        }
        return null;
    }

    public boolean checkElement(int index) {
        return index >= 0 && index < size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int nextIndex = 0;

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
