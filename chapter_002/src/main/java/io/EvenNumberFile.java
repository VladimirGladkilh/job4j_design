package io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (line.length() > 0) {
                    int val = Integer.parseInt(line);
                    if (val % 2 == 0) {
                        System.out.println(val + " четное число");
                    } else {
                        System.out.println(val + " нечетное число");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
