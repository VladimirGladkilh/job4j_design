package io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, "java").forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {

        SearchFiles searcher = new SearchFiles();
        searcher.ext = ext;
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();

    }
}