package ru.job4j.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SearchTest {
    @Test
    public void testSearch() throws IOException {
        Path start = Paths.get(".");
        Predicate<Path> condition = p -> p.getFileName().toString().endsWith("js");
        List<Path> res = Search.search(start, condition);
        Iterator<Path> it = res.listIterator();
        List<String> exp = List.of(
                "D:\\JD\\PROJECTS\\job4j_design\\.\\target\\site\\jacoco\\jacoco-resources\\prettify.js",
                "D:\\JD\\PROJECTS\\job4j_design\\.\\target\\site\\jacoco\\jacoco-resources\\sort.js");
        assertThat(it.next().toString(), is(exp.get(0)));
        assertThat(it.next().toString(), is(exp.get(1)));
    }
}