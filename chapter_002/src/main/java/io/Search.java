package io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER EXT");
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("Searched extension is null. Usage java -jar dir.jar ROOT_FOLDER EXT");
        }
        String folder = args[0];
        String ext = args[1];
        Path start = Paths.get(folder);
        search(start, ext).forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {

        SearchFiles searcher = new SearchFiles(path -> path.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();

    }
}