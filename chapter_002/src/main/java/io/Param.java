package io;

import java.util.HashMap;
import java.util.Map;

public class Param {
    private final Map<String, String> params = new HashMap<>();

    public Param(String[] args) {
        for (String s : args
        ) {
            String[] arr = s.split("=");
            if (arr.length > 1) {
                params.put(arr[0].trim(), arr[1].trim());
            }
        }
    }

    /**
     * Получено 3 и более параметра и среди них есть нужные нам
     *
     * @return
     */
    public boolean valid() {
        return params.size() >= 3 && !params.getOrDefault("-d", "").equals("")
                && !params.getOrDefault("-o", "").equals("")
                && (!params.getOrDefault("-n", "").equals("")
                || !params.getOrDefault("-m", "").equals("")
                || !params.getOrDefault("-r", "").equals(""));
    }

    public String directory() {
        return params.get("-d");
    }

    public String fileName() {
        return params.getOrDefault("-n", "");
    }

    public String mask() {
        return params.getOrDefault("-m", "");
    }

    public String regexp() {
        return params.getOrDefault("-r", "");
    }

    public String output() {
        return params.getOrDefault("-o", "");
    }
}
