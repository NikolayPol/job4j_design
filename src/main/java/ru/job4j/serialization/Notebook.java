package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isWorkStation() {
        return workStation;
    }

    public void setWorkStation(boolean workStation) {
        this.workStation = workStation;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Processor getCpu() {
        return cpu;
    }

    public void setCpu(Processor cpu) {
        this.cpu = cpu;
    }

    public String[] getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String[] characteristics) {
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

//        // ???????????????? ???????????????? ?????? ?????????????? ?? ??????
//        JAXBContext context = JAXBContext.newInstance(Notebook.class);
//        // ?????????????? ????????????????????????
//        Marshaller marshaller = context.createMarshaller();
//        // ??????????????????, ?????? ?????? ?????????? ????????????????????????????
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//
//        try (StringWriter writer = new StringWriter()) {
//            // ??????????????????????
//            marshaller.marshal(notebook, writer);
//            String result = writer.getBuffer().toString();
//            System.out.println(result);
//
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            try (StringReader reader = new StringReader(result)) {
//                // ??????????????????????????
//                Notebook rsl = (Notebook) unmarshaller.unmarshal(reader);
//                System.out.println(rsl);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Processor processor = new Processor();
        processor.setName("core-i5");
        processor.setCores(4);
        /* JSONObject ???? json-???????????? ???????????? */
        JSONObject jsonCpu = new JSONObject("{\"name\":\"core-i5\", \"cores\":\"4\"}");
        jsonCpu.put("name", processor.getName());
        jsonCpu.put("cores", processor.getCores());

        /* JSONArray ???? ArrayList */
        List<String> list = new ArrayList<>();
        list.add("black");
        list.add("2kg");
        JSONArray jsonCharacteristics = new JSONArray(list);

        /* JSONObject ???????????????? ?????????????? put */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workStation", notebook.isWorkStation());
        jsonObject.put("size", notebook.getSize());
        jsonObject.put("name", notebook.getName());
        jsonObject.put("cpu", jsonCpu);
        jsonObject.put("characteristics", jsonCharacteristics);

        /* ?????????????? ?????????????????? ?? ?????????????? */
        System.out.println(jsonObject.toString());

        /* ?????????????????????? ???????????? person ?? json-???????????? */
        System.out.println(new JSONObject(notebook).toString());
    }
}
