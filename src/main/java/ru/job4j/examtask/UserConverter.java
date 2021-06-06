package ru.job4j.examtask;

import java.util.*;

public class UserConverter {
    private final HashMap<String, List<String>> map;
    private final HashMap<String, String> mapConvert = new HashMap<>();
    private final HashMap<String, List<String>> resultMap = new HashMap<>();

    public UserConverter(HashMap<String, List<String>> map) {
        this.map = map;
    }

    public HashMap<String, List<String>> userConvert() {
        mapConvert();
        String user;
        for (String email : mapConvert.keySet()) {
            user = mapConvert.get(email);
            if (!resultMap.containsKey(user)) {
                List<String> list = new ArrayList<>();
                list.add(email);
                resultMap.put(user, list);
            } else {
                List<String> list = resultMap.get(user);
                list.add(email);
                resultMap.replace(user, list);
            }
        }
        return resultMap;
    }

    private void mapConvert() {
        for (String user : map.keySet()) {
            for (String email : map.get(user)) {
                mapConvert.put(email, user);
            }
        }
    }
}

