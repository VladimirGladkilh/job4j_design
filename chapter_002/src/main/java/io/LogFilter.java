package io;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> res = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(s -> {
                int lineLength = s.split(" ").length;
                if (s.split(" ")[lineLength - 2].equals("404")) {
                    res.add(s);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }


    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            out.write(log.stream().collect(Collectors.joining(System.lineSeparator())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
