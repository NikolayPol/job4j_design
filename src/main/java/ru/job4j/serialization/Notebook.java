package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Notebook {
    private final boolean workStation;
    private final float size;
    private final String name;
    private final Processor cpu;
    private final String[] characteristics;

    public Notebook(boolean workStation, float size, String name, Processor cpu,
                    String[] characteristics) {
        this.workStation = workStation;
        this.size = size;
        this.name = name;
        this.cpu = cpu;
        this.characteristics = characteristics;
    }

    @Override
    public String toString() {
        return "Notebook{"
                + "workStation=" + workStation
                + ", size=" + size
                + ", name='" + name + '\''
                + ", cpu=" + cpu
                + ", characteristics=" + Arrays.toString(characteristics)
                + '}';
    }

    public static void main(String[] args) {
        Notebook notebook = new Notebook(
                false,
                15,
                "HP",
                new Processor("core-i5", 4),
                new String[]{"black", "2kg"});

        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(notebook));
        String notebookJson = "{"
                + "\"workStation\":false,"
                + "\"size\":15,"
                + "\"name\":\"HP\","
                + "\"cpu\": " + "{" + "\"name\":\"core-i5\", \"cores\": 4" + "},"
                + "\"characteristics\" : " + "[\"black\",\"2kg\"]"
                + "}";
        Notebook notebookMod = gson.fromJson(notebookJson, Notebook.class);
        System.out.println(notebookMod);
    }
}
