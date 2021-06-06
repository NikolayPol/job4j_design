package ru.job4j.examtask;

import java.util.*;

public class UserBase {
    private final HashMap<String, List<String>> map = new HashMap<>();

    public HashMap<String, List<String>> getMap() {
        return map;
    }

    public void add(String user, List<String> userlist) {

        map.put(user, userlist);
    }

    public static void main(String[] args) {
        UserBase uc = new UserBase();
        uc.add("user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        uc.add("user2", List.of("foo@gmail.com", "ups@pisem.net"));
        uc.add("user3", List.of("xyz@pisem.net", "vasya@pupkin.com"));
        uc.add("user4", List.of("ups@pisem.net", "aaa@bbb.ru"));
        uc.add("user5", List.of("xyz@pisem.net"));
        UserConverter userConverter = new UserConverter(uc.getMap());
        HashMap<String, List<String>> res = userConverter.userConvert();
        for (String user : res.keySet()) {
            System.out.println(user + " : " + res.get(user));
        }
    }

}
