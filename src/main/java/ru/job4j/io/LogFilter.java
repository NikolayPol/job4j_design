package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> res = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                lines.add(line);
            }
            for (String line : lines) {
                if (line.contains("404")) {
                    res.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file, false)))) {
            for (String line  : log) {
                //out.write(line + System.lineSeparator());
                out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String line : log) {
            System.out.println(line);
        }
        save(log, "logFilter.txt");
    }
}
