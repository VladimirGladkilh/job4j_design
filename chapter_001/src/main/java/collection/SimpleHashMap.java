package collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<V> {

    private int size = 0;

    private static class Node<K, V> {
        private final K key;
        private final V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public final String toString() {
            return key + "=" + value;
        }
    }

    private Node<K, V>[] table;

    private static int hash(Object key) {
        int h;
        if (key == null) {
            h = 0;
        } else {
            h = key.hashCode();
            h = h ^ (h >>> 16);
        }
        return h;
    }

    public SimpleHashMap() {
        table = new Node[16];
    }

    /**
     * Определяет индекс в массиве по хешу ключа
     *
     * @param hash хеш ключа
     * @return индекс в масссиве
     */
    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    /**
     * Увеличивает размер массив Node[]
     *
     * @param size новый размер массива
     */
    private void resize(int size) {
        Node<K, V>[] oldTab = table;
        table = new Node[size];
        for (Node<K, V> node : oldTab) {
            if (node != null) {
                K key = node.key;
                V value = node.value;
                int hash = hash(key);
                int index = indexFor(hash);
                table[index] = new Node<>(key, value);
            }
        }
    }

    /**
     * Вставка значения.
     * По одинаковым ключам перезаписвает значение
     * @param key   ключ
     * @param value значение
     */
    public void insert(K key, V value) {
        if (size == table.length) {
            resize(size * 2);
        }
        int hash = hash(key);
        int index = indexFor(hash);
        Node<K, V> oldVal = table[index];
        if (oldVal == null) {
            table[index] = new Node<>(key, value);
            size++;
        } else {
            if (oldVal.key.equals(key)) {
                table[index] = new Node<>(key, value);
            }
        }
    }

    /**
     * Получение значения по ключу
     * @param key ключ
     * @return значение по ключу
     */
    public V get(K key) {
        int hash = hash(key);
        int index = indexFor(hash);
        V value = null;
        Node<K, V> node = table[index];
        if (node != null) {
            if ((node.key == null && key == null) || (node.key != null && node.key.equals(key))) {
                value = node.value;
            }
        }
        return value;
    }

    /**
     * Удаление значения по ключу
     *
     * @param key ключ
     * @return результат удаления
     */
    public boolean delete(K key) {
        boolean result = false;
        if (table.length > 0) {
            int hash = hash(key);
            int index = indexFor(hash);
            Node<K, V> node = table[index];
            if (node != null && key.equals(node.key)) {
                table[index] = null;
                result = true;
                size--;
            }
        }
        return result;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                boolean hasNext = false;
                if (index < table.length) {
                    Node<K, V> node;
                    do {
                        node = table[index++];
                    }
                    while (node == null && index < table.length);
                    hasNext = (node != null);
                    index--;
                }
                return hasNext;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].value;
            }
        };
    }
}
