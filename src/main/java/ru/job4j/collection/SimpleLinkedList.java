package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private final Node<E> firstNode;
    private Node<E> prevNode;
    private int size = 0;
    private int modCount = 0;

    public SimpleLinkedList() {
        this.firstNode = new Node<>(null, null, null);
        this.prevNode = new Node<>(firstNode, null, null);
        this.firstNode.setNext(prevNode);
    }

    @Override
    public void add(E value) {
        Node<E> currentNode = new Node<>(prevNode, value, null);
        prevNode.setNext(currentNode);
        prevNode = currentNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = firstNode.getNext();
        for (int i = 0; i <= index; i++) {
            node = node.getNext();
        }
        return node.getItem();
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleLinkedListIterator(firstNode, size, modCount);
    }

    private static class Node<E> {
        private final E item;
        private Node<E> next;

        private final Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }

        public E getItem() {
            return item;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private class SimpleLinkedListIterator implements Iterator<E> {

        private Node<E> currentNode;
        private final int size;
        private int point = 0;
        private final int expectedModCount;

        public SimpleLinkedListIterator(Node<E> firstNode, int size, int modCount) {
            this.currentNode = firstNode.getNext();
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
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            currentNode = currentNode.getNext();
            E item = currentNode.getItem();
            point++;
            return item;
        }
    }
}