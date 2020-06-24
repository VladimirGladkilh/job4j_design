package collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> last;
    private int size = 0;

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T res = head.value;
        Node<T> next = head.next;
        head = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return res;

    }

    public T deleteLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        T res = last.value;
        Node<T> prev = last.prev;

        last = prev;
        if (prev == null)
            head = null;
        else
            prev.next = null;
        size--;
        return res;

    }
    public void add(T value) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public void revert() {
        Node<T> previous = null;
        Node<T> current = head;
        Node<T> forward;

        while (current != null) {
            forward = current.next;
            current.next = previous;
            previous = current;
            current = forward;
        }

        head = previous;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }


    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;
        public Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}