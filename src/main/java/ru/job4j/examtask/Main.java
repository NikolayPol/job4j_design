package ru.job4j.examtask;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserBase uc = new UserBase();
        uc.add("user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        uc.add("user2", List.of("foo@gmail.com", "ups@pisem.net"));
        uc.add("user3", List.of("xyz@pisem.net", "vasya@pupkin.com"));
        uc.add("user4", List.of("ups@pisem.net", "aaa@bbb.ru"));
        uc.add("user5", List.of("xyz@pisem.net"));
        uc.print();
    }
}
