package concurrent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FileLoader implements Callable<String> {
    private String file ;
    private int maxSize;
    private String loadFileName;

    public FileLoader(String[] args) {
        checkArgs(args);
    }

     public static void main(String[] args) throws ExecutionException, InterruptedException {
         //Получаем ExecutorService утилитного класса Executors с размером gпула потоков равному 10
         ExecutorService executor = Executors.newFixedThreadPool(10);
         // создаем экземпляр FileLoader
         Callable<String> callable = new FileLoader(args);
         System.out.println("call " + callable.toString());
         Future<String> future = executor.submit(callable);
         try {
             // печатаем в консоль возвращенное значение Future
             // будет задержка в 1 секунду, потому что Future.get()
             // ждет пока таск закончит выполнение
             System.out.println("Complete: " + future.get());
         } catch (InterruptedException | ExecutionException e) {
             throw e;
         }
         executor.shutdown();

     }

    private void load() throws IOException, InterruptedException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(this.file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(this.loadFileName)) {
            byte[] dataBuffer = new byte[maxSize * 1024];
            int bytesRead;
            long start = System.currentTimeMillis();
            System.out.println(this);
            while ((bytesRead = in.read(dataBuffer, 0, maxSize * 1024)) != -1) {
                long finish = System.currentTimeMillis();
                if (finish - start < 1000) {
                    Thread.sleep( (finish - start));
                    start = System.currentTimeMillis();
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                fileOutputStream.flush();
                System.out.println("\r" + bytesRead + " " + this.loadFileName);
            }
        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    private void checkArgs(String[] args)   {
        try {
            this.file = args[0];
            this.maxSize = Integer.parseInt(args[1]);
            this.loadFileName = args[2];
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String call() throws Exception {
        load();
        if (Files.exists(Paths.get(loadFileName))) {
            return "Load: " + loadFileName ;
        }
        return "Load error";
    }
}
