package ru.job4j.generics;


import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SimpleArrayIteratorTest {

    @Test
    public void testIterator() {
        Integer[] arr = new Integer[5];
        SimpleArray<Integer> simpleArray = new SimpleArray<>(arr);
        simpleArray.add(10);
        simpleArray.add(20);
        Iterator<Integer> iterator = new SimpleArrayIterator<>(arr);
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(10));
    }

}