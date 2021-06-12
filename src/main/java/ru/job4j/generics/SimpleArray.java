package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] arr;
    private int size;
    private int point = 0;

    public SimpleArray(T[] arr) {
        this.arr = arr;
        this.size = arr.length;
    }

    public int getSize() {
        return size;
    }

    public int getPoint() {
        return point;
    }

    public void add(T model) {
        arr[point++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        arr[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        arr[index] = null;
        System.arraycopy(arr, index + 1, arr, index, arr.length - index - 1);
        arr[arr.length - 1] = null;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return arr[index];

    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>(arr, size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return size == that.size && point
                == that.point && Arrays.equals(arr, that.arr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, point);
        result = 31 * result + Arrays.hashCode(arr);
        return result;
    }
}
