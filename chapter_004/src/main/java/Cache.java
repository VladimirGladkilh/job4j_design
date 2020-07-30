import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class Cache {
    private static boolean sleep = true;
    static final String COMMAND_STOP = "exit";
    static final String COMMAND_GC = "gc";
    private static SimpleCacheHashMap cacheHashMap = new SimpleCacheHashMap();
    private static final Path root = Paths.get("C:\\temp");
    private static final Logger LOG = LoggerFactory.getLogger(Cache.class.getName());

    public static void main(String[] args) {

        try {
            dialog();
        } catch (IOException e) {
            LOG.error("Dialog init error", e);
        }

    }
    private static void dialog() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя файла для отображения содержимого, gc для запуска GC или exit для выхода");
        while (sleep) {
            analize(scanner.nextLine());
            info();
        }
        System.out.println("Пока, пока");
    }

    /**
     * После каждой операции мониторим состояние памяти. При Xmx4m по мере приближения к 3.5 мб
     * ссылки из мапы начинают пропадать
     */
    private static void info() {
        int mb = 1024 ;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Memory info");
        System.out.println("Total = " + runtime.totalMemory() / mb );
        System.out.println("Used  = " + (runtime.totalMemory() - runtime.freeMemory()) / mb  );
        System.out.println("Free  = " + runtime.freeMemory() / mb );
    }

    /**
     * Ищем файл по имени в кэше. если его нет, то ищем его в каталоге
     * @param command
     */
    private static void analize(String command) throws IOException {
        if (command.toLowerCase().equals(COMMAND_STOP)) {
            sleep = false;
        } else {
            if (command.toLowerCase().equals(COMMAND_GC)) {
                System.out.println("Run GC");
                System.gc();
            } else {
                String fileInnerText = "";
                fileInnerText = searchInCache(command);
                if (fileInnerText.equals("")) {
                    System.out.println("File not found in cache. Search in directory");
                    fileInnerText = searchInDirectory(command);
                }
                if (fileInnerText.equals("")) {
                    System.out.println("File not exist");
                } else {
                    System.out.println(fileInnerText);
                }
            }
                
        }
    }

    private static String searchInDirectory(String command) throws IOException {

        String innerText = "";
        Predicate<Path> predicate = path -> path.toFile().getName().toLowerCase().equals(command);
        List<Path> sourceFiles = getPathList(root, predicate);
        for (Path file: sourceFiles) {
            innerText = readFileText(file.toFile().getAbsolutePath());
            cacheHashMap.insert(command, innerText);
        }
        return innerText;
    }

    private static String readFileText(String filePath) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
            in.lines().forEach(s -> sj.add(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sj.toString();
    }

    private static List<Path> getPathList(Path rootFolder, Predicate<Path> predicate) throws IOException {
        SearchFiles searcher = new SearchFiles(predicate);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static String searchInCache(String command) {
        String innerText = (String) cacheHashMap.get(command);
        return innerText != null ? innerText : "";
    }
}
