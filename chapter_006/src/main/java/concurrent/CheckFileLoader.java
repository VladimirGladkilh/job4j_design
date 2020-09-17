package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CheckFileLoader {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String[] arg1 = new String[]{"https://github.com/VladimirGladkilh/job4j_design/blob/master/result.txt",
        "20", "first.txt"};
        String[] arg2 = new String[]{"https://github.com/VladimirGladkilh/job4j_design/blob/master/new_chatLog.txt",
                "100", "second.txt"};
        String[] arg3 = new String[]{"https://github.com/VladimirGladkilh/job4j_design/blob/master/result.txt",
                "200", "third.txt"};
        //Получаем ExecutorService утилитного класса Executors с размером gпула потоков равному 10
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //создаем список с Future, которые ассоциированы с Callable
        List<Future<String>> list = new ArrayList<Future<String>>();
        // создаем экземпляры FileLoader
        Callable<String> callable = new FileLoader(arg1);
        Callable<String> callable2 = new FileLoader(arg2);
        Callable<String> callable3 = new FileLoader(arg3);
       // for(int i=0; i< 100; i++){
            //сабмитим Callable таски, которые будут
            //выполнены пулом потоков
            Future<String> future = executor.submit(callable);

            //добавляя Future в список,
            //мы сможем получить результат выполнения
            list.add(future);
       // }
        list.add(executor.submit(callable2));
        list.add(executor.submit(callable3));
        for(Future<String> fut : list){
            try {
                // печатаем в консоль возвращенное значение Future
                // будет задержка в 1 секунду, потому что Future.get()
                // ждет пока таск закончит выполнение
                System.out.println(fut.get(10000l, TimeUnit.MILLISECONDS));
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
