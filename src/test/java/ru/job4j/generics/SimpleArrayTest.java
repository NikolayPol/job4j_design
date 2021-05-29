package ru.job4j.generics;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    @Test
    public void testAdd() {
        Integer[] arr = new Integer[5];
        SimpleArray<Integer> simpleArray = new SimpleArray<>(arr);
        simpleArray.add(10);
        assertEquals(simpleArray.getSize(), 5);
        assertEquals(simpleArray.getPoint(), 1);
    }

    @Test
    public void testSetAndGet() {
        Integer[] arr = new Integer[5];
        SimpleArray<Integer> simpleArray = new SimpleArray<>(arr);
        simpleArray.add(10);
        simpleArray.set(0, 20);
        assertThat(simpleArray.get(0), is(20));
    }

    @Test
    public void testRemove() {
        Integer[] arr = new Integer[5];
        SimpleArray<Integer> simpleArray = new SimpleArray<>(arr);
        simpleArray.add(10);
        simpleArray.remove(0);
        assertNull(simpleArray.get(0));
    }

}