package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("Please add second parameter");
        }
        Path start = Paths.get(args[0]);
        String extansion = args[1];
        search(start, p -> p.toFile().getName().endsWith(extansion)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {

        if (!root.toFile().exists()) {
            throw new IllegalArgumentException(String.format("File %s not exist", root));
        }

        if (!root.toFile().isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", root));
        }

        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
