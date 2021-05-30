package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        Node<T> node;
        if (head == null) {
            throw new NoSuchElementException();
        }
        node = head;
        head = head.next;
        node.next = null;
        return node.value;
    }

    public T deleteLast() {
        Node<T> node = head;
        Node<T> prevNode = head;
        while (node.next != null) {
            prevNode = node;
            node = node.next;
        }
        T value = node.value;
        prevNode.next = null;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private Node<T> node = head;

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
        private final T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
