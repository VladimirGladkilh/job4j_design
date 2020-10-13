package io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ParseFile {
    private File file;
    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized void getContent(Consumer<String> consumer) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(consumer);
        } catch (Exception e) {
            throw e;
        }
        System.out.println();
    }

    public synchronized void saveContent(String content) throws IOException {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            out.write(content);
        } catch (Exception e) {
            throw e;
        }
    }


}