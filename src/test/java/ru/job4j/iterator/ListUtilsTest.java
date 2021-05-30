package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenPredicate() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Predicate<Integer> predicate = (x) -> x % 2 == 0;
        ListUtils.removeIf(list, predicate);
        assertThat(list, Is.is(List.of(1, 3)));
    }

    @Test
    public void whenReplace() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Predicate<Integer> predicate = (x) -> x % 2 == 0;
        ListUtils.replaceIf(list, predicate, 0);
        assertThat(list, Is.is(List.of(1, 0, 3, 0)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 4));
        ListUtils.removeAll(list, elements);
        assertThat(list, Is.is(List.of(2, 3)));
    }

}