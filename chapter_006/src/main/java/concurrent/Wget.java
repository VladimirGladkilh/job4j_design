package concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread first = new Thread(() -> {
           try {
               for (int i = 0; i < 100; i++) {
                   System.out.print("\rLoaded: " + i + "%");
                   Thread.sleep(100);
               }
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
        });
        first.start();
    }
}
