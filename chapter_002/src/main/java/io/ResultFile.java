package io;

import java.io.FileOutputStream;
import java.util.StringJoiner;

public class ResultFile {
    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (int i = 1; i < 10; i++) {
            StringJoiner sk = new StringJoiner("\t");
            for (int k = 1; k < 10; k++) {
                sk.add(k + "*" + i + "=" + k * i);
            }
            sj.add(sk.toString());
        }
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(sj.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
