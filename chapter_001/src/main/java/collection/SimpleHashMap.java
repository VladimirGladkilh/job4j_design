package collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private Node<K, V>[] table;
    private final float loadFactor;
    private Node<K, V> next;
    private int count;

    private int threshold;
    private Node<K, V> current;

    private transient int modCount = 0;


    public SimpleHashMap(int startSize, float loadFactor) {
        this.loadFactor = loadFactor;
        table = new Node[startSize];
        threshold = (int) Math.min(startSize * loadFactor, MAX_ARRAY_SIZE + 1);
    }

    public SimpleHashMap() {
        this(11, 0.75f);
    }


    @Override
    public Iterator<Node<K, V>> iterator() {
        return new SmIterator();
    }

    public V get(Object key) {
        Node<K, V>[] tab = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Node<K, V> e = tab[index]; e != null; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    public boolean delete(K key) {
        Node<K, V>[] tab = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        Node<K, V> e = tab[index];
        for (Node<K, V> prev = null; e != null; prev = e, e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    tab[index] = e.next;
                }
                modCount++;
                count--;
                e.value = null;
                return true;
            }
        }
        return false;
    }

    public boolean insert(K key, V value) {
        if (value == null) {
            throw new NullPointerException();
        }

        Node<K, V>[] tab = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;

        Node<K, V> node = tab[index];
        for (; node != null; node = node.next) {
            if ((node.hash == hash) && node.key.equals(key)) {
                //перезатираем
                node.value = value;
                return false;
            }
        }

        //Node<K, V> tab[] = table;
        if (count >= threshold) {
            rehash();
            tab = table;
            hash = key.hashCode();
            index = (hash & 0x7FFFFFFF) % tab.length;
        }

        Node<K, V> e = tab[index];
        tab[index] = new Node<>(hash, key, value, e);
        count++;
        modCount++;

        return true;
    }

    private void rehash() {
        int oldCapacity = table.length;
        Node<K, V>[] oldMap = table;

        // overflow-conscious code
        int newCapacity = (oldCapacity << 1) + 1;
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            if (oldCapacity == MAX_ARRAY_SIZE) {
                return;
            }
            newCapacity = MAX_ARRAY_SIZE;
        }
        Node<K, V>[] newMap = new Node[newCapacity];

        modCount++;
        threshold = (int) Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
        table = newMap;

        for (int i = oldCapacity; i-- > 0;) {
            for (Node<K, V> old = oldMap[i]; old != null;) {
                Node<K, V> e = old;
                old = old.next;
                int index = (e.hash & 0x7FFFFFFF) % newCapacity;
                e.next = newMap[index];
                newMap[index] = e;
            }
        }
    }

    public static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        protected Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            if (value == null) {
                throw new NullPointerException();
            }
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public String toString() {
            return key.toString() + "=" + value.toString();
        }
    }

    private class SmIterator implements Iterator<Node<K, V>> {
        Node<K, V> next;        // next entry to return
        Node<K, V> current;     // current entry
        int expectedModCount;  // for fast-fail
        int index;             // current slot

        public SmIterator() {
            expectedModCount = modCount;
            Node<K, V>[] t = table;
            current = next;
            next = null;
            index = 0;
            if (t != null && count > 0) { // advance to first entry
                do {
                    //some text
                } while (index < t.length && (next = t[index++]) == null);
            }
        }

        public boolean hasNext() {
            return next != null;
        }

        public Node<K, V> next() {
            Node<K, V>[] t;
            Node<K, V> e = next;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (e == null) {
                throw new NoSuchElementException();
            }
            current = e;
            t = table;
            next = current.next;
            if (next == null && t != null) {
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }
    }
}
