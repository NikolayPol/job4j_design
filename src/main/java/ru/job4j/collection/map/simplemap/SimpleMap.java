package ru.job4j.collection.map.simplemap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if (count >= LOAD_FACTOR * capacity) {
            int index;
            capacity = (int) Math.pow(2, Math.log(capacity) / Math.log(2) + 1);
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
            for (var el : table) {
                if (el != null) {
                    index = indexFor(hash(el.key.hashCode()));
                    newTable[index] = el;
                }
            }
            table = newTable;
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            return null;
        }
        if (table[index].key.equals(key)) {
            return table[index].getValue();
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new SimpleMapIterator(modCount, table);
    }

    private static class MapEntry<K, V> {

        private final K key;
        private final V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }
    }

    public class SimpleMapIterator implements Iterator<K> {
        private final int expectedModCount;
        private int index = 0;
        private final MapEntry<K, V>[] table;

        public SimpleMapIterator(int expectedModCount, MapEntry<K, V>[] table) {
            this.expectedModCount = expectedModCount;
            this.table = table;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            for (int i = 0; i < table.length; i++) {
                var element = table[i];
                if (element != null) {
                    index = i;
                    return true;
                }
            }
            return false;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            count++;
            return table[index].key;
        }
    }

}