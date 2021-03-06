package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private final T[] arr;
    private int point = 0;
    private int size;

    public SimpleArrayIterator(T[] arr, int size) {
        this.size = size;
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        return point < size;

    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return arr[point++];
    }
}
