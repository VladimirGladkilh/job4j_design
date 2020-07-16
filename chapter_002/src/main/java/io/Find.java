package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Find {
    private static final Logger LOG = LoggerFactory.getLogger(Find.class.getName());

    public static void main(String[] args) {
        Param param = new Param(args);
        if (param.valid())  {
            Path root = Paths.get(param.directory());
            List<Path> sourceFiles = null;
            try {
                Predicate<Path> predicate = preparePredicate(param);
                sourceFiles = getPathList(root, predicate);
            } catch (IOException e) {
                LOG.error("Get path error", e);
            }
            resultSourceFiles(sourceFiles, param.output());
            LOG.info("Complete");
        } else {
            LOG.error("You have invalid params. Write java -jar find.jar -d=c:/ -n=file.txt -m=*.mask -r=regexp -o=log.txt");
        }
    }

    private static Predicate<Path> preparePredicate(Param param) {
        if (!param.fileName().equals("")) {
            return path -> path.toFile().getName().toLowerCase().equals(param.fileName().toLowerCase());
        } else {
            if (!param.mask().equals("")) {
                String[] searcher = param.mask().split("\\.");
                if (searcher[0].equals("*")) {
                    return path -> path.toFile().getName().endsWith(searcher[1]);
                } else {
                    if (searcher[1].equals("*")) {
                        return path -> path.toFile().getName().startsWith(searcher[0]);
                    } else {
                        return path -> path.toFile().getName().toLowerCase().equals(param.mask().toLowerCase());
                    }
                }
            } else {
                return path -> path.toFile().getName().matches(param.regexp());
            }
        }
    }

    private static void resultSourceFiles(List<Path> sourceFiles, String output) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (Path file: sourceFiles) {
            sj.add(file.toFile().getAbsolutePath());
        }
        writeToLog(output, sj.toString());
    }

    private static List<Path> getPathList(Path root, Predicate<Path> predicate) throws IOException {
        SearchFiles searcher = new SearchFiles(predicate);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }




    private static void writeToLog(String fileName, String text) {
        try (FileWriter writer = new FileWriter(fileName, true);) {
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text + System.lineSeparator());
            bufferWriter.close();
        }
        catch (IOException e) {
            LOG.error("Write to log file", e);
        }
    }
}
