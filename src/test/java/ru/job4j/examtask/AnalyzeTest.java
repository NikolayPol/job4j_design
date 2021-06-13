package ru.job4j.examtask;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    @Test
    public void test() {
        Analyze an = new Analyze();
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "a"),
                new Analyze.User(2, "б"),
                new Analyze.User(3, "в"),
                new Analyze.User(4, "г"),
                new Analyze.User(5, "д")
        );
        List<Analyze.User> current = List.of(
                new Analyze.User(2, "б"),
                new Analyze.User(3, "в"),
                new Analyze.User(4, "изменено имя"),
                new Analyze.User(6, "д"),
                new Analyze.User(7, "е"),
                new Analyze.User(8, "е")
        );

        Analyze.Info actual = an.diff(previous, current);
        Analyze.Info expected = new Analyze.Info();
        expected.setAdded(3);
        expected.setChanged(1);
        expected.setDeleted(2);
        assertEquals(actual, is(expected));
    }
}