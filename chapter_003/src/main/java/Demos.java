import java.util.*;

public class Demos {
    public static void main(String[] args) {
        List<String> l = new LinkedList<>();
        l.add("as"); l.add(1, "33"); l.remove("as");
        l.get(1);
        l.set(2, "a");

        Set<String> s = new HashSet<>();
        s.size(); s.add("as"); s.clear(); s.contains("as"); s.hashCode();
        s.isEmpty(); s.iterator(); s.remove("asd");

        Map<String, String> m = new HashMap<>();
        m.put("a", "aa");
        m.size(); m.get("a"); m.getOrDefault("a", "dfs");
        m.remove("a");
        m.entrySet(); m.keySet(); m.clear(); m.containsKey("a"); m.containsValue("aa"); m.hashCode(); m.equals("a");

        Map<String, String> t = new TreeMap<>();

    }
}
