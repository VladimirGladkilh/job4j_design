package collection;

import java.util.*;

public class DynamicStore<E> implements Iterable<E> {
    private int size = 0;
    private int modCount = 0;
    private final int lastIndex = 0;
    private Node<E> first;
    private Node<E> last;
    private Node<E> lastGet;

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

    /**
     *
     * @param index индекс
     * @return нода
     */
    private Node<E> node(int index) {
        Node<E> x;
        if (index < (size >> 1)) {
            x = lastGet != null ? lastGet : first;

            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = lastGet != null ? lastGet : last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    public E get(int index) {
        if (checkElement(index)) {
            return node(index).item;
        }
        return null;
    }

    public boolean checkElement(int index) {
        return index >= 0 && index < size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> next = node(0);
            private int nextIndex;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return nextIndex < size && expectedModCount > 0;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> lastGetting = next;
                next = next.next;
                nextIndex++;
                return lastGetting.item;
            }
        };
    }

}
