package io;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Shell {

    private StringJoiner root = new StringJoiner("/");

    public void cd(String path) {
        for (String p : path.split("/")) {
            if (p.equals("..")) {
                backStep();
            } else {
                root.add(p);
            }
        }
    }
    private void backStep() {
        String[] path = root.toString().split("/");
        root = new StringJoiner("/");
        for (int i = 0; i < path.length -1; i ++) {
            root.add(path[i]);
        }
    }

    public String pwd() {
        return "/" + root.toString();
    }

    public static void main(String[] args) {

        int num = 1290123;
        long numUpper = (long) num;
        short numLower = (short) num;
        System.out.println(numUpper);
        System.out.println(numLower);
        isSorted(new int[] {1,2,3});
    }
    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i ++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}