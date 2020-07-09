package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgZip {

    private final Map<String, String> params = new HashMap<>();

    public ArgZip(String[] args) {
        for (String s:args
             ) {
            String[] arr = s.split("=");
            if (arr.length > 0 ) {
                params.put(arr[0].trim(), arr[1].trim());
            }
        }

    }

    /**
     * Получено 3 и более параметра и среди них есть нужные нам
     * @return
     */
    public boolean valid() {
        return params.size() >= 3 && !params.get("-d").equals("")
                && !params.get("-o").equals("") && !params.get("-e").equals("");
    }

    public String directory() {
        return params.get("-d");
    }

    public String exclude() {
        return params.get("-e");
    }

    public String output() {
        return params.get("-o");
    }
}
