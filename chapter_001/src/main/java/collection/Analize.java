package collection;

import java.util.List;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        for (User user: previous
             ) {
            int index = current.indexOf(user);
            if (index < 0) {
                info.setDeleted();
            } else {
                User cUser = current.get(index);
                if (cUser.hashCode() != user.hashCode()) {
                    info.setChanged();
                }
            }
        }
        for (User cUser: current) {
            if (previous.indexOf(cUser) < 0) {
                info.setAdded();
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
            this.deleted++;
        }

        @Override
        public String toString() {
            return "add: " + this.getAdded() + " modify: " + this.getChanged() + " del: " + this.getDeleted();
        }
    }

}