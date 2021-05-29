package ru.job4j.collection;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    @Test
    public void whenAddAndGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), Is.is(1));
        assertThat(list.get(1), Is.is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);

        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(1));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(2));
        assertThat(first.hasNext(), Is.is(false));

        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(1));
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(2));
        assertThat(second.hasNext(), Is.is(false));
    }
    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(10);
        Iterator<Integer> it = list.iterator();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        List<Double> list = new SimpleLinkedList<>();
        list.add(10.0);
        list.add(20.0);
        Iterator<Double> it = list.iterator();
        it.next();
        list.add(30.0);
        it.next();
    }

    @Test
    public void whenGetFirstAndLastElement() {
        List<Character> list = new SimpleLinkedList<>();
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');
        assertThat(list.get(0), is('a'));
        assertThat(list.get(3), is('d'));
    }

    @Test
    public void whenAddAndGetNull() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(null);
        list.add(1);
        assertNull(list.get(0));
        assertThat(list.get(1), Is.is(1));
    }
}