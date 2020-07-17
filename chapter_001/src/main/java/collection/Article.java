package collection;

import java.util.*;

public class Article {
    public static boolean generateBy(String origin, String line) {
        origin = replaceSpec(origin);
        line = replaceSpec(line);
        //with Set
        Set<String> originSet = new TreeSet<>();
        for (String subString: origin.split(" ")) {
            originSet.add(subString);
        }
        Set<String> lineSet = new TreeSet<>();
        for (String subString: line.split(" ")) {
            lineSet.add(subString);
        }
        return originSet.containsAll(lineSet);
        /*
        //with one Map
        Map<String, String> originMap = new TreeMap<>();
        for (String subString: origin.split(" ")) {
            originMap.put(subString, subString);
        }
        //если будет найден ноывй элемент, то сразу выйдем из цикла
        for (String subString: line.split(" ")) {
            if (originMap.getOrDefault(subString, "").equals("")) {
                return false;
            }
        }
        return true; */

    }
    private static String replaceSpec(String origin) {
        return origin.replace(".", "")
                .replace(",","")
                .replace("!", "")
                .replace("?", "");
    }
}
