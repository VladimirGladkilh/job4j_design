package io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringJoiner;

public class Analizy {
    public void unavailable(String source, String target) {

        StringJoiner sj = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String[] start = {""};
            in.lines().forEach(s -> {
                String status = s.split(" ")[0];
                String time = s.split(" ")[1];
                if ((status.equals("400") || status.equals("500"))) {
                    if (start[0].equals("")) {
                        start[0] = time;
                    }
                } else {
                    if (!start[0].equals("")) { //чтобы не писать в лог с пустой датой старта
                        sj.add(start[0] + "; " + time);
                    }
                    start[0] = "";
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeTarget(sj.toString(), target);
    }

    private void writeTarget(String data, String fileName) {
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            out.write(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}