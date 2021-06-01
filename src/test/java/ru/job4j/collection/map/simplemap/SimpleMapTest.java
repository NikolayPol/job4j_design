package ru.job4j.collection.map.simplemap;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void put() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("abc", 10));
        assertFalse(map.put("abc", 10));
    }

    @Test
    public void whenMorePutElements() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("a", 1));
        assertTrue(map.put("b", 2));
        assertTrue(map.put("c", 3));
        assertTrue(map.put("d", 4));
        assertTrue(map.put("e", 5));
        assertTrue(map.put("f", 6));
        assertTrue(map.put("g", 7));
        assertTrue(map.put("h", 8));
        assertTrue(map.put("i", 9));
        assertTrue(map.put("j", 10));
        assertTrue(map.put("k", 11));
        assertTrue(map.put("l", 12));
        assertTrue(map.put("m", 13));
        assertTrue(map.put("n", 14));
    }

    @Test
    public void get() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        assertThat(map.get("a"), is(1));
        assertThat(map.get("b"), is(2));
        assertThat(map.get("c"), is(3));
    }

    @Test
    public void whenGetNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        assertNull(map.get("d"));

    }

    @Test
    public void remove() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.remove("a");
        assertNull(map.get("a"));
        assertThat(map.get("b"), is(2));
    }

    @Test
    public void iterator() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        Iterator<String> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is("a"));
    }

    @Test(expected = NoSuchElementException.class)
    public void nextElementIsAbsent() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        Iterator<String> i = simpleMap.iterator();
        i.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorException() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("a", 1);
        Iterator<String> i = simpleMap.iterator();
        assertThat(i.next(), is("a"));
        simpleMap.put("b", 2);
        i.next();
    }

}