package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cpu")
public class Processor {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int cores;

    public Processor() {
    }

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
