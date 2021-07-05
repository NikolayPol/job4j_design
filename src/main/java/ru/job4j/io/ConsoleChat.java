package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> history = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String[] answers = new String[0];
        String question;
        String answer;
        boolean start = true;
        try {
            Path p = Paths.get(botAnswers);
            answers = Files.lines(p, StandardCharsets.UTF_8).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (start) {
            question = scanner.nextLine();
            history.add(question);
            if (question.equalsIgnoreCase(STOP)) {
                question = scanner.nextLine();
                while (!question.equalsIgnoreCase(CONTINUE)) {
                    question = scanner.nextLine();
                }
                if (question.equalsIgnoreCase(CONTINUE)) {
                    continue;
                }
            }
            if (question.equalsIgnoreCase(OUT)) {
                recordHistory(history);
                System.exit(1);
            }
            answer = answers[new Random().nextInt(answers.length)];
            System.out.println(answer);
            history.add(answer);
        }
    }

    private void recordHistory(List<String> history) {
        try (PrintWriter pw = new PrintWriter(path, StandardCharsets.UTF_8)) {
            history.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/botHistory.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
