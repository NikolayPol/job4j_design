package ru.job4j.examtask;

import java.util.*;

public class Analyze {
    public Info diff(List<User> previous, List<User> current) {
        Set<User> deleted = new HashSet<>(previous);
        Set<User> added = new HashSet<>(current);
        Set<User> changed = new HashSet<>(previous);

        deleted.removeAll(current);
        added.removeAll(previous);
        changed.retainAll(current);

        int count = 0;
        for (User user1 : changed) {
            for (User user2 : current) {
                if (user1.getId() == user2.getId()
                        && !(user1.getName().equals(user2.getName()))) {
                    count++;
                }
            }
        }

        Info info = new Info();
        info.setDeleted(deleted.size());
        info.setAdded(added.size());
        info.setChanged(count);
        return info;
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public void setAdded(int added) {
            this.added = added;
        }

        public void setChanged(int changed) {
            this.changed = changed;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        @Override
        public String toString() {
            return "Info{"
                    + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted
                    + "}";
        }
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
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
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
