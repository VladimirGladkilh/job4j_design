package collection;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WorkWithUser {
    public static void main(String[] args) {
        User u1 = new User("Vasya", 0, new Calendar.Builder().setDate(2000, 1, 1).build());
        User u2 = new User("Vasya", 1, new Calendar.Builder().setDate(2000, 1, 1).build());
        Map<User, Object> userObjectMap = new HashMap<User, Object>();
        userObjectMap.put(u1, u1.hashCode());
        userObjectMap.put(u2, u2.hashCode());
        System.out.println(userObjectMap);
    }
}
