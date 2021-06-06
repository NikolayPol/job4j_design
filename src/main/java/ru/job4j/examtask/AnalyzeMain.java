package ru.job4j.examtask;

import java.util.List;

public class AnalyzeMain {
    public static void main(String[] args) {
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
        System.out.println(an.diff(previous, current));
    }
}
