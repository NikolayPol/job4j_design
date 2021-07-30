package ru.job4j.examtask;

import org.apache.commons.collections.map.HashedMap;

import java.util.*;

public class Analyze {
    public Info diff(List<User> previous, List<User> current) {
        int added = 0;
        int deleted;
        int changed = 0;
        HashedMap map = new HashedMap();

        for (int i = 0; i < previous.size(); i++) {
            map.put(i, previous.get(i));
        }

        deleted = map.size();
        for (User userCurrent : current) {
            if (!map.containsKey(userCurrent.getId())) {
                added++;
            } else if (!userCurrent.getName().equals(map.get(userCurrent.getId()))) {
                changed++;
            }
            if (map.containsKey(userCurrent.getId())) {
                deleted--;
            }
        }

        Info info = new Info();
        info.setDeleted(deleted);
        info.setAdded(added);
        info.setChanged(changed);
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added && changed == info.changed && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
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
