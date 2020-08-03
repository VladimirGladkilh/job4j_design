package io;


import java.util.*;

public class Shell {

    private final Stack<String> root = new Stack<>();

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
        return '/' + String.join("/", root);
    }

}