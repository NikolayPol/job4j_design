package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final HashMap<FileProperty, Boolean> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        long size = file.toFile().length();
        String name = file.toFile().getName();
        String path = file.toFile().getAbsolutePath();
        FileProperty fileProperty = new FileProperty(size, name, path);
        if (!map.containsKey(fileProperty)) {
            map.put(fileProperty, false);
        } else {
            map.replace(fileProperty, true);
        }
        return super.visitFile(file, attrs);
    }

    public List<FileProperty> getResult() {
        List<FileProperty> res = new ArrayList<>();

        for (FileProperty f : map.keySet()) {
            if (map.get(f)) {
                res.add(f);
            }
        }
        return res;
    }
}