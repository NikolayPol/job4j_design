//package ru.job4j.io;
//
//import org.junit.Test;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Iterator;
//import java.util.List;
//import java.util.function.Predicate;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//
//public class SearchTest {
//    @Test
//    public void testSearch() throws IOException {
//        Path start = Paths.get("D:\\JD\\PROJECTS\\job4j_design");
//        Predicate<Path> condition = p -> p.getFileName().toString().endsWith(".gitignore");
//        List<Path> res = Search.search(start, condition);
//        Iterator<Path> it = res.listIterator();
//        List<String> exp = List.of(
//                "D:\\JD\\PROJECTS\\job4j_design\\.gitignore");
//        assertThat(it.next().toString(), is(exp.get(0)));
//    }
//}