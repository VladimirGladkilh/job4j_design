package io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(s -> output.append(s));
        } catch (Exception e) {
            throw e;
        }
        return output.toString();
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file, Charset.forName("UTF8")))) {
            in.lines().forEach(s -> s.chars().boxed().forEach(
                    code -> {
                        if (code < 0x80) {
                            output.append(Character.toString(code));
                        }
                    })
            );
        } catch (Exception e) {
            throw e;
        }
        return output.toString();
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