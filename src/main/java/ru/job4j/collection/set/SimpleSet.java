package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private final SimpleArray<T> set = new SimpleArray<>();

    public SimpleSet() {
    }

    @Override
    public boolean add(T value) {
        boolean res = false;
        if (!contains(value)) {
            set.add(value);
            res = true;
        }
        return res;
    }

    @Override
    public boolean contains(T value) {
        boolean res = false;
        for (T el : set) {
            if (Objects.equals(el, value)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
