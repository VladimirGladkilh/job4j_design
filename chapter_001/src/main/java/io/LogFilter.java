package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> res = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<String>();
            in.lines().forEach(lines::add);
            for (String line : lines) {
                int lineLength = line.split(" ").length;
                if (line.split(" ")[lineLength - 2].equals("404")) {
                    res.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
