package concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileLoader {
    private static String file;
    private static int maxSize;
    public static void main(String[] args)  {
        if (checkArgs(args)) {
          load();
        } else {
            System.out.println("Incorrect parameters");
        }
    }

    private static void load() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("new_chatLog.txt")) {
            byte[] dataBuffer = new byte[maxSize * 1024];
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, maxSize * 1024)) != -1) {
                long finish = System.currentTimeMillis();
                if (finish - start < 1000) {
                    Thread.sleep(1000 - (finish - start));
                    start = System.currentTimeMillis();
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                System.out.println(bytesRead);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Complete");
    }

    private static boolean checkArgs(String[] args)  {
        if (args.length < 2) {
            return false;
        }
        try {
            file = args[0];
            maxSize = Integer.parseInt(args[1]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
