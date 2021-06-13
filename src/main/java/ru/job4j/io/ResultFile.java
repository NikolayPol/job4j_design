package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt", false)) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    byte[] bytes = (i + " * " + j + " = " + i * j + "\n").getBytes();
                    out.write(bytes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
