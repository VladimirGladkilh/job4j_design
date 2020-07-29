import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GCDemo {
    private static class User {
        private String name;
        private String id;

        public User(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public User() {

        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("User Deleted"+ this.name);
        }
    }
    public static void main(String[] args) {
        someMethod();
        info("Use GC");
    }
    private static void someMethod() {
        info("Start");
        List<User> list = new LinkedList<>();
        for (int i = 0; i < 20000; i++) {
            User data = new User("User " + i, "Id "+ i);
            list.add(data);
            //list.add(new User());
        }
        //list = null;
        info("Byssy");
        System.gc();

    }
    private static void info(String s) {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println(s);
        System.out.println("Total = " + runtime.totalMemory() / mb );
        System.out.println("Used  = " + (runtime.totalMemory() - runtime.freeMemory()) / mb  );
        System.out.println("Free  = " + runtime.freeMemory() / mb );
    }
}
