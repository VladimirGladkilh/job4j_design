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
            SearchFiles searcher = new SearchFiles(path -> !path.toFile().getName().endsWith(argZip.exclude()));
            Files.walkFileTree(root, searcher);
            List<Path> sourceFiles = searcher.getPaths();
            File destFile = new File(root + "\\" + argZip.output());
            Zip zip = new Zip();
            zip.packFiles(sourceFiles, destFile);
            System.out.println("Complete");
        } else {
            System.out.println("You have invalid params");
        }
    }
}
