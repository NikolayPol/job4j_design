package ru.job4j.serialization;

public class Processor {
    private final String name;
    private final int cores;

    public Processor(String name, int cores) {
        this.name = name;
        this.cores = cores;
    }

    @Override
    public String toString() {
        return "Processor{"
                + "name='" + name + '\''
                + ", cores=" + cores
                + '}';
    }
}
