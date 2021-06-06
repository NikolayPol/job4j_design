package ru.job4j.examtask;

import java.util.*;

public class UserBase {
    private final HashMap<String, List<String>> map = new HashMap<>();
    private final UserConverter userConverter = new UserConverter(map);

    public void add(String user, List<String> list) {
        map.put(user, list);
    }

    public HashMap<String, List<String>> convert() {
        return userConverter.userConvert();
    }

    public void print() {
        HashMap<String, List<String>> res = convert();
        for (String user : res.keySet()) {
            System.out.println(user + " : " + res.get(user));
        }
    }
}
