package collection;

import java.util.*;

public class Article {
    public static boolean generateBy(String origin, String line) {
        origin = replaceSpec(origin);
        line = replaceSpec(line);

        //with one Map
        Map<String, Integer> originMap = new HashMap<>();
        for (String subString: origin.split(" ")) {
            int kol = originMap.getOrDefault(subString, 0) + 1;
            originMap.put(subString, kol);
        }

        for (String subString: line.split(" ")) {
            int kol = originMap.getOrDefault(subString, 0);
            if (kol == 0) {
                return false;
            }
            kol--;
            originMap.put(subString, kol);
        }

        return true;

    }
    private static String replaceSpec(String origin) {
        return origin.replace(".", "")
                .replace(",", "")
                .replace("!", "")
                .replace("?", "");
    }
}
