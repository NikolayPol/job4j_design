package ru.job4j.generics;

public class RoleStore {
    public static void main(String[] args) {
        UserStore userStore = new UserStore();
        userStore.add(new User("001"));
        userStore.add(new User("002"));
        userStore.replace("001", new User("003"));
        userStore.delete("002");
        System.out.println(userStore.findById("001").getId());
        System.out.println(userStore.findById("002"));
    }
}
