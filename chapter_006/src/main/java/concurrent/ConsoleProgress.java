package concurrent;


public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int i = 0;
        String[] process = new String[]{"|", "/", "-", "\\"};
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\rload: " + process[i % 4]);
            i++;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args)  {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(2000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt(); //
        System.out.println("End");
    }
}
