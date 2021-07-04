package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "notebook")
@XmlAccessorType(XmlAccessType.FIELD)
public class Notebook {
    @XmlAttribute
    private boolean workStation;
    @XmlAttribute
    private float size;
    @XmlAttribute
    private String name;
    private Processor cpu;
    private String[] characteristics;

    public Notebook() {
    }

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

    public static void main(String[] args) throws JAXBException {
        Notebook notebook = new Notebook(
                false,
                15,
                "HP",
                new Processor("core-i5", 4),
                new String[]{"black", "2kg"});

//        Gson gson = new GsonBuilder().create();
//        System.out.println(gson.toJson(notebook));
//        String notebookJson = "{"
//                + "\"workStation\":false,"
//                + "\"size\":15,"
//                + "\"name\":\"HP\","
//                + "\"cpu\": " + "{" + "\"name\":\"core-i5\", \"cores\": 4" + "},"
//                + "\"characteristics\" : " + "[\"black\",\"2kg\"]"
//                + "}";
//        Notebook notebookMod = gson.fromJson(notebookJson, Notebook.class);
//        System.out.println(notebookMod);

        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Notebook.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(notebook, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            try (StringReader reader = new StringReader(result)) {
                // десериализуем
                Notebook rsl = (Notebook) unmarshaller.unmarshal(reader);
                System.out.println(rsl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
