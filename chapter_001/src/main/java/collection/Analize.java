package collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> prevUserMap = new HashMap<>();

        for (User user : previous) {
            prevUserMap.put(user.id, user);
        }
        Map<Integer, User> currentUserMap = new HashMap<>();
        for (User currentUser: current
             ) {
            Optional<User> testPrevUserMap = Optional.ofNullable(prevUserMap.putIfAbsent(currentUser.id, currentUser));
            if (testPrevUserMap.isEmpty()) {
                info.setAdded();
            } else if (!testPrevUserMap.get().name.equals(currentUser.name)) {
                info.setChanged();
            }
            currentUserMap.put(currentUser.id, currentUser);
        }
        //for deleted
        for (User prevUser : previous) {
            Optional<User> testCurrUserMap = Optional.ofNullable(currentUserMap.putIfAbsent(prevUser.id, prevUser));
            if (testCurrUserMap.isEmpty()) {
                info.setDeleted();
            }
        }

        return info;

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

        /**
         * привязываем хэш к ИД чтобы indexOf в списке находил пользователей по их ИД
         * @return
         */
        @Override
        public int hashCode() {
            int result = 17;
            result = 37 * result + this.id;
            result = 37 * result + this.name.hashCode();
            return result;
        }

        /**
         * Сравниваем только по именам
         * @param obj
         * @return
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof User)) {
                return false;
            }
            User u = (User) obj;
            return this.id == u.id;
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info() {
            this.added = 0;
            this.changed = 0;
            this.deleted = 0;
        }

        public int getAdded() {
            return added;
        }

        public void setAdded() {
            this.added ++;
        }

        public int getChanged() {
            return changed;
        }

        public void setChanged() {
            this.changed ++;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted() {
            this.deleted ++;
        }

        @Override
        public String toString() {
            return "add: " + this.getAdded() + " modify: " + this.getChanged() + " del: " + this.getDeleted();
        }
    }

}