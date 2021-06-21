package ru.job4j.io;

import java.io.*;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pr = new PrintWriter(
                     new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean flag = false;
            String line;
            for (line = br.readLine(); line != null; line = br.readLine()) {
                String[] str = line.split(" ");
                if (line.contains("400") || line.contains("500")) {
                    if (!flag) {
                        pr.print(str[1] + ";");
                        flag = true;
                    }
                }
                if (!line.contains("400") && !line.contains("500")) {
                    if (flag) {
                        pr.print(str[1] + ";" + System.lineSeparator());
                        flag = false;
                    }
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy.unavailable("source.csv", "target.csv");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}