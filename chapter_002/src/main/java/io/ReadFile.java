package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}