package ru.job4j.io.scanner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Not found arguments");
        }
        for (String arg : args) {
            String[] str = arg.split("=");
            if (str.length == 1) {
                throw new IllegalArgumentException("Wrong Some Argument");
            }
            values.put(str[0].replace("-", ""), str[1]);
        }
    }

    public static CSVReader of(String[] args) {
        CSVReader names = new CSVReader();
        names.parse(args);
        return names;
    }

    public void execute() throws IOException {
        Scanner scanner = new Scanner(new File(
                "src\\main\\java\\ru\\job4j\\io\\scanner\\" + values.get("path")));
        Path pathOut = Paths.get(values.get("out"));
        List<String> list = readFile(scanner);
        if (values.get("out").equals("stdout")) {
            for (String str : list) {
                System.out.println(str);
            }
        } else {
            Files.write(pathOut, list);
        }
    }

    private List<String> readFile(Scanner scanner) {
        boolean flag = true;
        List<Integer> index = new ArrayList<>();
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String[] row = scanner.nextLine().split(values.get("delimiter"));
            if (flag) {
                index = findIndex(row);
                flag = false;
            }
            for (Integer i : index) {
                    sb.append(row[i]).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        list.add(sb.toString());
        return list;
    }

    private List<Integer> findIndex(String[] row) {
        List<Integer> list = new ArrayList<>();
        String[] filter = values.get("filter").split(",");
        for (String s : filter) {
            for (int j = 0; j < row.length; j++) {
                if (s.equals(row[j])) {
                    list.add(j);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        CSVReader csvReader = CSVReader.of(args);
        csvReader.execute();
    }
}
