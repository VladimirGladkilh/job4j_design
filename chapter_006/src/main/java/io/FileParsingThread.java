package io;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class FileParsingThread {
    private static File file = new File("sometext.txt");
    public static void main(String[] args) {
        Consumer<String> consumerContentWithoutUnicode = s -> s.chars().boxed().forEach(
                code -> {
                    if (code < 0x80) {
                        System.out.print(Character.toString(code));
                    }
                });
        Consumer<String> consumerAllContent = s -> System.out.print(s);
        Thread thread = new Thread(
                () -> {
                    ParseFile parseFile = new ParseFile();
                    parseFile.setFile(file.getAbsoluteFile());
                    try {
                        System.out.println("All Content");
                        parseFile.getContent(consumerAllContent);
                        System.out.println("ContentWithoutUnicode");
                        parseFile.getContent(consumerContentWithoutUnicode);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
        System.out.println("Main");
    }
}
