package ru.job4j.examtask2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Set<String>> map = new HashMap<>();
        map.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        map.put("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        map.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        map.put("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        map.put("user5", Set.of("xyz@pisem.net"));
        Map<String, Set<String>> res = merge(map);
        for (String user : res.keySet()) {
            System.out.println(user + " : " + res.get(user));
        }
    }

    public static Map<String, Set<String>> merge(Map<String, Set<String>> data) {
        HashMap<String, String> mapConvert = new HashMap<>();
        HashMap<String, Set<String>> resultMap = new HashMap<>();
        for (String user : data.keySet()) {
            for (String email : data.get(user)) {
                mapConvert.put(email, user);
            }
        }
        String user;
        for (String email : mapConvert.keySet()) {
            user = mapConvert.get(email);
            if (!resultMap.containsKey(user)) {
                Set<String> list = new HashSet<>();
                list.add(email);
                resultMap.put(user, list);
            } else {
                Set<String> list = resultMap.get(user);
                list.add(email);
                resultMap.replace(user, list);
            }
        }
        return resultMap;
    }

}

