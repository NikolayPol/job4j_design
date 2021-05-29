package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] container;
    private int capacity;
    private int size = 0;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public SimpleArray() {
        this.capacity = 10;
        container = (T[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public SimpleArray(int capacity) {
        this.capacity = capacity;
        container = (T[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public void add(T model) {
        container[size++] = model;
        modCount++;
        if (size == capacity) {
            capacity += (int) (0.5 * capacity) + 1;
            T[] arrBuffer = (T[]) new Object[capacity];
            System.arraycopy(container, 0, arrBuffer, 0, container.length);
            container = arrBuffer;
        }
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator(container, size, modCount);
    }

    private class SimpleArrayIterator implements Iterator<T> {
        private final T[] container;
        private final int size;
        private int point = 0;
        private final int expectedModCount;

        public SimpleArrayIterator(T[] container, int size, int modCount) {
            this.container = container;
            this.size = size;
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return point < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return container[point++];
        }
    }
}