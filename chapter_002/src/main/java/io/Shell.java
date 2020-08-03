package io;


import java.util.*;
import java.util.stream.Collectors;

public class Shell {

    private Stack<String> root = new Stack<>();

    public void cd(String path) {
        for (String p : path.split("/")) {
            if (p.equals("..")) {
                backStep();
            } else {
                root.push(p);
            }
        }
    }
    private void backStep() {
        root.pop();
    }

    public String pwd() {
        if (root.isEmpty() ) {
            return "/";
        }
        return '/' + root.stream().collect(Collectors.joining("/"));
    }

}