package io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Analizy {
    public void unavailable(String source, String target) {

        StringJoiner sj = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            List<String> lines = new ArrayList<String>();
            in.lines().forEach(lines::add);
            String start = "";
            String stop;
            for (String line : lines) {
                String status = line.split(" ")[0];
                String time = line.split(" ")[1];
                if ((status.equals("400") || status.equals("500")) && start.equals("")) {
                    start = time;
                }
                if ((!status.equals("400") && !status.equals("500")) && !start.equals("")) {
                    stop = time;
                    sj.add(start + "; " + stop);
                    start = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (FileOutputStream out = new FileOutputStream(target)) {
            out.write(sj.toString().getBytes());
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