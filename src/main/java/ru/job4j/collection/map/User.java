package ru.job4j.collection.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private String birthday;

    public User(String name, int children, String birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }

    public static void main(String[] args) {
        User user1 = new User("Ivan", 2, "01.01.2020");
        User user2 = new User("Ivan", 2, "01.01.2020");

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());

        Set<Map.Entry<User, Object>> set = map.entrySet();
        for (Map.Entry<User, Object> el : set) {
            System.out.println(el.getKey() + ": " + el.getValue());
        }
    }
}
