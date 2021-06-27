package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dv = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), dv);
        List<FileProperty> list = dv.getResult();
        list.forEach(el ->
                System.out.println(el.getName() + " " + el.getSize() + " " + el.getPath()));
    }
}
