package io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainZip {
    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        if (argZip.valid())  {
            Path root = Paths.get(argZip.directory());
            List<Path> sourceFiles = getPathList(root, argZip.exclude());
            File destFile = new File(root + File.separator + argZip.output());
            Zip zip = new Zip();
            zip.packFiles(sourceFiles, destFile);
            System.out.println("Complete");
        } else {
            System.out.println("You have invalid params");
        }
    }
    private static List<Path> getPathList(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(path -> !path.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
