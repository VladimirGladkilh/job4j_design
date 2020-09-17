package io;

import java.io.File;
import java.io.IOException;

public class FileParsingThread {
    private static File file = new File("sometext.txt");
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    ParseFile parseFile = new ParseFile();
                    parseFile.setFile(file.getAbsoluteFile());
                    try {
                        System.out.println(parseFile.getContent());
                        System.out.println(parseFile.getContentWithoutUnicode());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
        System.out.println("Main");
    }
}
