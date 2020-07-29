package io;


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
}